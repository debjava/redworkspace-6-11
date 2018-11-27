package com.ddlab.rnd.service;

public class StartService 
{
	public static void main(String[] args) 
	{
		try 
		{
			SplashFrame1 frame = new SplashFrame1();
			frame.setVisible(true);
			Thread.sleep(5000);
			frame.setVisible(false);
			frame.dispose();
			ServiceWindow servWindow = new ServiceWindow();
			servWindow.open();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
