package com.lixin.utils;

import android.util.Log;

/**
 * Created by lixin .
 */
public class LogUtil {
    private static String log_tag = "LX";
    private static boolean is_output = true;
    /**
     * 输出log操作
     *
     * @MethodDescription log
     * @param key
     * @param value
     * @exception
     * @since 1.0.0
    builder.append(value);
     */
    public static void log(String key, String value) {
        StringBuilder builder = new StringBuilder();
        builder.append(key);
        builder.append("====");
        builder.append(value);
        log(builder.toString());
    }

    public static void log(String key, int value) {
        StringBuilder builder = new StringBuilder();
        builder.append(key);
        builder.append("====");
        builder.append(value);
        log(builder.toString());
    }

    /**
     * 输出log操作
     *
     * @MethodDescription log
     * @param msg
     * @exception
     * @since 1.0.0
     */
    public static void log(String msg) {
        if (is_output) {

            Log.i(log_tag, msg);

        }

    }

}
