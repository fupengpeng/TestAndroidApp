package com.jiudianlianxian.utils;

import java.io.File;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.widget.SlidingPaneLayout.PanelSlideListener;

import com.jiudianlianxian.entity.ChildInfo;
import com.jiudianlianxian.entity.GroupInfo;
import com.jiudianlianxian.memoryspace.R;


/**
 * ������������
 * @author fupengpeng
 *
 */
public class ClearUtil {
	/*
	 * ʵ��
	 *     GroupInfo--->name  size  isLoading  ArrayList<ChildInfo> content
	 *     ChildInfo--->isChecked  icon  lable  size  File
	 */
	private static ArrayList<GroupInfo> mGroupList;
	public static String [] groupName={"�ֻ��ڴ�����","�ֻ���������","sd�ڴ�����","sd��������","�ļ���������"};
	public static final int INIT_FINISH=200;
	public static final int LOADING=201;
	public static final int FINISH=202;
	public static Handler mHandler;
	/**
	 * ��ȡ��������������Ϣ
	 * @return
	 */
	public static ArrayList<GroupInfo> getClearInfo(Context context,Handler handler){
		mHandler=handler;
		//ʹ�ü���
		init();
		/* 
		 * װ����
		 *     1.��ȡ�����еİ���
		 *     2.��ȡ����Ӧ�õ�Context
		 *     3.��ȡ��Ӧ���ļ�
		 */
		handler.sendEmptyMessage(INIT_FINISH);
		PackageManager pm=context.getPackageManager();//��ȡgetPackageManager����ȡ������ǰ��
		List<PackageInfo> lists=pm.getInstalledPackages(0);//��ȡ���еİ���
		for (PackageInfo packageInfo : lists) {//�������еİ���
			String packageName=packageInfo.packageName;//��ȡ���е�Ӧ����
			Drawable icon=packageInfo.applicationInfo.loadIcon(pm);//����Ӧ�ð���ͼ��
			String lable=packageInfo.applicationInfo.loadLabel(pm).toString();//��ȡ����Ӧ�õİ���
			//���ݰ�����ȡ��Ӧ����Context pkgContext����Ϊ��
			Context pkgContext=getPackageContext(packageName, context);// ����getPackageContext������ȡ����Ӧ�õ�Context
			//�ֻ��ڴ�    ͨ��pkgContext��ȡ
			if (pkgContext==null) {//�ж��Ƿ�Ϊ��
				continue;//��������ѭ��
			}
			File phoneFile=pkgContext.getFilesDir();//��ȡ�ֻ��ڴ��ļ�
			//��ȡ�ļ�phoneFile��С
			long phoneFileSize=getSize(phoneFile);//����getSize()�������㲢��ȡ�ļ���С
			if (phoneFileSize>0) {//�ж�����ļ�����0��˵��������
				ChildInfo cInfo=new ChildInfo(icon, lable, phoneFileSize, phoneFile);
				mGroupList.get(0).content.add(cInfo);//�������ļ�װ��mGroupList
				mGroupList.get(0).groupSize+=phoneFileSize;//��ͳ�������ļ��Ĵ�С
			}
			//�ֻ���������
			File phoneCache=pkgContext.getCacheDir();
			//��ȡ�ļ�phoneFile��С
			long phoneCacheSize=getSize(phoneCache);
			if (phoneCacheSize>0) {
				ChildInfo cInfo=new ChildInfo(icon, lable, phoneCacheSize, phoneCache);
				mGroupList.get(1).content.add(cInfo);
				mGroupList.get(1).groupSize+=phoneCacheSize;
			}
			//sd���ڴ�����
			File SDFile=null;
			try {
				SDFile=pkgContext.getExternalFilesDir(null);//null��Ϊlable
			} catch (Exception e) {
			}
			//��ȡ�ļ�phoneFile��С
			long SDFileSize=getSize(SDFile);
			if (SDFileSize>0) {
				ChildInfo cInfo=new ChildInfo(icon, lable, SDFileSize, SDFile);
				mGroupList.get(2).content.add(cInfo);
				mGroupList.get(2).groupSize+=SDFileSize;
			}
			//sd����������
			File SDCache=null;
			try {
				SDCache=pkgContext.getExternalCacheDir();
			} catch (Exception e) {
			}
			//��ȡ�ļ�phoneFile��С
			long SDCacheSize=getSize(SDCache);
			if (SDCacheSize>0) {
				ChildInfo cInfo=new ChildInfo(icon, lable, SDCacheSize, SDCache);
				mGroupList.get(3).content.add(cInfo);
				mGroupList.get(3).groupSize+=SDCacheSize;
			}
		}
		//��ȡ���       �ļ���������
		File cacheFile=Environment.getDownloadCacheDirectory();//���������ļ�����ļ���
		listCacheFile(cacheFile, context);
		return mGroupList;
	}
	/*
	 * ��ʼ������
	 */
	public static void init(){
		if (mGroupList==null) {//�ж��Ƿ�Ϊ�գ���ֹ��Ϊ�������ݶ��ۼ�����
			mGroupList=new ArrayList<GroupInfo>();
			for (int i = 0; i < groupName.length; i++) {
				ArrayList<ChildInfo> content=new ArrayList<ChildInfo>();
				GroupInfo info=new GroupInfo(groupName[i], 0, content);//content ��Ҫһ��װ��ChildInfo����
				mGroupList.add(info);
			}
		}else {//����֮ǰ������
			for (GroupInfo info : mGroupList) {
//				info.content=new ArrayList<ChildInfo>();//����info���������
				info.content.clear();//����info���������
				info.groupSize=0;//����groupInfo��ʾ������
			}
		}
		
		
	}
	
	/**
	 * ����cacheFile�ļ�
	 * @param cacheFile
	 */
	public static void listCacheFile(File cacheFile,Context context){
		if (cacheFile==null&&cacheFile.exists()) {//�Ƿ����
			if (cacheFile.isDirectory()&&cacheFile.canRead()) {//�Ƿ�ɶ�
				File[] files=cacheFile.listFiles();
				for (File file : files) {
					listCacheFile(file,context);
				}
			}else {
				//��ȡ�ļ�
				
				ChildInfo cInfo=new ChildInfo(context.getResources().getDrawable(
						R.drawable.icon_telmgr), cacheFile.getName(), cacheFile.length(), cacheFile);
				mGroupList.get(4).groupSize+=cacheFile.length();
				mGroupList.get(4).content.add(cInfo);
			}
		}
		
	}
	/**
	 * 
	 * @return
	 */
	public static ArrayList<GroupInfo> getData(){
		init();
		return mGroupList;
	}
	/**
	 * 2.��ȡ����Ӧ�õ�Context
	 *     ͨ��������ȡ��Ӧ����Context
	 * @param packageName
	 * @param context
	 * @return
	 */
	public static Context getPackageContext(String packageName,Context context){
		Context pkgContext=null;//��ʼ��pkgContext
		try {
			pkgContext=context.createPackageContext(packageName,Context.CONTEXT_IGNORE_SECURITY);
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pkgContext;
	}
	/**
	 * ÿ�ζ�ȡ�ļ�ʱ ������
	 * @param file
	 * @return
	 */
	public static long getSize(File file){//ÿ��ȡһ�Σ�����һ��
		size=0;//ÿ��ȡһ�Σ�����һ��
		mHandler.sendEmptyMessage(LOADING);
		return getFileSize(file);//���ö�ȡ�ļ��ķ���
//		return size;
	}
	/**
	 * ��ȡ�ļ���С
	 * @param file
	 * @return
	 */
	static long size=0;//��ʼ����¼��ȡ�ļ��Ĵ�С
	public static long getFileSize(File file){
		if (file!=null&&file.exists()) {//�жϴ��ļ���Ϊ���Ҵ���
			if (file.isDirectory()&&file.canRead()) {//�ж��Ƿ����ļ��в��ҿɶ�
				File [] files=file.listFiles();//�����ļ���������ļ��л����ļ�
				for (File file2 : files) {
					getFileSize(file2);//�ݹ�
				}
			}else {//�ļ�  ֱ�Ӷ�
				size+=file.length();//��¼��ȡ���ļ���С
			}
		}
		return size;
	}
//	static OnFileLoadingListener mOnFileLoadingListener;
//	public void SetOnFileLoadingListener(OnfileLoadingLinstener mOnFileLoadingListener){
//		this.mOnFileLoadingListener=mOnFileLoadingListener;
//	}
//	/**
//	 * �ص��ӿ�
//	 * @author Administrator
//	 *
//	 */
//	interface OnFileLoadingListener{
//		void onLoading();
//	}
}
