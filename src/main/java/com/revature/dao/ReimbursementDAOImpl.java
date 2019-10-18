package com.revature.dao;

import static com.revature.util.LoggerUtil.error;
import static com.revature.util.LoggerUtil.info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.revature.util.ConnectionFactory;
import com.revature.util.LoggerUtil;
import com.revature.pojo.Reimbursement;

public class ReimbursementDAOImpl implements ReimbursementDAO {
	private static Connection conn = ConnectionFactory.getConnection();
	
	public Reimbursement getR(String email) {
		return null;
	}

	public boolean insert(Reimbursement re) {
		int result = 0;
		info(re.toString());

		try {
			String sql = "insert into reimbursements(email,date_,location_,originalamount,tentativeamount,eventtype,description,format,isDS,isDH,isBC,isBCAltered,hasGrade) " + 
			"values(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?);"; 
			if(conn == null) {
				LoggerUtil.error("Conn null");
			}
			PreparedStatement stmt = conn.prepareStatement(sql);
			int count = 1;
			stmt.setString(count++, re.getRequestorEmail());
			stmt.setObject(count++, re.getDate(), Types.DATE);
			stmt.setString(count++, re.getLocation());
			stmt.setDouble(count++, re.getOriginalAmount());
			stmt.setDouble(count++, re.getTentativeAmount());
			stmt.setString(count++, re.getType());
			stmt.setString(count++, re.getDescription());
			stmt.setString(count++, re.getFormat());
			stmt.setBoolean(count++, re.isDSApproved());
			stmt.setBoolean(count++, re.isDHApproved());
			stmt.setBoolean(count++, re.isBCApproved());
			stmt.setBoolean(count++, re.isBCAltered());	
			stmt.setBoolean(count++, re.getGradeUploaded());	
			result = stmt.executeUpdate();	//should be 1 row updated

		} catch (SQLException e) {
			//int errorCode = Integer.parseInt(e.getSQLState());
			//debug("State: " + errorCode);
			error(e);
			e.printStackTrace();

		}
		return result != 0;
	}
	
	
	
	

}
