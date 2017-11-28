package com.jiudianlianxian.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.support.annotation.RequiresPermission.Read;
import android.text.format.Formatter;
import android.util.Log;

import com.jiudianlianxian.memoryspace.MainActivity;

/**
 * 获取手机内存信息
 *
 * @author Administrator
 *
 */
public class MemoryManager {
	public static final String TAG = "--MemoryManager------------";
	static DecimalFormat dFormat = new DecimalFormat("#.00");
	private static File absoluteFile;
	private static String absolutePath;

	/**
	 * 内存大小格式化
	 * @param size
	 * @return
	 */
	public static String getSize(long size) {
		if (size < 1024) {
			return size + "B";
		} else if (size < 1024 * 1024) {
			return dFormat.format(size / 1024f) + "KB";
		} else if (size < 1024 * 1024 * 1024f) {
			return dFormat.format(size / 1024 / 1024f) + "MB";
		} else {
			return dFormat.format(size / 1024 / 1024 / 1024f) + "GB";
		}
	}

	/**
	 * 获取手机自身内存
	 */
	public static long getSelfMemory(boolean isAvailabl) {
		// 拿到内置存储的自身内存文件
		File file = Environment.getDataDirectory();
		absoluteFile = file.getAbsoluteFile();
		absolutePath = file.getAbsolutePath();

		// //用于计算内存大小 path 文件路径
		// StatFs statf=new StatFs(file.getAbsolutePath());
		// //拿到内存块的数量
		// //注意需要修改manifest中的最小sdk版本
		// long blockCount=statf.getBlockCountLong();
		// //拿到内存块的大小
		// long blockSize=statf.getBlockSizeLong();
		// //相乘
		// long selfMemory=blockCount*blockSize;//单位b
		// File file=Environment.getRootDirectory();
		// File file=Environment.getDownloadCacheDirectory();
		return getMemory(file.getAbsolutePath(), isAvailabl);
	}
	/**
	 * 获取手机内置sd卡的内存
	 *
	 */
	public static long getSelfSDCardsMemory(boolean isAvailabl) {
		// Environment.getExternalStorageState();//注意：需要判断内置sd卡的一个状态
		// 只有在挂载状态才可以使用
		// 判断是否为挂载（可使用）状态
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return 0;
		}
		File file = Environment.getExternalStorageDirectory();
		// //用于计算内存大小 path 文件路径
		// StatFs statf=new StatFs(file.getAbsolutePath());
		// //拿到内存块的数量
		// //注意需要修改manifest中的最小sdk版本
		// long blockCount=statf.getBlockCountLong();
		// //拿到内存块的大小
		// long blockSize=statf.getBlockSizeLong();
		// //相乘
		// long selfMemory=blockCount*blockSize;//单位b
		return getMemory(file.getAbsolutePath(), isAvailabl);
	}
	/**
	 * 用于计算内存
	 *
	 * @param filePath
	 *            所计算内存的文件路径
	 * @return 返回内存大小
	 */
	public static long getMemory(String filePath, boolean isAvailabl) {
		// 用于计算内存大小 path 文件路径
		StatFs statf = new StatFs(filePath);
		// 拿到内存块的数量
		// 注意需要修改manifest中的最小sdk版本
		long blockCount = isAvailabl ? statf.getAvailableBlocksLong() : statf
				.getBlockCountLong();
		// 拿到内存块的大小
		long blockSize = statf.getBlockSizeLong();
		// 相乘
		return blockCount * blockSize;
	}
	// statf.getAvailableBlockslong 计算可用内存
	/**
	 * 获取外置sd卡的内存
	 *
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static long getOutSDCradMemory(Context context, boolean isAvailabl) {
		long outSDCardMemory = 0;
		/**
		 * Returns list of paths for all mountable volumes.拿到所有的sd卡路径
		 */
		StorageManager manager = (StorageManager) // 拿到menager内存管理
				// 使用ActivityManager同样
				context.getSystemService(Context.STORAGE_SERVICE);
		/*
		 * 反射 可调用私有的东西
		 *
		 * getVolumePaths 私有的方法
		 */
		Class<?> managerClass = manager.getClass();// 拿到反射
		/**
		 * name 方法名 parameterTypes 参数类型
		 */
		try {
			Method method = managerClass.getDeclaredMethod("getVolumePaths");// 拿到私有方法
			// 不进行访问效验
			method.setAccessible(true);
			/*
			 * receiver args 参数
			 */
			String[] paths = (String[]) method.invoke(manager);
			for (String string : paths) {
				// LogWapper.e("aaa", "存储路径=" + string);
				if (!string.equals(Environment.getExternalStorageDirectory()
						.getAbsolutePath())) {
					outSDCardMemory += getMemory(string, isAvailabl);// 计算
				}
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outSDCardMemory;
	}

	/**
	 * 拿到手机的运行时可用内存
	 *
	 * @param context
	 * @return
	 */
	public static long getRunAvailableMemory(Context context) {
		ActivityManager manager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		// 拿到存储信息
		MemoryInfo info = new MemoryInfo();
		manager.getMemoryInfo(info);
		// 拿到运行时的信息
//		long avaiMem = info.availMem;// 拿到可用内存
//		long totalMem = info.totalMem;
		// LogWapper.e("aaa", "avaiMem=="+avaiMem+"totalMem=="+totalMem);
		return info.availMem;
	}
	/**
	 * 拿到手机的运行时总内存
	 *
	 * @param
	 */
	public static long getRunMemory() {
		BufferedReader read = null;
		long totalMemory = 0;
		try {
			Reader rea = new FileReader("/proc/meminfo");
			read = new BufferedReader(rea);
			String info = read.readLine();
//			LogWapper.e("aaa", "---" + info);
			String[] str = info.split("\\s+");
			totalMemory = Long.parseLong(str[1]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 拿到流
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (read != null) {
					read.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return totalMemory * 1024;
	}

	//获取可用运存大小
	public static String getAvailMemory(Context context){
		// 获取android当前可用内存大小
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo mi = new MemoryInfo();
		am.getMemoryInfo(mi);
		Long usableOperationLong = mi.availMem;
		return getSize(usableOperationLong);
	}

	//获取总运存大小
	public static String   getOperationMemory(Context context){
		String str1 = "/proc/meminfo";// 系统内存信息文件
		String str2;
		String[] arrayOfString;
		long initial_memory = 0;
		try {
			FileReader localFileReader = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(
					localFileReader, 8192);
			str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
			arrayOfString = str2.split("\\s+");
			initial_memory = (long)(Long.valueOf(arrayOfString[1]).intValue())*1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
			localBufferedReader.close();
		} catch (IOException e) {
		}
		return getSize(initial_memory);
	}



	/** * 获取设备信息 * * @return */
	public static  String getDeviceInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("主板：" + Build.BOARD);
		sb.append("  系统启动程序版本号：" + Build.BOOTLOADER);
		sb.append("  系统定制商： "+ Build.BRAND);
		sb.append("  cpu指令集：" + Build.CPU_ABI);
		sb.append("  cpu指令集2: "+ Build.CPU_ABI2);
		sb.append("  设置参数：" + Build.DEVICE);
		sb.append("  显示屏参数：" + Build.DISPLAY);
		sb.append("  www.2cto.com无线电固件版本：" + Build.getRadioVersion());
		sb.append("  硬件识别码：" + Build.FINGERPRINT);
		sb.append("  硬件名称：" + Build.HARDWARE);
		sb.append("  HOST: "+ Build.HOST);
		sb.append("  修订版本列表：" + Build.ID);
		sb.append("  硬件制造商： "+ Build.MANUFACTURER);
		sb.append("  版本： "+ Build.MODEL);
		sb.append("  硬件序列号：" + Build.SERIAL);
		sb.append("  手机制造商：" + Build.PRODUCT);
		sb.append("  描述Build的标签：" + Build.TAGS);
		sb.append("  TIME: "+ Build.TIME);
		sb.append("  builder类型：" + Build.TYPE);
		sb.append("  USER: "+ Build.USER);
        sb.append("  设备SDK版本 " + Build.VERSION.SDK);
        sb.append("  设备的系统版本 " + Build.VERSION.RELEASE);
        sb.append("--"+Build.VERSION.BASE_OS+"--");
        sb.append("--"+Build.VERSION.CODENAME+"--");
        sb.append("--"+Build.VERSION.INCREMENTAL+"--");
        sb.append("--"+Build.VERSION.SECURITY_PATCH+"--");
        sb.append("--"+Build.VERSION.PREVIEW_SDK_INT+"--");
        sb.append("--"+Build.VERSION.SDK_INT+"--");
        sb.append("--"+Build.VERSION_CODES.BASE+"--");

		return sb.toString();
	}


//	/** * 获得SD卡总大小 * * @return */
//	private String getSDTotalSize() {
//		File path = Environment.getExternalStorageDirectory();
//		StatFs stat = new StatFs(path.getPath());
//		long blockSize = stat.getBlockSize();
//		long totalBlocks = stat.getBlockCount();
//		return Formatter.formatFileSize(MainActivity.this, blockSize * totalBlocks);
//	}
//
//	/** * 获得sd卡剩余容量，即可用大小 * * @return */
//	private String getSDAvailableSize() {
//		File path = Environment.getExternalStorageDirectory();
//		StatFs stat = new StatFs(path.getPath());
//		long blockSize = stat.getBlockSize();
//		long availableBlocks = stat.getAvailableBlocks();
//		return Formatter.formatFileSize(MainActivity.this, blockSize * availableBlocks);
//	}
//
//	/** * 获得机身内存总大小 * * @return */
//	private String getRomTotalSize() {
//		File path = Environment.getDataDirectory();
//		StatFs stat = new StatFs(path.getPath());
//		long blockSize = stat.getBlockSize();
//		long totalBlocks = stat.getBlockCount();
//		return Formatter.formatFileSize(MainActivity.this, blockSize * totalBlocks);
//	}
//
//	/** * 获得机身可用内存 * * @return */
//	private String getRomAvailableSize() {
//		File path = Environment.getDataDirectory();
//		StatFs stat = new StatFs(path.getPath());
//		long blockSize = stat.getBlockSize();
//		long availableBlocks = stat.getAvailableBlocks();
//		return Formatter.formatFileSize(MainActivity.this, blockSize * availableBlocks);
//	}


//	        “status”（int类型）…状态，定义值是BatteryManager.BATTERY_STATUS_XXX。
//			“health”（int类型）…健康，定义值是BatteryManager.BATTERY_HEALTH_XXX。
//			“present”（boolean类型）
//			“level”（int类型）…电池剩余容量
//          “scale”（int类型）…电池最大值。通常为100。
//			“icon-small”（int类型）…图标ID。
//			“plugged”（int类型）…连接的电源插座，定义值是BatteryManager.BATTERY_PLUGGED_XXX。
//			“voltage”（int类型）…mV。
//			“temperature”（int类型）…温度，0.1度单位。例如 表示197的时候，意思为19.7度。
//			“technology”（String类型）…电池类型，例如，Li-ion等等。

}