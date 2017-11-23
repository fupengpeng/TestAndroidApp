package com.jiudianlianxian.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 04.存储文件信息
 * @author Administrator
 *
 */
public class FileInfo implements Serializable{
	public int fileType;//文件类型
	public long fileSize;//文件大小
	public int  fileNumber;//文件数量
	public  ArrayList<FileDetailInfo> listDetail=new ArrayList<FileDetailInfo>();
	//lastModified   返回最后一次被修改的时间
	public int getFileType() {
		return fileType;
	}
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public int getFileNumber() {
		return fileNumber;
	}
	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}
	@Override
	public String toString() {
		return "FileInfo [fileType=" + fileType + ", fileSize=" + fileSize
				+ ", fileNumber=" + fileNumber + "]";
	}
}