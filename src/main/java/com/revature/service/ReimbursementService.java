package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.pojo.Reimbursement;

public class ReimbursementService {

	private static ReimbursementDAO reimbursementService = new ReimbursementDAOImpl();
	
	public boolean addReimbursement(Reimbursement r) {
		boolean check = reimbursementService.insert(r);
		return check;
	}
	
}
