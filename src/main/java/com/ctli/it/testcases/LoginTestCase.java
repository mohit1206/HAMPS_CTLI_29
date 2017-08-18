package com.ctli.it.testcases;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ctli.it.webPage.LoginPage;
import com.ctli.it.webPage.SfaPage;
import com.relevantcodes.extentreports.LogStatus;


public class LoginTestCase  extends TestNgInitialization{
	
	@Test
	public void testcase() throws InterruptedException
	{
//		LoginPage lp= new LoginPage(driver);
		//LoginPage lp= new LoginPage(driver,testReport);
//		Reporter.log("click on Gmail Button", true);
//		lp.demo();
//		testReport.log(LogStatus.PASS, "Browser Launched Successfully"); 
		SfaPage sf=new SfaPage(driver,testReport);
		//testReport.log(LogStatus.FAIL,"login with valid credential");
		//SfaPage sf=new SfaPage(driver);
		sf.click_sso();
		//testReport.log(LogStatus.PASS,"login with valid credential");
//		sf.enterDetails();
//		sf.clickLink();
//		sf.click_configure();
		
		
	}

}
