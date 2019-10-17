package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoLogin;
import com.revature.pojo.User;

public class UserServiceImpl implements UserService {
	
	private static UserDao userDao = new UserDaoLogin();
	
	public User loginUser(String username, String password) {
		System.out.println("Attempted login with credentials: Username - " + username + " Password - " + password); //Use log 4 j
		
		User user = userDao.getUser(username, password);
		
		if ((user != null) && (user.getPassword().equals(password))) {
			return user;
		}
		return null;
	}

}
