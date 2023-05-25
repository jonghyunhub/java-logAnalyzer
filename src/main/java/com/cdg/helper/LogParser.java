package com.cdg.helper;


import com.cdg.domain.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    private final String regex = "\\[(\\d{2,3})\\]\\[(.*?)\\]\\[(.*?)\\]\\[(.*?)\\]";
//    "\\[(\\d{3})\\]\\[(.*?)\\]\\[(.*?)\\]\\[(.*?)\\]";

//    다음과 같은 정규식을 사용하여 일치하는 문자열을 찾을 수 있습니다.

    //    \[(\d{3})\]\[(.*?)\]\[(.*?)\]\[(.*?)\]
    //    위의 정규식을 설명하면 다음과 같습니다:
    //    \[(\d{3})\]: 대괄호 안에 3개의 연속된 숫자가 있어야 하며, 이 숫자는 해당 인덱스의 그룹(group)으로 캡처됩니다.
    //    \[(.*?)\]: 대괄호 안에 문자열(예를 들어 'http://example.com')이 있어야 하며 이 문자열은 해당 인덱스의 그룹으로 캡처됩니다. .*?는 가능한 적은 문자가 일치하는 것을 의미합니다. 이것은 URL이 공백을 포함할 수 있기 때문입니다.
    //    \[(.*?)\]: 대괄호 안에 문자열(예를 들어 'Mozilla Firefox')이 있어야 하며 이 문자열은 해당 인덱스의 그룹으로 캡처됩니다.
    //    \[(.*?)\]: 대괄호 안에 문자열(예를 들어 '2019-01-01 00:00:00')이 있어야 하며 이 문자열은 해당 인덱스의 그룹으로 캡처됩니다.


    /**
     * 로그 텍스트 로그 도메인 객체로 파싱 - 이 로직 static 매서드로 Log 도메인안에 넣어야하나 고민됨
     * @param logText
     * @return Log
     */
    public Log parseTextToLog(String logText) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logText);


        if(matcher.matches()) {
            String status = matcher.group(1);
            String url = matcher.group(2);
            String browser = matcher.group(3);
            String timestamp = matcher.group(4);

            Log log = new Log(status, url, browser, timestamp);
            return log;
        } else {}

        return null;
    }



}
