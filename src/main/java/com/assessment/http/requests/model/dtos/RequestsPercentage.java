package com.assessment.http.requests.model.dtos;

public class RequestsPercentage {

    private String percentage;

    public RequestsPercentage() {
    }

    public RequestsPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
