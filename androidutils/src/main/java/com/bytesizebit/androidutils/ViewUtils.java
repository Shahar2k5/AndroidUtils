package com.bytesizebit.androidutils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

/***********
 * Android Utils
 * Created by Shahar Barsheshet on 07/02/2015.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ***********/
public class ViewUtils {
    public static void setSiblingsVisibility(View view, int visibility) {
        ViewGroup parent = (ViewGroup) view.getParent();
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child != view) {
                child.setVisibility(visibility);
            }
        }
    }

    /**
     * scroll listview to bottom
     *
     * @param listView     - the llistview to scroll
     * @param smoothScroll - should scroll smooth or snap
     */
    public static void listViewScrollToBottom(final ListView listView, boolean smoothScroll) {
        // due to issue with scrolling in listview doesnt reach the end of the list, this small hack is needed - calling the method twice with small delay in between
        if (smoothScroll) {
            listView.post(new Runnable() {
                @Override
                public void run() {
                    listView.smoothScrollToPosition(listView.getCount() - 1);
                }
            });
            listView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listView.smoothScrollToPosition(listView.getCount() - 1);
                }
            }, 100);
        } else {
            listView.post(new Runnable() {
                @Override
                public void run() {
                    listView.setSelection(listView.getCount() - 1);
                }
            });
            listView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listView.setSelection(listView.getCount() - 1);
                }
            }, 100);
        }
    }

    public static void makeUnderlinedTextClickable(TextView textView, ClickableSpan onclick) {
        SpannedString string = (SpannedString) textView.getText();
        int underlineStart = string.nextSpanTransition(0, string.length(), UnderlineSpan.class);
        int underlineEnd = string.nextSpanTransition(underlineStart, string.length(), UnderlineSpan.class);

        SpannableString ss = new SpannableString(string);
        ss.setSpan(onclick, underlineStart, underlineEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }



}
