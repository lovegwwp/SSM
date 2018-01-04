package com.jyss.mst.entity;

import java.util.Date;

public class Zd {
	private int id;
	private int dId;// 医生id
	private int pId;// 病人ID
	private int ksId;// 科室ID
	private String ksName;// 科室名称
	private String bq;// 病区
	private String jbqk;// 基本情况
	private String zd;// 诊断
	private int status;// 状态
	private Date createdAt;// 创建时间
	private Date lastModifyTime;// 最先修改时间
	private int dwId;// 单位ID
	private String dwName;// 单位名称
	private String uname;// 医生名称

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
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

	public int getKsId() {
		return ksId;
	}

	public void setKsId(int ksId) {
		this.ksId = ksId;
	}

	public String getKsName() {
		return ksName;
	}

	public void setKsName(String ksName) {
		this.ksName = ksName;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getDwId() {
		return dwId;
	}

	public void setDwId(int dwId) {
		this.dwId = dwId;
	}

	public String getDwName() {
		return dwName;
	}

	public void setDwName(String dwName) {
		this.dwName = dwName;
	}

}
