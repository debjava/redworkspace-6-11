package com.ddlab.rnd.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MyThread implements Runnable 
{
	protected static Logger logger = Logger.getLogger(MyThread.class);
	private Properties configProp = new Properties();
	
	public MyThread()
	{
		try 
		{
			InputStream inStream = new FileInputStream("config/config.properties");
			configProp.load(inStream);
			inStream.close();
			System.out.println(configProp);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public void run() 
	{
		int timeInSec = Integer.parseInt( configProp.getProperty("time") );
		while( AppConstants.RUN_FLAG )
		{
			logger.debug("Service is running ...");
			try 
			{
				Thread.sleep(timeInSec);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

}
