package com.revature.servlet;

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
import static com.revature.util.LoggerUtil.*;

public class DepartmentHeadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ReimbursementService reimburseServ = new ReimbursementService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			ObjectMapper om = new ObjectMapper();
			debug("(DH) doGet)");
			ArrayList<Reimbursement> reimbList = null;
			HttpSession session = req.getSession(false);
			String type = (String) session.getAttribute("usertype");
			String email = (String) session.getAttribute("email");
			debug("(DH) doGet, employee email: " + email + " Empl type: " + type);
			if (type != "dh") {
				debug("Not an DH! Go away!");
			} else {
				reimbList = reimburseServ.getAllReimbursements(null);
				resp.sendRedirect("DHDashboard.html");
			}
		} catch (Exception e) {
			debug("Supervisor exception, doGet");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
