package com.ctli.it.mobilepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctli.it.lib.MobileBaseClass;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NaukriHomePage extends MobileBaseClass{

	public NaukriHomePage(AndroidDriver androiddriver, ExtentTest testReport) {
		super(androiddriver, testReport);
		PageFactory.initElements(new AppiumFieldDecorator(androiddriver), this);
	}
	
	@FindBy(id="searchJob")
	public WebElement tbx_searchJob;
	
	

}
