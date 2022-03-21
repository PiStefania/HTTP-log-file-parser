package com.assessment.http.requests.controller;

import com.assessment.http.requests.model.response.SuccessfullRequests;
import com.assessment.http.requests.model.response.TopSite;
import com.assessment.http.requests.service.StatsGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController {

    @Autowired
    StatsGenerationService statsGenerationService;

    @GetMapping("requests/successful")
    public SuccessfullRequests getPercentageOfSuccessfulRequests() {
        return statsGenerationService.getSuccessfullRequests();
    }

    @GetMapping("requests/unsuccessful")
    public SuccessfullRequests getPercentageOfUnsuccessfulRequests() {
        return statsGenerationService.getUnsuccessfulRequests();
    }
}
