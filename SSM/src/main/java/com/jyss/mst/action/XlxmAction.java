package com.jyss.mst.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.mst.entity.Page;
import com.jyss.mst.entity.PcTest;
import com.jyss.mst.entity.ResponseEntity;
import com.jyss.mst.entity.Xlxm;
import com.jyss.mst.entity.Xtcl;
import com.jyss.mst.service.XlxmService;
import com.jyss.mst.service.XtclService;
import com.jyss.mst.utils.CommTool;
import com.jyss.mst.utils.MsgUtil;
import com.jyss.mst.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class XlxmAction
{
  @Autowired
  private XlxmService xmService;
  @Autowired
  XtclService clService;
  @Autowired
  private HttpServletRequest request;
  
  
  
  @RequestMapping({"/doc/getTitles"})
  @ResponseBody
  public List<Xlxm> getTitles(@RequestParam("type") String type)
  {
    List<Xlxm> xmList = this.xmService.getTitles(type);
    return xmList;
  }
  
  @RequestMapping({"/pat/getAllTitles"})
  @ResponseBody
  public Map<String, Object> getAllTitles()
  {
    Map<String, Object> m = new HashMap();
    List<Xtcl> titleList = new ArrayList();
    titleList = this.clService.getClsCombox("xlxm_type", "");
    List<Map<String, String>> typeList = new ArrayList();
    if (titleList != null) {
      for (Xtcl clTitle : titleList) {
        Map<String, String> map = new HashMap();
        
        map.put("type", clTitle.getBz_id());
        map.put("name", clTitle.getBz_value());
        typeList.add(map);
      }
    }
    m.put("typeTile", typeList);
    return m;
  }
  
  @RequestMapping({"/xm"})
  public String newsTz()
  {
    return "xlxm";
  }
  
  @RequestMapping({"/xmPc"})
  public String xmPcTz() {
    return "xmPc";
  }
  
  @RequestMapping({"/xmTest"})
  public String xmTestTz() {
    return "xmTest";
  }
  
  @RequestMapping({"/yxzx"})
  public String yxzxTz() {
    return "yxzx";
  }
  
  @RequestMapping({"/getXm"})
  @ResponseBody
  public Page<Xlxm> getNews(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<Xlxm> xmList = this.xmService.getXm();
    PageInfo<Xlxm> pageInfoXm = new PageInfo(xmList);
    return new Page(pageInfoXm);
  }
  
  @RequestMapping({"/getXmBy"})
  @ResponseBody
  public Page<Xlxm> getNewsBy(@RequestParam("titles1") String titles1, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<Xlxm> xmByList = this.xmService.getXmBy(titles1);
    PageInfo<Xlxm> pageInfoXmBy = new PageInfo(xmByList);
    return new Page(pageInfoXmBy);
  }
  
  @RequestMapping({"/addXm"})
  @ResponseBody
  public ResponseEntity addXm(Xlxm xm)
  {
    int count = 0;
    
    if (xm.getId() == 0)
    {
      count = this.xmService.addXm(xm);
    }
    else {
      count = this.xmService.updateXm(xm);
    }
    
    if (count == 1) {
      return new ResponseEntity("OK", "操作成功！");
    }
    return new ResponseEntity("NO", "操作失败！");
  }
  
  @RequestMapping({"/delXm"})
  @ResponseBody
  public ResponseEntity deleteNews(String strIds)
  {
    int count = 0;
    List<Long> ids = Utils.stringToLongList(strIds, ",");
    count = this.xmService.deleteXm(ids);
    if (count >= 1) {
      return new ResponseEntity("true", "操作成功！");
    }
    return new ResponseEntity("false", "操作失败！");
  }
  
  @RequestMapping({"/getXmPc"})
  @ResponseBody
  public Page<PcTest> getXmPc(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<PcTest> xmList = this.xmService.getPcBy("", "0");
    PageInfo<PcTest> pageInfoXm = new PageInfo(xmList);
    return new Page(pageInfoXm);
  }
  
  @RequestMapping({"/getXmPcBy"})
  @ResponseBody
  public Page<PcTest> getXmPcBy(@RequestParam("titles1") String titles1, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<PcTest> xmByList = this.xmService.getPcBy(titles1, "0");
    PageInfo<PcTest> pageInfoXmBy = new PageInfo(xmByList);
    return new Page(pageInfoXmBy);
  }
  
  @RequestMapping({"/addXmPc"})
  @ResponseBody
  public ResponseEntity addXmPc(PcTest pt)
  {
    int count = 0;
    pt.setType("0");
    if (pt.getId() == 0)
    {
      count = this.xmService.addPc(pt);
    }
    else {
      count = this.xmService.updatePc(pt);
    }
    
    if (count == 1) {
      return new ResponseEntity("OK", "操作成功！");
    }
    return new ResponseEntity("NO", "操作失败！");
  }
  
  @RequestMapping({"/getYxzx"})
  @ResponseBody
  public Page<PcTest> getYxzx(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<PcTest> xmList = this.xmService.getPcBy("", "9");
    PageInfo<PcTest> pageInfoXm = new PageInfo(xmList);
    return new Page(pageInfoXm);
  }
  
  @RequestMapping({"/getYxzxBy"})
  @ResponseBody
  public Page<PcTest> getYxzxBy(@RequestParam("titles1") String titles1, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<PcTest> xmByList = this.xmService.getPcBy(titles1, "9");
    PageInfo<PcTest> pageInfoXmBy = new PageInfo(xmByList);
    return new Page(pageInfoXmBy);
  }
  
  @RequestMapping({"/addYxzx"})
  @ResponseBody
  public ResponseEntity addYxzx(PcTest pt)
  {
    int count = 0;
    pt.setType("9");
    if (pt.getId() == 0)
    {
      count = this.xmService.addPc(pt);
    }
    else {
      count = this.xmService.updatePc(pt);
    }
    
    if (count == 1) {
      return new ResponseEntity("OK", "操作成功！");
    }
    return new ResponseEntity("NO", "操作失败！");
  }
  
  @RequestMapping({"/delXmPc"})
  @ResponseBody
  public ResponseEntity delXmPc(String strIds)
  {
    int count = 0;
    List<Long> ids = Utils.stringToLongList(strIds, ",");
    count = this.xmService.deletePc(ids);
    if (count >= 1) {
      return new ResponseEntity("true", "操作成功！");
    }
    return new ResponseEntity("false", "操作失败！");
  }
  
  @RequestMapping({"/getTest"})
  @ResponseBody
  public Page<PcTest> getTest(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<PcTest> xmList = this.xmService.getTsetBy("");
    PageInfo<PcTest> pageInfoXm = new PageInfo(xmList);
    return new Page(pageInfoXm);
  }
  
  @RequestMapping({"/getTestBy"})
  @ResponseBody
  public Page<PcTest> getTestBy(@RequestParam("titles1") String titles1, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<PcTest> xmByList = this.xmService.getTsetBy(titles1);
    PageInfo<PcTest> pageInfoXmBy = new PageInfo(xmByList);
    return new Page(pageInfoXmBy);
  }
  
  @RequestMapping({"/addTest"})
  @ResponseBody
  public ResponseEntity addTest(PcTest pt)
  {
    int count = 0;
    
    if (pt.getId() == 0)
    {
      pt.setType("0");
      count = this.xmService.addTset(pt);
    }
    else {
      count = this.xmService.updateTset(pt);
    }
    
    if (count == 1) {
      return new ResponseEntity("OK", "操作成功！");
    }
    return new ResponseEntity("NO", "操作失败！");
  }
  
  @RequestMapping({"/delXmTest"})
  @ResponseBody
  public ResponseEntity delXmTest(String strIds)
  {
    int count = 0;
    List<Long> ids = Utils.stringToLongList(strIds, ",");
    count = this.xmService.deleteTset(ids);
    if (count >= 1) {
      return new ResponseEntity("true", "操作成功！");
    }
    return new ResponseEntity("false", "操作失败！");
  }
  
  public void addXm2(Xlxm xm)
  {
    int count = 0;
    
    count = this.xmService.addXm(xm);
  }
  
  @RequestMapping({"/TestTest"})
  @ResponseBody
  public ResponseEntity addTest()
  {
    String vedioStr = "uploadVedio/00_00";
    int vedioTyee = 1;
    Xlxm xm2 = new Xlxm();
    xm2.setByteSize(0L);
    xm2.setCreatedAt(CommTool.getNowTimestamp());
    xm2.setType("00");
    xm2.setItemid("0");
    xm2.setVedioType(1);
    for (int i = 100; i < 201; i++) {
      for (int j = 1; j < 3; j++) {
        vedioStr = vedioStr + i + "_" + j + ".mp4";
        xm2.setVedioType(j);
        xm2.setTitles(i+"");
        xm2.setVedio(vedioStr);
        addXm2(xm2);
        vedioStr = "uploadVedio/00_00";
      }
    }
    
    return new ResponseEntity("YES", "OKOK！");
  }
}
