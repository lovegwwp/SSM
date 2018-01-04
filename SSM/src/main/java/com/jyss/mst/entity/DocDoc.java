package com.jyss.mst.entity;

import java.util.Date;

public class DocDoc {
	private int id;
	private int dId;// 医生ID
	private int docId;// '医生加医生时 type=2',
	private int groupId;//
	private String newGroupName;// 新组名
	private int zdId;// 医生诊断详情ID',
	private int status;// '1初始添加状态 2 通过好友状态 3 拒绝还有状态 0解绑状态 移除关系',
	private int type;// 1=病人指向医生 2=医生指向医生',
	private Date createdAt;//
	private Date lastModifyTime;//
	private String cjsj;// 创建时间
	private String xgsj;// 最新修改时间
	private String docGroupName;// 医生对病人的分组名称
	private String docPs;// 医生对病人的备注
	private String yzInfo;// 医生对病人的好友验证消息
	// 视图字段
	private String account;
	private String sex;
	private String age;
	private String uname;
	private String avatar;
	private String job;
	private String dwName;
	private String ksName;
	private String lableNames;
	private int isRead;
	private String skills;// 擅长技能
	private String abstracts;// 简介
	private String scores;// 评分星级
	private String nick;// 昵称

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getNewGroupName() {
		return newGroupName;
	}

	public void setNewGroupName(String newGroupName) {
		this.newGroupName = newGroupName;
	}

	public int getZdId() {
		return zdId;
	}

	public void setZdId(int zdId) {
		this.zdId = zdId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
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

	public String getDocGroupName() {
		return docGroupName;
	}

	public void setDocGroupName(String docGroupName) {
		this.docGroupName = docGroupName;
	}

	public String getDocPs() {
		return docPs;
	}

	public void setDocPs(String docPs) {
		this.docPs = docPs;
	}

	public String getYzInfo() {
		return yzInfo;
	}

	public void setYzInfo(String yzInfo) {
		this.yzInfo = yzInfo;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getDwName() {
		return dwName;
	}

	public void setDwName(String dwName) {
		this.dwName = dwName;
	}

	public String getKsName() {
		return ksName;
	}

	public void setKsName(String ksName) {
		this.ksName = ksName;
	}

	public String getLableNames() {
		return lableNames;
	}

	public void setLableNames(String lableNames) {
		this.lableNames = lableNames;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

}
