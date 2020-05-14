package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : zhanyy  ʕ·͡ˑ·ཻʔෆʕ•̫͡•ོʔ
 * @Date : 2019/3/2
 * @Desc : to do anything
 */
@Slf4j
public class TimeUtil {

    /**
     * 1分钟的秒时间
     */
    public static final long ONE_MINUTE_IN_SECONDS = 60L;

    /**
     * 1小时的分钟时间
     */
    public static final long ONE_HOUR_IN_MINUTES = 60L;

    /**
     * 一分钟的毫秒时长
     */
    public static final long ONE_MINUTE_IN_MILLISECONDS = ONE_MINUTE_IN_SECONDS * 1000;

    /**
     * 一小时的毫秒时长
     */
    public static final long ONE_HOUR_IN_MILLISECONDS = 60L * ONE_MINUTE_IN_MILLISECONDS;
    /**
     * 一天的毫秒时长
     */
    public static final long ONE_DAY_IN_MILLISECONDS = 24L * ONE_HOUR_IN_MILLISECONDS;

    /**
     * 一天的分鐘时长
     */
    public static final int ONE_DAY_IN_MIN = (int) (24 * ONE_MINUTE_IN_SECONDS);

    /**
     * 一天的秒时长
     */
    public static final long ONE_DAY_IN_SECONDS = ONE_DAY_IN_MIN * ONE_MINUTE_IN_SECONDS;


    /**
     * 1秒的时长
     */
    public static final long ONE_MILLS = 1000L;

    /**
     * 2015-02-23 12:12:12格式
     */
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式 2019-03-09-00-00-00
     */
    public static final String TIME_FORMAT_1 = "yyyy-MM-dd-HH-mm-ss";

    /**
     * 验证日期字符串是否是YYYY-MM-dd格式或者YYYYMMdd
     *
     * @param str str
     * @return boolean
     */
    public static boolean checkDateFormat(String str) {
        boolean flag = false;
        String regex = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1 = Pattern.compile(regex);
        Matcher isNo = pattern1.matcher(str);
        if (isNo.matches()) {
            flag = true;
        }
        return flag;
    }

    public static int getNowOfSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static long getNowOfMills() {
        return System.currentTimeMillis();
    }

    /**
     * 获取每天0点开始，每过2个小时的时间戳
     *
     * @param point 间隔小时，例如:间隔2小时，就传2，单位：小时
     * @return
     */
    public static long getNowEvenTime(int point) {
        if (point <= 0) {
            return 0;
        }
        int hourTest = getNowHour();
        int mo = hourTest / point;
        int hour = (mo + 1) * point;
        int dayAdd = 0;
        if (hour >= 24) { //跨天
            hour = 0;
            //TODO  可能增加几天
            dayAdd = 1;
        }

        LocalDateTime localDateTime = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), hour, 0, 0);
        if (dayAdd > 0) {
            localDateTime = localDateTime.plusDays(dayAdd);
        }
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 获取当前时间的小时数
     *
     * @return
     */
    public static int getNowHour() {
        return LocalDateTime.now().getHour();
    }

    /**
     * 判断两个时间是否是同一天
     *
     * @param sourceTime
     * @param targetTime
     * @return
     */
    public static boolean isSameDay(long sourceTime, long targetTime) {
        Instant instant1 = Instant.ofEpochMilli(sourceTime);
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());
        long day1 = localDateTime1.getLong(ChronoField.EPOCH_DAY);

        Instant instant2 = Instant.ofEpochMilli(targetTime);
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault());
        long day2 = localDateTime2.getLong(ChronoField.EPOCH_DAY);

        return day1 == day2;

    }

    /*
     * 获取time日零点秒时间
     *
     * @param time 时间（毫秒）
     */
    public static int dayZeroSecondsFromTime(long time) {
        return (int) (dayZeroMillsFromTime(time * 1000L) / 1000);
    }

    /**
     * 获取time日零点毫秒时间
     *
     * @param time 时间 （毫秒）
     */
    public static long dayZeroMillsFromTime(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        LocalDateTime dt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        return dt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 格式化时间
     *
     * @param time
     * @param format
     * @return
     */
    public static String timeFormat(long time, String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        return ldt.format(dtf);
    }

    public static String timeFormat(LocalDateTime time, String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        return time.format(dtf);
    }

    public static Date timeFormat(String dateStr, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(dateStr);
        } catch (Exception e) {
            log.error("日期转换错误:{}", dateStr);
            return null;
        }
    }

    /**
     * 获取今天已经过去的分钟数
     *
     * @return
     */
    public static int getTodayOfMinute() {
        int nowOfMinutes = getNowOfMinutes();
        int zeroMinuteFromNow = dayZeroMinuteFromNow();
        return nowOfMinutes - zeroMinuteFromNow;
    }

    public static int getNowOfMinutes() {
        return (int) (System.currentTimeMillis() / 1000 / 60);
    }

    /**
     * 获取今天零点的分钟数
     */
    public static int dayZeroMinuteFromNow() {
        LocalDateTime localDateTime = LocalDate.now().atStartOfDay();
        return (int) (localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 60000);
    }

    /**
     * 获取今天零点的秒数
     */
    public static int dayZeroSecondsFromNow() {
        return (int) (dayZeroMillsFromNow() / ONE_MILLS);
    }

    public static long dayZeroMillsFromNow() {
        LocalDateTime localDateTime = LocalDate.now().atStartOfDay();
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 判断今天是否为某年的同一周
     *
     * @return boolean
     */
    public static boolean isSameWeek(long oldTime) {
        LocalDate now = LocalDate.now();
        Instant instant = Instant.ofEpochMilli(oldTime);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return now.getYear() == dateTime.getYear()
                && now.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == dateTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
    }

    /**
     * 判断指定的时间是否是今天
     *
     * @param time 毫秒数
     * @return
     */
    public static boolean isToday(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.toLocalDate().isEqual(LocalDate.now());
    }

    /**
     * 获取距离time的自然天数
     *
     * @param time
     * @return
     */
    public static int getNaturalDayFromTime(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        long day = ldt.getLong(ChronoField.EPOCH_DAY);
        long nowDay = LocalDate.now().getLong(ChronoField.EPOCH_DAY);
        return (int) (nowDay - day);
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔天数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static int betweenDay(long time1, long time2) {
        return (int) (Math.abs(time1 - time2) / ONE_DAY_IN_MILLISECONDS);
    }

    /**
     * 获取指定时间的自然天数
     *
     * @param time
     * @return
     */
    public static long getDay(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        return ldt.getLong(ChronoField.EPOCH_DAY);
    }

    public static LocalDateTime getDateTimeOfMillis(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 获取指定日期的时间戳
     *
     * @param year
     * @param month       从1开始
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param milliSecond
     * @return
     */
    public static long getTimeInMillis(int year, int month, int day, int hour, int minute, int second,
                                       int milliSecond) {
        LocalDateTime ldt = LocalDateTime.of(year, month, day, hour, minute, second, milliSecond * 1000_000);
        return ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 两个时间相差时间
     *
     * @param minTime    小的时间
     * @param maxTime    大的时间
     * @param chronoUnit 时间类型
     * @return
     */
    public static long getBetween(long minTime, long maxTime, ChronoUnit chronoUnit) {
        return chronoUnit.between(LocalDateTime.ofInstant(Instant.ofEpochMilli(minTime), ZoneId.systemDefault()), LocalDateTime.ofInstant(Instant.ofEpochMilli(maxTime), ZoneId.systemDefault()));
    }

    /**
     * 获取下个周一零点的时间
     *
     * @return
     */
    public static long timeToNextMonday() {
        Calendar cal = Calendar.getInstance();
        int[] weekDayArray = {0, 7, 1, 2, 3, 4, 5, 6};
        int nowDay = cal.get(Calendar.DAY_OF_WEEK);
        long zeroNowDay = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        int disDay = weekDayArray[1] - weekDayArray[nowDay] + 1;
        return zeroNowDay + disDay * ONE_DAY_IN_MILLISECONDS;
    }

    /**
     * 获取本周一零点时间
     *
     * @return
     */
    public static long getThisWeekMonday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
}
