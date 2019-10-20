package com.revature.servlet;

import java.io.IOException;



import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.Reimbursement;
import com.revature.service.ReimbursementService;

import static com.revature.util.LoggerUtil.*;

public class ReimbursementServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	private ReimbursementService reimburseServ = new ReimbursementService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ObjectMapper om = new ObjectMapper();

		String name = request.getPathInfo();
		info("Name: " + name);

		if (name != null && !"".equals(name.substring(1))) {
			//response.getWriter().write(om.writeValueAsString(reimburseServ.);
			info("grabbing individual reimb..");
		} else {
			info("grabbing all reimb");

			ArrayList<Reimbursement> reimbList = reimburseServ.getAllReimbursements();

			response.getWriter().write(om.writeValueAsString(reimbList));
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		debug("Post from AddRServlet");
		//todo: figure out how to get the email without the client re inputting
		Reimbursement reimb = new Reimbursement();
		PrintWriter pw = resp.getWriter();		
		reimb.setRequestorEmail("sample.email@gmail.com");
		reimb.setType(req.getParameter("type"));
		reimb.setLocation(req.getParameter("location"));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = req.getParameter("date");
		debug("raw date: " + date);
		LocalDate localDate = LocalDate.parse(date, formatter);
		debug("formatted date: " + localDate);
		
		reimb.setDate(localDate);
		Double amount = Double.parseDouble(req.getParameter("amount"));
		reimb.setOriginalAmount(amount);
		reimb.setTentativeAmount(amount);
		reimb.setDescription(req.getParameter("description"));
		reimb.setFormat(req.getParameter("format"));
		if( reimburseServ.addReimbursement(reimb) ) {
			pw.write("Successfully added your submission!");
		}else {
			pw.write("Could not process your form!");
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getPathInfo();
		info("whole: " + name);
		String id = name.substring(1);
		info("url: " + id);
			
		
	}
	

}
