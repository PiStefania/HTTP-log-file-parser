package com.assessment.http.requests.controller;

import com.assessment.http.requests.helper.JsonUtil;
import com.assessment.http.requests.model.dtos.RequestsPercentage;
import com.assessment.http.requests.service.StatsGenerationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestControllerTest {

    @InjectMocks
    private RequestController requestController;

    @Mock
    private StatsGenerationService statsGenerationService;

    @Test
    void getPercentageOfSuccessfulRequests() throws Exception {
        when(statsGenerationService.getPercentageOfRequests("successful"))
                .thenReturn(JsonUtil.jsonToDto("requestsPercentage.json", RequestsPercentage.class));
        RequestsPercentage requestsPercentage = requestController.getPercentageOfRequests("successful");
        assertNotNull(requestsPercentage);
        assertEquals("50.22", requestsPercentage.getPercentage());
    }

    @Test
    void getPercentageOfUnsuccessfulRequests() throws Exception {
        when(statsGenerationService.getPercentageOfRequests("unsuccessful"))
                .thenReturn(JsonUtil.jsonToDto("requestsPercentage.json", RequestsPercentage.class));
        RequestsPercentage requestsPercentage = requestController.getPercentageOfRequests("unsuccessful");
        assertNotNull(requestsPercentage);
        assertEquals("50.22", requestsPercentage.getPercentage());
    }

    @Test
    void getPercentageOfNotRegisteredType() {
        RequestsPercentage requestsPercentage = requestController.getPercentageOfRequests("unsuccessful");
        assertNull(requestsPercentage);
    }
}
