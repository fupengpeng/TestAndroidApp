package com.jiudianlianxian.utils;

import java.io.File;
import java.util.ArrayList;



import android.os.Environment;
import android.os.Handler;
import android.renderscript.Element;

import com.jiudianlianxian.entity.FileDetailInfo;
import com.jiudianlianxian.entity.FileInfo;

/**
 * 02.获取手机中文件的信息（带分类）
 * @author Administrator
 *
 */
public class FileMemoryInfoUtil {
	public static final int FILE_ALL=200;//05.02声明
	public static final int FILE_TXT=201;
	public static final int FILE_VEDIO=202;
	public static final int FILE_AUDIO=203;
	public static final int FILE_PIC=204;
	public static final int FILE_ZIP=205;
	public static final int FILE_APK=206;
	public static ArrayList<FileInfo> list=new ArrayList<FileInfo>();//05.01存储文件信息
	static{//静态代码块
		FileInfo infoAll=new FileInfo();//05.03初始化七个
		infoAll.setFileType(0);
		list.add(infoAll);
		FileInfo infoTxt=new FileInfo();
		infoTxt.setFileType(0);
		list.add(infoTxt);
		FileInfo infoVedio=new FileInfo();
		infoVedio.setFileType(0);
		list.add(infoVedio);
		FileInfo infoAudio=new FileInfo();
		infoAudio.setFileType(0);
		list.add(infoAudio);
		FileInfo infoPic=new FileInfo();
		infoPic.setFileType(0);
		list.add(infoPic);
		FileInfo infoZip=new FileInfo();
		infoZip.setFileType(0);
		list.add(infoZip);
		FileInfo infoApk=new FileInfo();
		infoApk.setFileType(0);
		list.add(infoApk);
	}
	static Handler mHandler;
	/**
	 * 03.获取文件（的大小）（分类）
	 *     03.01.获取文件位置(得到文件或者文件夹)  Environment.getExternalStorageDirectory();文件位置
	 *     03.02.读取文件或者文件夹
	 *
	 */
	public static void getFileMemory(final Handler handler){
		mHandler=handler;
		final File file=Environment.getExternalStorageDirectory();//03.01 内置sd卡，文件存储位置
		//03.03耗时操作   放在子线程中  防止主线程阻塞
		new Thread(){//03.03.01开个子线程
			public void run(){//03.03.02重写run方法
				searchFile(file);//03.03.03  调用getFileInfo(file)方法拿到所有的文件（传入file）
				//调用方法getFileInfo(file)所有文件筛选完毕
				handler.sendEmptyMessage(1);
			};
		}//new Thread()至此匿名对象
				.start();//new Thread()至此匿名内部类
	}
	//搜索文件的方法      File file 传进搜索的文件
	public static void  searchFile(File file) {
		/**
		 *  03.02.读取文件或者文件夹    如果是文件夹  并且可读
		 */
		if (file.isDirectory()&&file.canRead()) {//030303.01判断是否为文件夹并且可读
			File[] file1=file.listFiles();//是文件夹  获取文件夹下的所有文件和文件夹
			for (File file2 : file1) {//遍历
				searchFile(file2);//递归
			}
		}else {//判断是文件
			//是     直接读取     大小   类型    数量       04.创建实例
			getFileInfo(file);//重复读取每一个文件
		}
	}
	/**
	 * 05.获取文件信息
	 */
	private static void getFileInfo(File file) {
		//读取文件信息     大小   类型(后缀)     数量
		long length=file.length();//文件大小
		String fileName=file.getName();//拿到文件名    之后拿到文件类型（后缀）
		String [] strs=fileName.split("\\.");//通过正则  点字符切    拿到切断的字符串数组
		if (strs.length>=2) {//判断文件是否有后缀
			String fileSuffix=strs[strs.length-1];//拿到后缀    文件类型
			/*
			 * 区分文件类型       isTextFile（）
			 */
//			list.get(0).setFileSize(list.get(0).getFileSize()+length);//
			list.get(0).fileSize+=length;
			list.get(0).setFileNumber(list.get(0).getFileNumber()+1);
			//详细的东西装进去
			FileDetailInfo detdilInfo=new FileDetailInfo
					( fileName, file.lastModified(), length, file);
			list.get(0).listDetail.add(detdilInfo);
			if (FileUtils.isTextFile(fileSuffix)) {//筛选器    筛选文件类型   大小累加
				list.get(1).fileSize+=length;//
				detdilInfo.fileType=FILE_TXT;
				list.get(1).setFileNumber(list.get(1).getFileNumber()+1);
				list.get(1).listDetail.add(detdilInfo);
			}else if (FileUtils.isVideoFile(fileSuffix)) {
				list.get(2).fileSize+=length;//
				detdilInfo.fileType=FILE_VEDIO;
				list.get(2).setFileNumber(list.get(2).getFileNumber()+1);
				list.get(2).listDetail.add(detdilInfo);
			}else if (FileUtils.isAudioFile(fileSuffix)) {
				list.get(3).fileSize+=length;//
				detdilInfo.fileType=FILE_AUDIO;
				list.get(3).setFileNumber(list.get(3).getFileNumber()+1);
				list.get(3).listDetail.add(detdilInfo);
			}else if (FileUtils.isImageFile(fileSuffix)) {
				list.get(4).fileSize+=length;//
				detdilInfo.fileType=FILE_PIC;
				list.get(4).setFileNumber(list.get(4).getFileNumber()+1);
				list.get(4).listDetail.add(detdilInfo);
			}else if (FileUtils.isZipFile(fileSuffix)) {
				list.get(5).fileSize+=length;//
				detdilInfo.fileType=FILE_ZIP;
				list.get(5).setFileNumber(list.get(5).getFileNumber()+1);
				list.get(5).listDetail.add(detdilInfo);
			}else if (FileUtils.isProgramFile(fileSuffix)) {
				list.get(6).fileSize+=length;//
				detdilInfo.fileType=FILE_APK;
				list.get(6).setFileNumber(list.get(6).getFileNumber()+1);
				list.get(6).listDetail.add(detdilInfo);
			}else {
			}
		}else {
		}
		if (list.get(0).getFileNumber()%50==0) {//判断  读取50个文件在进行更新   防止由于读取一个就更新一次出现的卡顿
			mHandler.sendEmptyMessage(2);
		}
	}
}








