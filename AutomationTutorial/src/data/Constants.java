package data;

import java.util.Random;

import infrastructure.Configuration;
import utils.DateUtils;
import utils.FileSystemUtils;
import utils.Utils;

/**
 * Description : All Constants
 * 
 * @author Sangam
 */

public class Constants {

	Random rand = new Random();
	public static String workingDir = System.getProperty("user.dir"); //C:\\AutomationProjects\\TestAutomation_Selenium
	//public static String datapoolPath = "C:\\AutomationProjects\\SeleniumTutorial\\resource\\TestDataPool_Automation.xls";

	public static final String automationDatapoolPath = workingDir + "\\resources\\TestDataPool_Automation.xls";

	public static final String CHROMEDRIVER_PATH = "E:\\AutomationProjects\\drivers\\chromedriver.exe";
	public static final String FIREFOXDRIVER_PATH = "C:\\drivers\\geckodriver.exe";

	public static final String filePath_Toolsqa = "\\Users\\sanga\\Pictures\\go2meeting.png";

	public static final String url_Demoaut = "http://www.newtours.demoaut.com/";
	
//	public static String screenshotFolderPath = "C:\\Selenium_Logs\\Screenshots\\";
	public static String screenshotFolderPath = "C:\\Selenium_Logs\\Screenshots\\";
	
	public static final String configPath = workingDir + "/src/config/config.properties";
	public static String configPathHardCoded = "C:/AutomationProjects/SeleniumTutorial/src/config/config.properties";

	public static int counter = 0;
	
	public static String dataPool_Xls = "C:\\AutomationProjects\\SeleniumTutorial\\resource\\TestDataPool_Automation.xls"; 
	public static String dataPool_Xlsx = "C:/Selenium_Logs/TestData/Datapool.xlsx"; // \\=/

	public static String path_PropertyFile_config ="C:/Selenium_Logs/TestData/config.properties";
	
	public static String path_PropertyFile_webservices ="C:\\Selenium_Logs\\WebServices/webserivces.properties";
	public static String path_RequestXMLFile ="C:/Selenium_Logs/WebServices/requests/SampleWebService.xml";
	public static String path_ResponseXMLFile ="C:/Selenium_Logs/WebServices/responses/response.xml";

	public static String propertyFilePath_Input_Network = "\\\\ServerName\\Testers$\\Automation-AutomationProjects\\Datapool\\TestInputData.properties";

	// Environments

	// public static String workingDir = "C:\\AutomationProjects\\TestAutomation_Selenium";
	public static String localConfig = "C://AutomationProjects/config.properties";
	public static String localUserFile = "C://AutomationProjects/jenkinsUser.properties";//Badri
 	// Selenium WebDriver - Execution Log Paths
 	public static String AutomationTest_ProjectLogFolder_Path = "C:\\AutomationProjects\\TestAutomation_Selenium_logs\\";
 	
 	public static String logFolder = createLogFolder(); //UNCOMMENT LATER BADRI
 	//public static String logFolder = "C:\\AutomationProjects\\TestAutomation_Selenium_logs\\AutomationTest_ExecutionLogNOTICETEST"; //UNCOMMENT THE ABOVE LINE AND COMMENT THIS ONE AFTER DONE. BADRI
 		
 	public static String AutomationTest_ExecutionLog_Path = logFolder + "\\";
	public static String propertyFilePath_Captions= AutomationTest_ExecutionLog_Path + "Captions.properties";
	public static String propertyFilePath_TestHeader = AutomationTest_ExecutionLog_Path + "TestHeader.properties";
	public static String additionalScreenshots = AutomationTest_ProjectLogFolder_Path + "AdditionalScreenshots\\" ;
	
	public static String propertyFilePath_Input = AutomationTest_ExecutionLog_Path+"TestInputData.properties";
	public static String propertyFilePath_Output = AutomationTest_ExecutionLog_Path+"TestOutputData.properties";
	
	public final String FF = "Mozilla Firefox", IE = "Internet Explorer", CH = "Chrome";

	public final String LOCAL_CITPortal = "http://localhost:8080/DemoAut";

	// Global Variables

	// public static final String datapool =// workingDir+"\\Datapool\\TestDataPool.xlsx";
 
	public static final String DataPool = Utils.propertyGet(workingDir+"\\src\\config\\config.properties", "TestDataPool");
	public static final String TestDataPool_Network = Utils.propertyGet(workingDir+"\\src\\config\\config.properties", "TestDataPool_Network");
	public static final String TestDataPool_Local = Utils.propertyGet(workingDir+"\\src\\config\\config.properties", "TestDataPool_Local");
	public static final String TestDataPool_DescExp = workingDir+"\\src\\config\\TestCaseDescExp.xml";

	public static final String datapoolPath = Utils.propertyGet(workingDir+"\\src\\config\\config.properties", "datapool");
	public static final String namesDBPath = Utils.propertyGet(workingDir+"\\src\\config\\config.properties", "namesDBPath");

	// New added page textPlease do not re-apply if :you have already submitted an application and have questionsyou are currently receiving Medicaid benefitsyou want to report a change / add a new household memberInstead, please call the Customer Service Center at 1-855-372-1084 or contact your local Automation office
	//public static String newText = "Please do not re-apply if : you have already submitted an application and have questions you are currently receiving Medicaid benefits you want to report a change / add a new household member Instead, please call the Customer Service Center at 1-855-372-1084 or contact your local Automation office.";
	//public static String newText = "Please do not re-apply if :you have already submitted an application and have questionsyou are currently receiving Medicaid benefitsyou want to report a change / add a new household memberInstead, please call the Customer Service Center at 1-855-372-1084 or contact your local Automation office.";
	public static String newText = "Please do not re-apply if : you have already submitted an application and have questions you are currently receiving Medicaid benefits you want to report a change / add a new household member Instead, please call the Customer Service Center at 1-855-372-1084 or contact your local Automation office.";
	
	public static String newEmailText1 = "If you do not have an email address you can go to the home page of one of the popular free email providers* such as Gmail(@gmail.com) or Yahoo Mail(@yahoo.com). From there, click sign up or create an account.";
	public static String newEmailText2 = "Please note that Automation does not endorse any third-party email provider.";
 
	// Primary Applicant's Details
	private final String nameSuffix = Long.toString(System.currentTimeMillis()).substring(7);
	public String username = "ALEX" + getNameSuffix(), firstname1 = "ALEX" + getNameSuffix(), lastname1 = "SMITH" + getNameSuffix(), middlename1 = "M";
	// public String username =
	// "ALEX"+Long.toString(System.currentTimeMillis()).substring(9), firstname1
	// = "ALEX"+Long.toString(System.currentTimeMillis()).substring(9),
	// lastname1 =
	// "SMITH"+Long.toString(System.currentTimeMillis()).substring(9),
	// middlename1 = "M", name1 =
	// "ALEX"+Long.toString(System.currentTimeMillis()).substring(9);
	public String gender1 = "Male", dob1 = "1/1/1980", email = "automation.Selenium@gmail.com";
	public String password = "Alex@123", answer = "Favorite Answer";
		public String employer = "Company", income = "24000", incomeStartDate = "1/1/2015", incomestartdt1 = "1/1/2014", incomeCycle = "Yearly", incomeCycle_W = "Weekly",
			incomeCycle_BW = "Bi-Weekly", incomeCycle_M = "Monthly", incomeCycle_Y = "Yearly";
	// public String ssn1 = util.randomSSN();
	public String ssn1 = "71" + Long.toString(System.currentTimeMillis()).substring(6, 11) + "00";
	
	// Results
	public String tru = "True";
	public String err = "Error";
	public String fal = "False";
	public String pass = "Pass";
	public String passm = "Pass-";
	public String fail = "Fail";
	public String failm = "Fail-";
	public String done = "Done";
	public String none = "";
	public String success = " is successful!";
	public String nsuccess = " is NOT successful!";
	public String empty = "";
	public String r = ".*";
	public String and = " and ";

	// Object Properties

	public String id = ".id";
	public String text = ".text";
	public String ctext = ".contentText";
	public String title = ".title";
	public String name = ".name";
	public String value = ".value";
	public String type = ".type";
	public String tag = ".tag";
	public String cindex = ".classIndex";
	public String cls = ".class";
	public String cname = ".className";
	public String dojo = ".dojoclass";
	public String rstate = ".readyState";

	// Keystroke variables (if you are pressing the specific key then use {})
	public String eq = " = ";

	public String lb = "(";
	public String rb = ")";
	public String q = "\"";
	public String line = "\n";
	public String t = "{TAB}";
	public String e = "{ENTER}";
	public String sh = "{SHIFT}";
	public String alt = "{ALT}";
	public String ctrl = "{CTRL}";
	public String d = "{ExtDelete}";
	public String del = "{ExtDelete}";
	public String s = "{SPACE}";
	public String sp = " ";
	public String b = "{BACKSPACE}", backspace = "{BKSP}";
	public String esc = "{ESCAPE}";
	public String st = "{SHIFT}+{TAB}";
	public String dn = "{ExtDown}", up = "{ExtUp}", left = "{ExtLeft}", right = "{ExtRight}";
	public String home = "{ExtHome}", insert = "{ExtInsert}", end = "{ExtEnd}", pgup = "{ExtPgUp}", pgdn = "{ExtPgDn}";
	public String leftalt = "{LeftAlt}", rightalt = "{RightAlt}", leftaltE = "{LeftAlt}e";
	public String leftctrl = "{LeftCtrl}", rightctrl = "{RightCtrl}";
	public String printscreen = "{PRTSC}";
	public String delay = "{PRTSC}";
	public String selall = "{CTRL}+{a}", copy1 = "{CTRL}+{c}", paste1 = "{CTRL}+{v}";
	public String y = "Yes", n = "No", wages = "Wages and Salaries";
	public String leftbrace = "{SHIFT}+{9}", rightbrace = "{SHIFT}+{0}";
	public String rightclick = "{APPS}";
	public String selectall = "^a", copy = "^c", paste = "^v";
	public String f5 = "{F5}", tilde = "{~}", numlock = "{NUMLOCK}";
	public String home1 = "{HOME}", up1 = "{UP}", leftarrow = "{LEFT}", rightarrow = "{RIGHT}", clear = "{CLEAR}", insert1 = "{INSERT}";
	public String win = "{Win}", rightwin = "{RIGHTWIN}";
	public String a = "a", one = "1";

	String env= "Environment";
	
	public static String propertyFilePath_Log = workingDir + "\\src\\config\\TestCaseLog.properties";
	public static String propertyFilePath_Data = workingDir + "\\src\\config\\TestHarnessData.properties";
	public static String propertyFilePath_User = workingDir + "\\src\\config\\User.properties";
 	public static String propertyFilePath_Env = workingDir + "\\src\\config\\environments.properties";
	public static String propertyFilePath_Config = workingDir + "\\src\\config\\config.properties";
	public static String propertyFilePath_Result = workingDir + "\\src\\config\\result.properties";
	public static String propertyFilePath_Codes = workingDir + "\\src\\config\\codes.properties";
 

	public String[] readHTMLTable;

	public String getNameSuffix() {
		// System.out.println("Working Directory = "+ Constants.workingDir);
		// System.out.println("Name Suffix: "+ nameSuffix);
		return nameSuffix;
	}
	
	public static String createLogFolder(){
		if(!"Yes".equals(new Configuration().getProperty("runJavaReporting"))){
			return "C:\\AutomationProjects\\TestAutomation_Selenium_logs\\AutomationTest_ExecutionLog";
		}
		String newLogFolder = AutomationTest_ProjectLogFolder_Path + getCallerCallerClassName() + "_" + DateUtils.datetime_Now("MMddYYHHmmss");
		FileSystemUtils.createFolder(newLogFolder);
		return newLogFolder;
	}

	public static String getCallerCallerClassName() { 
	    StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
	    String callerClassName = null;
	    for (int i=1; i<stElements.length; i++) {
	        StackTraceElement ste = stElements[i];
	        if (!ste.getClassName().equals(Constants.class.getName())&& ste.getClassName().indexOf("java.lang.Thread")!=0) {
	            if (callerClassName==null) {
	                callerClassName = ste.getClassName();
	            } else if (!callerClassName.equals(ste.getClassName())) {
	                return ste.getClassName();
	            }
	        }
	    }
	    return null;
	 }
	
	public enum State {
		AL("Alabama"), AK("Alaska"), AZ("Arizona"), AR("Arkansas"), CA("California"), CO("Colorado"), CT("Connecticut"), DE("Delaware"), FL("Florida"), GA("Georgia"), HI("Hawaii"), ID(
				"Idaho"), IL("Illinois"), IN("Indiana"), IA("Iowa"), KS("Kansas"), KY("Kentucky"), LA("Louisiana"), ME("Maine"), MD("Maryland"), MA("Massachusetts"), MI("Michigan"), MN(
						"Minnesota"), MS("Mississippi"), MO("Missouri"), MT("Montana"), NE("Nebraska"), NV("Nevada"), NH("New Hampshire"), NJ("New Jersey"), NM("New Mexico"), NY(
								"New York"), NC("North Carolina"), ND("North Dakota"), OH("Ohio"), OK("Oklahoma"), OR("Oregon"), PA("Pennsylvania"), RI("Rhode Island"), SC("South Carolina"), SD(
										"South Dakota"), TN("Tennessee"), TX("Texas"), UT("Utah"), VT("Vermont"), VA("Virginia"), WA("Washington"), WV("West Virginia"), WI("Wisconsin"), WY("Wyoming");

		private String value;

		private State(String value) {
			this.value = value;
		}

		public String getState() {
			return value;
		}

	}

}
