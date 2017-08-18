package com.ctli.it.lib;

import java.util.HashMap;
import java.util.Properties;

public interface ExcelDriver {
	
	 HashMap<String, String> getRunData();

	  void setRunData(HashMap<String, String> runData);
		
	  void addAValueToRunData(String key, String value);
			
	  String getRunDataValue(String key);	
}
