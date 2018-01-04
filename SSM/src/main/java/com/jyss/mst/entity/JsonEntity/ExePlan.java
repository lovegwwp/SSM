package com.jyss.mst.entity.JsonEntity;

import java.util.List;

public class ExePlan {

	private String exeName;
	private ExeMakeIf exeMakeIf;
	private List<Plan> listPlan;
	private String time;

	public ExePlan(String exeName, ExeMakeIf exeMakeIf, List<Plan> listPlan,
			String time) {
		super();
		this.exeName = exeName;
		this.exeMakeIf = exeMakeIf;
		this.listPlan = listPlan;
		this.time = time;
	}

	public String getExeName() {
		return exeName;
	}

	public void setExeName(String exeName) {
		this.exeName = exeName;
	}

	public ExeMakeIf getExeMakeIf() {
		return exeMakeIf;
	}

	public void setExeMakeIf(ExeMakeIf exeMakeIf) {
		this.exeMakeIf = exeMakeIf;
	}

	public List<Plan> getListPlan() {
		return listPlan;
	}

	public void setListPlan(List<Plan> listPlan) {
		this.listPlan = listPlan;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/*
	 * public ExePlan(String exeName, ExeMakeIf exemakeIf, List<Plan> listPlan,
	 * String time) { super(); this.exeName = exeName; this.exemakeIf =
	 * exemakeIf; this.listPlan = listPlan; this.time = time; }
	 */

}
