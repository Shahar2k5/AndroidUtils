package com.bytesizebit.androidutils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

/***********
 * Android Utils
 * Created by Shahar Barsheshet on 17/10/2015.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ***********/
public class ScreenUtils {

    private ScreenUtils() {
    }

    /**
     * Get screen width
     *
     * @param context the context
     * @return screen width in pixels
     */
    public static int getScreenWidthInPx(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * Get screen height
     *
     * @param context the context
     * @return screen height in pixels
     */
    public static int getScreenHeightInPx(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    /**
     * Get the screen size
     *
     * @param context some context
     * @return ScreenSize object
     */
    public static ScreenSize getScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return new ScreenSize(metrics.widthPixels, metrics.heightPixels);
    }

    /**
     * Set transparent status bar
     * MIN API 19
     * should be called when onCreate() starts
     *
     * @param activity some activity
     */
    public static void setTransparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * Get status bar height
     *
     * @param context the context
     * @return status bar height in pixels
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * Check if we have status bar
     *
     * @param activity some activity
     * @return {@code true} if we have status bar
     */
    public static boolean hasStatusBar(Activity activity) {
        LayoutParams params = activity.getWindow().getAttributes();
        return (params.flags & LayoutParams.FLAG_FULLSCREEN) != LayoutParams.FLAG_FULLSCREEN;
    }

    /**
     * Get the action bar height
     *
     * @param activity some activity
     * @return action bar height in pixels
     */
    public static int getActionBarHeight(Activity activity) {
        TypedValue tv = new TypedValue();
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
        }
        return 0;
    }

    /**
     * Take a screenshot with the status bar
     *
     * @param activity the activity to capture
     * @return a Bitmap
     */
    public static Bitmap takeScreenShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidthInPx(activity);
        int height = getScreenHeightInPx(activity);
        Bitmap bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * Take a screenshot without the status bar
     *
     * @param activity the activity to capture
     * @return a Bitmap
     */
    public static Bitmap takeScreenShoteWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int statusBarHeight = getStatusBarHeight(activity);
        int width = getScreenWidthInPx(activity);
        int height = getScreenHeightInPx(activity);
        Bitmap bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * Check if the screen is locked
     *
     * @param context some context
     * @return {@code true} if the screen is lock
     */
    public static boolean isScreenLocked(Context context) {
        KeyguardManager km = (KeyguardManager) context
                .getSystemService(Context.KEYGUARD_SERVICE);
        return km.inKeyguardRestrictedInputMode();
    }

    private static class ScreenSize {
        private int screenWidth;
        private int screenHeight;

        public ScreenSize(int screenWidth, int screenHeight) {
            this.screenWidth = screenWidth;
            this.screenHeight = screenHeight;
        }

        public int getScreenWidth() {
            return screenWidth;
        }

        public void setScreenWidth(int screenWidth) {
            this.screenWidth = screenWidth;
        }

        public int getScreenHeight() {
            return screenHeight;
        }

        public void setScreenHeight(int screenHeight) {
            this.screenHeight = screenHeight;
        }
    }
}
