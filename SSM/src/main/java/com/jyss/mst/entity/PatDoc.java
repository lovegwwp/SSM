package com.jyss.mst.entity;

import java.util.Date;

public class PatDoc {
	private int id;
	private int dId;// 医生ID
	private int pId;// 病人ID type=1
	private int docId;// '医生加医生时 type=2',
	private int groupId;// 充值金额
	private String groupName;//
	private String newGroupName;
	private int zdId;// 医生诊断详情ID',
	private int status;// '1初始添加状态 2 通过好友状态 3 拒绝还有状态 0解绑状态 移除关系',
	private int type;// 1=病人指向医生 2=医生指向医生',
	private Date createdAt;//
	private Date lastModifyTime;//
	private String cjsj;// 创建时间
	private String xgsj;// 最新修改时间
	// ////视图
	private String account;// 登录账号手机号
	private String avatar; // 头像
	private String uname;// 姓名
	private String sex;// 性别
	private String dw_name;// 单位名称

	private String ks_name;// 科室名称
	private int age;// 年龄
	private String job;// 职务
	private String skills;// 擅长技能
	private int scores;// 星级评价

	public PatDoc() {
		super();
	}

	public PatDoc(int id, int dId, int pId, int docId, int groupId,
			String groupName, String newGroupName, int zdId, int status,
			int type, Date createdAt, Date lastModifyTime, String cjsj,
			String xgsj, String account, String avatar, String uname,
			String sex, String dw_name, String ks_name, int age, String job,
			String skills, int scores, String abstracts, String lableNames,
			String docGroupName, String docPs, String yzInfo, String videoTime,
			String talkTime, int isRead, String duname, String zd) {
		super();
		this.id = id;
		this.dId = dId;
		this.pId = pId;
		this.docId = docId;
		this.groupId = groupId;
		this.groupName = groupName;
		this.newGroupName = newGroupName;
		this.zdId = zdId;
		this.status = status;
		this.type = type;
		this.createdAt = createdAt;
		this.lastModifyTime = lastModifyTime;
		this.cjsj = cjsj;
		this.xgsj = xgsj;
		this.account = account;
		this.avatar = avatar;
		this.uname = uname;
		this.sex = sex;
		this.dw_name = dw_name;
		this.ks_name = ks_name;
		this.age = age;
		this.job = job;
		this.skills = skills;
		this.scores = scores;
		this.abstracts = abstracts;
		this.lableNames = lableNames;
		this.docGroupName = docGroupName;
		this.docPs = docPs;
		this.yzInfo = yzInfo;
		this.videoTime = videoTime;
		this.talkTime = talkTime;
		this.isRead = isRead;
		this.duname = duname;
		this.zd = zd;
	}

	public PatDoc(int dId, int pId, String groupName) {
		super();
		this.dId = dId;
		this.pId = pId;
		this.groupName = groupName;
	}

	private String abstracts;// 简介
	private String lableNames;// 标签
	private String docGroupName;// 医生对病人的分组名称
	private String docPs;// 医生对病人的备注
	private String yzInfo;// 医生对病人的好友验证消息
	private String videoTime;// 视频时长
	private String talkTime;// 通话时长
	private int isRead;
	private String duname;// dID 对应医生姓名
	private String zd;//

	public int getIsRead() {
		return isRead;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
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

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDw_name() {
		return dw_name;
	}

	public void setDw_name(String dw_name) {
		this.dw_name = dw_name;
	}

	public String getKs_name() {
		return ks_name;
	}

	public void setKs_name(String ks_name) {
		this.ks_name = ks_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

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

	public String getLableNames() {
		return lableNames;
	}

	public void setLableNames(String lableNames) {
		this.lableNames = lableNames;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}

	public String getTalkTime() {
		return talkTime;
	}

	public void setTalkTime(String talkTime) {
		this.talkTime = talkTime;
	}

	public String getDuname() {
		return duname;
	}

	public void setDuname(String duname) {
		this.duname = duname;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

}
