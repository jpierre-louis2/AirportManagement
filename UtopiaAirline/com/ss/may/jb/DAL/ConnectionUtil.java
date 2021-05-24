package com.ss.may.jb.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost/utopia";
	private static final String username = "root";
	private static final String password = "root";
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		//Class.forName("com.mysql.cj.jdbc.Driver");
		Class.forName(driver);
		//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "root", "root");
		Connection conn = DriverManager.getConnection(url, username, password);
		//Sets soft commits incase of rollbacks
		conn.setAutoCommit(Boolean.FALSE);
		return conn;
	}

}
