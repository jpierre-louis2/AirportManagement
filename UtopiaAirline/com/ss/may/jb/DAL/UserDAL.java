package com.ss.may.jb.DAL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.may.jb.DAO.UserDAO;
import com.ss.may.jb.entities.User;

public class UserDAL {
	
	ConnectionUtil u = new ConnectionUtil();
	
	//Prints the Details associated for a user
	public void printUserDetails(int id) {
		User user = getUser(id);
		System.out.println("\nUser Details: ");
		System.out.println("Name: " + user.getGivenName() + " " + user.getFamilyName());
		System.out.println("Username: " + user.getUsername() + "\nEmail: " + user.getEmail() + "\nPhone: " + user.getPhone());
	}
	
	//Prints out a list of Users with the normal role status
	public void printNormalUsers() {
		System.out.println("\nNormal Users:");
		System.out.print("----------------------");
		int count = 1;
		ArrayList<User> users = getBookingUsers();
		for(User user : users) {
			System.out.print("\n" + count + ". ");
			printUserDetails(user.getUserId());
			++count;
		}
	}
	
	//Prints out a list of Users with the employee role status
	public void printEmployeeUsers() {
		System.out.println("\nEmployees:");
		System.out.print("----------------------");
		int count = 1;
		ArrayList<User> users = getBookingEmployees();
		for(User user : users) {
			System.out.print("\n" + count + ". ");
			printUserDetails(user.getUserId());
			++count;
		}
	}
	
	//Finds the ID for a new insertion
	private int findNewUserId(){
		ArrayList<User> users = getAllUsers();
		if (users.size() == 0) {
			return 1;
		}
		int highest = users.get(0).getUserId();
		for(User user : users) {
			if (user.getUserId() > highest)
				highest = user.getUserId();
		}
		
		return highest + 1;
	}
	
	//Returns an ArrayList of users with the normal status role
	public ArrayList<User> getBookingUsers(){
		ArrayList<User> normalUsers = new ArrayList<User>();
		try(Connection conn = u.getConnection()){
			UserDAO us = new UserDAO(conn);
			normalUsers = us.getAllNormal();
			return normalUsers;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Returns an ArrayList of users with the Employee status role
	public ArrayList<User> getBookingEmployees(){
		ArrayList<User> employees = new ArrayList<User>();
		try(Connection conn = u.getConnection()){
			UserDAO us = new UserDAO(conn);
			employees = us.getAllEmployees();
			return employees;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Returns the user specified by User Id
	public User getUser(int id){
		try(Connection conn = u.getConnection()){
			UserDAO us = new UserDAO(conn);
			User user = us.getUser(id);
			return user;
		}catch(Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	//Returns an ArrayList of all users from database
	public ArrayList<User> getAllUsers(){
		ArrayList<User> users = new ArrayList<User>();
		try(Connection conn = u.getConnection()){
			UserDAO us = new UserDAO(conn);
			users = us.getAllUsers();
			return users;
		}catch(Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	//Inserts a new User with the Employee Role
	public String addEmployee(String gName, String fName, String username, String email, String password, String phone) throws SQLException{
		int id = findNewUserId();
		User employee = new User(id, 1, gName, fName, username, email, password, phone);
		Connection conn = null;
		try {
			conn = u.getConnection();
			UserDAO us = new UserDAO(conn);
			us.addUser(employee);
			conn.commit();
			return "Employee User Successfully Added!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Employee User Could not be Added!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	//Inserts a new User with the normal user Role
	public String addNormalUser(String gName, String fName, String username, String email, String password, String phone) throws SQLException{
		int id = findNewUserId();
		User employee = new User(id, 2, gName, fName, username, email, password, phone);
		Connection conn = null;
		try {
			conn = u.getConnection();
			UserDAO us = new UserDAO(conn);
			us.addUser(employee);
			conn.commit();
			return "Normal User Successfully Added!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Normal User Could not be Added!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	//Updates a User's information
	public String updateUser(int id, int role, String gName, String fName, String username, String email, String password, String phone) throws SQLException{
		User user = new User(id, role, gName, fName, username, email, password, phone);
		Connection conn = null;
		try {
			conn = u.getConnection();
			UserDAO us = new UserDAO(conn);
			us.updateUser(user);
			conn.commit();
			return "Successfully Updated!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Could not be Updated!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	//Deletes a user from database specified by ID
	public String deleteUser(int id) throws SQLException{
		Connection conn = null;
		try {
			conn = u.getConnection();
			UserDAO us = new UserDAO(conn);
			us.deleteUser(id);
			conn.commit();
			return "Employee Successfully Deleted!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Employee Could not be Deleted!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}

}
