package com.jiudianlianxian.entity;
/**
 * 通讯录的通讯信息
 *
 * @author Administrator
 *
 */
public class TelInfo {
	private String  number;
	private String name;
	/**
	 * 封装
	 * @return
	 */
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "TelInfo [number=" + number + ", name=" + name + "]";
	}
	public TelInfo(String number, String name) {
		super();
		this.number = number;
		this.name = name;
	}

}
