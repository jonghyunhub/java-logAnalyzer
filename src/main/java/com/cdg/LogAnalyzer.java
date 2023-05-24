package com.cdg;

import com.cdg.input.LogReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LogAnalyzer {

    public void start() {
        LogReader logReader = new LogReader();
        List<String> logList = logReader.readLog();



    }
}
