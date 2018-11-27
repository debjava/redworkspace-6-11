import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.commons.codec.binary.Hex;


public class TempTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		 String ss = "äå";
//		  ss = "асдцвбфршя";
		  
//		  byte[] bb = BinaryCodec.toAsciiBytes(ss.getBytes());
//		  System.out.println( new String(bb));
		  
		  char[] ch = Hex.encodeHex(ss.getBytes());
		  System.out.println( new String(ch));
		  System.out.println(  Hex.encodeHexString(ss.getBytes()) );
		  System.out.println(  new String( new Hex().encode(ss.getBytes()) )  );
		  
		  File file = new File("data/checkRu.txt");
		  BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream (file)));
			BufferedReader readerRu = new BufferedReader(new InputStreamReader(new FileInputStream (file), "Unicode"));
			OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream("data/file2.txt"), "Unicode");
		  
	}

}
