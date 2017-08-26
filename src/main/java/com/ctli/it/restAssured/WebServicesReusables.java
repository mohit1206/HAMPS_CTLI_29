package com.ctli.it.restAssured;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.ctli.it.lib.ReadPropertyFile;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;

public class WebServicesReusables {
	
	public static String getURI()
	{
	return ReadPropertyFile.getPropertyValue("ServiceURI");
	}
   public static String getResource()
   {
	   return ReadPropertyFile.getPropertyValue("Resources");
   }
   public static String PostResource()
   {
	   return ReadPropertyFile.getPropertyValue("PostResources");
   }
   public static String DeleteResource()
   {
	   return ReadPropertyFile.getPropertyValue("DeleteResources");
   }
   public static String getKey()
   {
	   return ReadPropertyFile.getPropertyValue("Key");
   }
   
   public static String getParamName1()
   {
	   return ReadPropertyFile.getPropertyValue("FirstParameterName");
   }
   
   public static String getParamValue1()
   {
	   return ReadPropertyFile.getPropertyValue("FirstParameterValue");
   }
   
   public static String getParamName2()
   {
	   return ReadPropertyFile.getPropertyValue("SecondParameterName");
   }
   
   public static String getParamValue2()
   {
	   return ReadPropertyFile.getPropertyValue("SecondParameterValue");
   }
   
   public static String generateStringfromXML(String path) throws IOException
   {
 	  return new String(Files.readAllBytes(Paths.get(path)));
   }
   public static JsonPath StringtoJson(String result){

 	return new JsonPath(result);
	   
   }
   public static XmlPath StringtoXML(String result)
   {
	   XmlPath x=new XmlPath(result);
	   return x;
	   
   }
}
