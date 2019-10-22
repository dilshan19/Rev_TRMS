package com.revature.service;

import com.revature.pojo.User;

public interface UserService {
	public User loginUser(String username, String password);
	public User registerUser(String firstName, String lastName, String username, String password, String role);
}
