package com.ss.may.jb.entities;

public class UserRole {
	
	private int userRoleId;
	private String name;
	
	public UserRole(int id, String name) {
		setUserRoleId(id);
		setName(name);
	}
	
	public int getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
