package com.assessment.http.requests.controller;

import com.assessment.http.requests.model.dtos.HostSitesStats;
import com.assessment.http.requests.model.dtos.HostStats;
import com.assessment.http.requests.service.StatsGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hosts")
public class HostController {

    @Autowired
    StatsGenerationService statsGenerationService;

    @GetMapping("/top/{numberOfHosts}")
    public List<HostStats> getTopHosts(@PathVariable int numberOfHosts) {
        return statsGenerationService.getTopHosts(numberOfHosts);
    }

    @GetMapping("/top/{numberOfHosts}/sites/top/{numberOfSites}")
    public List<HostSitesStats> getTopSitesOfTopHosts(@PathVariable int numberOfHosts,
                                                      @PathVariable int numberOfSites) {
        return statsGenerationService.getTopSitesTopHosts(numberOfHosts, numberOfSites);
    }
}
