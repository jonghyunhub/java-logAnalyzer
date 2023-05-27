package com.cdg.statistic;

import com.cdg.domain.Log;
import com.cdg.helper.LogGenerator;
import com.cdg.storage.LogDataCollector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LogDataAggregatorTest {

    LogDataAggregator logDataAggregator;

    LogDataCollector logDataCollector;




    @BeforeEach
    void setUp() {

        logDataCollector = new LogDataCollector();

        logDataAggregator = new LogDataAggregator();


    }

    @AfterEach
    void tearDown() {
        logDataAggregator = null;
        logDataCollector = null;
    }

    /**
     * 테스트 로그 데이터 생성
     * @return Stream.of(logList)
     */
    static Stream<List<Log>> generateLogData() {
        List<Log> logList = new ArrayList<>();


        for (int i = 0; i < 200; i++) {
            Log log = LogGenerator.generateRandomLog();
            logList.add(log);
        }


        return Stream.of(logList);
    }



    @DisplayName("최다 호출 API 키 통계")
    @ParameterizedTest
    @MethodSource("generateLogData")
    public void aggregateApiKey_성공(List<Log> logList) {

        for (Log log : logList) {
            logDataAggregator.aggregateApiKey(log, logDataCollector);
        }

        Map<String, Integer> apiKeyCalledNumMap = logDataCollector.getApiKeyCalledNumMap();

        String maxCalledApiKey = logDataCollector.getMaxCalledApiKey();


        String maxKey = logDataAggregator.findMaxKey(apiKeyCalledNumMap);

        assertThat(maxCalledApiKey).isEqualTo(maxKey);


    }


    @DisplayName("API 최다 호출 시간 집계")
    @ParameterizedTest
    @MethodSource("generateLogData")
    public void aggregatePeekTime_성공(List<Log> logList) {

        for (Log log : logList) {
            logDataAggregator.aggregatePeekTime(log, logDataCollector);
        }

        String peekTime = logDataCollector.getPeekTime();
        Map<String, Integer> peekTimeNumMap = logDataCollector.getPeekTimeNumMap();

        String maxKey = logDataAggregator.findMaxKey(peekTimeNumMap);

        assertThat(peekTime).isEqualTo(maxKey);


    }


    @DisplayName("상태 코드 호출 횟수 집계")
    @ParameterizedTest
    @MethodSource("generateLogData")
    public void aggregateStatusCode_성공(List<Log> logList) {
        
        Map<String, Integer> answer = new HashMap<>();
        

        Map<String, Integer> statusCodeCalledNumMap = logDataCollector.getStatusCodeCalledNumMap();


        for (Log log : logList) {

            answer.put(log.getStatusCode(), answer.getOrDefault(log.getStatusCode(),0 ) + 1);
            
            logDataAggregator.aggregateStatusCode(log, statusCodeCalledNumMap);
        }

        assertThat(answer).isEqualTo(statusCodeCalledNumMap);


    }

    @DisplayName("서비스 ID 호출 횟수 집계")
    @ParameterizedTest
    @MethodSource("generateLogData")
    public void aggregateServiceId_성공(List<Log> logList) {

        Map<String, Integer> answer = new HashMap<>();


        Map<String, Integer> serviceIdCalledNumMap = logDataCollector.getServiceIdCalledNumMap();


        for (Log log : logList) {

            answer.put(log.getStatusCode(), answer.getOrDefault(log.getStatusCode(),0 ) + 1);

            logDataAggregator.aggregateStatusCode(log, serviceIdCalledNumMap);
        }

        assertThat(answer).isEqualTo(serviceIdCalledNumMap);

    }


    @DisplayName("브라우저 호출 횟수 집계")
    @ParameterizedTest
    @MethodSource("generateLogData")
    public void aggregateBrowser_성공(List<Log> logList) {

        Map<String, Integer> answer = new HashMap<>();


        Map<String, Integer> browserNumMap = logDataCollector.getBrowserNumMap();


        for (Log log : logList) {

            answer.put(log.getStatusCode(), answer.getOrDefault(log.getStatusCode(),0 ) + 1);

            logDataAggregator.aggregateStatusCode(log, browserNumMap);
        }

        assertThat(answer).isEqualTo(browserNumMap);
    }

}