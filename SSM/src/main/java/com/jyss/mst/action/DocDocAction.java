package com.jyss.mst.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyss.mst.entity.DocDoc;
import com.jyss.mst.entity.ResponseEntity;
import com.jyss.mst.service.DocDocService;

@Controller
public class DocDocAction {
	@Autowired
	private DocDocService docDocService;

	// 获取医生 获取 医生 好友分组
	@RequestMapping("/doc/getDGroupByDoc")
	@ResponseBody
	public List<DocDoc> getDGroupByDoc(
			@RequestParam(value = "dId", required = true) String dId) {
		List<DocDoc> ddList = docDocService.getDGroupByDoc(dId);
		return ddList;
	}

	// 获取医生 获取 医生 好友 列表
	@RequestMapping("/doc/getDFriendByDoc")
	@ResponseBody
	public List<DocDoc> getDFriendByDoc(
			@RequestParam(value = "dId", required = true) String dId) {
		List<DocDoc> ddFList = docDocService.getDFriendByDoc(dId);
		return ddFList;
	}

	// 医生 删除 医生分组 若里面有医生 则医生修改组名 --组名为第一个组
	@RequestMapping("/doc/delDocGroup")
	@ResponseBody
	public ResponseEntity delDocGroup(
			@RequestParam(value = "dId", required = true) String dId,
			@RequestParam(value = "docGroupName", required = true) String docGroupName) {
		// TODO Auto-generated method stub
		int count = 0;
		int count1 = 0;
		String firstDocGroupName = docDocService.getJsonDocGroup(dId).get(0)
				.getDocGroupName();
		// 默认从下往上删除 -- 但是 --如果从上往下删除 则会出现删除失败的原因
		if (docGroupName.equals(firstDocGroupName)) {
			if (docDocService.getJsonDocGroup(dId).size() > 1) {
				firstDocGroupName = docDocService.getJsonDocGroup(dId).get(1)
						.getDocGroupName();
			} else {
				firstDocGroupName = "我的好友";
			}

		}

		// 先删除为0分组
		count = docDocService.delDocGroup(dId, "0", docGroupName);
		// 在修改里面的医生组名
		count1 = docDocService.upGroupByDel(dId, docGroupName,
				firstDocGroupName);
		if (count + count1 >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}

		return new ResponseEntity("false", "操作失败！");
	}

	// 医生 删除医生 --好友关系
	@RequestMapping("/doc/delDocRelation")
	@ResponseBody
	public ResponseEntity delDocRelation(
			@RequestParam(value = "dId", required = true) String dId,
			@RequestParam(value = "docId", required = true) String docId) {
		// TODO Auto-generated method stub
		int count = 0;
		int count1 = 0;
		count = docDocService.delDocGroup(dId, docId, "");
		count1 = docDocService.delDocGroup(docId, dId, "");
		if (count + count1 >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生 医生添加分组
	@RequestMapping("/doc/addDdByDoc")
	@ResponseBody
	public ResponseEntity addDdByDoc(
			@RequestParam(value = "dId", required = true) int dId,
			@RequestParam(value = "docGroupName", required = true) String docGroupName) {
		// TODO Auto-generated method stub
		int count = 0;
		DocDoc dd = new DocDoc();
		dd.setdId(dId);
		dd.setDocGroupName(docGroupName);
		dd.setStatus(2);// 新增分组可以即可看到
		count = docDocService.addDdByDoc(dd);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生 医生添加好友
	@RequestMapping("/doc/addFriends")
	@ResponseBody
	public ResponseEntity addFriendsByDoc(
			@RequestParam(value = "dId", required = true) String dId,
			@RequestParam(value = "docId", required = true) String docId,
			@RequestParam(value = "docGroupName", required = true) String docGroupName,
			@RequestParam(value = "docPs", required = true) String docPs,
			@RequestParam(value = "yzInfo", required = true) String yzInfo) {
		// TODO Auto-generated method stub
		int count = 0;
		DocDoc dd2 = new DocDoc();
		dd2.setdId(Integer.parseInt(dId));
		dd2.setDocId(Integer.parseInt(docId));
		dd2.setDocGroupName(docGroupName);
		dd2.setDocPs(docPs);
		dd2.setYzInfo(yzInfo);
		dd2.setStatus(2);// 新增 添加须同意
		// 先判断是否该医生好友
		count = docDocService.isExistDoc(dId, docId);
		if (count >= 1) {
			return new ResponseEntity("false", "已存在该好友！");
		}
		count = 0;
		// 删除多余好友请求 防止重复添加
		// docDocService.delRepeat(dId, docId);
		count = docDocService.addDdByDoc(dd2);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生=== 医生=== 转移至其他分组
	@RequestMapping("/doc/docyzfz")
	@ResponseBody
	public ResponseEntity updateYzByDoc(DocDoc dd) {
		// TODO Auto-generated method stub
		int count = 0;
		count = docDocService.upDocYzByDoc(dd);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生 医生 修改分组
	@RequestMapping("/doc/upDdByDoc")
	@ResponseBody
	public ResponseEntity upDdByDoc(
			@RequestParam(value = "dId", required = true) int dId,
			@RequestParam(value = "newGroupName", required = true) String newGroupName,
			@RequestParam(value = "docGroupName", required = true) String docGroupName) {
		// TODO Auto-generated method stub
		int count = 0;
		DocDoc dd3 = new DocDoc();
		dd3.setdId(dId);
		dd3.setNewGroupName(newGroupName);
		dd3.setDocGroupName(docGroupName);
		count = docDocService.updateDdByDoc(dd3);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生 医生 同意 添加好友
	@RequestMapping("/doc/agreeFriends")
	@ResponseBody
	public ResponseEntity agreeFriends(
			@RequestParam(value = "dId", required = true) int dId,
			@RequestParam(value = "docId", required = true) int docId) {
		// TODO Auto-generated method stub
		int count = 0;
		DocDoc dd4 = new DocDoc();
		dd4.setdId(dId);
		dd4.setDocId(docId);
		dd4.setType(2);
		dd4.setStatus(2);
		count = docDocService.docAgreeFriend(dd4);
		if (count == 1) {
			dd4.setdId(docId);
			dd4.setDocId(dId);
			count = 0;
			count = docDocService.updateDdStatus(dd4);
			if (count == 1) {
				return new ResponseEntity("true", "操作成功！");
			}
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生 医生 拒绝 添加好友
	@RequestMapping("/doc/refuseFriends")
	@ResponseBody
	public ResponseEntity refuseFriends(
			@RequestParam(value = "dId", required = true) int dId,
			@RequestParam(value = "docId", required = true) int docId) {
		// TODO Auto-generated method stub
		int count = 0;
		DocDoc dd5 = new DocDoc();
		dd5.setdId(dId);
		dd5.setDocId(docId);
		dd5.setType(2);
		dd5.setStatus(3);
		// 删除多余拒绝信息
		docDocService.delRefuse(String.valueOf(docId), String.valueOf(dId));
		count = docDocService.docAgreeFriend(dd5);
		if (count == 1) {
			dd5.setdId(docId);
			dd5.setDocId(dId);
			count = 0;
			count = docDocService.updateDdStatus(dd5);
			if (count == 1) {
				return new ResponseEntity("true", "操作成功！");
			}
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 医生 消息 是否已读
	@RequestMapping("/doc/upIsRead")
	@ResponseBody
	public ResponseEntity upIsRead(
			@RequestParam(value = "dId", required = true) int dId,
			@RequestParam(value = "docId", required = true) int docId,
			@RequestParam(value = "bz", required = true) int bz) {
		// bz 1 我加别人的消息 2 别人加我的信息
		// TODO Auto-generated method stub
		int count = 0;
		DocDoc dd6 = new DocDoc();
		if (bz == 1) {
			dd6.setdId(dId);
			dd6.setDocId(docId);
			count = docDocService.upIsRead(dd6);
		}
		// 2 别人加我的信息
		if (bz == 2) {
			dd6.setdId(docId);
			dd6.setDocId(dId);
			count = docDocService.upIsRead(dd6);
		}
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}
}
