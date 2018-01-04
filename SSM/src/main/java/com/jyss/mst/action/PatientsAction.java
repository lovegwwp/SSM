package com.jyss.mst.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.mst.entity.Bl;
import com.jyss.mst.entity.Devices;
import com.jyss.mst.entity.Page;
import com.jyss.mst.entity.PageFooter;
import com.jyss.mst.entity.PatSample;
import com.jyss.mst.entity.Patients;
import com.jyss.mst.entity.ResponseEntity;
import com.jyss.mst.entity.TjEntity;
import com.jyss.mst.entity.Xtcl;
import com.jyss.mst.entity.Zd;
import com.jyss.mst.service.DevicesService;
import com.jyss.mst.service.PatientsService;
import com.jyss.mst.service.XtclService;
import com.jyss.mst.utils.HttpClientUtil;
import com.jyss.mst.utils.PasswordUtil;
import com.jyss.mst.utils.Utils;

@Controller
public class PatientsAction {

	@Autowired
	private PatientsService patService;
	@Autowired
	private DevicesService devService;
	@Autowired
	private XtclService clService;

	// /******************医生端****************************///
	@RequestMapping("/doc/getPatByDoc")
	@ResponseBody
	public List<Patients> getPatByDoc(
			@RequestParam(value = "age1", required = false) String age1,
			@RequestParam(value = "age2", required = false) String age2,
			@RequestParam(value = "sex", required = false) String sex,
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "uname", required = false) String uname,
			@RequestParam(value = "addr", required = false) String addr) {

		List<Patients> docPatlist = patService.getPatByDoc(account, uname, sex,
				age1, age2, addr);
		return docPatlist;

	}

	// 获取病人个体
	@RequestMapping("/doc/getPatSample")
	@ResponseBody
	public List<PatSample> getPatSample(
			@RequestParam(value = "pId", required = true) String pId) {

		List<PatSample> patSampleList = patService.getPatSample(pId);
		return patSampleList;

	}

	/**
	 * 医生操作病人 诊断内容
	 * 
	 * @param zd
	 * @return
	 */
	@RequestMapping("/doc/zdOperate")
	@ResponseBody
	public ResponseEntity zdOperate(Zd zd,
			@RequestParam(value = "type", required = true) int type) {
		// TODO Auto-generated method stub
		int count = 0;
		// 新增
		if (type == 1) {
			count = patService.addZdContent(zd);
			if (count == 1) {
				count = 0;
				count = patService.upPatZdId(zd.getId(), zd.getpId());
			}
		}
		// 修改
		if (type == 2) {
			count = patService.upZdContent(zd);
		}
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// /******************后台****************************///

	@RequestMapping("/pat")
	public String patTz() {
		return "patients";
	}

	@RequestMapping("/patAddrTj")
	public String patAddrTjTz() {
		return "patAddrTj";
	}

	@RequestMapping("/patOnTj")
	public String patOnTjTz() {
		return "patOnTj";
	}

	@RequestMapping("/patDwTj")
	public String patDwTjTz() {
		return "patDwTj";
	}

	/******************* 单位统计 *********************/
	@RequestMapping({"/patAddrTjBy"})
	  @ResponseBody
	  public Page<TjEntity> patAddrTjBy(@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
	  {
	    PageHelper.startPage(page, rows);
	    List<TjEntity> patlistAddr = this.patService.getPatAddrTj(province, city);
	    int sum = 0;
	    for (TjEntity tjEntity : patlistAddr) {
	      sum += tjEntity.getNum();
	    }
	    PageInfo<TjEntity> pageInfoPatAddr = new PageInfo(patlistAddr);
	    PageFooter pf = new PageFooter("总人数", sum);
	    List<PageFooter> ptList = new ArrayList();
	    ptList.add(pf);
	    Page p = new Page(pageInfoPatAddr);
	    p.setFooter(ptList);
	    return p;
	  }
	  
	  @RequestMapping({"/patAddrTjCx"})
	  @ResponseBody
	  public Page<TjEntity> patAddrTjCx(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
	  {
	    PageHelper.startPage(page, rows);
	    List<TjEntity> patlistAddr = this.patService.getPatAddrTj("", "");
	    int sum = 0;
	    for (TjEntity tjEntity : patlistAddr) {
	      sum += tjEntity.getNum();
	    }
	    PageInfo<TjEntity> pageInfoPatAddr = new PageInfo(patlistAddr);
	    PageFooter pf = new PageFooter("总人数", sum);
	    List<PageFooter> ptList = new ArrayList();
	    ptList.add(pf);
	    Page p = new Page(pageInfoPatAddr);
	    p.setFooter(ptList);
	    return p;
	  }
	  
	  @RequestMapping({"/patOnTjBy"})
	  @ResponseBody
	  public Page<TjEntity> patOnTjBy(@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
	  {
	    String loginXz = getLoginXz();
	    PageHelper.startPage(page, rows);
	    List<TjEntity> patlistOn = this.patService.getPatOnTj(province, city, 
	      loginXz);
	    int sum = 0;
	    for (TjEntity tjEntity : patlistOn) {
	      sum += tjEntity.getNum();
	    }
	    PageInfo<TjEntity> pageInfoPatOn = new PageInfo(patlistOn);
	    PageFooter pf = new PageFooter("总人数", sum);
	    List<PageFooter> ptList = new ArrayList();
	    ptList.add(pf);
	    Page p = new Page(pageInfoPatOn);
	    p.setFooter(ptList);
	    return p;
	  }
	  
	  public String getLoginXz()
	  {
	    Xtcl cl = new Xtcl();
	    cl = this.clService.getClsValue("login_type", "1");
	    if ((cl == null) || (cl.getBz_value() == null) || 
	      (cl.getBz_value().equals(""))) {
	      cl.setBz_type("2");
	    }
	    return cl.getBz_value();
	  }
	  
	  @RequestMapping({"/patOnTjCx"})
	  @ResponseBody
	  public Page<TjEntity> patOnTjCx(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
	  {
	    String loginXz = getLoginXz();
	    PageHelper.startPage(page, rows);
	    List<TjEntity> patlistOn = this.patService.getPatOnTj("", "", loginXz);
	    int sum = 0;
	    for (TjEntity tjEntity : patlistOn) {
	      sum += tjEntity.getNum();
	    }
	    PageInfo<TjEntity> pageInfoPatOn = new PageInfo(patlistOn);
	    PageFooter pf = new PageFooter("总人数", sum);
	    List<PageFooter> ptList = new ArrayList();
	    ptList.add(pf);
	    Page p = new Page(pageInfoPatOn);
	    p.setFooter(ptList);
	    return p;
	  }
	  
	  @RequestMapping({"/patDwTjBy"})
	  @ResponseBody
	  public Page<TjEntity> patDwTjBy(@RequestParam("dw") String dw, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
	  {
	    PageHelper.startPage(page, rows);
	    List<TjEntity> patlistDw = this.patService.getPatDwTj(dw);
	    int sum = 0;
	    for (TjEntity tjEntity : patlistDw) {
	      sum += tjEntity.getNum();
	    }
	    PageInfo<TjEntity> pageInfoPatDw = new PageInfo(patlistDw);
	    PageFooter pf = new PageFooter("总人数", sum);
	    List<PageFooter> ptList = new ArrayList();
	    ptList.add(pf);
	    Page p = new Page(pageInfoPatDw);
	    p.setFooter(ptList);
	    return p;
	  }
	  
	  @RequestMapping({"/patDwTjCx"})
	  @ResponseBody
	  public Page<TjEntity> patDwTjCz(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
	  {
	    PageHelper.startPage(page, rows);
	    List<TjEntity> patlistDw = this.patService.getPatDwTj("");
	    int sum = 0;
	    for (TjEntity tjEntity : patlistDw) {
	      sum += tjEntity.getNum();
	    }
	    PageInfo<TjEntity> pageInfoPatDw = new PageInfo(patlistDw);
	    PageFooter pf = new PageFooter("总人数", sum);
	    List<PageFooter> ptList = new ArrayList();
	    ptList.add(pf);
	    Page p = new Page(pageInfoPatDw);
	    p.setFooter(ptList);
	    return p;
	  }
	@RequestMapping("/getPat")
	@ResponseBody
	public Page<Patients> getDev(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<Patients> patlist = patService.getPat();
		PageInfo<Patients> pageInfoPat = new PageInfo<Patients>(patlist);
		return new Page<Patients>(pageInfoPat);

	}

	@RequestMapping("/getPatBy")
	@ResponseBody
	 public Page<Patients> getDevBy(@RequestParam("account1") String account1, @RequestParam("uname1") String uname1, @RequestParam("sex1") String sex1, @RequestParam("province1") String province1, @RequestParam("city1") String city1, @RequestParam("area1") String area1, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
	  {
	    PageHelper.startPage(page, rows);
	    List<Patients> patByList = this.patService.getPatBy(account1, uname1, sex1, 
	      province1, city1, area1);
		PageInfo<Patients> pageInfoPatBy = new PageInfo<Patients>(patByList);
		return new Page<Patients>(pageInfoPatBy);
	}

	@RequestMapping("/addPat")
	@ResponseBody
	public ResponseEntity addPat(Patients pat) {
		// TODO Auto-generated method stub
		int count = 0;
		int count1 = 0;
		int count2 = 0;
		int checkRe = 0;

		if (pat.getId() == 0) {
			// 新增
			// 1验证设备号是否合法，2验证设备号是否已经绑定
			checkRe = checDevValidate(pat.getAccount());
			if (checkRe == 1) {
				return new ResponseEntity("NO", "设备号不存在!");
			} else if (checkRe == 2) {
				return new ResponseEntity("NO", "此设备已绑定!");
			}
			count = patService.addPat(pat);
			if (count == 1) {
				// 设置绑定
				count2 = devService.setBd("1", pat.getAccount(),
						String.valueOf(pat.getId()));// 是否可以获的mybatis的自动注入主键？
			}
			if (count2 == 1) {
				/*
				 * Bl bl = getBl(pat); count2 = 0; count2 =
				 * patService.addBl(bl); if (count2 == 1) { Zd zd = getZd(pat);
				 * zd.setdId(-1);// 后台增加的诊断 count2 = 0; count2 =
				 * patService.addZd(zd); if (count2 == 1) { count2 = 0; count2 =
				 * patService.upPatZdId(zd.getId(), pat.getId()); } }
				 */
				Map<String, String> m = new HashMap<String, String>();
				m.put("username", "pat" + String.valueOf(pat.getId()));
				m.put("password", PasswordUtil.generate("123456", "JYCS"));
				System.out.println(m.get("password"));
				if (!HttpClientUtil.HuanXinDo(m)) {
					return new ResponseEntity("false", "注册失败！");
				}
			}

		} else {
			// 先解绑旧设备 再绑定新的 最后修改病人
			String macBefore = devService.getMac(pat.getId());
			// 解绑设备
			// 判断设备号有无变化
			if (macBefore.equals(pat.getAccount())) {
				count = 1;// 解绑成功
				count1 = 1;// 绑定成功
			} else {
				if (!(macBefore.isEmpty()) && (macBefore != "")) {
					// 解绑旧设备
					count = devService.setBd("0", macBefore, "0");
				}
				if (count == 1) {
					// 绑定新设备
					count1 = devService.setBd("1", pat.getAccount(),
							String.valueOf(pat.getId()));
				}
			}
			// 修改病人
			if (count1 == 1) {
				count2 = patService.updatePat(pat);
				/*
				 * if (count2 == 1) { Bl bl = getBl(pat); count2 = 0; count2 =
				 * patService.upBl(bl); if (count2 == 1) { Zd zd = getZd(pat);
				 * count2 = 0; count2 = patService.upZd(zd); } }
				 */
			}
		}

		if (count2 == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	public int checDevValidate(String macId) {
		int isValidate = 0;
		List<Devices> devList = new ArrayList<Devices>();
		devList = devService.getDevBy(macId,"","");
		if (devList == null || devList.size() != 1) {
			isValidate = 1;
			// return new ResponseEntity("NO", "设备号不存在!");
		} else if (devList.get(0).getType() == 1) {
			isValidate = 2;
			// return new ResponseEntity("NO", "此设备已绑定!");
		}
		return isValidate;
	}

	public Bl getBl(Patients p) {
		Bl b = new Bl();
		b.setpId(p.getId());
		b.setCxbw(p.getCxbw());
		b.setCxdx(p.getCxdx());
		b.setLs(p.getLs());
		b.setHc(p.getHc());
		return b;

	}

	public Zd getZd(Patients p) {
		Zd z = new Zd();
		z.setpId(p.getId());
		z.setDwId(p.getDwId());
		z.setDwName(p.getDwName());
		z.setKsId(p.getKsId());
		z.setKsName(p.getKsName());
		z.setBq(p.getBq());
		z.setJbqk(p.getJbqk());
		z.setZd(p.getZd());
		return z;

	}

	@RequiresPermissions("pat:delete")
	@RequestMapping("/delPat")
	@ResponseBody
	public ResponseEntity deleteDev(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		int count2 = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		// 先解绑，在删除
		count = devService.batchJb(ids);
		if (count >= 1) {
			count2 = patService.deletePat(ids);
		}
		// 解绑成功，进行删除
		if (count2 >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// //////---------病人端-------------------
	/**
	 * 获取个体病人
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping("/getMe")
	@ResponseBody
	public Patients getMe(@RequestParam("account") String account) {
		Patients pat = patService.getMe(account);
		List<Zd> zdList = new ArrayList<Zd>();
		if (pat != null) {
			zdList = patService.getZdList(pat.getId() + "");
			if (zdList != null) {
				pat.setZdList(zdList);
			}
		}
		return pat;

	}

	// 修改病人视频时长 前台传来秒数
	@RequestMapping("/pat/upVedioTime")
	@ResponseBody
	public Map<String, String> upVedioTime(@RequestParam("id") int id,
			@RequestParam("videoTime") int videoTime) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		int count = 0;
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
		count = patService.upVedioTime(videoTime, id);
		if (count == 1) {
			map.put("status", "true");
			map.put("time", videoTime + "");
			return map;
		}
		map.put("status", "false");
		return map;
	}

	// 修改病人通话时长 前台传来秒数
	@RequestMapping("/pat/upTalkTime")
	@ResponseBody
	public Map<String, String> upTalkTime(@RequestParam("id") String id,
			@RequestParam("talkTime") String talkTime) {
		// TODO Auto-generated method stub
		Map<String, String> m = new HashMap<String, String>();
		int count = 0;
		if (id == null || id.equals("")) {
			id = "0";
		}
		if (talkTime == null || talkTime.equals("")) {
			talkTime = "0";
		}
		int talkTime2 = Integer.parseInt(talkTime);
		int id2 = Integer.parseInt(id);
		if (talkTime2 > 60) {
			int sy = talkTime2 % 60;
			if (sy > 30) {
				talkTime2 = (talkTime2 / 60) + 1;
			} else {
				talkTime2 = talkTime2 / 60;
			}
		} else if (talkTime2 > 30) {
			talkTime2 = 1;
		} else {
			talkTime2 = 0;
		}
		count = patService.upTalkTime(talkTime2, id2);
		if (count == 1) {
			m.put("status", "true");
			m.put("time", talkTime2 + "");
			return m;

		}
		m.put("status", "false");
		return m;
	}

	// 病人 修改病人个人地址
	@RequestMapping("/pat/upPatbyPat")
	@ResponseBody
	public ResponseEntity upPatbyPat(@RequestParam("id") int id,
			@RequestParam("province") String province,
			@RequestParam("city") String city,
			@RequestParam("area") String area, @RequestParam("addr") String addr) {
		// TODO Auto-generated method stub
		int count = 0;
		Patients pa = new Patients();
		pa.setProvince(province);
		pa.setCity(city);
		pa.setArea(area);
		pa.setAddr(addr);
		pa.setId(id);
		count = patService.upPatbyPat(pa);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}
}
