package com.jyss.mst.entity.JsonEntity;

import java.util.List;

public class JhJsonEntity {
	private String pId;
	private String dId;
	private String exeName;
	private String type;
	private ExeMakeIf exeMakeIf;
	private String time;
	private String uuIdNum;
	private List<PlanListEntity> weekDayTimesList;

	public String getPId() {
		return pId;
	}

	public void setPId(String pId) {
		this.pId = pId;
	}

	public String getDId() {
		return dId;
	}

	public void setDId(String dId) {
		this.dId = dId;
	}

	public String getExeName() {
		return exeName;
	}

	public void setExeName(String exeName) {
		this.exeName = exeName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ExeMakeIf getExeMakeIf() {
		return exeMakeIf;
	}

	public void setExeMakeIf(ExeMakeIf exeMakeIf) {
		this.exeMakeIf = exeMakeIf;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUuIdNum() {
		return uuIdNum;
	}

	public void setUuIdNum(String uuIdNum) {
		this.uuIdNum = uuIdNum;
	}

	public List<PlanListEntity> getWeekDayTimesList() {
		return weekDayTimesList;
	}

	public void setWeekDayTimesList(List<PlanListEntity> weekDayTimesList) {
		this.weekDayTimesList = weekDayTimesList;
	}

}
