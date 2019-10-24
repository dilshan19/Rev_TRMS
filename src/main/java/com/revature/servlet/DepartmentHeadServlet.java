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
import com.revature.service.ReimbursementService;

/**
 * Servlet implementation class EmployeeServlet
 */
public class DepartmentHeadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementService reimburseServ = new ReimbursementService();
	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			ObjectMapper om = new ObjectMapper();
			String name = request.getPathInfo();
			debug("(SUPERVISOR) doGet, ext: " + name);
			ArrayList<Reimbursement> reimbList = null;
			HttpSession session = request.getSession(false);
			String type = (String) session.getAttribute("usertype");
			String email = (String) session.getAttribute("email");
			debug("(SUPERVISOR) doGet, employee email: " + email + " Empl type: " + type);
			if(type != "dh") {
				debug("Not an supervisor! Go away!");
			}else {
				reimbList = reimburseServ.getAllReimbursements(null);
				response.sendRedirect("DSDashboard.html");	
			}
		} catch (Exception e) {
			debug("Supervisor exception, doGet");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
