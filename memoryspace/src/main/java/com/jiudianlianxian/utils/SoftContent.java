package com.jiudianlianxian.utils;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.jiudianlianxian.entity.AppInfo;

/**
 * 获取软件信息
 */
public class SoftContent {
	/**
	 * 获取手机中的应用信息
	 *
	 */
	public static final int ALL_SOFT=200;//所有应用
	public static final int SYSTEM_SOFT=201;//系统应用
	public static final int USER_SOFT=202;//用户应用
	public static ArrayList<AppInfo> getSoftInfo(Context mContext, int type){
		/**
		 * 1.PackageManager   获取手机中的应用
		 *     Context.getPackageManager();
		 *     getInstalledPackages(0);  拿到手机中安装的所有应用
		 *
		 *     拿到 icon lable         packageName versionName
		 *     将信息装起来
		 * 2.区分用户软件   系统软件
		 *
		 *
		 */

		ArrayList<AppInfo> listAll=new ArrayList<AppInfo>();//声明并初始化集合   放所有的应用信息
		ArrayList<AppInfo> listStstem=new ArrayList<AppInfo>();
		ArrayList<AppInfo> listUser=new ArrayList<AppInfo>();
		PackageManager pm=mContext.getPackageManager();//拿到对象
		List<PackageInfo> list=pm.getInstalledPackages(0);//获得已安装的多个应用包信息，0代表所有的包
		for (PackageInfo packageInfo : list) {//遍历所有应用信息，从中筛选
			ApplicationInfo appInfo=packageInfo.applicationInfo;
			String packageName=packageInfo.packageName;//包名
			String versionName=packageInfo.versionName;//版本名
			//Drawable    Bitmap
			Drawable icon=appInfo.loadIcon(pm);//应用图标
			String lable=appInfo.loadLabel(pm).toString();//应用信息
			AppInfo appInfo2=new AppInfo(lable, packageName, versionName, icon);
			//区分系统  还是  用户
			if ((appInfo.flags&ApplicationInfo.FLAG_SYSTEM)>0) {//区分判断
				listStstem.add(appInfo2);
			}else {
				listUser.add(appInfo2);
			}
			listAll.add(appInfo2);
		}
		if (type==USER_SOFT) {
			return listUser;
		}else if(type==SYSTEM_SOFT){
			return listStstem;
		}
		return listAll;
	}
}




