package edu.asu.poly.promise.service.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class is the wrapper for the database connection.
 * @author Deepak S N
 */
public class ConnectionFactory {
	public Connection Get_Connection(){	
		Connection con = null;
		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		String dbUrl = null;
		String dbPort = null;
		String dbName = null;
		String dbUser = null;
		String dbPwd = null;
		String connectionURL = null;

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = getClass().getResourceAsStream("config.properties");

			// load a properties file
			prop.load(input);


		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


		dbUrl = prop.getProperty("dbURL");
		dbPort = prop.getProperty("dbport");
		dbName = prop.getProperty("dbName");
		dbUser = prop.getProperty("dbUser");
		dbPwd = prop.getProperty("dbPassword");

		connectionURL = "jdbc:mysql://"+dbUrl+":"+dbPort+"/"+dbName;

		try {
			con = DriverManager.getConnection(connectionURL,dbUser, dbPwd);
			//		con = DriverManager.getConnection("jdbc:mysql://locahost:3306/aspira","dbuser", "root123");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		if (con != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

		return con;
	}
}
