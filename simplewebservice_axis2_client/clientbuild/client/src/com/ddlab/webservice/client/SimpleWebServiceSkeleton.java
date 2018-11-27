
/**
 * SimpleWebServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
package com.ddlab.webservice.client;

import simplewebservice.service.xsd.GetNameResponse;

/**
 *  SimpleWebServiceSkeleton java skeleton for the axisService
 */
public class SimpleWebServiceSkeleton{


	/**
	 * Auto generated method signature
	 * 
	 * @param getName
	 */

	public simplewebservice.service.xsd.GetNameResponse getName(simplewebservice.service.xsd.GetName getName)
	{
		//TODO : fill this with the necessary business logic
		GetNameResponse getNameRes = null;
		try 
		{
			getNameRes = new GetNameResponse();
			getNameRes.set_return("Hello World");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getName");
		}
		return getNameRes;
	}

}
