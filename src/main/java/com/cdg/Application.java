package com.cdg;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Application {
    public static void main(String[] args) {
        try {
            OutputStream output = new FileOutputStream("");
            String str ="오늘 날씨는 아주 좋습니다.";
            byte[] by=str.getBytes();
            output.write(by);

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
