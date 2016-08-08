package com.bytesizebit.androidutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/***********
 * Android Utils
 * Created by Shahar Barsheshet on 20/01/2016.
 * bytesizebit@gmail.com
 * www.bytesizebit.com
 ***********/
public class EncryptionUtils {

    private EncryptionUtils() {
    }

    /**
     * create MD5 String from string
     *
     * @param str - the string
     * @return MD5 String
     */
    public static String getMD5String(String str) {
        return getMD5String(str.getBytes());
    }

    /**
     * create MD5 String with salt
     *
     * @param str  - the string
     * @param salt - salt
     * @return MD5 String
     */
    public static String getMD5String(String str, String salt) {
        return bytesToHex(encryptMD5((str + salt).getBytes()));
    }

    /**
     * create MD5 String from byte array
     *
     * @param bytes - byte array
     * @return MD5 String
     */
    public static String getMD5String(byte[] bytes) {
        return bytesToHex(encryptMD5(bytes));
    }

    /**
     * create MD5 String from byte array with salt
     *
     * @param bytes - byte array
     * @param salt  - salt
     * @return MD5 String
     */
    public static String getMD5String(byte[] bytes, byte[] salt) {
        byte[] dataSalt = new byte[bytes.length + salt.length];
        System.arraycopy(bytes, 0, dataSalt, 0, bytes.length);
        System.arraycopy(salt, 0, dataSalt, bytes.length, salt.length);
        return bytesToHex(encryptMD5(dataSalt));
    }

    /**
     * encrypt byte array
     *
     * @param bytes bytes to encrypt
     * @return encrypted byte array if available
     */
    public static byte[] encryptMD5(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * create a MD5 String from file path
     *
     * @param filePath - path to a file
     * @return
     */
    public static String getMD5File(String filePath) {
        return getMD5File(new File(filePath));
    }

    /**
     * create a MD5 String from file
     *
     * @param file - file to get MD5 from
     * @return MD5 String
     */
    public static String getMD5File(File file) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            FileChannel channel = in.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(buffer);
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {
                }
            }
        }
        return "";
    }

    /**
     * get SHA1 from String
     *
     * @param str - String to get SHA1 from
     * @return SHA1 string
     */
    public static String getSHA(String str) {
        return getSHA(str.getBytes());
    }

    /**
     * get SHA1 from byte array
     *
     * @param bytes - bytes to get SHA1 from
     * @return SHA1 String
     */
    public static String getSHA(byte[] bytes) {
        return bytesToHex(encryptSHA(bytes));
    }

    /**
     * encrypt SHA1
     *
     * @param bytes - bytes to encrypt
     * @return an SHA1 encrypted byte array
     */
    public static byte[] encryptSHA(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(bytes);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * convert bytes to Hex
     *
     * @param src - byte array to convert
     * @return String from converted byte array
     */
    public static String bytesToHex(byte[] src) {
        char[] res = new char[src.length * 2];
        final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        for (int i = 0, j = 0; i < src.length; i++) {
            res[j++] = hexDigits[src[i] >>> 4 & 0x0f];
            res[j++] = hexDigits[src[i] & 0x0f];
        }
        return new String(res);
    }
}
