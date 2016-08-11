package com.bytesizebit.androidutils;

import android.util.Patterns;

/******
 * Android Utils
 * Created by Shahar Barsheshet on 08/08/2016.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ******/
public class ValidationUtils {
    private ValidationUtils() {
    }

    /**
     * Check if an email is valid
     *
     * @param email the email to check
     * @return {@code true} if the email is valid
     */
    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Check if an IP Address is valid
     *
     * @param IPAddress the IP Address to check
     * @return {@code true} if the email is valid
     */
    public static boolean isValidIPAddress(String IPAddress) {
        return Patterns.IP_ADDRESS.matcher(IPAddress).matches();
    }

    /**
     * Check if a url is valid
     *
     * @param url the url to check
     * @return {@code true} if the url is valid
     */
    public static boolean isValidUrl(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }
}
