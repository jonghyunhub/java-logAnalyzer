package com.cdg;

import com.cdg.helper.LogParser;
import com.cdg.io.LogFileReader;
import com.cdg.service.LogAnalyzer;
import com.cdg.statistic.LogDataAggregator;
import com.cdg.storage.LogDataCollector;

public class LogAnalyzerApplication {
    public static void main(String[] args) {
        LogAnalyzer logAnalyzer = new LogAnalyzer(
                new LogFileReader(),
                new LogParser(),
                new LogDataAggregator(),
                new LogDataCollector()); //싱글톤 패턴을 위해 di 진행
        logAnalyzer.start();
    }
}
