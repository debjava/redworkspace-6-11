package com.iit.bsass.rnd.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddlab.rnd.util.DatabaseUtil;

/**
 * Servlet implementation class SavenReadFromDBServlet
 */
public class SaveNreadFromDBServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveNreadFromDBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		
		String originalVal = UnicodeConverter.fromEscapedUnicode(textdata);
		System.out.println("Original Value---->"+originalVal);
		
		
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		DatabaseUtil.makeDbOperation(originalVal, out);
		out.println("Response from Server ...\n");
//		out.println(  "["+originalVal+"]" );
	}

}
