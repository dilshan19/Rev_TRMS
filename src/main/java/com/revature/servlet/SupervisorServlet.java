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
public class SupervisorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementService reimburseServ = new ReimbursementService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ObjectMapper om = new ObjectMapper();
			String name = request.getPathInfo();
			debug("(SUPERVISOR) doGet, ext: " + name);
			ArrayList<Reimbursement> reimbList = null;
			HttpSession session = request.getSession(false);
			String type = (String) session.getAttribute("usertype");
			String email = (String) session.getAttribute("email");
			debug("(SUPERVISOR) doGet, employee email: " + email + " Empl type: " + type);
			if (type != "ds") {
				debug("Not an supervisor! Go away!");
			} else {
				reimbList = reimburseServ.getAllReimbursements(null);
				response.sendRedirect("DSDashboard.html");
			}
		} catch (Exception e) {
			debug("Supervisor exception, doGet");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			HttpSession session = req.getSession(false);

			String type = (String) session.getAttribute("usertype");

			if (type != "ds") {
				debug("Non-supervisor performed GET to rejection servlet");
				return;
			}
			int id = Integer.parseInt(req.getParameter("id").substring(5) );

			//int id = Integer.parseInt( req.getParameter("id") );
			debug("... " + id);
			session.setAttribute("denyId", id);
			info("(SupervisorServlet) doGet, " + id);
			resp.sendRedirect("reject");
		} catch (Exception e) {
			error("(supervisor doPost) error");
			error(e);
		}

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
