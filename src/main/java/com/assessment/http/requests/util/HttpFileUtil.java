package com.assessment.http.requests.util;

import com.assessment.http.requests.model.HttpLogLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.assessment.http.requests.util.Constants.HTTP_LOG_LINE_PATTERN;
import static com.assessment.http.requests.util.Constants.LOG_LINE_PARTS;

public class HttpFileUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpFileUtil.class);
    private static final Pattern pattern = Pattern.compile(HTTP_LOG_LINE_PATTERN);

    private HttpFileUtil() {
    }

    public static List<HttpLogLine> readHttpLogFile(String path) {
        List<HttpLogLine> httpLogLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new ClassPathResource(path).getFile()))) {
            String line = reader.readLine();
            while (line != null) {
                HttpLogLine httpLogLine = findHttpLogLineParts(line);
                if (httpLogLine != null) {
                    httpLogLines.add(httpLogLine);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            logger.error("Could not read file with title: {}", path, e);
            return new ArrayList<>();
        }
        return httpLogLines;
    }

    private static HttpLogLine findHttpLogLineParts(String logLine) {
        Matcher matcher = pattern.matcher(logLine);
        if (matcher.find() && matcher.groupCount() == LOG_LINE_PARTS) {
            return new HttpLogLine.Builder()
                    .withHost(matcher.group(1))
                    .withClientIdentd(matcher.group(2))
                    .withUserId(matcher.group(3))
                    .withDateTime(matcher.group(4))
                    .withMethod(matcher.group(5))
                    .withEndpoint(matcher.group(6))
                    .withProtocol(matcher.group(7))
                    .withStatusCode(matcher.group(8))
                    .withSize(matcher.group(9))
                    .build();
        } else {
            logger.error("HTTP log line is wrongly formatted. Line: {}", logLine);
            return null;
        }
    }

}
