package com.jyss.mst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.mst.entity.PatXlFa;
import com.jyss.mst.entity.Xljh;
import com.jyss.mst.entity.JsonEntity.ExeMakeIf;
import com.jyss.mst.mapper.XljhMapper;
import com.jyss.mst.service.XljhService;
import com.jyss.mst.utils.CommTool;

@Service
public class XljhServiceImpl implements XljhService {
	@Autowired
	XljhMapper jhMapper;

	// /=====**************病人端*********************====///

	// /=====***************医生端*********************====///

	// 医生端---获取模板训练计划
	@Override
	public List<PatXlFa> getPatJh(String pID, String dID) {
		// TODO Auto-generated method stub
		return jhMapper.getPatJh(pID, dID);
	}

	@Override
	public int scJh(Xljh jh) {
		// TODO Auto-generated method stub
		// jh.setXlmb(0);// 未发送病人 未激活状态
		jh.setVedioIds("0");
		jh.setVedioAccount("0");
		jh.setCreatedAt(CommTool.getNowTimestamp());
		return jhMapper.scJh(jh);
	}

	@Override
	public int saveMb(Xljh jh) {
		// TODO Auto-generated method stub
		return jhMapper.saveMb(jh);
	}

	// 医生端---删除未激活计划
	@Override
	public int delMoreJh(Xljh jh) {
		// TODO Auto-generated method stub
		return jhMapper.delMoreJh(jh);
	}

	// 医生端---激活计划
	@Override
	public int activeJh(Xljh jh) {
		// TODO Auto-generated method stub
		jh.setXlmb(1);// 激活
		return jhMapper.activeJh(jh);
	}

	// 获取 模板计划列表
	@Override
	public List<Xljh> getMbJh(String type, String dID) {
		// TODO Auto-generated method stub
		return jhMapper.getMbJh(type, dID);
	}

	// 获取自定义计划列表
	@Override
	public List<Xljh> getScJh(String type, String dID, String pID) {
		// TODO Auto-generated method stub
		return jhMapper.getScJh(type, dID, pID);
	}

	// 复制模板计划
	@Override
	public int copyMbJh(Xljh jh) {
		// TODO Auto-generated method stub
		return jhMapper.copyMbJh(jh);
	}

	// 删除多余模板计划
	@Override
	public int delMoreMbJh(Xljh jh) {
		// TODO Auto-generated method stub
		return jhMapper.delMoreMbJh(jh);
	}

	// 回置计划视频数
	@Override
	public int setJhAccount(Xljh jh) {
		// TODO Auto-generated method stub
		return jhMapper.setJhAccount(jh);
	}

	// 删除 整个方案计划
	@Override
	public int delFaJh(String dID, String pID, String type) {
		// TODO Auto-generated method stub
		return jhMapper.delFaJh(dID, pID, type);
	}

	@Override
	public int isHasYw(int dId) {
		// TODO Auto-generated method stub
		return jhMapper.isHasYw(dId);
	}

	@Override
	public List<Xljh> getJhForBj(String type, String dID, String pID) {
		// TODO Auto-generated method stub
		return jhMapper.getJhForBj(type, dID, pID);
	}

	@Override
	public List<Xljh> getJhForJson(String xlmb, String type, String dID,
			String pID) {
		// TODO Auto-generated method stub
		return jhMapper.getJhForJson(xlmb, type, dID, pID);
	}

	@Override
	public List<Xljh> getTypeForJson(String xlmb, String dID, String pID) {
		// TODO Auto-generated method stub
		return jhMapper.getTypeForJson(xlmb, dID, pID);
	}

	@Override
	public ExeMakeIf getTotalJhForJson(String xlmb, String type, String dID,
			String pID) {
		// TODO Auto-generated method stub
		return jhMapper.getTotalJhForJson(xlmb, type, dID, pID);
	}

	@Override
	public int replaceJhType(Xljh jh) {
		// TODO Auto-generated method stub
		return jhMapper.replaceJhType(jh);
	}

	@Override
	public List<PatXlFa> getPatJhDID(String pID) {
		// TODO Auto-generated method stub
		return jhMapper.getPatJhDID(pID);
	}

}
