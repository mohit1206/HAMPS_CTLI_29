package com.ctli.it.testcases;

import org.testng.annotations.Test;

import com.ctli.it.lib.TestNgInitialization;
import com.ctli.it.webpage.NaukiriTestcase2Page;

public class NaukiriTestCase2 extends TestNgInitialization{
	
	@Test
	public void CVUpload(){
		NaukiriTestcase2Page NT2 = new NaukiriTestcase2Page(driver, testReport);
		NT2.Upload();
	}


}
