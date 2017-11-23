package com.jiudianlianxian.utils;


import android.util.Log;

import com.jiudianlianxian.memoryspace.BuildConfig;

public class LogWapper {
	/**
	 * ��ӡ��־�Ŀ���   �Լ��Ŀ���&���Կ���
	 */
	private static boolean DEBUG=true& BuildConfig.DEBUG;
	/**
	 * ��ӡerror�����Log��־
	 * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
	 */
	public static void e(String tag,String msg){
		if (DEBUG) {
			Log.e(tag, msg);
		}
	}
	/**
	 * ��ӡwarning�����Log��־
	 * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
	 */
	public static void w(String tag,String msg){
		if (DEBUG) {
			Log.w(tag, msg);
		}
	}
	/**
	 * ��ӡinfo�����Log��־
	 * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
	 */
	public static void i(String tag,String msg){
		if (DEBUG) {
			Log.i(tag, msg);
		}
	}
	/**
	 * ��ӡdebug�����Log��־
	 * @param tagtag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
	 */
	public static void d(String tag,String msg){
		if (DEBUG) {
			Log.d(tag, msg);
		}
	}
}
