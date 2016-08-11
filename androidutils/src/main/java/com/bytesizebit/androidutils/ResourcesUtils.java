package com.bytesizebit.androidutils;

import java.lang.reflect.Field;

/***********
 * Android Utils
 * Created by Shahar Barsheshet on 11/03/2016.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ***********/
public class ResourcesUtils {

    /**
     * Load resource id by name
     *
     * @param resName the name of the resource
     * @param c       some clazz
     * @return the id of the resource
     */
    public static int getResIdFromString(CharSequence resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName.toString());
            return idField.getInt(idField);
        } catch (Exception e) {
            return -1;
        }

    }
}
