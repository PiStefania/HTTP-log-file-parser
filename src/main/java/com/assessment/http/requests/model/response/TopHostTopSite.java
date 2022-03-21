package com.assessment.http.requests.model.response;

import java.util.List;

public class TopHostTopSite {
    private String host;
    private Integer requests;
    List<TopSite> topSites;

    public TopHostTopSite(String host, Integer requests) {
        this.host = host;
        this.requests = requests;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getRequests() {
        return requests;
    }

    public void setRequests(Integer requests) {
        this.requests = requests;
    }

    public List<TopSite> getTopSites() {
        return topSites;
    }

    public void setTopSites(List<TopSite> topSites) {
        this.topSites = topSites;
    }
}
