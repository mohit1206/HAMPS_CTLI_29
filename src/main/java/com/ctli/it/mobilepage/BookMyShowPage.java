package com.ctli.it.mobilepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ctli.it.lib.BaseClass;
import com.ctli.it.lib.MobileBaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BookMyShowPage extends MobileBaseClass {

	public BookMyShowPage(AndroidDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	@FindBy(name="Coming Soon")
	public WebElement btn_ComingSoon;
	@FindBy(name="Vekh Baraatan Challiyan")
	public WebElement link_vekhshow;
	@FindBy(name="Share")
	public WebElement btn_share;
	@FindBy(name="Filters")
	public WebElement btn_Filter;
	@FindBy(name="English")
	public WebElement btn_english;
	@FindBy(name="Hindi")
	public WebElement btn_Hindi;
	@FindBy(name="Apply")
	public WebElement btn_Apply;
	@FindBy(id="com.bt.bms:id/search_src_text")
	public WebElement tbx_search;
	@FindBy(xpath="//android.widget.TextView[@text='Berlin Syndrome']")
	public WebElement btn_berlinsyndrome;
	@FindBy(name="Mom")
	public WebElement txt_mom;
	
	@FindBy(name="Now Showing")
	public WebElement lbl_nowShowing;
	
	@FindBy(xpath="//android.widget.TextView[@text='Mom']/../android.widget.TextView[@text='BOOK']")
	public WebElement btn_Book;
	
	@FindBy(name="Filters")
	public WebElement btn_Filter2;
	
	
	public void completeScenario()
	{
		WebDriverWait wt=new WebDriverWait(androiddriver, 60);
		wt.until(ExpectedConditions.visibilityOf(lbl_nowShowing));
	      // tap(btn_ComingSoon);
		verticalScroll("some",txt_mom);
		wt.until(ExpectedConditions.visibilityOf(btn_Book));
		System.out.println("get page sources"+androiddriver.getPageSource());
		btn_Book.click();
		wt.until(ExpectedConditions.visibilityOf(btn_Filter2));
		btn_Filter2.click();
		
	}
	

}
