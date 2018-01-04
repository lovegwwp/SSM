package com.jyss.mst.mapper;

import com.jyss.mst.entity.GoodCategory;
import com.jyss.mst.entity.Goods;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface GoodsMapper
{
	  public abstract List<GoodCategory> getGoodCategoryBy(@Param("name") String name);
	  
	  public abstract int addGoodCategory(GoodCategory paramGoodCategory);
	  
	  public abstract int updateGoodCategory(GoodCategory paramGoodCategory);
	  
	  public abstract int deleteGoodCategory(@Param("ids") List<Long> ids);
	  
	  public abstract List<Goods> getGoodsBy(@Param("name") String name, @Param("categoryId") String categoryId);
	  
	  public abstract int addGoods(Goods paramGoods);
	  
	  public abstract int updateGoods(Goods paramGoods);
	  
	  public abstract int deleteGoods(@Param("ids") List<Long> ids);
  
  
}
