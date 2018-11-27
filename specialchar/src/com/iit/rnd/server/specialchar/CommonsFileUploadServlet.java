package com.iit.rnd.server.specialchar;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
/**
 * @author Debadatta Mishra (PIKU)
 *
 */
public class CommonsFileUploadServlet extends HttpServlet 
{
	private static final long serialVersionUID = 2146902009710211391L;
	
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("-------------- CommonsFileUploadServlet : doPost() --------------");
		/*
		 * The following few lines are very important
		 * for special character handling.
		 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
	    PrintWriter out = response.getWriter();
		DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
		/*
		 *Set the size threshold, above which content will be stored on disk.
		 */
		fileItemFactory.setSizeThreshold(1*1024*1024); //1 MB
		/*
		 * Set the temporary directory to store the uploaded files of size above threshold.
		 */
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		ByteArrayInputStream bis = null;
		InputStreamReader insr = null;
		BufferedReader br = null;
		try 
		{
			List items = uploadHandler.parseRequest(request);
			Iterator itr = items.iterator();
			while(itr.hasNext()) 
			{
				FileItem item = (FileItem) itr.next();
				/*
				 * Handle Form Fields.
				 */
				if(item.isFormField()) 
				{
					System.out.println("File Name = "+item.getFieldName()+", Value = "+item.getString());
				}
				else 
				{
					System.out.println("Item Name --->"+item.getName());
					System.out.println("Length of the file --->"+item.getSize());
					byte[] buff = item.get();
					bis = new ByteArrayInputStream(buff);
					//Reade the file in UTF-8 encoding always
					insr = new InputStreamReader(bis,"UTF-8");
					br = new BufferedReader( insr );
					String ss = null;
					StringBuffer sb = new StringBuffer();
					while( ( ss = br.readLine() ) != null  )
					{
						sb.append(ss).append("\n");
					}
					String fileContents = sb.toString();
					String unicodeVal = UnicodeConverter.toEscapedUnicode(fileContents);
					System.out.println("Unicode representation \n"+unicodeVal);
					String originalVal = UnicodeConverter.fromEscapedUnicode(unicodeVal);
					System.out.println("Original values \n"+originalVal);
					out.println(originalVal);
				}
			}
		}
		catch(FileUploadException fu) 
		{
			fu.printStackTrace();
			out.println("FileUploadException Exception thrown . "+fu.getMessage());
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			out.println("UnExpected Server Exception thrown . "+ex.getMessage());
		}
		finally
		{
			if( br != null ) br.close();
			if( insr != null ) insr.close();
			if( bis != null ) bis.close();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		doPost(req, resp);
	}
 
}