package com.assessment.http.requests.model.response;

public class TopSite {
    private String endpoint;
    private int requests;

    public TopSite(String endpoint, int requests) {
        this.endpoint = endpoint;
        this.requests = requests;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public int getRequests() {
        return requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }
}
