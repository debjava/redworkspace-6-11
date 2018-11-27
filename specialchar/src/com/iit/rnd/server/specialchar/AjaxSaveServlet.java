//package com.iit.rnd.server.specialchar;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class AjaxSaveServlet extends HttpServlet 
//{
//	
//	private static final long serialVersionUID = 8261374256416978540L;
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException
//	{
//		/*
//		 * The following three lines are important to handle special char
//		 */
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
//
//		System.out.println("doPost Method of AjaxSaveServlet ...");
//		String textdata = request.getParameter("unicodeTextData");
//		System.out.println("TEXT DATA :::"+textdata);
//		
////		String locationTextParam = request.getParameter("locationText");
////		System.out.println("Location Text :::"+locationTextParam);
//		
//		String saveLocation = request.getParameter("saveLocation");
//		System.out.println("Save Location :::"+saveLocation);
//		
//		PrintWriter out = response.getWriter();
//		try 
//		{
//			if( textdata != null && saveLocation != null )
//			{
//				String actualValue = UnicodeConverter.fromEscapedUnicode(textdata);
//				System.out.println("Converted Value :::"+actualValue);
////				out.println(actualValue);
//				saveText(saveLocation, actualValue);
//			}
//			else
//			{
//				out.println(  "Error ...." );
//			}
//		}
//		catch (Exception e) 
//		{
//			// Handle for any kind of exception
//			System.out.println("UnExpected Error occured ...");
//			out.println(  "UnExpected Error ..." );
//		}
//	}
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException
//	{
//		System.out.println("doGet Method of AjaxSaveServlet ...");
//		doPost(request, response);
//	}
//	
//	private void saveText( String filePath , String contents ) throws Exception
//	{
//		filePath = "F:/tempdelete/rus/russ.txt";
//		File file = new File( filePath );
////		if( file.isDirectory() && !file.exists() ) file.mkdirs();
////		if( file.i)
//		//Write the file in utf-8
//		OutputStream out = new FileOutputStream(file);
//		OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
//		BufferedWriter bw = new BufferedWriter(osw);
//		bw.write(contents);
//		bw.flush();
//		bw.close();
//		osw.close();
//		out.close();
//	}
//	
//
//}
