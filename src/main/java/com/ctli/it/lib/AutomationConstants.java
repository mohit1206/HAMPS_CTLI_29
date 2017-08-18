package com.ctli.it.lib;

import com.relevantcodes.extentreports.ExtentReports;

public interface AutomationConstants {
	
	public static final String imageFolderPath="./screenShots";
	public static final String reportFilePath="./Report/results.html";
	public static final String configPptPath="./Config/config.properties";
	//public static final String scenariosPath="./Scripts/Scenarios.xlsx";
	//public static final String controllerPath="./Scripts/Controller.xlsx";
	public static final String chromeDriverPath="./driver/chromedriver.exe";
	//public static final String suiteSheet="Suite";
	public static  final String  ActualImageFolder="./ActualImage";
	public static  final String reportFolderPath="./Report/";
	public static  final String  ExpectedImageFolder="./ExpectedImage";
	public static  final String  CsvFolderPath="./csv";
	public static final String inputFilePath = "./data/Input.xlsx";
	public static  final ExtentReports eReport=new ExtentReports(reportFilePath);
	

}
