package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.pojo.User;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService = new UserServiceImpl();

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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.loginUser(username, password);
		if (user != null) {
			response.getWriter().write("Welcome to your homepage" + user.getFullName());
		} else {
			response.getWriter().write("Invalid login credentials");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.loginUser(username, password);
		if (user != null) {
			request.getSession().setAttribute("email", user.getEmail());
			request.getSession().setAttribute("pass", user.getPassword());
			if (user.getManagerStatus().equals("employee")) {
				response.sendRedirect("employee");
			} else if(user.getManagerStatus().equals("supervisor")){
				response.sendRedirect("manager");
			} else if(user.getManagerStatus().equals("departmentHead")){
				response.sendRedirect("departmentHead");
			} else if(user.getManagerStatus().equals("benco")){
					response.sendRedirect("benco");
			} else {
				System.out.println("Couldn't find where to redirect you.");
			}
		} else {
			response.getWriter().write("Sorry, but you were not able to login correctly :(");
		}
	}

}
