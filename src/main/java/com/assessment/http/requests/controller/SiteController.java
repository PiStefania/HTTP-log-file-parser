package com.assessment.http.requests.controller;

import com.assessment.http.requests.model.response.TopSite;
import com.assessment.http.requests.service.StatsGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SiteController {

    @Autowired
    StatsGenerationService statsGenerationService;

    @GetMapping("sites/top10")
    public List<TopSite> getTopSites() {
        return statsGenerationService.getTopSites();
    }
}
