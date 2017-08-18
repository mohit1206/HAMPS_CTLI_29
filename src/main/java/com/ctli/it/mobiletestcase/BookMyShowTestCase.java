package com.ctli.it.mobiletestcase;

import org.testng.annotations.Test;

import com.ctli.it.lib.BaseClass;
import com.ctli.it.mobilepage.BookMyShowPage;
import com.ctli.it.testcases.TestNgInitialization;

import io.appium.java_client.android.AndroidDriver;

public class BookMyShowTestCase extends TestNgInitialization {
	@Test
	public void bookMyShow()
	{
	BookMyShowPage bm=new BookMyShowPage(mobiledriver);
	bm.completeScenario();
	
	
	}

}
