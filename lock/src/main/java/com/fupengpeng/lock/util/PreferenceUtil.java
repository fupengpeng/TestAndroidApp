package com.fupengpeng.lock.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @description  密码保存
 * @author  fupengpeng
 * @date  2018/3/19 0019 14:13
 */
public class PreferenceUtil {
    public static String getGesturePassword(Context context) {
        SharedPreferences sp = context.getSharedPreferences("gesturePassword", Context.MODE_PRIVATE);
        String password = sp.getString("password", "");
        return password;
    }

    public static void setGesturePassword(Context context, String gesturePassword) {
        SharedPreferences sp = context.getSharedPreferences("gesturePassword", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("password", gesturePassword);
        editor.commit();
    }
}
