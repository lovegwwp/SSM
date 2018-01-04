package com.jyss.mst.entity;

import java.util.Date;

public class Xljh {
	private int id;
	private int dID;// 医生ID
	private int pID;// 病人ID
	private String week;// 第几周
	private String day;// 几天
	private String time;// 第几次
	private String vedioTime;// 训练视频时长
	private String vedioIds;// 添加视频
	private String vedioAccount;// 总共几个视频',
	private String type;// 训练类型 上肢下肢 口型 眼睛',
	private String bfTime;// 默认播放次数,
	private int xlmb;// 训练模板 1是模板 默认0 ',
	private Date createdAt;// 创建时间
	private Date lastModifyTime;// 最新修改时间
	private String cjsj;// 创建时间
	private String xgsj;// 最新修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getdID() {
		return dID;
	}

	public void setdID(int dID) {
		this.dID = dID;
	}

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVedioTime() {
		return vedioTime;
	}

	public void setVedioTime(String vedioTime) {
		this.vedioTime = vedioTime;
	}

	public String getVedioIds() {
		return vedioIds;
	}

	public void setVedioIds(String vedioIds) {
		this.vedioIds = vedioIds;
	}

	public String getVedioAccount() {
		return vedioAccount;
	}

	public void setVedioAccount(String vedioAccount) {
		this.vedioAccount = vedioAccount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public String getXgsj() {
		return xgsj;
	}

	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}

	public int getXlmb() {
		return xlmb;
	}

	public void setXlmb(int xlmb) {
		this.xlmb = xlmb;
	}

	public String getBfTime() {
		return bfTime;
	}

	public void setBfTime(String bfTime) {
		this.bfTime = bfTime;
	}

}
