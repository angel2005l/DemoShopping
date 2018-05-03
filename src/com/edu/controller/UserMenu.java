package com.edu.controller;

import java.util.Scanner;

import com.edu.entity.User;
import com.edu.service.RoleService;
import com.edu.service.impl.UserServiceImpl;
import com.edu.util.ConsoleUtil;
import com.edu.util.StrUtil;

public class UserMenu {
	private RoleService userSer = new UserServiceImpl();
	public void showForUser() throws InterruptedException {
		User userObj = Menu.user;
		Scanner in = new Scanner(System.in);
		System.out.println("\t员工操作界面");
		System.out.println("*****************************************");
		System.out.println("1.购物结算");
		System.out.println("2.修改密码");
		System.out.println("3.返回上层");
		System.out.println("4.退出");
		System.out.println("*****************************************");
		System.out.print("请输入操作：");
		if (in.hasNextInt()) {
			ConsoleUtil.lodaing();
			switch (in.nextInt()) {
			case 1:
				new GoodsCarMenu().showForGoodsCar();
				showForUser();
				break;
			case 2:
				System.out.print("请输入旧的密码:");
				String passwordOld = in.next();
				if(StrUtil.notBlank(passwordOld)&& userObj.getUserPassword().equals(passwordOld)){
					System.out.print("请输入新的密码:");
					String password = in.next();
					if(userSer.uptPasswordRole(userObj.getUserCode(), password)==0){
						System.out.println("密码修改成功");
					}
					showForUser();
				}else{
					System.err.println("输入的旧密码不一致，返回登录界面");
					new Menu().entry();
				}
				break;
			case 3:
				new Menu().entry();
				break;
			case 4:
				System.out.println("欢迎再次使用我行我素购物系统");
				Menu.user = null;
				ConsoleUtil.lodaing();
				break;
			default:
				ConsoleUtil.clear();
				System.out.println("您的输入有误，请重新输入");
				showForUser();
				break;
			}
		} else {
			// 输入的值不是 Int 类型
			ConsoleUtil.clear();
			System.out.println("非法操作，已退出系统");
		}
	}
}
