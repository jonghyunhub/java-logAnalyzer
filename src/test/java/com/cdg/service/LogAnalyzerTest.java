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

/**
 * 통합 테스트
 */
class LogAnalyzerTest {

    private static final String OUTPUT_LOG = "src/main/resources/log.out.log";

    private LogAnalyzer logAnalyzer;

    @BeforeEach
    void setUp() {

        logAnalyzer = new LogAnalyzer(
                new LogFileReader(),
                new LogParser(),
                new LogDataAggregator(),
                new LogDataCollector(),
                new ResultPrinter()
        );

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
    void start() {

        logAnalyzer.start();


    }
}