package com.jyss.mst.entity;

public class PatSample {
	private int id;// 主键
	private String account;// 登录账号-设备号
	private String uname;// 姓名
	private String sex;// 性别
	private int age;// 年龄
	private String avatar;// 头像路径
	private String nick;// 昵称
	private String addr;//
	private int status;// 审核状态
	private int zdId;// 诊断ID
	private String ys;// 医生姓名
	private int dId;// 医生id
	private String bq;// 病区
	private String jbqk;// 基本情况
	private String zd;// 诊断

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getZdId() {
		return zdId;
	}

	public void setZdId(int zdId) {
		this.zdId = zdId;
	}

	public String getYs() {
		return ys;
	}

	public void setYs(String ys) {
		this.ys = ys;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	public String getBq() {
		return bq;
	}

	public void setBq(String bq) {
		this.bq = bq;
	}

	public String getJbqk() {
		return jbqk;
	}

	public void setJbqk(String jbqk) {
		this.jbqk = jbqk;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

}
