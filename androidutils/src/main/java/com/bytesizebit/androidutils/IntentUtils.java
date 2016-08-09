package com.bytesizebit.androidutils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

/***********
 * Android Utils
 * Created by Shahar Barsheshet on 13/11/2015.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ***********/
public class IntentUtils {

    private IntentUtils() {
    }

    /**
     * makes a call
     *
     * @param context     - some context
     * @param phoneNumber - number to call
     */
    public static void callNumber(Context context, String phoneNumber) {
        openDialerActivityWithAction(context, phoneNumber, Intent.ACTION_CALL);
    }

    /**
     * dial a number in the phone's keypad
     *
     * @param context     - some context
     * @param phoneNumber - number to dial
     */
    public static void dialNumber(Context context, String phoneNumber) {
        openDialerActivityWithAction(context, phoneNumber, Intent.ACTION_DIAL);
    }

    /**
     * open dialer and perform action
     * <p>
     * Intent.ACTION_DIAL
     * Intent.ACTION_CALL
     * </p>
     *
     * @param context     - some context
     * @param phoneNumber - number to dial
     * @param action      - action to perform
     */
    public static void openDialerActivityWithAction(Context context, String phoneNumber, String action) {
        Intent callIntent = new Intent(action);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(callIntent, 0);
        ResolveInfo chosenRi = null;
        for (ResolveInfo ri : resolveInfos) {
            if (ri.activityInfo != null && ri.activityInfo.name.startsWith("com.android")) {
                chosenRi = ri;
            }
        }
        if (chosenRi != null) {
            callIntent.setPackage(chosenRi.activityInfo.packageName);
            context.startActivity(callIntent);
        }
    }

    /**
     * open compose email activity
     *
     * @param context   - some context
     * @param addresses - email adress to send to
     * @param subject   - email subject
     */
    public static void openComposeEmailActivity(Context context, String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        startIntentIfPossible(context, intent);
    }

    /**
     * open SMS activity to send SMS
     *
     * @param context     - some context
     * @param phoneNumber - number to send to
     * @param body        - the message
     */
    public static void openSendSmsActivity(Context context, String phoneNumber, String body) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String phone = phoneNumber != null ? phoneNumber : "";
        intent.setData(Uri.parse("sms:" + phone));
        if (StringUtils.isNotBlank(body)) {
            intent.putExtra("sms_body", body);
        }
        startIntentIfPossible(context, intent);
    }

    /**
     * Create a WhatsApp share intent
     *
     * @param body - the text to send
     * @return an Intent to share via whatsapp
     */
    public static Intent createWhatsAppShareIntent(String body) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        return intent;
    }

    /**
     * starts an Activity if it is exists
     *
     * @param context - the context
     * @param intent  - the intent to start
     */
    private static void startIntentIfPossible(Context context, Intent intent) {
        if (isActivityAvailableForIntent(context, intent)) {
            context.startActivity(intent);
        }
    }

    /**
     * check if the is a valid Activity for the intent
     *
     * @param context - the context
     * @param intent  - the intent to test
     * @return {@code true} if Activity exists for that Intent
     */
    public static boolean isActivityAvailableForIntent(Context context, Intent intent) {
        return intent.resolveActivity(context.getPackageManager()) != null;
    }

    /**
     * open the playstore page for the current app
     *
     * @param context - the context
     */
    public static void openPlayStoreAppPage(Context context) {
        final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    /**
     * check if a service is running
     *
     * @param className - service class name
     * @param context   - some context
     * @return {@code true} if service is running
     */
    public static boolean isRunningService(String className, Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(1000);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            ComponentName service = runningServiceInfo.service;
            if (className.equals(service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
