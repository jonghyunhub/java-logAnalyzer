package com.cdg.domain;

public enum ServiceIdType {
    BLOG("blog"),
    BOOK("book"),
    IMAGE("image"),
    KNOWLEDGE("knowledge"),
    NEWS("news"),
    VCLIP("vclip");

    private String serviceId;

    ServiceIdType(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getKey() {
        return name();
    }

    public static String checkCodeInType(String code) {
        for (ServiceIdType value : values()) {
            if (value.serviceId.equals(code)) {
                return code;
            }
        }
        return null;  // 찾는 코드가 없는 경우
    }
}
