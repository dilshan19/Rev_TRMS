package com.revature.servlet;

import static com.revature.util.LoggerUtil.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.User;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;
import com.revature.util.LoggerUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService = new UserServiceImpl();
	private static ReimbursementService reimburseServ = new ReimbursementService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		String name = request.getPathInfo();
		debug("(LOGINSERVLET) doGet, ext: " + name);
		HttpSession session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		LoggerUtil.debug("(LOGINSERVLET) doGet, email: " + email);
		if (name == null) { // call after logging into employee
			response.getWriter().write(om.writeValueAsString(reimburseServ.getAllReimbursements(email)));
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String button = request.getParameter("registerbutton");
		if ("".equals(button)) {
			response.sendRedirect("register");
		} else {

			String name = request.getPathInfo();
			debug("(LOGINSERVLET) doPost, ext: " + name);
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = userService.loginUser(username, password);
			if (user != null) {
				request.getSession().setAttribute("email", user.getEmail());
				request.getSession().setAttribute("pass", user.getPassword());
				request.getSession().setAttribute("usertype", user.getManagerStatus());
				if (user.getManagerStatus().equals("employee")) {
					response.sendRedirect("employee");
				} else if (user.getManagerStatus().equals("supervisor")) {
					response.sendRedirect("manager");
				} else if (user.getManagerStatus().equals("departmentHead")) {
					response.sendRedirect("departmentHead");
				} else if (user.getManagerStatus().equals("benco")) {
					response.sendRedirect("benco");
				} else {
					info("Couldn't find where to redirect you.");
				}
			}
		}
	}
}