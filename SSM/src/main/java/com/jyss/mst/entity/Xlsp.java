package com.jyss.mst.entity;

import java.util.Date;

public class Xlsp {
	private int id;
	private String xmID;// 训练项目ID
	private int dID;// 医生ID
	private int pID;// 病人ID
	private String watchTime;// 观看时长
	private String vedioTime;// 视频时长
	private String scores;// 评测内容
	private String comments;// 评测内容
	private String type;// 训练类型 上肢 下肢 ',
	private Date createdAt;// 创建时间
	private Date lastModifyTime;// 最新观看视频时间
	private String cjsj;// 创建时间
	private String xgsj;// 最新观看视频时间
	private Date lastPcTime;// 最新评测时间
	private String pcsj;// 评测时间
	private int status;// 视频状态 是否激活0 未激活
	private String week;// 第几周
	private String day;// 几天
	private String time;// 第几次
	private String titles;// 训练视频标题
	private String vedioType;// 视频类型 口型 动作',
	private String xlPercent;// 训练完成度百分比
	private String vedio;// 视频路径
	private String uname;// 视频路径

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	private int spLen;// 视频总数
	private int bfTime;// 循环次数

	public int getSpLen() {
		return spLen;
	}

	public void setSpLen(int spLen) {
		this.spLen = spLen;
	}

	public int getBfTime() {
		return bfTime;
	}

	public void setBfTime(int bfTime) {
		this.bfTime = bfTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWatchTime() {
		return watchTime;
	}

	public void setWatchTime(String watchTime) {
		this.watchTime = watchTime;
	}

	public String getVedioTime() {
		return vedioTime;
	}

	public void setVedioTime(String vedioTime) {
		this.vedioTime = vedioTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public Date getLastPcTime() {
		return lastPcTime;
	}

	public void setLastPcTime(Date lastPcTime) {
		this.lastPcTime = lastPcTime;
	}

	public String getPcsj() {
		return pcsj;
	}

	public void setPcsj(String pcsj) {
		this.pcsj = pcsj;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public String getXmID() {
		return xmID;
	}

	public void setXmID(String xmID) {
		this.xmID = xmID;
	}

	public String getVedioType() {
		return vedioType;
	}

	public void setVedioType(String vedioType) {
		this.vedioType = vedioType;
	}

	public String getXlPercent() {
		return xlPercent;
	}

	public void setXlPercent(String xlPercent) {
		this.xlPercent = xlPercent;
	}

	public String getVedio() {
		return vedio;
	}

	public void setVedio(String vedio) {
		this.vedio = vedio;
	}

}
