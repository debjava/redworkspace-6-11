import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.iit.rnd.server.specialchar.UnicodeConverter;


public class TestDb 
{
	private static byte[] getFileBytes( String filePath ) throws Exception
	{
		File file = new File( filePath );
		byte[] fileBuff = new byte[(int)file.length()];
		InputStream in = new FileInputStream(file);
		in.read(fileBuff);
		return fileBuff;
	}
	
	private static Connection getConnection() throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","test","test");
		return conn;
	}
	
	private static void store2DB( String query , Connection conn , String data ) throws Exception
	{
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, 1);
		pstmt.setString(2, data);
		int val = pstmt.executeUpdate();
		System.out.println("Executed successfully ...."+val);
	}
	
	public static void main(String[] args) throws Exception
	{
		String filePath = "D:/tempdata/oriya.txt";
		byte[] buff = getFileBytes(filePath);
		
		ByteArrayInputStream bis = new ByteArrayInputStream(buff);
		InputStreamReader insr = new InputStreamReader(bis,"UTF-8");
		BufferedReader br = new BufferedReader( insr );
		String ss = null;
		StringBuffer sb = new StringBuffer();
		while( ( ss = br.readLine() ) != null  )
		{
			sb.append(ss).append("\n");
		}
		String fileContents = sb.toString();
		br.close();
		insr.close();
		bis.close();
		
		String unicodeVal = UnicodeConverter.toEscapedUnicode(fileContents);
		System.out.println("Unicode ===\n"+unicodeVal);
		String originalVal = UnicodeConverter.fromEscapedUnicode(unicodeVal);
		System.out.println("Original ::::"+originalVal);
		System.out.println(originalVal);
		
		Connection conn = getConnection();
		String queryStr = "insert into language_data_table (id,data) values(?,?)";
		store2DB(queryStr, conn, originalVal);
	}
}
