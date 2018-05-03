package com.edu.dao;

import java.sql.SQLException;
import java.util.List;

import com.edu.entity.User;

public interface UserDao {
	//用户查询
	public List<User> selUser(String userCode ,String userLevel) throws SQLException;
	//用户注销
	public boolean delUser(String userCode) throws SQLException;
	//用户更新
	public boolean uptUser(String userCode,String userName,String password) throws SQLException;
	//用户新增
	public boolean addUser(User user) throws SQLException;
}
