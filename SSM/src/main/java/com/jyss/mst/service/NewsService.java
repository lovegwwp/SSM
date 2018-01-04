package com.jyss.mst.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.mst.entity.News;
import com.jyss.mst.entity.Share;
import com.jyss.mst.entity.ShareNews;

public interface NewsService {

	// /********医生端********///

	/**
	 * 获取新闻列表
	 * 
	 * @return
	 */
	List<News> getDocNews();

	/**
	 * 获取新闻列表 ==下拉刷新
	 * 
	 * @return
	 */
	List<News> getDocNewsByXl(@Param("id") long id);

	/**
	 * 获取新闻列表 ==上拉刷新
	 * 
	 * @return
	 */
	List<News> getDocNewsBySl(@Param("id") long id);

	/**
	 * 新增新闻分享
	 * 
	 * @param share
	 * @return
	 */
	int addShare(Share share);

	/**
	 * 获取 分享新闻列表
	 * 
	 * @return
	 */
	List<ShareNews> getDocFxNews(@Param("dId") String dId);

	/**
	 * 获取 分享新闻列表 ==上拉刷新
	 * 
	 * @return
	 */
	List<ShareNews> getDocFxNewsBySl(@Param("dId") String dId,
			@Param("id") long id);

	/**
	 * 获取 分享新闻列表 ==下拉刷新
	 * 
	 * @return
	 */
	List<ShareNews> getDocFxNewsByXl(@Param("dId") String dId,
			@Param("id") long id);

	/**
	 * 分享消息已读未读
	 * 
	 * @param sid
	 * @return
	 */
	int upFxNewsRead(@Param("sid") int sid);

	// /********病人端********///

	/**
	 * 获取新闻列表
	 * 
	 * @return
	 */
	List<News> getPatNews(@Param("id") String id);

	// /********后台端********///

	/**
	 * 获取新闻列表
	 * 
	 * @return
	 */
	List<News> getNews();

	/**
	 * 获取新闻列表
	 * 
	 * @param macId
	 * @return
	 */
	List<News> getNewsBy(@Param("titles") String titles);

	/**
	 * 新增
	 * 
	 * @param news
	 * @return
	 */
	int addNews(News news);

	/**
	 * 修改
	 * 
	 * @param news
	 * @return
	 */
	int updateNews(News news);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * 
	 */
	int deleteNews(@Param("ids") List<Long> ids);
}
