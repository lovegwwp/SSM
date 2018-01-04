package com.jyss.mst.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.mst.entity.Bl;
import com.jyss.mst.entity.PatSample;
import com.jyss.mst.entity.Patients;
import com.jyss.mst.entity.TjEntity;
import com.jyss.mst.entity.Zd;

public interface PatientsService {

	// /******************医生端****************************///

	/**
	 * 获取搜索病人列表
	 * 
	 * @return
	 */
	List<Patients> getPatByDoc(@Param("account") String account,
			@Param("uname") String uname, @Param("sex") String sex,
			@Param("age1") String age1, @Param("age2") String age2,
			@Param("addr") String addr);

	/**
	 * 病人端-- 获取个体病人多个诊断
	 * 
	 * @param pId
	 * @return
	 */
	List<Zd> getZdList(@Param("pId") String pId);

	/**
	 * 医生端 获取个体病人
	 * 
	 * @param pId
	 * @return
	 */
	List<PatSample> getPatSample(@Param("pId") String pId);

	/**
	 * 医生 新增病人 诊断
	 * 
	 * @param zd
	 * @return
	 */
	int addZdContent(Zd zd);

	/**
	 * 医生 修改病人 诊断
	 * 
	 * @param zd
	 * @return
	 */
	int upZdContent(Zd zd);

	// /*****************病人端****************************///
	/**
	 * 通话时长修改
	 * 
	 * @param videoTime
	 * @param id
	 * @return
	 */
	int upVedioTime(@Param("videoTime") int videoTime, @Param("id") int id);

	/**
	 * 观看时长修改
	 * 
	 * @param videoTime
	 * @param id
	 * @return
	 */
	int upTalkTime(@Param("talkTime") int talkTime, @Param("id") int id);

	/**
	 * 病人修改个人地址
	 * 
	 * @param pat
	 * @return
	 */
	int upPatbyPat(Patients pat);

	/**
	 * 通话时长===充值
	 * 
	 * @param videoTime
	 * @param id
	 * @return
	 */
	int upVedioTimeByCz(@Param("videoTime") long videoTime,
			@Param("account") String account);

	/**
	 * 观看时长===充值
	 * 
	 * @param videoTime
	 * @param id
	 * @return
	 */
	int upTalkTimeByCz(@Param("talkTime") long talkTime,
			@Param("account") String account);

	// /******************后台****************************///

	/**
	 * 新增诊断
	 * 
	 * @param zd
	 * @return
	 */
	int addZd(Zd zd);

	/**
	 * 修改诊断
	 * 
	 * @param zd
	 * @return
	 */
	int upZd(Zd zd);

	/**
	 * 新增病例
	 * 
	 * @param bl
	 * @return
	 */
	int addBl(Bl bl);

	/**
	 * 修改病例
	 * 
	 * @param bl
	 * @return
	 */
	int upBl(Bl bl);

	/**
	 * 修改病人诊断编号
	 * 
	 * @return
	 */
	int upPatZdId(@Param("zdId") int zdId, @Param("id") int id);

	// /******************后台****************************///

	/**
	 * 获取医生列表
	 * 
	 * @return
	 */
	List<Patients> getPat();

	/**
	 * 获取医生列表
	 * 
	 * @param macId
	 * @return
	 */
	List<Patients> getPatBy(@Param("account") String account, @Param("uname") String uname, 
			 @Param("sex") String sex, @Param("province") String province, @Param("city") String city, @Param("area") String area);

	/**
	 * 获取个体医生
	 * 
	 * @param account
	 * @return
	 */
	Patients getMe(@Param("account") String account);

	/**
	 * 新增
	 * 
	 * @param pat
	 * @return
	 */
	int addPat(Patients pat);

	/**
	 * 修改
	 * 
	 * @param pat
	 * @return
	 */
	int updatePat(Patients pat);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * 
	 */
	int deletePat(@Param("ids") List<Long> ids);

	// /******************后台********统计********************///
	/**
	 * 病人地域统计
	 * 
	 * @param province
	 * @param city
	 * @return
	 */
	List<TjEntity> getPatAddrTj(@Param("province") String province,
			@Param("city") String city);

	/**
	 * 病人在线地域统计
	 * 
	 * @param province
	 * @param city
	 * @return
	 */
	List<TjEntity> getPatOnTj(@Param("province") String province,
			@Param("city") String city, @Param("dlsjxz") String dlsjxz);

	/**
	 * 病人在线地域统计
	 * 
	 * @param province
	 * @param city
	 * @return
	 */
	List<TjEntity> getPatDwTj(@Param("dw") String dw);

}
