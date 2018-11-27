package com.ddlab.rnd.util;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {

	//This is for only Oracle
	private static String dbUserName = "test";
	private static String dbPassword = "test";
	private static String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static String driverName = "oracle.jdbc.driver.OracleDriver";

	private static Connection getConnection() throws Exception
	{
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl,dbUserName, dbPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch( Exception e) {
			e.printStackTrace();
			throw e;
		}
		return conn;
	}
	
	public static void save2DB( String pData ) throws Exception
	{
		Connection conn = null;
		try {
			conn = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception while getting a database connection ...");
		}
		try {
			storeData2Table(conn, pData);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception while storing data into database table ...");
		}
		finally
		{
			if( conn != null ) conn.close();
		}
	}
	
	public static String retrieveFromDB() throws Exception
	{
		Connection conn = null;
		String result = null;
		try {
			conn = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception while getting a database connection ...");
		}
		
		try {
			String queryString = "select * from language_data_table";
			result = executeQuerynRetrieve(conn, queryString);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception while storing data into database table ...");
		}
		finally
		{
			if( conn != null ) conn.close();
		}
		return result;
	}
	
	public static void makeDbOperation( String pName , PrintWriter out)
	{
		Connection conn = null;
		try 
		{
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl,dbUserName, dbPassword);

			String truncQuery = "truncate table language_data_table";
			executeQuery(conn, truncQuery);
			insertValues2Table(conn, pName);
			String queryString = "select * from language_data_table";
			executeQuery(conn, queryString, out);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if( conn != null ) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void storeData2Table( Connection conn , String data )
	{
		String query = "insert into language_data_table(data) values(?)";
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
	

	private static void insertValues2Table( Connection conn , String data )
	{
		String query = "insert into language_data_table(id,data) values(?,?)";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 1);
			pstmt.setString(2, data);
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

	private static void executeQuery( Connection conn , String query )
	{
		try {
			Statement stmt = conn.createStatement();
			boolean flag = stmt.execute(query);
			if( flag ) System.out.println("Query Executed Successfully ...");
			else
				System.out.println("Failed");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	private static String executeQuerynRetrieve( Connection conn , String query ) throws Exception
	{
		StringBuilder builder = new StringBuilder();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while( rs.next() )
			{
				String value = rs.getString(2);
				builder.append(value).append("\n");
			}
			System.out.println("Values------->"+builder.toString());
		} catch (Exception e) {
			throw e;
		}
		finally
		{
			if( rs != null ) rs.close();
			if( stmt != null ) stmt.close();
			if( conn != null ) conn.close();
		}
		return builder.toString();
	}

	private static void executeQuery( Connection conn , String query , PrintWriter out)
	{
		try {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			StringBuilder builder = new StringBuilder();
			while( rs.next() )
			{
				String value = rs.getString(2);
				builder.append(value);
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
				//				out.println( "\u00E4\u00E5\u00E4\u00E5" );

			}
			out.println(builder.toString());
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



}
