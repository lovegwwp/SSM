package com.jyss.mst.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.mst.entity.Devices;

public interface DevicesService {
	/**
	 * 获取设备列表
	 * 
	 * @return
	 */
	List<Devices> getDev();

	/**
	 * 获取设备列表
	 * 
	 * @return
	 */
	List<Devices> getDevErr();

	/**
	 * 获取设备常量
	 * 
	 * @return
	 */
	List<Devices> getDevCl();

	/**
	 * 获取设备列表
	 * 
	 * @param macId
	 * @return
	 */
	List<Devices> getDevErrBy(@Param("macId") String macId);

	/**
	 * 获取设备列表
	 * 
	 * @param macId
	 * @return
	 */
	List<Devices> getDevBy(@Param("macId") String macId, @Param("patID") String patID, @Param("name") String name);

	/**
	 * 新增
	 * 
	 * @param dev
	 * @return
	 */
	int addDev(Devices dev);

	/**
	 * 修改
	 * 
	 * @param dev
	 * @return
	 */
	int updateDev(Devices dev);

	/**
	 * 修改IP地址
	 * 
	 * @param dev
	 * @return
	 */
	int upDevIp(Devices dev);

	/**
	 * 修改设备状态
	 * 
	 * @param dev
	 * @return
	 */
	int updateDevZt(Devices dev);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * 
	 */
	int deleteDev(@Param("ids") List<Long> ids);

	/**
	 * 批量解绑
	 * 
	 * @param ids
	 * @return
	 * 
	 */
	int batchJb(@Param("ids") List<Long> ids);

	/**
	 * 绑定设置
	 * 
	 * @param type
	 * @param id
	 * @return
	 */
	int setBd(@Param("type") String type, @Param("macId") String macId,
			@Param("patId") String patId);

	/**
	 * 获取MacID
	 * 
	 * @param id
	 * @return
	 */
	String getMac(@Param("patId") int patId);

}
