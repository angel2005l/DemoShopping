package com.edu.controller;

import java.math.BigDecimal;
import java.util.Scanner;

import com.edu.entity.User;
import com.edu.service.RoleService;
import com.edu.service.impl.AdministratorServiceImpl;
import com.edu.service.impl.MemberServiceImpl;
import com.edu.service.impl.UserServiceImpl;
import com.edu.util.ConsoleUtil;
import com.edu.util.StrUtil;

public class Menu {
	public static User user = null;

	public static void main(String[] arg) {
		try {
			new Menu().entry();
		} catch (InterruptedException e) {
			System.err.println("扫描器对象异常");
		}
	}

	public void entry() throws InterruptedException {
		String userCode = "";
		String password = "";
		ConsoleUtil.clear();
		Scanner in = new Scanner(System.in);
		System.out.println("\t系统首页界面");
		System.out.println("*****************************************");
		System.out.println("1.管理员登录");
		System.out.println("2.会员登录");
		System.out.println("3.员工登录");
		System.out.println("4.注册会员");
		System.out.println("5.退出");
		System.out.println("*****************************************");
		System.out.print("请输入操作：");
		if (in.hasNextInt()) {
			ConsoleUtil.lodaing();
			switch (in.nextInt()) {
			case 1:
				System.out.print("请输入管理员编号:");
				userCode = in.next();
				System.out.print("请输入管理员密码:");
				password = in.next();
				if (checkLogin(userCode, password, new AdministratorServiceImpl())) {
					new AdminMenu().showForAdmin();
				} else {
					entry();
				}
				break;
			case 2:
				System.out.print("请输入会员编号:");
				userCode = in.next();
				System.out.print("请输入会员密码:");
				password = in.next();
				if (checkLogin(userCode, password, new MemberServiceImpl())) {
					new MemberMenu().showForMember();
				} else {
					entry();
				}
				break;

			case 3:
				System.out.print("请输入员工编号:");
				userCode = in.next();
				System.out.print("请输员工密码:");
				password = in.next();
				if (checkLogin(userCode, password, new UserServiceImpl())) {
					new UserMenu().showForUser();
				} else {
					entry();
				}
				break;
			case 4:
				User addUser = new User();
				System.out.print("请输入会员编号:");
				addUser.setUserCode(in.next());
				System.out.print("请输入会员名称:");
				addUser.setUserName(in.next());
				System.out.print("请输入会员年龄:");
				addUser.setUserAge(in.next());
				System.out.print("请输入会员电话:");
				addUser.setUserPhone(in.next());
				addUser.setUserCredit(BigDecimal.ZERO);
				addUser.setUserPassword("123456");
				addUser.setUserLevel("member");
				if (new MemberServiceImpl().addRole(addUser) == 0) {
					System.out.println("会员注册成功");
				}
				entry();
				break;
			case 5:
				System.out.println("欢迎再次使用我行我素购物系统");
				ConsoleUtil.lodaing();
				break;
			default:
				ConsoleUtil.clear();
				System.out.println("您的输入有误，请重新输入");
				entry();
				break;
			}
		} else {
			// 输入的值不是 Int 类型
			ConsoleUtil.clear();
			System.out.println("非法操作，已退出系统");
		}
	}

	private boolean checkLogin(String userCode, String password, RoleService obj) {
		if (StrUtil.isBlank(userCode)) {
			System.err.println("用户编号不能为空");
			return false;
		}
		User userCheck = obj.loginRole(userCode);
		if (password.equals(userCheck.getUserPassword())) {
			System.out.println("登录成功");
			user = userCheck;
			return true;
		}
		return false;
	}
}
