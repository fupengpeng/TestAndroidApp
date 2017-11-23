package com.jiudianlianxian.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * 手机检测工具类
 * @author fupengpeng
 *
 */
public class PhoneTestUtil {
	/**
	 * 获取CPU的名称
	 *     /proc/meminfo  (运行时总内存)
	 *     /proc  获取设备运行时的一些信息
	 *     /proc/cpuinfo
	 * @return
	 */
	public static String getCPUName(){
		//使用高级流读取文件第一行
		BufferedReader buffReader = null;//声明
		try {
			buffReader=new BufferedReader(new FileReader("/proc/cpuinfo"));//根据文件所在文件夹读取文件
			String info=buffReader.readLine();//读取文件第一行
			String [] strs=info.split(":\\s+");//使用split方法分割成数组
			return strs[1];
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (buffReader!=null) {
				try {
					buffReader.close();//关闭流
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "位置";
	}
	/**
	 * 获取CPU的核心数量
	 *     /sys/devices/system/cpu
	 * @return
	 */
	public static int getCPUCores(){
		//拿到路径所对应的file
		File file=new File("/sys/devices/system/cpu");//
		File [] files=file.listFiles(new FileFilter() {//过滤文件
			@Override
			public boolean accept(File pathname) {
				//写一个匹配    cpu0 cpu1 cpu2
				String fileName=pathname.getName();//获取文件名
				return fileName.matches("cpu[0-9]");//判断匹配   使用正则
			}
		});//
		return files.length;//返回CPU核心数量
	}
	/**
	 * 获取手机屏幕分辨率    px
	 * @return
	 */
	public static String getPhoneRadios(Activity activity){
		//DisplayMetrics  获取手机屏幕信息    例如 尺寸  像素密度  分辨率
		DisplayMetrics metrics = new DisplayMetrics();//声明并初始化一个盒子
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int height=metrics.heightPixels;
		int wight=metrics.widthPixels;
		return wight+"*"+height;
	}
	/**
	 * 获取相机的分辨率
	 *
	 * @return
	 */
	public static String getCameraRadios(){
		//注意使用hardware包下面的Camera
		Camera camera=Camera.open();//获取一个相机对象   如果没有后置相机有可能返回null
		if (camera==null) {
			return "";
		}
		Parameters params=camera.getParameters();//获取相机的参数       Parameters相机的内部类
		params.getSupportedFlashModes();//获取聚焦模式
		List<Size> sizes=params.getSupportedPictureSizes();//获取支持图片尺寸进而获取相机像素
		Collections.sort(sizes,new Comparator<Size>() {//排序        Collections 操作集合的类
			@Override
			public int compare(Size lhs, Size rhs) {//对比
				//比对lhs与rhs
				return rhs.height*rhs.width-lhs.height*lhs.width;//返回对比结果
			}
		});
		for (Size size : sizes) {//输出排序结果
			Log.e("", size.height+"*"+size.width);
		}
		camera.release();//释放掉  否则下次过滤拿不到
		return sizes.get(0).width+"*"+sizes.get(0).height;//返回相机像素
	}
	/**
	 * 获取基带（手机中的一块电路板  网络   通信  短信）版本
	 * @return
	 */
	public static String getBasebandVersion(){
		//Build   SDK_INT sdk版本          MODEL 型号        RELEASE 发布版本          BRAND 商标    基带版本
		return Build.getRadioVersion()==null?"unknown":Build.getRadioVersion();
	}
	/**
	 * 判断手机是否Root（从手机使用者------管理员）
	 * @return
	 */
	public static String isRoot(){
		/*
		 *  方式1、这两个文件夹下寻找su文件，找到就已经root，没有就没有root
		 *     1./system/bin/su
		 *     2./system/xbin/su
		 */
		if (new File("/system/bin/su").exists()|new File("/system/xbin/su").exists()) {//寻找su文件
			return "是";
		}
		return "否";
	}
	public static boolean isRootTwo(){
		/*
		 * 方式2、如果root了，可以查看data目录
		 */
		OutputStream ou=null;
		Runtime runtime=Runtime.getRuntime();
		try {
			Process process=runtime.exec("process");
			//输出流   写
			ou=process.getOutputStream();
			//命令执行完  需要关闭
			//执行命令        li---->List 遍历文件夹        cat--->打开文件
			ou.write("li data \n".getBytes());//执行命令    遍历data目录      "li data"需要转成字节使用方法getBytes()
			ou.write("exit \n".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}//
		finally{
			if (ou!=null) {
				try {
					ou.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return true;
	}
}




















