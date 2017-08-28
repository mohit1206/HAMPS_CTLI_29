package com.ctli.it.webpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctli.it.lib.BaseClass;
import com.ctli.it.lib.ReusableMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class NaukiriTestcase2Page extends BaseClass{

	public NaukiriTestcase2Page(WebDriver driver, ExtentTest testReport) {
		super(driver, testReport);
		PageFactory.initElements(driver, this);
	}
	
/*	.//*[@id='hpCarousel']//div[@class='registSec']//div[@id='uploadBtnCont']/input[@class='plainBtn']
*/	
	@FindBy(xpath=".//*[@value='Upload CV']")
	public WebElement lbl_upload;
	
	@FindBy(xpath=".//*[text()='Register']")
	public WebElement btn_register;
	
	@FindBy(xpath=".//*[@id='geoLocPopUp']//.[text()='Later']")
	public WebElement btn_popup_ltr;
	
	public void Upload() {
		ReusableMethods rm=new ReusableMethods(driver, testReport);
		
		waitForPageToLoad();
		
		click(lbl_upload);
		System.out.println(System.getProperty("user.dir")+"\\Resources\\DummyCV.docx");
		rm.Uploadfile("File Upload", "Edit1", "DummyCV.docx", "Button1");
		//rm.Uploadfile("Choose File to Upload", "Edit1", "DummyCV.docx", "Button1");
		//rm.Uploadfile2("Choose File to Upload", "ToolbarWindow32","Edit1", "DummyCV.docx", "Button1");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isVisible(btn_register);
	rm.Takescreenshot();
	}
	


}
