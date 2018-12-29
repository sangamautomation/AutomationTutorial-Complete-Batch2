package tests.playground;

import java.util.HashMap;

import utils.DatabaseUtils;
import utils.ExcelUtils;
import utils.KeyboardUtils;
import utils.PDFUtils;
import utils.PropertyUtils;
import utils.ScreenshotUtils;

public class TC_Debugging {

	public static void main(String[] args) throws Exception {

		KeyboardUtils.Key_RightClick();

		PDFUtils.screenshotToPdf("C:/Selenium_Logs/Screenshots/", "screenShot0000.png", "screenShot", true, 0000);
		PDFUtils.screenshotToPdf("C:/Selenium_Logs/Screenshots/", "screenShot0000.png",false);

		String folderPath = "C:\\Utils";
		String path = "C:\\Utils\\Data.properties";

		PropertyUtils.propertyFile_Write(path, "FirstName", "John");	
		PropertyUtils.propertyFile_Write(path, "LastName", "Walkman");	

		String propValue = PropertyUtils.propertyFile_Read(path, "LastName");

		ScreenshotUtils.screenshot(folderPath, 1);
		Thread.sleep(5000);
		ScreenshotUtils.screenshot(folderPath, 2);

		utils.FileSystemUtils.createFolder("C:\\Selenium_Logs\\Screenshots\\MyFolder");
		utils.FileSystemUtils.createFile("C:\\Selenium_Logs\\Screenshots\\MyFolder\\", "MyFile", "txt");

		String datapoolPath = "C:\\AutomationProjects\\SeleniumTutorial\\resource\\TestDataPool_Automation.xls";
		String sheetName = "Automation";
		int header=0;
		int tc=1;

		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tc);

		String firstName1 = rowData.get("firstName1");

		System.out.println("First name = "+ firstName1);

		String  ipAddress = "10.123.123.2";
		String portNumber = "8080";
		String databaseName = "CDSA124";
		String userid = "ShuklaUser";
		String password = "Password@123"; 
		String sql1="Select firstName from PersonalDertails";;
		String input1 = "31";

		String ActualDBResult = DatabaseUtils.databaseValidation_SelectSQL(ipAddress, databaseName, portNumber, sql1, input1, null, null, null, "firstName");
		String ExpectedDBResult = "Shukla";
		DatabaseUtils.Database_Compare(ExpectedDBResult, ActualDBResult, "DB Validation for Person Table");



	}

}
