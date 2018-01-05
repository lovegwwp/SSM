package com.jyss.mst.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.mst.entity.Page;
import com.jyss.mst.entity.ResponseEntity;
import com.jyss.mst.entity.Xtgx;
import com.jyss.mst.entity.Xtnews;
import com.jyss.mst.service.XtNewsService;
import com.jyss.mst.utils.Utils;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class XtNewsAction
{
  @Autowired
  private XtNewsService xtService;
  @Autowired
  private HttpServletRequest request;
  
  @RequestMapping("/xtnews")
  public String xtnewsTz()
  {
    return "xtnews";
  }
  
  @RequestMapping("/appxtnews")
  public String appxtnewsTz() {
    return "appxtnews";
  }
  
  @RequestMapping("/xtgx")
  public String xtgxTz() {
    return "xtgx";
  }
  
  @RequestMapping("/kaijixtnews")
  public String kaijixtnewsTz() {
    return "kaijixtnews";
  }
  
  @RequestMapping("/getKaijiXtNews")
  @ResponseBody
  public Page<Xtnews> getKaijiXtNews(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<Xtnews> xtnewsList = this.xtService.getXtNews("web.kaiji");
    PageInfo<Xtnews> pageInfoNews = new PageInfo(xtnewsList);
    return new Page(pageInfoNews);
  }
  
  @RequestMapping("/getKaijiXtNewsBy")
  @ResponseBody
  public Page<Xtnews> getKaijiXtNewsBy(@RequestParam("title1") String title1, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<Xtnews> xtnewsByList = this.xtService.getXtNewsBy(title1, "web.kaiji");
    PageInfo<Xtnews> pageInfoNewsBy = new PageInfo(xtnewsByList);
    return new Page(pageInfoNewsBy);
  }
  
  @RequestMapping("/addKaijiXtNews")
  @ResponseBody
  public ResponseEntity addKaijiXtNews(Xtnews xtnews, @RequestParam("myFile") MultipartFile myFile)
  {
    int count = 0;
    
    String filePath = this.request.getSession().getServletContext()
      .getRealPath("/");
    int index = filePath.indexOf("SSM");
    filePath = filePath.substring(0, index) + "uploadImg/" + 
      myFile.getOriginalFilename();
    if (myFile.getSize() > 5400000L) {
      return new ResponseEntity("NO", "文件过大，应不超过5M!");
    }
    xtnews.setByteSize(myFile.getSize());
    if (!Utils.saveUpload(myFile, filePath)) {
      return new ResponseEntity("NO", "文件上传失败！");
    }
    
    filePath = filePath.substring(filePath.indexOf("uploadImg"));
    xtnews.setKeytype("web.kaiji");
    
    xtnews.setPic(filePath);
    if (xtnews.getId() == 0)
    {
      count = this.xtService.addXtNews(xtnews);
    }
    else {
      count = this.xtService.updateXtNews(xtnews);
    }
    
    if (count == 1) {
      return new ResponseEntity("OK", "操作成功！");
    }
    return new ResponseEntity("NO", "操作失败！");
  }
  
  @RequestMapping("/getXtNewsApp")
  @ResponseBody
  public Page<Xtnews> getXtNewsApp(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<Xtnews> xtnewsList = this.xtService.getXtNews("app.aboutus");
    PageInfo<Xtnews> pageInfoNews = new PageInfo(xtnewsList);
    return new Page(pageInfoNews);
  }
  
  @RequestMapping("/getAPPXtNewsBy")
  @ResponseBody
  public Page<Xtnews> getAPPXtNewsBy(@RequestParam("title1") String title1, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<Xtnews> xtnewsByList = this.xtService
      .getXtNewsBy(title1, "app.aboutus");
    PageInfo<Xtnews> pageInfoNewsBy = new PageInfo(xtnewsByList);
    return new Page(pageInfoNewsBy);
  }
  
  @RequestMapping("/addAPPXtNews")
  @ResponseBody
  public ResponseEntity addAPPXtNews(com.jyss.mst.entity.Xtnews xtnews)
  {
    int count = 0;
    
    xtnews.setKeytype("app.aboutus");
    
    if (xtnews.getId() == 0)
    {
      count = this.xtService.addXtNews(xtnews);
    }
    else {
      count = this.xtService.updateXtNews(xtnews);
    }
    
    if (count == 1) {
      return new ResponseEntity("OK", "操作成功！");
    }
    return new ResponseEntity("NO", "操作失败！");
  }
  
  @RequestMapping("/getXtNews")
  @ResponseBody
  public Page<com.jyss.mst.entity.Xtnews> getXtNews(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<com.jyss.mst.entity.Xtnews> xtnewsList = this.xtService.getXtNews("web.aboutus");
    PageInfo<com.jyss.mst.entity.Xtnews> pageInfoNews = new PageInfo(xtnewsList);
    return new Page(pageInfoNews);
  }
  
  @RequestMapping("/getXtNewsBy")
  @ResponseBody
  public Page<com.jyss.mst.entity.Xtnews> getXtNewsBy(@RequestParam("title1") String title1, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<com.jyss.mst.entity.Xtnews> xtnewsByList = this.xtService
      .getXtNewsBy(title1, "web.aboutus");
    PageInfo<com.jyss.mst.entity.Xtnews> pageInfoNewsBy = new PageInfo(xtnewsByList);
    return new Page(pageInfoNewsBy);
  }
  
  @RequestMapping("/addXtNews")
  @ResponseBody
  public ResponseEntity addXtNews(com.jyss.mst.entity.Xtnews xtnews)
  {
    int count = 0;
    
    xtnews.setKeytype("web.aboutus");
    
    if (xtnews.getId() == 0)
    {
      count = this.xtService.addXtNews(xtnews);
    }
    else {
      count = this.xtService.updateXtNews(xtnews);
    }
    
    if (count == 1) {
      return new ResponseEntity("OK", "操作成功！");
    }
    return new ResponseEntity("NO", "操作失败！");
  }
  
  @RequestMapping("/delXtNews")
  @ResponseBody
  public ResponseEntity deleteXtNews(String strIds)
  {
    int count = 0;
    List<Long> ids = Utils.stringToLongList(strIds, ",");
    count = this.xtService.deleteXtNews(ids);
    if (count >= 1) {
      return new ResponseEntity("true", "操作成功！");
    }
    return new ResponseEntity("false", "操作失败！");
  }
  
  @RequestMapping("/getXtgx")
  @ResponseBody
  public Page<Xtgx> getXtgx(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<Xtgx> xtnewsList = this.xtService.getXtgx();
    PageInfo<Xtgx> pageInfoNews = new PageInfo(xtnewsList);
    return new Page(pageInfoNews);
  }
  
  @RequestMapping("/getXtgxBy")
  @ResponseBody
  public Page<Xtgx> getXtgxBy(@RequestParam("titles1") String titles1, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<Xtgx> xtnewsByList = this.xtService.getXtgxBy(titles1);
    PageInfo<Xtgx> pageInfoNewsBy = new PageInfo(xtnewsByList);
    return new Page(pageInfoNewsBy);
  }
  
  @RequestMapping("/addXtgx")
  @ResponseBody
  public ResponseEntity addXtgx(Xtgx xtgx, @RequestParam("myFile") MultipartFile myFile)
  {
    int count = 0;
    
    String filePath = this.request.getSession().getServletContext()
      .getRealPath("/");
    int index = filePath.indexOf("SSM");
    filePath = filePath.substring(0, index) + "uploadWar/" + 
      myFile.getOriginalFilename();
    if (myFile.getSize() > 240000000L) {
      return new ResponseEntity("NO", "文件过大，应不超过220M!");
    }
    xtgx.setByteSize(myFile.getSize());
    if (!Utils.saveUpload(myFile, filePath)) {
      return new ResponseEntity("NO", "文件上传失败！");
    }
    
    filePath = filePath.substring(filePath.indexOf("uploadWar"));
    xtgx.setSrc(filePath);
    if (xtgx.getId() == 0)
    {
      count = this.xtService.addXtgx(xtgx);
    }
    else {
      count = this.xtService.updateXtgx(xtgx);
    }
    
    if (count == 1) {
      return new ResponseEntity("OK", "操作成功！");
    }
    return new ResponseEntity("NO", "操作失败！");
  }
  
  @RequestMapping("/delXtgx")
  @ResponseBody
  public ResponseEntity deleteXtgx(String strIds)
  {
    int count = 0;
    List<Long> ids = Utils.stringToLongList(strIds, ",");
    count = this.xtService.deleteXtgx(ids);
    if (count >= 1) {
      return new ResponseEntity("true", "操作成功！");
    }
    return new ResponseEntity("false", "操作失败！");
  }
  
  @RequestMapping("/pat/getPatXtNews")
  @ResponseBody
  public com.jyss.mst.entity.Xtnews getPatXtNews()
  {
    com.jyss.mst.entity.Xtnews xtnewspat = (com.jyss.mst.entity.Xtnews)this.xtService.getPatXtNews().get(0);
    return xtnewspat;
  }
  
  @RequestMapping("/pat/getPatXtgx")
  @ResponseBody
  public Xtgx getPatXtgx()
  {
    Xtgx xtgxpat = (Xtgx)this.xtService.getPatXtgx().get(0);
    return xtgxpat;
  }
}
