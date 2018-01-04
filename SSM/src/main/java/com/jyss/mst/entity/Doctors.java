package com.jyss.mst.entity;

import java.util.Date;
import java.util.List;

public class Doctors {
	private int id;// 主键
	private String account;// 登录账号手机号
	private String uname;// 姓名
	private String sex;// 性别
	private int dw_id;// 单位id
	private String dw_name;// 单位名称
	private int ks_id;// 科室id
	private String ks_name;// 科室名称
	private int age;// 年龄
	private String job;// 职务
	private String skills;// 擅长技能
	private String abstracts;// 简介
	private String salt;//
	private String password;//
	private String uuid;//
	private String token;//
	private String avatar;// 头像路径
	private String nick;// 昵称
	private int province_id;// 省份
	private String province;//
	private int city_id;// 城市
	private String city;//
	private int area_id;// 区域
	private String area;//
	private String comments;// 评价内容
	private int scores;// 评分星级
	private String lables;// 标签
	private String lableNames;// 标签
	private String emails;// 邮箱
	private Date created_at;// 创建时间
	private Date last_modify_time;// 最新修改时间
	private Date last_login_time;//
	private Date last_locate_time;//
	private String cjsj;// 创建时间
	private String xgsj;// 最新修改时间
	private int type;// 权限类型
	private int status;// 审核状态
	private int czr;// 操作人
	private List<String> strIds;
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

	public int getDw_id() {
		return dw_id;
	}

	public void setDw_id(int dw_id) {
		this.dw_id = dw_id;
	}

	public String getDw_name() {
		return dw_name;
	}

	public void setDw_name(String dw_name) {
		this.dw_name = dw_name;
	}

	public int getKs_id() {
		return ks_id;
	}

	public void setKs_id(int ks_id) {
		this.ks_id = ks_id;
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

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
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

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public Date getLast_locate_time() {
		return last_locate_time;
	}

	public void setLast_locate_time(Date last_locate_time) {
		this.last_locate_time = last_locate_time;
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

	public List<String> getStrIds() {
		return strIds;
	}

	public void setStrIds(List<String> strIds) {
		this.strIds = strIds;
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

	public int getCzr() {
		return czr;
	}

	public void setCzr(int czr) {
		this.czr = czr;
	}

}
