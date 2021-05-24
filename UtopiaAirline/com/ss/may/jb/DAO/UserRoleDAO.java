package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.may.jb.entities.UserRole;

public class UserRoleDAO extends BaseDAO<UserRole>{
	//Supports Database Connection
	public UserRoleDAO(Connection conn) {
			super(conn);
		}
	
	//Returns an ArrayList of UserRoles based on the Select Statement sent
	public ArrayList<UserRole> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<UserRole> roles = new ArrayList<UserRole>();
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			UserRole role = new UserRole(id, name);
			roles.add(role);
		}
		return roles;
	}
	//Returns the UserRole specified by UserRole Id
	public UserRole getUserRole(int id) throws ClassNotFoundException, SQLException{
		ArrayList<UserRole> roles = read("SELECT id, name FROM user_role WHERE id=?", new Object[] {id});
		return roles.get(0);
	}
	//Returns an ArrayList of all UserRoles in UserRole Table
	public ArrayList<UserRole>getAllUserRoles() throws ClassNotFoundException, SQLException{
		ArrayList<UserRole> roles = read("SELECT id, name FROM user_role WHERE id!=0", null);
		return roles;
	}
	//Inserts a new UserRole into database
	public void addUserRole(UserRole role) throws ClassNotFoundException, SQLException{
		save("INSERT INTO user_role (id, name) VALUES (?, ?)", new Object[] {role.getUserRoleId(), role.getName()});
	}
	//Updates the information for an UserRole specified by UserRole Id
	public void updateUserRole(UserRole role) throws ClassNotFoundException, SQLException{
		save("UPDATE user_role SET name=? WHERE id=?", new Object[] {role.getName(), role.getUserRoleId()});
	}
	//Deletes the UserRole specified by UserRole Id
	public void deleteUserRole(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM user_role WHERE id=?", new Object[] {id});
	}	
}
