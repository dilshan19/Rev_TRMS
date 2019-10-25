package com.revature.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.util.ConnectionFactory;

public class FileDaoUpload implements FileDao{
	
	private Connection conn = ConnectionFactory.getConnection();

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	private static Logger log = Logger.getRootLogger();
	
    public boolean writeToDB(String id, String fileName) throws SQLException {
    	 
        String sql = "Update reimbursements set xfilepath = ? where requestid = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        //Long id = this.getMaxAttachmentId(conn) + 1;
        pstm.setString(1, fileName);
        pstm.setInt(2, Integer.parseInt(id));
        pstm.executeUpdate();
		return true;
    }
}
