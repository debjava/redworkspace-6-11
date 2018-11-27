package com.iit.rnd.server.submit;

public class Test1 
{

	public static void main(String[] args)
	{
//		String ss = "A";
//		String unicode = UnicodeConverter.toEscapedUnicode(ss);
//		System.out.println("Unicode Value :::"+unicode);
		
		System.out.println(UnicodeConverter.fromEscapedUnicode("\u0063"));
		
	}

}
