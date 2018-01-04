package com.jyss.mst.entity.JsonEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MyPat {
	private String pId;
	private String docGroupName;
	private String status;
	private String account;
	private String uname;
	private String sex;
	private String age;
	private String avatar;
	private String nick;
	private String zdId;
	private String zd;
	private String dId;
	private String duname;
	private String type;
	private String xlPercent;
	private Date wcsj;
	private List<Map<String, String>> xlList;

	public List<Map<String, String>> getXlList() {
		return xlList;
	}

	public void setXlList(List<Map<String, String>> xlList) {
		this.xlList = xlList;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getDocGroupName() {
		return docGroupName;
	}

	public void setDocGroupName(String docGroupName) {
		this.docGroupName = docGroupName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
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

	public String getZdId() {
		return zdId;
	}

	public void setZdId(String zdId) {
		this.zdId = zdId;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getdId() {
		return dId;
	}

	public void setdId(String dId) {
		this.dId = dId;
	}

	public String getDuname() {
		return duname;
	}

	public void setDuname(String duname) {
		this.duname = duname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getXlPercent() {
		return xlPercent;
	}

	public void setXlPercent(String xlPercent) {
		this.xlPercent = xlPercent;
	}

	public Date getWcsj() {
		return wcsj;
	}

	public void setWcsj(Date wcsj) {
		this.wcsj = wcsj;
	}

	public MyPat(String pId, String docGroupName, String status,
			String account, String uname, String sex, String age,
			String avatar, String nick, String zdId, String zd, String dId,
			String duname, String type, String xlPercent, Date wcsj,
			List<Map<String, String>> xlList) {
		super();
		this.pId = pId;
		this.docGroupName = docGroupName;
		this.status = status;
		this.account = account;
		this.uname = uname;
		this.sex = sex;
		this.age = age;
		this.avatar = avatar;
		this.nick = nick;
		this.zdId = zdId;
		this.zd = zd;
		this.dId = dId;
		this.duname = duname;
		this.type = type;
		this.xlPercent = xlPercent;
		this.wcsj = wcsj;
		this.xlList = xlList;
	}

	public MyPat(String pId, String docGroupName, String status,
			String account, String uname, String sex, String age,
			String avatar, String nick, String zdId, String zd, String dId,
			String duname, String type, String xlPercent, Date wcsj) {
		super();
		this.pId = pId;
		this.docGroupName = docGroupName;
		this.status = status;
		this.account = account;
		this.uname = uname;
		this.sex = sex;
		this.age = age;
		this.avatar = avatar;
		this.nick = nick;
		this.zdId = zdId;
		this.zd = zd;
		this.dId = dId;
		this.duname = duname;
		this.type = type;
		this.xlPercent = xlPercent;
		this.wcsj = wcsj;

	}

}
