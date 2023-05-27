package com.cdg.io;

import com.cdg.storage.LogDataCollector;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ResultPrinter {

    private static final String FILENAME = "src/main/resources/log/output.log";

    /**
     * 로그 출력 시작
     * @param logDataCollector
     */
    public void startPrint(LogDataCollector logDataCollector) {
        String data = generateLogText(logDataCollector);
        printLog(data, FILENAME);
    }


    /**
     * 로그 출력
     * @param data
     */
    public void printLog(String data, String fileName) {

        try(PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {


            writer.println(data);

            System.out.println("Text written to log file successfully");
        } catch (IOException e) {
            System.out.println("Error writing to log file");
            e.printStackTrace();
        }
    }


    /**
     * 로그에 넣을 텍스트 생성
     * @param logDataCollector
     * @return
     */
    private String generateLogText(LogDataCollector logDataCollector) {

        Map<String, Integer> statusCodeCalledNumMap = logDataCollector.getStatusCodeCalledNumMap();
        Map<String, Integer> serviceIdCalledNumMap = logDataCollector.getServiceIdCalledNumMap();
        Map<String, Integer> browserNumMap = logDataCollector.getBrowserNumMap();


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("최다호출 APIKEY\n\n")
                .append(logDataCollector.getMaxCalledApiKey())
                .append("\n\n")
                .append("상태코드 별 횟수\n\n");

        for (Map.Entry<String, Integer> entry : statusCodeCalledNumMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }

        stringBuilder.append("\n\n")
                .append("상위 3개의 API ServiceID와 각각의 요청 수\n\n");

        for (Map.Entry<String, Integer> entry : serviceIdCalledNumMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }


        stringBuilder.append("\n\n")
                .append("피크 시간대\n\n")
                .append(logDataCollector.getPeekTime())
                .append("\n\n")
                .append("웹 브라우저 별 사용비율\n\n");

        int sum = 0;
        for (Integer value : browserNumMap.values()) {
            sum += value;
        }

        for (String key : browserNumMap.keySet()) {
            int value = browserNumMap.get(key);
            double percentage = ((double) value / sum) * 100;
            stringBuilder.append(key).append(": ").append(String.format("%.2f%%", percentage)).append("\n");
        }



        return stringBuilder.toString();

    }
}
