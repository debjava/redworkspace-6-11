package com.iit.rnd.server.specialchar;

import java.io.IOException;
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

/**
 * @author Debadatta Mishra (PIKU)
 *
 */
public class SpecialCharAjaxServlet extends HttpServlet 
{
	private static final long serialVersionUID = 6881569196850078304L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("-------------- SpecialCharAjaxServlet : doPost() --------------");
		/*
		 * The following three lines are important to handle special char
		 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String textdata = request.getParameter("unicodeTextData");
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
		System.out.println("doGet Method of SpecialCharAjaxServlet ...");
		doPost(request, response);
	}

}
