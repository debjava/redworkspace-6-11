import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class TestReader {

	public static String toHTML(String unicode) {
	    String tc = unicode;
	    String output = "";
	    char[] ca = tc.toCharArray();
	    for (int i = 0; i < ca.length; ++i) {
	      char a = ca[i];
	      if ((int) a > 255) {
	        output += "&#" + (int) a + ";";
	      } else {
	        output += a;
	      }
	    }
	    return output;
	  }
	  public static String toJAVA(String unicode) {
	    String tc = unicode;
	    String output = "";
	    char[] ca = tc.toCharArray();
	    for (int i = 0; i < ca.length; ++i) {
	      char a = ca[i];
	      if ((int) a > 255) {
	        output += "\\u" + Integer.toHexString((int) a);
	      } else {
	        output += a;
	      }
	    }
	    return output;
	  }
	  
	 public static String readInput( String filePath ) {

		    StringBuffer buffer = new StringBuffer();
		    try {
		      FileInputStream fis = new FileInputStream(filePath);
		      InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		      Reader in = new BufferedReader(isr);
		      int ch;
		      while ((ch = in.read()) > -1) {
		        buffer.append((char) ch);
		      }
		      in.close();
		      return buffer.toString();
		    } catch (IOException e) {
		      e.printStackTrace();
		      return null;
		    }
		  }
	
	public static void main(String[] args) {

		try {
//			File file = new File("data/checkRu.txt");
//			byte[] buff = new byte[ (int)file.length() ];
//			InputStream in = new FileInputStream(file);
//			in.read(buff);
//			String s1 = new String(buff);
			
			String s1 = "Б";
			
			String s11 = new String( s1.getBytes() );
			
			System.out.println( Converter.nativeToAscii(s1));
			System.out.println( Converter.asciiToNative(Converter.nativeToAscii(s1)));
			
			
			System.out.println( Converter.nativeToAscii(s11));
			System.out.println( Converter.asciiToNative(Converter.nativeToAscii(s11)));
			
//			System.out.println("All Data :::\n"+new String(buff,"ISO-8859-5"));
			
			
			
//			String ss = "äå";
//			String uniCodeVal = "\u0430\u0441\u0434\u0446\u0432\u0431\u0444\u0440\u0448\u044f";
//			System.out.println("html---->"+toHTML(uniCodeVal));
//			System.out.println("java---->"+toJAVA(uniCodeVal));
//			System.out.println(readInput("data/checkRu.txt"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
