package com.jyss.mst.entity.JsonEntity;

import java.util.List;

public class Plan {
	private String name;// 第1周第1天第1次
	private String exeTime;// 视频时长
	private String counts;// 视频数量
	// private ExePlans exePlans;// 训练具体视频
	private List<ExePlans> exePlans;// 训练具体视频

	public Plan(String name, String exeTime, String counts,
			List<ExePlans> exePlans) {
		super();
		this.name = name;
		this.exeTime = exeTime;
		this.counts = counts;
		this.exePlans = exePlans;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExeTime() {
		return exeTime;
	}

	public void setExeTime(String exeTime) {
		this.exeTime = exeTime;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public List<ExePlans> getExePlans() {
		return exePlans;
	}

	public void setExePlans(List<ExePlans> exePlans) {
		this.exePlans = exePlans;
	}

}
