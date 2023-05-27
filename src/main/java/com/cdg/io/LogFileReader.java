package com.cdg.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFileReader {

    /**
     * 로그 파일 읽어서 로그 한줄씩 ArrayList 에 담아줌
     * @return logLineList
     */
    public List<String> readLog(String fileUrl) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileUrl))) {
            List<String> logLineList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                logLineList.add(line);
            }
            return logLineList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
