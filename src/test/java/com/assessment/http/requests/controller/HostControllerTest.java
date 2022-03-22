package com.assessment.http.requests.controller;

import com.assessment.http.requests.helper.JsonUtil;
import com.assessment.http.requests.model.dtos.HostSitesStats;
import com.assessment.http.requests.model.dtos.HostStats;
import com.assessment.http.requests.service.StatsGenerationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HostControllerTest {

    @InjectMocks
    HostController hostController;

    @Mock
    StatsGenerationService statsGenerationService;

    @Test
    void getTop10HostsSuccessfulResponse() throws IOException {
        when(statsGenerationService.getTopHosts(10))
                .thenReturn(JsonUtil.jsonToListDto("top10Hosts.json", HostStats[].class));
        List<HostStats> hostStats = hostController.getTopHosts(10);
        assertEquals(10, hostStats.size());
        assertEquals(6529, hostStats.get(0).getRequests());
    }

    @Test
    void getTop10HostsTop5SitesSuccessfulResponse() throws IOException {
        when(statsGenerationService.getTopSitesTopHosts(10, 5))
                .thenReturn(JsonUtil.jsonToListDto("top10HostsTop5Sites.json", HostSitesStats[].class));
        List<HostSitesStats> hostSitesStats = hostController.getTopSitesOfTopHosts(10, 5);
        assertEquals(10, hostSitesStats.size());
        assertEquals(6529, hostSitesStats.get(0).getRequests());
        assertEquals(5, hostSitesStats.get(0).getTopSites().size());
        assertEquals(1019, hostSitesStats.get(0).getTopSites().get(0).getRequests());

    }


}
