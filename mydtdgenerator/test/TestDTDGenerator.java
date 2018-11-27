import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.ddlab.tornado.core.xml.dtd.generator.DTDGenerator;
import com.ddlab.tornado.core.xml.dtd.generator.DtdGenerationException;
import com.ddlab.tornado.core.xml.dtd.generator.MyDTDGenerator;


public class TestDTDGenerator 
{
	public static void generateDTDfromXmlFile()
	{
		String xmlFilePath = "data/org11.xml";// "data/org.xml";
		DTDGenerator generator = new MyDTDGenerator();
		try 
		{
			String dtdContents = generator.generateDTDfromXML( new File(xmlFilePath));
			System.out.println(dtdContents);
		}
		catch (DtdGenerationException e) 
		{
			e.printStackTrace();
//			System.out.println(e.getMessage());
		}
	}
	
	public static void generateDTDfromXmlContents()
	{
		String xmlFilePath = "data/org.xml";
		DTDGenerator generator = new MyDTDGenerator();
		try 
		{
			File file = new File( xmlFilePath );
			byte[] buff = new byte[(int)file.length()];
			InputStream in = new FileInputStream(file);
			in.read(buff);
			String xmlContents = new String( buff );
			String dtdContents = generator.generateDTDfromXML(xmlContents);
			System.out.println(dtdContents);
		}
		catch (DtdGenerationException e) 
		{
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
//		generateDTDfromXmlFile();
//		generateDTDfromXmlContents();
		
		String filePath = "F:/javaworkspaces/workspaces/A1/test/small.xml";
		filePath = filePath.substring(0, filePath.lastIndexOf("/"));
		System.out.println(filePath);
		
		
	}

}
