package com.jyss.mst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.mst.entity.DocDoc;
import com.jyss.mst.mapper.DocDocMapper;
import com.jyss.mst.service.DocDocService;
import com.jyss.mst.utils.CommTool;

@Service
public class DocDocServiceImpl implements DocDocService {
	@Autowired
	private DocDocMapper docDocMapper;

	@Override
	public List<DocDoc> getDGroupByDoc(String dId) {
		// TODO Auto-generated method stub
		return docDocMapper.getDGroupByDoc(dId);
	}

	@Override
	public List<DocDoc> getDFriendByDoc(String dId) {
		// TODO Auto-generated method stub
		return docDocMapper.getDFriendByDoc(dId);
	}

	@Override
	public int addDdByDoc(DocDoc dd) {
		// TODO Auto-generated method stub
		dd.setCreatedAt(CommTool.getNowTimestamp());
		dd.setIsRead(0);
		dd.setType(1);// 主动先操作顺序
		return docDocMapper.addDdByDoc(dd);
	}

	@Override
	public int docAgreeFriend(DocDoc dd) {
		// TODO Auto-generated method stub
		dd.setCreatedAt(CommTool.getNowTimestamp());
		return docDocMapper.docAgreeFriend(dd);
	}

	@Override
	public int updateDdByDoc(DocDoc dd) {
		// TODO Auto-generated method stub
		dd.setLastModifyTime(CommTool.getNowTimestamp());
		return docDocMapper.updateDdByDoc(dd);
	}

	@Override
	public int delDocGroup(String dId, String docId, String docGroupName) {
		// TODO Auto-generated method stub
		return docDocMapper.delDocGroup(dId, docId, docGroupName);
	}

	@Override
	public int delRepeat(String dId, String docId) {
		// TODO Auto-generated method stub
		return docDocMapper.delRepeat(dId, docId);
	}

	@Override
	public int delRefuse(String dId, String docId) {
		// TODO Auto-generated method stub
		return docDocMapper.delRefuse(dId, docId);
	}

	@Override
	public int updateDdStatus(DocDoc dd) {
		// TODO Auto-generated method stub
		dd.setLastModifyTime(CommTool.getNowTimestamp());
		return docDocMapper.updateDdStatus(dd);
	}

	@Override
	public int isExistDoc(String dId, String docId) {
		// TODO Auto-generated method stub
		return docDocMapper.isExistDoc(dId, docId);
	}

	@Override
	public int upGroupByDel(String dId, String docGroupName, String newGroupName) {
		// TODO Auto-generated method stub
		return docDocMapper.upGroupByDel(dId, docGroupName, newGroupName);
	}

	@Override
	public int upIsRead(DocDoc dd) {
		// TODO Auto-generated method stub
		dd.setIsRead(1);
		dd.setLastModifyTime(CommTool.getNowTimestamp());
		return docDocMapper.upIsRead(dd);
	}

	@Override
	public List<DocDoc> getJsonDocGroup(String dId) {
		// TODO Auto-generated method stub
		return docDocMapper.getJsonDocGroup(dId);
	}

	@Override
	public List<DocDoc> getJsonDocFriend(String dId, String docGroupName) {
		// TODO Auto-generated method stub
		return docDocMapper.getJsonDocFriend(dId, docGroupName);
	}

	@Override
	public int upDocYzByDoc(DocDoc dd) {
		// TODO Auto-generated method stub
		dd.setLastModifyTime(CommTool.getNowTimestamp());
		return docDocMapper.upDocYzByDoc(dd);
	}

}
