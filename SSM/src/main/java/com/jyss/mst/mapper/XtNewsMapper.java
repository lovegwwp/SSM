package com.jyss.mst.mapper;

import com.jyss.mst.entity.Xtgx;
import com.jyss.mst.entity.Xtnews;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface XtNewsMapper
{
	public abstract List<Xtnews> getPatXtNews();
	  
	  public abstract List<Xtgx> getPatXtgx();
	  
	  public abstract List<Xtnews> getXtNews(@Param("keytype") String keytype);
	  
	  public abstract List<Xtnews> getXtNewsBy(@Param("title") String title, @Param("keytype") String keytype);
	  
	  public abstract int addXtNews(Xtnews paramXtnews);
	  
	  public abstract int updateXtNews(Xtnews paramXtnews);
	  
	  public abstract int deleteXtNews(@Param("ids") List<Long> ids);
	  
	  public abstract List<Xtgx> getXtgx();
	  
	  public abstract List<Xtgx> getXtgxBy(@Param("titles") String title);
	  
	  public abstract int addXtgx(Xtgx paramXtgx);
	  
	  public abstract int updateXtgx(Xtgx paramXtgx);
	  
	  public abstract int deleteXtgx(@Param("ids") List<Long> ids);
}
