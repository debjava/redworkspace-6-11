package com.iit.bsass.rnd.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddlab.rnd.util.FileUtil;

public class TestServlet extends HttpServlet 
{
	private static final long serialVersionUID = 6881569196850078304L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("request.getCharacterEncoding()--->"+request.getCharacterEncoding());
		System.out.println("request.getContentType()----->"+request.getContentType());
		System.out.println("response.getCharacterEncoding()----->"+response.getCharacterEncoding());
		System.out.println("response.getContentType()------>"+response.getContentType());
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
//		response.setContentType("text/html; charset=KOI8-R");
//		response.setCharacterEncoding("KOI8-R");
		
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = new PrintWriter( new OutputStreamWriter(response.getOutputStream(), "UTF-8"), true);
		
		System.out.println("doPost Method of TestServlet ...");
		String textdata = request.getParameter("textdata");
		System.out.println("TEXT DATA :::"+textdata);
		
		FileUtil.write2FileUTF8("F:/javaworkspaces/redworkspace-6-11/internationalchars/tempdata/txtData.txt", textdata);
		
//		String unicodeVal = UnicodeConverter.toEscapedUnicode(textdata);
//		System.out.println("Unicode values---->"+unicodeVal);
		
		String originalVal = UnicodeConverter.fromEscapedUnicode(textdata);
		System.out.println("Original Value---->"+originalVal);
		FileUtil.write2FileUTF8("F:/javaworkspaces/redworkspace-6-11/internationalchars/tempdata/originalData.txt", originalVal);
		
		
//		String native2AsciiStr = Converter.nativeToAscii(textdata);
//		System.out.println("Native to Ascii---->"+native2AsciiStr);
//		System.out.println("Ascii to Native ------->"+Converter.asciiToNative(native2AsciiStr));
		
		
		PrintWriter out = response.getWriter();
//		String result = "Response from Server ...\n"+textdata+"------"+originalVal;
		out.println("Response from Server ...\n");
//		out.println(  "Original Value ["+result+"]" );
//		out.println(  "\n\n" );
//		out.println(  "Converted Value ["+originalVal+"]" );
		out.println(  "["+originalVal+"]" );
		
		System.out.println("After Setting request.getCharacterEncoding()--->"+request.getCharacterEncoding());
		System.out.println("After Setting request.getContentType()----->"+request.getContentType());
		System.out.println("After Setting response.getCharacterEncoding()----->"+response.getCharacterEncoding());
		System.out.println("After Setting response.getContentType()------>"+response.getContentType());
		
		
		
//		PrintWriter out = response.getWriter();
//		String result = "Response from Server ...\n"+unicodeVal+"------"+originalVal;
//		out.println(  result );
		
		
//		System.out.println("\u0430\u0441\u0434\u0446\u0432\u0431\u0444\u0440\u0448\u044f");
//		out.println("\u0430\u0441\u0434\u0446\u0432\u0431\u0444\u0440\u0448\u044f");
		
//		out.println(Converter.asciiToNative(native2AsciiStr));
//		
//		 byte[] utfBytes = textdata.getBytes("UTF-8");
//	        String result11 = new String(utfBytes, "ISO-8859-1");
//	        out.println(result11);
		
		
//		RequestUtil.showAllRequestParams(request);
//		RequestUtil.showRequestParameterMap(request);
//		RequestUtil.showMethodInfo(request);
		
		
	}
	
	private void makeDbOperation( String pName , PrintWriter out)
	{
		try 
		{
//			Context context = new InitialContext();
//			DataSource ds = (DataSource)context.lookup("OracleDS");
//			Connection conn = ds.getConnection();
//			System.out.println("Database Connection :::"+conn);
			
//			if( conn == null )
//			{
//				String dbUserName = "cfealexfse01dev";
//				String dbPassword = "cfealexfse01dev";
//				String dbUrl = "jdbc:oracle:thin:@idealoradb01:1521:orcl";
//				String driverName = "oracle.jdbc.driver.OracleDriver";
//			    Class.forName(driverName);
//			    conn = DriverManager.getConnection(dbUrl,dbUserName, dbPassword);
//			}
//			if( conn != null )
//			{
//				insertValues2Table(conn, pName);
//				String queryString = "select * from swedishchartab";
//				executeQuery(conn, queryString, out);
//			}
			
			String dbUserName = "cfealexfse01dev";
			String dbPassword = "cfealexfse01dev";
			String dbUrl = "jdbc:oracle:thin:@idealoradb01:1521:orcl";
			String driverName = "oracle.jdbc.driver.OracleDriver";
		    Class.forName(driverName);
		    Connection conn = DriverManager.getConnection(dbUrl,dbUserName, dbPassword);
		    
		    insertValues2Table(conn, pName);
			String queryString = "select * from swedishchartab";
			executeQuery(conn, queryString, out);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private static void insertValues2Table( Connection conn , String data )
	{
		String query = "insert into swedishchartab(name) values(?)";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, data);
			int count = pstmt.executeUpdate();
			System.out.println("Result Count :::"+count);
			conn.commit();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if( pstmt != null )
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	private static void executeQuery( Connection conn , String query , PrintWriter out)
	{
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			while( rs.next() )
			{
				String value = rs.getString(1);
				System.out.println("Values------->"+value);
				
//				byte[] buff = value.getBytes();
//				String ss = new String(buff,"ISO-8859-1");
//				System.out.println(ss);
				
				StringBuilder sb = new StringBuilder();
				for( int i = 0 ; i < value.length() ; i++ )
				{
					sb.append( charToHex(value.charAt(i)) );
				}
				System.out.println("Unicode value----->"+sb.toString());
				out.println( "\u00E4\u00E5\u00E4\u00E5" );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
	}
	
	static public String byteToHex(byte b) {
	      // Returns hex String representation of byte b
	      char hexDigit[] = {
	         '0', '1', '2', '3', '4', '5', '6', '7',
	         '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	      };
	      char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
	      return new String(array);
	   }

	   static public String charToHex(char c) {
	      // Returns hex String representation of char c
	      byte hi = (byte) (c >>> 8);
	      byte lo = (byte) (c & 0xff);
	      return byteToHex(hi) + byteToHex(lo);
	   }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("doGet Method of CardServlet ...");
		doPost(request, response);
	}

}
