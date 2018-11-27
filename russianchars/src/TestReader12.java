import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class TestReader12 {

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
	
	public static String asciiToNative( String input ) {
		if (input == null) {
			return null;
		}
		StringBuffer buffer = new StringBuffer( input.length() );
		boolean precedingBackslash = false;
		for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (precedingBackslash) {
            	switch (c) {
            	case 'f': c = '\f'; break;
            	case 'n': c = '\n'; break;
            	case 'r': c = '\r'; break;
            	case 't': c = '\t'; break;
            	case 'u':
            		String hex = input.substring( i + 1, i + 5 );
            		c = (char) Integer.parseInt(hex, 16 );
            		i += 4;
            	}
            	precedingBackslash = false;
            } else {
            	precedingBackslash = (c == '\\');
            }
            if (!precedingBackslash) {
                buffer.append(c);
            }
        }
		return buffer.toString();
	}
	
	public static void main(String[] args) throws Exception
	{
//		BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream( new File("F:/rus/russian.txt")), "UTF-8"));
		BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream( new File("F:/rus/cyrillicUnicode.txt"))));
		String ss = null;
		StringBuffer sb = new StringBuffer();
		while( ( ss = br.readLine() ) != null  )
		{
//			System.out.println(ss);
			sb.append(ss);
		}
		
//		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(new File("F:/rus/testrux12.txt")), "UTF-8"));
		
		
		
		String ss22 = asciiToNative(sb.toString());
		
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(new File("F:/rus/testrux15.txt"))));
//		bw.write(sb.toString());
		bw.write(ss22);
		bw.close();
		
		
		
		
		
	}

}
