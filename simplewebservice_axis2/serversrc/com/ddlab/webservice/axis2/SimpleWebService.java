package com.ddlab.webservice.axis2;

public class SimpleWebService 
{
	public String getName( String name )
	{
		System.out.println("Name from webservice client--->"+name);
		return "Name from server "+name;
	}
}
