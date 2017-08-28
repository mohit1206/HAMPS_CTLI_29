package com.ctli.it.testcases;

import org.testng.annotations.Test;

import com.ctli.it.restassured.RestMethods;


public class RestService {
		@Test
		public void getResponse(){
			RestMethods rest =  new RestMethods();
			rest.get();
		}
}
