package com.jyss.mst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.mst.entity.News;
import com.jyss.mst.entity.Share;
import com.jyss.mst.entity.ShareNews;
import com.jyss.mst.mapper.NewsMapper;
import com.jyss.mst.service.NewsService;
import com.jyss.mst.utils.CommTool;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsMapper newsMapper;

	// /********后台端********///

	@Override
	public List<News> getNewsBy(String titles) {
		// TODO Auto-generated method stub
		return newsMapper.getNewsBy(titles);
	}

	@Override
	public List<News> getNews() {
		// TODO Auto-generated method stub
		return newsMapper.getNews();
	}

	@Override
	public int addNews(News news) {
		// TODO Auto-generated method stub
		news.setCreatedAt(CommTool.getNowTimestamp());
		return newsMapper.addNews(news);
	}

	@Override
	public int updateNews(News news) {
		// TODO Auto-generated method stub
		news.setLastModifyTime(CommTool.getNowTimestamp());
		return newsMapper.updateNews(news);
	}

	@Override
	public int deleteNews(List<Long> ids) {
		// TODO Auto-generated method stub
		return newsMapper.deleteNews(ids);
	}

	// /********医生端********///
	@Override
	public List<News> getDocNews() {
		// TODO Auto-generated method stub
		return newsMapper.getDocNews();
	}

	// /********病人端********///

	@Override
	public int addShare(Share share) {
		// TODO Auto-generated method stub
		share.setCreatedAt(CommTool.getNowTimestamp());
		share.setIsRead(0);
		return newsMapper.addShare(share);
	}

	@Override
	public List<News> getPatNews(String id) {
		// TODO Auto-generated method stub
		return newsMapper.getPatNews(id);
	}

	@Override
	public List<ShareNews> getDocFxNews(String dId) {
		// TODO Auto-generated method stub
		return newsMapper.getDocFxNews(dId);
	}

	@Override
	public int upFxNewsRead(int sid) {
		// TODO Auto-generated method stub
		return newsMapper.upFxNewsRead(sid);
	}

	@Override
	public List<News> getDocNewsBySl(long id) {
		// TODO Auto-generated method stub
		return newsMapper.getDocNewsBySl(id);
	}

	@Override
	public List<ShareNews> getDocFxNewsBySl(String dId, long id) {
		// TODO Auto-generated method stub
		return newsMapper.getDocFxNewsBySl(dId, id);
	}

	@Override
	public List<News> getDocNewsByXl(long id) {
		// TODO Auto-generated method stub
		return newsMapper.getDocNewsByXl(id);
	}

	@Override
	public List<ShareNews> getDocFxNewsByXl(String dId, long id) {
		// TODO Auto-generated method stub
		return newsMapper.getDocFxNewsByXl(dId, id);
	}

}
