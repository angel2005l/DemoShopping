package com.edu.entity;

import java.math.BigDecimal;

public class User {
	private int id;
	private String userCode;
	private String userName;
	private String userAge;
	private BigDecimal userCredit;
	private String userPhone;
	private String userPassword;
	private String userLevel;

	/**
	 * 
	 */
	public User() {
	}

	/**
	 * @param id
	 * @param userCode
	 * @param userName
	 * @param userAge
	 * @param userCredit
	 * @param userPhone
	 * @param userPassword
	 * @param userLevel
	 */
	public User(int id, String userCode, String userName, String userAge, BigDecimal userCredit, String userPhone,
			String userPassword, String userLevel) {
		this.id = id;
		this.userCode = userCode;
		this.userName = userName;
		this.userAge = userAge;
		this.userCredit = userCredit;
		this.userPhone = userPhone;
		this.userPassword = userPassword;
		this.userLevel = userLevel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public BigDecimal getUserCredit() {
		return userCredit;
	}

	public void setUserCredit(BigDecimal userCredit) {
		this.userCredit = userCredit;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

}
