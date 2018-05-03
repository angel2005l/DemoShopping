package com.edu.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.edu.entity.Goods;
import com.edu.service.GoodsService;
import com.edu.service.impl.GoodsServiceImpl;
import com.edu.util.ConsoleUtil;

public class GoodsMenu {
	private GoodsService goodsSer = new GoodsServiceImpl();

	// 菜单类

	// 管理员操作
	public void showForGoods() throws InterruptedException {
		String goodsCode = null;
		Scanner in = new Scanner(System.in);
		System.out.println("\t货品操作");
		System.out.println("*****************************************");
		System.out.println("1.货品查询");
		System.out.println("2.货品更新");
		System.out.println("3.货品删除");
		System.out.println("4.货品添加");
		System.out.println("5.返回上层");
		System.out.println("6.退出");
		System.out.println("*****************************************");
		System.out.print("请输入操作：");
		if (in.hasNextInt()) {
			ConsoleUtil.lodaing();
			switch (in.nextInt()) {
			case 1:
				showForSelGoods();
				break;
			case 2:
				System.out.print("请输入你要更新的货品编号:");
				goodsCode = in.next();
				System.out.print("请输入你要更新的货品价格:");
				String goodsPrice = in.next();
				System.out.print("请输入你要更新的货品价格:");
				String goodsDiscount = in.next();
				if (goodsSer.uptGoods(goodsCode, goodsPrice, goodsDiscount) == 0) {
					System.out.println("货品编号：" + goodsCode + "更新成功");
				}
				showForGoods();
				break;
			case 3:
				System.out.print("请输入你要删除的货品编号:");
				goodsCode = in.next();
				if (goodsSer.delGoods(goodsCode) == 0) {
					System.out.println("货品编号：" + goodsCode + "删除成功");
				}
				showForGoods();
				break;
			case 4:
				Goods addObj = new Goods();
				System.out.print("请输入你要更新的货品编号:");
				addObj.setGoodsCode(in.next());
				System.out.print("请输入你要更新的货品名称:");
				addObj.setGoodsName(in.next());
				System.out.print("请输入你要更新的货品价格:");
				addObj.setGoodsPrice(new BigDecimal(in.next()));
				System.out.print("请输入你要更新的货品折扣:");
				addObj.setGoodeDiscount(new BigDecimal(in.next()));
				if (goodsSer.addGoods(addObj) == 0) {
					System.out.println("货品添加成功");
				} else {
					System.out.println("货品添加失败");
				}
				showForGoods();
				break;
			case 5:
				new AdminMenu().showForAdmin();
				break;
			case 6:
				System.out.println("欢迎再次使用我行我素购物系统");
				ConsoleUtil.lodaing();
				break;
			default:
				ConsoleUtil.clear();
				System.out.println("您的输入有误，请重新输入");
				showForGoods();
				break;
			}
		} else {
			// 输入的值不是 Int 类型
			ConsoleUtil.clear();
			System.out.println("非法操作，已退出系统");
		}

	}

	public void showForSelGoods() throws InterruptedException {
		Scanner in = new Scanner(System.in);
		System.out.println("\t货品查询");
		System.out.println("*****************************************");
		System.out.println("1.全部查询");
		System.out.println("2.特定查询");
		System.out.println("3.返回上层");
		System.out.println("4.退出");
		System.out.println("*****************************************");
		System.out.print("请输入操作：");
		if (in.hasNextInt()) {
			ConsoleUtil.lodaing();
			switch (in.nextInt()) {
			case 1:// 全部查询
				List<Goods> selGoods = goodsSer.selGoods("");
				System.out.print("编号");
				System.out.print("	货品编号");
				System.out.print("	货品名称");
				System.out.print("		价格");
				System.out.println("	折扣");
				for (int i = 0; i < selGoods.size(); i++) {
					Goods goods = selGoods.get(i);
					System.out.print(i + 1);
					System.out.print("	" + goods.getGoodsCode());
					System.out.print("	" + goods.getGoodsName());
					System.out.print("		" + goods.getGoodsPrice().toString());
					System.out.println("	" + goods.getGoodeDiscount().toString());
				}
				showForSelGoods();
				break;
			case 2:
				System.out.print("请输入查询的货品编号：");
				if (in.hasNext()) {
					Goods goods = null;
					List<Goods> oneGoods = goodsSer.selGoods(in.next());
					if (!oneGoods.isEmpty()) {
						goods = oneGoods.get(0);
						System.out.print("编号");
						System.out.print("	货品编号");
						System.out.print("	货品名称");
						System.out.print("		价格");
						System.out.println("	折扣");
						System.out.print(1);
						System.out.print("	" + goods.getGoodsCode());
						System.out.print("	" + goods.getGoodsName());
						System.out.print("		" + goods.getGoodsPrice().toString());
						System.out.println("	" + goods.getGoodeDiscount().toString());
					}else{
						System.err.println("无该货品信息");
					}
				}
				showForSelGoods();
				break;
			case 3:
				showForGoods();
				break;
			case 4:
				ConsoleUtil.clear();
				System.out.println("欢迎再次使用我行我素购物系统");
				break;
			default:
				ConsoleUtil.clear();
				System.out.println("您的输入有误，请重新输入");
				ConsoleUtil.lodaing();
				showForSelGoods();
				break;
			}
		} else {
			// 输入的值不是 Int 类型
			ConsoleUtil.clear();
			System.err.println("非法操作，已退出系统");
		}
	}
	
}
