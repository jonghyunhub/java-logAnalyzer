package com.cdg;

public enum StatusCode {

    INVALID_PARM(10),
    OK(200),
    NOT_FOUND(404);


    private Integer value;

    StatusCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name();
    }
}
