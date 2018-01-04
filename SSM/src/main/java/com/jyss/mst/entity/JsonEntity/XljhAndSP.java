package com.jyss.mst.entity.JsonEntity;

import java.util.List;

public class XljhAndSP {
	private String dID;// 医生ID
	private String pID;// 病人ID
	// private ExePlan exePlan;// 训练内容
	private List<ExePlan> exePlanList;// 训练内容

	public XljhAndSP(String dID, String pID, List<ExePlan> exePlanList) {
		super();
		this.dID = dID;
		this.pID = pID;
		this.exePlanList = exePlanList;
	}

	public String getdID() {
		return dID;
	}

	public void setdID(String dID) {
		this.dID = dID;
	}

	public String getpID() {
		return pID;
	}

	public void setpID(String pID) {
		this.pID = pID;
	}

	public List<ExePlan> getExePlanList() {
		return exePlanList;
	}

	public void setExePlanList(List<ExePlan> exePlanList) {
		this.exePlanList = exePlanList;
	}

	/*
	 * public ExePlan getExePlan() { return exePlan; }
	 * 
	 * public void setExePlan(ExePlan exePlan) { this.exePlan = exePlan; }
	 */

}
