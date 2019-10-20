package com.revature.dao;

import com.revature.pojo.Event;
import com.revature.pojo.Reimbursement;

public interface ReimbursementDAO {
	
	public Event getEvent(String email, String eventdate, String address, String address2, String city, String state, String zip, String description, double cost, double reimbursementAmount, String type, String gradeFormat);
	public boolean insert(Event event);//update or insert a Reimbursement entry

}
