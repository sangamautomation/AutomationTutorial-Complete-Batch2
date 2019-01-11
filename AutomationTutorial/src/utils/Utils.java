package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;


/**
 * Description :  Utility Functions
 * 
 * @author Sangam

 */

public class Utils {

	public static void clearProps(String path) {

		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			props.clear();
			props.store(new FileOutputStream(path), "comments");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void propertySet(String path, String prop, String value) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			if (value != null) {
				props.setProperty(prop, value);
				props.store(new FileOutputStream(path), "comments");
				System.out.println("$Property set: " + prop + " = " + value);
			} else
				System.out.println("$Property Not set: " + prop + " = " + value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void propertySet(String path, HashMap<String, String> rowData) throws FileNotFoundException, IOException {
		// Read Property File for environments
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			// props.clear();
			props.putAll(rowData);
			props.store(new FileOutputStream(path), "comments");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void propertyFile_Write(String path, String prop, String value) throws FileNotFoundException, IOException {
		// Read Property File for environments
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			if (value != null) {
				props.setProperty(prop, value);
				props.store(new FileOutputStream(path), "comments");
				System.out.println("$Property set: " + prop + " = " + value);
			} else
				System.out.println("$Property Not set: " + prop + " = " + value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String propertyGet(String path, String prop) {
		// Read Property File for environments
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		System.out.println("$Property get: " + prop);

		return props.getProperty(prop);
	}

	// Runs the Webservice by launching CMD window to run Windows Shell/Batch file that calls external system (JMeter) input/request/payload
		public void runWebService(String batchFilePath) {
			try {
				String[] command = { "cmd.exe", "/C", "Start", batchFilePath };
				Process p = Runtime.getRuntime().exec(command);
			} catch (IOException ex) {
			}
		}
		
}
