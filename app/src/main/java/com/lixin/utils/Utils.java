package com.lixin.utils;

/**
 * Created by sunger on 2015/12/15.
 */

import android.content.Context;
import android.content.res.TypedArray;

import com.lixin.live.R;


public class Utils {

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }



    /**
     * 判断字符串是否为空
     * <p/>
     * 空值返回 true
     *
     * @param str
     */
    public static boolean stringIsEmpty(String str) {
        boolean b = true;
        if (str != null && !str.equals("") && !str.equals("null")) {
            b = false;
        }
        return b;
    }

    public static boolean stringIsNotEmpty(String str) {
        boolean b = true;
        if (stringIsEmpty(str)) {
            b = false;
        }
        return b;
    }
}