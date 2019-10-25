package com.revature.dao;

import com.revature.pojo.User;

public interface UserDao {
	
	public User getUser(String firstName, String lastName, String username, String password, String role);

	public User employeeLoginDao(User user);

	public User loginUser(User user);
	
	public User registerUser(User user);
	
	public User registerDao(User user); 
	
	public String getReason(int id);
}
