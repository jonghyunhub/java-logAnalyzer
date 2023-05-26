package com.cdg.service;

import com.cdg.helper.LogParser;
import com.cdg.io.LogFileReader;
import com.cdg.io.ResultPrinter;
import com.cdg.statistic.LogDataAggregator;
import com.cdg.storage.LogDataCollector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 통합테스트
 */
class LogAnalyzerTest {

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

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void start() {
        logAnalyzer.start();
    }
}