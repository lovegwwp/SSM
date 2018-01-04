package com.jyss.mst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.mst.entity.Xtgx;
import com.jyss.mst.entity.Xtnews;
import com.jyss.mst.mapper.XtNewsMapper;
import com.jyss.mst.service.XtNewsService;

@Service
public class XtNewsServiceImpl implements XtNewsService {
	@Autowired
	private XtNewsMapper xtMapper;

	@Override
	public List<Xtnews> getPatXtNews() {
		// TODO Auto-generated method stub
		return xtMapper.getPatXtNews();
	}

	@Override
	public List<Xtgx> getPatXtgx() {
		// TODO Auto-generated method stub
		return xtMapper.getPatXtgx();
	}

	@Override
	public List<Xtnews> getXtNews(String keytype) {
		// TODO Auto-generated method stub
		return xtMapper.getXtNews(keytype);
	}

	@Override
	public List<Xtnews> getXtNewsBy(String title, String keytype) {
		// TODO Auto-generated method stub
		return xtMapper.getXtNewsBy(title, keytype);
	}

	@Override
	public int addXtNews(Xtnews paramXtnews) {
		// TODO Auto-generated method stub
		return xtMapper.addXtNews(paramXtnews);
	}

	@Override
	public int updateXtNews(Xtnews paramXtnews) {
		// TODO Auto-generated method stub
		return xtMapper.updateXtNews(paramXtnews);
	}

	@Override
	public int deleteXtNews(List<Long> ids) {
		// TODO Auto-generated method stub
		return xtMapper.deleteXtNews(ids);
	}

	@Override
	public List<Xtgx> getXtgx() {
		// TODO Auto-generated method stub
		return xtMapper.getXtgx();
	}

	@Override
	public List<Xtgx> getXtgxBy(String title) {
		// TODO Auto-generated method stub
		return xtMapper.getXtgxBy(title);
	}

	@Override
	public int addXtgx(Xtgx paramXtgx) {
		// TODO Auto-generated method stub
		return xtMapper.addXtgx(paramXtgx);
	}

	@Override
	public int updateXtgx(Xtgx paramXtgx) {
		// TODO Auto-generated method stub
		return xtMapper.updateXtgx(paramXtgx);
	}

	@Override
	public int deleteXtgx(List<Long> ids) {
		// TODO Auto-generated method stub
		return xtMapper.deleteXtgx(ids);
	}

	

}
