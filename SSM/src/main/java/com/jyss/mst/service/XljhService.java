package com.jyss.mst.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.mst.entity.PatXlFa;
import com.jyss.mst.entity.Xljh;
import com.jyss.mst.entity.JsonEntity.ExeMakeIf;

public interface XljhService {

	// /=====***************医生端*********************====///
	/**
	 * 医生端---获取模板训练计划
	 * 
	 * @param pID
	 * @return
	 */
	List<PatXlFa> getPatJh(@Param("pID") String pID, @Param("dID") String dID);

	/**
	 * 医生端---获取模板训练计划
	 * 
	 * @param pID
	 * @return
	 */
	List<PatXlFa> getPatJhDID(@Param("pID") String pID);

	/**
	 * 医生端---获取模板训练计划
	 * 
	 * @param jh
	 * @return
	 */
	List<Xljh> getMbJh(@Param("type") String type, @Param("dID") String dID);

	/**
	 * 医生端---获取 自定义 训练计划
	 * 
	 * @param jh
	 * @return
	 */
	List<Xljh> getScJh(@Param("type") String type, @Param("dID") String dID,
			@Param("pID") String pID);

	/**
	 * 医生端---获取 编辑 训练计划
	 * 
	 * @return
	 */
	List<Xljh> getJhForBj(@Param("type") String type, @Param("dID") String dID,
			@Param("pID") String pID);

	/**
	 * 医生端---生成训练计划
	 * 
	 * @param jh
	 * @return
	 */
	int scJh(Xljh jh);

	/**
	 * 医生端---保存模板
	 * 
	 * @param jh
	 * @return
	 */
	int copyMbJh(Xljh jh);

	/**
	 * 医生端---保存模板
	 * 
	 * @param jh
	 * @return
	 */
	int saveMb(Xljh jh);

	/**
	 * 医生端---删除整个方案计划
	 * 
	 * @return
	 */
	int delFaJh(@Param("dID") String dID, @Param("pID") String pID,
			@Param("type") String type);

	/**
	 * 医生端---删除多余模板计划
	 * 
	 * @param jh
	 * @return
	 */
	int delMoreMbJh(Xljh jh);

	/**
	 * 医生端---替换多余计划
	 * 
	 * @param jh
	 * @return
	 */
	int replaceJhType(Xljh jh);

	/**
	 * 医生端---删除未激活计划
	 * 
	 * @param jh
	 * @return
	 */
	int delMoreJh(Xljh jh);

	/**
	 * 医生端---激活计划
	 * 
	 * @param jh
	 * @return
	 */
	int activeJh(Xljh jh);

	/**
	 * 医生端---生成视频的时候 回置计划视频数量
	 * 
	 * @param jh
	 * @return
	 */
	int setJhAccount(Xljh jh);

	/**
	 * 该医生是否存在业务
	 * 
	 * @param dID
	 * @return
	 */
	int isHasYw(@Param("dId") int dId);

	// /////////json数据转换///////////////
	/**
	 * 医生端---获取 编辑 训练计划 json格式转换
	 * 
	 * @return
	 */
	List<Xljh> getJhForJson(@Param("xlmb") String xlmb,
			@Param("type") String type, @Param("dID") String dID,
			@Param("pID") String pID);

	/**
	 * 医生端---获取 训练类型 json格式转换
	 * 
	 * @return
	 */
	List<Xljh> getTypeForJson(@Param("xlmb") String xlmb,
			@Param("dID") String dID, @Param("pID") String pID);

	/**
	 * 医生端---获取 编辑 训练计划 获取当前总计划
	 * 
	 * @return
	 */
	ExeMakeIf getTotalJhForJson(@Param("xlmb") String xlmb,
			@Param("type") String type, @Param("dID") String dID,
			@Param("pID") String pID);

}
