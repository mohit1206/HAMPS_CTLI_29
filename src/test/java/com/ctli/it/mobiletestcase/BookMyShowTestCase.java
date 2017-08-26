package com.ctli.it.mobiletestcase;

import org.testng.annotations.Test;

import com.ctli.it.lib.TestNgInitialization;
import com.ctli.it.mobilepage.BookMyShowPage;

public class BookMyShowTestCase extends TestNgInitialization {
	@Test
	public void bookMyShow()
	{
	BookMyShowPage bm=new BookMyShowPage(mobiledriver, testReport);
	bm.completeScenario();
	
	
	}

}
