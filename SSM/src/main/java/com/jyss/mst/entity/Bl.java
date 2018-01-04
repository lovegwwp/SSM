package com.jyss.mst.entity;

import java.util.Date;

public class Bl {
	private int id;
	private int dId;// 医生id
	private int pId;// 病人ID
	private String ls;// 利手
	private String hc;// 患侧
	private String cxbw;// 出血部位
	private String cxdx;// 出血大小
	private int status;// 状态
	private Date createdAt;// 创建时间
	private Date lastModifyTime;// 最先修改时间

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

	public String getLs() {
		return ls;
	}

	public void setLs(String ls) {
		this.ls = ls;
	}

	public String getHc() {
		return hc;
	}

	public void setHc(String hc) {
		this.hc = hc;
	}

	public String getCxbw() {
		return cxbw;
	}

	public void setCxbw(String cxbw) {
		this.cxbw = cxbw;
	}

	public String getCxdx() {
		return cxdx;
	}

	public void setCxdx(String cxdx) {
		this.cxdx = cxdx;
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

}
