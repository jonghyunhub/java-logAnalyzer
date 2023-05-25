package com.cdg.statistic;

import com.cdg.DataType.ServiceIDType;
import com.cdg.DataType.StatusCodeType;
import com.cdg.domain.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogDataAggregator {

    /**
     * 최대 호출 ApiKey
     */
    private String maxCalledApiKey;

    /**
     * 각 ApiKey들 호출횟수와 키들 저장소 Map, Map은 변경되면 안되니 final 로 할당
     */
    private final Map<String, Integer> apiKeyCalledNumMap = new HashMap<>();

    /**
     * 각 상태코드들 호출 횟수 저장소 Map
     */
    private final Map<StatusCodeType, Integer> statusCodeCalledNumMap = new HashMap<>();


    /**
     * 서비스 ID 별 호출 횟수 저장소 Map
     */
    private final Map<ServiceIDType, Integer> serviceIdCalledNumMap = new HashMap<>();

    /**
     * 피크 시간대
     */
    private String peekTime;


    /**
     * ApiKey 호출 횟수 집계
     * @param logList
     * @return
     */
    public String aggregateApiKey(List<Log> logList) {


        for (Log log : logList) {
            System.out.println(log.getUrl());
            String regex = "apikey=([^&]+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(log.getUrl());

            if (matcher.find()) {
                String apikey = matcher.group(1);
                apiKeyCalledNumMap.put(apikey, apiKeyCalledNumMap.getOrDefault(apikey, 0) + 1);
            }

        }

        for (Map.Entry<String,Integer> e: apiKeyCalledNumMap.entrySet()) {
            System.out.println("e.getKey() = " + e.getKey() + ", e.getValue() = " + e.getValue());
        }



        return findMaxKey();
    }

    /**
     * Map 안의 value 가 가장 큰 key 찾기
     * @return
     */
    private String findMaxKey() {
        String maxKey = null;
        int maxValue = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : apiKeyCalledNumMap.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxKey = entry.getKey();
                maxValue = entry.getValue();
            }
        }

        return maxKey;
    }
}
