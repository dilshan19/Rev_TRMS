package com.revature.dao;

import java.io.InputStream;
import java.sql.SQLException;

public interface FileDao {
	public boolean writeToDB(String id, String fileName) throws SQLException;
}
