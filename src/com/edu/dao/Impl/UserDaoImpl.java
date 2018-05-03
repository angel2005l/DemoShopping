package com.edu.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.dao.UserDao;
import com.edu.entity.User;
import com.edu.util.SqlUtil;
import com.edu.util.StrUtil;

public class UserDaoImpl implements UserDao {
	private Connection conObj = SqlUtil.getConnection();

	@Override
	public List<User> selUser(String userCode, String userLevel) throws SQLException {
		List<User> selUsers = new ArrayList<User>();
		StringBuffer sql = new StringBuffer("select * from user where 1=1 ");
		if (StrUtil.notBlank(userCode)) {
			sql.append(" and user_code = ? ");
		}
		if (StrUtil.notBlank(userLevel)) {
			sql.append(" and user_level = ? ");
		}
		int i = 1;
		sql.append(" limit 0,10");
		PreparedStatement ptms = conObj.prepareStatement(sql.toString());
		if (StrUtil.notBlank(userCode)) {
			ptms.setString(i++, userCode);
		}
		if (StrUtil.notBlank(userLevel)) {
			ptms.setString(i, userLevel);
		}
		ResultSet rs = ptms.executeQuery();
		while (rs.next()) {
			selUsers.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBigDecimal(5),
					rs.getString(6), rs.getString(7), rs.getString(8)));
		}
		return selUsers;
	}

	@Override
	public boolean delUser(String userCode) throws SQLException {
		String sql = "delete from user where user_code = ?";
		PreparedStatement ptms = conObj.prepareStatement(sql);
		ptms.setString(1, userCode);
		int rts = ptms.executeUpdate();
		if (rts > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean uptUser(String userCode, String userName, String password) throws SQLException {
		String sql = "update user set user_name = ?, user_password = ? where user_code = ?";
		PreparedStatement ptms = conObj.prepareStatement(sql);
		ptms.setString(1, userName);
		ptms.setString(2, password);
		ptms.setString(3, userCode);
		int rts = ptms.executeUpdate();
		if (rts > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addUser(User user) throws SQLException {
		String sql = "insert into user(user_code, user_name,user_age,user_credit,user_phone,user_password,user_level) values(?,?,?,?,?,?,?)";
		PreparedStatement ptms = conObj.prepareStatement(sql);
		ptms.setString(1, user.getUserCode());
		ptms.setString(2, user.getUserName());
		ptms.setString(3, user.getUserAge());
		ptms.setBigDecimal(4, user.getUserCredit());
		ptms.setString(5, user.getUserPhone());
		ptms.setString(6, user.getUserPassword());
		ptms.setString(7, user.getUserLevel());
		int rts = ptms.executeUpdate();
		if (rts > 0) {
			return true;
		}
		return false;
	}

	public boolean uptCredit(User user) throws SQLException {
		String sql = "update user set user_credit = ?  where userCode = ?";
		PreparedStatement ptms = conObj.prepareStatement(sql);
		ptms.setBigDecimal(1, user.getUserCredit());
		ptms.setString(2, user.getUserCode());
		int rts = ptms.executeUpdate();
		if (rts > 0) {
			return true;
		}
		return false;
	}
}
