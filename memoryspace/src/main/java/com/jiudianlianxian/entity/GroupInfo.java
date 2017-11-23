package com.jiudianlianxian.entity;

import java.util.ArrayList;

public class GroupInfo {
	public String groupName;
	public long groupSize;
	public boolean isLoading=true;
	public ArrayList<ChildInfo> content;
	public GroupInfo(String groupName, long groupSize,
					 ArrayList<ChildInfo> content) {
		super();
		this.groupName = groupName;
		this.groupSize = groupSize;
		this.content = content;
	}
}
