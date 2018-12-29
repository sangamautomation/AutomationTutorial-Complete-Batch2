package utils;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import data.Constants;
import data.TestDataPool;

/**
 * Report Utils
 * @author Sangam
 */
public class ReportUtils {


	public static void reportResult(String verdict_Pass_Fail_Done, String header, String reportComment){

		int counter = Constants.counter;
		Constants.counter++;

		switch (verdict_Pass_Fail_Done) {
		case "Pass":
			System.out.println("PASS ~ Test Step Passed for "+header+ " :: "+ reportComment);
			PropertyUtils.propertyFile_Write(Constants.screenshotFolderPath+"Captions.properties", "Caption"+counter, reportComment);
			ScreenshotUtils.screenshot(Constants.screenshotFolderPath, counter);
			break;
		case "Fail":
			System.out.println("FAIL ~ Test Step FAILED for "+header+ " :: "+ reportComment);
			PropertyUtils.propertyFile_Write(Constants.screenshotFolderPath+"Captions.properties", "Caption"+counter, reportComment);
			ScreenshotUtils.screenshot(Constants.screenshotFolderPath, counter);
			break;
		case "Done":
			System.out.println("Done ~ Test Step is Done for "+header+ " :: "+ reportComment);
			PropertyUtils.propertyFile_Write(Constants.screenshotFolderPath+"Captions.properties", "Caption"+counter, reportComment);
			ScreenshotUtils.screenshot(Constants.screenshotFolderPath, counter);
			break;
		default:
			System.out.println("Please mention either Pass, Fail or Done");
			break;
		}

	}

	public static boolean compare(String ExpectedResult, String ActualResult) {
		LogUtils.log("~~~~~~~~~~~~~~~ Validation ~~~~~~~~~~~~~~~");
		boolean flag = false;
		try {
			//ExpectedResult = ExpectedResult.trim();
			//ActualResult = ActualResult.trim();
			if (ExpectedResult.equals(ActualResult)) {
				flag = true;
				System.out.println("Pass - "+ "ExpectedResult :"+ExpectedResult + " is same as ActualResult: "+ActualResult);
			} else {
				flag = false;
				System.out.println("Fail - "+ "ExpectedResult :"+ExpectedResult + " is not same as ActualResult: "+ActualResult);
			}
		} catch (Exception e) {
			//	e.printStackTrace();
			System.out.println("Enter proper values to compare!");
		}
		return flag;

	}


	/**
	 * 
	 * Compares expected & actual results and Reports
	 * @param Validating
	 * @param ExpectedResult
	 * @param ActualResult
	 * @param ReportComment
	 * @throws Exception
	 */
	public static void compare(String Validating, String ExpectedResult, String ActualResult, String ReportComment) throws Exception {
		if (ExpectedResult == ActualResult || ExpectedResult.equals(ActualResult) || ActualResult.contains(ExpectedResult)) {
			reportResult("Pass", "Verification Point: "+ Validating +  "\n" + "Result = "+ActualResult +"\n", ReportComment);
		} else {
			reportResult("Fail", "Verification Point: "+ Validating + " \n" + "Expected Result: "+ExpectedResult +" != " + "Actual Result: "+ActualResult +"\n",ReportComment);
		}
	}



	// Reports Header

	public static void reportHeader() throws Exception {
		try {
			PropertyUtils.clearProps(Constants.propertyFilePath_TestHeader); //To remove old properties

			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_TestHeader,"TestCase",TestDataPool.rowData.get("stack")); //TestCaseID
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_TestHeader,"TestFullCaseName",TestDataPool.rowData.get("testCaseName")); //TestCaseName
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_TestHeader,"ScenarioDescription",TestDataPool.rowData.get("scenarioDescription"));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_TestHeader,"ExpectedResult",TestDataPool.rowData.get("expectedResult"));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_TestHeader,"Environment",TestDataPool.rowData.get("Environment"));

			//		PropertyUtils.propertyFile_Write(reportHeaderFile,"TestCaseName",TestDataPool.rowData.get("TestCaseName"));
			System.out.println("Header: "+"TestCaseName" + "\n"+TestDataPool.rowData.get("stack"));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public static void reportHeader(String folderPath) throws Exception {

		try {

			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_TestHeader,"TestCase",TestDataPool.rowData.get("TestCaseName"));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_TestHeader,"ScenarioDescription",TestDataPool.rowData.get("scenarioDescription"));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_TestHeader,"ExpectedResult",TestDataPool.rowData.get("expectedResult"));
			PropertyUtils.propertyFile_Write(Constants.propertyFilePath_TestHeader,"Environment",TestDataPool.rowData.get("Environment"));

			//		PropertyUtils.propertyFile_Write(reportHeaderFile,"TestCaseName",TestDataPool.rowData.get("TestCaseName"));
			System.out.println("Header: "+"TestCaseName" + "\n"+"TestCaseName");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Reports results by taking screenshots using robot & writing captions
	 * @param Result_Pass_Fail_Done_None
	 * @param reportHeading
	 * @param reportComment
	 * @throws Exception
	 */
	public static void reportResult_Extended(String Result_Pass_Fail_Done_None, String reportHeading, String reportComment) throws Exception {
		HashMap<String, String> h = TestDataPool.rowData;
		int counter = TestDataPool.counter + 1;
		TestDataPool.counter ++;
		String AutomationTest_ExecutionLog_Path = Constants.AutomationTest_ExecutionLog_Path; //"D:\\AutomationProjects\\TestAutomation_Selenium/screenshots/"

		String sCounter = "0000" + String.valueOf(counter);
		sCounter = sCounter.substring(  sCounter.length()-4, sCounter.length() );

		Thread.sleep(1000);
		reportComment = reportComment.replaceAll("&lt;", "\n&lt;");
		Constants c= new Constants();
		try {
			switch (Result_Pass_Fail_Done_None) {
			case "Pass":
				ReportUtils.screenshot(AutomationTest_ExecutionLog_Path,counter); 
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				System.out.println(DateUtils.timestamp()+" | "+":::PASS::: | "+reportHeading + ":\n"+reportComment);
				PropertyUtils.propertyFile_Write(c.propertyFilePath_Output, "Status_"+reportHeading.replaceAll("\\s", ""), "Pass");
				h.put("Status_"+reportHeading.replaceAll("\\s", ""), "Pass");
				break;
			case "NoticeP":
				//ReportUtils.screenshot(AutomationTest_ExecutionLog_Path,counter); 
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+ reportComment);
				System.out.println(DateUtils.timestamp()+" ~ PASS: "+reportHeading + ":\n"+reportComment);
				break;
			case "ScreenShot":
				ReportUtils.screenshot(AutomationTest_ExecutionLog_Path,counter); 
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+" : \n"+ reportComment);
				System.out.println(DateUtils.timestamp()+" ~ PASS: "+reportHeading + ":\n"+reportComment);
				break;
			case "Pass-":
				// ReportUtils.screenshot(screenshotsPath,counter); 
				//	TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+"\n"+ reportComment);
				TestDataPool.captionsData.put("Caption"+sCounter, "Passm"+"-"+reportHeading+":\n"+ reportComment); // To avoid clash of instr for Pass & Pass- in VBS
				System.out.println(DateUtils.timestamp()+" ~ PASS: "+reportHeading + "\n"+reportComment);
				break;
			case "Fail":
				ReportUtils.screenshot(AutomationTest_ExecutionLog_Path,counter); 
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				System.out.println(DateUtils.timestamp()+" | "+ "<<<<<<<<<<<<<< FAIL >>>>>>>>>>>>> | "+reportHeading + ":\n"+reportComment);
				PropertyUtils.propertyFile_Write(c.propertyFilePath_Output, "Status_"+reportHeading.replaceAll("\\s", ""), "Fail");
				h.put("Status_"+reportHeading.replaceAll("\\s", ""), "Fail");
				break; 
			case "Special":
				ReportUtils.screenshot(AutomationTest_ExecutionLog_Path,counter); 
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				System.out.println(DateUtils.timestamp()+" | "+ "<<<<<<<<<<<<<< SPECIAL >>>>>>>>>>>>> | "+reportHeading + ":\n"+reportComment);
				PropertyUtils.propertyFile_Write(c.propertyFilePath_Output, "Status_"+reportHeading.replaceAll("\\s", ""), "Special");
				h.put("Status_"+reportHeading.replaceAll("\\s", ""), "Special");
				break; 
			case "NoticeF":
				//	ReportUtils.screenshot(AutomationTest_ExecutionLog_Path,counter); 
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+ reportComment);
				System.out.println(DateUtils.timestamp()+" ~ FAIL: "+reportHeading + ":\n"+reportComment);
				break;
			case "Fail-":
				// ReportUtils.screenshot(screenshotsPath,counter); 
				//	TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+"\n"+ reportComment);
				TestDataPool.captionsData.put("Caption"+sCounter, "Failm"+"-"+reportHeading+":\n"+ reportComment); // To avoid clash of instr for Fail & Fail- in VBS
				System.out.println(DateUtils.timestamp()+" ~ FAIL: "+reportHeading + ":\n"+reportComment);
				break;
			case "Done":
				//	ReportUtils.screenshot(screenshotsPath,counter);   // Commenting screenshot for Done, uncomment only if the captions and screenshots are out of sync
				//	TestDataPool.captionsData.put("Caption", Result_Pass_Fail_Done_None+"-"+reportHeading+" : "+ reportComment);
				//TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+" "+ " displayed!"+" : \n"+ reportComment);
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				System.out.println(DateUtils.timestamp()+" | "+"!!!DONE!!! | "+reportHeading + ":\n"+reportComment);
				break;
			case "True": // For repeated actions like toggle expansions - screenshot & caption
				ReportUtils.screenshot(AutomationTest_ExecutionLog_Path,counter); 
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				System.out.println(DateUtils.timestamp()+" ~ TRUE: "+reportHeading + ":\t"+reportComment);
				break;	
			case "Error":
				ReportUtils.screenshot(AutomationTest_ExecutionLog_Path,counter); 
				// TestDataPool.captionsData.put("Caption"+sCounter, "Fail"+"-"+reportHeading+":\n"+ reportComment); // Commented this temporarily to avoid printing the exception details in the pdf log.
				if (!"Fail".equals(TestDataPool.rowData.get("screenShotTaken"))) {
					TestDataPool.captionsData.put("Caption"+sCounter, "Fail" + "-" + "Error occured, please see the screenshot for more details.");
				}
				TestDataPool.rowData.put("Verdict", "Fail");
				System.out.println(DateUtils.timestamp()+" | "+"???ERROR??? | "+reportHeading + " ~> "+reportComment);
				break;	
			case "ExitLoop":
				//	ReportUtils.screenshot(AutomationTest_ExecutionLog_Path,counter); 
				TestDataPool.captionsData.put("Caption"+sCounter, "ExitLoop"+" - "+"Final caption for VBS.");
				System.out.println(DateUtils.timestamp()+" | "+")))ExitLoop((( | "+reportHeading + " # "+reportComment);
				break;	
			case "":
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				System.out.println(DateUtils.timestamp()+" ~ "+reportHeading + " ~ "+reportComment);
				break;	
			case "Screenshot":
				ReportUtils.screenshot(AutomationTest_ExecutionLog_Path,counter); 
				TestDataPool.captionsData.put("Caption"+sCounter, ""+"-"+""+":\n"+ "");//Screenshot only for page 2
				System.out.println(DateUtils.timestamp()+" | "+":::PASS::: | "+reportHeading + ":\n"+reportComment);
				PropertyUtils.propertyFile_Write(c.propertyFilePath_Output, "Status_"+reportHeading.replaceAll("\\s", ""), "Pass");
				h.put("Status_"+reportHeading.replaceAll("\\s", ""), "Pass");
				break;

			default:
				ReportUtils.screenshot(AutomationTest_ExecutionLog_Path,counter); 
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				System.out.println("DONE: "+reportHeading + ":\n"+reportComment);
				break;
			}
		} catch (Exception e) {
			//throw e;
		}

	}


	/**
	 * Reports results by taking screenshots using WebDriver & writing captions
	 * @param driver
	 * @param Result_Pass_Fail_Done_None
	 * @param reportHeading
	 * @param reportComment
	 * @throws Exception
	 */
	public static void reportResult(WebDriver driver,String Result_Pass_Fail_Done_None, String reportHeading, String reportComment) throws Exception {
		int counter = TestDataPool.counter + 1;
		TestDataPool.counter ++;
		String AutomationTest_ExecutionLog_Path = Constants.AutomationTest_ExecutionLog_Path; //"D:\\AutomationProjects\\TestAutomation_Selenium/screenshots/"

		String sCounter = "0000" + String.valueOf(counter);
		sCounter = sCounter.substring(  sCounter.length()-4, sCounter.length() );

		Thread.sleep(1000);
		reportComment = reportComment.replaceAll("&lt;", "\n&lt;");
		Constants c= new Constants();
		try {
			switch (Result_Pass_Fail_Done_None) {
			case "Pass":
				ReportUtils.screenshot(driver,AutomationTest_ExecutionLog_Path,counter,".png");  //if .jpg - replace all png by jpg in vbs
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				System.out.println(DateUtils.timestamp()+" | "+":::PASS::: | "+reportHeading + ":\n"+reportComment);
				PropertyUtils.propertyFile_Write(c.propertyFilePath_Output, "Status_"+reportHeading.replaceAll("\\s", ""), "Pass");
				TestDataPool.rowData.put("Status_"+reportHeading.replaceAll("\\s", ""), "Pass");
				break;
			case "NoticeP":
				//ReportUtils.screenshot(AutomationTest_ExecutionLog_Path,counter); 
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+ reportComment);
				System.out.println(DateUtils.timestamp()+" ~ PASS: "+reportHeading + ":\n"+reportComment);
				break;
			case "ScreenShot":
				ReportUtils.screenshot(driver,AutomationTest_ExecutionLog_Path,counter, ".png"); 
				//	TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+" : \n"+ reportComment);
				System.out.println(DateUtils.timestamp()+" ~ PASS: "+reportHeading + ":\n"+reportComment);
				break;
			case "Pass-":
				// ReportUtils.screenshot(screenshotsPath,counter); 
				//	TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+"\n"+ reportComment);
				TestDataPool.captionsData.put("Caption"+sCounter, "Passm"+"-"+reportHeading+":\n"+ reportComment); // To avoid clash of instr for Pass & Pass- in VBS
				System.out.println(DateUtils.timestamp()+" ~ PASS: "+reportHeading + "\n"+reportComment);
				break;
			case "Fail":
				ReportUtils.screenshot(driver,AutomationTest_ExecutionLog_Path,counter,".png"); 
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				System.out.println(DateUtils.timestamp()+" | "+ "<<<<<<<<<<<<<< FAIL >>>>>>>>>>>>> | "+reportHeading + ":\n"+reportComment);
				PropertyUtils.propertyFile_Write(c.propertyFilePath_Output, "Status_"+reportHeading.replaceAll("\\s", ""), "Fail");
				TestDataPool.rowData.put("Status_"+reportHeading.replaceAll("\\s", ""), "Fail");
				break; 
			case "NoticeF":
				//	ReportUtils.screenshot(AutomationTest_ExecutionLog_Path,counter); 
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+ reportComment);
				System.out.println(DateUtils.timestamp()+" ~ FAIL: "+reportHeading + ":\n"+reportComment);
				break;
			case "Fail-":
				// ReportUtils.screenshot(screenshotsPath,counter); 
				//	TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+"\n"+ reportComment);
				TestDataPool.captionsData.put("Caption"+sCounter, "Failm"+"-"+reportHeading+":\n"+ reportComment); // To avoid clash of instr for Fail & Fail- in VBS
				System.out.println(DateUtils.timestamp()+" ~ FAIL: "+reportHeading + ":\n"+reportComment);
				break;
			case "Done":
				//	ReportUtils.screenshot(screenshotsPath,counter);   // Commenting screenshot for Done, uncomment only if the captions and screenshots are out of sync
				//	TestDataPool.captionsData.put("Caption", Result_Pass_Fail_Done_None+"-"+reportHeading+" : "+ reportComment);
				//TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+" "+ " displayed!"+" : \n"+ reportComment);
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				System.out.println(DateUtils.timestamp()+" | "+"!!!DONE!!! | "+reportHeading + ":\n"+reportComment);
				break;
			case "True": // For repeated actions like toggle expansions - screenshot & caption
				ReportUtils.screenshot(driver,AutomationTest_ExecutionLog_Path,counter,".png");
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				System.out.println(DateUtils.timestamp()+" ~ TRUE: "+reportHeading + ":\t"+reportComment);
				break;	
			case "Error":
				ReportUtils.screenshot(driver,AutomationTest_ExecutionLog_Path,counter,".png");
				if (!"Fail".equals(TestDataPool.rowData.get("screenShotTaken"))) {
					TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				}
				TestDataPool.rowData.put("Verdict", "Fail");
				System.out.println(DateUtils.timestamp()+" | "+"???ERROR??? | "+reportHeading + " ~> "+reportComment);
				break;	
			case "ExitLoop":
				//	ReportUtils.screenshot(driver,AutomationTest_ExecutionLog_Path,counter); 
				TestDataPool.captionsData.put("Caption"+sCounter, "ExitLoop"+" - "+"Final caption for VBS.");
				System.out.println(DateUtils.timestamp()+" | "+")))ExitLoop((( | "+reportHeading + " # "+reportComment);
				break;	
			case "":
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				System.out.println(DateUtils.timestamp()+" ~ "+reportHeading + " ~ "+reportComment);
				break;	
			default:
				ReportUtils.screenshot(driver,AutomationTest_ExecutionLog_Path,counter,".png");
				TestDataPool.captionsData.put("Caption"+sCounter, Result_Pass_Fail_Done_None+"-"+reportHeading+":\n"+ reportComment);
				System.out.println("DONE: "+reportHeading + ":\n"+reportComment);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}


	/* USED-This method will take a screen shot of current screen and store it in
	  ...\ScreenShots\screenShot.png file with passed counter number as suffix.
	 */
	public static void screenshot(String folderPath, int counter) {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		Rectangle screenRect = new Rectangle(screenSize);
		// Create screen shot
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		String sCounter = "0000" + String.valueOf(counter);
		sCounter = sCounter.substring(  sCounter.length()-4, sCounter.length() );

		BufferedImage image = robot.createScreenCapture(screenRect);
		// Save captured image to PNG file
		try {
			// create the ScreenShots Directory if does not exists.
			File theDir = new File(folderPath);
			if (!theDir.exists()) {
				theDir.mkdirs();
			}

			ImageIO.write(image, "png", new File(folderPath + "screenShot"+sCounter+".png"));
			TestDataPool.rowData.put("screenShotTaken", "Pass");

		} catch (IOException e) {
			System.out.println(DateUtils.timestamp()+" ~ "+"- Screen shot NOT saved: "+"screenShot"+sCounter+".png");
			e.printStackTrace();
			TestDataPool.rowData.put("screenShotTaken", "Fail");
		}
		// Give feedback
		System.out.println(DateUtils.timestamp()+" ~ "+"+ Screen shot saved (" + image.getWidth() + " x "
				+ image.getHeight() + " pixels) to file: " + folderPath + "screenShot"+sCounter+".png");
		//StringUtils.writeDiagonal("IMAGE", "                      ");
		//GraphicUtils.drawBox(15, 85);
	}

	/*   This method will take a screen shot of webDriver (browser only) and store it in
	  parameterized location as .jpg file with passed counter number as suffix. This way we can run parallel tests and take screenshots simultaneously!
	 */ 
	public static void screenshot(WebDriver driver, String folderPath, int counter, String extension) throws IOException, InterruptedException {

		//WebDriver driver = new FirefoxDriver();
		//driver.get("http://www.google.com/");

		//	System.out.println("Taking screenshot using webdriver....");
		//File scrFile =driver.findElement(By.xpath(Registration.link_Register)).getScreenshotAs(OutputType.FILE);
		File scrFile = null; // Pass source file as parameter
		FileUtils.copyFile(scrFile, new File("C:\\tmp\\screenshot.png"));


		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		Rectangle screenRect = new Rectangle(screenSize);
		// Create screen shot
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}

		String sCounter = "0000" + String.valueOf(counter);
		sCounter = sCounter.substring(  sCounter.length()-4, sCounter.length() );

		//		BufferedImage image = robot.createScreenCapture(screenRect);
		// Save captured image to JPG file
		try {
			// create the ScreenShots Directory if does not exists.
			File theDir = new File(folderPath);
			if (!theDir.exists()) {
				theDir.mkdirs();
			}

			//	ImageIO.write(image, "jpg", new File(folderPath + "/screenShot"+sCounter+".jpg"));

			Thread.sleep(1000);
			try {
				File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				// FileUtils.copyFile(scrFile, new File("D:\\tmp\\screenshot.jpg"));
				FileUtils.copyFile(scrFile1, new File(folderPath + "screenShot"+sCounter+extension));//org.apache.commons.io.
				System.out.println("+ Screenshot w/ driver saved @ "+ folderPath + "screenShot"+sCounter+extension);
				TestDataPool.rowData.put("screenShotTaken", "Pass");
			} catch (Exception e) {
				TestDataPool.rowData.put("screenShotTaken", "Fail");
				System.out.println("- Screen shot NOT saved: "+"screenShot"+sCounter+extension);
			}

		} catch (Exception e) {
			System.out.println("- Screen shot NOT saved: "+"screenShot"+sCounter+extension);
			TestDataPool.rowData.put("screenShotTaken", "Fail");
			e.printStackTrace();
		}
		// Give feedback
		//	System.out.println("+ Screenshot w/ driver saved @ "+ folderPath + "screenShot"+sCounter+extension); //Printing saved even if not saved
	}


	public static void compareNreport(String Validating, String ExpectedResult, String ActualResult,
			String ReportComment) throws Exception {
		Constants c = new Constants();
		if (ExpectedResult.contains(ActualResult)
				|| ExpectedResult.equals(ActualResult) || ActualResult.contains(ExpectedResult) ) {
			System.out.println(ReportComment + " : " + ExpectedResult + " & " + ActualResult + " = " +  "Pass");
			//			SuperHelper.logTestResult(
			//					"<font size=2 face=Tahoma Color=Green>" + Validating + " Verification Point: Pass</font>",
			//					true, ReportComment);
			reportResult(c.passm, Validating + " Verification Point: Pass \n" + ReportComment, c.empty);
		} else {
			System.out.println(ReportComment + " : " + ExpectedResult + " & " + ActualResult + " = " + " Fail");
			//			SuperHelper.logTestResult(
			//					"<font size=2 face=Tahoma Color=Red>" + Validating + " Verification Point: Fail</font>",
			//					false, ReportComment);
			reportResult(c.failm, Validating + " Verification Point: Fail \n" + ReportComment, c.empty);
		}

	}
	public void compareNoScreenShot(String Validating, String ExpectedResult, String ActualResult,
			String ReportComment) throws Exception {
		Constants c = new Constants();
		if (ExpectedResult == ActualResult
				|| ExpectedResult.equals(ActualResult)) {
			System.out.println(ReportComment + " Pass");
			reportResult(c.passm, Validating + " Verification Point: Pass \n" + ReportComment, c.empty);
		} else {
			System.out.println(ReportComment + " Fail");
			reportResult(c.failm, Validating + " Verification Point: Fail \n" + ReportComment, c.empty);
		}
	}

	public static void deleteFiles(File folder) throws IOException {
		File[] files = folder.listFiles();
		for(File file: files){
			if(file.isFile()){
				String fileName = file.getName();
				boolean del= file.delete();
				System.out.println(fileName + " : got deleted? " + del);
			}else if(file.isDirectory()) {
				deleteFiles(file);
			}
		}
	}


}
