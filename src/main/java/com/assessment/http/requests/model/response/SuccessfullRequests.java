package com.assessment.http.requests.model.response;

public class SuccessfullRequests {

    private String percentage;

    public SuccessfullRequests(String percentage) {
        this.percentage = percentage;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
