package com.assessment.http.requests.service;

import com.assessment.http.requests.model.HttpLogLine;
import com.assessment.http.requests.model.dtos.HostSitesStats;
import com.assessment.http.requests.model.dtos.HostStats;
import com.assessment.http.requests.model.dtos.RequestsPercentage;
import com.assessment.http.requests.model.dtos.SiteStats;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatsGenerationServiceTest {

    @InjectMocks
    StatsGenerationService statsGenerationService;

    @Mock
    HttpLogSchedulerService httpLogSchedulerService;

    @Test
    void getTopSitesSuccessful() {
        List<HttpLogLine> httpLogLines = new ArrayList<>();
        httpLogLines.add(new HttpLogLine.Builder().withEndpoint("/images/ksclogo-medium.gif").build());
        httpLogLines.add(new HttpLogLine.Builder().withEndpoint("/images/ksclogo-medium.gif").build());
        httpLogLines.add(new HttpLogLine.Builder().withEndpoint("/images/ksclogo-medium.gif").build());
        httpLogLines.add(new HttpLogLine.Builder().withEndpoint("/images/ksclogo-medium.gif").build());
        httpLogLines.add(new HttpLogLine.Builder().withEndpoint("/").build());
        when(httpLogSchedulerService.getHttpLogLines()).thenReturn(httpLogLines);
        List<SiteStats> topSites = statsGenerationService.getTopSites(2);
        assertNotNull(topSites);
        assertEquals(2, topSites.size());
        assertEquals("/images/ksclogo-medium.gif", topSites.get(0).getEndpoint());
        assertEquals(4, topSites.get(0).getRequests());
        assertEquals("/", topSites.get(1).getEndpoint());
    }

    @Test
    void getPercentageOfRequestsSuccessful() {
        List<HttpLogLine> httpLogLines = new ArrayList<>();
        httpLogLines.add(new HttpLogLine.Builder().withStatusCode("200").build());
        httpLogLines.add(new HttpLogLine.Builder().withStatusCode("500").build());
        when(httpLogSchedulerService.getHttpLogLines()).thenReturn(httpLogLines);
        RequestsPercentage requestsPercentage = statsGenerationService.getPercentageOfRequests("successful");
        assertNotNull(requestsPercentage);
        assertEquals("50.00", requestsPercentage.getPercentage());
    }

    @Test
    void getPercentageOfRequestsUnsuccessful() {
        List<HttpLogLine> httpLogLines = new ArrayList<>();
        httpLogLines.add(new HttpLogLine.Builder().withStatusCode("200").build());
        httpLogLines.add(new HttpLogLine.Builder().withStatusCode("300").build());
        httpLogLines.add(new HttpLogLine.Builder().withStatusCode("204").build());
        httpLogLines.add(new HttpLogLine.Builder().withStatusCode("500").build());
        when(httpLogSchedulerService.getHttpLogLines()).thenReturn(httpLogLines);
        RequestsPercentage requestsPercentage = statsGenerationService.getPercentageOfRequests("unsuccessful");
        assertNotNull(requestsPercentage);
        assertEquals("25.00", requestsPercentage.getPercentage());
    }

    @Test
    void getPercentageOfRequestsUnknownType() {
        List<HttpLogLine> httpLogLines = new ArrayList<>();
        httpLogLines.add(new HttpLogLine.Builder().withStatusCode("200").build());
        httpLogLines.add(new HttpLogLine.Builder().withStatusCode("500").build());
        when(httpLogSchedulerService.getHttpLogLines()).thenReturn(httpLogLines);
        RequestsPercentage requestsPercentage = statsGenerationService.getPercentageOfRequests("dummy");
        assertNull(requestsPercentage);
    }

    @Test
    void getPercentageOfRequestsBypassException() {
        List<HttpLogLine> httpLogLines = new ArrayList<>();
        httpLogLines.add(new HttpLogLine.Builder().withStatusCode("200").build());
        httpLogLines.add(new HttpLogLine.Builder().withStatusCode("500").build());
        httpLogLines.add(new HttpLogLine.Builder().withStatusCode(null).build());
        when(httpLogSchedulerService.getHttpLogLines()).thenReturn(httpLogLines);
        RequestsPercentage requestsPercentage = statsGenerationService.getPercentageOfRequests("successful");
        assertNotNull(requestsPercentage);
        assertEquals("50.00", requestsPercentage.getPercentage());
    }

    @Test
    void getTopHostsSuccessful() {
        List<HttpLogLine> httpLogLines = new ArrayList<>();
        httpLogLines.add(new HttpLogLine.Builder().withHost("uplherc.upl.com").build());
        httpLogLines.add(new HttpLogLine.Builder().withHost("uplherc.upl.com").build());
        httpLogLines.add(new HttpLogLine.Builder().withHost("uplherc.upl.com").build());
        httpLogLines.add(new HttpLogLine.Builder().withHost("uplherc.upl.com").build());
        httpLogLines.add(new HttpLogLine.Builder().withHost("biochem1.biochem.hmc.psu.edu").build());
        when(httpLogSchedulerService.getHttpLogLines()).thenReturn(httpLogLines);
        List<HostStats> topHosts = statsGenerationService.getTopHosts(2);
        assertNotNull(topHosts);
        assertEquals(2, topHosts.size());
        assertEquals("uplherc.upl.com", topHosts.get(0).getHost());
        assertEquals(4, topHosts.get(0).getRequests());
        assertEquals("biochem1.biochem.hmc.psu.edu", topHosts.get(1).getHost());
    }

    @Test
    void getTopSitesTopHostsSuccessful() {
        List<HttpLogLine> httpLogLines = new ArrayList<>();
        httpLogLines
                .add(new HttpLogLine.Builder().withHost("uplherc.upl.com").withEndpoint("/images/ksclogo-medium.gif")
                        .build());
        httpLogLines
                .add(new HttpLogLine.Builder().withHost("uplherc.upl.com").withEndpoint("/images/ksclogo-medium.gif")
                        .build());
        httpLogLines
                .add(new HttpLogLine.Builder().withHost("uplherc.upl.com").withEndpoint("/images/ksclogo-medium.gif")
                        .build());
        httpLogLines.add(new HttpLogLine.Builder().withHost("uplherc.upl.com").withEndpoint("/").build());
        httpLogLines.add(new HttpLogLine.Builder().withHost("biochem1.biochem.hmc.psu.edu").withEndpoint("/ksc.html")
                .build());
        when(httpLogSchedulerService.getHttpLogLines()).thenReturn(httpLogLines);
        List<HostSitesStats> topHostsTopSites = statsGenerationService.getTopSitesTopHosts(2, 2);
        assertNotNull(topHostsTopSites);
        assertEquals(2, topHostsTopSites.size());
        assertEquals(2, topHostsTopSites.get(0).getTopSites().size());
        assertEquals("uplherc.upl.com", topHostsTopSites.get(0).getHost());
        assertEquals(1, topHostsTopSites.get(1).getTopSites().size());
        assertEquals("biochem1.biochem.hmc.psu.edu", topHostsTopSites.get(1).getHost());
        assertEquals("/images/ksclogo-medium.gif", topHostsTopSites.get(0).getTopSites().get(0).getEndpoint());
        assertEquals(3, topHostsTopSites.get(0).getTopSites().get(0).getRequests());
    }
}
