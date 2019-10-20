package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.pojo.Event;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;

public class ReimbursementService {

	private static ReimbursementDAO reimbursementService = new ReimbursementDAOImpl();
	
	public Event addReimbursement(String email, String eventdate, String address, String address2, String city, String state, String zip, String description, double cost, double reimbursementAmount, String type, String gradeFormat) {
		//System.out.println("Attempted login with credentials: Username - " + username + " Password - " + password); //Use log 4 j
		
		Event event = reimbursementService.getEvent(email, eventdate, address, address2, city, state, zip, description, cost, reimbursementAmount, type, gradeFormat);
		event.setReimbursementAmount(500);//insert formula
		reimbursementService.insert(event);
		return event;
//		boolean check = reimbursementService.insert(r);
//		return check;
	}
	
}
