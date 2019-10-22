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
	
	public User getUser(String firstName, String lastName, String username, String password, String role) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setEmail(username);
		user.setRole(role);
		return user;
	}
	
	public User registerUser(User user) {
		if("Benco".equals(user.getRole())) {
			user.setRole("bencoinfo");
		} else if("Department Head".equals(user.getRole())) {
			user.setRole("departmentheadinfo");
		} else if("Supervisor".equals(user.getRole())) {
			user.setRole("supervisorinfo");
		} else if("Employee".equals(user.getRole())) {
			user.setRole("employeeinfo");
		} else {
			return null;
		}
		registerDao(user);
		
		return user;
	}
	
	public User loginUser(User user) {
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

	public User registerDao(User user) {
		String sql = "insert into " + user.getRole() + " (firstname, lastname, email, password) values (?, ?, ?, ?)";

		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			//stmt.setString(1, user.getRole());
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
			return null;
		}
		log.trace("User registered");
		System.out.println(System.lineSeparator());
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
