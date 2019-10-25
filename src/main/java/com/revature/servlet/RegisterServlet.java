package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.pojo.User;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService = new UserServiceImpl();

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.getRequestDispatcher("register.html").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		User user = userService.registerUser(firstName, lastName, username, password, role);
		if (user != null) {
			try {
				request.getRequestDispatcher("login.html").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.getWriter().write("Sorry, but you were not able to register correctly :(");
		}
	}

}
