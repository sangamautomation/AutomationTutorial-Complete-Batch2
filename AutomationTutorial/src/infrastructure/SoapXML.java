package infrastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import utils.ReportUtils;

/**
 *SoapXML - SOAP XML Infra-level functions
 *@author Sangam
 */
public class SoapXML {

	HttpPost post = null;
	CloseableHttpClient client = null;
	boolean isSoap12 = false;
	boolean isEnvInrequest = true;
	String SOAPAction = "fetching Cities";
	
	String responseSuccessCriteriaRegex = "Success";



	public void postXMLPayload (String  webServiceURL, String requestXMLFilePath, String responseXMLFilePath) throws Exception{

		ReportUtils.reportResult("Done", "Web Service EndPoint", webServiceURL);
		post = new HttpPost(webServiceURL);
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy(){
			public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException{
				return true;
			}
		}).build(), new NoopHostnameVerifier());

		client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		sendRequestFromXmlPayload(requestXMLFilePath,responseXMLFilePath);

	}


	private void sendRequestFromXmlPayload(String requestXMLFilePath,String responseXMLFilePath) throws Exception{

		BufferedReader reader = new BufferedReader(new FileReader(new File (requestXMLFilePath)));

		String line = "";
		StringBuilder sbXML = new StringBuilder();
		sbXML.append("");
		BufferedWriter bw = prepareFileToWrite(responseXMLFilePath);

		while((line=reader.readLine()) !=null	){
			if(line.trim().isEmpty()){
				continue;
			}
			
			sbXML.append(line);
			

		}

 ReportUtils.reportResult("Done", "Web Service Request Payload", sbXML.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", ""));
 //sendRequestWriteResponse(sbXML.toString(), bw);		
 sendRequestWriteResponse(sbXML.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", ""), bw);
 
 System.out.println("====================================");
 
 System.out.println(sbXML.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", ""));
 System.out.println("====================================");

 reader.close();
 bw.close();
 client.close();
 
 
	}


	private void sendRequestWriteResponse(String req, BufferedWriter bw) throws IOException {
		
		org.apache.http.HttpEntity entity = new ByteArrayEntity(req.getBytes("UTF-8"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post); // posts request to webservice and fetches respose
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
		String responseLine = "";
		
		StringBuilder sb = new StringBuilder();
		while((responseLine=rd.readLine()) !=null	){
 			sb.append(responseLine);		
	}
		
		ReportUtils.reportResult("Done", "Web Service Response XML", sb.toString());
 	
		Pattern pattern  = Pattern.compile(this.responseSuccessCriteriaRegex);
		
		if(pattern.matcher(sb.toString()).find()){
			System.out.println("SUCCESS");
		}
		else
			System.out.println("FAILURE");

		bw.write(sb.toString());
		bw.close();
	}


	private BufferedWriter prepareFileToWrite(String filePath) throws Exception {
		
		File file = new File (filePath);
		
		File parentFile = new File(file.getParent());
		if(!parentFile.exists()){
			parentFile.mkdirs();
		//	FileSystemUtils.createFolder(filePath);
		}
		if(!file.exists()){
			file.createNewFile();
		//	FileSystemUtils.createFile(folderPath, fileName, extension);
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),false);
		
		BufferedWriter bw = new BufferedWriter(fw);
		
		return bw;
		
	 
	}

}
	

