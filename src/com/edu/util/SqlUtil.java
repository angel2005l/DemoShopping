package com.edu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class SqlUtil {
	// 在内部定义一个私有的静态实例
	private static SqlUtil unit = new SqlUtil();
	// 数据库用户名
	private static String user;
	// 数据库密码
	private static String password;
	// 数据库驱动
	private static String jdbcDriver;
	// 数据库连接的url
	private static String url;
	// 连接对象
	private static Connection conn = null;

	// 声明私有的构造函数
	private SqlUtil() {
		user = "root";
		password = "1234";
		jdbcDriver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/javadesign?useUnicode=true&characterEncoding=UTF8";
	}

	static {
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 获得连接方法
	public static Connection getConnection() {
		if (unit == null) {
			unit = new SqlUtil();
		}
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;

	}

	public static void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException ex1) {
			ex1.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (SQLException ex2) {
				ex2.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
						conn = null;
					}
				} catch (SQLException ex3) {
					ex3.printStackTrace();
				}
			}
		}
	}

}
