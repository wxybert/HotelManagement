package com.tellh.utils;

import java.util.Calendar;

/**
 * Created by tlh on 2016/11/2.
 */
public class DateUtils {
    public static int getDiscrepantDays(long disTime) {
        return (int) (Math.ceil(disTime / 1000 / 60 / 60 / 24));
    }
    public static Calendar getDateShouldCheckOut(int days) {
        Calendar date = Calendar.getInstance();
        // reset hour, minutes, seconds and millis
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.add(Calendar.DAY_OF_MONTH, days);
        date.add(Calendar.HOUR_OF_DAY, 12);
        return date;
    }
}
