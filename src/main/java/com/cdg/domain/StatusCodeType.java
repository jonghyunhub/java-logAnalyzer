package com.cdg.domain;

public enum StatusCodeType{

    NOT_FOUND("404"),
    OK("200"),
    INVALID_PARM("10");

    private String statusCode;

    StatusCodeType(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getKey() {
        return name();
    }

    public static String checkCodeInType(String code) {
        for (StatusCodeType value : values()) {
            if (value.statusCode.equals(code)) {
                return code;
            }
        }
        return null;  // 찾는 코드가 없는 경우
    }
}
