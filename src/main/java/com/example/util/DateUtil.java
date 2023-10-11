package com.example.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final DateFormat DATEFORMAT_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat DATEFORMAT_YYYY_MM_DD_HH = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 格式化时间格式 yyyy-MM-dd
     * @param date
     * @return
     */
    public static String dateSimpleTimeFormat(Date date) {
        return DATEFORMAT_YYYY_MM_DD_HH.format(date);
    }
    /**
     * 格式化时间格式 yyyy-MM-dd
     * @param date
     * @return
     */
    public static String dateSimpleDateFormat(Date date) {
        return DATEFORMAT_YYYY_MM_DD.format(date);
    }
    public static Date dateParse(String date) {
        try {
            return DATEFORMAT_YYYY_MM_DD_HH.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
