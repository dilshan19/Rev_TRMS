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
		boolean check1 = reimbursementService.insert(r);
		LoggerUtil.debug("Adding reimb. Check1: " + check1);
		return check1;
	}
	
	public ArrayList<Reimbursement> getAllReimbursements(String email){
		return reimbursementService.getR(email);
	}
	
	public void acceptReimbursement(int id, String email, String usertype) {
		switch(usertype) {
		case "ds":
			reimbursementService.updateReimbursementBooleans(id, 0);
			break;
		case "dh":
			reimbursementService.updateReimbursementBooleans(id, 1);
			break;
		case "bc":
			reimbursementService.updateReimbursementBooleans(id, 2);
			break;
		case "dsdh":
			reimbursementService.updateReimbursementBooleans(id, 3);
			break;
			default:
				LoggerUtil.error("Unrecognized user type!");
		}
	}
	public void insertReason(int id, String email, String reason, String type) {	//update DS's table for why they rejected
		if(reason.length() > 1000) {
			error("Input reason larger than 1000 chars");
			return;
		}
		reimbursementService.insertReason( id,  email,  reason, 0);	//0 refers to Supervisor table
	}
	
	public void alterAmount(int id, double amount, String reason, String email) {	//update BC's reason table for why they changed amount
		if(amount > 1000.00) {										//also update actual amount on reimburse table
			LoggerUtil.debug("Amount greater than the yearly $1000 reimbursement limit");
			return;
		}
		if(reason.length() > 1000) {
			error("Input reason larger than 1000 chars");
			return;
		}
		if(reimbursementService.updateAmount(id, amount)) {
			LoggerUtil.debug("successfully altered id: " + id);
		}
		if(reimbursementService.insertReason( id,  email,  reason, 1)) { //reason refers to Benco Reason Table
			LoggerUtil.debug("Successfully adding bencos rason for request id: " + id);
		}
	}
	
	public void updateReimbursementTable(int id) {
		if(reimbursementService.isRequestAcceptedByAll(id) ){
			LoggerUtil.info("Everyone has accepted reimb #: " + id);
		}else {
			LoggerUtil.info("Not everyone has accepted reimb #: " + id);
		}
	}
	
	
	
}
