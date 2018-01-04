package com.jyss.mst.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.mst.entity.Xlsp;

public interface XlspService {

	// /=====**************病人端*********************====///

	// 病人训练计划视频
	List<Xlsp> getXlSpByPat(@Param("week") String week,
			@Param("day") String day, @Param("time") String time,
			@Param("dID") String dID, @Param("pID") String pID,
			@Param("type") String type, @Param("status") String status);

	// 病人评测程序
	List<Xlsp> getPcByPat(@Param("week") String week, @Param("day") String day,
			@Param("time") String time, @Param("dID") String dID,
			@Param("pID") String pID, @Param("type") String type,
			@Param("status") String status);

	// 病人训练视频下载路径
	List<Xlsp> getPatSpLoad(@Param("pID") String pID, @Param("sjc") long sjc);

	// 训练下载记录
	List<Xlsp> getPatLoadHistory(@Param("pID") String pID,
			@Param("sjc") long sjc);

	// 返回观看视频时长 计算百分比
	int computePercent(Xlsp sp);

	// 更新评测内容
	int upPcComment(Xlsp sp);

	// *************医生端**************************//

	/**
	 * 医生端 新增训练计划视频
	 * 
	 * @param sp
	 * @return
	 */
	int addSp(Xlsp sp);

	/**
	 * 医生端 复制模板视频
	 * 
	 * @param sp
	 * @return
	 */
	int copyMbSp(Xlsp sp);

	/**
	 * 医生端 保存为模板视频 此前先删除旧模板
	 * 
	 * @param sp
	 * @return
	 */
	int saveMbSp(Xlsp sp);

	/**
	 * 返回 特地 医生 病人 第几周第几天第几次的训练视频
	 * 
	 * @param week
	 * @param day
	 * @param time
	 * @param dID
	 * @param pID
	 * @return
	 */
	List<Xlsp> getBjSp(@Param("week") String week, @Param("day") String day,
			@Param("time") String time, @Param("dID") String dID,
			@Param("pID") String pID, @Param("type") String type);

	/**
	 * 医生端---删除整个方案视频
	 * 
	 * @return
	 */
	int delFaSp(@Param("dID") String dID, @Param("pID") String pID,
			@Param("type") String type);

	/**
	 * 删除 特地 医生 病人 第几周第几天第几次的训练视频
	 * 
	 * @param ids
	 * @return
	 * 
	 */
	int deleteSp(@Param("ids") List<Long> ids);

	/**
	 * 医生端 删除模板视频
	 * 
	 * @param sp
	 * @return
	 */
	int delMbSp(Xlsp sp);

	/**
	 * 医生端替换同类型视频
	 * 
	 * @param sp
	 * @return
	 */
	int replaceSpType(Xlsp sp);

	/**
	 * 医生端 删除未激活视频
	 * 
	 * @param sp
	 * @return
	 */
	int delMoreSp(Xlsp sp);

	/**
	 * 医生端 激活视频
	 * 
	 * @param sp
	 * @return
	 */
	int activeSp(Xlsp sp);

	/**
	 * 训练视频总进度
	 * 
	 * @param pID
	 * @return
	 */
	Xlsp getJsonXlPercent(@Param("pID") String pID, @Param("dID") String dID,
			@Param("type") String type);

	/**
	 * 病人训练种类
	 * 
	 * @param pID
	 * @return
	 */
	List<Xlsp> getPatXlType(@Param("pID") String pID, @Param("dID") String dID);

	// 评测名称
	List<Xlsp> getPcTitles(@Param("id") String id);

}
