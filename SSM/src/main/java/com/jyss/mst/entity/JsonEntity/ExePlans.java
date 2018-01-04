package com.jyss.mst.entity.JsonEntity;

public class ExePlans {

	private String name;// title
	// private String vedio_type;// 视频类型
	private String[] content;// 路径内容
	private String exeId;

	public ExePlans() {
		super();
	}

	public ExePlans(String name, String[] content, String exeId) {
		super();
		this.name = name;
		this.content = content;
		this.exeId = exeId;
	}

	public String getExeId() {
		return exeId;
	}

	public void setExeId(String exeId) {
		this.exeId = exeId;
	}

	public ExePlans(String name, String[] content) {
		super();
		this.name = name;
		this.content = content;
	}

	/*
	 * public String getVedio_type() { return vedio_type; }
	 * 
	 * public void setVedio_type(String vedio_type) { this.vedio_type =
	 * vedio_type; }
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getContent() {
		return content;
	}

	public void setContent(String[] content) {
		this.content = content;
	}

}
