package com.bytesizebit.androidutils;

import java.util.ArrayList;
import java.util.List;

/***********
 * Android Utils
 * Created by Shahar Barsheshet on 17/10/2015.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ***********/
public class JavaUtils {
    private JavaUtils() {
    }

    /**
     * Get the index of an object
     *
     * @param array        the items
     * @param objectToFind the object
     * @return the index of the object or -1 if not found
     */
    public static int indexOf(Object[] array, Object objectToFind) {
        if (array == null || array.length == 0 || objectToFind == null) {
            return -1;
        }
        for (int i = 0; i < array.length; i++) {
            Object obj = array[i];
            if (obj.equals(objectToFind)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Create a Integer arrayList from String arrayList
     *
     * @param arrayList source array
     * @return Integer arrayList
     */
    public static ArrayList<Integer> convertStringArrayToIntegerArray(List<String> arrayList) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (String str : arrayList) {
            integers.add(Integer.valueOf(str));
        }
        return integers;
    }

    /**
     * Check if object is null and throw
     *
     * @param object  the object to check
     * @param message the message to throw
     * @param <T>     the object type
     * @return the object if not null
     */
    public static <T> T checkNotNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
}
