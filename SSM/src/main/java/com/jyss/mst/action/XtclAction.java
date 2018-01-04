package com.jyss.mst.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.mst.entity.Page;
import com.jyss.mst.entity.ResponseEntity;
import com.jyss.mst.entity.Xtcl;
import com.jyss.mst.service.XtclService;
import com.jyss.mst.utils.Utils;

@Controller
public class XtclAction {
	@Autowired
	private XtclService clService;

	@RequestMapping("/cl")
	public String docTz() {
		return "xtcl";
	}

	@RequestMapping("/getCls")
	@ResponseBody
	public Page<Xtcl> getCls(int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<Xtcl> clList = clService.getCls();
		PageInfo<Xtcl> pageInfo = new PageInfo<Xtcl>(clList);
		return new Page<Xtcl>(pageInfo);
	}

	@RequestMapping("/getClsBy")
	@ResponseBody
	public Page<Xtcl> getClsBy(@RequestParam(value="bz_type", required=false) String bz_type, @RequestParam(value="bz_value", required=false) String bz_value, @RequestParam(value="ps", required=false) String ps, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
	  {
	    PageHelper.startPage(page, rows);
	    List<Xtcl> clListBy = this.clService.getClsBy(bz_type, bz_value, ps);
		PageInfo<Xtcl> pageInfoBy = new PageInfo<Xtcl>(clListBy);
		return new Page<Xtcl>(pageInfoBy);
	}

	@RequestMapping("/getClsCo")
	@ResponseBody
	public List<Xtcl> getClsCombox(
			@RequestParam(value = "bz_type", required = true) String bz_type,
			@RequestParam(value = "pid", required = false) String pid) {
		// TODO Auto-generated method stub
		List<Xtcl> clListCo = clService.getClsCombox(bz_type, pid);
		return clListCo;
	}

	@RequestMapping("/addCl")
	@ResponseBody
	public ResponseEntity addDoc(Xtcl cl) {
		// TODO Auto-generated method stub
		int count = 0;
		if (cl.getId() == 0) {
			// 洗新增
			count = clService.addCl(cl);
		} else {
			// 修改
			count = clService.updateCl(cl);
		}

		if (count == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delCl")
	@ResponseBody
	public ResponseEntity deleteDoc(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = clService.deleteCl(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功!");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// /***************安卓端*********单位--科室--职位-标签-下拉集合*************///
	@RequestMapping("/doc/getClMap")
	@ResponseBody
	public Map<String, List<Xtcl>> getClMap() {
		// TODO Auto-generated method stub
		Map<String, List<Xtcl>> m = new LinkedHashMap<String, List<Xtcl>>();
		List<Xtcl> dwClList = clService.getClsCombox("dw_type", "");
		m.put("DW", dwClList);
		List<Xtcl> ksClList = clService.getClsCombox("ks_type", "");
		m.put("KS", ksClList);
		List<Xtcl> jobClList = clService.getClsCombox("job_type", "");
		m.put("JOB", jobClList);
		List<Xtcl> lableClList = clService.getClsCombox("lable_type", "");
		m.put("LABLE", lableClList);
		return m;
	}

}
