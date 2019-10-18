package com.revature.dao;

import com.revature.pojo.Reimbursement;

public interface ReimbursementDAO {
	
	public Reimbursement getR(String email);
	public boolean insert(Reimbursement re);//update or insert a Reimbursement entry

}
