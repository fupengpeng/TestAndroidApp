package com.jiudianlianxian.entity;

import java.io.File;

import android.graphics.drawable.Drawable;

/**
 * 垃圾清理  信息
 * @author Administrator
 *
 */
public class ChildInfo {
	public boolean isChecked;
	public Drawable icon;
	public String lable;
	public long childSize;
	public File file;
	public ChildInfo(Drawable icon, String lable,
					 long childSize, File file) {
		super();
		this.icon = icon;
		this.lable = lable;
		this.childSize = childSize;
		this.file = file;
	}

}
