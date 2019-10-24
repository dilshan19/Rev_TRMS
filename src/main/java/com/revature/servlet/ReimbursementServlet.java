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

public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ReimbursementService reimburseServ = new ReimbursementService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			ObjectMapper om = new ObjectMapper();
			HttpSession session = request.getSession(false);
			if (session == null) {
				LoggerUtil.debug("Unknown user type accessing supervisor dashboard");
				return;
			}
			String type = (String) session.getAttribute("usertype");
			String email = (String) session.getAttribute("email");
			LoggerUtil.debug("(REIMBSERVLET) doGet, usertype: " + type);
			ArrayList<Reimbursement> reimbList = null;

			switch (type) {
			case "emp":
				reimbList = reimburseServ.getAllReimbursements(email);
				response.getWriter().write(om.writeValueAsString(reimbList));
				break;
			default:
				reimbList = reimburseServ.getAllReimbursements(null);
				response.getWriter().write(om.writeValueAsString(reimbList));
			}
		} catch (Exception e) {
			error(e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getPathInfo();
		debug("(REIMBSERVLET) doPost, ext: " + name); // todo: figure out how to get the email without the client re
														// inputting
		Reimbursement reimb = new Reimbursement();
		PrintWriter pw = resp.getWriter();

		HttpSession session = req.getSession(false);
		String email = (String) session.getAttribute("email");
		LoggerUtil.debug("(REIMBSERVLET) doPost, email: " + email);

		reimb.setRequestorEmail(email);
		reimb.setType(req.getParameter("type"));

		String addr = req.getParameter("address");
		String addr2 = (req.getParameter("address2") != null) ? req.getParameter("address2") : "";
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String zip = req.getParameter("zip");
		String completeAddr = addr + ", " + addr2 + ", " + city + ", " + state + ", " + zip;
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
		if (reimburseServ.addReimbursement(reimb)) {
			pw.write("Successfully added your submission!");
		} else {
			pw.write("Could not process your form!");
		}
		resp.sendRedirect("employee.html");

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		try {
			HttpSession session = req.getSession(false);
			String name = req.getPathInfo();
			int id = Integer.parseInt(req.getParameter("id"));			
			String type = (String) session.getAttribute("usertype");
			String email = (String) session.getAttribute("email");
			LoggerUtil.debug("(REIMBSERVLET) doPut, usertype: " + type);
			if (type == "emp") {
				error("An employee has access a non-requestor dashboard");
				return;
			}
			debug("ID: " + id + " accepted");
			reimburseServ.acceptReimbursement(id, email, type);

		} catch (Exception e) {
			error(e);
		}

	}

}
