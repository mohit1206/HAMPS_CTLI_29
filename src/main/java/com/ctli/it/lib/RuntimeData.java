package com.ctli.it.lib;

import java.util.HashMap;
import java.util.Map;



public class RuntimeData {
	static Map<String, String> map = new HashMap<String, String>();
		
	static void storeRuntimeData(String runtimeDataName, String runtimeDataValue){
		map.put(runtimeDataName, runtimeDataValue);
		System.out.println("Runtime date for the variable \""+runtimeDataName+"\"is saved as ::"+runtimeDataValue);
	}
	
	static String getRuntimeData(String runtimeDataName){
		System.out.println("Runtime date for the variable \""+runtimeDataName+"\"fetched as ::"+map.get(runtimeDataName));
		return map.get(runtimeDataName);
	}	
	
}
