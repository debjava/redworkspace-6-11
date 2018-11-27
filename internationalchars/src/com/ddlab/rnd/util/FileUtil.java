package com.ddlab.rnd.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class FileUtil 
{

	public static void write2FileUTF8( String filePath , String contents )
	{
		OutputStream out = null;
		OutputStreamWriter osw = null;

		try {
			out = new FileOutputStream(filePath);
			osw = new OutputStreamWriter(out,"UTF-8");
			osw.write(contents);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				 osw.flush();
				 out.flush();
				if( osw != null ) osw.close();
				if( out != null ) out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String readFileInUTF8( String filePath )
	{
		InputStream inStream = null;
		InputStreamReader insReader = null;
		final char[] buffer = new char[(int)new File(filePath).length()];
		final StringBuilder sb = new StringBuilder();
		try {
			inStream = new FileInputStream(filePath);
			insReader = new InputStreamReader(inStream,"UTF-8");
			for (;;)
			{
				int rsz;
				rsz = insReader.read(buffer, 0, buffer.length);
				if (rsz < 0)
					break;
				sb.append(buffer, 0, rsz);
			}
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}




	public static void write2File( String filePath , String contents )
	{
		Writer writer = null;
		BufferedWriter buffWriter = null;
		try {
			writer = new FileWriter(filePath);
			buffWriter = new BufferedWriter(writer);
			buffWriter.write(contents);
			buffWriter.flush();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if( buffWriter != null ) buffWriter.close();
				if( writer != null ) writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String readFileAsString( String filePath )
	{
		Reader reader = null;
		StringBuilder sb = new StringBuilder();
		BufferedReader buffReader = null;
		try {
			reader = new FileReader(filePath);
			buffReader = new BufferedReader(reader);
			String s = buffReader.readLine();
			sb.append(s);
			while( s != null ){
				s = buffReader.readLine();
				sb.append(s);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if( buffReader != null ) buffReader.close();
				if( reader != null ) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
