package com.ctli.it.testcases;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlTest;

import com.ctli.it.lib.AppiumServerStarting;
import com.ctli.it.lib.AutomationConstants;
import com.ctli.it.lib.ExcelDriver;
import com.ctli.it.lib.ReadPropertyFile;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;

public class TestNgInitialization implements AutomationConstants {
	public ExtentTest testReport;
	public WebDriver driver;
	public AndroidDriver mobiledriver;
	 public static ExtentReports extent;
	 private HashMap<String, String> runData;

	
	@BeforeSuite
	public void initialSetUp()
	{
		 String extentConfigFile=System.getProperty("user.dir")+"\\Resources\\Config.xml";
		  extent=new ExtentReports(reportFilePath, true);
		  extent.loadConfig(new File(extentConfigFile));  
	}

	@BeforeMethod
	public void launchApp(Method method, XmlTest test) throws Exception {
		
		testReport = extent.startTest((this.getClass().getSimpleName() + "::"  +method.getName()),method.getName()); 
		String browser = test.getParameter("browser");
		System.out.println(browser);
		if (browser.equalsIgnoreCase("GC")) {
			String key = "webdriver.chrome.driver";
			String value = "./Resources/chromedriver.exe";
			System.setProperty(key, value);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(ReadPropertyFile.getPropertyValue("URL"));

		} else if (browser.equalsIgnoreCase("IE")) {
			String Key = "webdriver.ie.driver";
			String value = "./Resources/IEDriverServer.exe";
			System.setProperty(Key, value);
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(ReadPropertyFile.getPropertyValue("URL"));

		} else if (browser.equalsIgnoreCase("FF")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(ReadPropertyFile.getPropertyValue("URL"));

			//driver.get("https://ctl--itv1.cs70.my.salesforce.com/");
		}

		else if (browser.equalsIgnoreCase("appium")) {
			testReport = eReport.startTest(browser+"_"+method.getName());
			if (ReadPropertyFile.getPropertyValue("APPTYPE").equals("WebApp")) {
				String filepath=System.getProperty("user.dir")+"\\Resources\\chromedriver.exe";
				 System.out.println("web app");
				AppiumServerStarting appiumserverStart = new AppiumServerStarting();
				appiumserverStart.appiumStart();
                 
				DesiredCapabilities ds = new DesiredCapabilities();
				ds.setCapability("deviceName", ReadPropertyFile.getPropertyValue("DEVICENAME"));
				//ds.setCapability(CapabilityType.BROWSER_NAME, "Android");
				ds.setCapability("browserName", "Chrome");
				ds.setCapability("chromedriverExecutable", filepath);
				ds.setCapability(CapabilityType.VERSION,  ReadPropertyFile.getPropertyValue("ANDROIDVERSION"));
				ds.setCapability("platformName", "Android");
				mobiledriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), ds);
				mobiledriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				mobiledriver.get(ReadPropertyFile.getPropertyValue("APPURL"));
			}

			else {
				AppiumServerStarting appiumserverStart = new AppiumServerStarting();
				appiumserverStart.appiumStart();
				DesiredCapabilities ds = new DesiredCapabilities();
				ds.setCapability("deviceName", ReadPropertyFile.getPropertyValue("DEVICENAME"));
				ds.setCapability(CapabilityType.BROWSER_NAME, "Android");
				ds.setCapability(CapabilityType.VERSION, ReadPropertyFile.getPropertyValue("ANDROIDVERSION"));
				ds.setCapability("platformName", "Android");
				ds.setCapability("appPackage", ReadPropertyFile.getPropertyValue("APPPACKAGE"));
				ds.setCapability("appActivity", ReadPropertyFile.getPropertyValue("APPACTIVITY"));
				mobiledriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), ds);
				mobiledriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			}
		}

		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// testReport = eReport.startTest("firefox"+"_"+method.getName());
		// driver=new FirefoxDriver();
		// driver.get("https://www.google.co.in/?gfe_rd=cr&ei=MLWOWbnpEYLz8AeBnLPoDA");
		// driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		

	}
	@AfterMethod
	public void closeApp(Method method, XmlTest test)
	{
		
           String browser = test.getParameter("browser");
		  
		  if(browser.equalsIgnoreCase("ff")||browser.equalsIgnoreCase("gc")||browser.equalsIgnoreCase("ie"))
		  {
			  driver.close();
		  }
		  else if (browser.equalsIgnoreCase("appium")) {
			mobiledriver.closeApp();
		}
		
	}
	
	
	  @AfterSuite
	  public void afterSuite( XmlTest test){
		
	  extent.flush();



	  }
	 

	}
