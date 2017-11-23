package com.jiudianlianxian.entity;

import java.io.Serializable;

import android.graphics.drawable.Drawable;

import com.jiudianlianxian.utils.ProcessType;

/**
 * 进程信息
 * @author fupengpeng
 *
 */
public class SpeedInfo implements Serializable{
	public boolean isChecked;//选中状态
	public Drawable icon;//应用图标
	public String lable;//应用名称
	public long processMem;//进程大小
	public ProcessType type;//进程类型
	public String packageName;//包名（用来结束进程）
	public SpeedInfo(Drawable icon, String lable,
					 long processMem, ProcessType type,String packageName) {
		super();
		this.packageName=packageName;
		this.icon = icon;
		this.lable = lable;
		this.processMem = processMem;
		this.type = type;
	}
}
