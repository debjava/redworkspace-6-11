package com.ddlab.tornado.core.xml.dtd.generator;

import java.io.File;

/**
 * @author Debadatta Mishra(PIKU)
 *
 */
public interface DTDGenerator 
{
	public String generateDTDfromXML( String xmlContents ) throws DtdGenerationException;
	
	public String generateDTDfromXML( File xmlFilePath ) throws DtdGenerationException;
}
