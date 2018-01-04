package com.jyss.mst.entity.JsonEntity;

import java.util.ArrayList;
import java.util.List;

import com.jyss.mst.entity.PatXlFa;

public class XlfaJsonEntity {
	private String dId;
	private List<PatXlFa> jhList = new ArrayList<PatXlFa>();

	public String getdId() {
		return dId;
	}

	public void setdId(String dId) {
		this.dId = dId;
	}

	public List<PatXlFa> getJhList() {
		return jhList;
	}

	public void setJhList(List<PatXlFa> jhList) {
		this.jhList = jhList;
	}

	public XlfaJsonEntity(String dId, List<PatXlFa> jhList) {
		super();
		this.dId = dId;
		this.jhList = jhList;
	}

}
