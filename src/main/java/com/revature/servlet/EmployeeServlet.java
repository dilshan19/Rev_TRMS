package com.revature.servlet;

import static com.revature.util.LoggerUtil.debug;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.ReimbursementService;
import com.revature.util.LoggerUtil;

/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementService reimburseServ = new ReimbursementService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String name = ((User)(request.getSession().getAttribute("user"))).getFullName();
//		String message = getServletContext().getInitParameter("message");
//		String role = getServletConfig().getInitParameter("role");
//		response.getWriter().write(message + " " + role + " " + name);
		ObjectMapper om = new ObjectMapper();
		String name = request.getPathInfo();
		debug("(EMPSERVLET) doGet, ext: " + name);
		ArrayList<Reimbursement> reimbList = null;
		try {
			HttpSession session = request.getSession(false);
			String email = (String) session.getAttribute("email");
			LoggerUtil.debug("Employee email: " + email);
			reimbList = reimburseServ.getAllReimbursements(email);
			response.sendRedirect("employee.html");	
		} catch (Exception e) {
			e.printStackTrace();
		}

		// response.getWriter().write(om.writeValueAsString(reimbList));
		//LoggerUtil.debug("->Individual's reimbs: " + om.writeValueAsString(reimbList));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getPathInfo();
		debug("(EMPSERVLET) doPost, ext: " + name);
		try {
			HttpSession session = request.getSession(false);
			String email = (String) session.getAttribute("email");
			LoggerUtil.debug("(EMPSERVLET) doPost, email: " + email);
			response.sendRedirect("reimbursement.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
