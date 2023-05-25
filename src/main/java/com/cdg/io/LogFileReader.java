package com.cdg.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LogFileReader {

    /**
     * 로그 파일 읽어서 로그 한줄씩 ArrayList 에 담아줌
     * @return logLineList
     */
    public List<String> readLog() {
        try {
            File file = new File("src/main/resources/log/input.log");
            InputStream inputStream = new FileInputStream(file);
            List<String> logLineList = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            while(true) {
                try {
                    if ((i = inputStream.read()) == -1) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if ((char) i == '\r') { //로그 마지막
                    logLineList.add(stringBuilder.toString());
                    stringBuilder.setLength(0); //스트링빌더 초기화
                } else if ((char) i == '\n') { // 개행문자 스킵
                }
                else { //로그 마지막 아님
                    stringBuilder.append((char) i);
                }
            }

            return logLineList;

        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }


}
