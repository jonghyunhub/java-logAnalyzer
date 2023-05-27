package com.cdg.statistic;

import com.cdg.domain.Log;
import com.cdg.domain.ServiceIdType;
import com.cdg.storage.LogDataCollector;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogDataAggregator {


    public void aggregateLog(List<Log> logList, LogDataCollector logDataCollector) {

        for (Log log : logList) {

            aggregateBrowserPercentage(log, logDataCollector.getBrowserNumMap()); //브라우저 호출 횟수 집계

            aggregatePeekTime(log, logDataCollector); // 피크 시간 집계

            aggregateServiceId(log, logDataCollector.getServiceIdCalledNumMap()); //서비스 id 호출 횟수 집계

            aggregateStatusCode(log, logDataCollector.getStatusCodeCalledNumMap()); //상태 코드 호출 횟수 집계

            aggregateApiKey(log,logDataCollector); // 최다 호출 apiKey 집계

        }

    }


    /**
     * ApiKey 호출 횟수 집계
     * @param log
     */
    public void aggregateApiKey(Log log,LogDataCollector logDataCollector) {

        Map<String, Integer> apiKeyCalledNumMap = logDataCollector.getApiKeyCalledNumMap();

        String regex = "apikey=([^&]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(log.getUrl());

        if (matcher.find()) {
            String apikey = matcher.group(1);
            apiKeyCalledNumMap.put(apikey, apiKeyCalledNumMap.getOrDefault(apikey, 0) + 1);
        }


        logDataCollector.setMaxCalledApiKey(findMaxKey(apiKeyCalledNumMap));
    }


    /**
     * 각 상태 코드 회수 집계
     * @param log
     * @param statusCodeCalledNumMap
     */
    public void aggregateStatusCode(Log log,  Map<String, Integer> statusCodeCalledNumMap) {


        statusCodeCalledNumMap.put(log.getStatusCode(), statusCodeCalledNumMap.getOrDefault(log.getStatusCode(), 0) + 1);


    }


    /**
     * 서비스ID별 호출 횟수 집계
     * @param log
     * @param getServiceIdCalledNumMap
     */
    public void aggregateServiceId(Log log, Map<String, Integer> getServiceIdCalledNumMap) {


        String[] splitString = log.getUrl().split("\\?");
        String[] apiUrl = splitString[0].split("//");

        String outputString = apiUrl[1].split("/search/")[1];

        if (ServiceIdType.checkCodeInType(outputString)!= null) {
            getServiceIdCalledNumMap.put(outputString, getServiceIdCalledNumMap.getOrDefault(outputString, 0) + 1);
        }


    }


    /**
     * 피크 시간대 집계
     * @param log
     * @param logDataCollector
     */
    public void aggregatePeekTime(Log log, LogDataCollector logDataCollector) {

        Map<String, Integer> peekTimeNumMap = logDataCollector.getPeekTimeNumMap();

        final String outputFormat = "yyyy-MM-dd HH:mm";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(outputFormat);
        LocalDateTime dt = LocalDateTime.parse(log.getTimestamp(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String outputString = dt.format(dtf);

        peekTimeNumMap.put(outputString, peekTimeNumMap.getOrDefault(outputString, 0) + 1);


        logDataCollector.setPeekTime(findMaxKey(peekTimeNumMap));



    }


    /**
     * 브라우저별 호출 횟수 집계
     * @param log
     * @param statusCodeCalledNumMap
     */
    public void aggregateBrowserPercentage(Log log,  Map<String, Integer> statusCodeCalledNumMap) {

        statusCodeCalledNumMap.put(log.getBrowser(), statusCodeCalledNumMap.getOrDefault(log.getBrowser(), 0) + 1);

    }


    /**
     * Map 안의 value 가 가장 큰 key 찾기
     * @return maxKey
     */
    public String findMaxKey(Map<String, Integer> map) {
        String maxKey = null;
        int maxValue = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxKey = entry.getKey();
                maxValue = entry.getValue();
            }
        }

        return maxKey;
    }


}
