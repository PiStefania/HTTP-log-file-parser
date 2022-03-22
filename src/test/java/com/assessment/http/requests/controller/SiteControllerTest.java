package com.assessment.http.requests.controller;

import com.assessment.http.requests.helper.JsonUtil;
import com.assessment.http.requests.model.dtos.SiteStats;
import com.assessment.http.requests.service.StatsGenerationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SiteController.class)
public class SiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatsGenerationService statsGenerationService;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(statsGenerationService.getTopSites(10))
                .thenReturn(JsonUtil.jsonToListDto("top10Sites.json", SiteStats[].class));
        this.mockMvc.perform(get("/sites/top/10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.endpoint[0]").value("/images/NASA-logosmall.gif"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.requests[0]").value(97391));
    }
}
