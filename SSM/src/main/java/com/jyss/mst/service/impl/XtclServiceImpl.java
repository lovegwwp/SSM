package com.jyss.mst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.mst.entity.Xtcl;
import com.jyss.mst.mapper.XtclMapper;
import com.jyss.mst.service.XtclService;

@Service
public class XtclServiceImpl implements XtclService {

	@Autowired
	private XtclMapper xtclMapper;

	@Override
	public List<Xtcl> getCls() {
		// TODO Auto-generated method stub
		return xtclMapper.getCls();
	}

	@Override
	public List<Xtcl> getClsBy(String bz_type, String bz_value,String ps) {
		// TODO Auto-generated method stub
		return xtclMapper.getClsBy(bz_type, bz_value, ps);
	}

	@Override
	public int addCl(Xtcl cl) {
		// TODO Auto-generated method stub
		return xtclMapper.addCl(cl);
	}

	@Override
	public int updateCl(Xtcl cl) {
		// TODO Auto-generated method stub
		return xtclMapper.updateCl(cl);
	}

	@Override
	public int deleteCl(List<Long> ids) {
		// TODO Auto-generated method stub
		return xtclMapper.deleteCl(ids);
	}

	@Override
	public List<Xtcl> getClsCombox(String bz_type, String pid) {
		// TODO Auto-generated method stub
		return xtclMapper.getClsCombox(bz_type, pid);
	}

	@Override
	public Xtcl getClsValue(String bz_type, String bz_id) {
		// TODO Auto-generated method stub
		return xtclMapper.getClsValue(bz_type, bz_id);
	}

}
