package com.hubciti.common.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * The class for reading property values through PropertiesReader
 * 
 * @author Kumar D
 */
public class PropertiesReader
{
	/**
	 * Variable prop declared as Properties.
	 */
	private static Properties prop; // Properties
	/**
	 * Get logger instance.
	 */
	private static final Logger LOG = Logger.getLogger(PropertiesReader.class);

	/**
	 * Private constructor to avoid instantiation of this object.
	 */
	private PropertiesReader() { }

	/**
	 * This method takes the property name and returns the value of it in the
	 * properties file.
	 * 
	 * @param strPropertyName
	 *            The property name
	 * @return strPropertyValue The property value
	 */

public static synchronized String getPropertyValue(final String strPropertyName) {
		
		String strPropertyValue = "";
		InputStream inputStream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			
			if ( null == prop) {
				prop = new Properties();
				prop.load(classLoader.getResourceAsStream("message.properties"));
		     }
			
			strPropertyValue = (String) prop.get(strPropertyName);
			
		} catch (IOException ioe) {
			LOG.error(ioe.getMessage());
		} finally {
			if (inputStream != null) {
				
				try {
					inputStream.close();
				} catch (IOException e) {
					LOG.error("Exception:" + e.toString());
				}
			}
			
		}
		
		return strPropertyValue;
	}
	
}
