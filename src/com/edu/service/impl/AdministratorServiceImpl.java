package com.edu.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.edu.dao.UserDao;
import com.edu.dao.Impl.UserDaoImpl;
import com.edu.entity.User;
import com.edu.service.RoleService;
import com.edu.util.StrUtil;

public class AdministratorServiceImpl implements RoleService {
	private UserDao userDao = new UserDaoImpl();

	@Override
	public User loginRole(String userCode) {
		if (StrUtil.isBlank(userCode)) {
			System.err.println("用户编号不能为空");
			return null;
		}
		try {
			List<User> selUser = userDao.selUser(userCode,"admin");
			if (selUser.isEmpty()) {
				System.err.println("用户编号不存在");
				return null;
			} else {
				return selUser.get(0);
			}
		} catch (SQLException e) {
			System.err.println("数据库操作失败" + e.getMessage());
			return null;
		}
	}

	// 未开发
	@Override
	public int uptRole(String userCode, String userName, String userPhone, String userAge) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 未开发
	@Override
	public int delRole(String userCode) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 未开发
	@Override
	public int addRole(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int uptPasswordRole(String userCode, String userPassword) {
		if (StrUtil.isBlank(userCode)) {
			System.err.println("用户编号不能为空");
			return 1;
		}
		if (StrUtil.isBlank(userPassword)) {
			System.err.println("新密码不为空");
			return 1;
		}
		try {
			if (userDao.uptUser(userCode, "", userPassword)) {
				return 0;
			} else {
				System.err.println();
				return 1;
			}
		} catch (SQLException e) {
			System.err.println("修改管理员密码,数据库操作失败...");
			return -1;
		}
	}
}
