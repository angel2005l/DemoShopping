package com.edu.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.edu.entity.Goods;

public interface GoodsDao {
	//添加商品
	public boolean addGoods(Goods obj) throws SQLException;
	//删除商品
	public boolean delGoods(String goodsCode) throws SQLException;
	//查询商品
	public List<Goods> selGoods(String goodsCode) throws SQLException;
	//更新商品
	public boolean uptGoods(String goodsCode,Map<String,BigDecimal> uptData) throws SQLException;
}
