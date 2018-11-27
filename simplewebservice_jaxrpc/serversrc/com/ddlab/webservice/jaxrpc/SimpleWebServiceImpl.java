package com.ddlab.webservice.jaxrpc;

import java.rmi.RemoteException;

public class SimpleWebServiceImpl implements SimpleWebService
{
	public String getName( String name ) throws RemoteException
	{
		System.out.println("Name from webservice client--->"+name);
		return "Name from server "+name;
	}
}
