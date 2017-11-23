package com.jiudianlianxian.utils;

import android.content.Context;
import android.widget.Toast;

/**-
 * Toast������
 * @author Administrator
 *
 */
public class ToastUtil{
	public static void showToastLong(Context context,String text){
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
	public static void showToastShort(Context context,String text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}
