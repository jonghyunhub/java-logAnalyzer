package com.cdg.service;

import com.cdg.helper.LogParser;
import com.cdg.io.LogFileReader;
import com.cdg.io.ResultPrinter;
import com.cdg.statistic.LogDataAggregator;
import com.cdg.storage.LogDataCollector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 통합 테스트
 */
class LogAnalyzerTest {

    private static final String OUTPUT_LOG = "src/main/resources/log/output.log";

    private LogAnalyzer logAnalyzer;

    private LogFileReader logFileReader;

    @BeforeEach
    void setUp() {

        logAnalyzer = new LogAnalyzer(
                new LogFileReader(),
                new LogParser(),
                new LogDataAggregator(),
                new LogDataCollector(),
                new ResultPrinter()
        );

        logFileReader = new LogFileReader();

        File file = new File(OUTPUT_LOG);

        file.delete(); // 테스트의 일관성을 위해 기존 log.out 파일 삭제하고 실행


    }

    @AfterEach
    void tearDown() {

        logAnalyzer = null;

        File file = new File(OUTPUT_LOG);

        file.delete(); //테스트가 종료되면 out.log 삭제

    }

    @DisplayName("통합 테스트 - out.log 결과물을 체크")
    @Test
    void logAnalyzerIntegrateTest() {

        logAnalyzer.start();

        List<String> outputData = logFileReader.readLog(OUTPUT_LOG);

        assertThat(outputData.get(2)).isEqualTo("2jdc"); // 최다 호출 api 검증

        assertThat(outputData.get(6)).isEqualTo("200 : 4747"); // 상태코드 별 횟수 검증

        assertThat(outputData.get(13)).isEqualTo("news : 834"); // 상위 3개의 API ServiceID 검증

        assertThat(outputData.get(23)).isEqualTo("2009-06-10 09:52"); // 피크 시간대 검증

        assertThat(outputData.get(27)).isEqualTo("Safari: 2.04%"); // 웹 브라우저 별 사용비율 검증




    }
}