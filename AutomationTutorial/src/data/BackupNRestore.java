package data;

import java.util.HashMap;

import utils.DateUtils;
import utils.LogUtils;
import utils.PropertyUtils;
import utils.ReportUtils;

/**
 * Description   : Data Preservation & Extraction & Reuse
 * @author Sangam
 */
public class BackupNRestore {

	// Day1: Writes output of current test case for input of next test case (2-day COC cases)	 for each test case
	public static void dataPreservation(HashMap<String, String> h) throws Exception {
		System.out.println("Data Preservation: ____________ \n");
		// Property file read/write for continuation of test case
		System.out.println("Propoerty  values set @ " +  Constants.propertyFilePath_Input_Network );
		String scriptName = h.get("stack").replaceAll("day1_cit.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", "");
		for(int i=1;i<=Integer.parseInt(h.get("householdSize"));i++)
		{
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Input_Network,scriptName+".ssn"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ssn"+i));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Input_Network,scriptName+".dob"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"dob"+i));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Input_Network,scriptName+".fullNameSuffix"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"fullNameSuffix"+i));

			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Input,h.get("stack").replaceAll("day1_cit.", "")+".ssn"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ssn"+i));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Input,h.get("stack").replaceAll("day1_cit.", "")+".dob"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"dob"+i));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Input,h.get("stack").replaceAll("day1_cit.", "")+".fullNameSuffix"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"fullNameSuffix"+i));

		}

		//	SuperHelper.logInfo("Data Preservation is successful!");
		ReportUtils.reportResult("Done", "Data Preservation: \n", "SSN1: "+h.get("ssn1") +"\n" + "DOB1: "+h.get("dob1") +"\n"+ "First Name1: "+h.get("fName1") +"\n"+ "Last Name1: "+h.get("lName1") +"\n"+ "Full Name1: "+h.get("fullNameSuffix1")+"\n"+ "Case Participant Name1: "+h.get("caseParticipant1"));

	}


	// Day1: Writes output of current test case for input of next test case (2-day COC cases)	 for each test case
	public static void dataPreservation() throws Exception {
		HashMap<String,String> h = TestDataPool.rowData;
		System.out.println("Data Preservation: ____________ \n");
		// Property file read/write for continuation of test case
		System.out.println("Propoerty  values set @ " + Constants.propertyFilePath_Input_Network );

		String scriptName = h.get("stack").replaceAll("day1_cit.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", "");
		//	for(int i=1;i<=Integer.parseInt(h.get("householdSize"));i++)// It's replacing previous member's data and finally overwriting with last member in prop file - need to build string and append other members OR write as _Person2 in seaparate lines (do it in _Household)
		for(int i=1;i<=1;i++) // Only for 1st member (need to be primary HOH)
		{
			h.put(scriptName + ".ssn"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ssn"+i));
			h.put(scriptName+".ssn"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ssn"+i));
			h.put(scriptName+".dob"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"dob"+i));
			h.put(scriptName+".fName"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"fName"+i));
			h.put(scriptName+".lName"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"lName"+i));
			h.put(scriptName+".firstLastName"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"firstLastName"+i));
			h.put(scriptName+".caseParticipant"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"caseParticipant"+i));
			h.put(scriptName+".fullNameSuffix"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"fullNameSuffix"+i));
			h.put(scriptName+".fullName"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"fullName"+i));
			h.put(scriptName+".PDCaseNo"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"PDCaseNo"));
			h.put(scriptName+".ICCaseNo"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ICCaseNo"));


			//Write to Network file (Consolidated backup copy)
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Input_Network,h.get("stack").replaceAll("day1.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", ""), PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"fullNameSuffix"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"dob"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ssn"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"PDCaseNo")+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ICCaseNo"));

			//Write to Network file (Environment-wise Used copy)
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Input_Network,h.get("stack").replaceAll("day1.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", ""), PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"fullNameSuffix"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"dob"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ssn"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"PDCaseNo")+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ICCaseNo"));


			Thread.sleep(3);
		}

		ReportUtils.reportResult("Done", "Data Preservation: \n", "SSN1: "+h.get("ssn1") +"\n" + "DOB1: "+h.get("dob1") +"\n"+ "First Name1: "+h.get("fName1") +"\n"+ "Last Name1: "+h.get("lName1") +"\n"+ "Full Name1: "+h.get("fullNameSuffix1")+"\n"+ "Case Participant Name1: "+h.get("caseParticipant1")+"\n"+ "PDCaseNo: "+h.get("PDCaseNo")+"\n"+ "ICCaseNo: "+h.get("ICCaseNo"));

	}

	// To Save data of a test case in order to use it later.
	public static void dataSave() throws Exception {
		//HashMap<String, String> h = new HashMap<>();
		LogUtils.logStart("dataSave");

		HashMap<String,String> h = TestDataPool.rowData;

		try {
			// Property file read/write for continuation of test case
			int hhs = Integer.parseInt(h.get("householdSize"));

			String[] sx1= new String[11];
			sx1[0]="";
			for(int i=1;i<=hhs;i++)// DONE: It's replacing previous member's data and finally overwriting with last member in prop file - need to build string and append other members OR write as _Person2 in seaparate lines (do it in _Household)
			{
				sx1[i]= PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"fullNameSuffix"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"dob"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ssn"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"PDCaseNo")+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ICCaseNo");

				Thread.sleep(3);
			}

			String fullofall = "";
			String x0= h.get("stack").replaceAll("day1.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", "");

			switch (hhs) {
			case 1: fullofall = sx1[1];break;
			case 2: fullofall = sx1[1]+";"+sx1[2];break; 
			case 3: fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3];break;
			case 4: fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3]+";"+sx1[4];break;
			case 5: fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3]+";"+sx1[4]+";"+sx1[5];break;
			case 6: fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3]+";"+sx1[4]+";"+sx1[5]+";"+sx1[6];break;
			case 7: fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3]+";"+sx1[4]+";"+sx1[5]+";"+sx1[6]+";"+sx1[7];break;
			case 8: fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3]+";"+sx1[4]+";"+sx1[5]+";"+sx1[6]+";"+sx1[7]+";"+sx1[8];break;
			default:fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3]+";"+sx1[4]+";"+sx1[5]+";"+sx1[6]+";"+sx1[7]+";"+sx1[8];break;
			}	
			//		PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Input_Network,x0,fullofall);

		} catch ( Exception e) {
			e.printStackTrace();
		}  

	}
	// @USED Day1: Writes output of current test case for input of next test case (2-day COC cases)	 for each test case
	public static void dataPreservation_COC() throws Exception {
		LogUtils.logStart("DataPreservation_COC");
		HashMap<String,String> h = TestDataPool.rowData;

		try {
			// Property file read/write for continuation of test case
			System.out.println("Propoerty  values set @ " + Constants.propertyFilePath_Input_Network );
			int hhs = Integer.parseInt(h.get("householdSize"));

			String[] sx1= new String[12];
			sx1[0]="";
			for(int i=1;i<=hhs;i++)// DONE: It's replacing previous member's data and finally overwriting with last member in prop file - need to build string and append other members OR write as _Person2 in seaparate lines (do it in _Household)
			{
				sx1[i]= h.get("RefNo")+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"userName1")+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"fullNameSuffix"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"dob"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ssn"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"PDCaseNo")+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ICCaseNo")+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ApplicationSubmitDate"); 
				//	sx1[i]= PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"fullNameSuffix"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"dob"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ssn"+i)+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"PDCaseNo")+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ICCaseNo")+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"ApplicationSubmitDate"+","+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"userName1")); 

				Thread.sleep(3);
			}

			String fullofall = "";
			String x0= h.get("stack").replaceAll("day1.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", "");

			// Building string by appending each set of value of person by a ;
			switch (hhs) {
			case 1: fullofall = sx1[1];break;
			case 2: fullofall = sx1[1]+";"+sx1[2];break; 
			case 3: fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3];break;
			case 4: fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3]+";"+sx1[4];break;
			case 5: fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3]+";"+sx1[4]+";"+sx1[5];break;
			case 6: fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3]+";"+sx1[4]+";"+sx1[5]+";"+sx1[6];break;
			case 7: fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3]+";"+sx1[4]+";"+sx1[5]+";"+sx1[6]+";"+sx1[7];break;
			case 8: fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3]+";"+sx1[4]+";"+sx1[5]+";"+sx1[6]+";"+sx1[7]+";"+sx1[8];break;
			default:fullofall = sx1[1]+";"+sx1[2]+";"+sx1[3]+";"+sx1[4]+";"+sx1[5]+";"+sx1[6]+";"+sx1[7]+";"+sx1[8];break;
			}	

			System.out.println("userName1="+PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Output,"userName1"));
			System.out.println("fullofall: "+fullofall);
			//PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Input_Network,x0,fullofall);
			//	PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Input_Network,x0,fullofall);//Env store for Backup purposes


			//Saving just for backup purposes
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Input,x0,fullofall);
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Input_Network,x0,fullofall);

			ReportUtils.reportResult("Done", "Data Preservation: \n",  "Reference No: "+h.get("RefNo")+"UserName1: "+h.get("userName1")+"\n "+"SSN1: "+h.get("ssn1") +"\n" + "DOB1: "+h.get("dob1") +"\n"+ "First Name1: "+h.get("fName1") +"\n"+ "Last Name1: "+h.get("lName1") +"\n"+ "Full Name1: "+h.get("fullNameSuffix1")+"\n"+ "Case Participant Name1: "+h.get("caseParticipant1")+"\n"+ "PDCaseNo: "+h.get("PDCaseNo")+"\n"+ "ICCaseNo: "+h.get("ICCaseNo"));

		} catch ( Exception e) {
			e.printStackTrace();
		}  

	}


	// Day2: Extracts output of previous test case for input of next test case (2-day COC cases)	 for each test case
	public static void dataExtraction(HashMap<String, String> h) throws Exception {
		System.out.println("Data Extraction: ____________ \n");

		//		HashMap<String,String> h = TestDataPool.rowData;

		// Property file read/write for continuation of test case
		for(int i=1;i<=Integer.parseInt(h.get("householdSize"));i++)
		{
			h.put("ssn"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Input_Network,h.get("stack").replaceAll("day2_coc.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", "")+".ssn"+i));
			h.put("dob"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Input_Network,h.get("stack").replaceAll("day2_coc.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", "")+".dob"+i));
			h.put("fullNameSuffix"+i, PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Input_Network,h.get("stack").replaceAll("day2_coc.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", "")+".fullNameSuffix"+i));
 
			String fullNameSuffix = h.get("fullNameSuffix"+i);
			String[] names = fullNameSuffix.split(" ");

			h.put("fName"+i, names[0]);
			h.put("lName"+i, names[2]);
			h.put("firstLastName"+i, names[0]+" "+names[2]);
			h.put("fullName"+i, names[0]+" "+names[1]+" "+names[2]);
 			h.put("caseParticipant"+i, h.get("firstLastName"+i)+" ("+DateUtils.ageCalculator("M/d/yyyy", h.get("dob"+i))+")"); // verify itesms dropdown only


			ReportUtils.reportResult("Done", "Data Extraction", "SSN1: "+h.get("ssn1") +"\n" + "DOB1: "+h.get("dob1") +"\n"+ "First Name1: "+h.get("fName1") +"\n"+ "Last Name1: "+h.get("lName1") +"\n"+ "Full Name1: "+h.get("fullNameSuffix1")+"\n"+ "Case Participant Name1: "+h.get("caseParticipant1"));
		}

		TestDataPool.rowData = h;
		//SuperHelper.logInfo("Data Extraction is successful!");

	}	


	// Day2: Extracts output of previous test case for input of next test case (2-day COC cases)	 for each test case
	public static void dataExtraction() throws Exception {
		System.out.println("Data Extraction: ____________ \n");

		HashMap<String,String> h = TestDataPool.rowData;
		//	TFP1_RegressionTest.Magi.AT_701_PCRxPCRxARKIDSAxARKIDSAxHCIPxHCIPxARKIDSA=TOCZEK Nordlinger Chalaban Esquire,7/28/1971,268043101

		// Property file read/write for continuation of test case
		for(int i=1;i<=Integer.parseInt(h.get("householdSize"));i++)
		{
	 		String fullText = PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Input,h.get("stack").replaceAll("day2_coc.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", ""));

			String[] individualText = fullText.split(",");

			h.put("fullNameSuffix"+i, individualText[0]);
			h.put("dob"+i, individualText[1]);
			h.put("ssn"+i, individualText[2]);
			h.put("PDCaseNo", individualText[3]);
			h.put("ICCaseNo", individualText[4]);


			String fullNameSuffix = h.get("fullNameSuffix"+i);
			String[] names = fullNameSuffix.split(" ");

			h.put("fName"+i, names[0]);
			h.put("lName"+i, names[2]);
			h.put("firstLastName"+i, names[0]+" "+names[2]);
			h.put("fullName"+i, names[0]+" "+names[1]+" "+names[2]);
			//	h.put("caseParticipant"+i, h.get("firstLastName"+i)+" ("+DateUtils.ageCalculator("M/d/yyyy", h.get("dob"+i)+")")); // verify itesms dropdown only
			h.put("caseParticipant"+i, h.get("firstLastName"+i)+" ("+DateUtils.ageCalculator("M/d/yyyy", h.get("dob"+i))+")"); // verify itesms dropdown only


			ReportUtils.reportResult("Done", "Data Extraction", "SSN1: "+h.get("ssn1") +"\n" + "DOB1: "+h.get("dob1") +"\n"+ "First Name1: "+h.get("fName1") +"\n"+ "Last Name1: "+h.get("lName1") +"\n"+ "Full Name1: "+h.get("fullNameSuffix1")+"\n"+ "Case Participant Name1: "+h.get("caseParticipant1")+"\n"+ "PDCaseNo: "+h.get("PDCaseNo")+"\n"+ "ICCaseNo: "+h.get("ICCaseNo"));
		}

		TestDataPool.rowData = h;
		//SuperHelper.logInfo("Data Extraction is successful!");

		// Writing back values to output prop file
		PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "fName1",h.get("fName1"));
		PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "lName1",h.get("lName1"));
		PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "ssn1",h.get("ssn1"));
		PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "dob1",h.get("dob1"));
		PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "PDCaseNo",h.get("PDCaseNo"));
		PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "ICCaseNo",h.get("ICCaseNo"));

	}	

	// Day2: Extracts output of previous test case for input of next test case (2-day COC cases)	 for each test case
	public static void dataExtraction_Household() throws Exception {
		System.out.println("\n_______________________________ DataExtraction_Household: _______________________________ \n");
		HashMap<String,String> h = TestDataPool.rowData;
		//	TFP1_RegressionTest.Magi.AT_701_PCRxPCRxARKIDSAxARKIDSAxHCIPxHCIPxARKIDSA=TOCZEK Nordlinger Chalaban Esquire,7/28/1971,268043101

		// Property file read/write for continuation of test case
		for(int i=1;i<=Integer.parseInt(h.get("householdSize"));i++)
		{
			//	String fullText = PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Input_Network_COC,h.get("stack").replaceAll("day2.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", "")); // From consolidated copy
			String fullText = PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Input_Network,h.get("stack").replaceAll("day2.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", "")); //From environmental copy

			//		String fullText = PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Input,h.get("stack").replaceAll("day2_coc.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", ""));

			String[] individualText = fullText.split(",");

			int j =0;
			/*for (int j2 = 0; j2 < individualText.length; j2++) {

				}*/
			h.put("fullNameSuffix"+i, individualText[j]);j++;
			h.put("dob"+i, individualText[1]);j++;
			h.put("ssn"+i, individualText[2]);j++;
			h.put("PDCaseNo", individualText[3]);j++;
			h.put("ICCaseNo", individualText[4]);j++;


			String fullNameSuffix = h.get("fullNameSuffix"+i);
			String[] names = fullNameSuffix.split(" ");


			h.put("fName"+i, names[0]);
			h.put("lName"+i, names[2]);
			h.put("firstLastName"+i, names[0]+" "+names[2]);
			h.put("fullName"+i, names[0]+" "+names[1]+" "+names[2]);
			//	h.put("caseParticipant"+i, h.get("firstLastName"+i)+" ("+DateUtils.ageCalculator("M/d/yyyy", h.get("dob"+i)+")")); // verify itesms dropdown only
			h.put("caseParticipant"+i, h.get("firstLastName"+i)+" ("+DateUtils.ageCalculator("M/d/yyyy", h.get("dob"+i))+")"); // verify itesms dropdown only

			String x_All="";
			x_All=x_All+"SSN"+i+":"+h.get("ssn"+i) +"\n" + "DOB"+i+":"+h.get("dob"+i) +"\n"+ "First Name"+i+":"+h.get("fName"+i) +"\n"+ "Last Name"+i+":"+h.get("lName"+i) +"\n"+ "Full Name"+i+":"+h.get("fullNameSuffix"+i)+"\n"+ "Case Participant Name"+i+":"+h.get("caseParticipant"+i)+"\n"+ "PDCaseNo"+i+":"+h.get("PDCaseNo"+i)+"\n"+ "ICCaseNo"+i+":"+h.get("ICCaseNo"+i);
			ReportUtils.reportResult("Done", "Data Extraction", x_All);
		}

		TestDataPool.rowData = h;
		//SuperHelper.logInfo("Data Extraction is successful!");

		// Writing back values to output prop file
		PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "fName1",h.get("fName1"));
		PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "lName1",h.get("lName1"));
		PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "ssn1",h.get("ssn1"));
		PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "dob1",h.get("dob1"));
		PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "PDCaseNo",h.get("PDCaseNo"));
		PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "ICCaseNo",h.get("ICCaseNo"));

	}	

	// @USED Day2: Reads output of day1 test case for input of next day2 test case (2-day COC cases) for each test case
	public static void dataExtraction_COC() throws Exception {
		LogUtils.logStart("DataExtraction_COC");
		HashMap<String,String> h = TestDataPool.rowData;
		String fullText;
		fullText = PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Input_Network,h.get("stack").replaceAll("day2.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", "")); // For Non-AccountTransfer scripts - data taken from consolidated copy
		System.out.println("fullText is : "+fullText);
		//String fullText = PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Input,h.get("stack").replaceAll("day2_coc.", "").replaceAll("[_a-zA-Z ]+.*[0-9]+.*[A-Z]+[1-5]", ""));
		String[] individualText_PersonSet = fullText.split(";") ;//Changed to , from ;
		System.out.println("----------" +individualText_PersonSet );
		String[] individualText_Details =  new String[10];
		String[] names = new String[10];

		// Looping between persons and data

		for (int j = 0; j < individualText_PersonSet.length; j++) {
			System.out.println("individualText_PersonSet[j]"+j+" = "+individualText_PersonSet[j]);
			individualText_Details = individualText_PersonSet[j].split(",") ;
			//individualText_Details = fullText.split(",") ;
			System.out.println("individualText_details"+j+" = "+individualText_Details);
			for (int i1 = 0; i1 < individualText_Details.length; i1++) {
				System.out.println("verify what prints here"+individualText_Details[i1]);
			}

			// Putting in HashMap
			h.put("RefNo", individualText_Details[0]);//arw portal
			h.put("userName1", individualText_Details[1]);//arw portal
			h.put("fullNameSuffix"+(j+1), individualText_Details[2]) ;
			h.put("dob"+(j+1), individualText_Details[3]); 
			h.put("ssn"+(j+1), individualText_Details[4]); 
			h.put("PDCaseNo"+(j+1), individualText_Details[5]); 
			h.put("ICCaseNo"+(j+1), individualText_Details[6]); 
			h.put("appDate", individualText_Details[7]);//App date

			names = h.get("fullNameSuffix"+(j+1)).split(" ");
			System.out.println("name is : "+names);

			h.put("fName"+(j+1), names[0]);
			h.put("lName"+(j+1), names[2]);
			h.put("firstLastName"+(j+1), names[0]+" "+names[2]);
			h.put("fullName"+(j+1), names[0]+" "+names[1]+" "+names[2]);
			h.put("caseParticipant"+(j+1), h.get("firstLastName"+(j+1))+" ("+DateUtils.ageCalculator("M/d/yyyy", h.get("dob"+(j+1)))+")"); // verify items dropdown only

			// Writing back values to output prop file
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "RefNo",h.get("RefNo"));//arw portal
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "userName",h.get("userName1"));//arw portal
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "fName"+(j+1),h.get("fName"+(j+1)));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "lName"+(j+1),h.get("lName"+(j+1)));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "ssn"+(j+1),h.get("ssn"+(j+1)));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "dob"+(j+1),h.get("dob"+(j+1)));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "PDCaseNo",h.get("PDCaseNo1"));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "ICCaseNo",h.get("ICCaseNo1"));

		}

		String x_All="";
		for (int i = 1; i <= Integer.parseInt(h.get("householdSize")); i++) {
			x_All=x_All+ "RefNo"+":"+h.get("RefNo")+"userName1"+":"+h.get("userName1")+"\n"+"SSN"+i+":"+h.get("ssn"+i) +"\n" + "DOB"+i+":"+h.get("dob"+i) +"\n"+ "First Name"+i+":"+h.get("fName"+i) +"\n"+ "Last Name"+i+":"+h.get("lName"+i) +"\n"+ "Full Name"+i+":"+h.get("fullNameSuffix"+i)+"\n"+ "Case Participant Name"+i+":"+h.get("caseParticipant"+i)+"\n"+ "PDCaseNo"+i+":"+h.get("PDCaseNo"+i)+"\n"+ "ICCaseNo"+i+":"+h.get("ICCaseNo"+i)+"\n";
		}

		ReportUtils.reportResult("Done", "Data Extraction", x_All);

		TestDataPool.rowData = h;
	}	

	// Recalls test data and puts in hashmap for the passed test case (for which dataSave was called)
	public static void dataRecall( String testcasePath) throws Exception {
		LogUtils.logStart("dataRecall - "+TestDataPool.rowData.get("Environment"));
		HashMap<String,String> h = TestDataPool.rowData;
		String fullText;
		System.out.println("else.................................................");
		fullText = PropertyUtils.propertyFile_Read(Constants.propertyFilePath_Input_Network,testcasePath);  
		String[] individualText_PersonSet = fullText.split(";") ;
		String[] individualText_Details =  new String[10];
		String[] names = new String[10];

		// Looping between persons and data

		for (int j = 0; j < individualText_PersonSet.length; j++) {
			System.out.println("individualText_PersonSet[j]"+j+" = "+individualText_PersonSet[j]);
			individualText_Details = individualText_PersonSet[j].split(",") ;

			for (int i1 = 0; i1 < individualText_Details.length; i1++) {
				System.out.println(individualText_Details[i1]);
			}

			// Putting in HashMap
			h.put("fullNameSuffix"+(j+1), individualText_Details[0]) ;
			h.put("dob"+(j+1), individualText_Details[1]); 
			h.put("ssn"+(j+1), individualText_Details[2]); 
			h.put("PDCaseNo"+(j+1), individualText_Details[3]); 
			h.put("ICCaseNo"+(j+1), individualText_Details[4]); 

			names = h.get("fullNameSuffix"+(j+1)).split(" ");

			h.put("fName"+(j+1), names[0]);
			h.put("lName"+(j+1), names[2]);
			h.put("firstLastName"+(j+1), names[0]+" "+names[2]);
			h.put("fullName"+(j+1), names[0]+" "+names[1]+" "+names[2]);
			h.put("caseParticipant"+(j+1), h.get("firstLastName"+(j+1))+" ("+DateUtils.ageCalculator("M/d/yyyy", h.get("dob"+(j+1)))+")"); // verify items dropdown only

			// Writing back values to output prop file
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "fName"+(j+1),h.get("fName"+(j+1)));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "lName"+(j+1),h.get("lName"+(j+1)));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "ssn"+(j+1),h.get("ssn"+(j+1)));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "dob"+(j+1),h.get("dob"+(j+1)));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "PDCaseNo",h.get("PDCaseNo1"));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_Output, "ICCaseNo",h.get("ICCaseNo1"));
		}

		String x_All="";
		for (int i = 1; i <= Integer.parseInt(h.get("householdSize")); i++) {
			x_All=x_All+"SSN"+i+":"+h.get("ssn"+i) +"\n" + "DOB"+i+":"+h.get("dob"+i) +"\n"+ "First Name"+i+":"+h.get("fName"+i) +"\n"+ "Last Name"+i+":"+h.get("lName"+i) +"\n"+ "Full Name"+i+":"+h.get("fullNameSuffix"+i)+"\n"+ "Case Participant Name"+i+":"+h.get("caseParticipant"+i)+"\n"+ "PDCaseNo"+i+":"+h.get("PDCaseNo"+i)+"\n"+ "ICCaseNo"+i+":"+h.get("ICCaseNo"+i)+"\n";
		}
		ReportUtils.reportResult("Done", "dataRecall: "+testcasePath, x_All);

		TestDataPool.rowData = h;
	}	

}
