package com.bytesizebit.androidutils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

/******
 * Android Utils
 * Created by Shahar Barsheshet on 20/06/16.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ******/
public class PermissionUtils {
    public static final int RC_CAMERA_PERM = 10;
    public static final int RC_LOCATION_PERM = 11;
    public static final int RC_MIC_PERM = 12;
    public static final int RC_CALL_PERM = 13;
    public static final int RC_CONTACT_PERM = 14;

    /**
     * Open the app settings to enable permissions
     *
     * @param context the calling Activity or Fragment.
     */
    public static void openPermissionsSettings(Context context) {
        if (context == null) {
            return;
        }
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }

    /**
     * Check if user denied permissions with the flag NEVER ASK AGAIN.
     *
     * @param object      the calling Activity or Fragment.
     * @param deniedPerms the set of denied permissions.
     * @return {@code true} if user denied at least one permission with the flag NEVER ASK AGAIN.
     */
    public static boolean checkDeniedPermissionsNeverAskAgain(Object object, String deniedPerms) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean shouldShowRationale;
            shouldShowRationale = shouldShowRequestPermissionRationale(object, deniedPerms);
            return !shouldShowRationale;
        }
        return false;
    }

    @TargetApi(23)
    private static boolean shouldShowRequestPermissionRationale(Object object, String perm) {
        if (object instanceof Activity) {
            return ActivityCompat.shouldShowRequestPermissionRationale((Activity) object, perm);
        } else if (object instanceof Fragment) {
            return ((Fragment) object).shouldShowRequestPermissionRationale(perm);
        } else
            return object instanceof android.app.Fragment && ((android.app.Fragment) object).shouldShowRequestPermissionRationale(perm);
    }
}
