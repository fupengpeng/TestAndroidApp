package com.jiudianlianxian.entity;

import java.io.File;
import java.io.Serializable;

/**
 * 文件的详细信息（）
 * @author Administrator
 * 建立实体
 */
public class FileDetailInfo implements Serializable{

	private boolean isChecked;//選中狀態
	private String fileName;//文件名
	private long createTime;//文件创建时间
	private long fileSize;//文件大小
	public File file;//此文件对象
	public int fileType;
	public FileDetailInfo( String fileName,
						   long createTime, long fileSize, File file) {
		super();
//		this.isChecked = isChecked;
		this.fileName = fileName;
		this.createTime = createTime;
		this.fileSize = fileSize;
		this.file = file;
	}
	@Override
	public String toString() {
		return "FileDetailInfo [isChecked=" + isChecked + ", fileName="
				+ fileName + ", createTime=" + createTime + ", fileSize="
				+ fileSize + ", file=" + file + "]";
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public  long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}











}