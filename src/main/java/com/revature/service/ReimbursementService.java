package com.revature.service;

import java.util.ArrayList;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.pojo.Reimbursement;

public class ReimbursementService {

	private static ReimbursementDAO reimbursementService = new ReimbursementDAOImpl();
	
	public boolean addReimbursement(Reimbursement r) {//add input checks here
		boolean check = reimbursementService.insert(r);
		return check;
	}
	
	public ArrayList<Reimbursement> getAllReimbursements(){
		return reimbursementService.getR(null);
	}
	
}
