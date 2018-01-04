package com.jyss.mst.entity.JsonEntity;

import java.util.Date;

public class MyDoc {
	private int id;// 主键
	private String account;// 登录账号手机号
	private String uname;// 姓名
	private String sex;// 性别
	private String dw_name;// 单位名称
	private String ks_name;// 科室名称
	private int age;// 年龄
	private String job;// 职务
	private String skills;// 擅长技能
	private String abstracts;// 简介
	private String avatar;// 头像路径
	private String nick;// 昵称
	private String comments;// 评价内容
	private String scores;// 评分星级
	private String lables;// 标签
	private String lableNames;// 标签
	private String emails;// 邮箱
	private Date created_at;// 创建时间
	private Date last_modify_time;// 最新修改时间
	// private Date last_login_time;//
	// private Date last_locate_time;//
	private int type;// 权限类型
	private int status;// 审核状态
	private int czr;// 操作人
	// 视图字段
	private String totalFw;// 总服务病人数
	private String nowFw;// 正在服务病人数

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public String getLables() {
		return lables;
	}

	public void setLables(String lables) {
		this.lables = lables;
	}

	public String getLableNames() {
		return lableNames;
	}

	public void setLableNames(String lableNames) {
		this.lableNames = lableNames;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getLast_modify_time() {
		return last_modify_time;
	}

	public void setLast_modify_time(Date last_modify_time) {
		this.last_modify_time = last_modify_time;
	}

	/*
	 * public Date getLast_login_time() { return last_login_time; }
	 * 
	 * public void setLast_login_time(Date last_login_time) {
	 * this.last_login_time = last_login_time; }
	 * 
	 * public Date getLast_locate_time() { return last_locate_time; }
	 * 
	 * public void setLast_locate_time(Date last_locate_time) {
	 * this.last_locate_time = last_locate_time; }
	 */

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCzr() {
		return czr;
	}

	public void setCzr(int czr) {
		this.czr = czr;
	}

	public String getTotalFw() {
		return totalFw;
	}

	public void setTotalFw(String totalFw) {
		this.totalFw = totalFw;
	}

	public String getNowFw() {
		return nowFw;
	}

	public void setNowFw(String nowFw) {
		this.nowFw = nowFw;
	}

	public MyDoc(int id, String account, String uname, String sex,
			String dw_name, String ks_name, int age, String job, String skills,
			String abstracts, String avatar, String nick, String comments,
			String scores, String lables, String lableNames, String emails,
			Date created_at, Date last_modify_time, int type, int status,
			int czr, String totalFw, String nowFw) {
		super();
		this.id = id;
		this.account = account;
		this.uname = uname;
		this.sex = sex;
		this.dw_name = dw_name;
		this.ks_name = ks_name;
		this.age = age;
		this.job = job;
		this.skills = skills;
		this.abstracts = abstracts;
		this.avatar = avatar;
		this.nick = nick;
		this.comments = comments;
		this.scores = scores;
		this.lables = lables;
		this.lableNames = lableNames;
		this.emails = emails;
		this.created_at = created_at;
		this.last_modify_time = last_modify_time;
		/*
		 * this.last_login_time = last_login_time; this.last_locate_time =
		 * last_locate_time;
		 */
		this.type = type;
		this.status = status;
		this.czr = czr;
		this.totalFw = totalFw;
		this.nowFw = nowFw;
	}

}
