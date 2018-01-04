package com.jyss.mst.service.impl;

import com.jyss.mst.entity.GoodCategory;
import com.jyss.mst.entity.Goods;
import com.jyss.mst.mapper.GoodsMapper;
import com.jyss.mst.service.GoodsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl
  implements GoodsService
{
  @Autowired
  private GoodsMapper gsMapper;

@Override
public List<GoodCategory> getGoodCategoryBy(String name) {
	// TODO Auto-generated method stub
	return gsMapper.getGoodCategoryBy(name);
}

@Override
public int addGoodCategory(GoodCategory paramGoodCategory) {
	// TODO Auto-generated method stub
	return gsMapper.addGoodCategory(paramGoodCategory);
}

@Override
public int updateGoodCategory(GoodCategory paramGoodCategory) {
	// TODO Auto-generated method stub
	return gsMapper.updateGoodCategory(paramGoodCategory);
}

@Override
public int deleteGoodCategory(List<Long> ids) {
	// TODO Auto-generated method stub
	return gsMapper.deleteGoodCategory(ids);
}

@Override
public List<Goods> getGoodsBy(String name, String categoryId) {
	// TODO Auto-generated method stub
	return gsMapper.getGoodsBy(name, categoryId);
}

@Override
public int addGoods(Goods paramGoods) {
	// TODO Auto-generated method stub
	return gsMapper.addGoods(paramGoods);
}

@Override
public int updateGoods(Goods paramGoods) {
	// TODO Auto-generated method stub
	return gsMapper.updateGoods(paramGoods);
}

@Override
public int deleteGoods(List<Long> ids) {
	// TODO Auto-generated method stub
	return gsMapper.deleteGoods(ids);
}
  
  
}
