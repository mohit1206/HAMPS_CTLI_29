package com.ctli.it.lib;

import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class MobileBaseClass {
	public AndroidDriver androiddriver;
	static final long TIME_OUT = 60;

	public MobileBaseClass(AndroidDriver driver) {
		this.androiddriver = driver;
	}

	public void tap(WebElement element) {
		try {
			shouldBeVisible(element);
			TouchAction t = new TouchAction(androiddriver);
			t.tap(element).perform();
		} catch (Exception e) {
			e.printStackTrace();
			fail("unable to tap on mobile element");
		}
	}

	public void shouldBeVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(androiddriver, TIME_OUT);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
			fail("element is not visible");
		}
	}

	public void verticalScroll(String text ,WebElement element) {

		// AndroidElement list = (AndroidElement)
		// androiddriver.findElement(By.name("Coming Soon"));
		// MobileElement listGroup = list.findElement(MobileBy.AndroidUIAutomator(
		// "new UiScrollable(new UiSelector()).scrollIntoView(" + "new
		// UiSelector().text(\"Mom\"));"));
       for(int i=0;i<10;i++)
       {
    	   try
    	   {
    		   androiddriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	   if(element.isDisplayed())
    	   {
    		   break;
    	   }}
    	   catch(Exception e)
    	   {
    		   Dimension size = androiddriver.manage().window().getSize();
   			int y_start = (int) (size.height * 0.60);
   			int y_end = (int) (size.height * 0.20);
   			int x = size.width / 2;
   			androiddriver.swipe(x, y_start, x, y_end, 500);
    		   e.printStackTrace();
    	   }
    	 }
    	 
       
		

	}

	public void horizontalScroll() {
		try {
			Dimension size = androiddriver.manage().window().getSize();
			int x_start = (int) (size.width * 0.60);
			int x_end = (int) (size.width * 0.30);
			int y = 130;
			androiddriver.swipe(x_start, y, x_end, y, 4000);
		} catch (Exception e) {
			e.printStackTrace();
			fail("");
		}

	}

	public void seekBar(WebElement element) {
		try {
			// element should be the locator of seekbar
			shouldBeVisible(element);
			// get start co-ordinate of seekbar
			int start = element.getLocation().getX();
			// Get width of seekbar
			int end = element.getSize().getWidth();
			// get location of seekbar vertically
			int y = element.getLocation().getY();

			// Select till which position you want to move the seekbar
			TouchAction action = new TouchAction(androiddriver);

			// Move it will the end
			action.press(start, y).moveTo(end, y).release().perform();

			// Move it 40%
			int moveTo = (int) (end * 0.4);
			action.press(start, y).moveTo(moveTo, y).release().perform();

		} catch (Exception e) {
			e.printStackTrace();
			fail("");

		}
	}

	// to handle app permission (allow this app to access ur conatct)
	//
	public void allowAppPermission(String xpath) {
		while (androiddriver.findElements(MobileBy.xpath(xpath)).size() > 0)

		{
			androiddriver.findElement(MobileBy.xpath(xpath)).click();
		}

	}

	public void type(WebElement element, String text) {
		try {
			shouldBeVisible(element);
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
			fail("unable to enter text");
		}
		// androiddriver.

	}

	public Boolean isEnabled(WebElement element) {
		shouldBeVisible(element);
		return element.isEnabled();
	}

	public Boolean isDisplayed(WebElement element) {
		shouldBeVisible(element);
		return element.isDisplayed();
	}

	public void webScroll() {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) androiddriver;
			jse.executeScript("window.scrollBy(0,250)", "");
		} catch (Exception e) {
			e.printStackTrace();
			fail("unable to scroll the element");
		}
	}
	
	
	public void checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor)androiddriver;


		if (js.executeScript("return document.readyState").toString().equals("complete")){ 
			System.out.println("Page Is loaded.");
			return; 
		} 

		for (int i=0; i<25; i++){ 
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {} 
			if (js.executeScript("return document.readyState").toString().equals("complete")){ 
				break; 
			}   
		}
	}
	
	public void scrollToWebElement(WebElement element){
		//WebElement element = driver.findElement(locator);
		try {
			((JavascriptExecutor) androiddriver).executeScript("arguments[0].scrollIntoView();", element);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean slider(WebElement elemnt, int x, int y) throws Throwable {
		boolean status = false;
		try {
			waitTillElementToBeClickble(elemnt);
			Actions moveElement = new Actions(androiddriver);
			new Actions(androiddriver).clickAndHold(elemnt).moveByOffset(x, y).release().perform();
			moveElement.build().perform();
			status = true;
			System.out.println("Slider moved");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return status;
	}

	
	public void waitTillElementToBeClickble(WebElement ele) throws Throwable {
		WebDriverWait wait = new WebDriverWait(androiddriver, TIME_OUT);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			
		} catch (Exception e) {
			System.out.println("element is not clickable");
			e.printStackTrace();
		} 
		}
	
	
	public void dragAndDrop(WebElement sourceLocator, WebElement destinationLocator, String locatorName) throws Throwable {
		try {

			Actions builder = new Actions(this.androiddriver);
			org.openqa.selenium.interactions.Action dragAndDrop = builder.clickAndHold(sourceLocator)
					.moveToElement(destinationLocator).release(destinationLocator).build();
			dragAndDrop.perform();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}
	
	public void avoidStaleElement(String xpath) throws InterruptedException
	{
		Thread.sleep(5000);
		int count=0;
		while (count < 4) {
			   try {
			    //If exception generated that means It Is not able to find element then catch block will handle It.
			    WebElement staledElement = androiddriver.findElement(By.xpath(xpath));
			    //If exception not generated that means element found and element text get cleared.
			    staledElement.click();    
			   } catch (StaleElementReferenceException e) {
			    e.toString();
			    System.out.println("Trying to recover from a stale element :" + e.getMessage());
			    count = count + 1;
			   }
			   count = count + 4;
		
		}}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
