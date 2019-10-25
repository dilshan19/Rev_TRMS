package com.revature.servlet;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class MultipartServlet
 */
@WebServlet("/uploadToDB")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class PersistAndOpenFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private static FileUpload fileUpload = new FileUploadService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String thePath = request.getParameter("thePath");
		ObjectMapper om = new ObjectMapper();
		String body = request.getReader().readLine();
		String thePath = om.readValue(body, String.class);
		File fileToOpen = new File("C:/Repositories/P1/Rev_TRMS-fixReimb/src/main/webapp/WEB-INF/uploads/" + thePath);
		Desktop desktop = Desktop.getDesktop();
		if (fileToOpen.exists()) {
			desktop.open(fileToOpen);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get the file chosen by the user

		ObjectMapper om = new ObjectMapper();
		String body = request.getReader().readLine();
		String thePath = om.readValue(body, String.class);
		if (!thePath.contains("fakepath")) {
			File fileToOpen = new File(
					"C:/Repositories/P1/Rev_TRMS-fixReimb/src/main/webapp/WEB-INF/uploads/" + thePath);
			Desktop desktop = Desktop.getDesktop();
			if (fileToOpen.exists()) {
				desktop.open(fileToOpen);
			}
		} else {
			String[] sArr = thePath.split("\\\\");
			thePath = sArr[sArr.length - 1];
			// thePath = request.getParameter("thePath");
			// Part filePart = request.getPart("fileupl");

			// get the InputStream to store the file somewhere
			// InputStream fileInputStream = filePart.getInputStream();

			// for example, you can copy the uploaded file to the server
			// note that you probably don't want to do this in real life!
			// upload it to a file host like S3 or GCS instead
			// File fileToSave = new File("WebContent/uploaded-files/" +
			// filePart.getSubmittedFileName());
			// String thePath = request.getParameter("thePath");
			File fileToSave = new File(
					"C:/Repositories/P1/Rev_TRMS-fixReimb/src/main/webapp/WEB-INF/uploads/" + thePath);
			// Files.copy(fileInputStream, fileToSave.toPath(),
			// StandardCopyOption.REPLACE_EXISTING);
			if (!fileToSave.exists()) {
				Files.createFile(fileToSave.toPath());
			} else {
				Files.copy(fileToSave.toPath(), fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
		}
		// get the URL of the uploaded file
		// String fileUrl = "http://localhost:8081/uploaded-files/" +
		// filePart.getSubmittedFileName();

		// You can get other form data too
		// String name = request.getParameter("name");

		// create output HTML that uses the
//		response.getOutputStream().println("<p>Thanks ! Here's a link to your uploaded file:</p>");
//		response.getOutputStream().println("<p><a href=\"" + fileUrl + "\">" + fileUrl + "</a></p>");
//		response.getOutputStream().println("<p>Upload another file <a href=\"http://localhost:8080/index.html\">here</a>.</p>");
	}
}
