package com.ctli.it.webPage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctli.it.lib.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;

public class SfaPage extends BaseClass {

	public SfaPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public SfaPage(WebDriver driver, ExtentTest testReport) {
		super(driver,testReport);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="(//span[text()='CenturyLink SSO'])[1]")
	public WebElement lbl_sso;
	@FindBy(name="user")
	public WebElement tbx_username;
	@FindBy(name="password")
	public WebElement tbx_password;
	
	@FindBy(xpath="//input[@value=\"Login\"]")
	public WebElement btn_login;
	
	@FindBy(id="phSearchButton")
	public WebElement btn_search;
	@FindBy(xpath="//a[text()='Edit']/../..//th//a")
	public WebElement lnk_order;
	
	@FindBy(xpath="//img[@alt=\"Configure Products\"]")
	public WebElement btn_configure;
	@FindBy(xpath="//div[contains(text(),'All Categories')]/..//div[2]//input")
	public WebElement tbx_findproduct;
	
	 
	 
	  
	  @FindBy(id="phSearchInput")
	  public WebElement tbx_search;
	  
	  @FindBy(xpath="//a[text()='Fiber+ Enterprise']/../../../..//span[text()='Configure...']")
	  public WebElement btn_fiber;
	
	public void click_sso()
	{
		try {
			lbl_sso.click();
			getPageScreenShot();
		}
		catch(Exception e)
		{
			driver.findElement(By.xpath("(//span[text()='CenturyLink SSO'])[2]")).click();
			getPageScreenShot();
		}
		
		
	}
	public void enterDetails() throws InterruptedException
	{
		
		type(tbx_username, "SFATST15");
		type(tbx_password, "Yellow25");
		click(btn_login);
		Thread.sleep(5000);
	}
	public void clickLink() throws InterruptedException
	{
		type(tbx_search, "Q-00162581 ");
		Thread.sleep(5000);
		tbx_search.sendKeys(Keys.ENTER);
		//jsClick(btn_search);
		//jsclick(btn_search);
		click(lnk_order);
	}
 public void click_configure()
 {
	 click( btn_configure);
	 type(tbx_findproduct, "Fiber+ Enterprise");
	 tbx_findproduct.sendKeys(Keys.ENTER);
	 jsClick(btn_fiber);
	 
 }
 
 public void clickDyanaically(String text)
 {
	    String xp="//a[text()='"+text+"']/../../../..//span[text()='Configure...']";
	    WebElement ele=driver.findElement(By.xpath(xp));
	    jsClick(ele);
 }
	
	
}
