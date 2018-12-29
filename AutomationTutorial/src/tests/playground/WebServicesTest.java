package tests.playground;
 
import java.util.HashMap;

import data.Constants;
import infrastructure.SoapXML;
import utils.PropertyUtils;
import utils.XMLUtils;

public class WebServicesTest {


	public static void main(String[] args) throws Exception {

		String requestXMLFilePath= Constants.path_RequestXMLFile;
		String responseXMLFilePath= Constants.path_ResponseXMLFile;
		String webServiceURL = PropertyUtils.propertyFile_Read(Constants.path_PropertyFile_config, "webServiceURL");
		

		System.out.println("Posting the XML...");
		new SoapXML().postXMLPayload(webServiceURL, requestXMLFilePath, responseXMLFilePath);
		
		
	 	XMLUtils xml = new XMLUtils();
	 	// Read XML Request File
		HashMap<String,String> xmlRequestData =	new XMLUtils().getHashMapFromXML(Constants.path_RequestXMLFile);
		HashMap<String,String> xmlResponseData =	new XMLUtils().getHashMapFromXML(Constants.path_ResponseXMLFile);

	 	String requestCode = xmlRequestData.get("CityName");
		System.out.println(requestCode); 
	 	
 
	}

}
