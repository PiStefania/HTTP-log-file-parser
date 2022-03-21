package com.assessment.http.requests.model;

public class HttpLogLine {
    private String host;
    private String clientIdentd;
    private String userId;
    private String dateTime;
    private String method;
    private String endpoint;
    private String protocol;
    private String statusCode;
    private String size;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getClientIdentd() {
        return clientIdentd;
    }

    public void setClientIdentd(String clientIdentd) {
        this.clientIdentd = clientIdentd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public static class Builder {
        private String host;
        private String clientIdentd;
        private String userId;
        private String dateTime;
        private String method;
        private String endpoint;
        private String protocol;
        private String statusCode;
        private String size;

        public HttpLogLine build() {
            HttpLogLine httpLogLine = new HttpLogLine();
            httpLogLine.host = this.host;
            httpLogLine.clientIdentd = this.clientIdentd;
            httpLogLine.userId = this.userId;
            httpLogLine.dateTime = this.dateTime;
            httpLogLine.method = this.method;
            httpLogLine.endpoint = this.endpoint;
            httpLogLine.protocol = this.protocol;
            httpLogLine.statusCode = this.statusCode;
            httpLogLine.size = this.size;
            return httpLogLine;
        }

        public HttpLogLine.Builder withHost(String host) {
            this.host = host;
            return this;
        }

        public HttpLogLine.Builder withClientIdentd(String clientIdentd) {
            this.clientIdentd = clientIdentd;
            return this;
        }

        public HttpLogLine.Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public HttpLogLine.Builder withDateTime(String dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public HttpLogLine.Builder withMethod(String method) {
            this.method = method;
            return this;
        }

        public HttpLogLine.Builder withEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public HttpLogLine.Builder withProtocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public HttpLogLine.Builder withStatusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public HttpLogLine.Builder withSize(String size) {
            this.size = size;
            return this;
        }

    }
}
