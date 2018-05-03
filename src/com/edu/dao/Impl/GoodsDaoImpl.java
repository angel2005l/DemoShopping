package com.edu.dao.Impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.edu.dao.GoodsDao;
import com.edu.entity.Goods;
import com.edu.util.SqlUtil;

public class GoodsDaoImpl implements GoodsDao {
	private Connection conObj = null;

	public GoodsDaoImpl() {
		conObj = SqlUtil.getConnection();
	}

	@Override
	public boolean addGoods(Goods obj) throws SQLException {
		String sql = "insert into goods(goods_code,goods_name,goods_price,goods_discount) value(?,?,?,?)";
		PreparedStatement pstm = conObj.prepareStatement(sql);
		pstm.setString(1, obj.getGoodsCode());
		pstm.setString(2, obj.getGoodsName());
		pstm.setBigDecimal(3, obj.getGoodsPrice());
		pstm.setBigDecimal(4, obj.getGoodeDiscount());
		int rst = pstm.executeUpdate();
		if (rst > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delGoods(String goodsCode) throws SQLException {
		String sql = "delete from goods where goods_code=?";
		PreparedStatement ptms = conObj.prepareStatement(sql);
		ptms.setString(1, goodsCode);
		int rst = ptms.executeUpdate();
		if (rst > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Goods> selGoods(String goodsCode) throws SQLException {
		StringBuffer sql = new StringBuffer("select * from goods where 1=1");
		List<Goods> goodsData = new ArrayList<Goods>();
		if (!goodsCode.equals("")) {
			sql.append(" and goods_code = ? ");
		}
		sql.append(" Limit 0,10");
		PreparedStatement ptms = conObj.prepareStatement(sql.toString());
		if (!goodsCode.equals("")) {
			ptms.setString(1, goodsCode);
		}
		ResultSet rs = ptms.executeQuery();
		while (rs.next()) {
			goodsData.add(new Goods(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getBigDecimal(4),
					rs.getBigDecimal(5)));
		}
		return goodsData;
	}

	@Override
	public boolean uptGoods(String goodsCode, Map<String, BigDecimal> uptData) throws SQLException {
		String sql = "update goods set goods_price=? ,goods_discount =? where goods_code=? ";
		PreparedStatement ptms = conObj.prepareStatement(sql);
		ptms.setBigDecimal(1, uptData.get("goodsPrice"));
		ptms.setBigDecimal(2, uptData.get("goodsDiscount"));
		ptms.setString(3, goodsCode);
		int rst = ptms.executeUpdate();
		if (rst > 0) {
			return true;
		} else {
			return false;
		}
	}

}
