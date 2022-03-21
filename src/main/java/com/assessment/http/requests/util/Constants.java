package com.assessment.http.requests.util;

public class Constants {

    private Constants() {
    }

    public static final String REQUESTS_PARAM_TYPE_SUCCESSFUL = "successful";
    public static final String REQUESTS_PARAM_TYPE_UNSUCCESSFUL = "unsuccessful";
    public static final int LOG_LINE_PARTS = 9;
    public static final String HTTP_LOG_LINE_PATTERN = "^(\\S+) (\\S+) (\\S+) \\[([\\w:\\/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+)\\s*(\\S+)?\\s*\" (\\d{3}) (\\S+)$";
}
