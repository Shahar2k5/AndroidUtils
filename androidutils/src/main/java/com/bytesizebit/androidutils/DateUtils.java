package com.bytesizebit.androidutils;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/***********
 * Android Utils
 * Created by Shahar Barsheshet on 18/03/2015.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ***********/
public class DateUtils {

    private DateUtils() {
    }

    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final int TU_MILLISECONDS = 1;
    public static final int TU_SECONDS = 1000;
    public static final int TU_MINUTES = 60000;
    public static final int TU_HOURS = 3600000;
    public static final int TU_DAYS = 86400000;

    /**
     * Date to string with long format
     *
     * @param context - some context
     * @param date    - date to format
     * @return {@code String} formatted date
     */
    public static String formatDateLong(Context context, Date date) {
        DateFormat dateFormat = android.text.format.DateFormat.getLongDateFormat(context);
        return dateFormat.format(date);
    }

    /**
     * check if the date is less than 7 days
     *
     * @param date - date to check
     * @return {@code true} if the date is less than 7 days
     */
    private static boolean isLessThanOneWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -6);
        return date.after(calendar.getTime());
    }

    /**
     * check if the current date is today
     *
     * @param date - date to check
     * @return {@code true} if the date is today
     */
    public static boolean isToday(Date date) {
        return android.text.format.DateUtils.isToday(date.getTime());
    }

    /**
     * Is the specific date yasterday
     *
     * @param date - date to check
     * @return {@code true} if the date is yesterday
     */
    public static boolean isYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = calendar.get(Calendar.MONTH);
        int nowMonthDay = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTimeInMillis(date.getTime());
        int thenYear = calendar.get(Calendar.YEAR);
        int thenMonth = calendar.get(Calendar.MONTH);
        int thenMonthDay = calendar.get(Calendar.DAY_OF_MONTH);

        return (thenYear == nowYear)
                && (thenMonth == nowMonth)
                && (thenMonthDay == nowMonthDay);
    }

    /**
     * check for a leap year
     *
     * @param year - the year to check
     * @return {@code true} for a leap year
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * return date string from milliseconds
     *
     * @param milliseconds - time in millis
     * @return yyyy-MM-dd HH:mm:ss formatted string
     */
    public static String millisecondsToString(long milliseconds) {
        return millisecondsToString(milliseconds, SIMPLE_DATE_FORMAT);
    }

    /**
     * return date string from milliseconds with a specific format
     *
     * @param milliseconds - time in millis
     * @param dateFormat   - the format to use
     * @return date formatted string
     */
    public static String millisecondsToString(long milliseconds, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(milliseconds));
    }

    /**
     * create string from millisecond date
     *
     * @param formattedDate - date string in format yyyy-MM-dd HH:mm:ss
     * @return time in milliseconds
     */
    public static long stringToMilliseconds(String formattedDate) {
        return stringToMilliseconds(formattedDate, SIMPLE_DATE_FORMAT);
    }

    /**
     * create string from millisecond date
     *
     * @param formattedDate - date string in format yyyy-MM-dd HH:mm:ss
     * @param dateFormat    - how to format the string
     * @return time in milliseconds
     */
    public static long stringToMilliseconds(String formattedDate, SimpleDateFormat dateFormat) {
        try {
            return dateFormat.parse(formattedDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * create a date from formatted string
     *
     * @param formattedDate - the formatted string
     * @return Date
     */
    public static Date stringToDate(String formattedDate) {
        return stringToDate(formattedDate, SIMPLE_DATE_FORMAT);
    }

    /**
     * convert string to Date object
     *
     * @param formattedDate - string to convert
     * @param dateFormat    -
     * @return {@code Date} Date object from the string value
     */
    public static Date stringToDate(String formattedDate, SimpleDateFormat dateFormat) {
        return new Date(stringToMilliseconds(formattedDate, dateFormat));
    }

    /**
     * Create a formatted date
     *
     * @param date - date to format
     * @return a yyyy-MM-dd HH:mm:ss formatted string
     */
    public static String dateToString(Date date) {
        return dateToString(date, SIMPLE_DATE_FORMAT);
    }

    /**
     * Create a formatted date
     *
     * @param date       - date to format
     * @param dateFormat - the Date format to use
     * @return a date formatted string
     */
    public static String dateToString(Date date, SimpleDateFormat dateFormat) {
        return dateFormat.format(date);
    }

    /**
     * converts date object to milliseconds
     *
     * @param date - object to convert
     * @return long
     */
    public static long dateToMilliseconds(Date date) {
        return date.getTime();
    }

    /**
     * convert milliseconds to date object
     *
     * @param milliseconds - time to use
     * @return Date
     */
    public static Date millisecondsToDate(long milliseconds) {
        return new Date(milliseconds);
    }

    /**
     * converts milliseconds to specific time unit
     *
     * @param milliseconds - time in milliseconds
     * @param timeUnit     - time unit to use
     * @return long in Time Unit
     */
    private static long millisecondsToTimeUnit(long milliseconds, int timeUnit) {
        switch (timeUnit) {
            case TU_MILLISECONDS:
            case TU_SECONDS:
            case TU_MINUTES:
            case TU_HOURS:
            case TU_DAYS:
                return Math.abs(milliseconds) / timeUnit;
        }
        return -1;
    }


    /**
     * get current time in milliseconds
     *
     * @return long time in milliseconds
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in formatted string
     *
     * @return a yyyy-MM-dd HH:mm:ss formatted string
     */
    public static String getCurrentTimeString() {
        return millisecondsToString(getCurrentTimeMillis());
    }

    /**
     * get formatted string from current time in milliseconds
     *
     * @param dateFormat - the date format to use
     * @return String time in milliseconds
     */
    public static String getCurrentTimeString(SimpleDateFormat dateFormat) {
        return millisecondsToString(getCurrentTimeMillis(), dateFormat);
    }

    /**
     * @return a Date object with the current time
     */
    public static Date getCurrentTimeDate() {
        return new Date();
    }

    /**
     * calculate time interval from now
     *
     * @param date     - date to compare
     * @param timeUnit - time unit to get the result
     * @return long time in time unit
     */
    public static long getTimeIntervalFromNow(Date date, int timeUnit) {
        return getTimeIntervalBetweenDates(getCurrentTimeDate(), date, timeUnit);
    }

    /**
     * calculate time interval from now
     *
     * @param dateString - date to compare
     * @param timeUnit   - time unit to get the result
     * @return long time in time unit
     */
    public static long getTimeIntervalFromNow(String dateString, int timeUnit) {
        return getTimeIntervalFromNow(dateString, timeUnit, SIMPLE_DATE_FORMAT);
    }


    /**
     * calculate time interval from now with specific format
     *
     * @param dateString - date to compare
     * @param timeUnit   - time unit to get the result
     * @param dateFormat - the format to use
     * @return long time in time unit
     */
    public static long getTimeIntervalFromNow(String dateString, int timeUnit, SimpleDateFormat dateFormat) {
        return getTimeIntervalBetweenDates(getCurrentTimeString(), dateString, timeUnit, dateFormat);
    }

    /**
     * calculate time interval between 2 dates
     *
     * @param dateString1 - first date
     * @param dateString2 - second date
     * @param timeUnit    - time unit to get the result
     * @return time in time unit
     */
    public static long getTimeIntervalBetweenDates(String dateString1, String dateString2, int timeUnit) {
        return getTimeIntervalBetweenDates(dateString1, dateString2, timeUnit, SIMPLE_DATE_FORMAT);
    }

    /**
     * calculate time interval between 2 dates with a specific date format
     *
     * @param dateString1 - first date
     * @param dateString2 - second date
     * @param timeUnit    - time unit to get the result
     * @param dateFormat  - the format used in the dates
     * @return {@code long} the time interval between two dates
     */
    public static long getTimeIntervalBetweenDates(String dateString1, String dateString2, int timeUnit, SimpleDateFormat dateFormat) {
        return millisecondsToTimeUnit(stringToMilliseconds(dateString1, dateFormat)
                - stringToMilliseconds(dateString2, dateFormat), timeUnit);
    }

    /**
     * calculate time interval between 2 dates
     *
     * @param date1    - first date
     * @param date2    - second date
     * @param timeUnit - time unit to get the result
     * @return {@code long} the time interval between two dates
     */
    public static long getTimeIntervalBetweenDates(Date date1, Date date2, int timeUnit) {
        return millisecondsToTimeUnit(dateToMilliseconds(date2) - dateToMilliseconds(date1), timeUnit);
    }


}
