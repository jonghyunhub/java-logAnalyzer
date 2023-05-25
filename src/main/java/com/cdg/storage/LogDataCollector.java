package com.cdg.storage;

import java.util.HashMap;
import java.util.Map;

public class LogDataCollector {

    /**
     * 최대 호출 ApiKey
     */
    private String maxCalledApiKey;

    /**
     * 각 ApiKey들 호출횟수와 키들 저장소 Map, Map은 변경되면 안되니 final 로 할당
     */
    private final Map<String, Integer> apiKeyCalledNumMap = new HashMap<>();

    /**
     * 각 상태 코드들 호출 횟수 저장소 Map
     */
    private final Map<String, Integer> statusCodeCalledNumMap = new HashMap<>();


    /**
     * 서비스 ID 별 호출 횟수 저장소 Map
     */
    private final Map<String, Integer> serviceIdCalledNumMap = new HashMap<>();

    /**
     * 피크 시간대
     */
    private String peekTime;

    /**
     * 시간대별 호출 횟수 저장소 Map
     */
    private final Map<String, Integer> peekTimeNumMap = new HashMap<>();

    /**
     * 브라우저별 호출 횟수 저장소 Map
     */
    private final Map<String, Integer> browserNumMap = new HashMap<>();


    public String getMaxCalledApiKey() {
        return maxCalledApiKey;
    }

    public Map<String, Integer> getApiKeyCalledNumMap() {
        return apiKeyCalledNumMap;
    }

    public Map<String, Integer> getStatusCodeCalledNumMap() {
        return statusCodeCalledNumMap;
    }

    public Map<String, Integer> getServiceIdCalledNumMap() {
        return serviceIdCalledNumMap;
    }

    public String getPeekTime() {
        return peekTime;
    }

    public Map<String, Integer> getPeekTimeNumMap() {
        return peekTimeNumMap;
    }

    public Map<String, Integer> getBrowserNumMap() {
        return browserNumMap;
    }

    public void setMaxCalledApiKey(String maxCalledApiKey) {
        this.maxCalledApiKey = maxCalledApiKey;
    }

    public void setPeekTime(String peekTime) {
        this.peekTime = peekTime;
    }

}
