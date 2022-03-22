package com.assessment.http.requests.service;

import com.assessment.http.requests.model.HttpLogLine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class HttpLogSchedulerServiceTest {

    @Autowired
    HttpLogSchedulerService httpLogSchedulerService;

    @Test
    void updateHttpLogLinesCheck() {
        List<HttpLogLine> httpLogLines = httpLogSchedulerService.getHttpLogLines();
        assertNotNull(httpLogLines);
        assertEquals(5, httpLogLines.size());
        assertEquals("uplherc.upl.com", httpLogLines.get(0).getHost());
        assertEquals("uplherc.upl.com", httpLogLines.get(0).getHost());
    }
}
