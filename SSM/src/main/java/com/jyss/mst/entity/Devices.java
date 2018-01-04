package com.jyss.mst.entity;

import java.util.Date;

public class Devices {
	private int id;
	private String macId;
	private int type;
	private int status;
	private int patId;// 绑定病人
	private Date createdAt;// 创建时间
	private Date lastModifyTime;// 最新修改时间
	private String dlIp;// 登录IP
	private String dlAddr;// 登录地址
	private Date lastLoginTime;// 登陆时间
	private Date lastLocateTime;//
	private String cjsj;// 创建时间
	private String dlsj;// 登陆时间
	private String xgsj;// 最新修改时间
	// 视图字段
	private int age;
	private String sex;
	private String uname;
	private Date bdTime;// 病人绑定时间
	private String bdsj;// 最新绑定时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

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

	public int getPatId() {
		return patId;
	}

	public void setPatId(int patId) {
		this.patId = patId;
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

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastLocateTime() {
		return lastLocateTime;
	}

	public void setLastLocateTime(Date lastLocateTime) {
		this.lastLocateTime = lastLocateTime;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Date getBdTime() {
		return bdTime;
	}

	public void setBdTime(Date bdTime) {
		this.bdTime = bdTime;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public String getDlIp() {
		return dlIp;
	}

	public void setDlIp(String dlIp) {
		this.dlIp = dlIp;
	}

	public String getDlAddr() {
		return dlAddr;
	}

	public void setDlAddr(String dlAddr) {
		this.dlAddr = dlAddr;
	}

	public String getDlsj() {
		return dlsj;
	}

	public void setDlsj(String dlsj) {
		this.dlsj = dlsj;
	}

	public String getXgsj() {
		return xgsj;
	}

	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}

	public String getBdsj() {
		return bdsj;
	}

	public void setBdsj(String bdsj) {
		this.bdsj = bdsj;
	}

}
