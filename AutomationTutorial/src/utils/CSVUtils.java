/**
 * 
 */
package utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Sangam
 * Description: CSV Utils 
 */
public class CSVUtils {

	// Get Field Value From CSV (arguments : row name & column name )
	
	public void lineNo(){/*
		
		 int line = 0;
		   for(String[] row: rowsAsTokens) 
		   {

		        //Check for conditions within CSV file
		        if (isErro) {
		            String errMsg = "error in line: " + line;
		            // output errrMsg;
		        }
		        line++;
		   }
	*/
		/*
		try 
		{
		    CSVReader reader = new CSVReader(new FileReader(filePath), ',');

		    // Reads the complete file into list of tokens.
		    List<String[]> rowsAsTokens = null;

		    int lineNum = 0;

		    try 
		    {
		        rowsAsTokens = reader.readAll();
		    } 

		    for(String[] row: rowsAsTokens) 
		    {
		        lineNum++; // increment the line number
		        //Check for conditions within CSV file
		        if (ERRONEOUS VALUE) {
		            // save the lineNum. possibly to an array or a string. whatever you need
		        }
		    }

		    catch (IOException e1)
		    {
		        e1.printStackTrace();
		    }
		}
	*/
		
		
	}
	
	
	public String searchWord(String key, File f) throws Exception {

	    LineNumberReader lnr = new LineNumberReader(new FileReader(f));
	    String line = null;
	    line = recursiveSearch(lnr.readLine(), key, lnr);

	    return line;
	}

	public String recursiveSearch(String currentLineText, String key, LineNumberReader lnr)
	        throws Exception {

	    if (currentLineText != null) {
	        String lCase = currentLineText.toLowerCase();
	        if (lCase.contains(key.toLowerCase())) {

	            return ("Line " + lnr.getLineNumber() + ": " + currentLineText
	                    + "\n");
	        }
	    }
	    return recursiveSearch(lnr.readLine(), key, lnr);
	}
	
	
	public static void hi(){
		
		
		/*
		
		static Map<String, List<Integer>> addLine(Reader reader) {
		    LineNumberReader r = new LineNumberReader(reader);
		    return r.lines()
		        .filter(line -> line.length() != 0)
		        .flatMap(line -> Stream.of(line.split("\\W+")))
		        .map(word -> word.toLowerCase())
		        .collect(TreeMap::new,
		            (m, w) -> m.computeIfAbsent(w, k -> new ArrayList<>())
		                .add(r.getLineNumber()),
		            (m, n) -> m.putAll(n));
		}

 		    try (Reader reader = new FileReader("C:/Users/Text.txt")) {
		        for (Entry<String, List<Integer>> e : addLine(reader).entrySet())
		            System.out.println("Word: " + e.getKey() + "\tLine Number: " + e.getValue());
 		}
	*/}

	 public static int grepLineNumber(String file, String word) throws Exception {
	    BufferedReader buf = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));

	    String line;
	    int lineNumber = 0;
	    while ((line = buf.readLine()) != null)   {
	        lineNumber++;
	        if (word.equals(line)) {

	            return Integer.parseInt(line);
	        }
	        
	    }
		return lineNumber;
	    
    //   System.out.println(Integer.parseInt(line));

    //   return Integer.parseInt(line);

	//   return -1;
	} 
	
	
	public static ArrayList<Integer> find(String word, File text) throws IOException {
	    LineNumberReader rdr = new LineNumberReader(new FileReader(text));
	    ArrayList<Integer> results = new ArrayList<Integer>();
	    try {
	        String line = rdr.readLine();
	        if (line.indexOf(word) >= 0) {
	            results.add(rdr.getLineNumber());
	            System.out.println(rdr.getLineNumber());
	        }
	    } finally {
	        rdr.close();
	    }
	    
	    System.out.println(results);
	    return results;
	}
	
	public static List<String> recursiveSearch2(String currentLineText, String key, LineNumberReader lnr)
	        throws Exception {
	    List<String> resultList = new ArrayList<String>();
	    if (currentLineText != null) {
	        String lCase = currentLineText.toLowerCase();
	        if (lCase.contains(key.toLowerCase())) {

	            String result = "Line " + lnr.getLineNumber() + ": " + currentLineText + "\n";
	            resultList.add(result);
	            
	            System.out.println(lnr.getLineNumber());
	        }
	    }
	    String nextLine = lnr.readLine(); 
	  /*  if(nextLine != null) {
	         resultList.addALL(recursiveSearch(nextLine, key, lnr));
	    }*/
	    return resultList;
	}
	
	
	public static String lineNumbers(String filePath, String textToFind) throws IOException
	{
	    LineNumberReader lineReader = new LineNumberReader(new FileReader(filePath));
	    String numbers = "";
	    String line;
	    while ((line = lineReader.readLine()) != null)
	    {
	        if (line.contains(textToFind))
	        {
	            numbers += "," + lineReader.getLineNumber();
	            System.out.println(lineReader.getLineNumber());
	        }
	    }
        System.out.println(numbers.substring(1));

	    return "[" + numbers.substring(1) + "]";

	}
	public static String lineNumbers2(String filePath, String textToFind) throws IOException
	{
	    LineNumberReader lineReader = new LineNumberReader(new FileReader(filePath));
	    String numbers = "";
	    String line;
	    while ((line = lineReader.readLine()) != null)
	    {
	        if (line.contains(textToFind.replaceAll("\n", "")));
	        {
	            numbers += "," + lineReader.getLineNumber();
	       //    System.out.println(lineReader.getLineNumber());
	        }
	    }
        System.out.println(numbers.substring(1));

	    return "[" + numbers.substring(1) + "]";

	}
	public static String csv_Read(String fileName, String rowName, String colName) {
		String strLine = null;
		// String content[]=null;
		String headder[] = null;
		
		int rowNum = 0;
		
		// passing rowNumber from rowName
 
	  /*  if (rowName != null) {
	        String lCase = rowName.toLowerCase();
	        if (lCase.contains(key.toLowerCase())) {

	            return ("Line " + lnr.getLineNumber() + ": " + currentLineText
	                    + "\n");
	        }
	    } */
	    
		rowNum = rowName.indexOf(rowName);
		
		int colNum = 0;
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(fileName);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			// Taking Headder Row
			if ((strLine = br.readLine()) != null && rowNum >= 1) {
				--rowNum;
				headder = strLine.split(",");
				// System.out.println(headder.length);
			}
			// Taking Column Num
			for (colNum = 0; colNum < headder.length
					&& !(headder[colNum].trim().equals(colName.trim())); ++colNum)
				;
			if (colNum >= headder.length) {
				System.out
				.println("Column Name - " + colName + " - Not Found!");
				br.close();
				return null;
			}

			// Read File Line By Line
			while (rowNum >= 1 && (strLine = br.readLine()) != null) {
				--rowNum;
				if (rowNum <= 0)
					break;
			}
			// Close the input stream
			in.close();
			// if row not found
			if (rowNum != 0) {
				System.out.println("Row Not Found");
				br.close();
				return null;
			}

			// System.out.println("Datapool: "+ colName+" = "
			// +strLine.split(",")[colNum].trim());

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return strLine.split(",")[colNum].trim();
	}


	public static String csv_Read(String fileName, int rowNum,
			String colName) {
		String strLine = null;
		// String content[]=null;
		String headder[] = null;
		int colNum = 0;
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(fileName);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			// Taking Headder Row
			if ((strLine = br.readLine()) != null && rowNum >= 1) {
				--rowNum;
				headder = strLine.split(",");
				// System.out.println(headder.length);
			}
			// Taking Column Num
			for (colNum = 0; colNum < headder.length
					&& !(headder[colNum].trim().equals(colName.trim())); ++colNum)
				;
			if (colNum >= headder.length) {
				System.out
				.println("Column Name - " + colName + " - Not Found!");
				br.close();
				return null;
			}

			// Read File Line By Line
			while (rowNum >= 1 && (strLine = br.readLine()) != null) {
				--rowNum;
				if (rowNum <= 0)
					break;
			}
			// Close the input stream
			in.close();
			// if row not found
			if (rowNum != 0) {
				System.out.println("Row Not Found");
				br.close();
				return null;
			}

			// System.out.println("Datapool: "+ colName+" = "
			// +strLine.split(",")[colNum].trim());

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		System.out.println(strLine.split(",")[colNum].trim());
		return strLine.split(",")[colNum].trim();
	}


	// Get Field Value from CSV (arguments : row number & column number )

	public static String csv_Read(String fileName, int rowNum, int colNum) {
		String strLine = null;
		// String content[]=null;
		String headder[] = null;
		// int colNum = 0;
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(fileName);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			// Read File Line By Line
			while (rowNum >= 1 && (strLine = br.readLine()) != null) {
				--rowNum;
				if (rowNum <= 0)
					break;
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		// System.out.println(strLine);//.split(",")[colNum-1].trim());
		if (strLine == null)
			return null;
		return strLine.split(",")[colNum - 1].trim();
	}
	
	
	public static String csv2Hashmap(String file, String rowNum, String colNum){
		   
  	        try{   
	            BufferedReader br = new BufferedReader(new FileReader(file));   
	          //  Map<Integer,String> values = new HashMap<Integer,String>(); 
	            Map<String,String> values = new HashMap<String,String>();   

	            String line = "";   
	            StringTokenizer tokens = null;   
	            int lineNo = 0;   
	            int tokenNo = 0;   
	            //reading the csv file line by line   
	            while ((line = br.readLine()) != null) {   
	                //increment the lineNo after every line is being read   
	                lineNo++;   
	                System.out.println("Reading Line No. : "+lineNo);   
	                tokens = new StringTokenizer(line, ",");   
	                while (tokens.hasMoreTokens()) {   
	                    //increment the token no!   
	                    tokenNo++;   
	                    //Print csv values   
	                    System.out.print(tokens.nextToken() + "  ");   
	                    //Need to write the values to the hashmap   
	                    values.put(rowNum, colNum);   
	                }   
	                 System.out.println();   
	                 //reset token number   
	                 tokenNo = 0;   
	            } 
	            
	            System.out.println("csv2Hashmap : "+ rowNum + ", "+ colNum);

	         }
	        catch(Exception ex){   
	            System.err.println("CSV file not found : " + ex);   
	        }
			return colNum;   
 	}
}
