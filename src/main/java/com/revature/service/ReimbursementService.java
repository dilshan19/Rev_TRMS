package com.revature.service;

import com.revature.util.LoggerUtil;

import static com.revature.util.LoggerUtil.error;

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
	
	public void acceptReimbursement(int id, String email, String usertype) {
		switch(usertype) {
		case "ds":
			reimbursementService.updateToAccept(id, 0);
			break;
		case "dh":
			reimbursementService.updateToAccept(id, 1);
			break;
		case "bc":
			reimbursementService.updateToAccept(id, 2);
			break;
		case "dsdh":
			reimbursementService.updateToAccept(id, 4);
			break;
			default:
				LoggerUtil.error("Unrecognized user type!");
		}
	}
	public void insertReason(int id, String email, String reason) {
		if(reason.length() > 1000) {
			error("Input reason larger than 1000 chars");
			return;
		}
		reimbursementService.insertDeniedR( id,  email,  reason);
	}
	
	public void alterAmount(int id, double amount) {
		if(amount > 1000.00) {
			LoggerUtil.debug("Amount greater than the yearly $1000 reimbursement limit");
			return;
		}
		reimbursementService.updateAmount(id, amount);
		
	}
	
	
}
