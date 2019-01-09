package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import infrastructure.Configuration;
import utils.DateUtils;
import utils.ExcelUtils;
import utils.FileSystemUtils;
import utils.LogUtils;
import utils.PropertyUtils;
import utils.StringUtils;
import utils.Utils;
import utils.XMLUtils;

/**
 * Description : Datapool Level  Functions
 * @author Sangam
 */

public class TestDataPool {

	Constants c = new Constants();

	private   String nameSuffix;
	private final String randomNo= StringUtils.randomSSN();  
	private final String datetimeNow= DateUtils.datetime_Now("MMddYYHHmmss")+"123";  
	private final String dateMonthOnly= DateUtils.datetime_Now("MMdd");  
	private final String dateMonthYearOnly= DateUtils.datetime_Now("MMddyyyy");  

	//	  	public final String nameSuffix = Long.toString(System.currentTimeMillis()).substring(7);
	//int maxHH =9;

	// Hashmap  
	public static HashMap<String, String> rowData = new HashMap<>();
	public static HashMap<String, String> tcData = new HashMap<>();

	public static HashMap<String, String> namesData = new HashMap<>();
	public static HashMap<String, String> captionsData = new HashMap<>();
	public static int counter=0;

	// Hostname
	String hostname = InetAddress.getLocalHost().getHostName();
	String ipAddress = InetAddress.getLocalHost().getHostAddress();


	// Constructor, Default
	@SuppressWarnings("static-access")
	public TestDataPool() throws Exception
	{
		System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ TestDataPool ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
		//System.out.println("\n__________________________________... TEST CASE STARTS ...__________________________________\n");
		if("Yes".equals(new Configuration().getProperty("runJavaReporting"))){
			FileSystemUtils.createFile(Constants.AutomationTest_ExecutionLog_Path, "Captions", ".properties");
			FileSystemUtils.createFile(Constants.AutomationTest_ExecutionLog_Path, "TestHeader", ".properties");
			FileSystemUtils.createFile(Constants.AutomationTest_ExecutionLog_Path, "TestInputData", ".properties");
			FileSystemUtils.createFile(Constants.AutomationTest_ExecutionLog_Path, "TestOutputData", ".properties");
			FileSystemUtils.deleteFilesContains(Constants.AutomationTest_ProjectLogFolder_Path,"hereFiles.loog");
		}else{
			FileSystemUtils.deleteFilesWithExtension(Constants.AutomationTest_ExecutionLog_Path,".png");//delete screenshots
			FileSystemUtils.deleteFilesWithExtension(Constants.AutomationTest_ExecutionLog_Path,".jpg");//delete screenshots
			FileSystemUtils.deleteFilesWithExtension(Constants.AutomationTest_ExecutionLog_Path,".pdf");//delete notice pdfs
			FileSystemUtils.deleteFilesWithExtension(Constants.AutomationTest_ExecutionLog_Path,".txt");//delete notice txts
			FileSystemUtils.deleteFilesWithExtension(Constants.AutomationTest_ExecutionLog_Path,".html");//delete notice htmls
			FileSystemUtils.deleteFilesContains(Constants.AutomationTest_ExecutionLog_Path,"Notice");//delete notice property files
			FileSystemUtils.deleteFilesWithExtension(Constants.AutomationTest_ExecutionLog_Path,".doc");//delete docs
			FileSystemUtils.deleteFilesWithExtension(Constants.AutomationTest_ExecutionLog_Path,".docx");//delete docxs
			FileSystemUtils.deleteFilesWithExtension(Constants.AutomationTest_ExecutionLog_Path,".mht");//delete mht
			FileSystemUtils.deleteFilesContains(Constants.AutomationTest_ExecutionLog_Path,"hereFiles.loog");//delete temp file
			//FileSystemUtils.deleteFilesMatching(Constants.AutomationTest_ExecutionLog_Path,"Notice*properties");//delete notice property files
		}
		// Current Thread - Stack

		String stack;
		try {
			stack = Thread.currentThread().getStackTrace()[3].getClassName(); // For 3rd stack including AutomationProjects classes
		} catch (Exception e1) {
			stack = Thread.currentThread().getStackTrace()[2].getClassName(); // From elicpse, the test case is 2nd stack
		}
		String packageName = stack.substring(0,stack.lastIndexOf("."));
		//	String className = stack.substring(stack.lastIndexOf(".") + 1); //e.g. AT_100
		String className = stack; //e.g.regressionTest.magi.AT_100
		System.out.println("Test Case Details:");
		System.out.println("Complete TestCase Name = "+stack);
		System.out.println("packageName = "+packageName);
		System.out.println("className = "+className);
		rowData.put("TestCaseName", className);

		// String sheetName = packageName;
		String testCaseName = className;
		// String TestDataPool = Constants.TestDataPool; // Local Private Datapool (Jenkins)
		String TestDataPool_Network = Constants.TestDataPool_Network; // Network Shared Datapool


		System.out.println("Host Name = "+ hostname);
		System.out.println("IP Address: "+ ipAddress);
		testCaseName=testCaseName.replaceAll("day1.", "").replaceAll("day2.", "");//remove day1, day2 words from testCaseName to maintain single row for both

		LogUtils.logSpecial("testCaseName = "+testCaseName);

		if(!hostname.equals("YOURHOSTNAME")) 
			rowData = ExcelUtils.getTestDataXlsx(TestDataPool_Network, "Automation", 2, testCaseName); // For All Test cases From Individual Machines Shared
		else
			rowData = ExcelUtils.getTestDataXlsx(Constants.TestDataPool_Local, "Automation", 2, testCaseName); //local data pool

		// For Regression-ONLY TestCase Description & Expected Result will be overwritten in hashmap
		if(rowData.get("executionType").equals("Regression")){

			//	String testCaseID =testCaseName.replaceAll("day1.", "").replaceAll("day2.", "");//remove day1, day2 words from testCaseID to maintain single row for both
			String testCaseID =testCaseName; //Already removed day1, day2 words from testCaseName in xlsx rowData above 

			LogUtils.logSpecial("testCaseID = "+testCaseID);

			String scenarioDescription = new XMLUtils().getElementByXPathFromXML(Constants.TestDataPool_DescExp, "//testcase[@id='"+ testCaseID + "']/scenarioDescription");
			System.out.println("scenarioDescription="+scenarioDescription);
			String expectedResult = new XMLUtils().getElementByXPathFromXML(Constants.TestDataPool_DescExp, "//testcase[@id='"+ testCaseID + "']/expectedResult");
			System.out.println("expectedResult="+expectedResult);

			rowData.put("scenarioDescription",scenarioDescription);
			rowData.put("expectedResult",expectedResult);

	 	}

		// Names Conversions
		if (!rowData.get("testHarnessData").equals("Yes")) {
			try {
				if(!hostname.equals("YOURHOSTNAME"))// temporarily using local data pool
					namesData = ExcelUtils.getNamesMap(Constants.namesDBPath, "Names");
				else
					namesData = ExcelUtils.getNamesMap("D:\\Datapool\\NamesDB_Automation.xlsx", "Names"); //local names data pool

			} catch (Exception e) {
				Thread.sleep(1000);
				namesData = ExcelUtils.getNamesMap(Constants.namesDBPath, "Names");
			}
		}

		System.out.println(" \n ++++++++ Start ~ Debugging Output  +++++++++ \n");		


		//rowData.put("browser", "Firefox");
		//rowData.put("browser", "IE");
		TestDataPool.rowData.put("hostname", hostname);
		TestDataPool.rowData.put("ipAddress", ipAddress);
		TestDataPool.rowData.put("scriptStartTime", DateUtils.datetime_Now("yyyy-MM-dd-HH.mm.ss"));

		rowData.put("stack", stack);
		rowData.put("packageName", packageName);
		rowData.put("className", className);
		//rowData.put("TestCaseFullName", rowData.get("testCaseName"));

		//	rowData.put("Environment", environmentName);
		rowData.put(("householdSize"), rowData.get("hSize"));
		rowData.put(("householdSizeCOC"), rowData.get("hSizeC"));

		//Putting hSizeC in hSize for Day2
		if(rowData.get("stack").contains("day2") && rowData.get("stack").contains("incarceration"))
			rowData.put("householdSize", rowData.get("householdSizeCOC"));

		//	//	if(rowData.get("incomeAmount1_2" ).isEmpty()){
		//       //     rowData.put(("incomeAmount1_2"),"0");
		//   //  }
		//
		//		
		String hhs=  rowData.get("householdSize"); 
		//		if(hhs.equals("")){
		//			hhs="0";
		//		}

		System.out.println("Household Size = "+hhs);
		int hh = Integer.parseInt(hhs);
		System.out.println("Household Size = "+hh);


		/*if(rowData.get("incomeAmount1_2" ).isEmpty()){
            rowData.put(("incomeAmount1_2"),"0");
     }
		 */

		rowData.put(("applicationDate"), DateUtils.date_MinusDays(rowData.get("appDate"), "M/d/YYYY"));

		System.out.println("Test Case Type : "+rowData.get("testCaseType"));
		nameSuffix = c.getNameSuffix();


		rowData.put(("householdSizeF"), rowData.get("hSizeF"));
		rowData.put(("householdSizeCOC"), rowData.get("hSizeC")); 

		rowData.put(("ssncoc"),randomNo+2);
		int incHH = 0;


		if(rowData.get("hSizeC" ).isEmpty()){
			rowData.put(("hSizeC"),"0");
		}
		if(rowData.get("hSize" ).isEmpty()){
			rowData.put(("hSize"),"0");
		}

		if(Integer.parseInt(rowData.get("hSizeC"))>Integer.parseInt(rowData.get("hSize")))
		{
			System.out.println("hSizeCoC"+rowData.get("hSizeC"));
			rowData.put(("hSize"), rowData.get("householdSizeCOC")); 
		}


		for(int i=1;i<=Integer.parseInt(rowData.get("hSize"));i++){
			//for(int i=1;i<=hh;i++){

			//		rowData.put("Environment", environmentName);
			//	rowData.put(("householdSize"), rowData.get("hSize"));

			//Emancipation Data
			rowData.put(("emancipatedMinor"+i),"No"); //Default value

			// SSNs Conversions
			if (!rowData.get("testHarnessData").equals("Yes")) 
				rowData.put(("ssn"+i),randomNo+ i); 

			// Alien# Conversions
			if (!rowData.get("testHarnessData").equals("Yes")) 
				rowData.put(("AlienNo"+i),randomNo+ i +"11"	); //11 digits 

			// FFC
			rowData.put(("fosterCare"+i),rowData.get("ffc"+i));


			//Verifying Name entry
			if (!rowData.get("testHarnessData").equals("Yes")) {
				rowData.put(("fName"+i), namesData.get("firstName"+i).toUpperCase()); // first name will be in upper case for clearly distinguishing b/w different parts of name
				rowData.put(("fNameN"+i), namesData.get("firstName"+i).toUpperCase());
				rowData.put(("mName"+i), namesData.get("middleName"+i));
				rowData.put(("lName"+i), namesData.get("lastName"+i));
			}
			String userName = "TST" + rowData.get("fName1").substring(0, Math.min(rowData.get("fName1").length(), 3)) + datetimeNow; 
			userName =	userName.substring(0, Math.min(userName.length(), 19));
			rowData.put(("userName"), userName);
			rowData.put(("userName1"), userName);
			if (rowData.get("testHarnessData").equals("Yes")) 
				rowData.put(("userName"), (rowData.get("fName1")+datetimeNow)); // good if we run same scenario with same names more than once in a day
			rowData.put(("password"), "Testing@123"); // Hardcoded
			// 	 rowData.put(("password"), rowData.get("fName1")+"@"); // Password is same as fName1@ which is easier to copy from application page to login to cp if neede

			//rowData.put(("headOfHousehold"), rowData.get("hoh"+i));
			rowData.put(("headOfHousehold"),rowData.get(rowData.get("hoh")));

			rowData.put(("preferredContactMethod"),rowData.get(rowData.get("contactMethod")));
			rowData.put(("preferredLanguage"),rowData.get(rowData.get("language")));

			rowData.put(("fullName"+i), rowData.get("fName"+i) + " "+rowData.get("mName"+i)+" "+rowData.get("lName"+i));
			rowData.put(("fullNameSuffix"+i), rowData.get("fName"+i) + " "+rowData.get("mName"+i)+" "+rowData.get("lName"+i) +" "+rowData.get("suffix"+i));
			rowData.put(("firstLastName"+i), rowData.get("fName"+i) + " "+rowData.get("lName"+i) );

			// More People
			rowData.put(("morePeople"+i), rowData.get("moreP"+i));


			// DOB Conversions
			if (!rowData.get("testHarnessData").equals("Yes")) {
				//rowData.put(("age"+i), rowData.get("dob"+i)); // This will not work if the cell contains 12.4 (year.month)
				rowData.put(("age_Raw"+i), rowData.get("dob"+i));
				//			rowData.put(("age"+i), DateUtils.ageYears(rowData.get("dob"+i)));

				rowData.put(("age"+i), DateUtils.ageYearsMonths(rowData.get("dob"+i)));
				//rowData.put(("dob"+i), Dates.date_MinusYearsMonths(rowData.get("dob"+i), "M/d/YYYY"));
				rowData.put(("dob"+i), DateUtils.date_MinusYearsMonthsDays(rowData.get("dob"+i), "M/d/YYYY"));
			}

			rowData.put(("passport_ExpiryDate"+i), DateUtils.date_PlusYearsMonthsDays(rowData.get("passport_ExpiryDate"+i), "M/d/YYYY"));

			System.out.println(" \n ++++++++ End ~ Debugging Output  +++++++++ \n");		

			//rowData.put("TestCaseFullName", rowData.get("testCaseName"));

			PropertyUtils.propertyFile_Write(new Constants().propertyFilePath_Output, "TestFullCaseName", rowData.get("testCaseName"));

			// *******************************************************Property File*******************************************************
			writeToPropertyFile();		

		}
	}	
	public void writeToPropertyFile() throws FileNotFoundException, IOException {
		System.out.println(" \n ++++++++ Start of Property File Writing +++++++++ \n");		

		String propFileName = new Constants().propertyFilePath_Output;

		// Clear the property file
		PropertyUtils.clearProps(propFileName);

		// Write all the HashMap-rowData content to property file (just print the data until householdSize only)

		 Utils.propertySet(propFileName, "Environment", "ENV");
		PropertyUtils.propertySet(propFileName, "TestCaseId", rowData.get("testCaseID"));
		PropertyUtils.propertySet(propFileName, "TestFullCaseName", rowData.get("testCaseName"));
		PropertyUtils.propertySet(propFileName, "householdSize", rowData.get("householdSize"));

		//Writing Notices data to TestOutputData.properties
		PropertyUtils.propertySet(propFileName, "notices_Household", rowData.get("notices_Household"));
		PropertyUtils.propertySet(propFileName, "not_Pend", rowData.get("not_Pend"));
		PropertyUtils.propertySet(propFileName, "not_Appr", rowData.get("not_Appr"));
		PropertyUtils.propertySet(propFileName, "not_Rede", rowData.get("not_Rede"));
		PropertyUtils.propertySet(propFileName, "not_Deni", rowData.get("not_Deni"));
		PropertyUtils.propertySet(propFileName, "not_Clos", rowData.get("not_Clos"));
		PropertyUtils.propertySet(propFileName, "not_ReAp", rowData.get("not_ReAp"));
		PropertyUtils.propertySet(propFileName, "not_ReDe", rowData.get("not_ReDe"));
		PropertyUtils.propertySet(propFileName, "not_ReCl", rowData.get("not_ReCl"));
		PropertyUtils.propertySet(propFileName, "not_Rein", rowData.get("not_Rein"));
		PropertyUtils.propertySet(propFileName, "not_CaCh", rowData.get("not_CaCh"));

		int hhSizeDP = Integer.parseInt(rowData.get("hSize"));
		for (Map.Entry<String, String> entry : rowData.entrySet()) {
			String colName = entry.getKey();
			try {
				int hhSize = Integer.parseInt(colName.substring(colName.length()-1, colName.length()));
				//	if(hhSize<=hhSizeDP && !entry.getValue().equalsIgnoreCase("N/A")) {
				if(hhSize<=hhSizeDP) {

					//	if(!entry.getValue().equalsIgnoreCase("N/A"))
					PropertyUtils.propertySet(propFileName, entry.getKey(), entry.getValue());
				}
			}catch(NumberFormatException ex){
				//	if(!entry.getValue().equalsIgnoreCase("N/A") || !entry.getValue().equalsIgnoreCase(""))
				//		Utils.propertySet(propFileName, entry.getKey(), entry.getValue());
			}

		}
		//	System.out.println(entry.getKey() + "=" + entry.getValue());
		//	Utils.propertySet(new Constants().propertyFilePath_Output, rowData);	
		System.out.println(" \n ++++++++ End of Property File Writing +++++++++ \n");
	}


	public TestDataPool(String environment) throws Exception
	{
		// Current Thread - Stack

		String stack = Thread.currentThread().getStackTrace()[3].getClassName();
		String packageName = stack.substring(0,stack.lastIndexOf("."));
		String className = stack.substring(stack.lastIndexOf(".") + 1);
		System.out.println("Test Case Details:");
		System.out.println("Complete Testcase Name = "+stack);
		System.out.println("packageName = "+packageName);
		System.out.println("className = "+className);
		rowData.put("TestCaseName", className);
		String sheetName = packageName;
		String testCaseName = className;
		String TestDataPool = "Z:\\Automation-AutomationProjects\\Datapool\\TestDataPool_Automation.xlsx"; // Local Private Datapool (Jenkins)

		String className1 =packageName;
		String scriptName = className;
		int hh=0;

		System.out.println("Host Name = "+ hostname);
		System.out.println("IP Address: "+ipAddress);

		rowData = ExcelUtils.getTestDataXlsx(TestDataPool, "Automation", 2, testCaseName); // For All Test cases From Individual Machines Shared
		try {
			namesData = ExcelUtils.getNamesMap("Z:\\Automation-AutomationProjects\\Datapool\\NamesDB_Automation.xlsx", "Names");
		}catch(Exception e){
			Thread.sleep(1000);
			namesData = ExcelUtils.getNamesMap("Z:\\Automation-AutomationProjects\\Datapool\\NamesDB_Automation.xlsx", "Names");
		}

		System.out.println(" \n ++++++++ Start ~ Debugging Output  +++++++++ \n");		

		rowData.put("stack", stack);
		rowData.put("packageName", packageName);
		rowData.put("className", className);

		rowData.put("Environment", environment);
		rowData.put(("householdSize"), rowData.get("hSize"));

		String hhs=  rowData.get("hSize"); 
		String hhF=  rowData.get("hSizeF"); 
		System.out.println("Household Size = "+hhs);
		System.out.println("Household Full Size = "+hhF);

		int hS=Integer.parseInt(hhs); System.out.println("Household Size = "+hS);

		int hF = Integer.parseInt(hhF); System.out.println("Household Size = "+hF);

		if(hF>hS)

			hh=hF;
		else

			hh=hS;

		rowData.put(("applicationDate"), DateUtils.date_MinusDays(rowData.get("appDate"), "M/d/YYYY"));


		System.out.println("Test Case Type : "+rowData.get("testCaseType"));

		//	testCaseType=rowData.get("testCaseType");  //$ To drive the test case based on its type (e.g. A-Approved, D-Denied, COC-Change of circumstances, R-Reinstated, C-Closed, etc.)

		String userName = rowData.get("fName1")+datetimeNow; 
		userName =	userName.substring(0, Math.min(userName.length(), 19));
		rowData.put(("userName"), userName);
		rowData.put(("userName1"), userName);
		//		rowData.put(("userName"), rowData.get("userName")+Integer.parseInt(rowData.get("householdSize"))+datetimeNow);
		rowData.put(("password"), rowData.get("password")+"@"+datetimeNow); // Password is same as userName@
		//	for(int i=1;i<=Integer.parseInt(rowData.get("householdSize"));i++){
		for(int i=1;i<=hh;i++){

			rowData.put("Environment", environment);
			//	rowData.put(("householdSize"), rowData.get("hSize"));

			// SSNs
			rowData.put(("ssn"+i),randomNo+ i); 

			// FFC
			rowData.put(("fosterCare"+i),rowData.get("ffc"+i));

			/*	// Names (Random/Unique Number suffixed to Name)
			rowData.put(("fName"+i), rowData.get("fName"+i)+Integer.parseInt(rowData.get("householdSize"))+datetimeNow);
			//	rowData.put(("fName"+i), rowData.get("fName"+i)+datetimeNow);
			rowData.put(("mName"+i), rowData.get("mName"+i)+Integer.parseInt(rowData.get("householdSize"))+datetimeNow);
			rowData.put(("lName"+i), rowData.get("lName"+i)+Integer.parseInt(rowData.get("householdSize"))+datetimeNow);
			 */
			// Names Data for TST - No need to get/put, we can also comment below(Same Name from data pool without any suffix) - User SSN1 for IP search for getting just one result
			/*		rowData.put(("fName"+i), rowData.get("fName"+i));
 			rowData.put(("mName"+i), rowData.get("mName"+i));
			rowData.put(("lName"+i), rowData.get("lName"+i));
			 */

			// Names Data for TST4 (Old SNAP code , if we take same names, the persons become prospect - need unique names everytime)
			if( environment.matches("TST.*") || environment.matches("TFP.*")  ||environment.matches("DC.*") ||environment.matches("DEV.*") ||environment.matches("TRN.*") ||environment.matches("OOTB.*")){
				// Names (Random/Unique Number suffixed to Name)
				System.out.println("Environment is "+environment + " and the names will be suffixed with householdSize and dateTimeStamp");
				rowData.put(("fName"+i), rowData.get("fName"+i)+Integer.parseInt(rowData.get("householdSize"))+datetimeNow);
				//	rowData.put(("fName"+i), rowData.get("fName"+i)+datetimeNow);
				rowData.put(("mName"+i), rowData.get("mName"+i)+Integer.parseInt(rowData.get("householdSize"))+datetimeNow);
				rowData.put(("lName"+i), rowData.get("lName"+i)+Integer.parseInt(rowData.get("householdSize"))+datetimeNow);
			}


			// Names Data Mapping
			//			if(new Configuration().getEnvironment().contains("UAT") || new Configuration().getEnvironment().contains("TFP") || new Configuration().getEnvironment().contains("TST")|| new Configuration().getEnvironment().contains("DEV") ){
			System.out.println("Taking Names data as Environment = "+ environment);
			//		namesData = new Utils().getNamesMap(TestDataPool_Network, "Names");
			//	namesData = new Utils().getNamesMap(TestDataPool, "Names");

			rowData.put(("fName"+i), namesData.get("firstName"+i));
			rowData.put(("mName"+i), namesData.get("middleName"+i));
			rowData.put(("lName"+i), namesData.get("lastName"+i));



			System.out.println(" \n ++++++++ End ~ Debugging Output  +++++++++ \n");		

		}

	}
}
