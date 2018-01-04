package com.jyss.mst.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.mst.entity.Doctors;
import com.jyss.mst.entity.Page;
import com.jyss.mst.entity.PageFooter;
import com.jyss.mst.entity.ResponseEntity;
import com.jyss.mst.entity.TjEntity;
import com.jyss.mst.entity.Xtcl;
import com.jyss.mst.filter.MySessionContext;
import com.jyss.mst.service.DoctorsService;
import com.jyss.mst.service.XljhService;
import com.jyss.mst.service.XtclService;
import com.jyss.mst.utils.CommTool;
import com.jyss.mst.utils.EmailUtil;
import com.jyss.mst.utils.HttpClientUtil;
import com.jyss.mst.utils.PasswordUtil;
import com.jyss.mst.utils.Utils;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

@Controller
public class DoctorsAction {
	@Autowired
	private DoctorsService dcoService;
	@Autowired
	private XljhService jhService;
	@Autowired
	private XtclService clService;
	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/doc")
	public String docTz() {
		return "doctors";
	}

	@RequestMapping("/docAddrTj")
	public String patAddrTjTz() {
		return "docAddrTj";
	}

	@RequestMapping("/docOnTj")
	public String patOnTjTz() {
		return "docOnTj";
	}

	// ----------医生端 接口--获取医生登录---------

	@RequestMapping("/doc/login")
	@ResponseBody
	public Map<String, Object> login(
			@RequestParam(value = "account", required = true) String account,
			@RequestParam(value = "password", required = true) String password) {
		// TODO Auto-generated method stub
		Map<String, Object> m = new HashMap<String, Object>();
		Map<String, String> contentMap = new HashMap<String, String>();
		Doctors d = new Doctors();
		d = dcoService.getDocsByAccount(account);
		if (d == null) {
			m.put("status", "false");
			m.put("message", "无此用户！");
			m.put("pwd", "");
			m.put("id", "");
			return m;

		}
		if (PasswordUtil.generate(password, d.getSalt())
				.equals(d.getPassword())) {
			// contentMap.put("pwd", d.getPassword());
			m.put("status", "true");
			m.put("message", "登录成功！");
			m.put("pwd", PasswordUtil.generate("666666", "JYCS"));
			m.put("id", d.getId() + "");
			return m;
		}
		m.put("status", "false");
		m.put("message", "密码错误！");
		m.put("pwd", "");
		m.put("id", "");
		return m;
	}

	// ------------医生端 接口--获取邮箱验证码------------

	@RequestMapping("/doc/sendEmail")
	@ResponseBody
	public Map<String, Object> sendEmail(@RequestParam("to") String to,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int count = 0;
		boolean isSucc = false;
		// Doctors d3 = new Doctors();
		List<Doctors> docList = new ArrayList<Doctors>();
		docList = dcoService.getDocsByEmail(to);
		// if (docList == null && docList.size() == 0) {
		// returnMap.put("status", "false");
		// returnMap.put("message", "此邮箱不存在！");
		// return returnMap;
		// } else if (docList.size() != 1) {
		// returnMap.put("status", "false");
		// returnMap.put("message", "此邮箱用户不唯一！");
		// return returnMap;
		// }
		// 企业邮箱
		List<Xtcl> clList = new ArrayList<Xtcl>();
		clList = clService.getClsCombox("email_type", "0");
		Xtcl cl = new Xtcl();
		cl = clList.get(0);
		// 授权码
		List<Xtcl> clList2 = new ArrayList<Xtcl>();
		clList2 = clService.getClsCombox("sq_type", "0");
		Xtcl cl2 = new Xtcl();
		cl2 = clList2.get(0);
		// 随机生成验证码
		String yzm = CommTool.getYzm();
		// 设置session
		HttpSession session = request.getSession();
		String sessionId = "";
		sessionId = session.getId();
		session.removeAttribute("MSTYZM");
		session.setAttribute("MSTYZM", yzm);
		session.removeAttribute("MSTYX");
		session.setAttribute("MSTYX", to);
		// 设置过期时间(S)
		session.setMaxInactiveInterval(10 * 60);
		// 发送邮件
		isSucc = EmailUtil.send(cl.getBz_value(), cl2.getBz_value(), to, "明斯特",
				"您此次操作的验证码的是：" + yzm + " ,请尽快在10分钟内完成验证");
		System.out.println(yzm);
		if (isSucc) {
			returnMap.put("status", "true");
			returnMap.put("message", "操作成功！");
			returnMap.put("sessionId", sessionId);
			return returnMap;
		}
		returnMap.put("status", "false");
		returnMap.put("message", "操作失败！");
		returnMap.put("sessionId", "");
		return returnMap;

	}

	// ------------医生端 接口--获取邮箱验证码------------

	@RequestMapping("/doc/checkEmailYzm")
	@ResponseBody
	public Map<String, Object> checkEmailYzm(@RequestParam("to") String to,
			@RequestParam("yzm") String yzm, HttpServletRequest request,
			@RequestParam("sessionId") String sessionId) {
		// TODO Auto-generated method stub
		Map<String, Object> reMap = new HashMap<String, Object>();
		// 获取session
		// HttpSession session = request.getSession();
		HttpSession session = MySessionContext.getSession(sessionId);
		String SessTo = (String) session.getAttribute("MSTYX");
		String SessToYzm = (String) session.getAttribute("MSTYZM");
		if (SessTo != null && !(SessTo.equals("")) && SessToYzm != null
				&& !(SessToYzm.equals(""))) {

			if (SessTo.equals(to)) {
				if (SessToYzm.equals(yzm)) {
					reMap.put("status", "true");
					reMap.put("message", "验证码正确！");
					return reMap;
				}
			}
		}
		reMap.put("status", "false");
		reMap.put("message", "验证码错误！");
		return reMap;

	}

	// ------------医生端 接口--获取短信验证码------------

	@RequestMapping("/doc/sendMsg")
	@ResponseBody
	public Map<String, Object> sendMsg(@RequestParam("to") String to,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		int count = 0;
		Doctors d4 = new Doctors();
		d4 = dcoService.getDocsByAccount(to);
		if (d4 == null) {
			retMap.put("status", "false");
			retMap.put("message", "无此用户！");
			return retMap;
		}
		// 随机生成验证码
		String sjyzm = CommTool.getYzm();
		// 设置session
		HttpSession session = request.getSession();
		String sessionId = "";
		sessionId = session.getId();
		session.removeAttribute("MSTSJYZM");
		session.setAttribute("MSTSJYZM", sjyzm);
		session.removeAttribute("MSTSJH");
		session.setAttribute("MSTSJH", to);
		// 设置过期时间(S)
		session.setMaxInactiveInterval(10 * 60);
		// 发送信息

		String re = HttpClientUtil.MsgDo(to, "您此次操作的验证码为： " + sjyzm
				+ " ,请尽快在10分钟内完成验证");
		if (re.equals("1")) {
			retMap.put("status", "true");
			retMap.put("message", "操作成功！");
			retMap.put("sessionId", sessionId);
			return retMap;
		}
		retMap.put("status", "false");
		retMap.put("message", "操作失败！");
		retMap.put("sessionId", "");
		return retMap;

	}

	// ------------医生端 接口--验证 短信验证码------------

	@RequestMapping("/doc/checkMsgYzm")
	@ResponseBody
	public Map<String, Object> checkMsgYzm(@RequestParam("to") String to,
			@RequestParam("yzm") String yzm, HttpServletRequest request,
			@RequestParam("sessionId") String sessionId) {
		// TODO Auto-generated method stub
		Map<String, Object> retuMap = new HashMap<String, Object>();
		// 获取session
		// HttpSession session = request.getSession();
		HttpSession session = MySessionContext.getSession(sessionId);
		String SessTo = (String) session.getAttribute("MSTSJH");
		String SessToYzm = (String) session.getAttribute("MSTSJYZM");
		if (SessTo != null && !(SessTo.equals("")) && SessToYzm != null
				&& !(SessToYzm.equals(""))) {
			if (SessTo.equals(to)) {
				if (SessToYzm.equals(yzm)) {
					retuMap.put("status", "true");
					retuMap.put("message", "验证码正确！");
					return retuMap;

				}
			}
		}
		retuMap.put("status", "false");
		retuMap.put("message", "验证码错误！");
		return retuMap;

	}

	// ------------医生端 接口--重置 ---密码-----------
	@RequestMapping("/doc/CzPwd")
	@ResponseBody
	public Map<String, Object> CzPwdByDoc(
			@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "newPwd", required = true) String newPwd) {
		// TODO Auto-generated method stub
		Map<String, Object> returMap = new HashMap<String, Object>();
		int count = 0;
		Doctors d2 = new Doctors();
		d2 = dcoService.getDocsById(id);
		if (d2 == null) {
			returMap.put("status", "false");
			returMap.put("message", "无此用户！");
			return returMap;

		}
		count = dcoService.upPwd(String.valueOf(id), newPwd);
		if (count != 1) {
			returMap.put("status", "false");
			returMap.put("message", "操作失败！");
			return returMap;
		}
		returMap.put("status", "true");
		returMap.put("message", "操作成功！");
		return returMap;
	}

	// ------------医生端 接口--重置 ---密码----手机号-------
	@RequestMapping("/doc/CzPwdByDocByAccount")
	@ResponseBody
	public Map<String, Object> CzPwdByDocByAccount(
			@RequestParam(value = "account", required = true) String account,
			@RequestParam(value = "newPwd", required = true) String newPwd) {
		// TODO Auto-generated method stub
		Map<String, Object> rMap = new HashMap<String, Object>();
		int count = 0;
		Doctors d2 = new Doctors();
		d2 = dcoService.getDocsByAccount(account);
		if (d2 == null) {
			rMap.put("status", "false");
			rMap.put("message", "无此用户！");
			return rMap;

		}
		count = dcoService.upPwd(String.valueOf(d2.getId()), newPwd);
		if (count != 1) {
			rMap.put("status", "false");
			rMap.put("message", "操作失败！");
			return rMap;

		}
		rMap.put("status", "true");
		rMap.put("message", "操作成功！");
		return rMap;
	}

	// ------------医生端 接口--重置 ---密码------邮箱-----
	@RequestMapping("/doc/CzPwdByEmail")
	@ResponseBody
	public Map<String, Object> CzPwdByEmail(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "newPwd", required = true) String newPwd) {
		// TODO Auto-generated method stub
		Map<String, Object> rrMap = new HashMap<String, Object>();
		int count = 0;
		Doctors d2 = new Doctors();
		List<Doctors> docEmailList = new ArrayList<Doctors>();
		docEmailList = dcoService.getDocsByEmail(email);
		if (docEmailList == null) {
			rrMap.put("status", "false");
			rrMap.put("message", "此邮箱不存在！");
			return rrMap;

		} else if (docEmailList.size() != 1) {
			rrMap.put("status", "false");
			rrMap.put("message", "此邮箱用户不唯一！");
			return rrMap;
		}
		d2 = docEmailList.get(0);
		count = dcoService.upPwd(String.valueOf(d2.getId()), newPwd);
		if (count != 1) {
			rrMap.put("status", "false");
			rrMap.put("message", "操作失败！");
			return rrMap;

		}
		rrMap.put("status", "true");
		rrMap.put("message", "操作成功！");
		return rrMap;

	}

	// ------------医生端 接口--修改密码-----------
	@RequestMapping("/doc/UpPwd")
	@ResponseBody
	public Map<String, Object> UpPwdByDoc(
			@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "newPwd", required = true) String newPwd) {
		// TODO Auto-generated method stub
		Map<String, Object> rreMap = new HashMap<String, Object>();
		int count = 0;
		Doctors d = new Doctors();
		d = dcoService.getDocsById(id);
		if (d == null) {
			rreMap.put("status", "false");
			rreMap.put("message", "无此用户！");
			return rreMap;

		}
		if (!(PasswordUtil.generate(password, d.getSalt()).equals(d
				.getPassword()))) {
			rreMap.put("status", "false");
			rreMap.put("message", "原密码错误！");
			return rreMap;

		}
		count = dcoService.upPwd(String.valueOf(id), newPwd);
		if (count != 1) {
			rreMap.put("status", "false");
			rreMap.put("message", "操作失败！");
			return rreMap;

		}
		rreMap.put("status", "true");
		rreMap.put("message", "操作成功！");
		return rreMap;

	}

	@RequestMapping("/doc/upDocByDoc")
	@ResponseBody
	public Map<String, Object> upDocByDoc(Doctors doc) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Doctors> dList = new ArrayList<Doctors>();
		boolean judgeResult = false;
		Map<String, Object> rretMap = new HashMap<String, Object>();
		// 修改
		// 判断手机 不存在=true,存在=false
		judgeResult = judgeAccount(doc.getAccount(), doc.getId());
		if (!judgeResult) {
			rretMap.put("status", "false");
			rretMap.put("message", "此账号已存在！");
			return rretMap;
		}
		count = dcoService.updateMyDoc(doc);
		if (count == 1) {
			rretMap.put("status", "true");
			rretMap.put("message", "操作成功！");
			return rretMap;

		}
		rretMap.put("status", "false");
		rretMap.put("message", "操作失败！");
		return rretMap;

	}

	// 判断手机号

	private boolean judgeAccount(String accountStr, int idInt) {
		boolean judgeRe = true;
		Doctors dc = null;
		List<Doctors> dList = new ArrayList<Doctors>();
		int isHas = 0;
		// 存在几个重复值 >=2返回false, 不存在返回true
		isHas = dcoService.accountPd(accountStr);
		if (isHas != 0) {
			if (isHas > 1) {
				judgeRe = false;
			} else {
				dList = dcoService.accountPdInfo(accountStr);
				dc = dList.get(0);
				if (dc.getId() != idInt) {
					judgeRe = false;
				}
			}
		}
		return judgeRe;
	}

	/**
	 * 
	 * @param dId
	 * 
	 * @return
	 */
	@RequestMapping("/doc/getAddDocs")
	@ResponseBody
	public List<Doctors> getAddDocs(
			@RequestParam(value = "dId", required = false) int dId) {
		List<Doctors> addocDoclist = dcoService.getAddDocs(dId);
		return addocDoclist;
	}

	/**
	 * 
	 * @param dId
	 * 
	 * @return
	 */
	@RequestMapping("/doc/getQxType")
	@ResponseBody
	public Map<String, String> getQxType(
			@RequestParam(value = "dId", required = true) int dId) {
		Map<String, String> m = new HashMap<String, String>();
		Doctors dd = dcoService.getDocsById(dId);
		if (dd == null) {
			m.put("type", "");
		} else {
			m.put("type", dd.getType() + "");
		}
		return m;
	}

	@RequestMapping("/doc/upLoginTime")
	@ResponseBody
	public void upLoginTime(
			@RequestParam(value = "dId", required = true) int dId) {
		Map<String, String> m = new HashMap<String, String>();
		Date dlsj = CommTool.getNowTimestamp();
		dcoService.upLoginTime(dId, dlsj);

	}

	/**
	 * 
	 * @param id
	 * @param uname
	 * @param dw_name
	 * @param ks_name
	 * @param job
	 * @param lableNames
	 * @return
	 */
	@RequestMapping("/doc/getDocByDoc")
	@ResponseBody
	public List<Doctors> getDocByDoc(
			@RequestParam(value = "id", required = false) int id,
			@RequestParam(value = "uname", required = false) String uname,
			@RequestParam(value = "dw_name", required = false) String dw_name,
			@RequestParam(value = "ks_name", required = false) String ks_name,
			@RequestParam(value = "job", required = false) String job,
			@RequestParam(value = "lableNames", required = false) String lableNames) {
		Xtcl xtcl = clService.getClsValue("sjxz_type", "1");
		List<Doctors> docDoclist = dcoService.getDocByDoc(id, uname, dw_name,
				ks_name, job, lableNames, xtcl.getBz_value());
		return docDoclist;
	}

	@RequestMapping("/doc/upQx")
	@ResponseBody
	public Map<String, Object> upDocQxByDoc(
			@RequestParam(value = "docId", required = true) int docId,
			@RequestParam(value = "dId", required = true) int dId) {
		// TODO Auto-generated method stub
		int count = 0;
		Map<String, Object> rretuMap = new HashMap<String, Object>();
		Doctors doc = dcoService.getDocsById(dId);
		if (doc.getType() != 1) {
			rretuMap.put("status", "false");
			rretuMap.put("message", "无权限修改！");
			return rretuMap;

		}
		Date xgsj = CommTool.getNowTimestamp();
		count = dcoService.upDocQxByDoc(dId, docId, 1, xgsj);
		if (count == 1) {
			rretuMap.put("status", "true");
			rretuMap.put("message", "操作成功！");
			return rretuMap;
		}
		rretuMap.put("status", "false");
		rretuMap.put("message", "操作失败！");
		return rretuMap;

	}

	@RequestMapping("/doc/addDoc")
	@ResponseBody
	public Map<String, Object> addDocByDoc(Doctors doc) {
		// TODO Auto-generated method stub
		int count = 0;
		Map<String, Object> rreturMap = new HashMap<String, Object>();
		Doctors d = dcoService.getDocsById(doc.getId());
		if (d == null) {
			rreturMap.put("status", "false");
			rreturMap.put("message", "当前操作用户不合法！");
			rreturMap.put("docId", "");
			return rreturMap;

		}
		if (d.getType() != 1) {
			rreturMap.put("status", "false");
			rreturMap.put("message", "无权限增加！");
			rreturMap.put("docId", "");
			return rreturMap;

		}
		doc.setUuid(CommTool.getUUID());
		doc.setSalt(CommTool.getSalt());
		// 新增
		int isHas = dcoService.accountPd(doc.getAccount());
		if (isHas != 0) {
			rreturMap.put("status", "false");
			rreturMap.put("message", "账号已存在！");
			rreturMap.put("docId", "");
			return rreturMap;
		}
		count = dcoService.addDocByDoc(doc);
		if (count == 1) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("username", "doc" + String.valueOf(doc.getId()));
			m.put("password", PasswordUtil.generate("666666", "JYCS"));
			if (HttpClientUtil.HuanXinDo(m)) {
				rreturMap.put("status", "true");
				rreturMap.put("message", "创建账号成功！");
				rreturMap.put("docId", doc.getId());
				return rreturMap;
			} else {
				rreturMap.put("status", "false");
				rreturMap.put("message", "环信注册失败！");
				rreturMap.put("docId", "");
				return rreturMap;
			}
		}
		rreturMap.put("status", "false");
		rreturMap.put("message", "创建账号失败！");
		rreturMap.put("docId", "");
		return rreturMap;
	}

	@RequestMapping("/doc/delDoc")
	@ResponseBody
	public Map<String, Object> delDocByDoc(
			@RequestParam(value = "docId", required = true) int docId,
			@RequestParam(value = "dId", required = true) int dId) {
		// TODO Auto-generated method stub
		int count = 0;
		Map<String, Object> rreturnMap = new HashMap<String, Object>();
		count = jhService.isHasYw(docId);
		if (count >= 1) {
			rreturnMap.put("status", "false");
			rreturnMap.put("message", "该医生已存在业务！");
			return rreturnMap;

		}
		// 判断操作医生dId 是否有权限1管理员
		Doctors d1 = new Doctors();
		d1 = dcoService.getDocsById(dId);
		if (d1 == null) {
			rreturnMap.put("status", "false");
			rreturnMap.put("message", "操作医生已不存在！");
			return rreturnMap;

		} else if (d1.getType() != 1) {
			rreturnMap.put("status", "false");
			rreturnMap.put("message", "当前医生无权限！");
			return rreturnMap;

		}
		// 判断该医生是否还存在 -----docId要删除的医生
		Doctors d = new Doctors();
		d = dcoService.getDocsById(docId);
		if (d == null) {
			rreturnMap.put("status", "false");
			rreturnMap.put("message", "该医生已不存在！");
			return rreturnMap;
		} else {
			if (d.getCzr() == dId) {
				count = 0;
				count = dcoService.delDocByDoc(docId);
			} else {
				rreturnMap.put("status", "false");
				rreturnMap.put("message", "无删除权限！");
				return rreturnMap;
			}
		}

		if (count == 1) {
			rreturnMap.put("status", "true");
			rreturnMap.put("message", "操作成功！");
			return rreturnMap;
		}
		rreturnMap.put("status", "false");
		rreturnMap.put("message", "操作失败！");
		return rreturnMap;
	}

	// form 协议传输
	@RequestMapping("/doc/upAvatar")
	@ResponseBody
	public Map<String, Object> upAvatar(
			@RequestParam(value = "dId", required = true) int dId,
			@RequestParam("myFile") MultipartFile myFile) {
		// TODO Auto-generated method stub
		int count = 0;
		Map<String, Object> mrMap = new HashMap<String, Object>();
		// MultipartFile myFile = news.getMyFile();
		String filename = myFile.getOriginalFilename();
		filename = filename.substring(filename.lastIndexOf("."));
		filename = "doc" + dId + filename;
		String filePath = request.getSession().getServletContext()
				.getRealPath("/");
		int index = filePath.indexOf("SSM");
		filePath = filePath.substring(0, index) + "uploadImg/" + filename;
		if (myFile.getSize() > 5400000) {
			mrMap.put("status", "false");
			mrMap.put("message", "文件过大，应不超过5M!");
			return mrMap;
		}
		if (!Utils.saveUpload(myFile, filePath)) {
			mrMap.put("status", "false");
			mrMap.put("message", "文件上传失败！");
			return mrMap;
		}
		// 字符串截取 头像路径
		filePath = filePath.substring(filePath.indexOf("uploadImg"));
		count = dcoService.upDocAvatar(dId, filePath,
				CommTool.getNowTimestamp());
		if (count == 1) {
			mrMap.put("status", "true");
			mrMap.put("message", "操作成功！");
			return mrMap;
		}
		mrMap.put("status", "false");
		mrMap.put("message", "操作失败！");
		return mrMap;
	}

	@RequestMapping("/doc/upAvatarByIo")
	@ResponseBody
	public Map<String, Object> upAvatarByIo(
			@RequestParam(value = "dId", required = true) int dId,
			// @RequestParam("myFile") MultipartFile myFile,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int count = 0;
		// Base64.decode(photo);
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String photo = request.getParameter("myFile");
		Map<String, Object> mrMap = new HashMap<String, Object>();
		String filePath = request.getSession().getServletContext()
				.getRealPath("/");
		int index = filePath.indexOf("SSM");
		filePath = filePath.substring(0, index) + "uploadImg/";
		try {

			// 对base64数据进行解码
			byte[] decode = Base64.decode(photo);
			if (decode.length > 5400000) {
				mrMap.put("status", "false");
				mrMap.put("message", "文件过大，应不超过5M!");
				return mrMap;
			}
			filePath = filePath + dId + "decode.jpg";
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
			out.write(decode);
			out.flush();
			out.close();
		} catch (Base64DecodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mrMap.put("status", "false");
			mrMap.put("message", "文件上传失败！");
			return mrMap;
		}

		// 字符串截取 头像路径
		filePath = filePath.substring(filePath.indexOf("uploadImg"));
		count = dcoService.upDocAvatar(dId, filePath,
				CommTool.getNowTimestamp());
		if (count == 1) {
			mrMap.put("status", "true");
			mrMap.put("message", "数据更新成功！");
			return mrMap;
		}
		mrMap.put("status", "false");
		mrMap.put("message", "数据更新失败！");
		return mrMap;
	}

	// ----------病人端接口-----------

	/**
	 * 医生详情主页
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/pat/getDocInfo")
	@ResponseBody
	public List<Doctors> getDocInfo(@RequestParam("id") int id) {
		Xtcl xt = clService.getClsValue("sjxz_type", "1");
		List<Doctors> docList = dcoService.getDocInfo(id, xt.getBz_value());
		return docList;
	}

	/**
	 * 病人端搜索医生
	 * 
	 * @param searchBy
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/getDocsByPat")
	@ResponseBody
	public List<Doctors> getDocsByPat(@RequestParam("searchBy") String searchBy) {
		// TODO Auto-generated method stub
		// PageHelper.startPage(page, rows);// 分页语句
		List<Doctors> docNamelist = dcoService.getDocsByPat(searchBy);
		// PageInfo<Doctors> pageInfoDocByPat = new
		// PageInfo<Doctors>(docNamelist);
		return docNamelist;
	}

	@RequestMapping("/getDocs")
	@ResponseBody
	public Page<Doctors> getDocs(
			@RequestParam(value = "page", required = true, defaultValue = "1") int page,
			@RequestParam(value = "rows", required = true, defaultValue = "20") int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<Doctors> doclist = dcoService.getDocs();
		PageInfo<Doctors> pageInfoDoc = new PageInfo<Doctors>(doclist);
		return new Page<Doctors>(pageInfoDoc);

	}

	@RequestMapping("/getSearch")
	@ResponseBody
	public Page<Doctors> getDocsByAccountAndName(
			@RequestParam("account") String account,
			@RequestParam("uname") String uname,@RequestParam("type") String type,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<Doctors> docNamelist = dcoService.getDocsByAccountAndName(account,
				uname,type);
		PageInfo<Doctors> pageInfoDocBy = new PageInfo<Doctors>(docNamelist);
		return new Page<Doctors>(pageInfoDocBy);
	}

	@RequestMapping("/addDoc")
	@ResponseBody
	public Map<String, Object> addDoc(Doctors doc) {
		// TODO Auto-generated method stub
		int count = 0;
		boolean isexist = false;
		Map<String, Object> msrMap = new HashMap<String, Object>();
		if (doc.getId() == 0) {
			// 新增
			int isHas = dcoService.accountPd(doc.getAccount());
			if (isHas != 0) {
				msrMap.put("status", "false");
				msrMap.put("message", "账号已存在！");
				return msrMap;
			}
			count = dcoService.addDoc(doc);
			if (count == 1) {
				Map<String, String> m = new HashMap<String, String>();
				m.put("username", "doc" + String.valueOf(doc.getId()));
				m.put("password", PasswordUtil.generate("666666", "JYCS"));
				if (HttpClientUtil.HuanXinDo(m)) {
					msrMap.put("status", "true");
					// msrMap.put("message", "环信注册成功！");
					msrMap.put("message", "注册成功！");
					return msrMap;

				} else {
					msrMap.put("status", "false");
					msrMap.put("message", "环信注册失败！");
					return msrMap;
				}
			}
		} else {
			// 修改
			// 判断手机 不存在=true,存在=false
			isexist = judgeAccount(doc.getAccount(), doc.getId());
			if (!isexist) {
				msrMap.put("status", "false");
				msrMap.put("message", "此账号已存在！");
				return msrMap;
			}
			count = dcoService.updateDoc(doc);
		}

		if (count == 1) {
			msrMap.put("status", "true");
			msrMap.put("message", "操作成功！");
			return msrMap;
		}
		msrMap.put("status", "false");
		msrMap.put("message", "操作失败！");
		return msrMap;
	}

	@RequiresPermissions("doc:delete")
	@RequestMapping("/delDoc")
	@ResponseBody
	public ResponseEntity deleteDoc(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = dcoService.deleteDoc(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequiresPermissions("doc:jy")
	@RequestMapping("/jyDoc")
	@ResponseBody
	public ResponseEntity jyDoc(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = dcoService.jyDoc(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequiresPermissions("doc:qx")
	@RequestMapping("/qxDoc")
	@ResponseBody
	public ResponseEntity qxDoc(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = dcoService.qxDoc(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequiresPermissions("doc:upPwd")
	@RequestMapping("/upPwd")
	@ResponseBody
	public ResponseEntity upPwd(String id, String pwd) {
		// TODO Auto-generated method stub
		int count = 0;
		if ((!pwd.equals("")) && pwd != null) {
			if ((!id.equals("")) && id != null) {
				count = dcoService.upPwd(id, pwd);
			}
		}
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// //*************后台统计************************////
	@RequestMapping({"/docAddrTjBy"})
	  @ResponseBody
	  public Page<TjEntity> docAddrTjBy(@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
	  {
	    PageHelper.startPage(page, rows);
	    List<TjEntity> doclistAddr = this.dcoService.getDocAddrTj(province, city);
	    int sum = 0;
	    for (TjEntity tjEntity : doclistAddr) {
	      sum += tjEntity.getNum();
	    }
	    PageInfo<TjEntity> pageInfoPatAddr = new PageInfo(doclistAddr);
	    PageFooter pf = new PageFooter("总人数", sum);
	    List<PageFooter> ptList = new ArrayList();
	    ptList.add(pf);
	    Page p = new Page(pageInfoPatAddr);
	    p.setFooter(ptList);
	    return p;
	  }
	  
	  @RequestMapping({"/docAddrTjCx"})
	  @ResponseBody
	  public Page<TjEntity> docAddrTjCx(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
	  {
	    PageHelper.startPage(page, rows);
	    List<TjEntity> doclistAddr = this.dcoService.getDocAddrTj("", "");
	    int sum = 0;
	    for (TjEntity tjEntity : doclistAddr) {
	      sum += tjEntity.getNum();
	    }
	    
	    PageInfo<TjEntity> pageInfoPatAddr = new PageInfo(doclistAddr);
	    PageFooter pf = new PageFooter("总人数", sum);
	    List<PageFooter> ptList = new ArrayList();
	    ptList.add(pf);
	    Page p = new Page(pageInfoPatAddr);
	    p.setFooter(ptList);
	    return p;
	  }
	  
	  @RequestMapping({"/docOnTjBy"})
	  @ResponseBody
	  public Page<TjEntity> docOnTjBy(@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
	  {
	    String loginXz = getLoginXz();
	    PageHelper.startPage(page, rows);
	    List<TjEntity> patlistOn = this.dcoService.getDocOnTj(province, city, 
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
	  
	  @RequestMapping({"/docOnTjCx"})
	  @ResponseBody
	  public Page<TjEntity> docOnTjCx(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
	  {
	    String loginXz = getLoginXz();
	    PageHelper.startPage(page, rows);
	    List<TjEntity> patlistOn = this.dcoService.getDocOnTj("", "", loginXz);
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
}
