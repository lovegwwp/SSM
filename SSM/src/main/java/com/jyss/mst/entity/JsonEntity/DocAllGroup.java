package com.jyss.mst.entity.JsonEntity;

import java.util.List;

import com.jyss.mst.entity.Doctors;
import com.jyss.mst.entity.News;
import com.jyss.mst.entity.ShareNews;

public class DocAllGroup {
	private String dId;// 当前医生ID
	private List<PatGroup> patGroupList;// 病人好友列表
	private List<DocGroup> docGroupList;// 医生好友列表
	private Doctors doc;// 医生好友列表
	private List<News> newsList;// 新闻列表
	private List<ShareNews> fxNewsList;// 分享新闻列表

	public String getdId() {
		return dId;
	}

	public void setdId(String dId) {
		this.dId = dId;
	}

	public List<PatGroup> getPatGroupList() {
		return patGroupList;
	}

	public void setPatGroupList(List<PatGroup> patGroupList) {
		this.patGroupList = patGroupList;
	}

	public List<DocGroup> getDocGroupList() {
		return docGroupList;
	}

	public void setDocGroupList(List<DocGroup> docGroupList) {
		this.docGroupList = docGroupList;
	}

	public Doctors getDoc() {
		return doc;
	}

	public void setDoc(Doctors doc) {
		this.doc = doc;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public List<ShareNews> getFxNewsList() {
		return fxNewsList;
	}

	public void setFxNewsList(List<ShareNews> fxNewsList) {
		this.fxNewsList = fxNewsList;
	}

	public DocAllGroup(String dId, List<PatGroup> patGroupList,
			List<DocGroup> docGroupList, Doctors doc, List<News> newsList,
			List<ShareNews> fxNewsList) {
		super();
		this.dId = dId;
		this.patGroupList = patGroupList;
		this.docGroupList = docGroupList;
		this.doc = doc;
		this.newsList = newsList;
		this.fxNewsList = fxNewsList;
	}

}
