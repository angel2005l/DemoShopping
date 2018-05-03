package com.edu.service;

import java.util.List;

import com.edu.entity.Goods;

public interface GoodsService {
	//添加商品
	public int addGoods(Goods obj);
	//删除商品
	public int delGoods(String goodsCode);
	//查询商品
	public List<Goods> selGoods(String goodsCode);
	//更新商品
	public int uptGoods(String goodsCode, String goodsPrice ,String goodsDiscount);
}
