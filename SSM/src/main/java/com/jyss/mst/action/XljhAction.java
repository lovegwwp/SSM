package com.jyss.mst.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jyss.mst.entity.PatXlFa;
import com.jyss.mst.entity.ResponseEntity;
import com.jyss.mst.entity.Xljh;
import com.jyss.mst.entity.Xlsp;
import com.jyss.mst.entity.Xtcl;
import com.jyss.mst.entity.JsonEntity.BfTimeJsonEntity;
import com.jyss.mst.entity.JsonEntity.ExeMakeIf;
import com.jyss.mst.entity.JsonEntity.ExePlan;
import com.jyss.mst.entity.JsonEntity.ExePlans;
import com.jyss.mst.entity.JsonEntity.JhJsonEntity;
import com.jyss.mst.entity.JsonEntity.Plan;
import com.jyss.mst.entity.JsonEntity.PlanListEntity;
import com.jyss.mst.entity.JsonEntity.XlfaJsonEntity;
import com.jyss.mst.entity.JsonEntity.XljhAndSP;
import com.jyss.mst.service.XljhService;
import com.jyss.mst.service.XlspService;
import com.jyss.mst.service.XlxmService;
import com.jyss.mst.service.XtclService;
import com.jyss.mst.utils.CommTool;
import com.jyss.mst.utils.MsgUtil;

@Controller
public class XljhAction {
	@Autowired
	XljhService jhService;
	@Autowired
	XlspService spService;
	@Autowired
	XtclService clService;
	@Autowired
	private XlxmService xmService;

	// /=====**************病人端*********************====///

	@RequestMapping("/pat/getPatJh")
	@ResponseBody
	public List<XlfaJsonEntity> getPatJh(@RequestParam("pID") String pID) {
		// TODO Auto-generated method stub
		List<XlfaJsonEntity> jsonList = new ArrayList<XlfaJsonEntity>();
		XlfaJsonEntity jsonFa = null;

		// 获取医生列表
		List<PatXlFa> dIDJhList = jhService.getPatJhDID(pID);
		// 循环医生ID
		BfTimeJsonEntity bfEntity = null;

		if (dIDJhList != null) {
			for (PatXlFa dIdXlFa : dIDJhList) {
				List<PatXlFa> patJhList = jhService.getPatJh(pID,
						dIdXlFa.getdID() + "");
				if (patJhList != null) {
					for (PatXlFa patXlFa : patJhList) {
						List<Xlsp> patXlSpList = spService.getXlSpByPat(
								patXlFa.getWeek(), patXlFa.getDay(),
								patXlFa.getTime(), patXlFa.getdID() + "", pID,
								patXlFa.getType(), "1");
						// 例如： 第一周第一天第一次训练平均百分比
						float xlPerCent = 0;
						for (Xlsp xlsp : patXlSpList) {
							xlPerCent += Float.parseFloat(xlsp.getXlPercent());
						}
						xlPerCent = xlPerCent / patXlSpList.size();
						patXlFa.setXlPercent(xlPerCent + "");

						// 每个视频列表都加一个播放次数
						bfEntity = new BfTimeJsonEntity(patXlFa.getBfTime(),
								MsgUtil.formatList(patXlSpList));
						patXlFa.setSpEntity(bfEntity);
						patXlFa.setItem(patXlFa.getType() + patXlFa.getWeek()
								+ patXlFa.getDay() + patXlFa.getTime());

					}
				}
				jsonFa = new XlfaJsonEntity(dIdXlFa.getdID() + "", patJhList);
				jsonList.add(jsonFa);
			}
		}

		return jsonList;
	}

	// /=====***************医生端*********************====///

	/**
	 * 获取自定义生成计划
	 * 
	 * @param type
	 * @param pID
	 * @param dID
	 * @return
	 */
	@RequestMapping("/doc/getJhForBj")
	@ResponseBody
	public List<Xljh> getJhForBj(@RequestParam("type") String type,
			@RequestParam("pID") String pID, @RequestParam("dID") String dID) {
		// TODO Auto-generated method stub
		// PageHelper.startPage(page, rows);// 分页语句
		List<Xljh> jhList = jhService.getJhForBj(type, dID, pID);
		// PageInfo<Xljh> pageInfoJh = new PageInfo<Xljh>(jhList);
		return jhList;
	}

	@RequestMapping("/doc/getJsonJhForBj")
	@ResponseBody
	public XljhAndSP getJsonJhForBj(@RequestParam("pId") String pId,
			@RequestParam("dId") String dId) {

		ExePlan exePlan = null;
		String exeName = "";
		List<ExePlan> exePlanList = new ArrayList<ExePlan>();
		List<Xljh> jhTypeList = jhService.getTypeForJson("1", dId, pId);
		if (jhTypeList.size() != 0 && jhTypeList != null) {
			// 训练类型循环

			for (Xljh xljh : jhTypeList) {
				Xtcl cl = clService.getClsValue("xlxm_type", xljh.getType());
				exeName = cl.getBz_value();
				// 循环该类型下训练项目
				ExeMakeIf exeMakeIf = jhService.getTotalJhForJson("1",
						xljh.getType(), dId, pId);
				// 分计划循环
				List<Plan> listPlan = new ArrayList<Plan>();
				Plan plan = null;
				List<Xljh> jhInfoList = jhService.getJhForJson("1",
						xljh.getType(), dId, pId);
				if (jhInfoList.size() != 0 && jhInfoList != null) {
					for (Xljh xljh2 : jhInfoList) {
						if (xljh.getType().equals("09")) {
							// 评测
							List<Xlsp> spList = spService.getPcByPat(
									xljh2.getWeek(), xljh2.getDay(),
									xljh2.getTime(), dId, pId, xljh.getType(),
									"1");
						}
						// 训练视频
						List<Xlsp> spList = spService.getXlSpByPat(
								xljh2.getWeek(), xljh2.getDay(),
								xljh2.getTime(), dId, pId, xljh.getType(), "1");
						/*
						 * for (Xlsp xlsp : spList) {
						 * System.out.println(xlsp.getTitles() + "-------->" +
						 * xlsp.getVedio()); }
						 * System.out.println("============================");
						 */
						// 格式化视频
						spList = MsgUtil.formatList(spList);
						/*
						 * for (Xlsp xlsp : spList) {
						 * System.out.println(xlsp.getTitles() + "-------->" +
						 * xlsp.getVedio()); }
						 */

						/*
						 * List<Xlsp> spList = spService.getXlSpByPat(
						 * xljh2.getWeek(), xljh2.getDay(), xljh.getTime(), dId,
						 * pId, xljh.getType());
						 */

						List<ExePlans> exePlansList = new ArrayList<ExePlans>();
						int watchTime = 0;
						ExePlans exePlans = null;
						if (spList.size() != 0 && spList != null) {
							// 视频路径URL
							for (Xlsp xlsp : spList) {
								exePlans = new ExePlans(xlsp.getTitles(), xlsp
										.getVedio().split(","), "");
								exePlansList.add(exePlans);
								// 累计观看时长
								if (xlsp.getWatchTime() != null
										&& !(xlsp.getWatchTime().equals(""))) {
									watchTime += Integer.parseInt(xlsp
											.getWatchTime());
									System.out.println(xlsp.getWatchTime()
											+ "========>" + watchTime);
								}

							}

						}
						plan = new Plan("第" + xljh2.getWeek() + "周" + "第"
								+ xljh2.getDay() + "天" + "第" + xljh2.getTime()
								+ "次", watchTime + "", String.valueOf(spList
								.size()), exePlansList);
						listPlan.add(plan);
					}

				}
				exePlan = new ExePlan(exeName, exeMakeIf, listPlan,
						new Date().getTime() + "");
				exePlanList.add(exePlan);
			}
		}
		XljhAndSP jhsp = new XljhAndSP(pId, dId, exePlanList);
		return jhsp;
	}

	@RequestMapping("/doc/getJsonJhForBjByType")
	@ResponseBody
	public XljhAndSP getJsonJhForBjByType(@RequestParam("pId") String pId,
			@RequestParam("dId") String dId, @RequestParam("type") String type) {

		ExePlan exePlan = null;
		String exeName = "";
		List<ExePlan> exePlanList = new ArrayList<ExePlan>();

		// 训练类型循环

		/* for (Xljh xljh : jhTypeList) { */
		Xtcl cl = clService.getClsValue("xlxm_type", type);
		exeName = cl.getBz_value();
		// 循环该类型下训练项目
		ExeMakeIf exeMakeIf = jhService.getTotalJhForJson("1", type, dId, pId);
		// 分计划循环
		List<Plan> listPlan = new ArrayList<Plan>();
		Plan plan = null;
		List<Xlsp> spList = new ArrayList<Xlsp>();
		List<Xljh> jhInfoList = jhService.getJhForJson("1", type, dId, pId);
		if (jhInfoList.size() != 0 && jhInfoList != null) {
			for (Xljh xljh2 : jhInfoList) {
				if (type.equals("09")) {
					// 评测
					spList = spService.getPcByPat(xljh2.getWeek(),
							xljh2.getDay(), xljh2.getTime(), dId, pId, type,
							"1");
				} else {
					// 训练视频
					spList = spService.getXlSpByPat(xljh2.getWeek(),
							xljh2.getDay(), xljh2.getTime(), dId, pId, type,
							"1");
				}

				// 格式化视频
				spList = MsgUtil.formatList(spList);
				for (Xlsp p : spList) {
					System.out.println(p.getVedio());
				}

				List<ExePlans> exePlansList = new ArrayList<ExePlans>();
				int watchTime = 0;
				ExePlans exePlans = null;
				String[] strArr = { "" };
				if (spList.size() != 0 && spList != null) {
					// 视频路径URL
					for (Xlsp xlsp : spList) {
						if (xlsp != null) {

							if (xlsp.getVedio() != null
									&& !xlsp.getVedio().equals("")
									&& xlsp.getVedio().contains(",")) {
								strArr = xlsp.getVedio().split(",");
							}
							exePlans = new ExePlans(xlsp.getTitles(), strArr,
									"");
							exePlansList.add(exePlans);
							// 累计观看时长
							if (xlsp.getWatchTime() != null
									&& !(xlsp.getWatchTime().equals(""))) {
								watchTime += Integer.parseInt(xlsp
										.getWatchTime());

							}
						}
					}

				}
				plan = new Plan("第" + xljh2.getWeek() + "周" + "第"
						+ xljh2.getDay() + "天" + "第" + xljh2.getTime() + "次",
						watchTime + "", String.valueOf(spList.size()),
						exePlansList);
				listPlan.add(plan);
			}

		}
		exePlan = new ExePlan(exeName, exeMakeIf, listPlan,
				new Date().getTime() + "");
		exePlanList.add(exePlan);
		XljhAndSP jhsp = new XljhAndSP(pId, dId, exePlanList);
		return jhsp;
	}

	@RequestMapping("/doc/getJsonJhMb")
	@ResponseBody
	public XljhAndSP getJsonJhMb(@RequestParam("type") String type,
			@RequestParam("dId") String dId) {
		ExePlan exePlan = null;
		String exeName = "";
		List<ExePlan> exePlanList = new ArrayList<ExePlan>();

		Xtcl cl = clService.getClsValue("xlxm_type", type);
		if (cl == null) {
			exeName = "";
		} else {
			exeName = cl.getBz_value();
		}
		// 循环该类型下训练项目
		// ExeMakeIf exeMakeIf = new ExeMakeIf();
		ExeMakeIf exeMakeIf = jhService.getTotalJhForJson("2", type, dId, "");
		if (exeMakeIf == null) {
			exeMakeIf = new ExeMakeIf("", "", "", "", "");
		}
		// 分计划循环
		List<Plan> listPlan = new ArrayList<Plan>();
		Plan plan = null;
		List<Xlsp> spList = new ArrayList<Xlsp>();
		List<Xljh> jhInfoList = jhService.getJhForJson("2", type, dId, "");
		if (jhInfoList.size() != 0 && jhInfoList != null) {
			for (Xljh xljh2 : jhInfoList) {
				if (type.equals("09")) {
					// 评测
					spList = spService
							.getPcByPat(xljh2.getWeek(), xljh2.getDay(),
									xljh2.getTime(), dId, "", type, "2");
				} else {
					// 训练视频
					spList = spService
							.getXlSpByPat(xljh2.getWeek(), xljh2.getDay(),
									xljh2.getTime(), dId, "", type, "2");
				}

				spList = MsgUtil.formatList(spList);

				List<ExePlans> exePlansList = new ArrayList<ExePlans>();
				int watchTime = 0;
				String[] content = {};
				ExePlans exePlans = new ExePlans("", content, "");
				if (spList.size() != 0 && spList != null) {
					// 视频路径URL
					for (Xlsp xlsp : spList) {
						if (xlsp.getVedio() != null
								&& !xlsp.getVedio().equals("")) {
							content = xlsp.getVedio().split(",");
						}
						exePlans = new ExePlans(xlsp.getTitles(), content,
								xlsp.getXmID());
						exePlansList.add(exePlans);
						// 累计观看时长
						if (xlsp.getWatchTime() != null
								&& !(xlsp.getWatchTime().equals(""))) {
							watchTime += Integer.parseInt(xlsp.getWatchTime());

						}

					}

				}
				plan = new Plan("第" + xljh2.getWeek() + "周" + "第"
						+ xljh2.getDay() + "天" + "第" + xljh2.getTime() + "次",
						watchTime + "", String.valueOf(spList.size()),
						exePlansList);
				listPlan.add(plan);
			}

		}
		exePlan = new ExePlan(exeName, exeMakeIf, listPlan,
				new Date().getTime() + "");
		exePlanList.add(exePlan);

		XljhAndSP jhsp = new XljhAndSP(dId, "", exePlanList);
		return jhsp;
	}

	/**
	 * 编辑 自定义生成计划
	 * 
	 * @param type
	 * @param pID
	 * @param dID
	 * @return
	 */
	@RequestMapping("/doc/getScJh")
	@ResponseBody
	public List<Xljh> getScJh(@RequestParam("type") String type,
			@RequestParam("pID") String pID, @RequestParam("dID") String dID) {
		// TODO Auto-generated method stub
		// PageHelper.startPage(page, rows);// 分页语句
		List<Xljh> jhList = jhService.getScJh(type, dID, pID);
		// PageInfo<Xljh> pageInfoJh = new PageInfo<Xljh>(jhList);
		return jhList;
	}

	// 生成计划 ===计划插入
	@RequestMapping("/doc/scjh")
	@ResponseBody
	public ResponseEntity scJh(Xljh jh) {
		// TODO Auto-generated method stub
		// 删除多余未激活计划视频
		Xlsp sp = new Xlsp();
		sp.setpID(jh.getpID());
		sp.setdID(jh.getdID());
		sp.setType(jh.getType());
		spService.delMoreSp(sp);
		// 替换视频
		spService.replaceSpType(sp);
		// 先删除多余未激活计划
		jhService.delMoreJh(jh);
		// 替换计划
		jhService.replaceJhType(jh);
		int count = 0;
		int week = Integer.parseInt(jh.getWeek());
		int day = Integer.parseInt(jh.getDay());
		int time = Integer.parseInt(jh.getTime());
		int totalaccount = week * day * time;
		// 循环存入训练计划 根据 周 天 次
		for (int i = 1; i <= week; i++) {
			for (int j = 1; j <= day; j++) {
				for (int j2 = 1; j2 <= time; j2++) {
					jh.setWeek(String.valueOf(i));
					jh.setDay(String.valueOf(j));
					jh.setTime(String.valueOf(j2));
					count += jhService.scJh(jh);
				}
			}
		}
		if (count == totalaccount) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 生成计划 ===计划插入===根据安卓需求进行改动
	@RequestMapping(value = "/doc/scJhByJson", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity scJhByJson(@RequestParam("myJson") String myJson) {
		// TODO Auto-generated method stub
		System.out.println(myJson);
		if (myJson == null || myJson.equals("")) {
			return new ResponseEntity("false", "发送数据为空！");
		}
		String type = "0";
		String pId = "0";
		String dId = "0";
		JhJsonEntity jhJsonEntity = null;
		try {
			jhJsonEntity = JSON.parseObject(myJson, JhJsonEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("false", "解析错误！");
		}
		if (jhJsonEntity == null) {
			return new ResponseEntity("false", "解析错误！");
		}
		type = jhJsonEntity.getType();
		pId = jhJsonEntity.getPId();
		dId = jhJsonEntity.getDId();
		// 先删除同一医生 同一病人 同意训练类型 的训练方案==========////
		jhService.delFaJh(dId, pId, type);
		spService.delFaSp(dId, pId, type);
		// ///======================================////
		// 第一周第一天第一次
		List<PlanListEntity> weekDayTimesList = new ArrayList<PlanListEntity>();
		// 第一周第一天第一次 =====具体对应视频
		List<ExePlans> exePlansList = new ArrayList<ExePlans>();
		Xljh jh = null;
		Xlsp sp = null;
		String vedioAccount = "0";
		String exeid = "0";
		String name = "0";
		String timemins = "0";
		String bfTime = "0";
		String titles = "0";
		int week = 0;
		int day = 0;
		int time = 0;
		int totalCount = 0;
		int count = 0;
		int SingeleCount = 0;
		ExeMakeIf exeMakeIf = null;
		Map<String, String> weekDayTimeMap = new HashMap<String, String>();
		exeMakeIf = jhJsonEntity.getExeMakeIf();
		if (exeMakeIf != null) {
			week = Integer.parseInt(exeMakeIf.getWeek());
			day = Integer.parseInt(exeMakeIf.getWeekday());
			time = Integer.parseInt(exeMakeIf.getDaytime());
			totalCount = week * day * time;
			timemins = exeMakeIf.getTimesmins();
			bfTime = exeMakeIf.getBfTime();
		} else {
			return new ResponseEntity("false", "发送数据为空！");
		}
		weekDayTimesList = jhJsonEntity.getWeekDayTimesList();
		for (PlanListEntity PlanListEntity : weekDayTimesList) {
			vedioAccount = PlanListEntity.getCounts();// 视频个数
			name = PlanListEntity.getName();
			weekDayTimeMap = CommTool.getMyMap(name);
			exePlansList = PlanListEntity.getExePlansList();
			if (exePlansList != null && exePlansList.size() != 0) {
				for (ExePlans exePlans : exePlansList) {
					exeid = exePlans.getExeId();
					if (type.equals("09")) {
						if (spService.getPcTitles(exeid) != null
								&& spService.getPcTitles(exeid).size() != 0) {
							titles = spService.getPcTitles(exeid).get(0)
									.getTitles();
						} else {
							return new ResponseEntity("false", "无对应评测程序！");
						}
					} else {
						titles = xmService.getTitleById(exeid);
					}
					// 插入视频表
					sp = returnSp(Integer.parseInt(pId), Integer.parseInt(dId),
							type, weekDayTimeMap.get("week"),
							weekDayTimeMap.get("day"),
							weekDayTimeMap.get("time"), 1, titles, exeid);
					count += spService.addSp(sp);
				}
			}
			if (count < 1) {
				return new ResponseEntity("false", "操作失败！");
			}
			jh = returnJh(Integer.parseInt(pId), Integer.parseInt(dId), type,
					weekDayTimeMap.get("week"), weekDayTimeMap.get("day"),
					weekDayTimeMap.get("time"), 1, timemins, vedioAccount,
					bfTime);
			SingeleCount += jhService.scJh(jh);
			// 插入计划表
		}
		if (SingeleCount == totalCount) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 生成计划 ===计划插入===根据安卓需求进行改动
	@RequestMapping(value = "/doc/scMbByJson", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity scMbByJson(@RequestParam("myJson") String myJson) {
		// TODO Auto-generated method stub
		System.out.println(myJson);
		Xljh jh = new Xljh();
		Xlsp sp = new Xlsp();
		JhJsonEntity jhJsonEntity = null;
		if (myJson == null || myJson.equals("")) {
			return new ResponseEntity("false", "发送数据为空！");
		}
		String type = "0";
		String pId = "0";
		String dId = "0";
		try {
			jhJsonEntity = JSON.parseObject(myJson, JhJsonEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("false", "解析错误！");
		}
		if (jhJsonEntity == null) {
			return new ResponseEntity("false", "发送数据为空！");
		}
		type = jhJsonEntity.getType();
		// pId = jhJsonEntity.getPId();
		dId = jhJsonEntity.getDId();
		System.out.println("=================>" + dId);
		// 先删除同一医生 同一病人 同意训练类型 的训练方案==========////
		jh.setdID(Integer.parseInt(dId));
		jh.setType(type);
		sp.setdID(Integer.parseInt(dId));
		sp.setType(type);
		jhService.delMoreMbJh(jh);
		spService.delMbSp(sp);
		// ///======================================////
		// 第一周第一天第一次
		List<PlanListEntity> weekDayTimesList = new ArrayList<PlanListEntity>();
		// 第一周第一天第一次 =====具体对应视频
		List<ExePlans> exePlansList = new ArrayList<ExePlans>();
		String vedioAccount = "0";
		String exeid = "0";
		String name = "0";
		String timemins = "0";
		String bfTime = "0";
		String titles = "0";
		int week = 0;
		int day = 0;
		int time = 0;
		int totalCount = 0;
		int count = 0;
		int SingeleCount = 0;
		ExeMakeIf exeMakeIf = null;
		Map<String, String> weekDayTimeMap = new HashMap<String, String>();
		exeMakeIf = jhJsonEntity.getExeMakeIf();
		if (exeMakeIf != null) {
			week = Integer.parseInt(exeMakeIf.getWeek());
			day = Integer.parseInt(exeMakeIf.getWeekday());
			time = Integer.parseInt(exeMakeIf.getDaytime());
			totalCount = week * day * time;
			timemins = exeMakeIf.getTimesmins();
			bfTime = exeMakeIf.getBfTime();
		} else {
			return new ResponseEntity("false", "发送数据为空！");
		}
		weekDayTimesList = jhJsonEntity.getWeekDayTimesList();
		for (PlanListEntity PlanListEntity : weekDayTimesList) {
			vedioAccount = PlanListEntity.getCounts();// 视频个数
			name = PlanListEntity.getName();
			weekDayTimeMap = CommTool.getMyMap(name);
			exePlansList = PlanListEntity.getExePlansList();
			if (exePlansList != null && exePlansList.size() != 0) {
				for (ExePlans exePlans : exePlansList) {
					exeid = exePlans.getExeId();
					if (type.equals("09")) {
						if (spService.getPcTitles(exeid) != null
								&& spService.getPcTitles(exeid).size() != 0) {
							titles = spService.getPcTitles(exeid).get(0)
									.getTitles();
						} else {
							return new ResponseEntity("false", "无对应评测程序！");
						}
					} else {
						titles = xmService.getTitleById(exeid);
					}
					// 插入视频表
					sp = returnSp(Integer.parseInt(pId), Integer.parseInt(dId),
							type, weekDayTimeMap.get("week"),
							weekDayTimeMap.get("day"),
							weekDayTimeMap.get("time"), 2, titles, exeid);
					count += spService.addSp(sp);
				}
			}
			if (count < 1) {
				return new ResponseEntity("false", "操作失败！");
			}
			jh = returnJh(Integer.parseInt(pId), Integer.parseInt(dId), type,
					weekDayTimeMap.get("week"), weekDayTimeMap.get("day"),
					weekDayTimeMap.get("time"), 2, timemins, vedioAccount,
					bfTime);
			// 插入计划表
			SingeleCount += jhService.scJh(jh);

		}
		if (SingeleCount == totalCount) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	public Xljh returnJh(int pID, int dID, String type, String week,
			String day, String time, int xlmb, String vedioTime,
			String vedioAccount, String bfTime) {
		Xljh jh = new Xljh();
		jh.setpID(pID);
		jh.setdID(dID);
		jh.setType(type);
		jh.setWeek(week);
		jh.setDay(day);
		jh.setTime(time);
		jh.setVedioTime(vedioTime);
		jh.setXlmb(xlmb);
		jh.setVedioAccount(vedioAccount);
		jh.setBfTime(bfTime);
		return jh;

	}

	public Xlsp returnSp(int pID, int dID, String type, String week,
			String day, String time, int status, String titles, String xmID) {
		Xlsp sp = new Xlsp();
		sp.setpID(pID);
		sp.setdID(dID);
		sp.setType(type);
		sp.setWeek(week);
		sp.setDay(day);
		sp.setTime(time);
		sp.setStatus(status);
		sp.setTitles(titles);
		sp.setXmID(xmID);
		return sp;

	}

	// 发送给病人 激活计划 视频
	@RequestMapping("/doc/fsbr")
	@ResponseBody
	public ResponseEntity activeXl(@RequestParam("pID") int pID,
			@RequestParam("dID") int dID, @RequestParam("type") String type) {
		// TODO Auto-generated method stub
		Xljh jh2 = new Xljh();
		Xlsp sp2 = new Xlsp();
		int count = 0;
		sp2.setdID(dID);
		sp2.setpID(pID);
		sp2.setType(type);
		jh2.setdID(dID);
		jh2.setpID(pID);
		jh2.setType(type);
		count = jhService.activeJh(jh2);
		if (count >= 1) {
			count = spService.activeSp(sp2);
			if (count >= 1) {
				return new ResponseEntity("true", "操作成功！");
			}
		}
		return new ResponseEntity("false", "操作失败！");
	}

	/**
	 * 获取自模板计划 载入模板计划
	 * 
	 * @param type
	 * @param dID
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/doc/getMbJh")
	@ResponseBody
	public ResponseEntity getMbJh(@RequestParam("type") String type,
			@RequestParam("dID") int dID, @RequestParam("pID") int pID) {
		int count = 0;
		List<Xljh> mbList = jhService.getMbJh(String.valueOf(type),
				String.valueOf(dID));
		// 查询是否有无模板
		if (mbList == null || mbList.size() == 0) {
			return new ResponseEntity("false", "尚无模板计划");
		}
		// 有模板 就进行copy 计划和视频 此前先删除残余未激活视频
		Xljh jh2 = new Xljh();
		jh2.setType(type);
		jh2.setpID(pID);
		jh2.setdID(dID);
		jhService.delMoreJh(jh2);
		Xlsp sp2 = new Xlsp();
		sp2.setType(type);
		sp2.setpID(pID);
		sp2.setdID(dID);
		spService.delMoreSp(sp2);
		count = jhService.copyMbJh(jh2);
		if (count >= 1) {
			count = spService.copyMbSp(sp2);
			if (count >= 1) {
				return new ResponseEntity("true", "获取模板成功！");
			}
		}
		return new ResponseEntity("false", "获取模板失败！");
	}

	@RequestMapping("/doc/bcmb")
	@ResponseBody
	public ResponseEntity saveMb(Xljh jh) {
		// TODO Auto-generated method stub
		int count = 0;
		// 删除多余模板视频
		Xlsp sp1 = new Xlsp();
		sp1.setType(jh.getType());
		sp1.setpID(jh.getpID());
		sp1.setdID(jh.getdID());
		spService.delMbSp(sp1);
		// 删除多余模板计划
		jhService.delMoreMbJh(jh);
		// 进行模板保存
		count = jhService.saveMb(jh);
		if (count >= 1) {
			count = spService.saveMbSp(sp1);
			if (count >= 1) {
				return new ResponseEntity("true", "操作成功！");
			}
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 删除发送的方案 包括计划视频
	@RequestMapping("/doc/delFa")
	@ResponseBody
	public ResponseEntity delFa(@RequestParam("pID") String pID,
			@RequestParam("dID") String dID, @RequestParam("type") String type) {

		int count = 0;
		count = jhService.delFaJh(dID, pID, type);
		if (count >= 1) {
			count = spService.delFaSp(dID, pID, type);
			if (count >= 1) {
				return new ResponseEntity("true", "操作成功！");
			}
		}
		return new ResponseEntity("false", "操作失败！");
	}

}
