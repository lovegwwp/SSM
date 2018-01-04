package com.jyss.mst.entity.JsonEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jyss.mst.entity.Xljh;
import com.jyss.mst.entity.Xlsp;
import com.jyss.mst.entity.Xtcl;
import com.jyss.mst.service.XljhService;
import com.jyss.mst.service.XlspService;
import com.jyss.mst.service.XtclService;
import com.jyss.mst.service.impl.XljhServiceImpl;
import com.jyss.mst.service.impl.XlspServiceImpl;
import com.jyss.mst.service.impl.XtclServiceImpl;
import com.jyss.mst.utils.MsgUtil;

public class JsonMake {

	public static XljhAndSP getMyJson(String pId, String dId, String xlmb) {

		XljhService jhService = new XljhServiceImpl();
		XlspService spService = new XlspServiceImpl();
		XtclService clService = new XtclServiceImpl();

		// List<xtcl> cl = clService.;
		ExePlan exePlan = null;
		String exeName = "";
		List<ExePlan> exePlanList = null;
		List<Xljh> jhTypeList = jhService.getTypeForJson(xlmb, dId, pId);
		if (jhTypeList.size() != 0 && jhTypeList != null) {
			// 训练类型循环

			for (Xljh xljh : jhTypeList) {
				Xtcl cl = clService.getClsValue("xlxm_type", xljh.getType());
				exeName = cl.getBz_value();
				// 循环该类型下训练项目
				ExeMakeIf exeMakeIf = jhService.getTotalJhForJson(xlmb,
						xljh.getType(), dId, pId);
				// 分计划循环
				List<Plan> listPlan = new ArrayList<Plan>();
				Plan plan = null;
				List<Xljh> jhInfoList = jhService.getJhForJson(xlmb,
						xljh.getType(), dId, pId);
				if (jhInfoList.size() != 0 && jhInfoList != null) {
					for (Xljh xljh2 : jhInfoList) {
						// 训练视频
						List<Xlsp> spList = MsgUtil.formatList(spService
								.getXlSpByPat(xljh2.getWeek(), xljh2.getDay(),
										xljh.getTime(), dId, pId,
										xljh.getType(), "1"));

						List<ExePlans> exePlansList = new ArrayList<ExePlans>();

						ExePlans exePlans = null;
						if (spList.size() != 0 && spList != null) {
							// 视频路径URL
							for (Xlsp xlsp : spList) {
								exePlans = new ExePlans(xlsp.getTitles(), xlsp
										.getVedio().split(","));
								exePlansList.add(exePlans);
							}

						}
						plan = new Plan("第" + xljh2.getWeek() + "周" + "第"
								+ xljh2.getDay() + "天" + "第" + xljh2.getTime()
								+ "次", xljh2.getVedioTime(),
								String.valueOf(spList.size()), exePlansList);
					}
					listPlan.add(plan);
				}
				exePlan = new ExePlan(exeName, exeMakeIf, listPlan,
						new Date().getTime() + "");
				exePlanList.add(exePlan);
			}
		}
		XljhAndSP jhsp = new XljhAndSP(pId, dId, exePlanList);
		return jhsp;
	}
}
