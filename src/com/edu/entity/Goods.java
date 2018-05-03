package com.edu.entity;

import java.math.BigDecimal;

public class Goods {
	private int id;
	private String goodsCode;
	private String goodsName;
	private BigDecimal goodsPrice;
	private BigDecimal goodeDiscount;
	
	/**
	 * 
	 */
	public Goods() {
	}

	/**
	 * @param id
	 * @param goodsCode
	 * @param goodsName
	 * @param goodsPrice
	 * @param goodeDiscount
	 */
	public Goods(int id, String goodsCode, String goodsName, BigDecimal goodsPrice, BigDecimal goodeDiscount) {
		this.id = id;
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodeDiscount = goodeDiscount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getGoodeDiscount() {
		return goodeDiscount;
	}

	public void setGoodeDiscount(BigDecimal goodeDiscount) {
		this.goodeDiscount = goodeDiscount;
	}
}
