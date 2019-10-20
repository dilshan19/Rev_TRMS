package com.revature.dao;

import static com.revature.util.LoggerUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.revature.util.ConnectionFactory;
import com.revature.util.LoggerUtil;
import com.revature.pojo.Reimbursement;

public class ReimbursementDAOImpl implements ReimbursementDAO {
	private static Connection conn = ConnectionFactory.getConnection();
	
	public ArrayList<Reimbursement> getR(String email) {
		ArrayList<Reimbursement> rList = new ArrayList<Reimbursement>();
		try {
			PreparedStatement stmt;
			if(email == null) {
				String sql = "select * from reimbursements"; 
				stmt = conn.prepareStatement(sql);
			}else {
				String sql = "select * from reimbursements where email = ?"; 
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, email);
				LoggerUtil.info(stmt.toString());
			}
			ResultSet r = stmt.executeQuery();
			Reimbursement tempR;
			while(r.next() != false) {
				int count = 2;
				tempR = new Reimbursement();
				tempR.setRequestorEmail(r.getString(count++));				
				Date temp = r.getDate(count++);
				tempR.setDate(temp.toLocalDate());//--> buggy line somehow. wont print stacktrace on my STS so Im not sure what is causing err
				tempR.setLocation(r.getString(count++));
				tempR.setOriginalAmount(r.getDouble(count++));
				tempR.setTentativeAmount(r.getDouble(count++));
				tempR.setType(r.getString(count++));
				tempR.setDescription(r.getString(count++));
				tempR.setFormat(r.getString(count++));
				tempR.setDSApproved(r.getBoolean(count++));
				tempR.setDHApproved(r.getBoolean(count++));
				tempR.setBCApproved(r.getBoolean(count++));
				tempR.setBCAltered(r.getBoolean(count++));
				tempR.setGradeUploaded(r.getBoolean(count++));	
				rList.add(tempR);
			}
			r.close();
		}catch(SQLException e){
			error(e);
			e.printStackTrace();
		}
		return ( rList.size() != 0 ) ? rList : null;
	}

	public boolean insert(Reimbursement re) {
		int result = 0;
		debug(re.toString());

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

	@Override
	public boolean update(String id, int field) {
		
		return false;
	}
	
	
	
	

}
