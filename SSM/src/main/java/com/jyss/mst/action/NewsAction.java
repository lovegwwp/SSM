package com.jyss.mst.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.mst.entity.News;
import com.jyss.mst.entity.Page;
import com.jyss.mst.entity.ResponseEntity;
import com.jyss.mst.entity.Share;
import com.jyss.mst.entity.ShareNews;
import com.jyss.mst.service.NewsService;
import com.jyss.mst.utils.Utils;

@Controller
public class NewsAction {

	@Autowired
	private NewsService newsService;
	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/news")
	public String newsTz() {
		return "news";
	}

	@RequestMapping("/getNews")
	@ResponseBody
	public Page<News> getNews(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<News> newsList = newsService.getNews();
		PageInfo<News> pageInfoNews = new PageInfo<News>(newsList);
		return new Page<News>(pageInfoNews);
	}

	@RequestMapping("/getNewsBy")
	@ResponseBody
	public Page<News> getNewsBy(@RequestParam("titles1") String titles1,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<News> newsByList = newsService.getNewsBy(titles1);
		PageInfo<News> pageInfoNewsBy = new PageInfo<News>(newsByList);
		return new Page<News>(pageInfoNewsBy);
	}

	@RequestMapping("/addNews")
	@ResponseBody
	public ResponseEntity addNews(News news, @RequestParam("pics2") MultipartFile pics2)
	  {
	    int count = 0;
	    
	    String filePath = this.request.getSession().getServletContext()
	      .getRealPath("/");
	    int index = filePath.indexOf("SSM");
	    filePath = filePath.substring(0, index) + "uploadImg/" + 
	      pics2.getOriginalFilename();
	    if (pics2.getSize() > 5400000L) {
	      return new ResponseEntity("NO", "文件过大，应不超过5M!");
	    }
	    news.setByteSize(pics2.getSize());
	    if (!Utils.saveUpload(pics2, filePath)) {
	      return new ResponseEntity("NO", "文件上传失败！");
	    }
	    
	    filePath = filePath.substring(filePath.indexOf("uploadImg"));
	    news.setPics(filePath);
	    if (news.getId() == 0)
	    {
	      count = this.newsService.addNews(news);
	    }
	    else {
	      count = this.newsService.updateNews(news);
	    }
	    
	    if (count == 1) {
	      return new ResponseEntity("OK", "操作成功！");
	    }
	    return new ResponseEntity("NO", "操作失败！");
	  }

	@RequestMapping("/delNews")
	@ResponseBody
	public ResponseEntity deleteNews(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = newsService.deleteNews(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// /////医生端///////////

	@RequestMapping("/doc/getDocNews")
	@ResponseBody
	public List<News> getDocNews() {

		List<News> docNewsList = newsService.getDocNews();
		return docNewsList;
	}

	@RequestMapping("/doc/getDocNewsBySl")
	@ResponseBody
	public List<News> getDocNewsBySl(
			@RequestParam(value = "id", required = true) long id) {
		List<News> docNewsListBySl = newsService.getDocNewsBySl(id);
		return docNewsListBySl;
	}

	@RequestMapping("/doc/getDocNewsByXl")
	@ResponseBody
	public List<News> getDocNewsByXl(
			@RequestParam(value = "id", required = true) long id) {
		List<News> docNewsListBySl = newsService.getDocNewsByXl(id);
		return docNewsListBySl;
	}

	@RequestMapping("/doc/getDocFxNews")
	@ResponseBody
	public List<ShareNews> getDocFxNews(
			@RequestParam(value = "dId", required = true) String dId) {
		List<ShareNews> docFxNewsList = newsService.getDocFxNews(dId);
		return docFxNewsList;
	}

	@RequestMapping("/doc/getDocFxNewsBySl")
	@ResponseBody
	public List<ShareNews> getDocFxNewsBySl(
			@RequestParam(value = "dId", required = true) String dId,
			@RequestParam(value = "id", required = true) long id) {
		List<ShareNews> docFxNewsListBySl = newsService.getDocFxNewsBySl(dId,
				id);
		return docFxNewsListBySl;
	}

	@RequestMapping("/doc/getDocFxNewsByXl")
	@ResponseBody
	public List<ShareNews> getDocFxNewsByXl(
			@RequestParam(value = "dId", required = true) String dId,
			@RequestParam(value = "id", required = true) long id) {
		List<ShareNews> docFxNewsListBySl = newsService.getDocFxNewsByXl(dId,
				id);
		return docFxNewsListBySl;
	}

	/*
	 * @RequestMapping("/pat/getPatNews")
	 * 
	 * @ResponseBody public List<News> getPatNews(
	 * 
	 * @RequestParam(value = "id", required = true) String id ) { List<News>
	 * newsList = newsService.getPatNews(id); return newsList; }
	 */

	@RequestMapping("/pat/getPatNews")
	@ResponseBody
	public Page<News> getPatNews(
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<News> newsList = newsService.getPatNews(id);
		PageInfo<News> pageInfoNews = new PageInfo<News>(newsList);
		return new Page<News>(pageInfoNews);
	}

	@RequestMapping("/doc/addShare")
	@ResponseBody
	public ResponseEntity addShare(
			@RequestParam(value = "dId", required = true) int dId,
			@RequestParam(value = "docId", required = true) int docId,
			@RequestParam(value = "newsId", required = true) int newsId,
			@RequestParam(value = "type", required = true) int type) {
		// TODO Auto-generated method stub
		int count = 0;
		Share sh = new Share();
		sh.setdId(dId);
		sh.setDocId(docId);
		sh.setNewsId(newsId);
		sh.setType(type);
		count = newsService.addShare(sh);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/doc/addSharePat")
	@ResponseBody
	public ResponseEntity addSharePat(
			@RequestParam(value = "dId", required = true) int dId,
			@RequestParam(value = "pId", required = true) int pId,
			@RequestParam(value = "newsId", required = true) int newsId,
			@RequestParam(value = "type", required = true) int type) {
		// TODO Auto-generated method stub
		int count = 0;
		Share sh = new Share();
		sh.setdId(dId);
		sh.setpId(pId);
		sh.setNewsId(newsId);
		sh.setType(type);
		count = newsService.addShare(sh);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/doc/upFxNewsRead")
	@ResponseBody
	public ResponseEntity upFxNewsRead(
			@RequestParam(value = "sid", required = true) int sid) {
		// TODO Auto-generated method stub
		int count = 0;
		count = newsService.upFxNewsRead(sid);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}
}
