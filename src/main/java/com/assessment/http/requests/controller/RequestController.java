package com.assessment.http.requests.controller;

import com.assessment.http.requests.model.dtos.RequestsPercentage;
import com.assessment.http.requests.service.StatsGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("requests")
public class RequestController {

    private final StatsGenerationService statsGenerationService;

    @Autowired
    public RequestController(StatsGenerationService statsGenerationService) {
        this.statsGenerationService = statsGenerationService;
    }

    @GetMapping
    public RequestsPercentage getPercentageOfRequests(@RequestParam String type) {
        return statsGenerationService.getPercentageOfRequests(type);
    }
}
