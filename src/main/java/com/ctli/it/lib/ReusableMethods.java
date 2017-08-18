package com.ctli.it.lib;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class ReusableMethods extends BaseClass{
	
	public ReusableMethods(WebDriver driver){
		super(driver);
	}

	  public void clickdate(String inputDate){
		    WebElement ele =driver.findElement(By.id("DepartDate"));
		    ele.click();
		    String month = driver.findElement(By.xpath("(//span[@class='ui-datepicker-month'])[1]")).getText();
		    String year = driver.findElement(By.xpath("(//span[@class='ui-datepicker-year'])[1]")).getText();
		    System.out.println("Application month : "+month + " Year :"+year);
		    int monthNum = getMonthNum(month);
		    System.out.println("Enum Num : "+monthNum);
		    String[] parts = inputDate.split("/");
		    int noOfHits = ((Integer.parseInt(parts[2])-Integer.parseInt(year))*12)+(Integer.parseInt(parts[1])-monthNum);
		    System.out.println("No OF Hits "+noOfHits);
		    for(int i=0; i< noOfHits;i++){
		    driver.findElement(By.xpath("//span[text()='Next']")).click();
		    System.out.println("clicked on next");
		    }
		    List<WebElement> cals=driver.findElements(By.xpath("(//table[@class='ui-datepicker-calendar'])[1]//tr"));
		    System.out.println("total row "+ cals.size());
		    for( WebElement daterow : cals){
		    List<WebElement> datenums = daterow.findElements(By.xpath("//div[@id='ui-datepicker-div']/div//td"));
		    /*iterating the "td" list*/
		    for(WebElement date : datenums ){
		    /* Checking The our input Date(if it match go inside and click*/
		    	if(date.getText().equalsIgnoreCase(parts[0])){
		    		date.click();
		    		System.out.println("pass");
		    		break;
		    	}
		    }
		    break;
		    }
		}

		public  int getMonthNum(String month){
		    for (Month mName : Month.values()) {
		    if(mName.name().equalsIgnoreCase(month))
		    return mName.value;
		    }
		    return -1;
		    }

		public enum Month{
		    January(1), February(2), March(3), April(4), May(5), June(6) , July(7), August(8), September(9), October(10), November(11),December(12);
		    private int value;

		    private Month(int value) {
		    this.value = value;
		    }

		    }


		
		//date picker
		public void datePickerForDrpdwn(WebElement dateBox,WebElement yearDrpdwn,WebElement monthDrpdwn, WebElement date, String inputDate){
			dateBox.click();
			System.out.println(inputDate);
			 String[] parts = inputDate.split("/");
			 String year = parts[2];
			 System.out.println("year =" +year);
			 int month = Integer.parseInt(parts[1]);
//			 yearDrpdwn.selectByVisibleText(year);
//			 monthDrpdwn.selectByIndex(month-1);
			 date.click();
		}
}
