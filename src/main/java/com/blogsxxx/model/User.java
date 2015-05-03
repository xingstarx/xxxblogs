package com.blogsxxx.model;
/**
 * @desc 用户信息
 * @author xiongxingxing
 *
 */
public class User {
	private String userName;
	private String password;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}
	
}
