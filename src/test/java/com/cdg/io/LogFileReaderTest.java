package com.cdg.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.*;



class LogFileReaderTest {

    LogFileReader logFileReader;

    ResultPrinter resultPrinter;

    private final String testLogPath = "src/test/resources/test.log";

    /**
     * test.log 파일에 테스트 문구 작성
     */
    @BeforeEach
    public void setUp() {
        logFileReader = new LogFileReader();
        resultPrinter = new ResultPrinter();
        resultPrinter.printLog("@AfterEach\n" +
                "    public void tearDown() {\n" +
                "        logFileReader = null;\n" +
                "        resultPrinter = null;\n" +
                "    }", testLogPath);
    }




    @ParameterizedTest
    @ValueSource(strings = {
            "@AfterEach",
                    "    public void tearDown() {",
                    "        logFileReader = null;",
                    "        resultPrinter = null;",
                    "    }"
    })
    public void readLog은_로그파일의_모든_줄을_읽어서_리스트에_넣어야_함(String logText) {
        List<String> readLog = logFileReader.readLog(testLogPath);

        assertThat(readLog.contains(logText)).isTrue();
    }


    /**
     * 테스트 재사용성을 위해 테스트 완료 후 생성한 test.log 파일 삭제
     */
    @AfterEach
    public void tearDown() {
        logFileReader = null;
        resultPrinter = null;

        File file = new File(testLogPath);

        file.delete();
    }



}