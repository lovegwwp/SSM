package com.jyss.mst.entity.JsonEntity;

import java.util.List;

public class PatGroup {

	private String groupName;// 组名
	private int totalNum;// 总人数
	private List<MyPat> MyPatList;// 病人好友列表详细信息

	// private Object ObjectList;// 病人好友列表详细信息

	// public Object getObjectList() {
	// return ObjectList;
	// }
	//
	// public void setObjectList(Object objectList) {
	// ObjectList = objectList;
	// }

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

	public List<MyPat> getMyPatList() {
		return MyPatList;
	}

	public void setMyPatList(List<MyPat> myPatList) {
		MyPatList = myPatList;
	}

	public PatGroup(String groupName, int totalNum, List<MyPat> myPatList) {
		super();
		this.groupName = groupName;
		this.totalNum = totalNum;
		MyPatList = myPatList;
	}

	/*
	 * public PatGroup(String groupName, int totalNum, Object ObjectList) {
	 * super(); this.groupName = groupName; this.totalNum = totalNum;
	 * this.ObjectList = ObjectList; }
	 */

}
