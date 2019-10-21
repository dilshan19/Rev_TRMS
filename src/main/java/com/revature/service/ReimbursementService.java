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
	
	public ArrayList<Reimbursement> getAllReimbursements(String email){
		return reimbursementService.getR(email);
	}
	
	public boolean updateReimbTable(int id, int field) {
		return(reimbursementService.update( id,  field));
	}
	
}
