package com.ctli.it.mobilepage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.ctli.it.lib.DataTable;
import com.ctli.it.lib.MobileBaseClass;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NaukriJobDescriptionPage extends MobileBaseClass {

	public NaukriJobDescriptionPage(AndroidDriver androiddriver, ExtentTest testReport) {
		super(androiddriver, testReport);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(androiddriver), this);
	}
	
//	@FindBy(xpath="")
//	public webelement 
	
	
	public void storeDataIntoExcel()
	{
		List<WebElement> list = androiddriver.findElements(By.xpath("//div[@class=\"externalHTML section\"]"));
		System.out.println(list.size());
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i).getText());
//		      DataTable dt=new DataTable("./Resources/FeatureInput.xlsx");
//		      dt.setCellDataByColNum("sheet2", i, 1,list.get(i).getText().toString());
			testReport.log(LogStatus.PASS, "Write The Data into Excel");
		}
		
		
	}
	
	

}
