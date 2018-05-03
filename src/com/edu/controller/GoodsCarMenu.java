package com.edu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.edu.entity.Goods;
import com.edu.entity.User;
import com.edu.service.GoodsService;
import com.edu.service.impl.GoodsServiceImpl;
import com.edu.service.impl.MemberServiceImpl;
import com.edu.util.ConsoleUtil;

public class GoodsCarMenu {
	boolean isBuy = true;

	public void showForGoodsCar() {
		GoodsService goosdSer = new GoodsServiceImpl();
		MemberServiceImpl memberSer = new MemberServiceImpl();
		List<Map<String, Object>> goodsData = new ArrayList<Map<String, Object>>();
		Scanner in = new Scanner(System.in);
		System.out.println("\t购物结算界面");
		System.out.println("*****************");
		System.out.println("请输入会员编码");
		boolean isMember = false;
		User memberUser = memberSer.selMember(in.next());
		if (memberUser != null) {
			isMember = true;
		}
		while (isBuy) {
			System.out.print("请输入货品编号操作：");
			String goodsCode = in.next();
			System.out.print("请输入货品数量：");
			int qty = in.nextInt();
			List<Goods> selGoods = goosdSer.selGoods(goodsCode);
			if (selGoods != null && !selGoods.isEmpty()) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("goods", selGoods.get(0));
				dataMap.put("qty", qty);
				goodsData.add(dataMap);
				System.out.println("已计入数据");
			}
			System.out.println("是否继续Y/N");
			String isGo = in.next();
			if ("N".equals(isGo.toUpperCase())) {
				isBuy = false;
			}
		}
		ConsoleUtil.clear();
		BigDecimal price = BigDecimal.ZERO;
		int goodsNum = 0;
		System.out.println("购物清单");
		System.out.println("*****************");
		System.out.print("	货品编号");
		System.out.print("	货品名称");
		System.out.print("		价格");
		System.out.println("	数量");
		for (Map<String, Object> map : goodsData) {
			Goods goodsObj = (Goods) map.get("goods");
			int qty = (int) map.get("qty");
			System.out.print("	" + goodsObj.getGoodsCode());
			System.out.print("	" + goodsObj.getGoodsName());
			System.out.print("		" + goodsObj.getGoodsPrice());
			System.out.println("	" + qty);

			price = price.add(goodsObj.getGoodsPrice().multiply(new BigDecimal(qty)).multiply(isMember? goodsObj.getGoodeDiscount():BigDecimal.ONE));
			goodsNum += qty;
		}
		System.out.print("消费价格" + price);
		System.out.println("		总数量 " + goodsNum);

		System.out.print("顾客支付金额：");
		String money = in.next();
		System.out.println("*****************");
		System.out.println("找零:" + new BigDecimal(money).subtract(price));
		
		if(isMember){
			memberUser.setUserCredit(memberUser.getUserCredit().add(price.divide(new BigDecimal("100"), 0,BigDecimal.ROUND_UP)));
			if(memberSer.uptCredit(memberUser)==0){
				System.out.println("*****************");
				System.out.println("累计积分："+memberUser.getUserCredit());
			}
		}
	}
}
