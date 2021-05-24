package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.may.jb.entities.User;

public class UserDAO extends BaseDAO<User>{
	//Supports Database Connection
	public UserDAO(Connection conn) {
			super(conn);
		}
	
	//Returns an ArrayList of users based on the Select Statement sent
	public ArrayList<User> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<User> users = new ArrayList<User>();
		while(rs.next()) {
			int id = rs.getInt("id");
			int roleId = rs.getInt("role_id");
			String gName = rs.getString("given_name");
			String fName = rs.getString("family_name");
			String uName = rs.getString("username");
			String email = rs.getString("email");
			String pass = rs.getString("password");
			String phone = rs.getString("phone");
			User user = new User(id, roleId, gName, fName, uName, email, pass, phone);
			users.add(user);
		}
		return users;
	}
	//Returns the user specified by User Id
	public User getUser(int id) throws ClassNotFoundException, SQLException{
		ArrayList<User> users = read("SELECT id, role_id, given_name, family_name, username, email, password, phone FROM user WHERE id=?", new Object[] {id});
		return users.get(0);
	}
	//Returns an ArrayList of all users in User Table
	public ArrayList<User>getAllUsers() throws ClassNotFoundException, SQLException{
		ArrayList<User> users = read("SELECT id, role_id, given_name, family_name, username, email, password, phone FROM user WHERE id!=0", null);
		return users;
	}
	//Returns an ArrayList of all users in User Table with Normal User ROle
	public ArrayList<User>getAllNormal() throws ClassNotFoundException, SQLException{
		ArrayList<User> users = read("SELECT id, role_id, given_name, family_name, username, email, password, phone FROM user WHERE role_id=2", null);
		return users;
	}
	//Returns an ArrayList of all users in User Table with Employee Role
	public ArrayList<User>getAllEmployees() throws ClassNotFoundException, SQLException{
		ArrayList<User> users = read("SELECT id, role_id, given_name, family_name, username, email, password, phone FROM user WHERE role_id=1", null);
		return users;
	}
	//Inserts a new user into database
	public void addUser(User user) throws ClassNotFoundException, SQLException{
		save("INSERT INTO user (id, role_id, given_name, family_name, username, email, password, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] {user.getUserId(), user.getRoleId(), user.getGivenName(), user.getFamilyName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getPhone()});
	}
	//Updates the information for an user specified by User Id
	public void updateUser(User user) throws ClassNotFoundException, SQLException{
		save("UPDATE user SET role_id=?, given_name=?, family_name=?, username=?, email=?, password=?, phone=? WHERE id=?",
				new Object[] {user.getRoleId(), user.getGivenName(), user.getFamilyName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getPhone(), user.getUserId()});
	}
	//Deletes the user specified by User Id
	public void deleteUser(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM user WHERE id=?", new Object[] {id});
	}
}
