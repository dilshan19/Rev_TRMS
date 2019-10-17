package com.revature.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.pojo.Reimbursement;
import com.revature.service.ReimbursementService;

import static com.revature.util.LoggerUtil.*;

public class ReimbursementServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	private ReimbursementService reimburseServ = new ReimbursementService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		debug("Get from AddRServlet");
		PrintWriter pw = resp.getWriter();
		String type = req.getParameter("type");
		String location = req.getParameter("location");
		String get_date = req.getParameter("date");
		
		Double amount = Double.parseDouble(req.getParameter("amount"));
		String description = req.getParameter("description");
		String format = req.getParameter("format");

		info("Date: " + get_date);
		pw.write("type: " + type + " location: " + location + " amount: "+amount+" description: " + description + "Date: " + get_date + "format: "
				+format);
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
		LocalDate localDate = LocalDate.parse(date, formatter);
		debug("localD: " + localDate);
		
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

}
