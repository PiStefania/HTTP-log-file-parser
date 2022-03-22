package com.assessment.http.requests.controller;

import com.assessment.http.requests.helper.JsonUtil;
import com.assessment.http.requests.model.dtos.SiteStats;
import com.assessment.http.requests.service.StatsGenerationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SiteControllerTest {

    @InjectMocks
    private SiteController siteController;

    @Mock
    private StatsGenerationService statsGenerationService;

    @Test
    void getTop10SitesSuccessfulResponse() throws Exception {
        when(statsGenerationService.getTopSites(10))
                .thenReturn(JsonUtil.jsonToListDto("samples/top10Sites.json", SiteStats[].class));
        List<SiteStats> siteStatsList = siteController.getTopSites(10);
        assertEquals(10, siteStatsList.size());
        assertEquals(97391, siteStatsList.get(0).getRequests());
    }
}
