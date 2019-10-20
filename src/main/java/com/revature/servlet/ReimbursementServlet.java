package com.revature.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojo.Event;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.ReimbursementService;

import static com.revature.util.LoggerUtil.*;

public class ReimbursementServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	private ReimbursementService reimburseServ = new ReimbursementService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		debug("Get from AddRServlet");
//		PrintWriter pw = resp.getWriter();
//		String type = req.getParameter("type");
//		String location = req.getParameter("location");
//		String get_date = req.getParameter("date");
//		
//		Double amount = Double.parseDouble(req.getParameter("amount"));
//		String description = req.getParameter("description");
//		String format = req.getParameter("format");
//
//		info("Date: " + get_date);
//		pw.write("type: " + type + " location: " + location + " amount: "+amount+" description: " + description + "Date: " + get_date + "format: "
//				+format);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String email = req.getParameter("email");
		String eventdate = req.getParameter("eventdate");
		String address = req.getParameter("address");
		String address2 = req.getParameter("address2");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String zip = req.getParameter("zip");
		String type = req.getParameter("type");
		String gradeFormat = req.getParameter("gradeformat");
		double cost = Integer.parseInt(req.getParameter("cost"));
		String description = req.getParameter("description");
		double reimbursementAmount=0.00;
		Event event = reimburseServ.addReimbursement(email, eventdate, address, address2, city, state, zip, description, cost, reimbursementAmount, type, gradeFormat);
		debug("Post from AddRServlet");
		resp.sendRedirect("employee.html");


	}
//	PrintWriter pw = resp.getWriter();
//	String type = req.getParameter("type");
//	String location = req.getParameter("location");
//	String get_date = req.getParameter("date");
//	
//	Double amount = Double.parseDouble(req.getParameter("amount"));
//	String description = req.getParameter("description");
//	String format = req.getParameter("format");
//
//	info("Date: " + get_date);
//	pw.write("type: " + type + " location: " + location + " amount: "+amount+" description: " + description + "Date: " + get_date + "format: "
//			+format);
	//todo: figure out how to get the email without the client re inputting
//	Reimbursement reimb = new Reimbursement();
//	PrintWriter pw = resp.getWriter();		
//	reimb.setRequestorEmail("sample.email@gmail.com");
//	reimb.setType(req.getParameter("type"));
//	reimb.setLocation(req.getParameter("location"));
//	
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	String date = req.getParameter("date");
//	debug("raw date: " + date);
//	LocalDate localDate = LocalDate.parse(date, formatter);
//	debug("formatted date: " + localDate);
//	
//	reimb.setDate(localDate);
//	Double amount = Double.parseDouble(req.getParameter("amount"));
//	reimb.setOriginalAmount(amount);
//	reimb.setTentativeAmount(amount);
//	reimb.setDescription(req.getParameter("description"));
//	reimb.setFormat(req.getParameter("format"));
//	if( reimburseServ.addReimbursement(reimb) ) {
//		pw.write("Successfully added your submission!");
//	}else {
//		pw.write("Could not process your form!");
//	}
}
