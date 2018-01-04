package com.jyss.mst.entity.JsonEntity;

import java.util.List;

public class PlanListEntity {

	private String counts;
	private List<ExePlans> exePlansList;//
	private String exeTime;
	private String name;

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public List<ExePlans> getExePlansList() {
		return exePlansList;
	}

	public void setExePlansList(List<ExePlans> exePlansList) {
		this.exePlansList = exePlansList;
	}

	public String getExeTime() {
		return exeTime;
	}

	public void setExeTime(String exeTime) {
		this.exeTime = exeTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
