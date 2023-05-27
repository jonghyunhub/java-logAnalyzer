package com.cdg.statistic;

import com.cdg.domain.Log;
import com.cdg.helper.LogGenerator;
import com.cdg.io.LogFileReader;
import com.cdg.storage.LogDataCollector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
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

    @DisplayName("최다 호출 API 키 통계 성공")
    @ParameterizedTest
    @MethodSource("generateLogData")
    public void aggregateApiKey_성공(List<Log> logList) {

        for (Log log : logList) {
            logDataAggregator.aggregateApiKey(log, logDataCollector);
        }

        Map<String, Integer> apiKeyCalledNumMap = logDataCollector.getApiKeyCalledNumMap();

        String maxCalledApiKey = logDataCollector.getMaxCalledApiKey();


        String findResult = null;
        int maxCalledNum = 0;
        for (Map.Entry<String, Integer> entry : apiKeyCalledNumMap.entrySet()) {
            if (maxCalledNum < entry.getValue()) {
                maxCalledNum = entry.getValue();
                findResult = entry.getKey();
            }
        }

        assertThat(maxCalledApiKey).isEqualTo(findResult);


    }


    @DisplayName("API 최다 호출 시간 집계 메서드 성공")
    @ParameterizedTest
    @MethodSource("generateLogData")
    public void aggregatePeekTime_성공() {

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
}