import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class TestReader11 {

	public static String nativeToAscii( String input ) {
		if (input == null) {
			return null;
		}
		StringBuffer buffer = new StringBuffer( input.length() + 60 );
		for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c <= 0x7E) { 
                buffer.append(c);
            }
            else {
            	buffer.append("\\u");
            	String hex = Integer.toHexString(c);
            	for (int j = hex.length(); j < 4; j++ ) {
            		buffer.append( '0' );
            	}
            	buffer.append( hex );
            }
        }
		return buffer.toString();
	}
	
	public static void main(String[] args) throws Exception
	{
//		BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream( new File("F:/rus/russian.txt")), "UTF-8"));
		BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream( new File("F:/rus/cyrillic1.txt"))));
		String ss = null;
		StringBuffer sb = new StringBuffer();
		while( ( ss = br.readLine() ) != null  )
		{
//			System.out.println(ss);
			sb.append(ss);
		}
		
//		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(new File("F:/rus/testrux12.txt")), "UTF-8"));
		
		
		String ss11 = nativeToAscii(sb.toString());
		
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(new File("F:/rus/cyrillicUnicode.txt"))));
//		bw.write(sb.toString());
		bw.write(ss11);
		bw.close();
		
		
		
		
		
	}

}
