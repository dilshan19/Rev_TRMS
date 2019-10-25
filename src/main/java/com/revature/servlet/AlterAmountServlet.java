package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.service.ReimbursementService;
import static com.revature.util.LoggerUtil.*;

public class AlterAmountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ReimbursementService reimburseServ = new ReimbursementService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			debug("(AlterAmountServlet) doGet");

			HttpSession session = req.getSession(false);
			String type = (String) session.getAttribute("usertype");
			if (type != "bc") {
				debug("Not an bc; Go away!");
			} else {
				resp.sendRedirect("changeAmount.html");
				debug("redirected to alter page");
			}
		} catch (Exception e) {
			debug("BC exception, doGet");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {	
			debug("(AlterAmountServlet) doPost");
			HttpSession session = req.getSession(false);
			String type = (String) session.getAttribute("usertype");
			String amount = req.getParameter("amount");
			String reason = req.getParameter("reason");

			String email = (String) session.getAttribute("email");
			int id = (Integer) session.getAttribute("alterId");
			debug("(AlterServ) doPost, employee email: " + email + " amount: " + amount);
			if(type != "bc") {
				debug("Not an bc. Go away!");
			}else {
				debug("(BC) altering amount");
				reimburseServ.alterAmount(id,  Double.parseDouble(amount) , reason, email);
				resp.sendRedirect("BCDashboard.html");					
			}
		} catch (Exception e) {
			debug("BC exception, doPost");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	

	}

}
