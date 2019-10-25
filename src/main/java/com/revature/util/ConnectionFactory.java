package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static String url;

	private static String user;

	private static String password;
	
	static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

	//private static final String PROPERTIES_FILE = "D:/Users/Dilshan/Desktop/code/STS/Project1/src/main/resources/database.properties";
	private static final String PROPERTIES_FILE = "C:/Repositories/P1/Rev_TRMS-fixReimb/src/main/webapp/WEB-INF/database.properties";

	private static ConnectionFactory cf;

	public static Connection getConnection() {

		if (cf == null) {
			cf = new ConnectionFactory();
		}

		return cf.createConnection();

	}

	private ConnectionFactory() {

		Properties prop = new Properties();

		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {

			prop.load(fis);
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Connection createConnection() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Failed to create connection");
			e.printStackTrace();
		}

		return conn;

	}

}
