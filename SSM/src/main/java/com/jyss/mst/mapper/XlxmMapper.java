package com.jyss.mst.mapper;

import com.jyss.mst.entity.PcTest;
import com.jyss.mst.entity.Xlxm;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface XlxmMapper
{
  public abstract List<Xlxm> getTitles(@Param("type") String type);
  
  public abstract List<Xlxm> getAllSpSrc(@Param("type") String type);
  
  public abstract List<Xlxm> getType();
  
  public abstract List<Xlxm> getPcXms(@Param("type") String type);
  
  public abstract List<Xlxm> getPcType();
  
  public abstract String getTitleById(@Param("id") String id);
  
  public abstract String getPcTitleById(@Param("id") String id);
  
  public abstract List<Xlxm> getXm();
  
  public abstract List<Xlxm> getXmBy(@Param("titles") String titles);
  
  public abstract int addXm(Xlxm paramXlxm);
  
  public abstract int updateXm(Xlxm paramXlxm);
  
  public abstract int deleteXm(@Param("ids") List<Long> ids);
  
  public abstract List<PcTest> getPcBy(@Param("titles") String titles, @Param("type") String type);
  
  public abstract int addPc(PcTest paramPcTest);
  
  public abstract int updatePc(PcTest paramPcTest);
  
  public abstract int deletePc(@Param("ids") List<Long> ids);
  
  public abstract List<PcTest> getTsetBy(@Param("titles") String titles);
  
  public abstract int addTset(PcTest paramPcTest);
  
  public abstract int updateTset(PcTest paramPcTest);
  
  public abstract int deleteTset(@Param("ids") List<Long> ids);
}
