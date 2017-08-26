package com.ctli.it.webPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ctli.it.lib.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;

public class EmpTeblePage  extends BaseClass {
	public EmpTeblePage(WebDriver driver, ExtentTest testReport) {
		super(driver, testReport);
	}
	static int column;
	
	public int getgetcolumnnumber(){
		
		List<WebElement> empHeader =driver.findElements(By.xpath("//table[@id='emptable']//th"));
		for(int i= 0; i< empHeader.size(); i++){
			String empColumnName = empHeader.get(i).getText();
			if(empColumnName.equals("EMPNAME")){
			column = i+1;
			break;
			}
		}
		return column;
	}
	
	public void getData(){
		int column =  getgetcolumnnumber();
		System.out.println(column);
		List<WebElement> listId = driver.findElements(By.xpath("//table[@id='emptable']//tr/td["+column+"]"));
			int empList = listId.size();
		for(int i = 0; i<empList; i++){
			String id = listId.get(i).getText();
			System.out.println("employee id ="+id);
			getEmpName(id);
			departmentName(id);
			salary(id);
			city(id);
		}
	}
	
	public void getEmpName(String id){
			String empName =driver.findElement(By.xpath("(//tr[td[text()='"+id+"']]/td[2])[1]")).getText();
			System.out.println("employee Name ="+empName);
			
	}
	
	public void departmentName(String id){
		String departNum = driver.findElement(By.xpath("//tr[td[text()='"+id+"']]/td[3]")).getText();
		String departName = driver.findElement(By.xpath("//table[@id='depttable']//tr[td[text()='"+departNum+"']]/td[2]")).getText();
		System.out.println("department Name ="+departName);
	}
	
	public void salary(String id){
		String salary = driver.findElement(By.xpath("//table[@id='salarytable']//tr[td[text()='"+id+"']]/td[2]")).getText();
		System.out.println("salary ="+salary);
	}
	
	public void city(String id){
		String city = driver.findElement(By.xpath("//tr[td[text()='"+id+"']]/td[4]")).getText();
		System.out.println("city = "+city);
		System.out.println("-----------------------------------------------------------------");
	}
}
