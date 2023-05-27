package com.cdg.helper;

import com.cdg.domain.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class LogGenerator {

    private static final String[] STATUS_CODES = {"200", "10" ,"404"};
    private static final String[] URLS = {"http://apis.cdg.com/blog",
            "http://apis.cdg.com/book",
            "http://apis.cdg.com/image",
            "http://apis.cdg.com/knowledge",
            "http://apis.cdg.com/news",
            "http://apis.cdg.com/vclip"
    };
    private static final String[] BROWSERS = {"Chrome", "Firefox", "Safari", "Opera", "IE"};
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final int MIN_STATUS_CODE = 0;
    private static final int MAX_STATUS_CODE = STATUS_CODES.length - 1;
    private static final int MIN_URL = 0;
    private static final int MAX_URL = URLS.length - 1;
    private static final int MIN_BROWSER = 0;
    private static final int MAX_BROWSER = BROWSERS.length - 1;

    public static Log generateRandomLog() {
        Random random = new Random();

        // 랜덤으로 HTTP 상태 코드, 요청한 URL, 사용한 브라우저, 로그가 기록된 시간 정보를 생성하여 Log 객체에 담는다
        String statusCode = STATUS_CODES[random.nextInt(MAX_STATUS_CODE - MIN_STATUS_CODE + 1) + MIN_STATUS_CODE];
        String url = URLS[random.nextInt(MAX_URL - MIN_URL + 1) + MIN_URL];
        String browser = BROWSERS[random.nextInt(MAX_BROWSER - MIN_BROWSER + 1) + MIN_BROWSER];
        String timestamp = LocalDateTime.now().format(FORMATTER);

        return new Log(statusCode, url, browser, timestamp);
    }
}