package com.edu.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.edu.dao.UserDao;
import com.edu.dao.Impl.UserDaoImpl;
import com.edu.entity.User;
import com.edu.service.RoleService;
import com.edu.util.StrUtil;

public class UserServiceImpl implements RoleService {
	private UserDao userDao = new UserDaoImpl();
	private String defaultPass = "123456";

	@Override
	public User loginRole(String userCode) {
		if (StrUtil.isBlank(userCode)) {
			System.err.println("用户编号不能为空");
			return null;
		}
		try {
			List<User> selUser = userDao.selUser(userCode, "user");
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

	// 待开发
	@Override
	public int uptRole(String userCode, String userName, String userPhone, String userAge) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delRole(String userCode) {
		if (StrUtil.isBlank(userCode)) {
			System.err.println("用户编号不能为空");
			return 1;
		}
		try {
			if (userDao.delUser(userCode)) {
				return 0;
			} else {
				return 1;
			}
		} catch (SQLException e) {
			System.err.println("删除员工，数据库操作异常" + e.getMessage());
			return -1;
		}
	}

	@Override
	public int addRole(User user) {
		if (user != null) {
			try {
				if (userDao.addUser(user)) {
					return 0;
				} else {
					System.err.println("新增员工失败 ,请重试...");
					return 1;
				}
			} catch (SQLException e) {
				System.err.println("新增员工，数据库操作异常" + e.getMessage());
				return -1;
			}
		} else {
			System.err.println("新增员工信息不存在");
			return 1;
		}
	}

	@Override
	public int uptPasswordRole(String userCode, String userPassword) {
		if (StrUtil.isBlank(userCode)) {
			System.err.println("用户编号不能为空");
			return 1;
		}

		try {
			if (userDao.uptUser(userCode, "", (StrUtil.isBlank(userPassword)) ? defaultPass : userPassword)) {
				return 0;
			} else {
				System.err.println();
				return 1;
			}
		} catch (SQLException e) {
			System.err.println("修改员工密码,数据库操作失败...");
			return -1;
		}
	}

	public User selUser(String userCode) {
		if (StrUtil.isBlank(userCode)) {
			System.err.println("用户编号不能为空");
			return null;
		}
		try {
			List<User> selUser = userDao.selUser(userCode, "user");
			if (selUser.isEmpty()) {
				System.err.println("查询失败...");
				return null;
			} else {
				return selUser.get(0);
			}
		} catch (SQLException e) {
			System.err.println("查询会员信息，数据库操作异常" + e.getMessage());
			return null;
		}
	}

	public List<User> selUsers() {
		try {
			List<User> selUser = userDao.selUser("", "user");
			if (selUser.isEmpty()) {
				System.err.println("查询失败...");
				return null;
			} else {
				return selUser;
			}
		} catch (SQLException e) {
			System.err.println("查询会员信息，数据库操作异常" + e.getMessage());
			return null;
		}
	}

}
