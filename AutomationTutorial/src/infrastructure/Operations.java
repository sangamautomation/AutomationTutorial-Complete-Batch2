package infrastructure;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.TestDataPool;
import utils.DateUtils;
import utils.KeyboardUtils;
import utils.LogUtils;
import utils.ReportUtils;
import utils.StringUtils;

/**
 * Description : All Selenium WebDriver Infrastructure Level Functions
 * 
 * @author Sangam
 */
public class Operations{

	/**
	 * This method will click on Button or Link with the given xpathLocator
	 * @param driver
	 * @param xpathLocator
	 */
	public void clickLink(WebDriver driver, String xpathLocator) {
		try {
			driver.findElement(By.xpath(xpathLocator)).click(); //"//span[contains(@id, 'next_label')]"
			//log("+ OK - clickLinkOrButton - "+ xpathLocator);
			logOk("clickLink - "+ xpathLocator);
		} catch (Exception e) {
			logNotOk(" clickLink - "+ xpathLocator);
			e.printStackTrace();
			// throw e;
		}
	}	

	/**
	 * This method will click on Button or Link with the given text
	 * @param driver
	 * @param linkText
	 */
	public void clickLinkByText(WebDriver driver, String linkText) {
		try {
			driver.findElement(By.linkText(linkText)).click(); 
			log("+ OK - clickLinkByText - "+ linkText);
		} catch (Exception e1) {
			try {
				driver.findElement(By.xpath("//*[text()=\""+linkText + "\"]")).click();     
			} catch (Exception e) {
				log("- NOTOK - clickLinkByText - "+ linkText);
				e.printStackTrace();
				throw e;
			}
		}
	}

	// clickLinkByTitle partial match
	public static void clickLinkByTitle(WebDriver driver, String xpathLocatorTitle) throws Exception
	{
		try
		{
			//switchToFrame(driver, xpathLocator);
			driver.findElement(By.xpath("//*[contains(@title,'"+xpathLocatorTitle+"')]")).click(); //   \"  = ' (Use single quote or double quote with escape char)
			logOk("clickLinkByTitle - "+ xpathLocatorTitle);
		}catch (Exception e)
		{
			logNotOk("clickLinkByTitle - "+ xpathLocatorTitle);
			e.printStackTrace();
			throw e;
		}
		finally{

		}

		//	throw new Exception("Unable to find section: " + xpathLocatorTitle);
	}
	/**
	 * This method will click on Radio Button with the given xpathLocator
	 * @param driver
	 * @param xpathLocator
	 */
	public void clickRadiobutton(WebDriver driver, String xpathLocator) {
		try {
			driver.findElement(By.xpath(xpathLocator)).click(); //"//span[contains(@id, 'next_label')]"
			logOk("clickRadioButton - "+ xpathLocator);
		} catch (Exception e) {
			logNotOk("clickRadioButton - "+ xpathLocator);
			e.printStackTrace();
			throw e;
		}
	}	


	/**
	 * This method will switch to a frame before accessing an object with the given xpathLocator
	 * @param driver
	 * @param xpathLocator
	 * @throws InterruptedException 
	 */
	public void switchToFrame(WebDriver driver, String xpathLocator) throws InterruptedException {
		Thread.sleep(1000);
		try {
			driver.switchTo().frame(driver.findElement(By.xpath(xpathLocator))); //GettingStarted.iframeMainContent
			logOk("switchToFrame - "+ xpathLocator);
		} catch (Exception e) {
			logNotOk("switchToFrame - "+ xpathLocator);
			e.printStackTrace();			
			throw e;
		}
	}

	/**
	 * This method will switch to a alert with the given xpathLocator
	 * @param driver
	 * @param xpathLocator
	 * @throws InterruptedException 
	 */
	public void switchToAlert(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		try {
			driver.switchTo().alert().accept();// Click OK
			logOk("switchToAlert -");
		} catch (Exception e) {
			logNotOk("switchToAlert - ");
			e.printStackTrace();			
		}
	}

	/**
	 * This method will wait for an object implicitly for a given timeout period in sec
	 * @param driver
	 * @param timeout_Seconds
	 */
	public void waitImplicitly(WebDriver driver, int timeout_Seconds) {
		try {
			driver.manage().timeouts().implicitlyWait(timeout_Seconds, TimeUnit.SECONDS);
			log("--- waitImplicitly - "+ timeout_Seconds + "Sec.");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


	/**
	 * This method will wait implicitly for the specified time in seconds
	 * @param driver
	 * @param maxTimeOutInSecond
	 */
	public void wait(WebDriver driver, int maxTimeOutInSecond){
		try {
			driver.manage().timeouts().implicitlyWait(maxTimeOutInSecond, TimeUnit.SECONDS);
			LogUtils.log("Waiting implicitly for "+maxTimeOutInSecond + " seconds");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will wait explicitly for the specified time in seconds until the specified element is visible
	 * @param driver
	 * @param maxTimeOutInSecond
	 */
	public void waitExplicitly(WebDriver driver, int maxTimeOutInSeconds, String xpathOfWaitingElement){
		try {
			WebDriverWait myWait = new WebDriverWait(driver, maxTimeOutInSeconds);
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOfWaitingElement)));
			LogUtils.log("Waiting explicitly for "+maxTimeOutInSeconds + " seconds" + " for "+ xpathOfWaitingElement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will wait explicitly for the specified time in seconds until the specified text in element is present in html page or table element
	 * @param driver
	 * @param maxTimeOutInSecond
	 * @param expectedText
	 */
	public void waitExplicitly(WebDriver driver, int maxTimeOutInSeconds, String xpathOfWaitingElement, String expectedText){
		try {
			WebDriverWait myWait = new WebDriverWait(driver, maxTimeOutInSeconds);
			myWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath(xpathOfWaitingElement)), expectedText));

			LogUtils.log("Waiting explicitly for "+maxTimeOutInSeconds + " seconds" + " in "+ xpathOfWaitingElement+ " for "+ expectedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will switch to default content before exiting the page with the given xpathLocator
	 * @param driver
	 * @param xpathLocator
	 */
	public void switchToDefault(WebDriver driver) {
		try {
			driver.switchTo().defaultContent(); // Before entering or/and exiting the page
			log("+ switchToDefault");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method will return true if the passed testData is contained in the element with the given xpathLocator
	 * @param driver
	 * @param xpathLocator
	 */
	public boolean containsText(WebDriver driver, String xpathLocator, String testData ) {
		try {
			boolean flag = false;
			flag=	driver.findElement(By.xpath(xpathLocator)).getText().contains(testData); //h2[contains(@id, 'pageTitleHeading')]"
			System.out.println("- containsText - "+ testData + " - "+flag+ " - "+ xpathLocator );
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method will enter text in textbox with the given xpathLocator
	 * @param driver
	 * @param xpathLocator
	 * @throws InterruptedException 
	 * @throws Exception 
	 */
	public void setText(WebDriver driver, String xpathLocator, String testData) throws InterruptedException {
		Thread.sleep(100);
		try {
			//	driver.findElement(By.xpath(xpathLocator)).click();
			driver.findElement(By.xpath(xpathLocator)).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath(xpathLocator)).sendKeys(testData); //"//input[contains(@title, 'Is anyone in the household blind')]" ~ h.get("ai_Blind")
			logOk("-> setText : "+ xpathLocator + " = "+ testData );
		} catch (Exception e) {
			logNotOk("WebElement - "+ xpathLocator + " does not exist! Moving on...");
			e.printStackTrace();
			//		throw e;
		}
	}

	/**
	 * This method will click on Button or Link with the given xpathLocator's partial ID value
	 * @param driver
	 * @param idLocator
	 */
	public void setTextById(WebDriver driver, String idLocator, String testData) {
		try {
			//driver.findElement(By.id(idLocator)).clear();
			//	driver.findElement(By.id(idLocator)).sendKeys(testData); // "__o3id8" ~ h.get("ai_Blind")
			driver.findElement(By.xpath("//input[contains(@id,'"+idLocator+"')]")).clear(); 
			driver.findElement(By.xpath("//input[contains(@id,'"+idLocator+"')]")).sendKeys(testData); //   \"  = ' (Use single quote or double quote with escape char)
			log("- setTextById - "+ testData + " - "+ idLocator );
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void setTextInDropdown(WebDriver driver, String xpathLocator, String testData) throws InterruptedException {
		Thread.sleep(100);
		try {
			driver.findElement(By.xpath(xpathLocator)).clear();
			driver.findElement(By.xpath(xpathLocator)).sendKeys(testData); //"//input[contains(@title, 'Is anyone in the household blind')]" ~ h.get("ai_Blind")
			//driver.findElement(By.xpath(xpathLocator)).sendKeys(Keys.TAB);
			logOk("- setText - "+ testData + " - "+ xpathLocator );
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	// setTextByTitle partial match (Fast method - For Broker Method without isWidgetExists which takes time)
	public static void setTextByTitle(WebDriver driver, String xpathLocatorTitle,String testData) throws Exception
	{
		try
		{
			//switchToFrame(driver, xpathLocator);
			driver.findElement(By.xpath("//input[contains(@title,'"+xpathLocatorTitle+"')]")).clear();
			driver.findElement(By.xpath("//input[contains(@title,'"+xpathLocatorTitle+"')]")).sendKeys(testData); //   \"  = ' (Use single quote or double quote with escape char)
			// //input[contains(@title,\"Is anyone outside this household expected to enter\")]
			// "//input[contains(@title,'"+Is anyone outside this household expected to enter+"')]"
			logOk("setTextByTitle - "+ xpathLocatorTitle + " - "+ testData );
		}catch (Exception ex)
		{
			log("-ESC setTextByTitle - "+ xpathLocatorTitle + " - "+ testData );//Element does not exist, moving on..
			//	ex.printStackTrace(); //no need to print stack trace info as it is meant for trying and moving on.. use setText for printing stack trace as regular function
			//	throw ex;
		}

		//	throw new Exception("Unable to find section: " + textFieldTitle);
	}

	/**
	 * This method will select dropdown or combobox with the given xpathLocator
	 * @param driver
	 * @param xpathLocator
	 * WORKS
	 * @throws Exception 
	 */
	public void selectDropdown(WebDriver driver, String xpathLocator,  String value) throws Exception {
		try {
			WebElement ele= driver.findElement(By.xpath(xpathLocator));
			Select select = new Select(ele); //select webelement -dropdown : "//select[@id='periodId']"
			Thread.sleep(1000);
			//select.selectByValue(value); //   select particaular option
			select.selectByVisibleText(value);
			log("+ OK - selectDropdown - " + " - "+ xpathLocator );
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	public void selectDropdown(WebDriver driver, String xpathLocator,  int index) throws Exception {
		try {
			//driver.findElement(By.xpath(xpathLocator)).click(); //"//span[contains(@title, 'next_label')]"
			//	WebElement ele= driver.findElement(By.xpath(xpathLocator));
			//	log("element="+ele.toString());
			Select select = new Select(driver.findElement(By.xpath(xpathLocator))); //select webelement -dropdown : "//select[@id='periodId']"
			Thread.sleep(1000);
			//		 select.selectByVisibleText(value); //   select particaular option
			select.selectByIndex(index);
			//			select.selectByIndex(index);
			log("+ OK - selectDropdown - " + " - "+ xpathLocator );
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}




	/**
	 * This method will return text of an object with the given xpathLocator
	 * @param driver
	 * @param xpathLocator
	 */
	public String getText(WebDriver driver, String xpathLocator) {
		try {
			String objectText	= driver.findElement(By.xpath(xpathLocator)).getText(); //"//span[contains(@id, 'next_label')]"
			log("- getText - "+ xpathLocator + " = "+ objectText.toString() );
			return objectText.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// Checks if the text exists in a page and returns true/false
	public boolean  isTextExists(WebDriver driver, String xpathLocator, String checkingText) {
		boolean flag;
		log("isTextExists .....................................................................");
		try {
			String objectText	= driver.findElement(By.xpath(xpathLocator)).getText(); //"//span[contains(@id, 'next_label')]"
			//		if (objectText.matches(checkingText))
			//		if (objectText.contains(checkingText))
			if (objectText.indexOf(checkingText) >=0)
			{
				flag = true;
				//log("Text - " + checkingText	+ " exists in the page.");
				log("- isTextExists - "+ checkingText + " - "+flag+ " - "+ xpathLocator );
			}
			else 
			{
				flag = false;
				//log("The text - " + checkingText + " does not exist in the page.");
				log("- isTextExists - "+ checkingText + " - "+flag+ " - "+ xpathLocator );
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		finally {
		}

		return flag;
	}

	// Checks if the text exists in a page and reports as Pass
	public void  reportTextExists(WebDriver driver, String xpathLocator, String checkingText) throws Exception {
		try {		 
			log("reportTextExists_Pass .....................................................................");
			String objectText	= driver.findElement(By.xpath(xpathLocator)).getText(); //"//span[contains(@id, 'next_label')]"
			//		if (objectText.matches(checkingText))
			//		if (objectText.contains(checkingText))
			if (objectText.indexOf(checkingText) >=0)
			{
				ReportUtils.reportResult("Pass","Text Validation", "The text - "+checkingText +" Exists!");
				//log("Text - " + checkingText	+ " exists in the page.");
				log("OK- reportTextExists_Pass - "+ checkingText + " - "+ " - "+ xpathLocator );
			}
			else 
			{
				ReportUtils.reportResult("Fail","Text Validation", "The text - "+checkingText +" Exists!");
				//log("Text - " + checkingText	+ " does not exist in the page.");
				log("NOTOK- reportTextExists_Pass - "+ checkingText + " - "+ " - "+ xpathLocator );
				throw new Exception("Text Validation Failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	// Checks if the text exists in a page and reports as Pass
	public void  reportFailIfTextExists(WebDriver driver, String xpathLocator, String checkingText) throws Exception {
		try {
			log("reportTextExists_Fail .....................................................................");
			String objectText	= driver.findElement(By.xpath(xpathLocator)).getText(); //"//span[contains(@id, 'next_label')]"
			//		if (objectText.matches(checkingText))
			//		if (objectText.contains(checkingText))
			if (objectText.indexOf(checkingText) >=0)
			{
				ReportUtils.reportResult("Fail","Err Validation", "The error message - "+checkingText +" Exists!");
				//log("Text - " + checkingText	+ " exists in the page.");
				log("NOT OK- reportTextExists_Fail - "+ checkingText + " - "+ " - "+ xpathLocator );
				throw new Exception("Err Validation Failed");
			}
			else 
			{
				// No need to print non existence of error message in PDF log.
				//ReportUtils.reportResult("Pass","Err Validation", "The error message - "+checkingText +" does NOT Exists!");  //Cant use word Error as vbs uses it for abort 
				//log("Text - " + checkingText	+ " does not exist in the page.");
				log("OK - reportTextExists_Fail - "+ checkingText + " - "+ " - "+ xpathLocator );
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//	System.exit(0); // Abort java processexecution, but doesn't work here.. exit in finally block in tc itself

		}
	}
	/**
	 * This method will return WebElement with the given idText
	 * @param driver
	 * @param idText
	 */
	public WebElement getWebElement(WebDriver driver, String idText) {
		try {
			WebElement we = driver.findElement(By.id(idText)); //"iframe-Automation_ModalDialog_1"
			System.out.println("- getWebElement - "+ we.toString() + " - "+ idText );
			return we;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method will return WebElement with the given xpath
	 * @param driver
	 * @param idText
	 */
	public WebElement webElement(WebDriver driver, String xPath) {
		try {
			WebElement we = driver.findElement(By.xpath(xPath)); //"iframe-Automation_ModalDialog_1"
			System.out.println("- getWebElement - "+ we.toString() + " - "+ xPath );
			return we;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


	/**
	 * This method will click checkbox with the given xpathLocator
	 * @param driver
	 * @param xpathLocator
	 */
	public void clickCheckbox(WebDriver driver, String xpathLocator) {
		try {
			driver.findElement(By.xpath(xpathLocator)).click(); //"//span[contains(@id, 'next_label')]"
			System.out.println("- clickCheckbox - " + " - "+ xpathLocator );
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method will click on Visible Widget with the given xPathVar
	 * @param driver
	 * @param xPathVar
	 */
	public void clickVisibleWidget(WebDriver driver, String xPathVar) {
		log("clickVisibleWidget......");
		java.util.List<WebElement> ElementList = driver.findElements(By
				.xpath(xPathVar));
		log("No of Widgets found:" + ElementList.size());
		for (Iterator<WebElement> iterator = ElementList.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			try {
				if (webElement.isDisplayed()) {
					webElement.click();
					log("Widget clicked:" + xPathVar);
					break;
				} else {
					log("Widget Not visible.");
				}
			} catch (Exception e) {
				log("Could not click, trying with next.");
				// logic will try to click on next widget.
			}
		}
	}

	/**
	 * 	This method will click on Button or Link with the given partial link text after it becomes clickable
	 * @param driver
	 * @param timeSeconds
	 * @param partialLinkText
	 */
	@SuppressWarnings("unused")
	private void clickVisibleLink(WebDriver driver, int timeSeconds, String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))); //Locate element by xpath after condition is met
			System.out.println("+ OK - clickLinkByText - "+ xpath);

		} catch (Exception e) {
			System.out.println("- NOTOK - clickLinkByText - "+ xpath);
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * Returns the string value inside the widget with given xPath
	 * @param driver
	 * @param xPathVar
	 * @return text inside a widget
	 */
	public String getWidgetText(WebDriver driver, String xPathVar){
		StringBuilder sb = new StringBuilder();
		try {
			java.util.List<WebElement> ElementList = driver.findElements(By.xpath(xPathVar));
			for (Iterator<WebElement> iterator = ElementList.iterator(); iterator.hasNext();) {
				WebElement webElement = (WebElement) iterator.next();
				sb.append(webElement.getText());
				log("->OK getWidgetText : "+ xPathVar + " = "+ sb.toString() );
			}
		} catch (Exception e) {
			log("->NOK getWidgetText did NOT work : "+ xPathVar + " = "+ sb.toString() );
			sb.append("-> getWidgetText -- Widget Not found.");
		}
		return sb.toString();
	} 

	/**
	 * Clicks on the Toggle button
	 * @param driver
	 * @throws Exception 
	 */
	public void clickToggleExpand(WebDriver driver) throws Exception {
		java.util.List<WebElement> ElementList=driver.findElements(By.xpath("//tbody//a[@title='Toggle']"));
		log("Number of Toggle buttons found:" + ElementList.size());
		for (Iterator<WebElement> iterator = ElementList.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			webElement.click();
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			ReportUtils.reportResult("True","Toggle Expansion", "");
			webElement.click();
		}
	}


	/**
	 * Clicks on the Toggle button & Captures Value
	 * @param driver
	 * @throws Exception 
	 * 
	 * Ex Calling:  	String[] xPaths = {"Communications.link_Toggle","",""};
	 *                  sd.clickToggleExpandCapture(driver, xPaths);
	 */
	@SuppressWarnings("null")
	public void clickToggleExpandCapture(WebDriver driver, String[] xPaths) throws Exception {
		//		String[] x =  new String[16]; // max 16 notices
		//	HashMap<String,String> fileRefID = null;
		int i = 1;			
		int counter=0;

		java.util.List<WebElement> ElementList = driver.findElements(By.xpath(xPaths[0]));//xpath_Toggle   //"//tbody//a[@title='Toggle']"
		java.util.List<WebElement> frameList = driver.findElements(By.xpath(xPaths[1]));//"//iframe[contains(@title,'View Communication')]"
		Iterator<WebElement> iteratorFrame = frameList.iterator();

		log("frames count = "+ frameList.size()); //frames count = 0

		log("Number of Toggle buttons found:" + ElementList.size());
		TestDataPool.rowData.put("noOfNoticeFileRef", Integer.toString(ElementList.size())); 

		for (Iterator<WebElement> iterator = ElementList.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			webElement.click();
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//	ReportUtils.reportResult("True","Toggle Expansion", "");
			//switchToDefault(driver);
			//		WebElement webElementFrame = (WebElement) iteratorFrame.next();
			//		driver.switchTo().frame(webElementFrame); // i starts from 1
			//	switchToFrame(driver, "//iframe[contains(@title,'View Communication')]");

			//switchToFrame(driver, "//iframe[contains(@title,'View Communication') and contains(@src,'"+"_"+counter+"')]");    //iframe[contains(@title,'View Communication') and  contains(@src,'_1')]
			switchToFrame(driver,xPaths[2]); //Communications.iframe_outer(counter)   //iframe[contains(@title,'View Communication') and  contains(@src,'_1')]

			//	driver.findElement(By.xpath("//*[text()=\""+linkText + "\"]")).click();     
			//	driver.findElement(By.xpath("//*[contains(@title,'"+xpathLocatorTitle+"')]")).click(); //   \"  = ' (Use single quote or double quote with escape char)
			counter++;
			//	TestDataPool.rowData.put("NoticeFileRef" + i, getWidgetText(driver, xPathVar));//TestDataPool.rowData.get("NoticeFileRef"+j)
			//			x[i] = getWidgetText(driver, xPathVar);
			log("noOfNoticeFileRef "+TestDataPool.rowData.get("noOfNoticeFileRef"));

			//	for (int j = 1; j <= Integer.parseInt(TestDataPool.rowData.get("noOfNoticeFileRef")) ; j++) {
			TestDataPool.rowData.put("NoticeFileRef" + i, getWidgetText(driver, xPaths[03]));//xPathVar_CapturingText TestDataPool.rowData.get("NoticeFileRef"+j)
			TestDataPool.rowData.put("NoticeCaseMember" + i, getWidgetText(driver, xPaths[04]));//Communications.text_CaseMember

			log("NoticeFileref"+i+" = "+TestDataPool.rowData.get("NoticeFileRef"+i));
			ReportUtils.reportResult("Pass", "NoticeFileRef"+i, TestDataPool.rowData.get("NoticeFileRef"+i));
			//}

			//log("x"+i+" = "+ x[i]);
			i++;

			driver.switchTo().parentFrame();
			//	switchToFrame(driver, xPathIframe2); //to switch to outer frame
			webElement.click();
		}

	}

	public void contextClick_Open(WebDriver driver, String xpathLocator) throws AWTException, InterruptedException {

		log("Downloading file....");
		WebElement Webelement = driver.findElement(By.xpath(xpathLocator));  

		Actions action= new Actions(driver);
		action.moveToElement(Webelement);
		action.contextClick(Webelement).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();

		Thread.sleep(5000);

		/*	    
 		new KeyboardUtils().type("D");
 		 Robot robot = new Robot();
		  //  robot.keyPress(KeyEvent.VK_SHIFT);
		    robot.keyPress(KeyEvent.VK_COLON);
		    robot.keyRelease(KeyEvent.VK_COLON);        
		 //   robot.keyRelease(KeyEvent.VK_SHIFT);
		 */	    //		new KeyboardUtils().type("D:\\AutomationProjects\\TestAutomation_Selenium_logs\\AutomationTest_ExecutionLog\\Notice_1.pdf");
		KeyboardUtils.typeString("Notice.pdf");
		//D:\AutomationProjects\TestAutomation_Selenium_logs\AutomationTest_ExecutionLog\Notice_1.pdf
		//D:\\AutomationProjects\\TestAutomation_Selenium_logs\\AutomationTest_ExecutionLog\\Notice.pdf
		Thread.sleep(4000);

		new KeyboardUtils().Key_Enter();

		//		new KeyboardUtils().Key_Enter();
		//		new KeyboardUtils().Key_Enter();
		//
		//		new KeyboardUtils().Key_Esc();// in case there is replace dialog
		Thread.sleep(4000);

	}
	public void contextClick_Save(WebDriver driver, String xpathLocator) throws AWTException, InterruptedException {

		log("contextClick_Save..............Downloading file....");
		WebElement Webelement = driver.findElement(By.xpath(xpathLocator));  

		Actions action= new Actions(driver);
		action.moveToElement(Webelement);
		action.contextClick(Webelement).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
		//	action.contextClick(Webelement).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();

		Thread.sleep(5000);

		/*	    
 		new KeyboardUtils().type("D");
 		 Robot robot = new Robot();
		  //  robot.keyPress(KeyEvent.VK_SHIFT);
		    robot.keyPress(KeyEvent.VK_COLON);
		    robot.keyRelease(KeyEvent.VK_COLON);        
		 //   robot.keyRelease(KeyEvent.VK_SHIFT);
		 */	    //		new KeyboardUtils().type("D:\\AutomationProjects\\TestAutomation_Selenium_logs\\AutomationTest_ExecutionLog\\Notice_1.pdf");
		KeyboardUtils.typeString("Notice.pdf");
		//D:\AutomationProjects\TestAutomation_Selenium_logs\AutomationTest_ExecutionLog\Notice_1.pdf
		//D:\\AutomationProjects\\TestAutomation_Selenium_logs\\AutomationTest_ExecutionLog\\Notice.pdf
		Thread.sleep(4000);

		new KeyboardUtils().Key_Enter();

		//		new KeyboardUtils().Key_Enter();
		//		new KeyboardUtils().Key_Enter();
		//
		//		new KeyboardUtils().Key_Esc();// in case there is replace dialog
		Thread.sleep(4000);

	}

	public void contextClick2(WebDriver driver, String xpathLocator) throws AWTException, InterruptedException {

		log("Downloading file....");
		WebElement Webelement = driver.findElement(By.xpath(xpathLocator));  

		Actions action= new Actions(driver);
		action.moveToElement(Webelement);
		action.contextClick(Webelement).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();

		Thread.sleep(5000);

		/*	    
 		new KeyboardUtils().type("D");
 		 Robot robot = new Robot();
		  //  robot.keyPress(KeyEvent.VK_SHIFT);
		    robot.keyPress(KeyEvent.VK_COLON);
		    robot.keyRelease(KeyEvent.VK_COLON);        
		 //   robot.keyRelease(KeyEvent.VK_SHIFT);
		 */	    //		new KeyboardUtils().type("D:\\AutomationProjects\\TestAutomation_Selenium_logs\\AutomationTest_ExecutionLog\\Notice_1.pdf");
		KeyboardUtils.typeString("Notice.pdf");
		//D:\AutomationProjects\TestAutomation_Selenium_logs\AutomationTest_ExecutionLog\Notice_1.pdf
		//D:\\AutomationProjects\\TestAutomation_Selenium_logs\\AutomationTest_ExecutionLog\\Notice.pdf
		new KeyboardUtils().Key_Enter();

		//		new KeyboardUtils().Key_Enter();
		//		new KeyboardUtils().Key_Enter();
		//
		//		new KeyboardUtils().Key_Esc();// in case there is replace dialog
		Thread.sleep(4000);

	}

	public void rightClick(WebDriver driver, String xpathLocator) throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Actions oAction = new Actions(driver);

		WebElement Webelement = driver.findElement(By.xpath(xpathLocator));  
		oAction.moveToElement(Webelement);
		oAction.contextClick(Webelement).build().perform();  /* this will perform right click */
		WebElement elementOpen = driver.findElement(By.linkText("Save link as...")); // Save target as... //Open /*This will select menu after right click */
		elementOpen.click();

		KeyboardUtils.typeString("Notice.pdf");
		//D:\AutomationProjects\TestAutomation_Selenium_logs\AutomationTest_ExecutionLog\Notice_1.pdf
		//D:\\AutomationProjects\\TestAutomation_Selenium_logs\\AutomationTest_ExecutionLog\\Notice.pdf
		new KeyboardUtils().Key_Enter();

		//		new KeyboardUtils().Key_Enter();
		//		new KeyboardUtils().Key_Enter();
		//
		//		new KeyboardUtils().Key_Esc();// in case there is replace dialog
		Thread.sleep(4000);

	}


	public void clickToggle(WebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(@title,'Toggle') or contains(@aria-label,'Toggle']")).click();
	}

	public void getBrowserDetails(WebDriver driver) throws Exception{
		getBrowsername(driver);
		getBrowserVersion(driver);
		ReportUtils.reportResult("Done", "Browser Details", getBrowsername(driver) + " " + getBrowserVersion(driver));
	}

	public String getBrowsername(WebDriver driver){
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		log("Browser : " +browserName);
		return browserName;

	}

	public String getBrowserVersion(WebDriver driver){
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String v = cap.getVersion().toString();
		log("Version : " + v);
		return v;

	}


	/**
	 * launchBrowser
	 * All Drivers (latest version): Chrome,MozillaGecko,MicrosoftEdge,HtmlUnit,Firefox,Opera,Ghost,WindowsPhone,Selendroid,IOS,BlackBerry,Appium,CrossWalk,QT,JBrowser,Winium
	 * @return
	 */
	public WebDriver launchBrowser() {

		//    static WebDriver driver;
		WebDriver driver = null;
		if("Chrome".equals(TestDataPool.rowData.get("browser"))){
			System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe"); // Latest werbdriver compatible with Chrome v.53.0.2785.101
			driver = new ChromeDriver();
		}
		else if("Firefox".equals(TestDataPool.rowData.get("browser"))){

			driver = new FirefoxDriver();
			//    wait = new WebDriverWait(driver, 3000);
		}
		else if("IE".equals(TestDataPool.rowData.get("browser"))){
			// Use either 64 or 32 bit based on compatibility
			//	System.setProperty("webdriver.ie.driver", "D:\\drivers\\IEDriverServer_x64.exe");// IEDriverServer64Bit
			System.setProperty("webdriver.ie.driver", "D:\\drivers\\IEDriverServer_x32.exe");// IEDriverServer32Bit

			driver = new InternetExplorerDriver();
		}else {

			System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe"); // Latest werbdriver compatible with Chrome v.53.0.2785.101
			driver = new ChromeDriver();

			//	System.setProperty("webdriver.ie.driver", "D:\\drivers\\IEDriverServer_x32.exe");// IEDriverServer32Bit
			//driver = new InternetExplorerDriver();
		}

		return driver;
	}

	/**
	 * launchBrowser
	 * browserType can be passed from runtime or datapool [TestDataPool.rowData.get("browser")] 
	 * All Drivers (latest version): Chrome,MozillaGecko,MicrosoftEdge,HtmlUnit,Firefox,Opera,Ghost,WindowsPhone,Selendroid,IOS,BlackBerry,Appium,CrossWalk,QT,JBrowser,Winium
	 * @return
	 */
	public WebDriver launchBrowser_All(String browserType) {

		//    static WebDriver driver;
		Wait<WebDriver> wait = null;
		WebDriver driver = null;
		if("Chrome".equals(browserType)){

			/*	
		 // Figure out how to ignore zoom settings on chrome with capabilities or profile
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeDriver..)
			capabilities.setCapability(ChromeDriver
			 */

			System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe"); // Latest werbdriver compatible with Chrome v.53.0.2785.101
			driver = new ChromeDriver();

			//ChromeOptions options = new ChromeOptions();
			//   options.addArguments("start-maximized");
			//  options.addExtensions(new File("/path/to/extension.crx"));
			//  Map<String, Object> chromeOptions = new Map<String, Object>();
			// chromeOptions.put("binary", "/usr/lib/chromium-browser/chromium-browser");
			//DesiredCapabilities capabilities = new DesiredCapabilities();
			//capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			//    driver = new ChromeDriver(capabilities);
		}else if("MicrosoftEdge".equals(browserType)){
			System.setProperty("webdriver.edge.driver", "D:/drivers/MicrosoftWebDriver.exe"); // "driver/MicrosoftWebDriver.exe"
			driver = new EdgeDriver();
		}
		else if("MozillaGecko".equals(browserType)){
			System.out.println("Browser Type = MozillaGecko" );
			System.setProperty("webdriver.gecko.driver", "D:/drivers/geckodriver.exe"); 
			System.out.println("Firefox Browser Set");
			//driver = new MarionetteDriver();
			driver = new FirefoxDriver();
			//	driver = (WebDriver) new GeckoDriverService(executable, port, args, environment)
			System.out.println("Firefox Browser opened");
		}	
		else if("Firefox".equals(browserType)){


			System.out.println("Firefox profile..");

			File pathBinary = new File("C:/Program Files (x86)/Mozilla Firefox\\firefox.exe");
			FirefoxBinary Binary = new FirefoxBinary(pathBinary);
			FirefoxProfile firefoxPro = new FirefoxProfile();
			System.out.println("Firefox profile2..");

			driver.manage().window().fullscreen();
			//To download file in the directory mentioned while creating profile. And then, create a browser instance by passing profile:
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.dir", "directory_path_to_save_file");
			driver = new FirefoxDriver();
			//	driver = new FirefoxDriver(); 
			//    wait = new WebDriverWait(driver, 3000);
		}
		//		else if("HtmlUnit".equals(TestDataPool.rowData.get("browser"))){
		//			driver = new HtmlUnitDriver();
		//		}
		else if("IE".equals(browserType)){
			//Resets zoom level to 100%, in case it's zoomed out/in
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			// Use either 64 or 32 bit based on compatibility
			//	System.setProperty("webdriver.ie.driver", "D:\\drivers\\IEDriverServer_x64.exe");// IEDriverServer64Bit
			System.setProperty("webdriver.ie.driver", "D:\\drivers\\IEDriverServer_x32.exe");// IEDriverServer32Bit
			//	System.setProperty("webdriver.ie.driver", "D:\\drivers\\IEDriverServer32Bit.exe");//IEDriverServer_x64 IEDriverServer_x32 -- IEDriverServer_x32 -  IEDriverServer64Bit
			driver  = new InternetExplorerDriver(capabilities);
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,"0")); // Reset Zoom level to 100%

			//	driver = new InternetExplorerDriver();
		}
		else if("Opera".equals(browserType)){
			driver = new OperaDriver();
		}else {
			System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver_old.exe"); // old version of chrome driver
			driver = new ChromeDriver();
		}

		return driver;
	}



	public WebDriver launchBrowser_IE() {
		WebDriver driver_IE = null;
		try {

			//Resets zoom level to 100%, in case it's zoomed out/in
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			//     File fil = new File("iDrivers\\IEDriverServer.exe");
			//    System.setProperty("webdriver.ie.driver", fil.getAbsolutePath());
			//   System.setProperty("webdriver.ie.driver","D:\\IEDriverServer_Win32_2.33.0\\IEDriverServer.exe");

			System.setProperty("webdriver.ie.driver", "D:\\drivers\\IEDriverServer_x32.exe");//IEDriverServer_x64 IEDriverServer_x32 -- IEDriverServer_x32 -  IEDriverServer64Bit

			driver_IE= new InternetExplorerDriver(capabilities);

			//   driver.get(baseURl);

			driver_IE.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,"0"));

			//Identify your elements and go ahead testing...
			//    static WebDriver driver;
			//	System.setProperty("webdriver.ie.driver", "D:\\drivers\\IEDriverServer_x32.exe");//IEDriverServer_x32 -- IEDriverServer_x32 -  IEDriverServer64Bit
			//	Thread.sleep(2000);
			//	new KeyboardUtils().Key_ZoomReset();//Resets zoom level to 100%, in case it's zoomed out/in
			//	driver_IE = new InternetExplorerDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver_IE;
	}


	public WebDriver launchBrowser_Chrome(String driverPath) {
		//    static WebDriver driver;
		WebDriver driver = null;
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		return driver;
	}



	/**
	 * launchBrowser
	 * All Drivers (latest version): Chrome,MozillaGecko,MicrosoftEdge,HtmlUnit,Firefox,Opera,Ghost,WindowsPhone,Selendroid,IOS,BlackBerry,Appium,CrossWalk,QT,JBrowser,Winium
	 * @return
	 */
	public WebDriver launchBrowser(String browser) {
		WebDriver driver;
		if("Chrome".equals(browser)){
			System.setProperty("webdriver.chrome.driver", "D:\\jars\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if("Firefox".equals(TestDataPool.rowData.get("browser"))){
			driver = new FirefoxDriver();
		}else if("IE".equals(TestDataPool.rowData.get("browser"))){
			System.setProperty("webdriver.ie.driver", "D:\\jars\\IEDriverServer64Bit.exe");
			driver = new InternetExplorerDriver();
		}else {
			System.setProperty("webdriver.chrome.driver", "D:\\jars\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}

	/**
	 * waitForWidget
	 * @param driver
	 * @param xPath
	 * @throws Exception
	 */
	public void waitForWidget(WebDriver driver, String xPath) throws Exception{
		int iterator = 1;
		int maxTime = 40;
		boolean waitForScreen = true;
		do{
			Thread.sleep(1000);
			iterator++;
			List<WebElement> elements = driver.findElements(By.xpath(xPath));
			if(elements.size() > 0 || 
					iterator >= maxTime){
				waitForScreen = false;
			} else{	
				waitForScreen = true;
				log("waiting for widget......" + xPath);
			}
		}while(waitForScreen);
		if(iterator >= maxTime){
			log("+ NOK - waitForWidget : Widget with xPath =" + xPath + " was not displayed in " + maxTime + " seconds.");
		}
		log("+ OK - waitForWidget : Widget with xPath =" + xPath + " was displayed in " + maxTime + " seconds.");
	}

	/**
	 * waitForWidget_NoIterator - waits for widget for half a second only
	 * @param driver
	 * @param xPath
	 * @throws Exception
	 */
	public void waitForWidget_NoIterator(WebDriver driver, String xPath) throws Exception{
		boolean waitForScreen = true;
		Thread.sleep(500);
		List<WebElement> elements = driver.findElements(By.xpath(xPath));
		if(elements.size() > 0) { 
			waitForScreen = false;
		} else{	
			waitForScreen = true;
			log("waiting for widget......" + xPath);
		}

		//		log("+ OK - waitForWidget : Widget with xPath =" + xPath + " was displayed in " + maxTime + " seconds.");
	}
	/**
	 * returns the count of widgets matching the xpath given.
	 * @param driver
	 * @param xpath
	 * @return count of widgets
	 * @throws Exception
	 */
	public int getWidgetCount(WebDriver driver, String xpath) throws Exception
	{
		int count=0;

		List<WebElement> rows = driver.findElements(By.xpath(xpath)); // ".//table[@id='dummyTable']/tbody/tr"
		log("Total number of Widgets :"+ rows.size() + " >> "+ xpath);	

		count = rows.size();
		return count;
	}

	// Wait & Check if/until widget element exists/displayed

	public boolean isWidgetExists(WebDriver driver, String xPath) throws Exception{
		log("Checking if Widget exists....."+xPath);
		boolean widgetExists = false;
		//Thread.sleep(500);
		try{
			if(driver.findElement(By.xpath(xPath)).isDisplayed()){
				widgetExists = true;
			}
		}catch(Exception e){
			widgetExists = false;
			log("Widget " + xPath + " does NOT exist = " + widgetExists);
		}

		log("Widget " + xPath + " exists = " + widgetExists);
		return widgetExists;
	}


	// waitUntil element is Visible

	public void waitUntilVisible(WebDriver driver, String xPath, int maxWaitTimeInSec) throws Exception{
		try {
			log("Checking if Widget exists....."+xPath);
			WebDriverWait wait = new WebDriverWait(driver, maxWaitTimeInSec);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath))); 
			log("FOUND -- waitUntilVisible " + xPath );
		} catch (Exception e) {
			//	e.printStackTrace();
			log("NOTFOUND & MOVING ON -- Checking if Widget exists....."+xPath);	
		}
	}




	// Wait waitUntilTextPresent

	public void waitUntilTextPresent(WebDriver driver, String xPath, String textToBePresent) throws Exception{
		try {
			log("Checking if Widget exists....."+xPath);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.textToBePresentInElement(webElement(driver, xPath), textToBePresent));  
			log("waitUntilTextPresent " + xPath + " exists = " + textToBePresent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Checks if page exists or not (params : titles) - Customized for CP

	public boolean isPageExists(WebDriver driver, String pageTitle, String title_iframe, String pageTitle_Id) throws Exception{
		log("Check if Page exists.....");
		int iterator = 1;
		int maxTime = 3;
		boolean pageExists = false;
		do{
			Thread.sleep(2000);
			iterator++;
			try{
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title, '"+title_iframe+"')]"))); // Cúram Health & Human Services Exchange
				if(driver.findElement(By.xpath("//h2[contains(@id, '"+pageTitle_Id+"')]")).isDisplayed() && iterator <= maxTime){ // pageTitleHeading
					if(driver.findElement(By.xpath("//h2[contains(@id, '"+pageTitle_Id+"')]")).getText().contains(pageTitle)){
						pageExists = true;
					}
				}
			}catch(Exception e){
				pageExists = false;
			}

			driver.switchTo().defaultContent();
		}while(!pageExists && iterator <= maxTime);
		if(iterator >= maxTime){
			log("Widget with page title =" + pageTitle + " was not displayed in " + maxTime + " seconds.");
		}
		log("Page exists for "+pageTitle+ " = "+ pageExists);
		return pageExists;

	}

	// Checks if page exists or not (params : xpaths) - Generic

	public boolean pageExists(WebDriver driver, String pageTitle, String xpath_iframe, String xpath_PageTitle) throws Exception{
		log("Check if Page exists.....");
		int iterator = 1;
		int maxTime = 3;
		boolean pageExists = false;
		do{
			Thread.sleep(3000);
			iterator++;
			try{
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(xpath_iframe))); //"//iframe[contains(@title, 'Cúram Health & Human Services Exchange')]"
				if(driver.findElement(By.xpath(xpath_PageTitle)).isDisplayed() && 	iterator <= maxTime){ // "//h2[contains(@id, 'pageTitleHeading')]"
					if(driver.findElement(By.xpath(xpath_PageTitle)).getText().contains(pageTitle)){ // "//h2[contains(@id, 'pageTitleHeading')]"
						pageExists = true;
					}
				}
			}catch(Exception e){
				pageExists = false;
			}

			driver.switchTo().defaultContent();
		}while(!pageExists && iterator <= maxTime);
		if(iterator >= maxTime){
			log("Widget with page title =" + pageTitle + " was not displayed in " + maxTime + " seconds.");
		}
		log("Page exists for "+pageTitle+ " = "+ pageExists);
		return pageExists;

	}

	// Check if page exists or not in Citizen Portal

	public boolean pageExists_CP(WebDriver driver, String pageTitle) throws Exception{
		log("Check if Page exists.....");
		int iterator = 1;
		int maxTime = 3;
		boolean pageExists = false;
		do{
			Thread.sleep(3000);
			iterator++;
			try{
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title, 'Cúram Health & Human Services Exchange')]")));
				if(driver.findElement(By.xpath("//h2[contains(@id, 'pageTitleHeading')]")).isDisplayed() && 
						iterator <= maxTime){
					if(driver.findElement(By.xpath("//h2[contains(@id, 'pageTitleHeading')]")).getText().contains(pageTitle)){
						pageExists = true;
					}
				}
			}catch(Exception e){
				pageExists = false;
			}

			driver.switchTo().defaultContent();
		}while(!pageExists && iterator <= maxTime);
		if(iterator >= maxTime){
			log("Widget with page title =" + pageTitle + " was not displayed in " + maxTime + " seconds.");
		}
		log("Page exists for "+pageTitle+ " = "+ pageExists);
		return pageExists;

	}


	/**
	 * upload - upload any file in browser
	 * @param driver
	 * @param xpath
	 */
	public void upload(WebDriver driver, String xpathOrId, String filePath) {

		try {
			Thread.sleep(1000); 
			WebElement uploadElement = driver.findElement(By.xpath(xpathOrId)); //"uploadfile_0"
			//	WebElement uploadElement = driver.findElement(By.id(xpathOrId)); //"uploadfile_0"
			//enter the file path onto the file-selection input field 
			uploadElement.sendKeys(filePath); //"C:\\Selenium\\TestData\\Hi.xlsx"
			//check the "I accept the terms of service" check box 
			//	driver.findElement(By.id("terms")).click(); 
			//click the "UploadFile" button 
			//	driver.findElement(By.name("send")).click(); 
			log("Uploading..."); 
			Thread.sleep(30000);
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}

	/**
	 * download - download any file in browser
	 * @param driver
	 * @param xpath
	 */
	public void download(WebDriver driver, String xpathOrId, String filePath) {

		try {
			Thread.sleep(1000); 
			WebElement uploadElement = driver.findElement(By.xpath(xpathOrId)); //"uploadfile_0"
			//	WebElement uploadElement = driver.findElement(By.id(xpathOrId)); //"uploadfile_0"
			//enter the file path onto the file-selection input field 
			uploadElement.sendKeys(filePath); //"C:\\Selenium\\TestData\\Hi.xlsx"
			//check the "I accept the terms of service" check box 
			//	driver.findElement(By.id("terms")).click(); 
			//click the "UploadFile" button 
			//	driver.findElement(By.name("send")).click(); 
			log("Uploading..."); 
			Thread.sleep(30000);
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}
	// Checks if error exists in the page and if it does then throws error  /aborts

	public void  checkErrorText(WebDriver driver, String xpathLocator, String checkingText) throws Exception{
		log("Exit Test for Page Error...");
		//	boolean flag = getText(cpobj.browser_CitizenPortal, c.id, "bd", "error");
		//		boolean flag = containsText(driver, xpathLocator, checkingText);
		/*		boolean flag = isTextExists(driver, xpathLocator, checkingText);

		//	if(errorText.contains("error") || errorText.contains("Error")){
		if(!flag){
			log("NotOK-IEG error occurred becuase of the test data/ object properties changed!");
			throw new Exception("IEG error occurred becuase of the test data/ object properties changed!"); // throws exception and then enters catch block to execute using navigation
		}
		else
			log("OK-No IEG/Validation Error!!");
		 */	}

	// scrollAndClick
	public void scrollAndClick(WebDriver driver, String xpathExpression)
	{
		WebElement element = driver.findElement(By.xpath(xpathExpression));
		int elementPosition = element.getLocation().getY();
		String js = String.format("window.scroll(0, %s)", elementPosition);
		((JavascriptExecutor)driver).executeScript(js);
		element.click();
	}


	public void scrollDown(WebDriver driver, String xpath)
	{
		log("Scroll down ...");
		driver.findElement(By.xpath(xpath)).sendKeys(Keys.PAGE_DOWN);  

	}

	// scrollClick (for the element which are not visible in less resolution screens)
	public void scrollClick(WebDriver driver, String xpathExpression)
	{
		logPass("scrollClick");
		WebElement target = driver.findElement(By.xpath(xpathExpression));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", target);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} //not sure why the sleep was needed, but it was needed or it wouldnt work :(
		target.click();
	}

	//To shift focus by clicking on top section of page and get any dynamic fields populated
	public void shiftFocus(WebDriver driver, String xPathOfPageTopSection) throws InterruptedException {
		try {
			Thread.sleep(1000);
			//		waitImplicitely(driver, 1000);
			waitForWidget(driver, xPathOfPageTopSection); //"//span[contains(text(), 'Sign & Submit')]"
			log("Clicking on Top section to shift focus to get dynamic field populated");
			driver.findElement(By.xpath(xPathOfPageTopSection)).click(); // "//h2[contains(@id, 'pageTitleHeading')]"
			// Or use keystrokes
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void zoomInBrowser(WebDriver driver) throws InterruptedException {
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));

	}
	public void zoomOutBrowser(WebDriver driver) throws InterruptedException {
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

	}
	//reset the zoom back to 100%
	public void zoomResetBrowser(WebDriver driver) throws InterruptedException {
		WebElement html = driver.findElement(By.tagName("html"));

		html.sendKeys(Keys.chord(Keys.CONTROL, "0"));

	}


	public void zoomIE(WebDriver driver) throws InterruptedException {

		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

		System.setProperty("webdriver.ie.driver","D:\\IEDriverServer_Win32_2.33.0\\IEDriverServer.exe");

		driver= new InternetExplorerDriver(capabilities);


		//driver.get(baseURl);

		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,"0"));
		//This is to set the zoom to default value
		//Identify your elements and go ahead testing...

	}

	// ****************************** Broker Functions ******************************

	// Log to Console
	private static void log(String aMessage){
		System.out.println(DateUtils.timestamp()+" | "+aMessage +" ... ");
	}
	private static void logPass(String aMessage){
		System.out.println(DateUtils.timestamp()+" | "+"+ "+aMessage +" ... "); //OK
	}
	private static void logFail(String aMessage){
		System.out.println(DateUtils.timestamp()+" | "+"- "+aMessage +" ... "); //NOK - NOTOK
	}
	private static void logDone(String message){
		System.out.println(DateUtils.timestamp()+" | "+message);
		System.out.println(" \n __________________________________________"+"DONE"+"___________________________________________________________\n");
	}	 

	private static void logOk(String message){
		System.out.println(DateUtils.timestamp()+" | "+"+ OK : "+message);
	}
	private static void logNotOk(String message){
		System.out.println(DateUtils.timestamp()+" | "+StringUtils.emoji_Bug()+"- NOTOK : "+message);
	}	

	// Use this as a breakpoint for pausing script to debug real quick
	public void debug() {
		JOptionPane.showMessageDialog(null, "Debug it!!"); 
	}

	// Use this as a breakpoint for pausing script to debug real quick
	public void debug(String showSomething) {
		JOptionPane.showMessageDialog(null, showSomething); 
	}


	//Zoom Browser
	public void setup(WebDriver driver) throws Exception {
		// driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//  driver.get("http://google.com/"); 
	}


	public void getScrollStatus(WebDriver driver){
		//Call zooming functions to zoom in and out page.
		zoomIn(driver);  
		zoomOut(driver);
		zoomOut(driver);
		zoomReset(driver);
	}

	public void zoomIn(WebDriver driver){
		//To zoom In page 2 time using CTRL and + keys.
		for(int i=0; i<2; i++){   
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
		}
	}

	public void zoomOut(WebDriver driver){
		//To zoom out page 3 time using CTRL and - keys.
		for(int i=0; i<3; i++){
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		}
	}
	public void zoomOut(WebDriver driver, String xpath){
		//To zoom out page 3 time using CTRL and - keys.
		for(int i=0; i<3; i++){
			driver.findElement(By.xpath(xpath)).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		}
	}
	public void zoomReset(WebDriver driver){
		//To set browser to default zoom level 100%
		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
	}
	public void zoomReset(WebDriver driver, String xpath){
		//To set browser to default zoom level 100%
		driver.findElement(By.xpath(xpath)).sendKeys(Keys.chord(Keys.CONTROL, "0"));
	}
	public void saveFile(WebDriver driver, String xpathLocator) throws IOException {
		System.out.println("saveFile.................");
		String[] dialog =  new String[]{ "D:\\AutomationProjects\\TestAutomation_Selenium\\batches\\save_ie_file.exe","Save to...","Save", "D:/AutomationProjects/TestAutomation_Selenium_logs/AutomationTest_ExecutionLog/" }; // path to exe, dialog title, save/cancel/run, path to save file.
		Process p1 = Runtime.getRuntime().exec(dialog); // run the executable to wait for download link and process
		clickLink(driver, xpathLocator); // code to click on download link
		// clickLink("id=download_link"); // code to click on download link
		p1.destroy(); // kill the process looking to download}
	}

}
