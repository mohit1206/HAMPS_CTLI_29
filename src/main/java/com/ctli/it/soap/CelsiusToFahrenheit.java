package com.ctli.it.soap;

import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctli.it.lib.TestNgInitialization;
import com.eviware.soapui.impl.WsdlInterfaceFactory;
import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlRequest;
import com.eviware.soapui.impl.wsdl.WsdlSubmit;
import com.eviware.soapui.impl.wsdl.WsdlSubmitContext;
import com.eviware.soapui.model.iface.Request.SubmitException;
import com.eviware.soapui.support.SoapUIException;
import com.eviware.soapui.support.XmlHolder;
public class CelsiusToFahrenheit extends TestNgInitialization {
	

	@Test
	public  void CelsiusToFahrenheit() throws XmlException, IOException, SoapUIException, SubmitException {
	
		// Request : scan WSDL Step 1 in notes---->scan the wsdl
		String WSDLURL="http://webservices.daehosting.com/services/TemperatureConversions.wso?WSDL";
	WsdlProject project=new WsdlProject();
	WsdlInterface iface=WsdlInterfaceFactory.importWsdl(project, WSDLURL, true)[0];
	
	//Request : Step2 as per notes--->pick an operation
	WsdlOperation Operation=iface.getOperationByName("CelsiusToFahrenheit");

	//Request : step3-->Build a request
	WsdlRequest request = Operation.addNewRequest("SoapReq");
	
	//below line is when mock server is used as EndPoint
//request.setEndpoint("http://HP-PC:8088/mockTemperatureConversionsSoapBinding");
	
	//Request : step4:Build envolope
	request.setRequestContent(Operation.createRequest(true));
	
	//Request : step5 a--->Fetch the xml
	String xmlRequest=request.getRequestContent();
	System.out.println("Old request" + xmlRequest);
	
	
	

	// Request :step5 b----->edit the xml
	XmlHolder Reqholder= new XmlHolder(xmlRequest);
	Reqholder.declareNamespace("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
	Reqholder.declareNamespace("tem", "http://webservices.daehosting.com/temperature");
	Reqholder.setNodeValue("//tem:nCelsius", "12");
		String NewReq=Reqholder.getXml();
		System.out.println("New req" + NewReq);
	
	
		request.setRequestContent(NewReq);
	
	
	// Request :Step 6 --->sending request to server..here we get celesius to faraneit converted value
	WsdlSubmit submit=request.submit(new WsdlSubmitContext(request), false);
	
	//Response:step1  fetch values from response
	String  Res= submit.getResponse().getContentAsXml();
	System.out.println(Res); 

	Reporter.log(Res);
	

	//Response:step2  compare actual and expected value
	XmlHolder Resholder= new XmlHolder(Res);
	Resholder.declareNamespace("soap", "http://schemas.xmlsoap.org/soap/envelope/");
	Resholder.declareNamespace("m", "http://webservices.daehosting.com/temperature");
	
	String ActualValue = Resholder.getNodeValue("//m:CelsiusToFahrenheitResult"); 
			Assert.assertEquals(ActualValue,"53.6");
//	System.out.println(ActualValue);
//	Assert.assertEquals(Res.contains("104"),true);
	
	
	}

}
