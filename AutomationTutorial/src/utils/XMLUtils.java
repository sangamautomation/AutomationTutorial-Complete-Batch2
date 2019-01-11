package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import data.Constants;
//import groovy.util.XmlNodePrinter.NamespaceContext;
import infrastructure.SoapXML;

/**
 * XMLUtils - Reading Request Payload, Posting XML Payload, Fetching Response Payload
 * @author Sangam
 */
public class XMLUtils {
	
	
 	
	public  HashMap<String,String>  getHashMapFromXML(String filePath){
		
		
		BufferedReader br = null;
		
		
		try {
			
		String lineContent;
		
		br = new BufferedReader(new FileReader(filePath)); //D:\\Selenium_Logs\\WebServices\\requests\\SampleWebService.xml
			
			//Gets all XML elements into HashMap
			getElementsIntoHashMap(new InputSource(br));
 			// Print all XML Key+Value pairs to Console & Property file
			printHashMap();
		} catch (Exception e) {
			e.printStackTrace();
 		}
		
		finally{
			try{
				if(br != null)
					br.close();
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		
		
		return xmlData;
		
		
	}
	
	
	
	
	private void printHashMap(){
		System.out.println("printHashMap......");
		for (Map.Entry<String,String> entry : xmlData.entrySet()) {
			
			System.out.println(entry.getKey() + " : "+ entry.getValue());
			PropertyUtils.propertyFile_Write(Constants.path_PropertyFile_webservices, entry.getKey(), entry.getValue());
			
		}
	}
	
	
	////
	
	private static HashMap<String, String> xmlData = new HashMap<>();

	public HashMap<String, String> getHashMapFromXML2(String filePath) {
		BufferedReader br = null;

		try {
			String lineContent;
			br = new BufferedReader(new FileReader(filePath));
			//			String responseData = "";
			//
			//			while ((lineContent = br.readLine()) != null) {
			//						responseData = responseData + lineContent;
			//			}
			//
			//			responseData = constructXMLDataString(responseData);
			// System.out.println(responseData);
			//			getElementsIntoHashMap(new InputSource(new StringReader(
			//					responseData)));
			getElementsIntoHashMap(new InputSource(br));
			//			 printHashmap();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return xmlData;
	}

	private void printHashmap() throws Exception {
		// To print hashmap
		for (Map.Entry<String, String> entry : xmlData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
			Utils.propertySet(Constants.propertyFilePath_Output, entry.getKey(), entry.getValue());
		}
	}

	private void printHashmap(HashMap<String, String> rowData) throws Exception {
		// To print hashmap
		String propFileName = new Constants().propertyFilePath_Output;
		for (Map.Entry<String, String> entry : rowData.entrySet()) {
			System.out.println("$Prop Set "+ entry.getKey() + " =:" + entry.getValue());
			Utils.propertySet(propFileName, entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Converts the string into xml string by replacing &lt; &gt; and &quot;
	 * 
	 * @param responseString
	 * @return responseString
	 */
	public static String constructXMLDataString(String responseString) {

		responseString = responseString.replace("&lt;", "<");
		responseString = responseString.replace("&gt;", ">");
		responseString = responseString.replace("&quot;", "\"");
		// System.out.println(responseString);
		return responseString;
	}

	/**
	 * Reads the xml File and adds all the Elements into xmlData hashmap
	 * variable.
	 * 
	 * @param xmlFile
	 */
	public HashMap<String, String> getElementsIntoHashMap(InputSource xmlFile) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			// System.out.println("Root element :"
			// + doc.getDocumentElement().getNodeName());
			try {
				if (doc.hasChildNodes()) {

					printNote(doc.getChildNodes());

				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlData;
	}

	private static void printNote(NodeList nodeList) {

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				// get the string after : from the Node Name and put that key
				// and value in the hashmap
				String strKey = tempNode.getNodeName().substring(
						tempNode.getNodeName().lastIndexOf(":") + 1);
				//System.out.println("strKey=" + strKey);
				xmlData.put(strKey, tempNode.getTextContent());
				//System.out.println("strValue=" + tempNode.getTextContent());
			}

			if (tempNode.hasChildNodes()) {
				// loop again if has child nodes
				printNote(tempNode.getChildNodes());

			}

			 //System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
		}
	}

	private static void printAllNote(NodeList nodeList) {/*

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				// get node name and value
				System.out.println("\nNode Name =" + tempNode.getNodeName()
						+ " [OPEN]");
				System.out.println("Node Value =" + tempNode.getTextContent());

				// get the string after : from the Node Name and put that key
				// and value in the hashmap
				String strKey = tempNode.getNodeName().substring(
						tempNode.getNodeName().lastIndexOf(":") + 1);
				xmlData.put(strKey, tempNode.getTextContent());

				// Code to print Attributes of the node.
				if (tempNode.hasAttributes()) {
					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
						System.out.println("attr name : " + node.getNodeName());
						System.out.println("attr value : "
								+ node.getNodeValue());

					}

				}

				if (tempNode.hasChildNodes()) {
					// loop again if has child nodes
					printNote(tempNode.getChildNodes());

				}

				// System.out.println("Node Name =" + tempNode.getNodeName()
				// + " [CLOSE]");
			}
		}
	*/}

	/**
	 * Returns an Element string value for the xPath from the xmlString tag, stored as CDATA inside the xml file.
	 * @param xmlFilePath
	 * @param xPath
	 * @return
	 */
	public String getElementByXPathFromCDATA(String xmlFilePath, String xPath){
		DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		org.w3c.dom.Document document = null;
		XPath xPathVar =  XPathFactory.newInstance().newXPath();
		try {

			builder = builderFactory.newDocumentBuilder();
			document = builder.parse(new InputSource(new StringReader(getElementByXPathFromXML(xmlFilePath, "//xmlString"))));
			document.getDocumentElement().normalize();
			Object result = xPathVar.compile(xPath).evaluate(document, XPathConstants.NODE);
			Node node = (Node)result;
			// System.out.println(node3.getTextContent());
			return node.getTextContent();
		} catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retunrs an Element string value for the xPath from the xml file.
	 * @param xmlFilePath
	 * @param xPath
	 * @return
	 */
	public String getElementByXPathFromXML(String xmlFilePath, String xPath){/*
		DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder builder = null;
		org.w3c.dom.Document document = null;
		XPath xPathVar =  XPathFactory.newInstance().newXPath();
		xPathVar.setNamespaceContext(new NamespaceContext() {

            @Override
            public Iterator getPrefixes(String arg0) {
                return null;
            }

            @Override
            public String getPrefix(String arg0) {
                return null;
            }

            @Override
            public String getNamespaceURI(String arg0) {
            	if("soap".equals(arg0)) {
                    return "http://schemas.xmlsoap.org/soap/envelope/";
                }
           
            	return null;
            }
        });
		try {
			builder = builderFactory.newDocumentBuilder();
			document = builder.parse(xmlFilePath);
			document.getDocumentElement().normalize();

			Object result = xPathVar.compile(xPath).evaluate(document, XPathConstants.NODE);
			Node node = (Node)result;
			return node.getTextContent();

		} catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
			e.printStackTrace();
	*/		return null;
		}
 

	/**
	 * updates all the element values given in the updateList in the xmlTemplateFilePath and creates new file at xmlFilePath
	 * @param xmlTemplateFilePath
	 * @param xmlFilePath
	 * @param updateList
	 */
	public void setElementByXPathFromXML(String xmlTemplateFilePath, String xmlFilePath, String[][] updateList){/*
		DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder builder = null;
		org.w3c.dom.Document document = null;
		XPath xPathVar =  XPathFactory.newInstance().newXPath();
		xPathVar.setNamespaceContext(new NamespaceContext() {

            @Override
            public Iterator getPrefixes(String arg0) {
                return null;
            }

            @Override
            public String getPrefix(String arg0) {
                return null;
            }

            @Override
            public String getNamespaceURI(String arg0) {
            	if("soap".equals(arg0)) {
                    return "http://schemas.xmlsoap.org/soap/envelope/";
                }
              
            	return null;
            }
        });
		try {
			builder = builderFactory.newDocumentBuilder();
			document = builder.parse(xmlTemplateFilePath);
			document.getDocumentElement().normalize();
			for(int i=0; i<updateList.length; i++){
				try{
				Object result = xPathVar.compile(updateList[i][0]).evaluate(document, XPathConstants.NODE);
				Node node = (Node)result;
				node.setTextContent(updateList[i][1]);
				} catch (Exception e){
					System.out.println("Node " + updateList[i][0] + " not found in XML. Continuing with other node updates.");
				}
			}

			// Use a Transformer for output
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(xmlFilePath);
			transformer.transform(source, result);

		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	*/}
 
	
	private BufferedWriter prepareFileToWrite(String filePath)
			throws IOException {
		File file = new File(filePath);
		File parentFile = new File(file.getParent());
		if (!parentFile.exists()) {
			parentFile.mkdirs();

		}
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
		BufferedWriter bw = new BufferedWriter(fw);
		return bw;
	}
	
	public static HashMap<String, String> serviceTest(String requestXMLFilePath, String webServiceUrl) throws Exception{
		String responseXMLFilePath = Constants.workingDir+"\\webservices\\responses\\response.xml";
		System.out.println(webServiceUrl);
		System.out.println("Posting the XML");
		new SoapXML().postXMLPayload(webServiceUrl, requestXMLFilePath, responseXMLFilePath);
		
		// ***** reading and validating the response *****
		HashMap<String, String> xmlResponseData = new XMLUtils().getHashMapFromXML(responseXMLFilePath);
		return xmlResponseData;
		}
	

}
