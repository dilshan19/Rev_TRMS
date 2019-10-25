package com.revature.service;

import java.io.InputStream;
import java.sql.SQLException;

import com.revature.dao.FileDao;
import com.revature.dao.FileDaoUpload;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoLogin;
import com.revature.pojo.User;

public class FileUploadService implements FileUpload{
	private static FileDao fileDao = new FileDaoUpload();
	@Override
	public boolean uploadFile(String id, String fileName) {
		try {
			fileDao.writeToDB(id, fileName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


}
