package com.jyss.mst.entity.JsonEntity;

import java.util.List;

public class DocGroup {

	private String groupName;// 组名
	private int totalNum;// 总人数
	private List<MyDoc> MyDocList;// 医生好友列表详细信息

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public List<MyDoc> getMyDocList() {
		return MyDocList;
	}

	public void setMyDocList(List<MyDoc> myDocList) {
		MyDocList = myDocList;
	}

	public DocGroup(String groupName, int totalNum, List<MyDoc> myDocList) {
		super();
		this.groupName = groupName;
		this.totalNum = totalNum;
		MyDocList = myDocList;
	}

}
