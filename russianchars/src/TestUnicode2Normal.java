
public class TestUnicode2Normal
{
	
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



	public static void main(String[] args) throws Exception
	{
//		String unicodeStr = "\u00A3";
//		System.out.println(  asciiToNative("\u2260")  );
//		System.out.println( new String("\u2260"));
//		System.out.println("---"+new String("\u0430\u0441\u0434\u0446\u0432\u0431\u0444\u0440\u0448\u044f".getBytes("UTF-8"),"UTF-8"));
		
		String ss = "\u0444";
		String native2AsciiStr = nativeToAscii(ss);
		System.out.println("Native to Ascii---->"+native2AsciiStr);
		System.out.println("Ascii to Native ------->"+asciiToNative(ss));
		System.out.println("\u017Elu\u0165ou\u010Dk\u00FD k\u016F\u0148");
		Character letter = new Character('\u05E2');//'\u00F6'
		System.out.println(letter);
		
		
	}

}
