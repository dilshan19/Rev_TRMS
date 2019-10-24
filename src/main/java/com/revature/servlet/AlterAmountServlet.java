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

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			HttpSession session = req.getSession(false);

			String type = (String) session.getAttribute("usertype");

			if (type != "bc") {
				debug("Non-BC performed Put to rejection servlet");
				return;
			}
			double id = Double.parseDouble(req.getParameter("id").substring(6));

			// int id = Integer.parseInt( req.getParameter("id") );
			debug("... " + id);
			session.setAttribute("denyId", id);
			info("(SupervisorServlet) doGet, " + id);
			resp.sendRedirect("reject");
		} catch (Exception e) {
			error("(supervisor doPost) error");
			error(e);
		}

	}

}
