package com.jyss.mst.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyss.mst.entity.ResponseEntity;
import com.jyss.mst.entity.Xljh;
import com.jyss.mst.entity.Xlsp;
import com.jyss.mst.service.PatientsService;
import com.jyss.mst.service.XljhService;
import com.jyss.mst.service.XlspService;
import com.jyss.mst.service.XlxmService;
import com.jyss.mst.utils.Utils;

@Controller
public class XlspAction {
	@Autowired
	private XlspService spService;
	@Autowired
	private XljhService jhService;
	@Autowired
	private XlxmService xmService;
	@Autowired
	private PatientsService patService;

	// ***************病人端 ****************//

	// 获取编辑视频列表
	/*
	 * @RequestMapping("/pat/getPatSp")
	 * 
	 * @ResponseBody public List<Xlsp> getXlSpByPat(
	 * 
	 * @RequestParam(value = "week", required = true) String week,
	 * 
	 * @RequestParam(value = "day", required = true) String day,
	 * 
	 * @RequestParam(value = "time", required = true) String time,
	 * 
	 * @RequestParam(value = "dID", required = true) String dID,
	 * 
	 * @RequestParam(value = "pID", required = true) String pID,
	 * 
	 * @RequestParam(value = "type", required = true) String type) { // TODO
	 * Auto-generated method stub List<Xlsp> patXlSpList =
	 * spService.getXlSpByPat(week, day, time, dID, pID, type); // 格式化输出 return
	 * MsgUtil.formatList(patXlSpList); }
	 */

	// 获取训练视频下载路径
	@RequestMapping("/pat/getPatLoad")
	@ResponseBody
	public List<Xlsp> getPatLoad(
			@RequestParam(value = "pID", required = true) String pID,
			@RequestParam(value = "sjc", required = true) long sjc) {
		// TODO Auto-generated method stub\
		List<Xlsp> patXlSpList = new ArrayList<Xlsp>();
		patXlSpList = spService.getPatSpLoad(pID, sjc);
		Xlsp sp = null;
		for (int i = 0; i < patXlSpList.size(); i++) {
			sp = patXlSpList.get(i);
			if (sp.getVedio() == null || sp.getVedio().trim().equals("")
					|| sp.getVedio().trim().equals("0")
					|| sp.getVedio().contains(".exe")) {
				patXlSpList.remove(i);
			}
		}
		return patXlSpList;
	}

	// 获取下载记录
	@RequestMapping("/pat/getPatLoadHistory")
	@ResponseBody
	public List<Xlsp> getPatLoadHistory(
			@RequestParam(value = "pID", required = true) String pID,
			@RequestParam(value = "sjc", required = true) long sjc) {
		// TODO Auto-generated method stub
		List<Xlsp> patXlSpList = spService.getPatLoadHistory(pID, sjc);
		return patXlSpList;
	}

	// 计算视频百分比 以及病人时长
	@RequestMapping("/pat/computePercent")
	@ResponseBody
	public Map<String, String> computePercent(Xlsp sp) {
		Map<String, String> m = new HashMap<String, String>();
		int count = 0;
		int spLen = sp.getSpLen();
		int bfTime = sp.getBfTime();
		int watchTime = Integer.parseInt(sp.getWatchTime());// 观看总时长
		int pId = sp.getpID();// 当前病人
		// 计算病人时长
		if (upVedioTime(pId, watchTime).get("status").equals("true")) {
			// 计算视频百分比
			float percent = 0;
			watchTime = watchTime / bfTime;// 平均观看总时长
			if (sp.getVedioTime() == null || sp.getVedioTime().equals("")
					|| sp.getVedioTime().equals("NaN")) {
				System.out.println("sp.getVedioTime()" + sp.getVedioTime());
				sp.setVedioTime("0");
			}
			int vedioTime = spLen * Integer.parseInt(sp.getVedioTime());// 平均视频时长
			// 估计判断观看总时长与视频总时长大小
			if (watchTime >= vedioTime) {
				percent = 1;
			} else {
				percent = (float) watchTime / (float) vedioTime;
			}
			sp.setVedioTime(String.valueOf(vedioTime));
			sp.setWatchTime(sp.getWatchTime());
			sp.setXlPercent(String.valueOf(percent));
			count = spService.computePercent(sp);
			if (count >= 1) {
				m.put("status", "true");
				m.put("time",
						upVedioTime(pId, Integer.parseInt(sp.getWatchTime()))
								.get("time"));
				return m;
			}
			m.put("status", "no");
			return m;
		}
		m.put("status", "false");
		return m;
	}

	public Map<String, String> upVedioTime(int id, int videoTime) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		int myCount = 0;
		if (videoTime > 60) {
			int sy = videoTime % 60;
			if (sy > 30) {
				videoTime = (videoTime / 60) + 1;
			} else {
				videoTime = videoTime / 60;
			}
		} else if (videoTime > 30) {
			videoTime = 1;
		} else {
			videoTime = 0;
		}
		// System.out.println("videoTime=====>" + videoTime);
		myCount = patService.upVedioTime(videoTime, id);
		if (myCount == 1) {
			map.put("status", "true");
			map.put("time", videoTime + "");
			return map;
		}
		map.put("status", "false");
		return map;
	}

	// 修改评测
	@RequestMapping("/pat/upPcComment")
	@ResponseBody
	public ResponseEntity upPcComment(Xlsp sp) {
		// TODO Auto-generated method stub
		int count = 0;
		count = spService.upPcComment(sp);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// ***************医生端 ****************//

	// 生成训练计划视频
	@RequestMapping("/doc/xzsp")
	@ResponseBody
	public ResponseEntity scSp(Xlsp sp) {
		// TODO Auto-generated method stub
		// 先删除多余未激活视频
		// spService.delMoreSp(sp);
		int count = 0;
		int count2 = 0;
		// 将获取到的多个视频 进行转换成数组
		String xmIDs = sp.getXmID();
		List<String> xmIDArr = Utils.stringToStringList(xmIDs, ",");
		// 循环是否是评测EXE----09
		if (sp.getType().equals("09")) {
			for (String xmID : xmIDArr) {
				sp.setXmID(xmID);
				sp.setTitles(xmService.getPcTitleById(xmID));
				count += spService.addSp(sp);
			}
		} else {
			for (String xmID : xmIDArr) {
				sp.setXmID(xmID);
				sp.setTitles(xmService.getTitleById(xmID));
				count += spService.addSp(sp);
			}
		}
		if (count >= 1) {
			// 保存完视频 回置训练计划表
			Xljh jh = new Xljh();
			jh.setpID(sp.getpID());
			jh.setdID(sp.getdID());
			jh.setWeek(sp.getWeek());
			jh.setDay(sp.getDay());
			jh.setTime(sp.getTime());
			jh.setType(sp.getType());
			jh.setVedioAccount(String.valueOf(count));
			count2 = jhService.setJhAccount(jh);
			if (count2 == 1) {
				return new ResponseEntity("true", String.valueOf(count));
			}
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 获取编辑视频列表
	@RequestMapping("/doc/getBjsp")
	@ResponseBody
	public List<Xlsp> getBjSp(
			@RequestParam(value = "week", required = true) String week,
			@RequestParam(value = "day", required = true) String day,
			@RequestParam(value = "time", required = true) String time,
			@RequestParam(value = "dID", required = true) String dID,
			@RequestParam(value = "pID", required = true) String pID,
			@RequestParam(value = "type", required = true) String type) {
		// TODO Auto-generated method stub
		List<Xlsp> bjSpList = spService
				.getBjSp(week, day, time, dID, pID, type);
		return bjSpList;
	}

	// 删除 编辑视频列表
	@RequestMapping("/doc/scsp")
	@ResponseBody
	public ResponseEntity deleteDoc(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = spService.deleteSp(ids);
		if (count >= 1) {
			return new ResponseEntity("true", String.valueOf(count));
		}
		return new ResponseEntity("false", "操作失败！");
	}
}
