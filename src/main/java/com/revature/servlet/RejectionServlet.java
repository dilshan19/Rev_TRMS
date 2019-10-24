package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.service.ReimbursementService;
import static com.revature.util.LoggerUtil.*;

public class RejectionServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	private static ReimbursementService reimburseServ = new ReimbursementService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			debug("(RejectionServlet) doGet");

			HttpSession session = req.getSession(false);
			String type = (String) session.getAttribute("usertype");
			if (type != "ds") {
				debug("Not an supervisor; Go away!");
			} else {
				resp.sendRedirect("denyreason.html");
				debug("redirected to denial page");
			}
		} catch (Exception e) {
			debug("Supervisor exception, doGet");
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {			
			HttpSession session = req.getSession(false);
			String type = (String) session.getAttribute("usertype");
			String reason = req.getParameter("reason");
			String email = (String) session.getAttribute("email");
			int id = (Integer) session.getAttribute("denyId");
			debug("(RejectionServlet) doPost, employee email: " + email + " Empl type: " + reason);
			if(type != "ds") {
				debug("Not an supervisor. Go away!");
			}else {
				reimburseServ.insertReason(id, email, reason);
				resp.sendRedirect("DSDashboard.html");					
			}
		} catch (Exception e) {
			debug("Supervisor exception, doPost");
			e.printStackTrace();
		}

	}


	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
			
		
		
	}
	

}
