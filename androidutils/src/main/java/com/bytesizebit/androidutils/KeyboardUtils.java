package com.bytesizebit.androidutils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/***********
 * Android Utils
 * Created by Shahar Barsheshet on 17/10/2015.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ***********/
public class KeyboardUtils {

    private KeyboardUtils() {
    }

    /**
     * hide soft keyboard
     */
    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * hide soft keyboard
     */
    public static void hideSoftKeyboard(Context context, View view) {
        view.clearFocus();
        InputMethodManager inputmanger = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * show soft keyboard
     */
    public static void showSoftKeyboard(Activity activity) {
        showSoftKeyboard(activity, null);
    }

    /**
     * show soft keyboard
     */
    public static void showSoftKeyboard(Context context, View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(view, 0);
    }

    /**
     * toggle soft keyboard state
     */
    public static void toggleKeyboradState(Context context, EditText edit) {
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }


}
