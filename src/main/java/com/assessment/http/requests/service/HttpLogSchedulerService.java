package com.assessment.http.requests.service;

import com.assessment.http.requests.model.HttpLogLine;
import com.assessment.http.requests.util.HttpFileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HttpLogSchedulerService {

    @Value("${log.file.path}")
    private String logFilePath;

    private List<HttpLogLine> httpLogLines = new ArrayList<>();

    @Scheduled(fixedDelayString = "${scheduler.fixed.delay}")
    private void updateHttpLogLines() {
        httpLogLines = HttpFileUtil.readHttpLogFile(logFilePath);
    }

    public List<HttpLogLine> getHttpLogLines() {
        return httpLogLines;
    }

    public void setHttpLogLines(List<HttpLogLine> httpLogLines) {
        this.httpLogLines = httpLogLines;
    }
}
