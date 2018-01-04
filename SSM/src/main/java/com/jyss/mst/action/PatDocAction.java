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

import com.jyss.mst.entity.DocDoc;
import com.jyss.mst.entity.Doctors;
import com.jyss.mst.entity.News;
import com.jyss.mst.entity.PatDoc;
import com.jyss.mst.entity.ResponseEntity;
import com.jyss.mst.entity.ShareNews;
import com.jyss.mst.entity.Xlsp;
import com.jyss.mst.entity.Xtcl;
import com.jyss.mst.entity.JsonEntity.DocAllGroup;
import com.jyss.mst.entity.JsonEntity.DocGroup;
import com.jyss.mst.entity.JsonEntity.MyDoc;
import com.jyss.mst.entity.JsonEntity.MyPat;
import com.jyss.mst.entity.JsonEntity.PatGroup;
import com.jyss.mst.service.DocDocService;
import com.jyss.mst.service.DoctorsService;
import com.jyss.mst.service.NewsService;
import com.jyss.mst.service.PatDocService;
import com.jyss.mst.service.XlspService;
import com.jyss.mst.service.XtclService;

@Controller
public class PatDocAction {
	@Autowired
	private PatDocService pdService;
	@Autowired
	private DocDocService ddService;
	@Autowired
	private DoctorsService docService;
	@Autowired
	private XtclService clService;
	@Autowired
	private XlspService spService;
	@Autowired
	private NewsService newsService;

	// -------==============-----病人端-----================---------//
	/**
	 * 病人好友分组
	 * 
	 * @param pId
	 * @return
	 */

	/*
	 * public Page<PatDoc> getPFriend(
	 * 
	 * @RequestParam(value = "page", required = true) int page,
	 * 
	 * @RequestParam(value = "rows", required = true) int rows,
	 * 
	 * @RequestParam(value = "pId", required = true) String pId) {
	 * PageHelper.startPage(page, rows);// 分页语句 List<PatDoc> pdList =
	 * pdService.getPFriend(pId); PageInfo<PatDoc> pageInfoPatF = new
	 * PageInfo<PatDoc>(pdList); return new Page<PatDoc>(pageInfoPatF); }
	 */

	@RequestMapping("/pat/getPFriend")
	@ResponseBody
	public List<PatDoc> getPFriend(
			@RequestParam(value = "pId", required = true) String pId) {
		List<PatDoc> newPdList = new ArrayList<PatDoc>();
		List<PatDoc> pdList = new ArrayList<PatDoc>();
		List<PatDoc> emptyGroupList = new ArrayList<PatDoc>();
		List<PatDoc> pdAllList = new ArrayList<PatDoc>();
		List<PatDoc> emptyGroupAllList = new ArrayList<PatDoc>();
		List<PatDoc> myFriendList = new ArrayList<PatDoc>();
		// 获取好友分组
		List<PatDoc> pdGroupList = pdService.getPGroup(pId);
		// 获取分组医生好友列表
		int typeCount = 0;
		// 判断里面是否有【我的好友】分组
		if (pdGroupList != null) {
			for (PatDoc patDoc : pdGroupList) {
				if (patDoc.getGroupName() != null
						&& !patDoc.getGroupName().equals("")) {

					if (patDoc.getGroupName().equals("我的好友")) {
						typeCount++;
					}
				}
			}
		}
		// ，没有就默认添加
		if (typeCount == 0) {
			PatDoc newPd = new PatDoc(0, Integer.parseInt(pId), "我的好友");
			newPdList.add(newPd);
			// 存在【我的好友】分组
		} else {
			myFriendList = pdService.getPFriend(pId, "我的好友");
			newPdList.addAll(myFriendList);
		}
		// 循环分组，区分开【我的好友，以及【空分组】和【不空分组】】
		String countGroup = "0";
		for (PatDoc patd : pdGroupList) {
			if (patd.getGroupName() != null && !patd.getGroupName().equals("")) {
				countGroup = patd.getZd();// 获取是否是空组 ==1为空组
				if (!patd.getGroupName().equals("我的好友")) {
					if (countGroup.equals("1")) {
						// 空组
						emptyGroupList = pdService.getPFriend(pId,
								patd.getGroupName());
						emptyGroupAllList.addAll(emptyGroupList);
					} else {
						// 非空组
						pdList = pdService.getPFriend(pId, patd.getGroupName());
						pdAllList.addAll(pdList);
					}
				}

			}

		}
		newPdList.addAll(pdAllList);
		newPdList.addAll(emptyGroupAllList);
		return newPdList;
	}

	// 转移至其他分组
	@RequestMapping("/pat/yzfz")
	@ResponseBody
	public ResponseEntity updateYzByPat(PatDoc pd) {
		// TODO Auto-generated method stub
		int count = 0;
		count = pdService.updateYzByPat(pd);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 编辑分组名称
	@RequestMapping("/pat/bjfz")
	@ResponseBody
	public ResponseEntity updatePdByPat(PatDoc pd) {
		// TODO Auto-generated method stub
		int count = 0;
		count = pdService.updatePdByPat(pd);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 新增分组
	@RequestMapping("/pat/xzfz")
	@ResponseBody
	public ResponseEntity addPd(PatDoc pd) {
		// TODO Auto-generated method stub
		int count = 0;
		pd.setType(1);
		pd.setStatus(2);
		// pd.setDocGroupName("我的好友");
		count = pdService.addPd(pd);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 搜索医生 并加进分组
	@RequestMapping("/pat/ssxzfz")
	@ResponseBody
	public ResponseEntity searchAddPd(PatDoc pd) {
		// TODO Auto-generated method stub
		int count = 0;
		pd.setType(1);
		pd.setStatus(2);
		pd.setDocGroupName("我的病人");// 医生那边显示分组\
		List<PatDoc> myList = new ArrayList<PatDoc>();
		myList = pdService.getJsonDSingleFriend(pd.getDocId() + "", pd.getpId()
				+ "");
		if (myList != null && myList.size() >= 1) {
			return new ResponseEntity("false", "已存在好友关系！");
		}
		count = pdService.addPd(pd);
		if (count == 1) {
			/*
			 * count = 0; pd.setStatus(2); pd.setDocId(0); pd.setdId(0); count =
			 * pdService.addPd(pd); if (count == 1) {
			 */
			return new ResponseEntity("true", "操作成功！");

		}
		return new ResponseEntity("false", "操作失败！");
	}

	// -------==============-----医生端-----================---------//

	/**
	 * 医生 病人好友
	 * 
	 * @param docId
	 * @return
	 */
	@RequestMapping("/doc/getDFriend")
	@ResponseBody
	public List<PatDoc> getDFriend(
			@RequestParam(value = "docId", required = true) String docId) {
		List<PatDoc> pdList = pdService.getDFriend(docId);
		return pdList;
	}

	/**
	 * 医生 病人时长报警
	 * 
	 * @param docId
	 * @return
	 */

	@RequestMapping("/doc/getScWarm")
	@ResponseBody
	public Map<String, Object> getScWarm(
			@RequestParam(value = "docId", required = true) String docId) {

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("xlsc", getSpSc(docId));
		m.put("thsc", getTalkSc(docId));
		return m;
	}

	/**
	 * 医生 病人好友视频时长
	 * 
	 * @param docId
	 * @return
	 */
	// @RequestMapping("/doc/getSpSc")
	// @ResponseBody
	public List<PatDoc> getSpSc(
	// @RequestParam(value = "docId", required = true) String docId
			String docId) {
		String videoTime = "10";
		List<Xtcl> vxzList = clService.getClsCombox("vxz_type", "0");
		Xtcl cl2 = new Xtcl();
		if (vxzList.size() != 0) {
			cl2 = vxzList.get(0);
			videoTime = cl2.getBz_value();
		}
		List<PatDoc> pdVList = pdService.getPVTime(docId, videoTime);
		PatDoc c = new PatDoc();
		if (pdVList.size() > 0) {
			for (int i = 0; i < pdVList.size(); i++) {
				if (pdVList.get(i).getpId() == 0) {
					pdVList.remove(i);
				}
			}
		}
		return pdVList;
	}

	/**
	 * 医生 病人好友通话时长
	 * 
	 * @param docId
	 * @return
	 */

	// @RequestMapping("/doc/getTalkSc")
	// @ResponseBody
	public List<PatDoc> getTalkSc(
	// @RequestParam(value = "docId", required = true) String docId
			String docId) {

		List<Xtcl> txzList = clService.getClsCombox("txz_type", "0");
		Xtcl cl = new Xtcl();
		String talkTime = "10";
		if (txzList.size() != 0) {
			cl = txzList.get(0);
			talkTime = cl.getBz_value();
		}
		List<PatDoc> pdTList = pdService.getPTTime(docId, talkTime);
		if (pdTList.size() > 0) {
			for (int i = 0; i < pdTList.size(); i++) {
				if (pdTList.get(i).getpId() == 0) {
					pdTList.remove(i);
				}
			}
		}
		return pdTList;
	}

	/**
	 * 医生 病人好友分组
	 * 
	 * @param docId
	 * @return
	 */
	@RequestMapping("/doc/getDGoup")
	@ResponseBody
	public List<PatDoc> getDGoup(
			@RequestParam(value = "docId", required = true) String docId) {
		List<PatDoc> pdList = pdService.getDGroup(docId);
		return pdList;
	}

	/**
	 * 医生 病人好友分组---指定json
	 * 
	 * @param docId
	 * @return
	 */
	@RequestMapping("/doc/getMyJsonDGoupFriends")
	@ResponseBody
	public DocAllGroup getMyJsonDGoupFriends(
			@RequestParam(value = "docId", required = true) String docId) {

		// 病人列表
		List<PatGroup> patGroupList = new ArrayList<PatGroup>();
		List list = new ArrayList();
		// 病人组名循环
		List<PatDoc> pGroupList = pdService.getJsonDGroup(docId);
		PatGroup patGroup = null;
		boolean isHasPat = true;
		boolean isHasDoc = true;
		// 组名循环
		if (pGroupList != null) {
			// 训练病人列表
			String groupName = "";
			// 默认增加【我的病人】好友分组
			for (PatDoc p : pGroupList) {
				if (p.getDocGroupName().equals("我的病人")) {
					isHasPat = false;
				}
			}
			if (isHasPat) {
				patGroup = new PatGroup("我的病人", 0, list);
				patGroupList.add(patGroup);
			}
			for (PatDoc pd : pGroupList) {
				// 组名
				groupName = pd.getDocGroupName();
				// 循环每个组下面的病人好友
				List<PatDoc> pFriendList = pdService.getJsonDFriend(docId,
						pd.getDocGroupName());
				// 判断是否为空
				List<MyPat> myPatList = new ArrayList<MyPat>();
				MyPat myPat = null;
				Xlsp xlsp = null;
				int countPatNum = 0;
				if (pFriendList != null) {
					for (PatDoc pd2 : pFriendList) {
						if (pd2.getpId() != 0) {
							xlsp = spService.getJsonXlPercent(
									pd2.getpId() + "", docId, "");

							myPat = new MyPat(pd2.getpId() + "",
									pd2.getDocGroupName(),
									pd2.getStatus() + "", pd2.getAccount(),
									pd2.getUname(), pd2.getSex() + "",
									pd2.getAge() + "", pd2.getAvatar(), "",
									pd2.getZdId() + "", pd2.getZd(),
									pd2.getdId() + "", pd2.getDuname(), "",
									// clService.getClsValue("xlxm_type",pd2.getType()
									// + "").getBz_value(),
									"", null);
							if (xlsp == null) {
								myPat.setXlPercent("");
								myPat.setWcsj(null);
							} else {
								System.out.println("+++++++++++++++++++++++++");
								myPat.setXlPercent(xlsp.getXlPercent());
								System.out.println(xlsp.getXlPercent());
								myPat.setWcsj(xlsp.getLastModifyTime());
								System.out.println(xlsp.getLastModifyTime());
								System.out.println("+++++++++++++++++++++++++");
							}
							myPatList.add(myPat);
						} /*
						 * else { countPatNum += 1; }
						 */

					}
				}
				patGroup = new PatGroup(groupName, myPatList.size(), myPatList);
				patGroupList.add(patGroup);
			}

		}
		// 医生医生 好友列表
		List<DocGroup> docGroupList = new ArrayList<DocGroup>();
		// 医生好友组名
		List<DocDoc> ddGroupList = new ArrayList<DocDoc>();
		DocGroup docGroup = null;
		ddGroupList = ddService.getJsonDocGroup(docId);
		if (ddGroupList != null) {
			// 默认增加【我的好友】好友分组
			for (DocDoc dc : ddGroupList) {
				if (dc.getDocGroupName().equals("我的好友")) {
					isHasPat = false;
				}
			}
			if (isHasDoc) {
				docGroup = new DocGroup("我的好友", 0, list);
				docGroupList.add(docGroup);
			}
			// 组名循环
			String docgroupName = "";
			for (DocDoc docDoc : ddGroupList) {
				docgroupName = docDoc.getDocGroupName();
				// 循环每个组下面的医生好友
				List<DocDoc> dFriendList = ddService.getJsonDocFriend(docId,
						docDoc.getDocGroupName());
				List<MyDoc> MyDocList = new ArrayList<MyDoc>();
				MyDoc myDoc = null;
				int countDocNum = 0;
				String totalFw = "0";
				String nowFw = "0";
				if (dFriendList != null) {
					for (DocDoc dd : dFriendList) {
						if (dd.getDocId() != 0) {
							// 服务数量
							List<Doctors> docList = docService.getDocInfo(dd
									.getDocId(),
									clService.getClsValue("sjxz_type", "1")
											.getBz_value());
							if (docList != null && docList.size() > 0) {
								totalFw = docList.get(0).getTotalFw();
								nowFw = docList.get(0).getNowFw();

							}
							myDoc = new MyDoc(dd.getDocId(), dd.getAccount(),
									dd.getUname(), dd.getSex(), dd.getDwName(),
									dd.getKsName(), Integer.parseInt(dd
											.getAge()), dd.getJob(),
									dd.getSkills(), dd.getAbstracts(),
									dd.getAvatar(), "", "", dd.getScores(), "",
									dd.getLableNames(), "", dd.getCreatedAt(),
									dd.getLastModifyTime(), dd.getType(),
									dd.getStatus(), 0, totalFw, nowFw);
							MyDocList.add(myDoc);
						} /*
						 * else { countDocNum += 1; }
						 */

					}
				}
				docGroup = new DocGroup(docgroupName, MyDocList.size(),
						MyDocList);
				docGroupList.add(docGroup);
			}
		}
		Doctors doc = null;
		doc = docService.getDocsById(Integer.parseInt(docId));
		// 新闻列表
		List<News> newsList = newsService.getDocNews();
		// 新闻列表
		List<ShareNews> fxNewsList = newsService.getDocFxNews(docId);
		// 最终列表拼接
		DocAllGroup allGroup = new DocAllGroup(docId, patGroupList,
				docGroupList, doc, newsList, fxNewsList);
		return allGroup;
	}

	/**
	 * 医生 病人好友分组---指定json
	 * 
	 * @param docId
	 * @return
	 */
	@RequestMapping("/doc/getJsonDSinglePat")
	@ResponseBody
	public MyPat getJsonDSinglePat(
			@RequestParam(value = "docId", required = true) String docId,
			@RequestParam(value = "pId", required = true) String pId) {

		// PatGroup patGroup = null;
		// 得到单个病人好友
		List<PatDoc> pSingelFriendList = pdService.getJsonDSingleFriend(docId,
				pId);
		// 判断是否为空
		List<MyPat> mySingelPatList = new ArrayList<MyPat>();
		// 病人训练种类
		List<Xlsp> PatxlTypeList = new ArrayList<Xlsp>();
		PatxlTypeList = spService.getPatXlType(pId, docId);
		Map<String, String> m = null;
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		MyPat myPat = null;
		Xlsp xlsp = null;
		if (pSingelFriendList != null && pSingelFriendList.size() != 0) {
			PatDoc singelPat = pSingelFriendList.get(0);
			// for (PatDoc singelPat : pSingelFriendList) {
			if (singelPat.getpId() != 0) {
				for (Xlsp x : PatxlTypeList) {
					m = new HashMap<String, String>();
					m.put("type", x.getType());
					m.put("typeName",
							clService
									.getClsValue("xlxm_type", x.getType() + "")
									.getBz_value());
					xlsp = spService.getJsonXlPercent(singelPat.getpId() + "",
							docId, x.getType());
					if (xlsp == null) {
						m.put("xlPercent", "");
						m.put("wcsj", "");
					} else {
						if (x.getXlPercent() != null
								&& !x.getXlPercent().equals("")) {
							if (Float.parseFloat(x.getXlPercent().substring(0,
									1)) >= 1) {
								m.put("xlPercent", "1");
							} else {
								m.put("xlPercent", x.getXlPercent());
							}
						} else {
							m.put("xlPercent", "");
						}
						m.put("wcsj", x.getLastModifyTime().getTime() + "");
					}
					mapList.add(m);

				}
				myPat = new MyPat(singelPat.getpId() + "",
						singelPat.getDocGroupName(),
						singelPat.getStatus() + "", singelPat.getAccount(),
						singelPat.getUname(), singelPat.getSex() + "",
						singelPat.getAge() + "", singelPat.getAvatar(), "",
						singelPat.getZdId() + "", singelPat.getZd(),
						singelPat.getdId() + "", singelPat.getDuname(),
						clService.getClsValue("xlxm_type",
								singelPat.getType() + "").getBz_value(), "",
						null, mapList);

			}
		}

		// }
		return myPat;
	}

	// 转移至其他分组
	@RequestMapping("/doc/yzfz")
	@ResponseBody
	public ResponseEntity updateYzByDoc(PatDoc pd) {
		// TODO Auto-generated method stub
		int count = 0;
		count = pdService.updateYzByDoc(pd);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生 编辑分组名称
	@RequestMapping("/doc/bjfz")
	@ResponseBody
	public ResponseEntity updatePdByDoc(PatDoc pd) {
		// TODO Auto-generated method stub
		int count = 0;
		count = pdService.updatePdByDoc(pd);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生 新增分组
	@RequestMapping("/doc/xzfz")
	@ResponseBody
	public ResponseEntity addPdByDoc(PatDoc pd) {
		// TODO Auto-generated method stub
		int count = 0;
		pd.setStatus(2);
		count = pdService.addPdByDoc(pd);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生 搜索病人 并加进分组
	@RequestMapping("/doc/ssxzfz")
	@ResponseBody
	public ResponseEntity searchAddPdByDoc(PatDoc pd) {
		// TODO Auto-generated method stub
		int count = 0;
		pd.setStatus(2);
		pd.setGroupName("我的好友");// 病人对应分组
		List<PatDoc> myDList = new ArrayList<PatDoc>();
		myDList = pdService.getJsonDSingleFriend(pd.getDocId() + "",
				pd.getpId() + "");
		if (myDList != null && myDList.size() >= 1) {
			return new ResponseEntity("false", "已存在好友关系！");
		}
		count = pdService.addPdByDoc(pd);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生 删除 病人分组 把里面的病人移至到第一个分组
	@RequestMapping("/doc/scfz")
	@ResponseBody
	public ResponseEntity delPdFz(
			@RequestParam(value = "docId", required = true) String docId,
			@RequestParam(value = "groupName", required = true) String groupName) {
		// TODO Auto-generated method stub
		int count = 0;
		int count1 = 0;
		String firstGroupName = pdService.getJsonDGroup(docId).get(0)
				.getDocGroupName();
		// 默认从下往上删除 -- 但是 --如果从上往下删除 则会出现删除失败的原因
		if (groupName.equals(firstGroupName)) {
			if (pdService.getJsonDGroup(docId).size() > 1) {
				firstGroupName = pdService.getJsonDGroup(docId).get(1)
						.getDocGroupName();
			} else {
				firstGroupName = "我的病人";
			}

		}
		// 先删除为pid=0的原分组
		count = pdService.delDocGroup(docId, "0", groupName);
		PatDoc pd2 = new PatDoc();
		pd2.setDocId(Integer.parseInt(docId));
		pd2.setGroupName(groupName);
		pd2.setNewGroupName(firstGroupName);
		// 再次 更改组名 将删除分组的病人放到排序第一个的分组
		count1 = pdService.updatePdByDoc(pd2);
		if (count + count1 >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生 病人分享
	@RequestMapping("/doc/docAddFx")
	@ResponseBody
	public ResponseEntity docAddFx(
			@RequestParam(value = "docId", required = true) int docId,
			@RequestParam(value = "dId", required = true) int dId,
			@RequestParam(value = "pId", required = true) int pId) {
		// TODO Auto-generated method stub
		int count = 0;
		PatDoc pd = new PatDoc();
		pd.setpId(pId);
		pd.setdId(dId);
		pd.setDocId(docId);
		count = pdService.docAddFx(pd);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生-删除病人
	@RequestMapping("/doc/delPat")
	@ResponseBody
	public ResponseEntity delPat(
			@RequestParam(value = "dId", required = true) int dId,
			@RequestParam(value = "pId", required = true) int pId) {
		// TODO Auto-generated method stub
		int count = 0;
		count = pdService.delPatByDoc(dId, pId);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生-病人同意请求
	@RequestMapping("/agreepPdRelation")
	@ResponseBody
	public ResponseEntity agreepPdRelation(
			@RequestParam(value = "dId", required = true) int dId,
			@RequestParam(value = "pId", required = true) int pId) {
		// TODO Auto-generated method stub
		int count = 0;
		PatDoc pd = new PatDoc();
		pd.setdId(dId);
		pd.setpId(pId);
		pd.setStatus(2);
		count = pdService.upPdRelation(pd);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生-病人同意请求
	@RequestMapping("/refusepPdRelation")
	@ResponseBody
	public ResponseEntity refusepPdRelation(
			@RequestParam(value = "dId", required = true) int dId,
			@RequestParam(value = "pId", required = true) int pId) {
		// TODO Auto-generated method stub
		int count = 0;
		PatDoc pd3 = new PatDoc();
		pd3.setdId(dId);
		pd3.setpId(pId);
		pd3.setStatus(3);
		count = pdService.upPdRelation(pd3);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

}
