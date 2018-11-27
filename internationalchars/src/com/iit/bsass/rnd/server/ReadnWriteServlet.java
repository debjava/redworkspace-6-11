package com.iit.bsass.rnd.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddlab.rnd.util.FileUtil;

/**
 * Servlet implementation class ReadnWriteServlet
 */
public class ReadnWriteServlet extends HttpServlet {

	private static final long serialVersionUID = -7641852743670634041L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String textdata = request.getParameter("textdata");
		System.out.println("TEXT DATA :::"+textdata);
//		FileUtil.write2FileUTF8("F:/javaworkspaces/redworkspace-6-11/internationalchars/tempdata/txtData.txt", textdata);
		
		String originalVal = UnicodeConverter.fromEscapedUnicode(textdata);
		System.out.println("Original Value---->"+originalVal);
		String filePath = "F:/javaworkspaces/redworkspace-6-11/internationalchars/tempdata/originalData.txt";
		FileUtil.write2FileUTF8(filePath, originalVal);
		
		String contentInUtf8 = FileUtil.readFileInUTF8(filePath);//It is working as we are reading as UTF-8
//		String contentInUtf8 = FileUtil.readFileAsString(filePath);//It will not work, you have to read as UTF-8
		
		response.setCharacterEncoding("UTF-8");//This line is important, otherwise you will not get the proper response in the html page
		PrintWriter out = response.getWriter();
		out.println("Response from Server ...\n");
//		out.println(  "["+originalVal+"]" );
		out.println(  "["+contentInUtf8+"]" );
		
		
	}

}
