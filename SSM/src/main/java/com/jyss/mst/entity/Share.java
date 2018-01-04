package com.jyss.mst.entity;

import java.util.Date;

public class Share {
	private int id;
	private int dId;// 医生ID
	private int docId;// '医生分享医生时 type=2',
	private int pId;// 医生分享病人时 type=1
	private int newsId;// 医生诊断详情ID',
	private int type;// 1=分享给病人 2=分享给医生',
	private Date createdAt;//
	private Date lastModifyTime;//
	private int isRead;// 1=已读 0=未读',
	private String cjsj;// 创建时间
	private String xgsj;// 最新修改时间

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

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
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

	public int getIsRead() {
		return isRead;
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

}
