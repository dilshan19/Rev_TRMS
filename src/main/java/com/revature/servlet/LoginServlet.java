package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet  {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("Hello Get!");
		PrintWriter pw = resp.getWriter();
		String position = req.getParameter("position");
		String name = req.getParameter("name");
		
		pw.write("<h1>Hello " + position +" : " + name + "!</h1>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("Hello Post");
		PrintWriter pw = resp.getWriter();
		pw.write("<h3>Post method from HelloWorld servlet!</h3>");
	}
	
}
