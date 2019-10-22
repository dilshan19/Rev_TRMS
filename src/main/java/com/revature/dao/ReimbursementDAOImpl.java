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
			if (email == null) {
				String sql = "select * from reimbursements order by requestid asc";
				stmt = conn.prepareStatement(sql);
			} else {
				String sql = "select * from reimbursements where email = ? order by requestid asc";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, email);

				//LoggerUtil.debug(stmt.toString());
			}
			ResultSet r = stmt.executeQuery();
			Reimbursement tempR;
			while (r.next() != false) {
				int count = 1;
				tempR = new Reimbursement();
				tempR.setId(r.getInt(count++));
				tempR.setRequestorEmail(r.getString(count++));
				Date temp = r.getDate(count++);
				tempR.setDate(temp.toLocalDate());// --> buggy line somehow. wont print stacktrace on my STS so Im not
													// sure what is causing err
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
				//info(tempR.toString());

				rList.add(tempR);
			}
			r.close();
		} catch (SQLException e) {
			error(e);
			e.printStackTrace();
		}
		return (rList.size() != 0) ? rList : null;
	}

	public boolean insert(Reimbursement re) {
		int result = 0;
		debug(re.toString());

		try {
			String sql = "insert into reimbursements(email,date_,location_,originalamount,tentativeamount,eventtype,description,format,isDS,isDH,isBC,isBCAltered,hasGrade) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?);";
			if (conn == null) {
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
			result = stmt.executeUpdate(); // should be 1 row updated

		} catch (SQLException e) {
			error(e);
			e.printStackTrace();

		}
		return result != 0;
	}

	@Override
	public boolean update(int id, int field) {
		int result = 0;
		try {
			String sql = null;
			switch(field) {
			case 0:
				sql = "update reimbursements set isDSAltered = true where isDS = ?";
				break;
			case 1:
				sql = "update reimbursements set isDHAltered = true where isDH = ?";
				break;
			case 2:
				sql = "update reimbursements set isBCAltered = true where isBC = ?";
				break;
			case 3:
				sql = "update reimbursements set isBCaltered = true where isBCaltered = ?";
				break;
				default:
					error("Select a correct field option");
					return false;
			}
			if (conn == null) {
				LoggerUtil.error("Conn null");
			}
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			result = stmt.executeUpdate(); // should be 1 row updated
		} catch (SQLException e) {
			error(e);
			e.printStackTrace();
		}
		return result == 1;
	}

}
