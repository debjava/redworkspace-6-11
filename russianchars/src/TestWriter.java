import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;


public class TestWriter {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception 
	{
//		FileWriter fw = new FileWriter( new File("F:/rus/testrux.txt"));
		
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(new File("F:/rus/testrux.txt")), "UTF-8"));
		
		String str = new String("\u042F");
//		str = "A";
		bw.write(str);
		bw.close();
	}

}
