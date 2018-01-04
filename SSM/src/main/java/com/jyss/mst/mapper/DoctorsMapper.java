package com.jyss.mst.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.mst.entity.Doctors;
import com.jyss.mst.entity.TjEntity;

public interface DoctorsMapper {

	// ----------医生端 接口--获取医生搜索---------

	/**
	 * 获取医生 新增的医生
	 * 
	 * @return
	 */
	List<Doctors> getAddDocs(@Param("dId") int dId);

	/**
	 * 获取医生搜索列表
	 * 
	 * @return
	 */
	List<Doctors> getDocByDoc(@Param("id") int id,
			@Param("uname") String uname, @Param("dw_name") String dw_name,
			@Param("ks_name") String ks_name, @Param("job") String job,
			@Param("lableNames") String lableNames, @Param("sjxz") String sjxz);

	/**
	 * 医生管理员 修改医生权限
	 * 
	 * @param dId
	 * @param docId
	 * @param type
	 * @param xgsj
	 * @return
	 */
	int upDocQxByDoc(@Param("dId") int dId, @Param("docId") int docId,
			@Param("type") int type, @Param("xgsj") Date xgsj);

	/**
	 * 医生 修改头像
	 * 
	 * @param dId
	 * @param avatar
	 * @param xgsj
	 * @return
	 */
	int upDocAvatar(@Param("dId") int dId, @Param("avatar") String avatar,
			@Param("xgsj") Date xgsj);

	/**
	 * 医生 修改登陆时间
	 * 
	 * @param dId
	 * @param dlsj
	 * @return
	 */
	int upLoginTime(@Param("dId") int dId, @Param("dlsj") Date dlsj);

	/**
	 * 医生删除医生
	 * 
	 * @param docId
	 * @return
	 */
	int delDocByDoc(@Param("docId") int docId);

	/**
	 * 医生新增医生
	 * 
	 * @param doc
	 * @return
	 */
	int addDocByDoc(Doctors doc);

	// ----------病人端接口-----------
	/**
	 * 获取医生详情主页
	 * 
	 * @return
	 */
	List<Doctors> getDocInfo(@Param("id") int id, @Param("sjxz") String sjxz);

	// ---------后台处理-------------

	/**
	 * 根据参数获取医生
	 * 
	 * @param id
	 * @return
	 */

	Doctors getDocsById(@Param("id") int id);

	/**
	 * 根据参数获取医生
	 * 
	 * @param emails
	 * @return
	 */
	List<Doctors> getDocsByEmail(@Param("emails") String emails);

	/**
	 * 根据参数获取医生列表
	 * 
	 * @param uuid
	 * @return
	 */

	Doctors getDocsByUuid(@Param("uuid") String uuid);

	/**
	 * 根据参数获取医生列表
	 * 
	 * @param account
	 * @return
	 */

	Doctors getDocsByAccount(@Param("account") String account);

	/**
	 * 获取医生列表
	 * 
	 * @return
	 */
	List<Doctors> getDocs();

	/**
	 * 获取医生列表
	 * 
	 * @param account
	 * @param uname
	 * @return
	 */
	List<Doctors> getDocsByAccountAndName(@Param("account") String account,
			@Param("uname") String uname,@Param("type") String type);

	/**
	 * 获取医生列表 病人搜索
	 * 
	 * @param account
	 * @return
	 */
	List<Doctors> getDocsByPat(@Param("searchBy") String searchBy);

	/**
	 * 新增
	 * 
	 * @param doc
	 * @return
	 */
	int addDoc(Doctors doc);

	/**
	 * 修改
	 * 
	 * @param doc
	 * @return
	 */
	int updateDoc(Doctors doc);

	/**
	 * 修改密码
	 * 
	 * @param doc
	 * @return
	 */
	int upPwd(@Param("id") String id, @Param("pwd") String pwd,
			@Param("salt") String salt);

	/**
	 * 删除
	 * 
	 * @param doc
	 * @return
	 * 
	 */
	int deleteDoc(@Param("ids") List<Long> ids);

	/**
	 * 禁用
	 * 
	 * @param doc
	 * @return
	 * 
	 */
	int jyDoc(@Param("ids") List<Long> ids);

	/**
	 * 权限设置
	 * 
	 * @param doc
	 * @return
	 * 
	 */
	int qxDoc(@Param("ids") List<Long> ids);

	/**
	 * 
	 * 唯一账号判断
	 * 
	 * @param account
	 * @return
	 * 
	 */
	int accountPd(@Param("account") String account);

	/**
	 * 
	 * 唯一账号列表
	 * 
	 * @param account
	 * @return
	 * 
	 */

	List<Doctors> accountPdInfo(@Param("account") String account);

	/**
	 * 权限判断
	 * 
	 * @param dw_id
	 * @return
	 * 
	 */
	int getQxPd(@Param("dw_id") int dw_id, @Param("ks_id") int ks_id);

	/**
	 * 医生在线地域统计
	 * 
	 * @param province
	 * @param city
	 * @return
	 */
	List<TjEntity> getDocOnTj(@Param("province") String province,
			@Param("city") String city, @Param("dlsjxz") String dlsjxz);

	/**
	 * 医生地域统计
	 * 
	 * @param province
	 * @param city
	 * @return
	 */
	List<TjEntity> getDocAddrTj(@Param("province") String province,
			@Param("city") String city);

}
