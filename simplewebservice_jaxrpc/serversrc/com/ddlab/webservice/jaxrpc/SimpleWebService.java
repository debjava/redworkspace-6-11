package com.ddlab.webservice.jaxrpc;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SimpleWebService extends Remote
{
	public String getName( String name ) throws RemoteException;
}
