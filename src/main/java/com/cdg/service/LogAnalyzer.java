package com.cdg.service;

import com.cdg.domain.Log;
import com.cdg.helper.LogParser;
import com.cdg.input.LogFileReader;

import java.util.ArrayList;
import java.util.List;

public class LogAnalyzer {

    private final LogFileReader logFileReader;
    private final LogParser logParser;

    public LogAnalyzer(LogFileReader logFileReader, LogParser logParser) {
        this.logFileReader = logFileReader;
        this.logParser = logParser;
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

    }
}
