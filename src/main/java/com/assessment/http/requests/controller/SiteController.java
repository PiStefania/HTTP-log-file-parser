package com.assessment.http.requests.controller;

import com.assessment.http.requests.model.dtos.SiteStats;
import com.assessment.http.requests.service.StatsGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sites")
public class SiteController {

    private final StatsGenerationService statsGenerationService;

    @Autowired
    public SiteController(StatsGenerationService statsGenerationService) {
        this.statsGenerationService = statsGenerationService;
    }

    @GetMapping("/top/{numberOfSites}")
    public List<SiteStats> getTopSites(@PathVariable int numberOfSites) {
        return statsGenerationService.getTopSites(numberOfSites);
    }
}
