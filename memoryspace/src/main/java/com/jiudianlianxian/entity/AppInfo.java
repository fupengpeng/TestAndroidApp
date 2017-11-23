package com.jiudianlianxian.entity;

import android.graphics.drawable.Drawable;

/**
 * 软件信息
 * @author Administrator
 *
 */
public class AppInfo {
	private String lable;//应用信息
	private String packageName;//包名
	private String versionName;//版本名
	private Drawable icon;//应用图标
	public boolean isChecked;//是否选中
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "AppInfo [lable=" + lable + ", packageName=" + packageName
				+ ", versionName=" + versionName + ", icon=" + icon + "]";
	}
	public AppInfo(String lable, String packageName, String versionName,
				   Drawable icon) {
		super();
		this.lable = lable;
		this.packageName = packageName;
		this.versionName = versionName;
		this.icon = icon;
	}
}

