package com.revature.servlet;

import static com.revature.util.LoggerUtil.debug;
import static com.revature.util.LoggerUtil.error;
import static com.revature.util.LoggerUtil.info;

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
public class BencoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementService reimburseServ = new ReimbursementService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ObjectMapper om = new ObjectMapper();
			debug("(BC) doGet)");
			ArrayList<Reimbursement> reimbList = null;
			HttpSession session = req.getSession(false);
			String type = (String) session.getAttribute("usertype");
			String email = (String) session.getAttribute("email");
			debug("(BENCO) doGet, employee email: " + email + " Empl type: " + type);
			if (type != "bc") {
				debug("Not an benco! Go away!");
			} else {
				reimbList = reimburseServ.getAllReimbursements(null);
				resp.sendRedirect("BCDashboard.html");
			}
		} catch (Exception e) {
			debug("Supervisor exception, doGet");
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			HttpSession session = req.getSession(false);
			String type = (String) session.getAttribute("usertype");

			if (type != "bc") {
				debug("Non-BC performed Put to rejection servlet");
				return;
			}
			int id = Integer.parseInt(req.getParameter("id").substring(6));

			// int id = Integer.parseInt( req.getParameter("id") );
			debug("... " + id);
			session.setAttribute("alterId", id);
			debug("(BC) doGet, " + id);
			resp.sendRedirect("alter");
		} catch (Exception e) {
			error("(bc doPost) error");
			error(e);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//.updateAmount();
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
