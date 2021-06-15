package com.kaikeba.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateFormatUtil {
    private static  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String format(Timestamp timestamp) {
        return simpleDateFormat.format(timestamp);
    }
}
