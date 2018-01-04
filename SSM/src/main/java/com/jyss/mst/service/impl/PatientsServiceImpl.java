package com.jyss.mst.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.mst.entity.Bl;
import com.jyss.mst.entity.PatSample;
import com.jyss.mst.entity.Patients;
import com.jyss.mst.entity.TjEntity;
import com.jyss.mst.entity.Zd;
import com.jyss.mst.mapper.DevicesMapper;
import com.jyss.mst.mapper.PatientsMapper;
import com.jyss.mst.mapper.XtclMapper;
import com.jyss.mst.service.PatientsService;
import com.jyss.mst.utils.CommTool;

@Service
public class PatientsServiceImpl implements PatientsService {

	@Autowired
	private PatientsMapper patMapper;
	@Autowired
	private DevicesMapper devMapper;
	@Autowired
	private XtclMapper xtclMapper;

	// /******************医生端****************************///
	@Override
	public List<Patients> getPatByDoc(String account, String uname, String sex,
			String age1, String age2, String addr) {
		// TODO Auto-generated method stub
		return patMapper.getPatByDoc(account, uname, sex, age1, age2, addr);
	}

	// /******************后台****************************///

	@Override
	public List<Patients> getPat() {
		// TODO Auto-generated method stub
		return patMapper.getPat();
	}

	@Override
	public List<Patients> getPatBy(String account, String uname ,String sex,  String province,  String city,  String area) {
		// TODO Auto-generated method stub
		return patMapper.getPatBy(account, uname,sex,  province,  city,  area);
	}

	public String getValue(String bz_type, String id) {
		if (xtclMapper.getClsValue(bz_type, id) != null) {
			String nameVal = xtclMapper.getClsValue(bz_type, id).getBz_value();
			if (nameVal.isEmpty() || nameVal.equals("")) {
				return id;
			}
			return nameVal;
		} else {
			return id;
		}

	}

	/**
	 * 新增修改统一处理
	 * 
	 * @param pat
	 * @return
	 */
	public Patients patCl(Patients pat) {
		pat.setNick(pat.getUname());
		pat.setProvince(getValue("pro_type",
				String.valueOf(pat.getProvince_id())));
		pat.setCity(getValue("city_type", String.valueOf(pat.getCity_id())));
		pat.setArea(getValue("area_type", String.valueOf(pat.getArea_id())));
		pat.setKsName((getValue("ks_type", String.valueOf(pat.getKsId()))));
		pat.setDwName((getValue("dw_type", String.valueOf(pat.getDwId()))));
		return pat;
	}

	@Override
	public int addPat(Patients pat) {
		// TODO Auto-generated method stub
		pat.setUuid(CommTool.getUUID());
		pat.setStatus(1);// 正常状态
		pat.setAvatar("uploadImg/pat.png");
		pat.setCreatedAt(CommTool.getNowTimestamp());
		return patMapper.addPat(patCl(pat));
	}

	@Override
	public int updatePat(Patients pat) {
		// TODO Auto-generated method stub
		pat.setLastModifyTime(CommTool.getNowTimestamp());
		return patMapper.updatePat(patCl(pat));
	}

	@Override
	public int deletePat(List<Long> ids) {
		// TODO Auto-generated method stub
		return patMapper.deletePat(ids);
	}

	@Override
	public Patients getMe(String account) {
		// TODO Auto-generated method stub
		return patMapper.getMe(account);
	}

	@Override
	public int addZd(Zd zd) {
		// TODO Auto-generated method stub
		zd.setStatus(1);
		zd.setCreatedAt(CommTool.getNowTimestamp());
		zd.setLastModifyTime(CommTool.getNowTimestamp());
		return patMapper.addZd(zd);
	}

	@Override
	public int addBl(Bl bl) {
		// TODO Auto-generated method stub
		bl.setStatus(1);
		bl.setCreatedAt(CommTool.getNowTimestamp());
		bl.setLastModifyTime(CommTool.getNowTimestamp());
		return patMapper.addBl(bl);
	}

	@Override
	public int upZd(Zd zd) {
		// TODO Auto-generated method stub
		zd.setLastModifyTime(CommTool.getNowTimestamp());
		return patMapper.upZd(zd);
	}

	@Override
	public int upBl(Bl bl) {
		// TODO Auto-generated method stub
		bl.setLastModifyTime(CommTool.getNowTimestamp());
		return patMapper.upBl(bl);
	}

	@Override
	public int upPatZdId(int zdId, int id) {
		// TODO Auto-generated method stub
		return patMapper.upPatZdId(zdId, id);
	}

	@Override
	public int upVedioTime(int videoTime, int id) {
		// TODO Auto-generated method stub
		return patMapper.upVedioTime(videoTime, id);
	}

	@Override
	public int upTalkTime(int talkTime, int id) {
		// TODO Auto-generated method stub
		return patMapper.upTalkTime(talkTime, id);
	}

	/**
	 * 医生端 获取个体病人
	 * 
	 * @param pId
	 * @return
	 */

	@Override
	public List<PatSample> getPatSample(String pId) {
		// TODO Auto-generated method stub
		return patMapper.getPatSample(pId);
	}

	@Override
	public int addZdContent(Zd zd) {
		// TODO Auto-generated method stub
		return patMapper.addZdContent(zd);
	}

	@Override
	public int upZdContent(Zd zd) {
		// TODO Auto-generated method stub
		zd.setLastModifyTime(CommTool.getNowTimestamp());
		return patMapper.upZdContent(zd);
	}

	// 病人修改个人地址
	@Override
	public int upPatbyPat(Patients pat) {
		// TODO Auto-generated method stub
		pat.setLastModifyTime(CommTool.getNowTimestamp());
		return patMapper.upPatbyPat(pat);
	}

	@Override
	public int upVedioTimeByCz(long videoTime, String account) {
		// TODO Auto-generated method stub
		return patMapper.upVedioTimeByCz(videoTime, account);
	}

	@Override
	public int upTalkTimeByCz(long talkTime, String account) {
		// TODO Auto-generated method stub
		return patMapper.upTalkTimeByCz(talkTime, account);
	}

	@Override
	public List<Zd> getZdList(String pId) {
		// TODO Auto-generated method stub
		return patMapper.getZdList(pId);
	}

	// /******************后台********统计********************///

	@Override
	public List<TjEntity> getPatAddrTj(String province, String city) {
		// TODO Auto-generated method stub
		return patMapper.getPatAddrTj(province, city);
	}

	@Override
	public List<TjEntity> getPatOnTj(String province, String city, String dlsjxz) {
		// TODO Auto-generated method stub
		return patMapper.getPatOnTj(province, city, dlsjxz);
	}

	@Override
	public List<TjEntity> getPatDwTj(String dw) {
		// TODO Auto-generated method stub
		return patMapper.getPatDwTj(dw);
	}

}
