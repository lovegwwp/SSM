package com.jyss.mst.entity;

import java.util.Date;

import com.jyss.mst.entity.JsonEntity.BfTimeJsonEntity;

public class PatXlFa {
	// 视图字段p_xljh
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
	private int xlmb;// 训练模板 1是模板 默认0 ',
	private Date createdAt;// 创建时间
	private Date lastModifyTime;// 最新修改时间
	private String cjsj;// 创建时间
	private String xgsj;// 最新修改时间
	// 表联合字段 p_xlsp
	private String spid;// 视频key
	private String xmId;// xlxm key
	private String titles;// 视频标题
	private String watchTime;// 观看时长
	private String spTime;// 视频时长
	private String scores;// 评测分数
	private String comments;// 评测内容
	private String lastPcTime;// 评测时间
	private String pcsj;// 评测时间格式化'
	private String xlPercent;// 训练完成度百分比
	private String bfTime;// 默认播放次数,
	// 视频路径
	// private List<Xlsp> spList;// 具体训练视频\
	private BfTimeJsonEntity spEntity;// 具体训练视频\
	private String item;// 自定义编号

	public BfTimeJsonEntity getSpEntity() {
		return spEntity;
	}

	public void setSpEntity(BfTimeJsonEntity spEntity) {
		this.spEntity = spEntity;
	}

	public String getXlPercent() {
		return xlPercent;
	}

	public void setXlPercent(String xlPercent) {
		this.xlPercent = xlPercent;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

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

	public int getXlmb() {
		return xlmb;
	}

	public void setXlmb(int xlmb) {
		this.xlmb = xlmb;
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

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String getXmId() {
		return xmId;
	}

	public void setXmId(String xmId) {
		this.xmId = xmId;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public String getWatchTime() {
		return watchTime;
	}

	public void setWatchTime(String watchTime) {
		this.watchTime = watchTime;
	}

	public String getSpTime() {
		return spTime;
	}

	public void setSpTime(String spTime) {
		this.spTime = spTime;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getLastPcTime() {
		return lastPcTime;
	}

	public void setLastPcTime(String lastPcTime) {
		this.lastPcTime = lastPcTime;
	}

	public String getPcsj() {
		return pcsj;
	}

	public void setPcsj(String pcsj) {
		this.pcsj = pcsj;
	}

	public String getBfTime() {
		return bfTime;
	}

	public void setBfTime(String bfTime) {
		this.bfTime = bfTime;
	}

}
