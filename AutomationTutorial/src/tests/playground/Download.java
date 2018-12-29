package tests.playground;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import data.Constants;
import setup.TestRunSetup;

public class Download {

	public static String url ="https://dl.google.com/chat/latest/InstallHangoutsChat.msi";
	public static String baseUrl = "https://get.google.com/chat/";
	public static WebDriver driver;

	public static void main(String args[]){

		//System.setProperty("webdriver.gecko.driver","C:\\drivers\\geckodriver.exe");
		//driver = new FirefoxDriver();

		driver = TestRunSetup.launch(url, "chrome", Constants.CHROMEDRIVER_PATH);

		driver.get(baseUrl);
		WebElement downloadButton = driver.findElement(By.id("messenger-download"));
		String sourceLocation = downloadButton.getAttribute("href");
		String wget_command = "cmd /c wget -P c: --no-check-certificate " + sourceLocation;
		try {
			Process exec = Runtime.getRuntime().exec(wget_command);
			int exitVal = exec.waitFor();
			System.out.println("Exit value: " + exitVal);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		driver.quit();
	}
}