package com.edu.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edu.dao.GoodsDao;
import com.edu.dao.Impl.GoodsDaoImpl;
import com.edu.entity.Goods;
import com.edu.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
	private GoodsDao goodsDao = new GoodsDaoImpl();

	// 添加商品
	@Override
	public int addGoods(Goods obj) {
		try {
			if (goodsDao.addGoods(obj))
				return 0;
			else{
				return 1;				
			}
		} catch (SQLException e) {
			System.err.println("添加货品，数据库操作失败:" + e.getMessage());
			return -1;
		}
	}

	// 删除商品
	@Override
	public int delGoods(String goodsCode) {
		try {
			if (goodsDao.delGoods(goodsCode))
				return 0;
			else
				return 1;
		} catch (SQLException e) {
			System.err.println("删除货品，数据库操作失败:" + e.getMessage());
			return -1;
		}
	}

	// 查询商品
	@Override
	public List<Goods> selGoods(String goodsCode) {
		try {
			return goodsDao.selGoods(goodsCode);
		} catch (SQLException e) {
			System.err.println("查询货品，数据库操作失败:" + e.getMessage());
			return null;
		}
	}

	// 更新商品
	@Override
	public int uptGoods(String goodsCode, String goodsPrice, String goodsDiscount) {
		Map<String, BigDecimal> uptData = new HashMap<String, BigDecimal>();
		Goods goods = null;
		List<Goods> selGoods;
		try {
			selGoods = goodsDao.selGoods(goodsCode);
			if (selGoods.isEmpty()) {
				System.err.println("更新货品不存在");
				return 1;
			} else if (selGoods.size() != 1) {
				System.err.println("更新货品不唯一，更新失败");
				return 1;
			} else {
				goods = selGoods.get(0);
			}
			if (!goodsPrice.equals("")) {
				uptData.put("goodsPrice", goods.getGoodsPrice());
			} else {
				uptData.put("goodsPrice", new BigDecimal(goodsPrice));
			}
			if (goodsDiscount.equals("")) {
				uptData.put("goodsDiscount", goods.getGoodeDiscount());
			} else {
				uptData.put("goodsDiscount", new BigDecimal(goodsDiscount));
			}
			if (goodsDao.uptGoods(goodsCode, uptData)) {
				return 0;
			}
			return 1;
		} catch (SQLException e) {
			System.err.println("更新货品，数据库操作失败:" + e.getMessage());
			return -1;
		}
	}

}
