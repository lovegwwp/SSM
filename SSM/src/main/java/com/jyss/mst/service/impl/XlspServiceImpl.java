package com.jyss.mst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.mst.entity.Xlsp;
import com.jyss.mst.mapper.XlspMapper;
import com.jyss.mst.service.XlspService;
import com.jyss.mst.utils.CommTool;

@Service
public class XlspServiceImpl implements XlspService {

	@Autowired
	private XlspMapper spMapper;

	/**
	 * 医生端 新增训练计划视频
	 */
	@Override
	public int addSp(Xlsp sp) {
		// TODO Auto-generated method stub
		sp.setCreatedAt(CommTool.getNowTimestamp());
		// sp.setStatus(0);// 未发送病人 未激活状态
		sp.setXlPercent("0");
		sp.setWatchTime("0");
		sp.setVedioTime("0");
		sp.setScores("0");
		sp.setComments("0");
		sp.setVedioType("3");// 默认 口型+动作
		return spMapper.addSp(sp);
	}

	@Override
	public List<Xlsp> getBjSp(String week, String day, String time, String dID,
			String pID, String type) {
		// TODO Auto-generated method stub
		return spMapper.getBjSp(week, day, time, dID, pID, type);
	}

	@Override
	public int deleteSp(List<Long> ids) {
		// TODO Auto-generated method stub
		return spMapper.deleteSp(ids);
	}

	// 医生端 删除未激活视频
	@Override
	public int delMoreSp(Xlsp sp) {
		// TODO Auto-generated method stub
		return spMapper.delMoreSp(sp);
	}

	// 医生端激活视频
	@Override
	public int activeSp(Xlsp sp) {
		// TODO Auto-generated method stub
		sp.setStatus(1);
		return spMapper.activeSp(sp);
	}

	// 复制模板视频
	@Override
	public int copyMbSp(Xlsp sp) {
		// TODO Auto-generated method stub
		sp.setStatus(0);// 未发送状态
		return spMapper.copyMbSp(sp);
	}

	// 删除 模板视频
	@Override
	public int delMbSp(Xlsp sp) {
		// TODO Auto-generated method stub
		return spMapper.delMbSp(sp);
	}

	@Override
	public int saveMbSp(Xlsp sp) {
		// TODO Auto-generated method stub
		return spMapper.saveMbSp(sp);
	}

	@Override
	public int delFaSp(String dID, String pID, String type) {
		// TODO Auto-generated method stub
		return spMapper.delFaSp(dID, pID, type);
	}

	// /=====**************病人端*********************====///

	// 病人训练计划视频
	@Override
	public List<Xlsp> getXlSpByPat(String week, String day, String time,
			String dID, String pID, String type, String status) {
		// TODO Auto-generated method stub
		return spMapper.getXlSpByPat(week, day, time, dID, pID, type, status);
	}

	@Override
	public int computePercent(Xlsp sp) {
		// TODO Auto-generated method stub
		sp.setLastModifyTime(CommTool.getNowTimestamp());
		return spMapper.computePercent(sp);
	}

	@Override
	public int replaceSpType(Xlsp sp) {
		// TODO Auto-generated method stub
		return spMapper.replaceSpType(sp);
	}

	@Override
	public int upPcComment(Xlsp sp) {
		// TODO Auto-generated method stub
		sp.setLastPcTime(CommTool.getNowTimestamp());
		return spMapper.upPcComment(sp);
	}

	@Override
	public List<Xlsp> getPcByPat(String week, String day, String time,
			String dID, String pID, String type, String status) {
		// TODO Auto-generated method stub
		return spMapper.getPcByPat(week, day, time, dID, pID, type, status);
	}

	@Override
	public List<Xlsp> getPatSpLoad(String pID, long sjc) {
		// TODO Auto-generated method stub
		return spMapper.getPatSpLoad(pID, sjc);
	}

	@Override
	public List<Xlsp> getPatLoadHistory(String pID, long sjc) {
		// TODO Auto-generated method stub
		return spMapper.getPatLoadHistory(pID, sjc);
	}

	@Override
	public List<Xlsp> getPatXlType(String pID, String dID) {
		// TODO Auto-generated method stub
		return spMapper.getPatXlType(pID, dID);
	}

	@Override
	public Xlsp getJsonXlPercent(String pID, String dID, String type) {

		return spMapper.getJsonXlPercent(pID, dID, type);

	}

	@Override
	public List<Xlsp> getPcTitles(String id) {
		// TODO Auto-generated method stub
		return spMapper.getPcTitles(id);
	}
}
