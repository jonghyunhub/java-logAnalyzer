package com.cdg;

import com.cdg.helper.LogParser;
import com.cdg.input.LogFileReader;
import com.cdg.service.LogAnalyzer;

public class LogAnalyzerApplication {
    public static void main(String[] args) {
        LogAnalyzer logAnalyzer = new LogAnalyzer(new LogFileReader(), new LogParser()); //싱글톤 패턴을 위해 di 진행
        logAnalyzer.start();
    }
}
