package com.jyss.mst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.mst.entity.PatDoc;
import com.jyss.mst.mapper.PatDocMapper;
import com.jyss.mst.service.PatDocService;
import com.jyss.mst.utils.CommTool;

@Service
public class PatDocServiceImpl implements PatDocService {
	@Autowired
	private PatDocMapper pdMapper;

	@Override
	public List<PatDoc> getPGroup(String pId) {
		// TODO Auto-generated method stub
		return pdMapper.getPGroup(pId);
	}

	@Override
	public List<PatDoc> getDGroup(String docId) {
		// TODO Auto-generated method stub
		return pdMapper.getDGroup(docId);
	}

	@Override
	public int addPd(PatDoc pd) {
		// TODO Auto-generated method stub
		// pd.setStatus(2);
		pd.setCreatedAt(CommTool.getNowTimestamp());
		return pdMapper.addPd(pd);
	}

	@Override
	public int addPdByDoc(PatDoc pd) {
		// TODO Auto-generated method stub
		pd.setType(1);
		pd.setCreatedAt(CommTool.getNowTimestamp());
		return pdMapper.addPdByDoc(pd);
	}

	@Override
	public int updatePdByPat(PatDoc pd) {
		// TODO Auto-generated method stub
		pd.setLastModifyTime(CommTool.getNowTimestamp());
		return pdMapper.updatePdByPat(pd);
	}

	@Override
	public int updateYzByPat(PatDoc pd) {
		// TODO Auto-generated method stub
		pd.setLastModifyTime(CommTool.getNowTimestamp());
		return pdMapper.updateYzByPat(pd);
	}

	@Override
	public int updateScByPat(PatDoc pd) {
		// TODO Auto-generated method stub
		pd.setLastModifyTime(CommTool.getNowTimestamp());
		return pdMapper.updateScByPat(pd);
	}

	@Override
	public int updatePdByDoc(PatDoc pd) {
		// TODO Auto-generated method stub
		pd.setLastModifyTime(CommTool.getNowTimestamp());
		return pdMapper.updatePdByDoc(pd);
	}

	@Override
	public int updateYzByDoc(PatDoc pd) {
		// TODO Auto-generated method stub
		pd.setLastModifyTime(CommTool.getNowTimestamp());
		return pdMapper.updateYzByDoc(pd);
	}

	@Override
	public int updateScByDoc(PatDoc pd) {
		// TODO Auto-generated method stub
		pd.setLastModifyTime(CommTool.getNowTimestamp());
		return pdMapper.updateScByDoc(pd);
	}

	@Override
	public int deletePd(List<Long> ids) {
		// TODO Auto-generated method stub
		return pdMapper.deletePd(ids);
	}

	@Override
	public List<PatDoc> getPFriend(String pId, String groupName) {
		// TODO Auto-generated method stub
		return pdMapper.getPFriend(pId, groupName);
	}

	@Override
	public List<PatDoc> getDFriend(String docId) {
		// TODO Auto-generated method stub
		return pdMapper.getDFriend(docId);
	}

	@Override
	public int delDocGroup(String docId, String pId, String groupName) {
		// TODO Auto-generated method stub
		return pdMapper.delDocGroup(docId, pId, groupName);
	}

	// 分享医生
	@Override
	public int docAddFx(PatDoc pd) {
		// TODO Auto-generated method stub
		pd.setCreatedAt(CommTool.getNowTimestamp());
		pd.setType(1);
		pd.setStatus(5);
		return pdMapper.docAddFx(pd);
	}

	@Override
	public int upDocFx(PatDoc pd) {
		// TODO Auto-generated method stub
		pd.setLastModifyTime(CommTool.getNowTimestamp());
		return pdMapper.upDocFx(pd);
	}

	@Override
	public int upDocFxRead(PatDoc pd) {
		// TODO Auto-generated method stub
		pd.setStatus(1);
		pd.setLastModifyTime(CommTool.getNowTimestamp());
		return pdMapper.upDocFxRead(pd);
	}

	@Override
	public List<PatDoc> getPVTime(String docId, String videoTime) {
		// TODO Auto-generated method stub
		return pdMapper.getPVTime(docId, videoTime);
	}

	@Override
	public List<PatDoc> getPTTime(String docId, String talkTime) {
		// TODO Auto-generated method stub
		return pdMapper.getPTTime(docId, talkTime);
	}

	@Override
	public int upPdRelation(PatDoc pd) {
		// TODO Auto-generated method stub
		pd.setIsRead(1);
		pd.setLastModifyTime(CommTool.getNowTimestamp());
		return pdMapper.upPdRelation(pd);
	}

	@Override
	public List<PatDoc> getJsonDGroup(String docId) {
		// TODO Auto-generated method stub
		return pdMapper.getJsonDGroup(docId);
	}

	@Override
	public List<PatDoc> getJsonDFriend(String docId, String docGroupName) {
		// TODO Auto-generated method stub
		return pdMapper.getJsonDFriend(docId, docGroupName);
	}

	@Override
	public int delPatByDoc(int docId, int pId) {
		// TODO Auto-generated method stub
		return pdMapper.delPatByDoc(docId, pId);
	}

	@Override
	public List<PatDoc> getJsonDSingleFriend(String docId, String pId) {
		// TODO Auto-generated method stub
		return pdMapper.getJsonDSingleFriend(docId, pId);
	}

}
