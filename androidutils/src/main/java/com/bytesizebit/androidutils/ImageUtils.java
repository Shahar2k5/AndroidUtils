package com.bytesizebit.androidutils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;

/***********
 * Android Utils
 * Created by Shahar Barsheshet on 22/11/2015.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ***********/
public class ImageUtils {

    private ImageUtils() {
    }

    private static final int MAX_BITMAP_WIDTH = 640;
    private static final int MAX_BITMAP_HEIGHT = 640;

    /**
     * create a Bitmap from URI
     *
     * @param context - the context
     * @param uri     - the uri to load from
     * @return a Bitmap
     */
    @Nullable
    public static Bitmap getBitmapFromUri(Context context, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * get file system path from a URI
     *
     * @param context    - the context
     * @param contentURI - uri to load from
     * @return String - path to the actual file
     */
    public static String getRealPathFromURI(Context context, Uri contentURI) {
        String result;
        Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    /**
     * normlize a bitmap
     *
     * @param context       - the context
     * @param selectedImage - uri for the selected image
     * @return
     */
    public static Bitmap normalize(Context context, Uri selectedImage) {
        return normalize(context, selectedImage, MAX_BITMAP_WIDTH, MAX_BITMAP_HEIGHT);
    }

    /**
     * normlize a bitmap
     *
     * @param context       - the context
     * @param selectedImage - uri for the selected image
     * @return
     */
    public static Bitmap normalize(Context context, Uri selectedImage, int maxWidth, int maxHeight) {
        Bitmap bm;
        bm = getImageResized(context, selectedImage, maxWidth, maxHeight);
        bm = getImageRotatedByMetadata(context, bm, selectedImage);
        return bm;
    }

    /**
     * get image rotated by degree from metedata
     *
     * @param context - the context
     * @param bitmap  - the bitmap to check
     * @param src     - the uri of the image
     * @return a rotated Bitmap
     */
    public static Bitmap getImageRotatedByMetadata(Context context, Bitmap bitmap, Uri src) {
        try {
            ExifInterface exif = new ExifInterface(ImageUtils.getRealPathFromURI(context, src));
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            Matrix matrix = new Matrix();
            switch (orientation) {
                case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                    matrix.setScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.setRotate(180);
                    break;
                case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                    matrix.setRotate(180);
                    matrix.postScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_TRANSPOSE:
                    matrix.setRotate(90);
                    matrix.postScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.setRotate(90);
                    break;
                case ExifInterface.ORIENTATION_TRANSVERSE:
                    matrix.setRotate(-90);
                    matrix.postScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.setRotate(-90);
                    break;
                case ExifInterface.ORIENTATION_NORMAL:
                case ExifInterface.ORIENTATION_UNDEFINED:
                default:
                    return bitmap;
            }

            try {
                Bitmap oriented = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                bitmap.recycle();
                return oriented;
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * create a fullscreen image and punch a hole inside
     *
     * @param context         - some context
     * @param screenWidth     - the screen width
     * @param screenHeight    - the screen height
     * @param x               - x coordinate of the hole
     * @param y               - y coordinate of the hole
     * @param holeDiameter    - hole width
     * @param backgroundColor - the background color to use
     * @return
     */
    public static Bitmap punchARoundedHoleInABitmap(Context context, int screenWidth, int screenHeight, int x, int y, int holeDiameter, int backgroundColor) {
        Bitmap bitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawColor(context.getResources().getColor(backgroundColor));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawCircle(x + holeDiameter / 2, y + holeDiameter / 2, holeDiameter / 2, paint);
        return bitmap;
    }


    // helper for normalizing image size
    private static Bitmap decodeBitmapWithClosestSampleSize(Context context, Uri theUri, int maxWidth, int maxHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        AssetFileDescriptor fileDescriptor;
        try {
            fileDescriptor = context.getContentResolver().openAssetFileDescriptor(theUri, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);

        options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight);

        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);
    }

    // helper for normalizing image size
    private static Bitmap getImageResized(Context context, Uri selectedImage, int maxWidth, int maxHeight) {
        Bitmap bm = decodeBitmapWithClosestSampleSize(context, selectedImage, maxWidth, maxHeight);
        Bitmap resizedBmp = Bitmap.createScaledBitmap(bm, maxWidth, maxHeight, true);
        if (resizedBmp != bm) {
            bm.recycle();
        }
        return resizedBmp;
    }

    // helper for normalizing image size
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
