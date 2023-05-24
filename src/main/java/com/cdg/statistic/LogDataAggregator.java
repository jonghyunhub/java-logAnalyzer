package com.cdg.statistic;

import java.util.HashMap;
import java.util.Map;

public class LogDataAggregator {

    /**
     * 최대 호출 ApiKey
     */
    private String maxCalledApiKey;

    /**
     * 각 ApiKey들 호출횟수와 키들 저장소 Map
     */
    private Map<String, Integer> ApiKeyCalledNumMap = new HashMap<>();

    /**
     * 상태 코드
     */
    private String statusCode;




    public String aggregateApiKey() {

    }
}
