package com.jyss.mst.service.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.mst.entity.Devices;
import com.jyss.mst.mapper.DevicesMapper;
import com.jyss.mst.service.DevicesService;
import com.jyss.mst.utils.CommTool;

@Service
public class DevicesServiceImpl implements DevicesService {

	@Autowired
	private DevicesMapper devMapper;

	public List<Devices> timeStampToString(List<Devices> declist) {
		for (Devices dev : declist) {
			try {
				dev.setBdTime(CommTool.DateGsh(dev.getBdTime()));
				dev.setCreatedAt(CommTool.DateGsh(dev.getCreatedAt()));
				dev.setLastLocateTime(CommTool.DateGsh(dev.getLastLocateTime()));
				dev.setLastLoginTime(CommTool.DateGsh(dev.getLastLoginTime()));
				dev.setLastModifyTime(CommTool.DateGsh(dev.getLastModifyTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return declist;
	}

	@Override
	public List<Devices> getDevCl() {
		// TODO Auto-generated method stub
		return devMapper.getDevCl();
	}

	@Override
	public List<Devices> getDev() {
		// TODO Auto-generated method stub
		return timeStampToString(devMapper.getDev());
	}

	@Override
	public List<Devices> getDevBy(String macId, String patID, String name) {
		// TODO Auto-generated method stub
		return timeStampToString(devMapper.getDevBy(macId,patID,name));
	}

	@Override
	public int addDev(Devices dev) {
		// TODO Auto-generated method stub
		dev.setStatus(1);
		dev.setType(0);
		dev.setCreatedAt(CommTool.getNowTimestamp());
		// 自定义我的设备号====10.25更改不用加密
		// dev.setMacId(CommTool.getMyMac(dev.getMacId()));
		return devMapper.addDev(dev);
	}

	@Override
	public int updateDev(Devices dev) {
		// TODO Auto-generated method stub
		// 自定义我的设备号====10.25更改不用加密
		// dev.setMacId(CommTool.getMyMac(dev.getMacId()));
		dev.setLastModifyTime(CommTool.getNowTimestamp());
		return devMapper.updateDev(dev);
	}

	@Override
	public int deleteDev(List<Long> ids) {
		// TODO Auto-generated method stub
		return devMapper.deleteDev(ids);
	}

	@Override
	public int batchJb(List<Long> ids) {
		// TODO Auto-generated method stub
		return devMapper.batchJb(ids);
	}

	@Override
	public int setBd(String type, String macId, String patId) {
		// TODO Auto-generated method stub
		return devMapper.setBd(type, macId, patId);
	}

	@Override
	public String getMac(int patId) {
		// TODO Auto-generated method stub
		return devMapper.getMac(patId);
	}

	@Override
	public int upDevIp(Devices dev) {
		dev.setLastLoginTime(CommTool.getNowTimestamp());
		return devMapper.upDevIp(dev);
	}

	@Override
	public List<Devices> getDevErr() {
		// TODO Auto-generated method stub
		return devMapper.getDevErr();
	}

	@Override
	public List<Devices> getDevErrBy(String macId) {
		// TODO Auto-generated method stub
		return devMapper.getDevErrBy(macId);
	}

	@Override
	public int updateDevZt(Devices dev) {
		// TODO Auto-generated method stub
		dev.setLastModifyTime(CommTool.getNowTimestamp());
		return devMapper.updateDevZt(dev);
	}

}
