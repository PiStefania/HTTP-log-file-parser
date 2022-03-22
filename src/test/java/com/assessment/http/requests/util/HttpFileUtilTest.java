package com.assessment.http.requests.util;

import com.assessment.http.requests.model.HttpLogLine;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HttpFileUtilTest {

    @Test
    void readHttpLogFileSuccessful() {
        List<HttpLogLine> httpLogLines = HttpFileUtil.readHttpLogFile("httpLogFile.log");
        assertNotNull(httpLogLines);
        assertEquals(5, httpLogLines.size());
        assertEquals("uplherc.upl.com", httpLogLines.get(0).getHost());
        assertEquals("uplherc.upl.com", httpLogLines.get(0).getHost());
    }

    @Test
    void readHttpLogFileException() {
        List<HttpLogLine> httpLogLines = HttpFileUtil.readHttpLogFile("dummy.log");
        assertNull(httpLogLines);
    }
}
