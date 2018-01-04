package com.jyss.mst.entity;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Xlxm {

	private int id;
	private String titles;
	private String vedio;
	private long byteSize;// 文件大小
	private MultipartFile myFile;// 文件传输
	private String type; // 项目类型 上肢下肢,
	private String typeName; // 项目类型名字
	private String itemid;
	private int vedioType; // 视频类型 1 口型2动作 3口型+动作'
	private Date createdAt;// 创建时间
	private Date lastModifyTime;// 最新修改时间
	private String cjsj;// 创建时间
	private String xgsj;// 最新修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitles() {
		return titles;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public String getVedio() {
		return vedio;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public void setVedio(String vedio) {
		this.vedio = vedio;
	}

	public long getByteSize() {
		return byteSize;
	}

	public void setByteSize(long byteSize) {
		this.byteSize = byteSize;
	}

	public MultipartFile getMyFile() {
		return myFile;
	}

	public void setMyFile(MultipartFile myFile) {
		this.myFile = myFile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getVedioType() {
		return vedioType;
	}

	public void setVedioType(int vedioType) {
		this.vedioType = vedioType;
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
