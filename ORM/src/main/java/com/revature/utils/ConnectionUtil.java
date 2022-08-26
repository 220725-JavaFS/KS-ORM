package com.revature.utils;

import java.sql.Connection;//java.sql is the JDBC package
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	// A singleton design pattern - only allows one instance of a class to exist in memory at a time.
	
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		if(connection != null && !connection.isClosed()) {
			return connection;
		}else {
			
			//for many frameworks or in cases of multiple SQL drivers. 
			//Registering the driver your using for connection interface is necessary
			//This step can be unnecessary for simple projects 
			
			try {
				Class.forName("org.postgresql.Driver");
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			String url = "jdbc:postgresql://javafs220725.cyypgegi3m5d.us-east-1.rds.amazonaws.com:5432/project_one";
			String username = "postgres"; //possible to hide raw credentials with environment variables
			String password = "password1"; //can access those variables with System.getenv("variableName");
			
			connection = DriverManager.getConnection(url,username,password);
		}
		return connection;
	}
	
	public static void main(String[] args) {
		try {
			getConnection();
			System.out.println("Connection successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
