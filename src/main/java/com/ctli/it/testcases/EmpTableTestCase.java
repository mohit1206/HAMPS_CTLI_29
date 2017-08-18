package com.ctli.it.testcases;

import org.testng.annotations.Test;

import com.ctli.it.webPage.EmpTeblePage;

public class EmpTableTestCase extends TestNgInitialization{
	
	@Test
	public void EmpTable(){
		EmpTeblePage etPage = new EmpTeblePage(driver);
		etPage.getData();
	}

}
