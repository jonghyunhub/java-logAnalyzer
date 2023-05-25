package com.cdg.DataType;

public enum ServiceIDType {

    BLOG("blog"),
    BOOK("book"),
    IMAGE("image"),
    KNOWLEDGE("knowledge"),
    NEWS("news"),
    VCLIP("vclip");



    private String value;

    ServiceIDType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name();
    }
}
