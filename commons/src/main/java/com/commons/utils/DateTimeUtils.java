package com.commons.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author pengqingsong
 * @date 09/09/2017
 * @desc 时间相关的工具类
 */
public class DateTimeUtils {


    public static long endOfDay(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime().getTime();
    }

    /**
     * @param day
     * @return
     */
    public static long startOfDay(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    /**
     * 计算从此时到今天结束还剩多长时间，单位毫秒
     */
    public static long nowToEndOfToday() {
        Date today = new Date();
        return endOfDay(today) - today.getTime();
    }

    /**
     * 判断给定的时间戳是否是 0分0秒0毫秒
     */
    public static boolean isHourBegin(long mills) {
        Date date = new Date(mills);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);
        return minute == 0 && second == 0 && millisecond == 0;
    }

    /**
     * 判断给定的时间戳是否是 59分59秒999毫秒
     */
    public static boolean isHourEnd(long mills) {
        Date date = new Date(mills);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);
        return minute == 59 && second == 59 && millisecond == 999;
    }


    /**
     * 获取指定时间范围内所涉及到的0点时刻
     */
    public static List<Long> getDays(long startTime, long endTime) {
        List<Long> result = new ArrayList<>();

        long startOfDay = DateTimeUtils.startOfDay(new Date(startTime));

        while (startOfDay <= endTime) {
            result.add(startOfDay);
            startOfDay += (24000 * ConstantUtils.SECOND_IN_ONE_HOUR);
        }

        return result;
    }

    /**
     * 获取给定时间戳的小时数
     */
    public static int getHour(long mills) {
        Date date = new Date(mills);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定时间范围内的整点数
     *
     * @param startTime 必须是0分0秒0毫秒
     * @param endTime   必须是0分0秒0毫秒
     * @return 获取指定时间范围内的整点数
     */
    public static List<Long> getHours(long startTime, long endTime) {
        List<Long> result = new ArrayList<>();

        while (startTime <= endTime) {
            result.add(startTime);
            startTime += (1000 * ConstantUtils.SECOND_IN_ONE_HOUR);
        }

        return result;
    }
}
