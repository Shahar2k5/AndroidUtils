package com.bytesizebit.androidutils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

/***********
 * Android Utils
 * Created by Shahar Barsheshet on 17/10/2015.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ***********/
public class DialogUtils {

    private DialogUtils() {
    }

    /**
     * show an Alert Dialog with one button
     *
     * @param activity       - the context
     * @param title          - dialog title
     * @param text           - dialog message
     * @param buttonText     - text to appear in the dialog button
     * @param buttonListener - button onClick callback
     * @return the AlertDialog
     */
    public static AlertDialog showOneButtonsDialog(
            final Activity activity, final String title, final String text, final String buttonText, final DialogInterface.OnClickListener buttonListener) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        return new AlertDialog.Builder(activity).setTitle(title)
                .setMessage(text)
                .setPositiveButton(buttonText, buttonListener)
                .show();
    }

    /**
     * show an Alert Dialog with two buttons
     *
     * @param activity               - the context
     * @param title                  - dialog title
     * @param text                   - dialog message
     * @param negativeButtonText     - negative text
     * @param leftButtonListener     - negative button callback
     * @param positiveButtonText     - positive button text
     * @param positiveButtonListener - positive button text
     * @return the AlertDialog
     */
    public static AlertDialog showTwoButtonsDialog(
            final Activity activity, final String title, final String text, final String negativeButtonText, final DialogInterface.OnClickListener leftButtonListener, final String
            positiveButtonText, final DialogInterface.OnClickListener positiveButtonListener) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        return new AlertDialog.Builder(activity).setTitle(title)
                .setMessage(text)
                .setPositiveButton(negativeButtonText, leftButtonListener)
                .setNegativeButton(positiveButtonText, positiveButtonListener)
                .show();
    }


    /**
     * Displays a dialog box with an OK button
     *
     * @param activity   - the context
     * @param title      - dialog title
     * @param text       - dialog message
     * @param okListener - button callback
     */
    public static void showOkDialog(final Activity activity, final String title, final String text, final DialogInterface.OnClickListener okListener) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        tryOnUiThread(activity, new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(activity).setTitle(title).setMessage(text).setPositiveButton(android.R.string.ok, okListener).show().setOwnerActivity(activity);
            }
        });
    }

    /**
     * Displays a dialog box with an OK button
     *
     * @param activity - the context
     * @param title    - dialog title
     * @param text     - dialog message
     */
    public static void showOkDialog(final Activity activity, final String title, final String text) {
        showOkDialog(activity, title, text, null);
    }

    /**
     * Displays a dialog box with an OK button
     *
     * @param activity   - the context
     * @param title      - dialog title
     * @param text       - dialog message
     * @param okListener - button callback
     * @return the SlertDialog
     */
    public static AlertDialog createOkDialog(final Activity activity, final String title, final String text, final DialogInterface.OnClickListener okListener) {
        AlertDialog dialog = new AlertDialog.Builder(activity).setTitle(title).setMessage(text).setPositiveButton(android.R.string.ok, okListener).create();
        dialog.setOwnerActivity(activity);
        return dialog;
    }

    /**
     * dismiss dialog safely
     *
     * @param dialog - dialog to dismiss
     */
    public static void dismissDialogSafely(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    // helper to run on the UI thread
    private static void tryOnUiThread(Activity activity, final Runnable runnable) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } catch (Exception e) {
                    // probably window was closed
                }
            }
        });
    }

}
