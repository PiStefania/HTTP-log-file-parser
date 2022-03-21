package com.assessment.http.requests.model.dtos;

import java.util.List;

public class HostSitesStats extends HostStats {

    List<SiteStats> topSites;

    public HostSitesStats(String host, Integer requests) {
        super(host, requests);
    }

    public List<SiteStats> getTopSites() {
        return topSites;
    }

    public void setTopSites(List<SiteStats> topSites) {
        this.topSites = topSites;
    }
}
