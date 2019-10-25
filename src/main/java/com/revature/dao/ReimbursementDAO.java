package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.Reimbursement;

public interface ReimbursementDAO {
	
	public ArrayList<Reimbursement> getR(String email);
	public boolean insert(Reimbursement re);//update or insert a Reimbursement entry
	public String grabReason(int requestID);
	//Update individual field in reimbursement table -> any of the booleans
	public boolean updateReimbursementBooleans(int id, int field);
	
	//update only amount
	public boolean updateAmount(int id, double amount);
	
	//add entry to rejected table based on id
	public boolean insertReason(int id, String email, String reason, int field);
	

	public boolean isRequestAcceptedByAll(int id);

}
