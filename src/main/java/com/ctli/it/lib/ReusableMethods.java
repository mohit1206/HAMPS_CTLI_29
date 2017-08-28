package com.ctli.it.lib;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;



public class ReusableMethods extends BaseClass{
	
	public ReusableMethods(WebDriver driver, ExtentTest testReport){
		super(driver, testReport);
	}

	  public void clickdate(String inputDate){
		    WebElement ele =driver.findElement(By.id("DepartDate"));
		    ele.click();
		    WebElement month = driver.findElement(By.xpath("(//span[@class='ui-datepicker-month'])[1]"));
		    WebElement year = driver.findElement(By.xpath("(//span[@class='ui-datepicker-year'])[1]"));
		    String monthName = month.getText();
		    String yearName = year.getText();
		    System.out.println("Application month : "+monthName + " Year :"+yearName);
		    int monthNum = getMonthNum(monthName);
		    System.out.println("Enum Num : "+monthNum);
		    String[] parts = inputDate.split("/");
		    int noOfHits = ((Integer.parseInt(parts[2])-Integer.parseInt(yearName))*12)+(Integer.parseInt(parts[1])-monthNum);
		    System.out.println("No OF Hits "+noOfHits);
		    for(int i=0; i< noOfHits;i++){
		    driver.findElement(By.xpath("//span[text()='Next']")).click();
		    System.out.println("clicked on next");
		    }
		    //select row of first month
		    List<WebElement> cals=driver.findElements(By.xpath("(//table[@class='ui-datepicker-calendar'])[1]//tr"));
		    System.out.println("total row "+ cals.size());
		    for( WebElement daterow : cals){
		    	//select cells of both months
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
		
		//#########################   PDF Read Method  ############################################//
		public String ReadPdf(String pdfpath) throws InvalidPasswordException, IOException {
		String value=null;
		try (PDDocument document = PDDocument.load(new File(pdfpath))) {
		document.getClass();

		           if (!document.isEncrypted()) {

		               PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		               stripper.setSortByPosition(true);

		               PDFTextStripper tStripper = new PDFTextStripper();

		               String pdfFileInText = tStripper.getText(document);
		               //System.out.println("Text:" + st);

		// split by whitespace
		               String lines[] = pdfFileInText.split("\\r?\\n");
		               for (String line : lines) {
		                   System.out.println(line);
		                   value+=line;
		               }
		               
		               

		           }

		       }
		return value;
		 
		}

		
}
