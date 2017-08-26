package com.ctli.it.soap;

import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.eviware.soapui.impl.WsdlInterfaceFactory;
import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlRequest;
import com.eviware.soapui.impl.wsdl.WsdlSubmit;
import com.eviware.soapui.impl.wsdl.WsdlSubmitContext;
import com.eviware.soapui.support.SoapUIException;
import com.eviware.soapui.support.XmlHolder;
public class Soap_Refrence {
@Test
public void test() throws Exception
{
	String WSDLURL="https://ecaps-int1.foss.dev.qintra.com:40133/ecaps/service/UserAdminService?WSDL";
	WsdlProject project=new WsdlProject();
	WsdlInterface iface = WsdlInterfaceFactory.importWsdl(project, WSDLURL, true)[0];
	//pick the operation
	WsdlOperation opeartion = iface.getOperationByName("getUserById");
	
	
	//steps3
	WsdlRequest request = opeartion.addNewRequest("SoapReq1");

	//create  the envelop
	request.setRequestContent(opeartion.createRequest(true));
	//steps 5(a) 
	String xmlRequest = request.getRequestContent();
	
	System.out.println("get xml request"+xmlRequest);
	
	
	//To edit the xml
	XmlHolder Reqholder= new XmlHolder(xmlRequest);
	Reqholder.declareNamespace("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
	Reqholder.declareNamespace("user", "http://ecaps.ctl.com/service/userAdmin");
	Reqholder.setNodeValue("//user:userId", "9879183");
		String NewReq=Reqholder.getXml();
		System.out.println("New req" + NewReq);
	
	
		request.setRequestContent(NewReq);
	
	
	// sending request to server after 
	WsdlSubmit submit=request.submit(new WsdlSubmitContext(request), false);
	
	//Response:step1  fetch values from response
	String  Res= submit.getResponse().getContentAsXml();
	System.out.println(Res+"res"); 

	Reporter.log(Res);
	

	//Response:step2  compare actual and expected value
	XmlHolder Resholder= new XmlHolder(Res);
	Resholder.declareNamespace("soap", "http://schemas.xmlsoap.org/soap/envelope/");
	Resholder.declareNamespace("ns2", "http://ecaps.ctl.com/serviceObject");
	Resholder.declareNamespace("ns1", "http://ecaps.ctl.com/service/userAdmin");
	Resholder.declareNamespace("SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/");
	
	
	//String ActualValue = Resholder.getXml();
	String ActualValue = Resholder.getNodeValue("//ns2:emailAddress"); 
	System.out.println("Actual VAlue"+ActualValue);
			//Assert.assertEquals(ActualValue,Exp);

}
}
