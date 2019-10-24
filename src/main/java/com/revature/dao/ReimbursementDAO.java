package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.Reimbursement;

public interface ReimbursementDAO {
	
	public ArrayList<Reimbursement> getR(String email);
	public boolean insert(Reimbursement re);//update or insert a Reimbursement entry
	public String grabReason(int requestID);
	//Update individual field in reimbursement table -> any of the booleans
	public boolean updateToAccept(int id, int field);
	
	//update only amount
	public boolean updateAmount(int id, double field);

	//Remove row reimbursement table based on id
	
	
	//add entry to rejected table based on id
	public boolean insertDeniedR(int id, String email, String reason);
	//

}
