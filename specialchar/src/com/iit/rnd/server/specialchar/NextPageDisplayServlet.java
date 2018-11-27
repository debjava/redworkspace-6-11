package com.iit.rnd.server.specialchar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Debadatta Mishra(PIKU)
 *
 */
public class NextPageDisplayServlet extends HttpServlet 
{
	private static final long serialVersionUID = 4818823488994815789L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("-------------- NextPageDisplayServlet : doPost() --------------");
		/*
		 * The following three lines are important to handle special char
		 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String textdata = request.getParameter("submitText");
		System.out.println("Original TEXT DATA \n"+textdata);
		PrintWriter out = response.getWriter();
		try 
		{
			if( textdata != null )
			{
				String actualValue = UnicodeConverter.fromEscapedUnicode(textdata);
				System.out.println("Unicode Converted Value \n"+actualValue);
				out.println(actualValue);
			}
			else
			{
				out.println(  "Error ...." );
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			out.println("UnExpected Server Exception thrown . "+e.getMessage());
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("doGet Method of NextPageDisplayServlet ...");
		doPost(request, response);
	}
}
