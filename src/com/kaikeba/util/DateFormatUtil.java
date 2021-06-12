package com.kaikeba.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    private static  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String format(Date date) {
        return simpleDateFormat.format(date);
    }
}
