package com.ctli.it.webPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctli.it.lib.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;

public class LoginPage extends BaseClass {
	
	@FindBy(xpath="//a[text()='Gmail']")
	public WebElement btn_Gmail;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		
	}
	   
	
	public LoginPage(WebDriver driver, ExtentTest testReport) {
		super(driver,testReport);
		PageFactory.initElements(driver, this);
		
	}
	public void demo()
	{
	click(btn_Gmail);
	System.out.println("finished");
	
	
	}

}
