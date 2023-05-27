package com.cdg.helper;

import com.cdg.domain.Log;
import com.cdg.io.LogFileReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class LogParserTest {

    LogParser logParser;
    LogFileReader logFileReader;

    private final String testLogPath = "src/main/resources/log/input.log";

    private List<String> readLog;

    @BeforeEach
    void setUp() {
        logParser = new LogParser();

        logFileReader = new LogFileReader();

        readLog = logFileReader.readLog(testLogPath);
    }

    @AfterEach
    void tearDown() {
        logParser = null;
        logFileReader = null;
        readLog = null;
    }

    @ParameterizedTest
    @CsvSource(value = {"200,http://apis.cdg.com/search/knowledge?apikey=23jf&q=cdg,IE,2009-06-10 08:00:00",
            "10,http://apis.cdg.com/search/book?q=cdg,IE,2009-06-10 08:02:01",
            "404,http://apis.cdg.com/search/aaaaapikey=dcj8&q=cdg,IE,2009-06-10 08:03:55",
            "200,http://apis.cdg.com/search/knowledge?apikey=jg9k&q=cdg,IE,2009-06-10 10:11:12"
    }, delimiter = ',')
    public void parseTextToLog_성공(
            String statusCode,
            String url,
            String browser,
            String timestamp
    ) {
        List<Log> logList = new ArrayList<>();


        for (String logText : readLog) {
            logList.add(logParser.parseTextToLog(logText));
        }

        Log testCase = new Log(statusCode, url, browser, timestamp);

        assertThat(logList.contains(testCase)).isTrue();



    }


}