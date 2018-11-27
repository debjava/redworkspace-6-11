package com.ddlab.rnd.service;

public class StartService 
{
	public static void main(String[] args) 
	{
		try 
		{
			ServiceWindow servWindow = new ServiceWindow();
			servWindow.open();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
