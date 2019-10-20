package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.Reimbursement;

public interface ReimbursementDAO {
	
	public ArrayList<Reimbursement> getR(String email);
	public boolean insert(Reimbursement re);//update or insert a Reimbursement entry

}
