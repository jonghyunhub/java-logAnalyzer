package com.cdg.service;

import com.cdg.domain.Log;
import com.cdg.helper.LogParser;
import com.cdg.io.LogFileReader;
import com.cdg.statistic.LogDataAggregator;
import com.cdg.storage.LogDataCollector;

import java.util.ArrayList;
import java.util.List;


/**
 * 컨테이너 역할 객체들의 di 및 싱글톤 관리
 */
public class LogAnalyzer {

    private final LogFileReader logFileReader;
    private final LogParser logParser;

    private final LogDataAggregator logDataAggregator;

    private final LogDataCollector logDataCollector;


    public LogAnalyzer(LogFileReader logFileReader, LogParser logParser, LogDataAggregator logDataAggregator, LogDataCollector logDataCollector) {
        this.logFileReader = logFileReader;
        this.logParser = logParser;
        this.logDataAggregator = logDataAggregator;
        this.logDataCollector = logDataCollector;
    }

    public void start() {

        List<String> logTextList = logFileReader.readLog();

        List<Log> logDataList = new ArrayList<>();

        for (String log : logTextList) {
            Log result = logParser.parseTextToLog(log);
            if (result != null) {
                logDataList.add(result);
            }
        }

        logDataAggregator.aggregateLog(logDataList, logDataCollector);



    }
}
