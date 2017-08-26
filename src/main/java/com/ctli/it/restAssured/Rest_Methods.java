package com.ctli.it.restAssured;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;

public class Rest_Methods {
	
//Methods for get
	public void verifyGetResponse(){
		RestAssured.baseURI=WebServicesReusables.getURI();
		Response response = given().
			param("q", "turing").
//			param("radius","500").
//			param("type","restaurant").param("keyword", "cruise").param("key", "AIzaSyCm80-cwgG82AAtGzR5hTT1eafVN49AV_g").
	    when().
	    	get(WebServicesReusables.getResource());
			System.out.println(response.getStatusCode());
			Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	public void responseAsString(){
		RestAssured.baseURI=WebServicesReusables.getURI();
		Response response = given().
			param("q", "turing").
			param("radius","500").
			param("type","restaurant").param("keyword", "cruise").param("key", "AIzaSyCm80-cwgG82AAtGzR5hTT1eafVN49AV_g").
	    when().
	    	get(WebServicesReusables.getResource());
		
		String responseAsString  = response.asString();
		System.out.println(responseAsString);
	}
	
	public void get()
	{
		RestAssured.baseURI=WebServicesReusables.getURI();
		Response response = given().
			param("q", "turing").
//			param("radius","500").
//			param("type","restaurant").param("keyword", "cruise").param("key", "AIzaSyCm80-cwgG82AAtGzR5hTT1eafVN49AV_g").
	    when().
	    	get(WebServicesReusables.getResource());
		int statusCode = response.getStatusCode();
		System.out.println("status code: "+statusCode);		
		String responseValue =response.then().contentType(ContentType.JSON).extract().path("items[0].saleInfo.country");
		System.out.println("response value ="+responseValue);
	
  	}
	
	//post Methods
//	@Test
	public void post(){
		PostsBygetSet post = new PostsBygetSet();
		post.setId("1");
		post.setAuthor("Mohit");
		post.setTitle("Test");
		RestAssured.baseURI=WebServicesReusables.getURI();
		Response response = given().
		queryParam("key",WebServicesReusables.getKey()).
		when().contentType(ContentType.JSON).body(post).post(WebServicesReusables.PostResource());
		
		String responseValue = response.asString();
		System.out.println(responseValue);
	}
	
 
	public void postDelete()
	{
		RestAssured.baseURI=WebServicesReusables.getURI();
		String response = given().
				queryParam("key",WebServicesReusables.getKey()).
				body(PayLoad.placePostBody()).
		when().
				post(WebServicesReusables.PostResource()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
			.extract().response().asString();
		System.out.println(response);
			
			        JsonPath js=WebServicesReusables.StringtoJson(response);
					String placeid=js.get("place_id");
		given().
			queryParam("key",WebServicesReusables.getKey()).
			body(PayLoad.placeDeleteBody(placeid)).
		when().
			post(WebServicesReusables.DeleteResource()).
	    then().assertThat().statusCode(200);
		}
	
	//check with XML
		public void postDeleteXml() throws IOException
		{
			RestAssured.baseURI=WebServicesReusables.getURI();;
			String response=given().
				queryParam("key",WebServicesReusables.getKey()).
				body(WebServicesReusables.generateStringfromXML("C:\\TestAutothon\\TestAutothonFramework\\payload.xml")).
			when().
			    post("/maps/api/place/add/xml"). 
			    then().assertThat().statusCode(200).and().contentType(ContentType.XML).and()
				.extract().response().asString();
			System.out.println(response);
			   XmlPath x=WebServicesReusables.StringtoXML(response);
			  String placeid= x.get("PlaceAddResponse.place_id");
			  given().
				queryParam("key",WebServicesReusables.getKey()).
				body(WebServicesReusables.generateStringfromXML("C:\\TestAutothon\\TestAutothonFramework\\payloadDelete.xml")).
			  when().
			  	post("/maps/api/place/delete/xml").
			  	 then().assertThat().statusCode(200);
			   
		}
		
	//put or update
		public void putDelete()
		{
			RestAssured.baseURI=WebServicesReusables.getURI();;
			String response = given().
					queryParam("key",WebServicesReusables.getKey()).
					body(PayLoad.placePostBody()).
			when().
					put(WebServicesReusables.PostResource()).
			then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.extract().response().asString();
			System.out.println(response);
				
				        JsonPath js=WebServicesReusables.StringtoJson(response);
						String placeid=js.get("place_id");
			given().
				queryParam("key",WebServicesReusables.getKey()).
				body(PayLoad.placeDeleteBody(placeid)).
			when().
				post(WebServicesReusables.DeleteResource()).
		    then().assertThat().statusCode(200);
			}
		
		//patch
		public void patch(){
			RestAssured.baseURI=WebServicesReusables.getURI();
			Response response = given().
			queryParam("key",WebServicesReusables.getKey()).
			when().contentType(ContentType.JSON).body("{\"name\": \"Google Shoes!\"}").patch(WebServicesReusables.PostResource());
			
			String responseValue = response.asString();
			System.out.println(responseValue);
		}

}
