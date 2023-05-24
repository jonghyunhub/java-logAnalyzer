package com.cdg.domain;

public class Log {

    private String statusCode;

    private String url;

    private String browser;

    private String timestamp;

    public Log(String statusCode, String url, String browser, String timestamp) {
        this.statusCode = statusCode;
        this.url = url;
        this.browser = browser;
        this.timestamp = timestamp;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getUrl() {
        return url;
    }

    public String getBrowser() {
        return browser;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
