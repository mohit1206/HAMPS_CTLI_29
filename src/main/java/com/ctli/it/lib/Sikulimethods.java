package com.ctli.it.lib;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;

import org.sikuli.script.Screen;
import org.sikuli.script.SikuliException;

public class Sikulimethods {
	
	
	//###########################  Sikuli Methods Below ########################################//
	
	private static Screen getScreenObject(){
		Screen screen = null;
		if(screen==null){
			screen = new Screen();
		}
		return screen;				
	}

	private static Pattern getPatternObject(String imageName) {	
		Pattern pattern = null;
	/*	File file = new File(".\\Resources\\Screenshots\\"+imageName+".PNG");
		if(file.exists()){*/
		String imagepath=System.getProperty("user.dir")+"\\Resources\\Screenshots\\"+imageName+".PNG";
			pattern = new Pattern(imagepath);
			return pattern;
			/*}else{
			//throw new Error("FATAL--Image with name \""+imageName+"\" is not found.");
			throw new Error("FATAL--Image with name \""+imageName+"\" is not found.");
		}		*/
	}
	
	protected static void clickUsingSK(String imageName)  {
		try{
			getScreenObject().click(getPatternObject(imageName));
		
			System.out.println("INFO--Clicked on the image \""+imageName+"\" using sikuli.");
		}catch(FindFailed e){
			throw new Error("ERROR--Unable to click on the image \""+imageName+"\" using sikuli.");
			////failureScreenshot();
			//throw new Error("ERROR--Unable to click on the image \""+imageName+"\" using sikuli.");
		}catch(SikuliException e){
			throw new Error("ERROR--Unable to click on the image \""+imageName+"\" using sikuli. Sikuli Exception");
			//e.printStackTrace();
			////failureScreenshot();
			////throw new ExceptionInSikuli("ERROR--Unable to click on the image \""+imageName+"\" using sikuli.");			
		}
	}

	
	protected static void enterUsingSK(String imageName, String testData)  {
		try{
			getScreenObject().type(getPatternObject(imageName),testData);
			System.out.println("INFO -- \""+testData+"\" is entered into the Object ::  \""+imageName+"\" using sikuli.");
			////stepScreenshot();
		}catch(FindFailed e){
			throw new Error("ERROR -- \""+testData+"\" is unable to enter into the Object ::  \""+imageName+"\" using sikuli.");
			////failureScreenshot();
			////throw new ElementNotFoundException("INFO -- Unable to enter \""+testData+"\" in the object ::  \""+imageName+"\" using sikuli.");
		}catch(SikuliException e){
			throw new Error("FATAL -- Unable to enter \""+testData+"\" in the object ::  \""+imageName+"\" using sikuli. Sikuli Exception");
			//e.printStackTrace();
			////failureScreenshot();
			////throw new ExceptionInSikuli("FATAL -- Unable to enter \""+testData+"\" in the object ::  \""+imageName+"\" using sikuli. Sikuli Exception");	
		}
	}
	
	protected static void rightclickUsingSK(String imageName) {
		try{
			getScreenObject().rightClick(getPatternObject(imageName));
			System.out.println("INFO--right click on the image \""+imageName+"\" using sikuli.");
			//stepScreenshot();
		}catch(FindFailed e){
			throw new Error("ERROR--Unable to right click on the image \""+imageName+"\" using sikuli.");
			//failureScreenshot();
			////throw new ElementNotFoundException("ERROR--Unable to click on the image \""+imageName+"\" using sikuli.");
		}catch(SikuliException e){
			throw new Error("ERROR--Unable to right click on the image \""+imageName+"\" using sikuli. Sikuli Exception");
			//e.printStackTrace();
			//failureScreenshot();
			////throw new ExceptionInSikuli("ERROR--Unable to right click on the image \""+imageName+"\" using sikuli.");			
		}
	}
	
	protected static void findElementUsingSK(String imageName) {
		try{
			getScreenObject().find(getPatternObject(imageName));
			System.out.println("INFO--find element  \""+imageName+"\" using sikuli.");
			//stepScreenshot();
		}catch(FindFailed e){
			throw new Error("ERROR--Unable to find element \""+imageName+"\" using sikuli.");
			//failureScreenshot();
			//throw new ElementNotFoundException("ERROR--Unable to find element \""+imageName+"\" using sikuli.");
		}catch(SikuliException e){
			throw new Error("ERROR--Unable to find element \""+imageName+"\" using sikuli. Sikuli Exception");
			//e.printStackTrace();
			//failureScreenshot();
			//throw new ExceptionInSikuli("ERROR--Unable to find element \""+imageName+"\" using sikuli.");			
		}
	}
	
	protected static void doubleClickUsingSK(String imageName)  {
		try{
			getScreenObject().doubleClick(getPatternObject(imageName));
			System.out.println("INFO--doubleClicked on the image \""+imageName+"\" using sikuli.");
			//stepScreenshot();
		}catch(FindFailed e){
			throw new Error("ERROR--Unable to double click on the image \""+imageName+"\" using sikuli.");
			//failureScreenshot();
			//throw new ElementNotFoundException("ERROR--Unable to double click on the image \""+imageName+"\" using sikuli.");
		}catch(SikuliException e){
			throw new Error("ERROR--Unable to double click on the image \""+imageName+"\" using sikuli. Sikuli Exception");
			//e.printStackTrace();
			//failureScreenshot();
			//throw new ExceptionInSikuli("ERROR--Unable to double click on the image \""+imageName+"\" using sikuli.");			
		}
	}
	
	protected static void verifyElementPresentUsingSK(String imageName){
		try{
			getScreenObject().exists(getPatternObject(imageName));
			System.out.println("INFO--The Expected Element should be present \""+imageName+"\" on the page. using sikuli.");
			//stepScreenshot();
		}catch(Exception e){
			throw new Error("ERROR -- Element is not present  \""+imageName+"\" on the page. using sikuli.");
			//failureScreenshot();
		}
		
	}
	
	protected static void dragAndDropUsingSK(String imageFrom, String imageTo) {
		try{
			getScreenObject().dragDrop(getPatternObject(imageFrom),getPatternObject(imageTo));
			System.out.println("INFO--Drag the image \""+imageFrom+"\" and Drop on the image \""+imageTo+"\" using sikuli.");
			//stepScreenshot();
		}catch(FindFailed e){
			throw new Error("ERROR--Unable to Drag the image \""+imageFrom+"\" and Drop on the image \""+imageTo+"\" using sikuli.");
			//failureScreenshot();
			//throw new ElementNotFoundException("ERROR--Unable to Drag the image \""+imageFrom+"\" and Drop on the image \""+imageTo+"\" using sikuli.");
		}catch(SikuliException e){
			throw new Error("ERROR--Unable to Drag the image \""+imageFrom+"\" and Drop on the image \""+imageTo+"\" using sikuli. Sikuli Exception");
			//e.printStackTrace();
			//failureScreenshot();
			//throw new ExceptionInSikuli("ERROR--Unable to Drag the image \""+imageFrom+"\" and Drop on the image \""+imageTo+"\" using sikuli.");			
		}
	}
	
	protected static void mouseHoverUsingSK(String imageName) {
		try{
			getScreenObject().hover(getPatternObject(imageName));
			System.out.println("INFO--Mousehover on the image \""+imageName+"\" using sikuli.");
			//stepScreenshot();
		}catch(FindFailed e){
			throw new Error("ERROR--Unable to mousehover on the image \""+imageName+"\" using sikuli.");
			//failureScreenshot();
			//throw new ElementNotFoundException("ERROR--Unable to mousehover on the image \""+imageName+"\" using sikuli.");
		}catch(SikuliException e){
			throw new Error("ERROR--Unable to mousehover on the image \""+imageName+"\" using sikuli. Sikuli Exception");
			//e.printStackTrace();
			//failureScreenshot();
			//throw new ExceptionInSikuli("ERROR--Unable to mousehover on the image \""+imageName+"\" using sikuli.");			
		}
	}



}
