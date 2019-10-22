package com.revature.servlet;

import java.io.IOException;




import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.Reimbursement;
import com.revature.service.ReimbursementService;
import com.revature.util.LoggerUtil;

import static com.revature.util.LoggerUtil.*;

public class ReimbursementServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	private static ReimbursementService reimburseServ = new ReimbursementService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ObjectMapper om = new ObjectMapper();

		String name = request.getPathInfo();
		debug("(REIMBSERVLET) doGet, ext: " + name);

		HttpSession session = request.getSession(false);
		String type = (String) session.getAttribute("usertype");
		LoggerUtil.debug("(REIMBSERVLET) doGet, usertype: " + type);
		if (name == null && type == "employee") {
			response.sendRedirect("employee.html");
		} else { //grab all reimbs from non-requestor pages
			debug("(REIMBSERVLET) doGet, extension: " + name);
			
			ArrayList<Reimbursement> reimbList = reimburseServ.getAllReimbursements(null);

			response.getWriter().write(om.writeValueAsString(reimbList));
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getPathInfo();
		debug("(REIMBSERVLET) doPost, ext: " + name);		//todo: figure out how to get the email without the client re inputting
		Reimbursement reimb = new Reimbursement();
		PrintWriter pw = resp.getWriter();		
		
		HttpSession session = req.getSession(false);
		String email = (String) session.getAttribute("email");
		LoggerUtil.debug("(REIMBSERVLET) doPost, email: " + email);
		
		reimb.setRequestorEmail(email);
		reimb.setType(req.getParameter("type"));
		
		String addr = req.getParameter("address");
		String addr2 = (  req.getParameter("address2") != null ) ?  req.getParameter("address2") : "";
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String zip = req.getParameter("zip");
		String completeAddr = addr +", " + addr2+", " + city +", "+ state+", " + zip;
		reimb.setLocation(completeAddr);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = req.getParameter("eventdate");
		LocalDate localDate = LocalDate.parse(date, formatter);
		reimb.setDate(localDate);
		
		Double amount = Double.parseDouble(req.getParameter("cost"));
		reimb.setOriginalAmount(amount);
		reimb.setTentativeAmount(amount);
		reimb.setDescription(req.getParameter("description"));
		reimb.setFormat(req.getParameter("gradeformat"));
		if( reimburseServ.addReimbursement(reimb) ) {
			pw.write("Successfully added your submission!");
		}else {
			pw.write("Could not process your form!");
		}
		resp.sendRedirect("employee.html");

	}


	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getPathInfo();
		info("whole: " + name);
		String id = name.substring(1);
		info("url: " + id);
			
		
	}
	

}
