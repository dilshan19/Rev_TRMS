package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.service.FileUpload;
import com.revature.service.FileUploadService;
import com.revature.service.ReimbursementService;

/**
 * Servlet implementation class FilenameToDbServlet
 */
public class FilenameToDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static FileUpload fileServ = new FileUploadService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilenameToDbServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ObjectMapper om = new ObjectMapper();
		String body = request.getReader().readLine();
		String[] sArr = body.split("%");
		String id = sArr[0];
		id = id.substring(1);
		String fileName = sArr[1];
		fileName = fileName.substring(0,fileName.length()-1);
		//id = om.readValue(id, String.class);
		//fileName = om.readValue(fileName, String.class);
		fileServ.uploadFile(id, fileName);
		
	}

}
