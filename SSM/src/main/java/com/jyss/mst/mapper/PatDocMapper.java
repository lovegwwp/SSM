package com.jyss.mst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.mst.entity.PatDoc;

public interface PatDocMapper {
	/**
	 * 获取病人 获取好友分组
	 * 
	 * @return
	 */
	List<PatDoc> getPGroup(@Param("pId") String pId);

	/**
	 * 获取医生 获取 病人 好友分组
	 * 
	 * @return
	 */
	List<PatDoc> getDGroup(@Param("docId") String docId);

	/**
	 * 获取病人 获取好友
	 * 
	 * @return
	 */
	List<PatDoc> getPFriend(@Param("pId") String pId,
			@Param("groupName") String groupName);

	/**
	 * 获取医生 获取病人好友
	 * 
	 * @return
	 */
	List<PatDoc> getDFriend(@Param("docId") String docId);

	/**
	 * 获取医生 获取病人好友视频时长
	 * 
	 * @return
	 */
	List<PatDoc> getPVTime(@Param("docId") String docId,
			@Param("videoTime") String videoTime);

	/**
	 * 获取医生 获取病人好友通话时长
	 * 
	 * @return
	 */
	List<PatDoc> getPTTime(@Param("docId") String docId,
			@Param("talkTime") String talkTime);

	/**
	 * 病人新增医生病人关系 包括 添加搜索医生 添加分组 还有直接添加分组
	 * 
	 * @param pd
	 * @return
	 */
	int addPd(PatDoc pd);

	/**
	 * 医生 新增医生病人关系 包括 添加搜索病人 添加分组 还有直接添加分组
	 * 
	 * @param pd
	 * @return
	 */
	int addPdByDoc(PatDoc pd);

	/**
	 * 病人 编辑好友分组名称
	 * 
	 * @param pd
	 * @return
	 */
	int updatePdByPat(PatDoc pd);

	/**
	 * 病人 移除医生至其他分组
	 * 
	 * @param pd
	 * @return
	 */
	int updateYzByPat(PatDoc pd);

	/**
	 * 病人 移除当前医生
	 * 
	 * @param pd
	 * @return
	 */
	int updateScByPat(PatDoc pd);

	/**
	 * 医生 病人分组 编辑好友分组名称
	 * 
	 * @param pd
	 * @return
	 */
	int updatePdByDoc(PatDoc pd);

	/**
	 * 医生 病人分组 移除当前分组
	 * 
	 * @param pd
	 * @return
	 */
	int delDocGroup(@Param("docId") String docId, @Param("pId") String pId,
			@Param("groupName") String groupName);

	/**
	 * 医生 移除医生至其他分组
	 * 
	 * @param pd
	 * @return
	 */
	int updateYzByDoc(PatDoc pd);

	/**
	 * 医生 移除当前医生
	 * 
	 * @param pd
	 * @return
	 */
	int updateScByDoc(PatDoc pd);

	/**
	 * 医生---- 删除当前病人
	 * 
	 * @param docId
	 * @param pId
	 * @return
	 */
	int delPatByDoc(@Param("docId") int docId, @Param("pId") int pId);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * 
	 */
	int deletePd(@Param("ids") List<Long> ids);

	/**
	 * 医生 医生 病人分享
	 * 
	 * @param pd
	 * @return
	 */
	int docAddFx(PatDoc pd);

	/**
	 * -医生 医生 病人分享 同意2或是拒绝3
	 * 
	 * @param pd
	 * @return
	 */
	int upDocFx(PatDoc pd);

	/**
	 * -医生 医生 病人分享 消息已读未读
	 * 
	 * @param pd
	 * @return
	 */
	int upDocFxRead(PatDoc pd);

	/**
	 * -医生 -病人加好友
	 * 
	 * @param pd
	 * @return
	 */

	int upPdRelation(PatDoc pd);

	/**
	 * 获取医生 获取 病人 好友分组 json
	 * 
	 * @return
	 */
	List<PatDoc> getJsonDGroup(@Param("docId") String docId);

	/**
	 * 获取医生 获取 病人 好友 json
	 * 
	 * @return
	 */
	List<PatDoc> getJsonDFriend(@Param("docId") String docId,
			@Param("docGroupName") String docGroupName);

	/**
	 * 获取医生 获取 病人 好友 json ==单个人
	 * 
	 * @return
	 */
	List<PatDoc> getJsonDSingleFriend(@Param("docId") String docId,
			@Param("pId") String pId);

}
