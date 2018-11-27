import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class TestReadNWrite 
{
	public static String readFileAsString( String filePath )
	{
		String fileContents = null;
		try
		{
			InputStream in = new FileInputStream(filePath);
			InputStreamReader insr = new InputStreamReader(in,"UTF-8");
			BufferedReader br = new BufferedReader( insr );
			String ss = null;
			StringBuffer sb = new StringBuffer();
			while( ( ss = br.readLine() ) != null  )
			{
				sb.append(ss).append("\n");
			}
			fileContents = sb.toString();
			br.close();
			insr.close();
			in.close();
		}
		catch( FileNotFoundException fnfe )
		{
			fnfe.printStackTrace();
		}
		catch( IOException ioe )
		{
			ioe.printStackTrace();
		}
		return fileContents;
	}

	public static byte[] readFileAsBytes( String filePath )
	{
		File file = new File( filePath );
		byte[] buff = new byte[(int)file.length()];
		try
		{
			InputStream in = new FileInputStream(filePath);
			in.read(buff);
			in.close();
		}
		catch( FileNotFoundException fnfe )
		{
			fnfe.printStackTrace();
		}
		catch( IOException ioe )
		{
			ioe.printStackTrace();
		}
		return buff;
	}

	public static void writeFileContents( String filePath , String contents )
	{
		try
		{
			OutputStream out = new FileOutputStream(filePath);
			OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
			BufferedWriter bw = new BufferedWriter( osw );
			bw.write(contents);
			bw.close();
			osw.close();
			out.close();
		}
		catch( FileNotFoundException fnfe )
		{
			fnfe.printStackTrace();
		}
		catch( IOException ioe )
		{
			ioe.printStackTrace();
		}

	}

	public static String nativeToAscii( String input ) 
	{
		if (input == null) 
		{
			return null;
		}
		StringBuffer buffer = new StringBuffer( input.length() + 60 );
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c <= 0x7E) 
			{ 
				buffer.append(c);
			}
			else 
			{
				buffer.append("\\u");
				String hex = Integer.toHexString(c);
				for (int j = hex.length(); j < 4; j++ ) 
				{
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
	
	public static String toHTML(String unicode) 
	{
		String tc = unicode;
		String output = "";
		char[] ca = tc.toCharArray();
		for (int i = 0; i < ca.length; ++i) 
		{
			char a = ca[i];
			if ((int) a > 255) 
			{
				output += "&#" + (int) a + ";";
			}
			else 
			{
				output += a;
			}
		}
		return output;
	}
	
	

	public static void main(String[] args) throws Exception
	{
		//It is ok and perfect
		String readFilePath = "F:/rusalphabets/russianSmallLetters.txt";
		String writeFilePath = "F:/rusalphabets/convertedRus2.txt";
		//		String fileContents = readFileAsString(readFilePath);
		//		String unicodeContents = nativeToAscii(fileContents);
		//		System.out.println("Unicode Contents ::: \n"+unicodeContents);
		//		String russinChars = asciiToNative(unicodeContents);
		//		writeFileContents(writeFilePath, russinChars);
		
		readFilePath = "F:/rusalphabets/singleRussianchar.txt";
		String fileContents = readFileAsString(readFilePath);
		String unicodeContents = nativeToAscii(fileContents);
		System.out.println("Unicode Contents ::: "+unicodeContents);
		System.out.println("HTML Unicode Contents ::: "+getHtmlUnicode(fileContents));
		System.out.println(toHTML( new String(readFileAsBytes(readFilePath),"UTF-8") ));
		
		
		
		
		
//		byte[] bb = readFileAsBytes(readFilePath);
//		for (int k = 0; k < bb.length; k++) 
//		{
//			System.out.println("[" + k + "] = " + "0x"
//					+ UnicodeFormatter.byteToHex(bb[k]));
//		}



	}
}
