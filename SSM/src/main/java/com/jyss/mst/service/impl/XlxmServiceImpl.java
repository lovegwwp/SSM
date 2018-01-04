package com.jyss.mst.service.impl;

import com.jyss.mst.entity.PcTest;
import com.jyss.mst.entity.Xlxm;
import com.jyss.mst.mapper.XlxmMapper;
import com.jyss.mst.service.XlxmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XlxmServiceImpl
  implements XlxmService
{
  @Autowired
  private XlxmMapper xmMapper;
  
  public List<Xlxm> getTitles(String type)
  {
    return this.xmMapper.getTitles(type);
  }
  
  public String getTitleById(String id)
  {
    return this.xmMapper.getTitleById(id);
  }
  
  public List<Xlxm> getXm()
  {
    return this.xmMapper.getXm();
  }
  
  public List<Xlxm> getXmBy(String titles)
  {
    return this.xmMapper.getXmBy(titles);
  }
  
  public int addXm(Xlxm xm)
  {
    return this.xmMapper.addXm(xm);
  }
  
  public int updateXm(Xlxm xm)
  {
    return this.xmMapper.updateXm(xm);
  }
  
  public int deleteXm(List<Long> ids)
  {
    return this.xmMapper.deleteXm(ids);
  }
  
  public List<Xlxm> getType()
  {
    return this.xmMapper.getType();
  }
  
  public List<Xlxm> getPcXms(String type)
  {
    return this.xmMapper.getPcXms(type);
  }
  
  public List<Xlxm> getPcType()
  {
    return this.xmMapper.getPcType();
  }
  
  public String getPcTitleById(String id)
  {
    return this.xmMapper.getPcTitleById(id);
  }
  
  public List<Xlxm> getAllSpSrc(String type)
  {
    return this.xmMapper.getAllSpSrc(type);
  }
  
  public List<PcTest> getPcBy(String titles, String type)
  {
    return this.xmMapper.getPcBy(titles, type);
  }
  
  public int addPc(PcTest pt)
  {
    return this.xmMapper.addPc(pt);
  }
  
  public int updatePc(PcTest pt)
  {
    return this.xmMapper.updatePc(pt);
  }
  
  public int deletePc(List<Long> ids)
  {
    return this.xmMapper.deletePc(ids);
  }
  
  public List<PcTest> getTsetBy(String titles)
  {
    return this.xmMapper.getTsetBy(titles);
  }
  
  public int addTset(PcTest pt)
  {
    return this.xmMapper.addTset(pt);
  }
  
  public int updateTset(PcTest pt)
  {
    return this.xmMapper.updateTset(pt);
  }
  
  public int deleteTset(List<Long> ids)
  {
    return this.xmMapper.deleteTset(ids);
  }
}
