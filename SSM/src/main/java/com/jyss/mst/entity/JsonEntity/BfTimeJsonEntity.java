package com.jyss.mst.entity.JsonEntity;

import java.util.ArrayList;
import java.util.List;

import com.jyss.mst.entity.Xlsp;

public class BfTimeJsonEntity {
	private String bfTime;
	private List<Xlsp> spList = new ArrayList<Xlsp>();

	public String getBfTime() {
		return bfTime;
	}

	public void setBfTime(String bfTime) {
		this.bfTime = bfTime;
	}

	public List<Xlsp> getSpList() {
		return spList;
	}

	public void setSpList(List<Xlsp> spList) {
		this.spList = spList;
	}

	public BfTimeJsonEntity(String bfTime, List<Xlsp> spList) {
		super();
		this.bfTime = bfTime;
		this.spList = spList;
	}

}
