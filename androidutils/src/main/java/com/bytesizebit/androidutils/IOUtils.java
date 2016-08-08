package com.bytesizebit.androidutils;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/***********
 * Android Utils
 * Created by Shahar Barsheshet on 17/10/2015.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ***********/
public class IOUtils {

    private IOUtils() {
    }

    /**
     * save a Bitmap to a local file
     *
     * @param bmp                  - the Bitmap to save
     * @param fullPathWithFileName - path and file name
     * @throws IOException
     */
    public static void saveBitmapToFile(Bitmap bmp, String fullPathWithFileName) throws IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(createParentDirIfNotExists(fullPathWithFileName));
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * deletes a selected directory recursively
     *
     * @param dir - the directory to delete
     * @return true is deleted successfully
     */
    public static boolean deleteDir(File dir) {
        if (dir == null) {
            return false;
        }
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                boolean success = deleteDir(new File(dir, child));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * create a directory if needed
     *
     * @param filePath - the path to the directory
     * @return File of the create directory
     */
    public static File createParentDirIfNotExists(String filePath) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        return file;
    }
}
