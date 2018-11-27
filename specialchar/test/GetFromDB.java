import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class GetFromDB 
{
	private static Connection getConnection() throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","test","test");
		return conn;
	}
	
	public static void main(String[] args) throws Exception
	{
		String queryStr = "select * from language_data_table where id = ( select max(id) from language_data_table )";
		Connection conn = getConnection();
		
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(queryStr);
		rset.next();
		int id = rset.getInt(1);
		String data = rset.getString(2);
		System.out.println(id);
		System.out.println(data);
		
		ByteArrayInputStream bis = new ByteArrayInputStream(data.getBytes());
		OutputStream out = new FileOutputStream("D:/tempdata/temp.txt");
		OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
		osw.write(data);
		osw.flush();
		osw.close();
		out.close();
		
		
		
		
	}

}
