package com.ctli.it.restAssured;

public class PayLoad {
	
	public static String placePostBody()
	{
		String payload="{"+
				  "\"location\": {"+
				    "\"lat\": -33.8669710,"+
				    "\"lng\": 151.1958750"+
				  "},"+
				  "\"accuracy\": 50,"+
				  "\"name\": \"Google Shoes!\","+
				  "\"phone_number\": \"(02) 9374 4000\","+
				  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
				  "\"types\": [\"shoe_store\"],"+
				  "\"website\": \"http://www.google.com.au/\","+
				  "\"language\": \"en-AU\""+
				"}";
		
		
		return payload;
	}
	public static String placeDeleteBody(String placeid)
	{
		String payload="{"+
	    		  " \"place_id\": \""+placeid+"\""+
	    		  "}";
		return payload;
	}

}
