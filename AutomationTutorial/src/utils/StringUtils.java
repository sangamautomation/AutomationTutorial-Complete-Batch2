package utils;

import java.net.URI;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.PatternSyntaxException;

import data.TestDataPool;

/**
 * Date: Oct 28, 2018
 * Author: Sangam
 * Description: Reusable String Utility Functions
 */
public class StringUtils {
	
	public static String subString(String text, int beginIndex, int endIndex){
		text = text.substring(beginIndex, endIndex);
		return text;
	}

	public static boolean compare(String expectedValue, String actualValue){
		boolean flag;
		 if(expectedValue.equals(actualValue)){
			flag=true;
			System.out.println("PASS :: expectedValue :"+ expectedValue + " = "+ "actualValue :"+ actualValue );
			ReportUtils.reportResult("Pass", "Validation Successful", actualValue);
		 }
		else{
			flag=false; 
			System.out.println("FAIL :: expectedValue :"+ expectedValue + " != "+ "actualValue :"+ actualValue);
			ReportUtils.reportResult("Fail", "Validation Failed", "Exepcted value = " +expectedValue);

		}
	//	flag=expectedValue.equalsIgnoreCase(actualValue)?true:false;
  		
		
		return flag;
		
	}
	

	
	// Puts given key value pairs into passed hashmap n number of times
	public static void HashPut(int n, HashMap<String,String> h,String key, String value){
		for (int i = 1; i <= n ; i++) {
			h.put(key, value);
 		}
	}
	/**
	 * Puts given key String -value String Array pairs into passed HashMap 'n' number of times for same Key Name with number increment
	 * @param key
	 * @param value
	 */
	public static void hashmap_Put(int n, HashMap<String,String> h,String key, String[] value){
		for (int i = 0; i < n ; i++) {
			h.put(key+(i+1), value[i]);
			System.out.println("HashMap Put locally: "+ key+(i+1) + " = " + value[i]);
		
			TestDataPool.rowData=h;//Assigning back to rowData
			System.out.println("HashMap Put globally: "+ key+(i+1) + " = " + TestDataPool.rowData.get(key+(i+1))); //This value must not be null
			TestDataPool.rowData = h;
			System.out.println(">>>>> noticeBPMD #"+i + " = " +TestDataPool.rowData.get("noticeBPMD"+i));
 		}
		
	}
	
	
	/**
	 * Puts given key String -value String Array pairs into passed HashMap 'n' number of times for different Key Names
	 * @param key
	 * @param value
	 */
	public static void setHashMap(int n, HashMap<String,String> h,String key[], String[] value){
		for (int i = 0; i < n ; i++) {
			h.put(key[i], value[i]);
			System.out.println(">>>>> Set HashMap: "+ key[i] + " = " + value[i]);
 		}
	}
	

	/**
	 * Assigns multiple values to DataPool
	 * @param key
	 * @param value
	 */
	public static void assignDataPool(String[] keys,String[] values){
	 	//String[] keys = {"income_Amount1","incomeFrequency1"};
		//String[] values = {"15720","Yearly"};
		setHashMap(keys.length, TestDataPool.rowData, keys, values);
	}
	
	public static String urlBuilder(String urlStr) throws Exception {
		// String urlStr =
		// "http://10.174.32.74/repos/Selenium/Selenium-TestAutomation/branch/Automation_Selenium/Datapool/TestDataPool_Automation.xlsx";
		URL url1 = new URL(urlStr);
		URI uri1 = new URI(url1.getProtocol(), url1.getUserInfo(), url1.getHost(), url1.getPort(), url1.getPath(), url1.getQuery(), url1.getRef());
		urlStr = uri1.toASCIIString();
		System.out.println("URL = " + urlStr);

		return urlStr = uri1.toASCIIString();
	}

	

	

	/**
	 * Generate 10 random integers in the range 0..99.
	 * 
	 * o/p:Generating 10 random integers in range 0..99. Generated : 44
	 * */
	public final void RandomInteger() {

		log("Generating 10 random integers in range 0..99.");

		// note a single Random object is reused here
		Random randomGenerator = new Random();
		for (int idx = 1; idx <= 10; ++idx) {
			int randomInt = randomGenerator.nextInt(100);
			log("Generated : " + randomInt);
		}

		log("Done.");
	}

	private static void log(String aMessage) {
		System.out.println(aMessage);
	}

	/**
	 * Generate random integers in a certain range. o/p: Generated : 9
	 * */
	public final void RandomRange() {

		log("Generating random integers in the range 1..10.");

		int START = 1;
		int END = 10;
		Random random = new Random();
		for (int idx = 1; idx <= 10; ++idx) {
			showRandomInteger(START, END, random);
		}

		log("Done.");
	}

	private static void showRandomInteger(int aStart, int aEnd, Random aRandom) {
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		long range = (long) aEnd - (long) aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * aRandom.nextDouble());
		int randomNumber = (int) (fraction + aStart);
		log("Generated : " + randomNumber);
	}

	/**
	 * Generate pseudo-random floating point values, with an approximately
	 * Gaussian (normal) distribution.
	 * 
	 * Many physical measurements have an approximately Gaussian distribution;
	 * this provides a way of simulating such values.
	 * 
	 * O/P: 99.38221153454624
	 */
	/*
	 * private static void RandomGaussian() {
	 * 
	 * RandomGaussian gaussian = new RandomGaussian(); double MEAN = 100.0f;
	 * double VARIANCE = 5.0f; for (int idx = 1; idx <= 10; ++idx){
	 * log("Generated : " + gaussian.getGaussian(MEAN, VARIANCE)); } }
	 * 
	 * private Random fRandom = new Random();
	 * 
	 * private double getGaussian(double aMean, double aVariance){ return aMean
	 * + fRandom.nextGaussian() * aVariance; }
	 * 
	 * private static void log(Object aMsg){
	 * System.out.println(String.valueOf(aMsg)); }
	 */



	public static String[] splitStringAtDelimiter(String textWithDelimiter, String delimiter) {
		String mySplitStrings[] = null;
		try {
			mySplitStrings = textWithDelimiter.split("\\s*\\" + delimiter + "\\s*"); // remove
			// any
			// white
			System.out.println("splitStringAtDelimiter :: "+textWithDelimiter);																				// spaces
			for (int i = 0; i < mySplitStrings.length; i++) {
				String string = mySplitStrings[i];
				System.out.println("mySplitStrings"+i+" = "+mySplitStrings[i]);
			}
			System.out.println(mySplitStrings[0]);
		} catch (PatternSyntaxException e) {
			e.printStackTrace();
		}
		return mySplitStrings;
	}
	
	/**
	 * subStringFromStartToAnyChar
	 * Takes any string and end char as args and returns the substring from start to the end char
	 * @param FullString
	 * @param EndCharacter
	 * @return
	 */
	public static String subStringFromStartToAnyChar(String FullString, String EndCharacter) {
 		int yo = FullString.indexOf(EndCharacter);
		String subsome = FullString.substring(0, yo);
 		System.out.println("subStringFromStartToAnyChar of "+ FullString + "From start till"+ EndCharacter + " = "+ subsome);
		return subsome;
	}
	public static String subStringWithIndex(String FullString, String indexForStartingChar) {
 		String subsome = FullString.substring( FullString.indexOf(indexForStartingChar)+1);
 		//String pseudoSSN= personSearchResult.substring(personSearchResult.indexOf("- ")+1);
 		System.out.println("subStringWithIndex  "+ FullString  + " = "+ subsome);
		return subsome;
	}
	public static String replaceAll(String FullString, String regexToFind, String replacementStr) {
 		String subsome = FullString.replaceAll(regexToFind, replacementStr);
 		//String pseudoSSN1= personSearchResult.replaceAll("[\\D]", "");
 		System.out.println("replaceAll  "+ FullString  + " = "+ subsome);
		return subsome;
	}
	
	public static String replace(String textToModify, String textToReplace, String textToSubstitute) {

		// System.out.println("0.1".replaceAll("0.", ""));
		String finalString = null;
		try {
			finalString = textToModify.replaceAll(textToReplace, textToSubstitute);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalString;
	}

	public static String replaceXMLTagsCharInStr(String str){
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}


	

	/**
	 * This returns String at the end of actualString from a given String as index.
	 * @param actualString = complete text
	 * @param strForIndex = to get the index of a particular text in complete text
	 * @param noOfCharBetIndexStrActualStr = number of chars between the index string and the required string.
	 * @return
	 */
	public static String getTextFromText(String actualString, String strForIndex, int noOfCharBetIndexStrActualStr){
		int index = actualString.indexOf(strForIndex);
		int len_Str = actualString.length();
		String subStr = "";
		if (index != -1) {
			subStr = actualString.substring(index + noOfCharBetIndexStrActualStr, len_Str);
		}
		return subStr;
	}

	public static String lastIndexOf(String textToModify, String charForIndex, String textToSubstitute) {
		// String xx = "/abc/def/ghfj.doc";
		// String finalString =
		// textToModify.substring(textToModify.lastIndexOf("/") + 1);
		String finalString = textToModify.substring(textToModify.lastIndexOf(charForIndex) + 1);

		System.out.println(finalString);

		return finalString;
	}

	//Returns Random Number of 8 digits
	public static String randomSSN() {
		return "28"
				+ Long.toString(System.currentTimeMillis()).substring(6, 12);
		// return "24"+Long.toString(System.currentTimeMillis()).substring(6,
		// 11)+"10";

	}

	// public void containText(String Validating, String ExpectedResult, String
	// ActualResult,
	// String ReportComment) {
	//
	// if (ExpectedResult.contains(ActualResult)) {
	// System.out.println(ReportComment + " Pass");
	// SuperHelper.logTestResult(
	// "<font size=2 face=Tahoma Color=Green>" + Validating +
	// " Verification Point: Pass</font>",
	// true, ReportComment);
	//
	// } else {
	// System.out.println(ReportComment + " Fail");
	// SuperHelper.logTestResult(
	// "<font size=2 face=Tahoma Color=Red>" + Validating +
	// " Verification Point: Fail</font>",
	// false, ReportComment);
	//
	// }
	//
	// }
	// public String getTextInScreen() throws Exception{
	// String x="";
	// x=new AutomationProjects().getAllTextInPageTable(".tag","TABLE", ".className",
	// "horizontal-align-start" );
	// System.out.println("DONE : Screen Text capture completed");
	// return x;
	// }

	// Verification Point for Text validation on passed page

	// public void textVerfication(String expected, String page) throws
	// Exception{
	//
	// System.out.println("Expected text - "+ expected);
	// System.out.println("Actual text - "+ getTextInScreen());
	//
	// containText(page+" "+"new Verbiage", getTextInScreen(), expected,
	// "Text validation done");
	// Utils.reportResult("Done"," Page Text Validation",
	// "New Verbiage has been added on page"+" "+page );
	//
	// //return result;
	// }

	// Returns true if x is strictly between min & max

	public static boolean betweenExclusive(int x, int min, int max)
	{
		return x>min && x<max;    
	}
	
	public static void smiley()
	{
  			System.out.println("\u263A");
  	}
	public static void smileyDark()
	{
  			System.out.println("\u263B");
  	}
	public static void smiley(int repeat)
	{
		for (int i = 0; i < repeat; i++) {
 			System.out.print("\u263A");
		}
			System.out.println();
 	}
	
	public static String emoji_Smiley()
	{
  			return("\u263A \u263A \u263A");
  	}
	
	public static String emoji_Bug()
	{
  			return("\u263C \u263C \u263C");
  	}
	// Annual amount will be parsed to monthly amount with desired format
	public static String parseDollarAmount(String incomeProp) {

		double incomex =Double.parseDouble(incomeProp)/12; 
		DecimalFormat formatter = new DecimalFormat("#,###.##");

		String incomeString = formatter.format(incomex);
		if ( !incomeString.contains(".")) incomeString = incomeString+".00";
		System.out.println(incomeString);
		return incomeString;
	}

	
	//Returns true if x is inclusively between min & max

	public static boolean betweenInclusive(int x, int min, int max)
	{
		return x>=min && x<=max;    
	}
	
	//Writes something diagonally in console
	public static void writeDiagonal(String writeup, String noSpaceVertical_spacesDiagonal){
  		    String spaces = "";
 		    for (int i = 0; i < writeup.length(); i++) {
		        System.out.println(spaces + writeup.charAt(i));
		        spaces += noSpaceVertical_spacesDiagonal;
		    }
	}
	
}
