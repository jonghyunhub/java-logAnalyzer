package com.cdg.DataType;

public enum StatusCodeType {

    INVALID_PARM(10),
    OK(200),
    NOT_FOUND(404);


    private Integer value;

    StatusCodeType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name();
    }
}
