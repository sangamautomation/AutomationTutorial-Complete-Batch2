package infrastructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import data.Constants;

/**
 *  Configuration Class to read and write into properties file.
 * @author Sangam
 */
public class Configuration {
	Properties props = new Properties();
	
	public Configuration(){

		// Read config.properties file
		try {
			//System.out.println("Configuration...");
			//System.out.println("Working Directory = "+ Constants.workingDir);
			//props.load(new FileInputStream(new File("D:\\Selenium_Logs\\TestData\\config.properties")));
			props.load(new FileInputStream(new File(Constants.configPath)));

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	public Configuration(String fileName){
		// Read the Property file name passed as parameter 
		try {
			props.load(new FileInputStream(new File(fileName)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	
	public String getEnvname(){
		return props.getProperty("EnvName");
	}
	
	public String getEnvironment(){
		return props.getProperty("Environment");
	}
	
	
	public String getProperty(String propName){
		return props.getProperty(propName);
	}
	
	public void setProperty(String propName, String propValue){
		props.setProperty(propName, propValue);
	}

}
