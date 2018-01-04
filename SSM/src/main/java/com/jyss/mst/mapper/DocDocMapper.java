package com.jyss.mst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.mst.entity.DocDoc;

public interface DocDocMapper {
	/**
	 * 获取医生 获取 医生 好友分组
	 * 
	 * @return
	 */
	List<DocDoc> getDGroupByDoc(@Param("dId") String dId);

	/**
	 * 获取医生 获取 医生 好友 列表
	 * 
	 * @return
	 */
	List<DocDoc> getDFriendByDoc(@Param("dId") String dId);

	/**
	 * 获取医生 获取 医生 好友分组 json
	 * 
	 * @return
	 */
	List<DocDoc> getJsonDocGroup(@Param("dId") String dId);

	/**
	 * 获取医生 获取 医生 好友 列表 json
	 * 
	 * @return
	 */
	List<DocDoc> getJsonDocFriend(@Param("dId") String dId,
			@Param("docGroupName") String docGroupName);

	/**
	 * 医生 新增医生病人关系 包括 添加搜索病人 添加分组 还有直接添加分组
	 * 
	 * @param dd
	 * @return
	 */
	int addDdByDoc(DocDoc dd);

	/**
	 * 医生 医生 同意 医生2 好友 拒绝好友3
	 * 
	 * @param dId
	 * @param docId
	 * @return
	 */
	int docAgreeFriend(DocDoc dd);

	/**
	 * 医生 医生分组 编辑分组名称
	 * 
	 * @param dd
	 * @return
	 */
	int updateDdByDoc(DocDoc dd);

	/**
	 * 医生 医生 移至分组
	 * 
	 * @param dd
	 * @return
	 */
	int upDocYzByDoc(DocDoc dd);

	/**
	 * 医生 删除 医生分组 若里面有医生 则医生默认移动到我的好友分组
	 * 
	 * 
	 * @param dId
	 * @param docGroupName
	 * @return
	 */
	int delDocGroup(@Param("dId") String dId, @Param("docId") String docId,
			@Param("docGroupName") String docGroupName);

	/**
	 * 医生 删除 医生分组 若里面有医生 则医生默认移动到我的好友分组
	 * 
	 * @param dId
	 * @param docGroupName
	 * @return
	 */
	int upGroupByDel(@Param("dId") String dId,
			@Param("docGroupName") String docGroupName,
			@Param("newGroupName") String newGroupName);

	/**
	 * 医生 添加医生好友 防止重复添加 先删除之前添加
	 * 
	 * @param dId
	 * @param docId
	 * @return
	 */
	int delRepeat(@Param("dId") String dId, @Param("docId") String docId);

	/**
	 * 医生 防止重复拒绝 先删除之前决绝
	 * 
	 * @param dId
	 * @param docId
	 * @return
	 */
	int delRefuse(@Param("dId") String dId, @Param("docId") String docId);

	/**
	 * 医生 医生同意或拒绝好友修改状态
	 * 
	 * @param dd
	 * @return
	 */
	int updateDdStatus(DocDoc dd);

	/**
	 * 消息是否已读未读
	 * 
	 * @param dd
	 * @return
	 */
	int upIsRead(DocDoc dd);

	/**
	 * 医生 添加好友之前确认是否有该好友
	 * 
	 * @param dId
	 * @param docId
	 * @return
	 */
	int isExistDoc(@Param("dId") String dId, @Param("docId") String docId);

}
