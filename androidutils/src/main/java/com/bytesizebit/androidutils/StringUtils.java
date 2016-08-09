package com.bytesizebit.androidutils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

/***********
 * Android Utils
 * Created by Shahar Barsheshet on 12/11/2015.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ***********/
public class StringUtils {

    private StringUtils() {
    }

    /**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     * <p/>
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     */
    public static boolean isBlank(CharSequence str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     * <p/>
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is
     * not empty and not null and not whitespace
     */
    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    /**
     * check if a string is empty
     *
     * @param str - string to check
     * @return {@code true} if empty
     */
    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    /**
     * check if a string is NOT empty
     *
     * @param str - string to check
     * @return {@code true} if NOT empty
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * capital only first letter
     *
     * @param original - the string to change
     * @return a String with first capital letter
     */
    public static String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase();
    }

    /**
     * create a String from stacktrace
     *
     * @param ex - the exception
     * @return a String generated from exception's stacktrace
     */
    public static String exceptionStackTraceToString(Exception ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }

    /**
     * get initials from a string
     *
     * @param string    - string to get initials from
     * @param maxLength - max number of initials
     * @return a String with words initials
     */
    @NonNull
    public static String getInitialsFromString(String string, int maxLength) {
        String[] splitted = string.split(" ");
        String initials = "";
        for (String word : splitted) {
            if (StringUtils.isNotBlank(word)) {
                initials += word.charAt(0);
            }
            if (initials.length() >= maxLength) {
                break;
            }
        }
        return initials.toUpperCase();
    }
}
