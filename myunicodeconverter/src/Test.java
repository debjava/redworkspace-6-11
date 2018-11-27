import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;




public class Test 
{

	public static void main(String[] args) throws Exception
	{
		String rus = "яасшердфтгычйу цвбнзх";
		String unicodeVal = UnicodeConverter.toEscapedUnicode(rus);
		System.out.println(unicodeVal);
		
		
		String originalVal = UnicodeConverter.fromEscapedUnicode(unicodeVal);
		System.out.println(originalVal);
		
		File file = new File("data/checkRu.txt");
		byte[] buff = new byte[ (int)file.length() ];
		InputStream in = new FileInputStream(file);
		in.read(buff);
		String s1 = new String(buff);
		unicodeVal = UnicodeConverter.toEscapedUnicode(s1);
		System.out.println(unicodeVal);
		originalVal = UnicodeConverter.fromEscapedUnicode(unicodeVal);
		System.out.println(originalVal);
		
		
		
	}

}
