package com.ddlab.tornado.core.xml.dtd.generator;

public class DtdGenerationException extends Exception 
{
	private static final long serialVersionUID = 3275172752794731413L;
	private String exceptionMessage;
	
	public DtdGenerationException() 
	{
		super();
		this.exceptionMessage = "Exception in generating DTD from XML.";
	}
	
	public DtdGenerationException( String message ) 
	{
		super();
		this.exceptionMessage = message;
	}
	
	@Override
	public String getMessage()
	{
		return exceptionMessage;
	}
	 
}
