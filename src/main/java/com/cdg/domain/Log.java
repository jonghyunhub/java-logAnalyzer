package com.cdg.domain;

import java.util.Objects;

public class Log {

    private String statusCode;

    private String url;

    private String browser;

    private String timestamp;

    public Log(String statusCode, String url, String browser, String timestamp) {

        this.statusCode = StatusCodeType.checkCodeInType(statusCode);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(statusCode, log.statusCode) && Objects.equals(url, log.url) && Objects.equals(browser, log.browser) && Objects.equals(timestamp, log.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, url, browser, timestamp);
    }
}
