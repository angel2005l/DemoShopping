package com.edu.controller;

import java.util.Scanner;

import com.edu.entity.User;
import com.edu.service.impl.MemberServiceImpl;
import com.edu.util.ConsoleUtil;
import com.edu.util.StrUtil;

public class MemberMenu {
	private MemberServiceImpl userSer = new MemberServiceImpl();
	public void showForMember() throws InterruptedException {
		User memberObj = Menu.user;
		Scanner in = new Scanner(System.in);
		System.out.println("\t会员操作界面");
		System.out.println("*****************************************");
		System.out.println("1.信息查询");
		System.out.println("2.修改密码");
		System.out.println("3.返回上层");
		System.out.println("4.退出");
		System.out.println("*****************************************");
		System.out.print("请输入操作：");
		if (in.hasNextInt()) {
			ConsoleUtil.lodaing();
			switch (in.nextInt()) {
			case 1:
				User selMember = userSer.selMember(memberObj.getUserCode());
				System.out.print("编号");
				System.out.print("	会员编号");
				System.out.print("	会员名称");
				System.out.print("	年龄");
				System.out.print(" 联系方式");
				System.out.println("	积分");
				if(selMember!=null){
					System.out.print("1");
					System.out.print("	"+selMember.getUserCode());
					System.out.print("	"+selMember.getUserName());
					System.out.print("	"+selMember.getUserAge());
					System.out.print(" "+selMember.getUserPhone());
					System.out.println("	"+selMember.getUserCredit());
				}
				showForMember();
				break;
			case 2:
				System.out.print("请输入旧的密码:");
				String passwordOld = in.next();
				if(StrUtil.notBlank(passwordOld)&& memberObj.getUserPassword().equals(passwordOld)){
					System.out.print("请输入新的密码:");
					String password = in.next();
					if(userSer.uptPasswordRole(memberObj.getUserCode(), password)==0){
						System.out.println("密码修改成功");
					}
					showForMember();
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
				showForMember();
				break;
			}
		} else {
			// 输入的值不是 Int 类型
			ConsoleUtil.clear();
			System.out.println("非法操作，已退出系统");
		}
	}
}
