package com.jyss.mst.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.mst.entity.Doctors;
import com.jyss.mst.entity.TjEntity;
import com.jyss.mst.mapper.DoctorsMapper;
import com.jyss.mst.mapper.XtclMapper;
import com.jyss.mst.service.DoctorsService;
import com.jyss.mst.utils.CommTool;
import com.jyss.mst.utils.PasswordUtil;
import com.jyss.mst.utils.Utils;

@Service
public class DoctorsServiceImpl implements DoctorsService {

	@Autowired
	private DoctorsMapper docMapper;
	@Autowired
	private XtclMapper xtclMapper;

	@Override
	public Doctors getDocsById(int id) {
		// TODO Auto-generated method stub
		return docMapper.getDocsById(id);
	}

	@Override
	public List<Doctors> getDocs() {
		// TODO Auto-generated method stub
		return docMapper.getDocs();
	}

	public String getValue(String bz_type, String id) {
		if (xtclMapper.getClsValue(bz_type, id) != null) {
			String nameVal = xtclMapper.getClsValue(bz_type, id).getBz_value();
			if (nameVal.isEmpty() || nameVal.equals("")) {
				return id;
			}
			return nameVal;
		} else {
			return id;
		}

	}

	/**
	 * 新增修改统一处理
	 * 
	 * @param doc
	 * @return
	 */
	public Doctors docCl(Doctors doc) {
		doc.setNick(doc.getUname());
		String dw_name = getValue("dw_type", String.valueOf(doc.getDw_id()));
		if (doc.getId() == 0) {
			// 只有新增的时候判断
			if (dw_name.equals(String.valueOf(doc.getDw_id()))) {
				doc.setType(0);// 防止恶意注册成为管理员
			} else {
				// 正常注册流程 判断是否是某家医院第一位注册的 是 管理员
				int count = getQxPd(doc.getDw_id(), doc.getKs_id());
				if (count == 0) {
					doc.setType(1);
				} else {
					doc.setType(0);
				}
			}
		}
		doc.setDw_name(dw_name);
		doc.setKs_name(getValue("ks_type", String.valueOf(doc.getKs_id())));
		doc.setJob(getValue("job_type", String.valueOf(doc.getJob())));
		doc.setProvince(getValue("pro_type",
				String.valueOf(doc.getProvince_id())));
		doc.setCity(getValue("city_type", String.valueOf(doc.getCity_id())));
		doc.setArea(getValue("area_type", String.valueOf(doc.getArea_id())));
		String[] strArr = Utils.stringtoArray(doc.getLables(), ",");
		String lableVal = "";
		StringBuffer sbf = new StringBuffer();
		if (strArr.length != 0) {
			for (int i = 0; i < strArr.length; i++) {
				if (!(strArr[i].isEmpty())) {
					lableVal = getValue("lable_type", strArr[i]);
					if (i == 0) {
						sbf.append(lableVal);
					} else {
						sbf.append("," + lableVal);
					}
				}
			}
		}
		doc.setLableNames(sbf.toString());
		return doc;
	}

	@Override
	public int addDoc(Doctors doc) {
		// TODO Auto-generated method stub
		doc.setSalt(CommTool.getSalt());
		doc.setUuid(CommTool.getUUID());
		doc.setStatus(1);// 正常状态
		doc.setScores(5);// 评分默认5
		doc.setPassword(PasswordUtil.generate("666666", doc.getSalt()));// 密码默认
		doc.setCreated_at(CommTool.getNowTimestamp());
		doc.setToken("11111");
		doc.setAvatar("uploadImg/doc.jpg");
		return docMapper.addDoc(docCl(doc));

	}

	@Override
	public int updateDoc(Doctors doc) {
		// TODO Auto-generated method stub
		doc.setLast_modify_time(CommTool.getNowTimestamp());
		/*
		 * doc.setLables(""); doc.setNick(doc.getUname());
		 */
		return docMapper.updateDoc(docCl(doc));

	}

	@Override
	public int upPwd(String id, String pwd) {
		// TODO Auto-generated method stub
		String salt = CommTool.getSalt();
		pwd = PasswordUtil.generate(pwd, salt);
		return docMapper.upPwd(id, pwd, salt);

	}

	@Override
	public int deleteDoc(List<Long> ids) {
		// TODO Auto-generated method stub
		return docMapper.deleteDoc(ids);
	}

	@Override
	public Doctors getDocsByUuid(String uuid) {
		// TODO Auto-generated method stub
		return docMapper.getDocsByUuid(uuid);
	}

	@Override
	public Doctors getDocsByAccount(String account) {
		// TODO Auto-generated method stub
		return docMapper.getDocsByAccount(account);
	}

	@Override
	public List<Doctors> getDocsByAccountAndName(String account, String uname,String type) {
		// TODO Auto-generated method stub
		return docMapper.getDocsByAccountAndName(account, uname, type);
	}

	@Override
	public int jyDoc(List<Long> ids) {
		// TODO Auto-generated method stub
		return docMapper.jyDoc(ids);
	}

	@Override
	public int qxDoc(List<Long> ids) {
		// TODO Auto-generated method stub
		return docMapper.qxDoc(ids);
	}

	@Override
	public int getQxPd(int dw_id,int ks_id) {
		// TODO Auto-generated method stub
		return docMapper.getQxPd(dw_id, ks_id);
	}

	@Override
	public int accountPd(String account) {
		// TODO Auto-generated method stub
		return docMapper.accountPd(account);
	}

	/**
	 * 病人搜索医生
	 */
	@Override
	public List<Doctors> getDocsByPat(String searchBy) {
		// TODO Auto-generated method stub
		return docMapper.getDocsByPat(searchBy);
	}

	@Override
	public List<Doctors> getDocInfo(int id, String sjxz) {
		// TODO Auto-generated method stub
		return docMapper.getDocInfo(id, sjxz);
	}

	/**
	 * 医生端 医生搜索医生
	 */
	@Override
	public List<Doctors> getDocByDoc(int id, String uname, String dw_name,
			String ks_name, String job, String lableNames, String sjxz) {
		// TODO Auto-generated method stub
		return docMapper.getDocByDoc(id, uname, dw_name, ks_name, job,
				lableNames, sjxz);
	}

	@Override
	public int upDocQxByDoc(int dId, int docId, int type, Date xgsj) {
		// TODO Auto-generated method stub
		return docMapper.upDocQxByDoc(dId, docId, type, xgsj);
	}

	@Override
	public int delDocByDoc(int docId) {
		// TODO Auto-generated method stub
		return docMapper.delDocByDoc(docId);
	}

	@Override
	public int addDocByDoc(Doctors doc) {
		// TODO Auto-generated method stub
		doc.setCreated_at(CommTool.getNowTimestamp());
		doc.setNick(doc.getUname());
		doc.setSalt(CommTool.getSalt());
		doc.setPassword(PasswordUtil.generate(doc.getPassword(), doc.getSalt()));// 密码加密
		doc.setToken("999999");
		return docMapper.addDocByDoc(doc);
	}

	@Override
	public List<Doctors> getAddDocs(int dId) {
		// TODO Auto-generated method stub
		return docMapper.getAddDocs(dId);
	}

	@Override
	public List<Doctors> getDocsByEmail(String emails) {
		// TODO Auto-generated method stub
		return docMapper.getDocsByEmail(emails);
	}

	@Override
	public int upDocAvatar(int dId, String avatar, Date xgsj) {
		// TODO Auto-generated method stub
		return docMapper.upDocAvatar(dId, avatar, xgsj);
	}

	@Override
	public int updateMyDoc(Doctors doc) {
		// TODO Auto-generated method stub
		doc.setLast_modify_time(CommTool.getNowTimestamp());
		doc.setLables("");
		System.out.println("---------------------->" + doc.getUname());
		if (doc.getUname() == null || doc.getUname().equals("")) {
			doc.setUname("0");
		}
		if (doc.getDw_name() == null || doc.getDw_name().equals("")) {
			doc.setDw_name("0");
		}
		if (doc.getKs_name() == null || doc.getKs_name().equals("")) {
			doc.setKs_name("0");
		}
		doc.setNick(doc.getUname());
		return docMapper.updateDoc(doc);
	}

	@Override
	public List<Doctors> accountPdInfo(String account) {
		// TODO Auto-generated method stub
		return docMapper.accountPdInfo(account);
	}

	@Override
	public int upLoginTime(int dId, Date dlsj) {
		// TODO Auto-generated method stub
		return docMapper.upLoginTime(dId, dlsj);
	}

	@Override
	public List<TjEntity> getDocOnTj(String province, String city, String dlsjxz) {
		// TODO Auto-generated method stub
		return docMapper.getDocOnTj(province, city, dlsjxz);
	}

	@Override
	public List<TjEntity> getDocAddrTj(String province, String city) {
		// TODO Auto-generated method stub
		return docMapper.getDocAddrTj(province, city);
	}

}
