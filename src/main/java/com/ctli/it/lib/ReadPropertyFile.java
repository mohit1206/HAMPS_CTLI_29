package com.ctli.it.lib;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertyFile {
public static String getPropertyValue(String key)
	
	{
	  String filePath="./Resources\\config.properties";
	
		String value="";
		Properties ppt = new Properties();
		try{
		ppt.load(new FileInputStream(filePath));
		value= ppt.getProperty(key);
		}
		catch(Exception e)
		{}
		return value;
	}




}
