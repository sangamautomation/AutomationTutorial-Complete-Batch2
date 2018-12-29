package tests.playground;

import org.testng.annotations.Test;

import utils.LogUtils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNGDemo {

	/*	 @DataProvider
	  public Object[][] dp() {
	    return new Object[][] {
	      new Object[] { 1, "John" , "Doe" , "35" },
	      new Object[] { 2, "Rob" , "Marshall", "36"},
	      new Object[] { 3, "Sangam", "Yad", "37"}
	    };
	  }
	 */

	@BeforeSuite
	public void beforeSuite() {
		LogUtils.log("@BeforeSuite");

	}
	@BeforeTest
	public void beforeTest() {
		LogUtils.log("@BeforeTest");

	}


	@BeforeClass
	public void beforeClass() {
		LogUtils.log("@BeforeClass");

	}


	@Test//(dataProvider = "dp")
	public void f(Integer n, String s) {
		LogUtils.log("@Test");
	} 
	@AfterClass
	public void afterClass() {
		LogUtils.log("@AfterClass");

	}



	@AfterTest
	public void afterTest() {
		LogUtils.log("@AfterTest");

	}

	@BeforeMethod
	public void beforeMethod() {
		LogUtils.log("@BeforeMethod");

	}

	@AfterMethod
	public void afterMethod() {
		LogUtils.log("@AfterMethod");

	}









	@AfterSuite
	public void afterSuite() {
		LogUtils.log("@AfterSuite");

	}

}
