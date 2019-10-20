package com.revature.dao;

import static com.revature.util.LoggerUtil.error;
import static com.revature.util.LoggerUtil.info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Logger;

import com.revature.util.ConnectionFactory;
import com.revature.util.LoggerUtil;
import com.revature.pojo.Event;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;

public class ReimbursementDAOImpl implements ReimbursementDAO {
	private static Connection conn = ConnectionFactory.getConnection();
	
	public Event getEvent(String email, String eventdate, String address, String address2, String city, String state, String zip, String description, double cost, double reimbursementAmount, String type, String gradeFormat) {
		Event event = new Event();
		event.setEmail(email);
		event.setDate(eventdate);
		event.setAddress(address);
		event.setAddress2(address2);
		event.setCity(city);
		event.setState(state);
		event.setZip(zip);
		event.setDescription(description);
		event.setCost(cost);
		event.setType(type);
		event.setGradeFormat(gradeFormat);
		return event;
	}

	public boolean insert(Event event) {
		int result = 0;
		info(event.toString());

		try {
			String sql = "insert into requests(requestoremail,eventdate,address,address2,city,state,zip,eventdescription,eventcost,reimbursementamount,eventtype,gradingformat,supervisorapproval,dhapproval,bencoapproval,modifiedbybenco,gradeorpresentationstatus) " + 
			"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false, false, false, false, false);"; 
			if(conn == null) {
				LoggerUtil.debug("Conn null");
			}
			PreparedStatement stmt = conn.prepareStatement(sql);
			int count = 1;
			stmt.setString(count++, event.getEmail());
			stmt.setObject(count++, event.getDate(), Types.DATE);
			stmt.setString(count++, event.getAddress());
			stmt.setString(count++, event.getAddress2());
			stmt.setString(count++, event.getCity());
			stmt.setString(count++, event.getState());
			stmt.setString(count++, event.getZip());
			stmt.setString(count++, event.getDescription());
			stmt.setDouble(count++, event.getCost());
			stmt.setDouble(count++, event.getReimbursementAmount());
			stmt.setString(count++, event.getType());
			stmt.setString(count++, event.getGradeFormat());
			result = stmt.executeUpdate();	//should be 1 row updated
			info(String.valueOf(count));
		} catch (SQLException e) {
			//int errorCode = Integer.parseInt(e.getSQLState());
			//debug("State: " + errorCode);
			error(e);
			e.printStackTrace();
			return false;

		}
		return true;
	}
	
	
	
	

}
