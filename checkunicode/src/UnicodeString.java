import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class UnicodeString
{
	public static String convert(String str)
	{
		StringBuffer ostr = new StringBuffer();

		for(int i=0; i<str.length(); i++) 
		{
			char ch = str.charAt(i);

			if ((ch >= 0x0020) && (ch <= 0x007e))	// Does the char need to be converted to unicode? 
			{
				ostr.append(ch);					// No.
			} else 									// Yes.
			{
				ostr.append("\\u") ;				// standard unicode format.
				String hex = Integer.toHexString(str.charAt(i) & 0xFFFF);	// Get hex value of the char. 
				for(int j=0; j<4-hex.length(); j++)	// Prepend zeros because unicode requires 4 digits
					ostr.append("0");
				ostr.append(hex.toLowerCase());		// standard unicode format.
				//ostr.append(hex.toLowerCase(Locale.ENGLISH));
			}
		}

		return (new String(ostr));		//Return the stringbuffer cast as a string.
	}

	public static String getHtmlUnicode( String str )
	{
		StringBuilder sb = new StringBuilder();
		for ( int i = 0; i < str.length(); ++i ) 
		{
			char c = str.charAt( i );
			int j = (int) c;
			sb.append('&').append('#').append(Integer.toString(c)).append(";");
		}
		return sb.toString();
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
	
	 public static String convertUnicodeToNative(String source, String charset)
	          throws IOException
	      {
	          ByteArrayOutputStream baos = new ByteArrayOutputStream();
//	          OutputStreamWriter out = new OutputStreamWriter(baos, charset);
	          OutputStreamWriter out = new OutputStreamWriter(baos);
	          out.write(source);
	          out.close();
	          return baos.toString();
	      }
	 
	 
	 public static int CHAR_BUFFER_SIZE = 4 * 1024;
	 
	 
	 public static String convertNativeToUnicode(String input, String charset)
	          throws IOException
	      {
//	          InputStreamReader in = new InputStreamReader
//	              (new ByteArrayInputStream(input.getBytes()), charset);
		 
		 InputStreamReader in = new InputStreamReader
         (new ByteArrayInputStream(input.getBytes()));
	          StringBuffer output = new StringBuffer();
	          char[] buf = new char[CHAR_BUFFER_SIZE];
	          int count = 0;
	          while ((count = in.read(buf, 0, CHAR_BUFFER_SIZE)) > 0)
	          {
	              output.append(buf, 0, count);
	          }
	          in.close();
	          return output.toString();
	      }

	public static void main(String[] args) throws Exception
	{
		String ss = "äå";//éöÉÅÄÖ
		String convertedStr = convert(ss);
		System.out.println( "Unicode value ::::"+convertedStr );
		System.out.println("Original :::"+toJAVA(convertedStr));

//		Charset cs = Charset.defaultCharset();
//		CharsetEncoder encoder = cs.newEncoder();
//		CharsetDecoder decoder = cs.newDecoder();
//		
//		ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(ss)); 
//		System.out.println( new String( bbuf.array() ));
		
		System.out.println( "-----"+convertUnicodeToNative("u00e4", "UTF-8") );

		System.out.println("----->"+convertNativeToUnicode(ss, null));



//		System.out.println( new String("\u00e4\u00e5"));
//		Matcher matcher = Pattern.compile("\\#((?i)[0-9a-f]{4})").matcher(convertedStr);
//		while (matcher.find())
//		{
//			int codepoint = Integer.valueOf(matcher.group(1), 16);     
//			convertedStr = convertedStr.replaceAll(matcher.group(0), String.valueOf((char) codepoint)); 
//		}
//		System.out.println(convertedStr); // blah a blah 

		//		System.out.println( new String("\u00e4\u00e5\u00e9\u00f6\u00c9\u00c5\u00c4\u00d6"));

		//		StringBuilder sb = new StringBuilder();
		//		for ( int i = 0; i < ss.length(); ++i ) 
		//		{
		//			char c = ss.charAt( i );
		//			int j = (int) c;
		//			System.out.println(j);
		//			sb.append('&').append('#').append(Integer.toString(c)).append(";");
		//			
		//			String s1 = "&#"+Integer.toString(c);
		//			System.out.println(s1);
		//		}
		//		System.out.println(sb.toString());
		//		System.out.println("&#228");

		//		System.out.println(  getHtmlUnicode(ss));

	}

}