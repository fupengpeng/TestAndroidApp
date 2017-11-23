package com.jiudianlianxian.utils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * 获取文件信息的帮助类
 */
public class FileUtils {

	/**
	 * 根据文件名后缀判断该文件是否为文档类文件
	 *
	 * @param suffix
	 *            文件名的后缀
	 *
	 * @return 如果是文档类文件返回true，否则返回false
	 */
	public static boolean isTextFile(String suffix) {
		String[] str = { "txt", "doc", "docx", "xls", "xlsx", "ppt", "pptx",
				"pdf", "c", "h", "cpp", "hpp", "java", "js", "html", "xml",
				"xhtml", "css" };
		for (String string : str) {
			if (suffix.equals(string)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 根据文件名后缀判断该文件是否为视频类文件
	 *
	 * @param suffix
	 *            文件名的后缀
	 *
	 * @return 如果是视频类文件返回true，否则返回false
	 */
	public static boolean isVideoFile(String suffix) {
		String[] str = { "avi", "mp4", "rm", "rmvb", "3gp", "flash" };
		for (String string : str) {
			if (suffix.equals(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据文件名后缀判断该文件是否为音频类文件
	 *
	 * @param suffix
	 *            文件名的后缀
	 *
	 * @return 如果是音频类文件返回true，否则返回false
	 */
	public static boolean isAudioFile(String suffix) {
		String[] str = { "mp3", "wav", "wma", };
		for (String string : str) {
			if (suffix.equals(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据文件名后缀判断该文件是否为图像类文件
	 *
	 * @param suffix
	 *            文件名的后缀
	 *
	 * @return 如果是图像类文件返回true，否则返回false
	 */
	public static boolean isImageFile(String suffix) {
		String[] str = { "bmp", "jpg", "gif", "png", "jpeg", "ico", "tiff",
				"xcf" };
		for (String string : str) {
			if (suffix.equals(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据文件名后缀判断该文件是否为压缩文件
	 *
	 * @param suffix
	 *            文件名的后缀
	 *
	 * @return 如果是压缩文件返回true，否则返回false
	 */
	public static boolean isZipFile(String suffix) {
		String[] str = { "zip", "rar", "gz", "tar" };
		for (String string : str) {
			if (suffix.equals(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据文件名后缀判断该文件是否为程序包文件
	 *
	 * @param suffix
	 *            文件名的后缀
	 *
	 * @return 如果是程序包文件返回true，否则返回false
	 */
	public static boolean isProgramFile(String suffix) {
		String[] str = { "apk" };
		for (String string : str) {
			if (suffix.equals(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 格式化文件长度
	 *
	 * @param length
	 *            一个long类型的数，表示文件的长度，单位是：B
	 *
	 * @return 返回一个带有合适单位的，表示文件长度的小于1024的字符串类型的数
	 */
//	public String formatLength(long length) {
//		DecimalFormat format = new DecimalFormat(".0");
//
//		if (length >= 1024 && length < 1024 * 1024) {
//			double len = length * 1.0 / 1024;
//			String string = format.format(len);
//			return string + "K";
//		} else if (length >= 1024 * 1024 && length < 1024 * 1024 * 1024) {
//			double len = length * 1.0 / (1024 * 1024);
//			String string = format.format(len);
//			return string + "M";
//		} else if (length >= 1024 * 1024 * 1024) {
//			double len = length * 1.0 / (1024 * 1024 * 1024);
//			String string = format.format(len);
//			return string + "G";
//		}
//
//		return length + "B";
//	}

	/**
	 * 删除文件
	 *
	 */
	public static void deleteFolderFile(File file,boolean deleteThisPath){
		try {
			if (file.isDirectory()) {//判断文件夹下是否还有文件夹或者文件
				File files[] =file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFolderFile(files[i],deleteThisPath);
				}
			}
			if (deleteThisPath) {
				if (!file.isDirectory()) {//是文件
					file.delete();//直接删除
				}else {//是文件夹目录
					if (file.listFiles().length==0) {//文件夹为空
						file.delete();//直接删除
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}






