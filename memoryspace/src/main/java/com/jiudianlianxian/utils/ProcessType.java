package com.jiudianlianxian.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Debug;

import com.jiudianlianxian.entity.AppInfo;
import com.jiudianlianxian.entity.SpeedInfo;


/**
 * 进程类型
 *
 * @author fupengpeng
 *
 */
public enum ProcessType {
	USER {
		//返回用户的运行进程
		@Override
		public ArrayList<SpeedInfo> getRunningProcess(Context context) {
			// TODO Auto-generated method stub
			return getRunningPrcoess(context, USER);
		}
	},
	SYSTEM
			//抽象方法
			{
				//返回系统的运行进程
				@Override
				public ArrayList<SpeedInfo> getRunningProcess(Context context) {
					// TODO Auto-generated method stub
					return getRunningPrcoess(context, SYSTEM);
				}
			};
	public ArrayList<SpeedInfo> getRunningPrcoess
			(Context context,ProcessType type){//判断并区分运行的进程并装进ArrayList<SpeedInfo>集合
		//
		ArrayList<SpeedInfo> speedListInfo=new ArrayList<SpeedInfo>();//初始化装进程的集合
		//拿到用户进程信息---->先要拿到ActivityManager
		ActivityManager am=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		//拿到运行中的进程
		List<RunningAppProcessInfo> listRunningProcess=am.getRunningAppProcesses();//获取
		//获取已经安装的用户应用信息
		ArrayList<AppInfo> installAPP=    //判断区分使用户还是系统已安装应用进程信息
				type==USER?SoftContent.getSoftInfo(context, SoftContent.USER_SOFT)://返回用户的应用信息
						SoftContent.getSoftInfo(context, SoftContent.SYSTEM_SOFT);
		for (RunningAppProcessInfo runningAppProcessInfo : listRunningProcess) {//遍历listRunningProcess集合
			int pid=runningAppProcessInfo.pid;//进程id
			String processName=runningAppProcessInfo.processName;//进程名称    默认包名
			int uid=runningAppProcessInfo.uid;//用户id
			String [] pkgList=
					runningAppProcessInfo.pkgList;//运行在该进程下的所有应用程序的包名
			//拿到对应存储信息
			int[] pids=new int []{pid};//将单个pid初始化成数组 并传递
			Debug.MemoryInfo[] memInfo=
					am.getProcessMemoryInfo(pids);//拿到对应的存储信息  需要一个pid数组
			long memProcess=
					memInfo[0].dalvikPrivateDirty*1024;// 获取第0个，进程所占内存大小    kb
			for (String string : pkgList) {
				for (AppInfo insApp:installAPP) {
					//说明匹配成功       此安装的app正在运行
					if (string.equals(insApp.getPackageName())) {
						SpeedInfo info=new SpeedInfo(insApp.getIcon(), insApp.getLable(), memProcess, type, string);
						speedListInfo.add(info);
					}
				}
			}
		}
		return speedListInfo;
	}
	public abstract ArrayList<SpeedInfo> getRunningProcess(Context context);
}