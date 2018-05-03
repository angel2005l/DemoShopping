package com.edu.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.edu.entity.User;
import com.edu.service.impl.AdministratorServiceImpl;
import com.edu.service.impl.MemberServiceImpl;
import com.edu.service.impl.UserServiceImpl;
import com.edu.util.ConsoleUtil;
import com.edu.util.StrUtil;

public class AdminMenu {
	private AdministratorServiceImpl adminSer = new AdministratorServiceImpl();
	public void showForAdmin() throws InterruptedException {
		User userObj = Menu.user;
		Scanner in = new Scanner(System.in);
		System.out.println("\t管理员界面");
		System.out.println("*****************************************");
		System.out.println("1.货品管理");
		System.out.println("2.会员管理");
		System.out.println("3.员工管理");
		System.out.println("4.密码修改");
		System.out.println("5.返回上层");
		System.out.println("6.退出");
		System.out.println("*****************************************");
		System.out.print("请输入操作：");
		if (in.hasNextInt()) {
			ConsoleUtil.lodaing();
			switch (in.nextInt()) {
			case 1:
				new GoodsMenu().showForGoods();
				break;
			case 2:
				showForMemberByAdmin();
				break;
			case 3:
				showForUserByAdmin();
				break;
			case 4:
				System.out.print("请输入旧的密码:");
				String passwordOld = in.next();
				if(StrUtil.notBlank(passwordOld)&& userObj.getUserPassword().equals(passwordOld)){
					System.out.print("请输入新的密码:");
					String password = in.next();
					if(adminSer.uptPasswordRole(userObj.getUserCode(), password)==0){
						System.out.println("密码修改成功");
					}
					showForAdmin();
				}else{
					System.err.println("输入的旧密码不一致，返回登录界面");
					new Menu().entry();
				}
				break;
			case 5:
				new Menu().entry();
				break;
			case 6:
				System.out.println("欢迎再次使用我行我素购物系统");
				ConsoleUtil.lodaing();
				break;
			default:
				ConsoleUtil.clear();
				System.out.println("您的输入有误，请重新输入");
				showForAdmin();
				break;
			}
		} else {
			// 输入的值不是 Int 类型
			ConsoleUtil.clear();
			System.out.println("非法操作，已退出系统");
		}
	}

	private void showForUserByAdmin() throws InterruptedException {
		UserServiceImpl userSer = new UserServiceImpl();
		Scanner in = new Scanner(System.in);
		String userCode = "";
		System.out.println("\t员工管理界面");
		System.out.println("*****************************************");
		System.out.println("1.全部查询");
		System.out.println("2.特定查询");
		System.out.println("3.重置密码");
		System.out.println("4.添加员工");
		System.out.println("5.返回上层");
		System.out.println("6.退出");
		System.out.println("*****************************************");
		System.out.print("请输入操作：");
		if (in.hasNextInt()) {
			ConsoleUtil.lodaing();
			switch (in.nextInt()) {
			case 1:
				List<User> selMembers = userSer.selUsers();
				if (selMembers != null) {
					System.out.print("编号");
					System.out.print("	员工编号");
					System.out.print("	员工名称");
					System.out.print("	年龄");
					System.out.print(" 联系方式");
					for (int i = 0; i < selMembers.size(); i++) {
						User user = selMembers.get(i);
						System.out.print(i + 1);
						System.out.print("	" + user.getUserCode());
						System.out.print("	" + user.getUserName());
						System.out.print("	" + user.getUserAge());
						System.out.print(" " + user.getUserPhone());
					}
				}
				showForMemberByAdmin();
				break;
			case 2:
				System.out.print("请输入员工编码:");
				userCode = in.next();
				User selUser = userSer.selUser(userCode);
				System.out.print("编号");
				System.out.print("	会员编号");
				System.out.print("	会员名称");
				System.out.print("	年龄");
				System.out.print(" 联系方式");
				if (selUser != null) {
					System.out.print("1");
					System.out.print("	" + selUser.getUserCode());
					System.out.print("	" + selUser.getUserName());
					System.out.print("	" + selUser.getUserAge());
					System.out.print(" " + selUser.getUserPhone());
					System.out.println("	" + selUser.getUserCredit());
				}
				showForUserByAdmin();
				break;

			case 3:
				System.out.print("请输入员工编码:");
				userCode = in.next();
				if (userSer.uptPasswordRole(userCode, "") == 0) {
					System.out.println("密码重置成功");
				}
				break;
			case 4:
				User addUser = new User();
				System.out.print("请输入员工编号:");
				addUser.setUserCode(in.next());
				System.out.print("请输入员工名称:");
				addUser.setUserName(in.next());
				System.out.print("请输入员工年龄:");
				addUser.setUserAge(in.next());
				System.out.print("请输入员工电话:");
				addUser.setUserPhone(in.next());
				addUser.setUserCredit(BigDecimal.ZERO);
				addUser.setUserPassword("123456");
				addUser.setUserLevel("user");
				if (userSer.addRole(addUser) == 0) {
					System.out.println("会员注册成功");
				}
				showForUserByAdmin();
				break;
			case 5:
				showForAdmin();
				break;
			case 6:
				System.out.println("欢迎再次使用我行我素购物系统");
				ConsoleUtil.lodaing();
				break;
			default:
				ConsoleUtil.clear();
				System.out.println("您的输入有误，请重新输入");
				showForAdmin();
				break;
			}
		} else {
			// 输入的值不是 Int 类型
			ConsoleUtil.clear();
			System.out.println("非法操作，已退出系统");
		}

	}

	private void showForMemberByAdmin() throws InterruptedException {
		MemberServiceImpl memberSer = new MemberServiceImpl();
		Scanner in = new Scanner(System.in);
		String userCode = "";
		System.out.println("\t会员管理界面");
		System.out.println("*****************************************");
		System.out.println("1.全部查询");
		System.out.println("2.特定查询");
		System.out.println("3.重置密码");
		System.out.println("4.添加会员");
		System.out.println("5.返回上层");
		System.out.println("6.退出");
		System.out.println("*****************************************");
		System.out.print("请输入操作：");
		if (in.hasNextInt()) {
			ConsoleUtil.lodaing();
			switch (in.nextInt()) {
			case 1:
				List<User> selMembers = memberSer.selMembers();
				if (selMembers != null) {
					System.out.print("编号");
					System.out.print("	会员编号");
					System.out.print("	会员名称");
					System.out.print("	年龄");
					System.out.print(" 联系方式");
					System.out.println("	积分");
					for (int i = 0; i < selMembers.size(); i++) {
						User user = selMembers.get(i);
						System.out.print(i + 1);
						System.out.print("	" + user.getUserCode());
						System.out.print("	" + user.getUserName());
						System.out.print("	" + user.getUserAge());
						System.out.print(" " + user.getUserPhone());
						System.out.println("	" + user.getUserCredit());
					}
				}
				showForMemberByAdmin();
				break;
			case 2:
				System.out.print("请输入会员编码:");
				userCode = in.next();
				User selMember = memberSer.selMember(userCode);
				System.out.print("编号");
				System.out.print("	会员编号");
				System.out.print("	会员名称");
				System.out.print("	年龄");
				System.out.print(" 联系方式");
				System.out.println("	积分");
				if (selMember != null) {
					System.out.print("1");
					System.out.print("	" + selMember.getUserCode());
					System.out.print("	" + selMember.getUserName());
					System.out.print("	" + selMember.getUserAge());
					System.out.print(" " + selMember.getUserPhone());
					System.out.println("	" + selMember.getUserCredit());
				}
				showForMemberByAdmin();
				break;

			case 3:
				System.out.print("请输入会员编码:");
				userCode = in.next();
				if (memberSer.uptPasswordRole(userCode, "") == 0) {
					System.out.println("密码重置成功");
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
				if (memberSer.addRole(addUser) == 0) {
					System.out.println("会员注册成功");
				}
				showForMemberByAdmin();
				break;
			case 5:
				showForAdmin();
				break;
			case 6:
				System.out.println("欢迎再次使用我行我素购物系统");
				ConsoleUtil.lodaing();
				break;
			default:
				ConsoleUtil.clear();
				System.out.println("您的输入有误，请重新输入");
				showForAdmin();
				break;
			}
		} else {
			// 输入的值不是 Int 类型
			ConsoleUtil.clear();
			System.out.println("非法操作，已退出系统");
		}
	}
}
