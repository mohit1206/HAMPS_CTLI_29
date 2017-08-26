package com.ctli.it.testcases;

import org.testng.annotations.Test;

import com.ctli.it.restAssured.Rest_Methods;


public class RestService {
		@Test
		public void getResponse(){
			Rest_Methods rest =  new Rest_Methods();
			rest.get();
		}
}
