package com.ctli.it.webPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ctli.it.lib.BaseClass;
import com.ctli.it.lib.DataTable;
import com.relevantcodes.extentreports.ExtentTest;


public class NaukriHomePage extends BaseClass{

	public NaukriHomePage(WebDriver driver, ExtentTest testReport) {
		super(driver, testReport);
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath="//img[@alt='Naukri.com, India’s No.1 Job Site']")
	public WebElement img_Naukri;
	
	@FindBy(xpath="//input[@placeholder='Skills, Designations, Companies']")
	public WebElement tbx_SearchJobs;
	
	@FindBy(xpath="//input[@placeholder='Location']")
	public WebElement tbx_Location;
	
	@FindBy(xpath="//input[@placeholder='Experience']")
	public WebElement ddl_Experiece;
	
	@FindBy(xpath="//input[@placeholder='Salary']")
	public WebElement ddl_Salary;
	
	@FindBy(id="qsbFormBtn")
	public WebElement btn_Search;
	
	@FindBy(xpath="//a[contains(@title,'IT-Software / Software Services')]")
	public WebElement rdb_ITSoftware;
	
	@FindBy(xpath="//h3[contains(text(),'Education')]")
	public WebElement btn_education;
	
	@FindBy(xpath="(//span[text()='more'])[1]")
	public WebElement btn_more;
	
	@FindBy(xpath="//h3[contains(text(),'Industry')]")
	public WebElement btn_Industry;
	
	@FindBy(xpath="//span[contains(text(),'Job Views')]/strong")
	public WebElement lbl_jobView;
	
	@FindBy(xpath="//span[text()='Search']")
	public WebElement btn_searchForFillDetails;
	
	@FindBy(id="block")
	public WebElement popup_close;
	
	@FindBy(xpath="//span[contains(text(),'Job Applicants')]/strong")
	public WebElement lbl_jobApplicant;
	
	@FindBy(xpath="//h4[text()='Desired Candidate Profile']/following-sibling::ul")
	public WebElement lbl_desiredProfile;
	
	@FindBy(xpath="//h2[text()='Freshness']/following-sibling::div//input[1]")
	public WebElement ddl_freshness;
	
	@FindBy(xpath="//li[text()='Last 30 Days']")
	public WebElement lbl_Last30Days;
	
	public void verifyHomePage(){
		boolean naukriImgVisible = isVisible(img_Naukri);
		Assert.assertTrue(naukriImgVisible);
	}
	
	public void searchJobs() throws InterruptedException{
		DataTable dTable = new DataTable("C:\\Users\\AB49014\\git\\HAMPS_26Aug\\Resources\\FeatureInput.xlsx");
		String search = dTable.getCellData("TEST", "Search", 2);
		String location = dTable.getCellData("TEST", "Location", 2);
		String experience = dTable.getCellData("TEST", "Yrs of exp", 2);
		String salary = dTable.getCellData("TEST", "Salary", 2);
		
		click(btn_searchForFillDetails);
		tbx_SearchJobs.sendKeys(search);
		type(tbx_Location, location);
		click(ddl_Experiece);
		driver.findElement(By.xpath("(//li[text()='"+experience+"'])[1]")).click();
		click(ddl_Salary);
		driver.findElement(By.xpath("(//li[text()='"+salary+"'])[2]")).click();
		click(btn_Search);
	}
	
	public void filterCritaria(){
		DataTable dTable = new DataTable("C:\\Users\\AB49014\\git\\HAMPS_26Aug\\Resources\\FeatureInput.xlsx");
		String industry = dTable.getCellData("TEST", "Industry", 2);
		String[] educations = dTable.getCellData("TEST", "Education", 2).split(",");
		
		String btech = educations[0];
		String mtech = educations[1];
		String pg = educations[2];
		
		if(getAttribute(ddl_freshness, "placeholder").contains("Last 30 Days")){
			System.out.println("move next step");
		}else{
			click(ddl_freshness);
			click(lbl_Last30Days);
		}
		click(btn_Industry);
		click(driver.findElement(By.xpath("//a[contains(@title,'"+industry+"')]")));
		
		click(btn_education);
		if(driver.findElement(By.xpath("(//a[contains(text(),'"+btech+"')])[1]")).isDisplayed()){
			click(driver.findElement(By.xpath("(//a[contains(text(),'"+btech+"')])[1]")));
		}else{
			click(btn_more);
			click(driver.findElement(By.xpath("(//a[contains(text(),'"+btech+"')])[1]")));
		}
		try{
		if(driver.findElement(By.xpath("(//a[contains(text(),'"+mtech+"')])[1]")).isDisplayed()){
			click(driver.findElement(By.xpath("(//a[contains(text(),'"+mtech+"')])[1]")));
		}else{
			System.out.println("else for mtech");
			click(btn_more);
			click(driver.findElement(By.xpath("(//a[contains(text(),'"+mtech+"')])[1]")));
		}}catch (Exception e) {
			System.out.println("catch for mtech");
			jsClick(btn_more);
			click(driver.findElement(By.xpath("(//a[contains(text(),'"+mtech+"')])[1]")));
			}
		
		try{
		if(driver.findElement(By.xpath("(//a[contains(text(),'"+pg+"')])[1]")).isDisplayed()){
			click(driver.findElement(By.xpath("(//a[contains(text(),'"+pg+"')])[1]")));
		}else{
			System.out.println("else for pg");
			click(btn_more);
			click(driver.findElement(By.xpath("(//a[contains(text(),'"+pg+"')])[1]")));
		}}catch (Exception e) {
			System.out.println("catch for pg");
			click(btn_more);
			click(driver.findElement(By.xpath("(//a[contains(text(),'"+pg+"')])[1]")));
			}
		
		
	}
	
	public void jobDetails(){
		List<WebElement> jobsBySwatiGupta =getElements(By.xpath("//a[contains(text(),'Swati Gupta')]"));
		for(int i =0; i<jobsBySwatiGupta.size(); i++){
			if(i==1){
			driver.findElement(By.xpath("(//div[div[a[contains(text(),'Swati Gupta')]]]/preceding-sibling::a//li)[2]")).click();	
			break;
			}
		}
		switchToChildWindow();
		String jobViews = getText(lbl_jobView);
		String jobApplicants = getText(lbl_jobApplicant);
		String desiredCandidate = getText(lbl_desiredProfile);
		DataTable dTable = new DataTable("C:\\Users\\AB49014\\git\\HAMPS_26Aug\\Resources\\FeatureInput.xlsx");
		dTable.setCellData("TEST", "Job View", 2, jobViews);
		dTable.setCellData("TEST", "Job Applicant", 2, jobApplicants);
		dTable.setCellData("TEST", "Desired Candidate", 2, desiredCandidate);
	}
}
