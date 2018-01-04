package com.jyss.mst.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.mst.entity.Cwzf;
import com.jyss.mst.entity.Page;
import com.jyss.mst.entity.ResponseEntity;
import com.jyss.mst.service.CwzfService;
import com.jyss.mst.utils.Utils;

@Controller
public class CwzfAction {

	@Autowired
	private CwzfService cwService;

	@RequestMapping("/cwzf")
	public String cwzfTz() {
		return "cwzf";
	}

	@RequestMapping("/getCw")
	@ResponseBody
	public Page<Cwzf> getCW(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		PageHelper.startPage(page, rows);// 分页语句
		List<Cwzf> cwList = cwService.getCw();
		PageInfo<Cwzf> pageInfoCw = new PageInfo<Cwzf>(cwList);
		return new Page<Cwzf>(pageInfoCw);

	}

	@RequestMapping("/getCwBy")
	@ResponseBody
	public Page<Cwzf> getCwBy(@RequestParam("account") String account,
			@RequestParam("czType") String czType,
			@RequestParam("kssj") String kssj,
			@RequestParam("jssj") String jssj,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<Cwzf> cwByList = cwService.getCwBy(account, kssj, jssj, czType);
		PageInfo<Cwzf> pageInfoCwBy = new PageInfo<Cwzf>(cwByList);
		return new Page<Cwzf>(pageInfoCwBy);
	}

	@RequestMapping("/addCw")
	@ResponseBody
	public ResponseEntity addCW(Cwzf cw) {
		// TODO Auto-generated method stub
		int count = 0;
		cw.setAccount("111");// 充值账户
		cw.setZhType(1);// '充值账户类型 1病人 2医生 3单位'
		cw.setCzTime(100);// 充值时间
		cw.setCzMoney(100);// 充值对应金额
		cw.setCzType(2);// 充值类型 1 基础付费设置 2视频套餐设置 3通话套餐设置'
		cw.setZfType(1);// '支付类型 1支付宝 2微信 3其他',
		cw.setZfAccount("1111");// 支付账号
		cw.setZfUname("手拉手");// 支付昵称
		count = cwService.addCw(cw);
		if (count == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delCw")
	@ResponseBody
	public ResponseEntity delCw(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = cwService.deleteCw(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

}
