package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoLogin;
import com.revature.pojo.User;

public class UserServiceImpl implements UserService {
	
	private static UserDao userDao = new UserDaoLogin();
	
	public User loginUser(String username, String password) {
		System.out.println("Attempted login with credentials: Username - " + username + " Password - " + password); //Use log 4 j
		
		User user = userDao.getUser("", "", username, password, "");
		
		userDao.loginUser(user);
		
		if ((user != null) && (user.getPassword().equals(password))) {
			return user;
		}
		return null;
	}
	
	public User registerUser(String firstName, String lastName, String username, String password, String role) {
		System.out.println("Attempted registration with credentials: Username - " + username + " Password - " + password); //Use log 4 j
		
		User user = userDao.getUser(firstName, lastName, username, password, role);
		userDao.registerUser(user);
		if ((user != null) && (user.getPassword().equals(password))) {
			return user;
		}
		return null;
	}

}
