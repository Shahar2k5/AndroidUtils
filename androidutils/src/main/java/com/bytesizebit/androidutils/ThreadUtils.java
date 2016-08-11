package com.bytesizebit.androidutils;

import android.os.Looper;
import android.util.Log;

/***********
 * Android Utils
 * Created by Shahar Barsheshet on 11/06/2015.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ***********/
public class ThreadUtils {

    private ThreadUtils() {
    }

    /**
     * makes the thread sleep for some time
     *
     * @param millis time to sleep in millis
     */
    public static void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Log.e("Exception", StringUtils.exceptionStackTraceToString(ex));
        }
    }

    /**
     * Check if running on main thread
     */
    public static void checkUiThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException(
                    "Must be called from the main thread. Was: " + Thread.currentThread());
        }
    }
}
