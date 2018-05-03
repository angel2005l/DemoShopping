package com.edu.service;

import com.edu.entity.User;

public interface RoleService {
	//登录
	public User loginRole(String userCode);
	//更新
	public int uptRole(String userCode,String userName,String userPhone,String userAge);
	//注销
	public int delRole(String userCode);
	//添加
	public int addRole(User user);
	//更新密码
	public int uptPasswordRole(String userCode,String userPassword);
	
	
}
