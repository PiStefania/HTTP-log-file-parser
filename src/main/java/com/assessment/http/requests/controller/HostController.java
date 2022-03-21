package com.assessment.http.requests.controller;

import com.assessment.http.requests.model.response.TopHost;
import com.assessment.http.requests.model.response.TopHostTopSite;
import com.assessment.http.requests.service.StatsGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HostController {

    @Autowired
    StatsGenerationService statsGenerationService;

    @GetMapping("hosts/top10")
    public List<TopHost> getTopHosts() {
        return statsGenerationService.getTopHosts();
    }

    @GetMapping("hosts/top10/sites/top5")
    public List<TopHostTopSite> getTopSitesOfTopHosts() {
        return statsGenerationService.getTopSitesTopHosts();
    }
}
