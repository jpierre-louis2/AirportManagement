package com.ss.may.jb.entities;

public class User {

	private int userId;
	private int roleId;
	private String givenName;
	private String familyName;
	private String username;
	private String email;
	private String password;
	private String phone;
	
	
	public User(int uId, int rId, String gName, String fName, String uName, String mail, String pass, String pho) {
		setUserId(uId);
		setRoleId(rId);
		setGivenName(gName);
		setFamilyName(fName);
		setUsername(uName);
		setEmail(mail);
		setPassword(pass);
		setPhone(pho);
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
