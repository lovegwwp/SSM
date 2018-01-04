package com.jyss.mst.entity;

import java.util.Date;
import java.util.List;

public class Patients {
	private int id;// 主键
	private String account;// 登录账号-设备号
	private String uname;// 姓名
	private String sex;// 性别
	private int age;// 年龄
	private String uuid;//
	private String avatar;// 头像路径
	private String nick;// 昵称
	private int province_id;// 省份
	private String province;//
	private int city_id;// 城市
	private String city;//
	private int area_id;// 区域
	private String area;//
	private String addr;//
	private int talkTime;// 通话时长
	private int videoTime;// 视频时长
	private Date createdAt;// 创建时间
	private Date lastModifyTime;// 最新修改时间
	private String cjsj;// 创建时间
	private String xgsj;// 最新修改时间
	private Date lastLoginTime;//
	private Date lastLocateTime;//
	private int status;// 审核状态
	private int blId;// 病例ID
	private int zdId;// 诊断ID
	private int bId;// 病例宁=病例
	// /多余业务字段///
	// 病例
	private String ls;// 利手
	private String hc;// 患侧
	private String cxbw;// 出血部位
	private String cxdx;// 出血大小
	// 诊断
	private int dwId;// 单位ID
	private String dwName;// 单位名称
	private int ksId;// 科室ID
	private String ksName;// 科室名称
	private String bq;// 病区
	private String jbqk;// 基本情况
	private String zd;// 诊断

	private List<Zd> zdList;// 诊断list

	public List<Zd> getZdList() {
		return zdList;
	}

	public void setZdList(List<Zd> zdList) {
		this.zdList = zdList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public int getProvince_id() {
		return province_id;
	}

	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getArea_id() {
		return area_id;
	}

	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getTalkTime() {
		return talkTime;
	}

	public void setTalkTime(int talkTime) {
		this.talkTime = talkTime;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(int videoTime) {
		this.videoTime = videoTime;
	}

	// //多余业务字段//////
	public int getBlId() {
		return blId;
	}

	public void setBlId(int blId) {
		this.blId = blId;
	}

	public int getZdId() {
		return zdId;
	}

	public void setZdId(int zdId) {
		this.zdId = zdId;
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

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

}
