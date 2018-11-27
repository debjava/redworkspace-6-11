import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class TestRusReader {

	public static void readRussian( String filePath )
	{
		try 
		{
			File file = new File( filePath );
			byte[] buff = new byte[ (int) file.length() ];
			InputStream in = new FileInputStream(file);
			in.read(buff);
			
			String ss = new String(buff);
			System.out.println(ss);
			System.out.println( Converter.nativeToAscii(ss));
			System.out.println( Converter.asciiToNative(Converter.nativeToAscii(ss)));
			
//			byte b = -128;
//			//To Char
//			char c = (char)(b & 0xFF);
//			//To Byte
//			byte b2 = (byte)(c & 0xFF);

//			StringBuffer sb = new StringBuffer();
//			char[] buffer = new char[buff.length >> 1];
//			for(int i = 0; i < buffer.length; i++) 
//			{
//				  int bpos = i << 1;
//				  char c = (char)(((buff[bpos]&0x00FF)<<8) + (buff[bpos+1]&0x00FF));
//				  buffer[i] = c;
//			}
//			System.out.println(new String(buffer));
//			
//			for( int i = 0 ; i < buff.length ; i++ )
//			{
//				byte bb = buff[i];
////				System.out.print(bb);
//				char ch = (char)(buff[i] & 0xFF );
////				sb.append("\\u" + Integer.toHexString((int) ch));
////				System.out.print(ch);
//			}
////			System.out.println(sb.toString());
			in.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static String toJAVA(String unicode) 
	{
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
	
	public static void main(String[] args) 
	{
//		String filePath = "F:/rus/russian.txt";
//		readRussian(filePath);
		
		String str = "A";
		StringBuffer ostr = new StringBuffer();
		for(int i=0; i<str.length(); i++) 
		{
			char ch = str.charAt(i);

//			if ((ch >= 0x0020) && (ch <= 0x007e))	// Does the char need to be converted to unicode? 
//			{
//				ostr.append(ch);					// No.
//			} else 									// Yes.
//			{
//				ostr.append("\\u") ;				// standard unicode format.
//				String hex = Integer.toHexString(str.charAt(i) & 0xFFFF);	// Get hex value of the char. 
//				for(int j=0; j<4-hex.length(); j++)	// Prepend zeros because unicode requires 4 digits
//					ostr.append("0");
//				ostr.append(hex.toLowerCase());		// standard unicode format.
//				//ostr.append(hex.toLowerCase(Locale.ENGLISH));
//			}
			
			ostr.append("\\u") ;				// standard unicode format.
			String hex = Integer.toHexString(str.charAt(i) & 0xFFFF);
			for(int j=0; j<4-hex.length(); j++)	// Prepend zeros because unicode requires 4 digits
				ostr.append("0");
			ostr.append(hex.toLowerCase());	
			
		}
		System.out.println(new String(ostr));
		
		System.out.println(toJAVA(str));
		
		
	}

}
