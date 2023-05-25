package com.cdg.io;

import com.cdg.storage.LogDataCollector;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ResultPrinter {

    private static final String FILENAME = "src/main/resources/log/output.log";

    public void printLog(LogDataCollector logDataCollector) {
        Map<String, Integer> statusCodeCalledNumMap = logDataCollector.getStatusCodeCalledNumMap();
        Map<String, Integer> serviceIdCalledNumMap = logDataCollector.getServiceIdCalledNumMap();
        Map<String, Integer> browserNumMap = logDataCollector.getBrowserNumMap();

        try(PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("최다호출 APIKEY\n\n")
                    .append(logDataCollector.getMaxCalledApiKey())
                    .append("\n\n")
                    .append("상태코드 별 횟수\n\n");

            for (Map.Entry<String, Integer> entry : statusCodeCalledNumMap.entrySet()) {
                stringBuilder.append(entry.getKey() + " : " + entry.getValue() + "\n");
            }

            stringBuilder.append("\n\n")
                    .append("상위 3개의 API ServiceID와 각각의 요청 수\n\n");

            for (Map.Entry<String, Integer> entry : serviceIdCalledNumMap.entrySet()) {
                stringBuilder.append(entry.getKey() + " : " + entry.getValue() + "\n");
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


//            for (Map.Entry<String, Integer> entry : browserNumMap.entrySet()) {
//                stringBuilder.append(entry.getKey() + " : " + entry.getValue() + "\n");
//            }



            String data = stringBuilder.toString();
            writer.println(data);
            System.out.println("Text written to log file successfully");
        } catch (IOException e) {
            System.out.println("Error writing to log file");
            e.printStackTrace();
        }
    }
}
