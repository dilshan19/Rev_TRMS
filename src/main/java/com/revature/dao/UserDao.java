package com.revature.dao;

import com.revature.pojo.User;

public interface UserDao {
	
	public User getUser(String username, String password);

	User employeeLoginDao(User user);
	
}
