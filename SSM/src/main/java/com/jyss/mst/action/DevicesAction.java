package com.jyss.mst.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.mst.entity.Devices;
import com.jyss.mst.entity.Page;
import com.jyss.mst.entity.ResponseEntity;
import com.jyss.mst.service.DevicesService;
import com.jyss.mst.utils.IpUtil;
import com.jyss.mst.utils.Utils;

@Controller
public class DevicesAction {

	@Autowired
	private DevicesService devService;

	@RequestMapping("/dev")
	public String devTz() {
		return "devices";
	}

	@RequestMapping("/devErr")
	public String devErrTz() {
		return "devicesError";
	}

	@RequestMapping("/getDevCl")
	@ResponseBody
	public List<Devices> getDevCl() {
		List<Devices> devClList = devService.getDevCl();
		return devClList;

	}

	@RequestMapping("/getDev")
	@ResponseBody
	public Page<Devices> getDev(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<Devices> devlist = devService.getDev();
		PageInfo<Devices> pageInfoDev = new PageInfo<Devices>(devlist);
		return new Page<Devices>(pageInfoDev);

	}

	@RequestMapping("/getDevBy")
	@ResponseBody
	public Page<Devices> getDevBy(@RequestParam("macId") String macId, @RequestParam("patID") String patID, @RequestParam("name") String name, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
	  {
	    PageHelper.startPage(page, rows);
	    List<Devices> devByList = this.devService.getDevBy(macId, patID, name);
		PageInfo<Devices> pageInfoDevBy = new PageInfo<Devices>(devByList);
		return new Page<Devices>(pageInfoDevBy);
	}

	@RequestMapping("/getDevErr")
	@ResponseBody
	public Page<Devices> getDevErr(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<Devices> devErrlist = devService.getDevErr();
		PageInfo<Devices> pageInfoDevErr = new PageInfo<Devices>(devErrlist);
		return new Page<Devices>(pageInfoDevErr);

	}

	@RequestMapping("/getDevErrBy")
	@ResponseBody
	public Page<Devices> getDevErrBy(@RequestParam("macId") String macId,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<Devices> devErrByList = devService.getDevErrBy(macId);
		PageInfo<Devices> pageInfoDevErrBy = new PageInfo<Devices>(devErrByList);
		return new Page<Devices>(pageInfoDevErrBy);
	}

	@RequestMapping("/pat/getDevCheck")
	@ResponseBody
	public Map<String, Object> getDevCheck(@RequestParam("macId") String macId) {
		// TODO Auto-generated method stub
		// String md5MacId = CommTool.getMyMac(macId.trim());
		// System.out.println("------------------>" + md5MacId);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Devices> devCheckList = devService.getDevBy(macId,"","");
		if (devCheckList != null && devCheckList.size() == 1) {
			map.put("status", "true");
			map.put("info", devCheckList.get(0));
			map.put("oldMac", macId);
		} else {
			map.put("status", "fasle");
			map.put("info", "验证错误，设备号不合法！");
		}
		return map;
	}

	@RequestMapping("/addDev")
	@ResponseBody
	public ResponseEntity addDev(Devices dev) {
		// TODO Auto-generated method stub
		int count = 0;
		if (dev.getId() == 0) {
			// 新增
			count = devService.addDev(dev);
		} else {
			// 修改
			count = devService.updateDev(dev);
		}

		if (count == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delDev")
	@ResponseBody
	public ResponseEntity deleteDev(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = devService.deleteDev(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/updateDevZt")
	@ResponseBody
	public ResponseEntity updateDevZt(@RequestParam("macIds") String macIds,
			@RequestParam("type") int type) {
		// TODO Auto-generated method stub
		int count = 0;
		Devices devc = new Devices();
		devc.setMacId(macIds);
		devc.setType(type);// 0可正常使用，未绑定，1已经绑定，2报修 3送维修 4报废
		count = devService.updateDevZt(devc);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/upDevIp")
	@ResponseBody
	public ResponseEntity upDevIp(Devices dev,
			@RequestParam("patIP") String patIP) {
		// TODO Auto-generated method stub
		int count = 0;
		// String macIp = InternetInfo.getLocalIP().get("IP");
		dev.setDlIp(patIP.toString());
		// 获取IP,计算地址
		if (dev.getDlIp().equals("") || dev.getDlIp() == null) {
			dev.setDlIp("0");
			dev.setDlAddr("");
		} else {
			dev.setDlAddr(IpUtil.getAddrByIp(dev.getDlIp()));
		}
		count = devService.upDevIp(dev);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

}
