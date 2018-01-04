package com.jyss.mst.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.mst.entity.GoodCategory;
import com.jyss.mst.entity.Goods;
import com.jyss.mst.entity.Page;
import com.jyss.mst.entity.ResponseEntity;
import com.jyss.mst.service.GoodsService;
import com.jyss.mst.utils.CommTool;
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
public class GoodsAction
{
  @Autowired
  private GoodsService goodsService;
  @Autowired
  private HttpServletRequest request;
  
  @RequestMapping({"/goods"})
  public String goodsTz()
  {
    return "goods";
  }
  
  @RequestMapping({"/goodsCategory"})
  public String goodsCategoryTz() {
    return "goodsCategory";
  }
  
  @RequestMapping({"/getGoods"})
  @ResponseBody
  public Page<Goods> getGoods(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<Goods> gsList = this.goodsService.getGoodsBy("", "");
    for (Goods goods : gsList) {
      goods.setImg("http://121.40.29.64:8081/" + goods.getPics());
    }
    PageInfo<Goods> pageInfoNews = new PageInfo(gsList);
    return new Page(pageInfoNews);
  }
  
  @RequestMapping({"/getGoodsBy"})
  @ResponseBody
  public Page<Goods> getGoodsBy(@RequestParam("name") String name, @RequestParam("categoryId") String categoryId, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<Goods> goodsByList = this.goodsService.getGoodsBy(name, categoryId);
    for (Goods goods : goodsByList) {
      goods.setImg("http://121.40.29.64:8081/" + goods.getPics());
    }
    PageInfo<Goods> pageInfoNews = new PageInfo(goodsByList);
    return new Page(pageInfoNews);
  }
  
  @RequestMapping({"/getGoodCategory"})
  @ResponseBody
  public Page<GoodCategory> getGoodCategory(@RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<GoodCategory> gcList = this.goodsService.getGoodCategoryBy("");
    PageInfo<GoodCategory> pageInfoNews = new PageInfo(gcList);
    return new Page(pageInfoNews);
  }
  
  @RequestMapping({"/getGoodCategoryCol"})
  @ResponseBody
  public List<GoodCategory> getGoodCategoryCol()
  {
    List<GoodCategory> gcList = this.goodsService.getGoodCategoryBy("");
    return gcList;
  }
  
  @RequestMapping({"/getGoodCategoryBy"})
  @ResponseBody
  public Page<GoodCategory> getGoodCategoryBy(@RequestParam("name") String name, @RequestParam(value="page", required=true) int page, @RequestParam(value="rows", required=true) int rows)
  {
    PageHelper.startPage(page, rows);
    List<GoodCategory> gcList = this.goodsService.getGoodCategoryBy(name);
    PageInfo<GoodCategory> pageInfoNews = new PageInfo(gcList);
    return new Page(pageInfoNews);
  }
  
  @RequestMapping({"/addGoods"})
  @ResponseBody
  public ResponseEntity addGoods(Goods goods, @RequestParam("pics2") MultipartFile pics2)
  {
    int count = 0;
    
    String filePath = this.request.getSession().getServletContext()
      .getRealPath("/");
    int index = filePath.indexOf("SSM");
    filePath = filePath.substring(0, index) + "uploadImg/" + "sp" + 
      CommTool.getSalt() + pics2.getOriginalFilename();
    if (pics2.getSize() > 5400000L) {
      return new ResponseEntity("NO", "文件过大，应不超过5M!");
    }
    if (!Utils.saveUpload(pics2, filePath)) {
      return new ResponseEntity("NO", "文件上传失败！");
    }
    
    filePath = filePath.substring(filePath.indexOf("uploadImg"));
    goods.setPics(filePath);
    if (goods.getId() == 0)
    {
      count = this.goodsService.addGoods(goods);
    }
    else {
      count = this.goodsService.updateGoods(goods);
    }
    
    if (count == 1) {
      return new ResponseEntity("OK", "操作成功！");
    }
    return new ResponseEntity("NO", "操作失败！");
  }
  
  @RequestMapping({"/addGoodCategory"})
  @ResponseBody
  public ResponseEntity addGoodCategory(GoodCategory gc)
  {
    int count = 0;
    if (gc.getId() == 0)
    {
      count = this.goodsService.addGoodCategory(gc);
    }
    else {
      count = this.goodsService.updateGoodCategory(gc);
    }
    
    if (count == 1) {
      return new ResponseEntity("OK", "操作成功！");
    }
    return new ResponseEntity("NO", "操作失败！");
  }
  
  @RequestMapping({"/delGoods"})
  @ResponseBody
  public ResponseEntity delGoods(String strIds)
  {
    int count = 0;
    List<Long> ids = Utils.stringToLongList(strIds, ",");
    count = this.goodsService.deleteGoods(ids);
    if (count >= 1) {
      return new ResponseEntity("true", "操作成功！");
    }
    return new ResponseEntity("false", "操作失败！");
  }
  
  @RequestMapping({"/delGoodCategory"})
  @ResponseBody
  public ResponseEntity delGoodCategory(String strIds)
  {
    int count = 0;
    List<Long> ids = Utils.stringToLongList(strIds, ",");
    count = this.goodsService.deleteGoodCategory(ids);
    if (count >= 1) {
      return new ResponseEntity("true", "操作成功！");
    }
    return new ResponseEntity("false", "操作失败！");
  }
}
