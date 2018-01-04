package com.jyss.mst.entity;

public class ShareNews {
	private int sid;
	private int dId;
	private int pId;
	private int docId;
	private int NewsId;
	private String fxsj;
	private int ysType;
	private int isRead;
	private String account;
	private String dwName;
	private String uname;
	private String avatar;
	private int id;
	private String titles;
	private String pics;
	private String contents;
	private int type;
	private long sjc;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getdId() {
		return dId;
	}

	public long getSjc() {
		return sjc;
	}

	public void setSjc(long sjc) {
		this.sjc = sjc;
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

	public int getNewsId() {
		return NewsId;
	}

	public void setNewsId(int newsId) {
		NewsId = newsId;
	}

	public String getFxsj() {
		return fxsj;
	}

	public void setFxsj(String fxsj) {
		this.fxsj = fxsj;
	}

	public int getYsType() {
		return ysType;
	}

	public void setYsType(int ysType) {
		this.ysType = ysType;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDwName() {
		return dwName;
	}

	public void setDwName(String dwName) {
		this.dwName = dwName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
