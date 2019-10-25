package com.revature.service;

import java.io.InputStream;

import com.revature.pojo.User;

public interface FileUpload {
	public boolean uploadFile(String id, String fileName);
}
