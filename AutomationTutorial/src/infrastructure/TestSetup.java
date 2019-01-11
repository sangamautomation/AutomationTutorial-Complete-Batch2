package infrastructure;

import java.util.HashMap;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import data.BackupNRestore;
import data.Constants;
import data.TestDataPool;
import data.User;
import reports.SendReportEmail;
import reports.TestReportTools;
import utils.DatabaseUtils;
import utils.DateUtils;
import utils.PropertyUtils;
import utils.ReportUtils;
import utils.Utils;

/**
 * Description : Setup for dynamic environment URLs
 * 
 * @author Sangam
  */

public class TestSetup {

	// Environments

	public final String FF = "Mozilla Firefox", IE = "Internet Explorer", CH = "Chrome";
	public static User usr;
	public String datapoolPath;
	//	public static WebDriver driver = null; //To make static driver throughout the test if driver is not passed as parameter in functions

	public String getCitizenPortalURL(String environment){
		String envURL;
		if (environment.equals("OOTB")) {
			envURL = "https://Automation-d697fx1:9148/CitizenPortal";
		} else {
			if(environment.equals("UAT1")){
				environment = "UAT";
			} else if(environment.equals("UAT5")){
				environment = "UAT5temp";
			}
			envURL = "https://" + environment + "eligibilityenrollment.automation.com/CitizenPortal/application.do";
		}
		return envURL;
	}
	
	public String getIntakePortalURL(String environment){
		String intakeURL;
		if (environment.equals("OOTB")) {
			intakeURL = "https://Automation-d697fx1:9148/Automation";
		} else {
			if(environment.equals("UAT1")){
				environment = "UAT";
			}
			intakeURL = "https://" + environment + "Selenium.automation.com/Automation/logon.jsp";
		}
		return intakeURL;
	}
	
	public WebDriver TestSetup(String[] args, WebDriver driver) throws Exception {
		String env = args[0].toString().toUpperCase();
		try {
			TestDataPool.rowData.put("jenkinsUserName", args[1].toString());
			TestDataPool.rowData.put("jenkinsBuildNumber", args[2].toString());
			TestDataPool.rowData.put("jenkinsPackageName", args[3].toString());
			TestDataPool.rowData.put("jenkinsVMName", args[4].toString());
		} catch (Exception e) {
			System.out.println("some args is missing, please pass 5 arguments if running locally.");
		}
		driver = TestSetup(env, driver);
		return driver;
	}
	
	public WebDriver TestSetup(String environment, WebDriver driver) throws Exception {
		HashMap<String, String> h = TestDataPool.rowData;

		try {
			// Timestamp Initializations  01/27/2017 12\:50\:3
			final String testStartedTimeStamp = DateUtils.getTimeStamp();
//			if(h.get("appDate" ).isEmpty()){
//		         h.put(("appDate"),"0");
//		        }
			final String appDate = DateUtils.dateTodayPlus(Integer.parseInt(h.get("appDate")));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "TestStartedTimeStamp_CP", testStartedTimeStamp); 
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "TestEndedTimeStamp_CP", testStartedTimeStamp);
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "TestStartedTimeStamp_IP", testStartedTimeStamp);
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "TestEndedTimeStamp_IP", testStartedTimeStamp);
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "TestStartedTimeStamp_DS", testStartedTimeStamp);
			//Added App date
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "ApplicationSubmitDate", appDate);
			//	PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "TestEndedTimeStamp_DS", testStartedTimeStamp);

			PropertyUtils.propertySet(Constants.propertyFilePath_Captions, TestDataPool.captionsData);
			// Utils.propertyFile_Write(c.propertyFilePath_Env, "Environment",
			// environment); // Writes environment value to property file

			//Writing Environment to Property file's name of backup and restore
			TestDataPool.rowData.put("Environment", environment.toUpperCase());
		 	Constants.propertyFilePath_Input_Network  = "\\\\MB-SAS-FP-01\\Selenium-Testers$\\Automation-AutomationProjects\\Datapool\\backupNrestoreData\\magi\\TestInputData_MAGI_"+environment.toUpperCase()+".properties";

			//			PropertyUtils.propertyFile_Write(c.propertyFilePath_Config, "Environment", environment.toUpperCase()); // Writes
			//			ReportUtils.reportResult(c.none, "Environment: " + environment.toUpperCase(), c.none);

			String envTSTNo = environment.replaceAll("[a-zA-Z]+", "");
			TestDataPool.rowData.put("envTSTNo", envTSTNo);

			// Mapping for SSO Environments.
			if (//environment.contains("UAT5") // removed UAT5 from SSO temporarily.
					environment.contains("TST5")
					|| environment.contains("TFP1")
					|| environment.contains("PRDSTG1")
					) {
				TestDataPool.rowData.put("ssoEnv", "True");
			} else {
				TestDataPool.rowData.put("ssoEnv", "False");
			}
			
			
			// Mapping for Automation Build Number.
			if(environment.contains("TRN1")
					|| environment.contains("TRN4")) {
				TestDataPool.rowData.put("AutomationBuildNumber", "1_26");
			}
			else if(environment.contains("DEV4")
					|| environment.contains("UAT2")
					|| environment.contains("TST4")
					|| environment.contains("TFP1")) {
				TestDataPool.rowData.put("AutomationBuildNumber", "1_29");
			}
			else if(environment.contains("DEV2")
					|| environment.contains("TST1")
					|| environment.contains("DEV3")
					|| environment.contains("UAT1")	
					) {
				TestDataPool.rowData.put("AutomationBuildNumber", "1_30");
			}
			else if(environment.contains("DEV1")
					|| environment.contains("TST5")
					|| environment.contains("TST3")
					|| environment.contains("DC1")
					|| environment.contains("UAT5")
					) {
				TestDataPool.rowData.put("AutomationBuildNumber", "1_29_01");
			}
			else if(environment.contains("PRDSTG1")
					|| environment.contains("DEV5")
					|| environment.contains("UAT3")
					|| environment.contains("UAT4")
					) {
				TestDataPool.rowData.put("AutomationBuildNumber", "1_29_03");
			}
			else{
				TestDataPool.rowData.put("AutomationBuildNumber", "1_27");
			}

			// For AR Works Portal On/Off
			if(environment.contains("TST3")
				|| environment.contains("UAT5")
				|| environment.contains("UAT1")	
				|| environment.contains("TST2")	
				|| environment.contains("UAT3")
				|| environment.contains("PRDSTG")	
				|| environment.contains("PRD")
				|| environment.contains("DC2")) {
				TestDataPool.rowData.put("ARWorksSwitch", "OFF");
			}else{
				if (TestDataPool.rowData.get("AutomationBuildNumber").compareTo("1_29") >= 0){
					TestDataPool.rowData.put("ARWorksSwitch", "ON");
				} else {
					TestDataPool.rowData.put("ARWorksSwitch", "OFF");
				}
			}
			//Mapping Automation Version - Environment
//			if (environment.contains("DEV4")
//					|| environment.contains("DEV2")
//					|| environment.contains("DEV3")
//					|| environment.contains("TST1")
//					|| environment.contains("TST3")
//					|| environment.contains("TST5")
//					|| environment.contains("DC1")
//					|| environment.contains("UAT1")
//					|| environment.contains("UAT2")
//					|| environment.contains("UAT3")
//					|| environment.contains("UAT4")
//					|| environment.contains("UAT5")
//					|| environment.contains("TRN1")
//					|| environment.contains("TRN4")
//					|| environment.contains("TST5")
//					|| environment.contains("DC2")
//					|| environment.contains("TFP1")) {
				TestDataPool.rowData.put("AutomationVersion", "V7");
//			} else {
//				TestDataPool.rowData.put("AutomationVersion", "V6");
//			}

			//Mapping Business Rule Change - Environment
//			if (environment.contains("DEV4") 
//					|| environment.contains("TST1")
//					|| environment.contains("TST3") 
//					|| environment.contains("TST5") 
//					|| environment.contains("UAT1")
//					|| environment.contains("UAT2")
//					|| environment.contains("UAT3")
//					|| environment.contains("UAT4")
//					|| environment.contains("UAT5")
//					|| environment.contains("DC1")
//					|| environment.contains("DC2")
//					|| environment.contains("TRN1")
//					|| environment.contains("TRN4")
//					|| environment.contains("DEV3")
//					|| environment.contains("TST5")
//					|| environment.contains("DEV2"))
				TestDataPool.rowData.put("BRule-Residency", "N");
//			else 
//				TestDataPool.rowData.put("BRule-Residency", "Y");

// 		if (environment.contains("TRN") )
// 	TestDataPool.rowData.put("BRule-Residency", "Y");
//
// 		if (environment.contains("TRN") )
// 	TestDataPool.rowData.put("AutomationVersion", "V6");
 		
			System.out.println("Scenario Description: " + h.get("scenarioDescription"));
			System.out.println("Expected Result: " + h.get("expectedResult"));

			// Credentials
			String cwUser = Utils.propertyGet(Constants.propertyFilePath_User, "CWUSERNAME_" + environment);
			String cwPass = Utils.propertyGet(Constants.propertyFilePath_User, "CWPASSWORD_" + environment);
			usr = new User(cwUser, cwPass);

			driver = new Operations().launchBrowser();
			// 	driver = SeleniumDescriptive.launchBrowser();
			//	driver = launchBrowser(driver);	
			getBrowserDetails(driver);

			// Report Header
			ReportUtils.reportHeader();

			if(TestDataPool.rowData.get("stack").contains("day2")){
				BackupNRestore.dataExtraction_COC() ; 
				//BackupNRestore.dataExtraction() ; 
				//BackupNRestore.dataExtraction_Household();//All persons
			}
			/*	if(TestDataPool.rowData.get("testHarnessData").contains("Reuse")){
				BackupNRestore.dataRecall(testcasePath) ; 
			}*/
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		return driver;
	}

	public void getBrowserDetails(WebDriver driver) throws Exception{
		ReportUtils.reportResult("Done", "Browser Details", getBrowsername(driver) + " " + getBrowserVersion(driver));
	}

	public String getBrowsername(WebDriver driver){
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		System.out.println("Browser : " +browserName);
		return browserName;
	}

	public String getBrowserVersion(WebDriver driver){
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String v = cap.getVersion().toString();
		System.out.println("Version : " + v);
		return v;
	}

	public static void postrequisites(WebDriver driver) throws Exception {

		// ReportUtils.reportResult("Done","***** End Of "+c.AutomationType_IntakePortal+" *****",
		// "");
		final String testEndedTimeStamp = DateUtils.getTimeStamp();
		Utils.propertyFile_Write(Constants.propertyFilePath_Output, "Test Ended TimeStamp", testEndedTimeStamp);
		System.out.println("Test Ended @ " + testEndedTimeStamp);
		// RationalTestScript.logInfo("Test Ended @ "+testEndedTimeStamp);

		int timeDiff = DateUtils.getDiffBetweenDateAndTimes(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "Test Ended TimeStamp"),
				PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "Test Started TimeStamp"));
		System.out.println("Duration of the test is :" + timeDiff + " Min.");
		// RationalTestScript.logInfo("Test Duration: "+timeDiff+ " Min.");
		Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TestDuration", timeDiff + " Min.");
		ReportUtils.reportResult("Done", "Test Duration", timeDiff + " Min.");

		// BackupNRestore.dataSave();
		// ReportUtils.reportResult("Pass","Last Screenshot", "");
		// ReportUtils.reportResult("Done","Last Screenshot",
		// "____________ End of Test Case ___________");
		System.out.println("DONE : postrequisites");
		//k.Key_Tab(); //To avoid locking system

		driver.quit(); // kills browser instance
		//System.exit(0);//kills java process
	}

	// in case ip.postreq not reached
	public static void iptime(){

		try {
			final String testEndedTimeStamp = DateUtils.getTimeStamp();
			Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TestEndedTimeStamp_IP", testEndedTimeStamp);
			String timeDiff = DateUtils.datetime_JodaDateTimeDifference_MinSec(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_IP"),PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_IP"), "MM/dd/yyyy HH:mm:ss");
			String timeDiff_Automation = DateUtils.datetime_JodaDateTimeDifference_MinSec(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_CP"),PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_IP"), "MM/dd/yyyy HH:mm:ss");
			int timeDiff1 = DateUtils.getDiffBetweenDateAndTimes(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_IP"),
					PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_IP"));
			Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TestDuration_IP", timeDiff );
			Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TestDuration_Automation", timeDiff_Automation );
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// in case ds.postreq not reached
	public static void dstime(){

		try {
			final String testEndedTimeStamp = DateUtils.getTimeStamp();
			Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TestEndedTimeStamp_DS", testEndedTimeStamp);
			String timeDiff = DateUtils.datetime_JodaDateTimeDifference_MinSec(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_DS"),PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_DS"), "MM/dd/yyyy HH:mm:ss");
			String timeDiff_Automation = DateUtils.datetime_JodaDateTimeDifference_MinSec(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_CP"),PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_IP"), "MM/dd/yyyy HH:mm:ss");
			int timeDiff1 = DateUtils.getDiffBetweenDateAndTimes(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_IP"),
					PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_IP"));
			Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TestDuration_DS", "0 Min. 0 Sec." );
			Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TestDuration_Automation", timeDiff_Automation );
		} catch (Exception e) {
			e.printStackTrace();
		}
		//	PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "TestStartedTimeStamp_DS", testStartedTimeStamp);
		//	PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "TestEndedTimeStamp_DS", testStartedTimeStamp);
	}


	public static void testDuration() throws Exception {
		System.out.println("testDuration...");
		//	iptime();
		try {
			if(new Configuration().getProperty("DocuShareTest").equals("Yes")// Reset DocuShare=Yes/No based on if we want to turn on/off docuShare test in some environments (TST1/UAT1..)
					&&(TestDataPool.rowData.get("Environment").contains("TST")|| TestDataPool.rowData.get("Environment").contains("DEV"))
					// && "Regression".equalsIgnoreCase(TestDataPool.rowData.get("executionType"))
					&& "Pass".equalsIgnoreCase(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "Status_VerificationPoint-Notices"))
					&& !TestDataPool.rowData.get("stack").contains("day1")// For Day1 scripts, we dont need as the #ofNotices in datapool for after COC and we can directly validate them  after COC
					) { 

				String timeDiff_DocuShare = DateUtils.datetime_JodaDateTimeDifference_MinSec(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_DS"),PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_DS"), "MM/dd/yyyy HH:mm:ss");
				String timeDiff_Automation = DateUtils.datetime_JodaDateTimeDifference_MinSec(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_CP"),PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_IP"), "MM/dd/yyyy HH:mm:ss");
				String timeDiff_E2E = DateUtils.datetime_JodaDateTimeDifference_MinSec(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_CP"),PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_DS"), "MM/dd/yyyy HH:mm:ss");
				//DateUtils.datetime_JodaDateTimeDifference_MinSec("01/11/2017 13:14:44", "01/11/2017 13:19:47", "MM/dd/yyyy HH:mm:ss");
				int timeDiff1 = DateUtils.getDiffBetweenDateAndTimes(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_DS"),
						PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_DS"));
				System.out.println("Test duration of DocuShare is :" + timeDiff_DocuShare);
				System.out.println("Total duration of the test is :" + timeDiff_DocuShare);

				// RationalTestScript.logInfo("Test Duration: "+timeDiff+ " Min.");
				Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TestDuration_DS", timeDiff_DocuShare);
				Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TestDuration_E2E", timeDiff_E2E);
				Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TotalTestDuration", timeDiff_E2E);

				ReportUtils.reportResult("Done", "Total Duration: "+ timeDiff_E2E,"");
				System.out.println("Test Duration of End to End Run : "+ timeDiff_E2E+ " -- Automation : "+timeDiff_Automation +" + "+ "DocuShare : "+ timeDiff_DocuShare);
			}
			else {

				//String timeDiff_DocuShare = DateUtils.datetime_JodaDateTimeDifference_MinSec(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_DS"),PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_DS"), "MM/dd/yyyy HH:mm:ss");
				Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TestDuration_DS", "0 Min. 0 Sec." );
				String timeDiff_DocuShare ="0 Min. 0 Sec.";

				String timeDiff_Automation = DateUtils.datetime_JodaDateTimeDifference_MinSec(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_CP"),PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_IP"), "MM/dd/yyyy HH:mm:ss");
				String timeDiff_E2E = DateUtils.datetime_JodaDateTimeDifference_MinSec(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_CP"),PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_IP"), "MM/dd/yyyy HH:mm:ss");

				//DateUtils.datetime_JodaDateTimeDifference_MinSec("01/11/2017 13:14:44", "01/11/2017 13:19:47", "MM/dd/yyyy HH:mm:ss");
				//	int timeDiff1 = DateUtils.getDiffBetweenDateAndTimes(PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestEndedTimeStamp_DS"),PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output, "TestStartedTimeStamp_DS"));
				System.out.println("Test duration of DocuShare is :" + timeDiff_DocuShare);
				System.out.println("Total duration of the test is :" + timeDiff_DocuShare);

				// RationalTestScript.logInfo("Test Duration: "+timeDiff+ " Min.");
				//	Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TestDuration_DS", timeDiff_DocuShare);
				Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TestDuration_DS", "0 Min. 0 Sec." );

				Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TestDuration_E2E", timeDiff_E2E);
				Utils.propertyFile_Write(Constants.propertyFilePath_Output, "TotalTestDuration", timeDiff_E2E);

				ReportUtils.reportResult("Done", "Total Duration: "+ timeDiff_E2E,"");
				System.out.println("Test Duration of End to End Run : "+ timeDiff_E2E+ " -- Automation : "+timeDiff_Automation +" + "+ "DocuShare : "+ timeDiff_DocuShare);
			}

		} catch (Exception e) {
			e.printStackTrace();
			//throw e;
		}
	}

	public static void finallyMethod(WebDriver driver) throws Exception {
		System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ finallyMethod $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");

		if(TestDataPool.rowData.get("stack").contains("day1")) {
			BackupNRestore.dataPreservation_COC();
			//	 BackupNRestore.dataPreservation();
			//	BackupNRestore.dataPreservation_Household();//All persons
		}else if(!TestDataPool.rowData.get("stack").contains(".day2")) {// For non-coc scripts
			BackupNRestore.dataSave();
		}
		TestDataPool.rowData.put("scriptEndTime", DateUtils.datetime_Now("yyyy-MM-dd-HH.mm.ss"));
		// Test Duration
		//	if(!TestDataPool.rowData.get("stack").contains(".day2")) // For non-coc script	
		testDuration(); 

		//	ReportUtils.reportResult("Done", "**************************** TEST CASE ENDS ****************************","");
		ReportUtils.reportResult("ExitLoop","","");
		PropertyUtils.propertySet(Constants.propertyFilePath_Captions, TestDataPool.captionsData);//Call this at the end to write all Captions for reportResult

		//driver.close();
		driver.quit(); //kills browser instance - comment it for leaving the browser open to debug in case there is excp
		Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");//just kill browser 
		//	Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe"); 
		//	Runtime.getRuntime().exec("taskkill /F /IM chrome.exe"); 
		//	postrequisites(driver);
		// Below for loop is to print all the key value of the TestDataPool
//			for (String name: TestDataPool.rowData.keySet()){
//
//	            String key = name.toString();
//	            String value ="";
//	            try {
//	            	value = TestDataPool.rowData.get(name).toString();	
//				} catch (Exception e2) {
//					value = "";
//				}  
//	            System.out.println(key + "=" + value);  
//			}
		// This condition is future use, replacement of vbs to create and send report.
		
		TestReportTools.runTestReportTools(TestDataPool.rowData);
//			System.out.println("\nAbout to start HtmlToMsWordPDF.runHtmlToMsWordPDF(...)" );
//			HtmlToMsWordPDF.runHtmlToMsWordPDF(TestDataPool.rowData);
//			System.out.println("\nDone HtmlToMsWordPDF.runHtmlToMsWordPDF(...)" );
		if("Yes".equals(new Configuration().getProperty("runJavaReporting"))){
			System.out.println("\nAbout to start SendReportEmail.runSendReportEmail(...)" );
			SendReportEmail.runSendReportEmail(TestDataPool.rowData);
			System.out.println("\nDone SendReportEmail.runSendReportEmail(...)" );
		}
		DatabaseUtils.insertIntoAutomationDB(TestDataPool.rowData);
		//String[] cleanupVBS = {"C:\\Windows\\System32\\wscript.exe", (Constants.workingDir + "\\batches\\cleanupInstancesOfProcesses.vbs")};
		//Runtime.getRuntime().exec(cleanupVBS);
		
		System.exit(0);//kills java process	


	}
	
}
