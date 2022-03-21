package com.assessment.http.requests.model.dtos;

public class HostStats {
    private String host;
    private Integer requests;

    public HostStats(String host, Integer requests) {
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
}
