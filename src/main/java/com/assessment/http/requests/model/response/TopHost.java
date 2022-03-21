package com.assessment.http.requests.model.response;

public class TopHost {
    private String host;
    private Integer requests;

    public TopHost(String host, Integer requests) {
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
