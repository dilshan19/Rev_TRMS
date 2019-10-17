package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.revature.pojo.User;
import com.revature.util.ConnectionFactory;

public class UserDaoLogin implements UserDao {
	private Connection conn = ConnectionFactory.getConnection();

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	private static Logger log = Logger.getRootLogger();

	public User getUser(String username, String password) {
		User user = new User();
		user.setPassword(password);
		user.setEmail(username);
		if(bencoLoginDao(user) != null) {
			user.setManagerStatus("benco");
		} else if(departmentHeadLoginDao(user) != null) {
			user.setManagerStatus("departmentHead");
		} else if(supervisorLoginDao(user) != null) {
			user.setManagerStatus("supervisor");
		} else if(employeeLoginDao(user) != null) {
			user.setManagerStatus("employee");
		}else {
			
			return null;
		}
		return user;
	}

	public User employeeLoginDao(User user) {
		String sql = "select email, password from employeeinfo where email = ? and password = ?";

		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();

			if (!rs.next()) {
				return null;
			} else {
				user.setFullName(rs.getString(1) + " " + rs.getString(2));
			}
		} catch (SQLException e) {
			log.error(e);
			return null;
		}
		log.trace("User logged in");
		System.out.println(System.lineSeparator());
		return user;
	}
	
	public User supervisorLoginDao(User user) {
		String sql = "select email, password from supervisorinfo where email = ? and password = ?";

		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
		} catch (SQLException e) {
			log.error(e);
			return null;
		}
		log.trace("User logged in");
		System.out.println(System.lineSeparator());
		return user;
	}
	
	public User departmentHeadLoginDao(User user) {
		String sql = "select email, password from departmentheadinfo where email = ? and password = ?";

		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
		} catch (SQLException e) {
			log.error(e);
			return null;
		}
		log.trace("User logged in");
		System.out.println(System.lineSeparator());
		return user;
	}
	
	public User bencoLoginDao(User user) {
		String sql = "select email, password from bencoinfo where email = ? and password = ?";

		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
		} catch (SQLException e) {
			log.error(e);
			return null;
		}
		log.trace("User logged in");
		System.out.println(System.lineSeparator());
		return user;
	}

}
