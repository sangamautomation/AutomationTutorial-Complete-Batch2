package utils;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SubjectTerm;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

//import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.common.collect.Ordering;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import data.Constants;
import infrastructure.Configuration;
 

/**
 * Description : All Utility Functions [categorized + new functions]
 * 
 * @author Sangam
 */


/**
 * @author Sangam
 * Description: AllUtils.java
 */
public class AllUtils  {

 
	// Keystrokes variables

	public String t = "{TAB}", e = "{ENTER}", sh = "{SHIFT}", a = "{ALT}",
			c = "{CTRL}", d = "{ExtDelete}", s = " ", b = "{BKSP}",
			st = "+{TAB}";
	public String dn = "{ExtDown}", up = "{ExtUp}", left = "{ExtLeft}",
			right = "{ExtRight}", home = "{ExtHome}", end = "{ExtEnd}",
			pgup = "{ExtPgUp}", pgdn = "{ExtPgDn}";
	public String ctrlS = "^s", altS = "%s", rightclick = "@{Win}",
			f2 = "{F2}", rtmenu = "{APPS}", pr = "{PRTSC}";
	public String y = "Yes", n = "No";

	 

	public static ArrayList detDateTime = null;
	public static ArrayList<String> XMLFiles = null;
	public static ArrayList ordXMLFiles = null;

	public static boolean dateFlag = false;
	public static boolean dateFlagGT45 = false;

	char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWZYZ"
			.toCharArray();
	StringBuilder sb = new StringBuilder();
	Random r1 = new Random();

	// this static variable is used to keep track of page numbers in
	// takescreenshotandaddtopdf method.
	static int pdfPageCount = 0;

	// Automation Dates

	private static SimpleDateFormat dateFormatUS = new SimpleDateFormat(
			"M/d/yyyy", Locale.US);

	private static SimpleDateFormat dateAndTimeFormatUS = new SimpleDateFormat(
			"M/d/yyyy HH:mm", Locale.US);

	private static SimpleDateFormat batchDateFormatUS = new SimpleDateFormat(
			"yyyyMMdd", Locale.US);

	private static SimpleDateFormat dateFormatLocaleMachineUS = new SimpleDateFormat(
			"yyyyMMdd", Locale.US);

	private static SimpleDateFormat reassessedDateFormatUS = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.US);

	private static SimpleDateFormat ganttChartFormatUS = new SimpleDateFormat(
			"MMMMM d, yyyy", Locale.US);

	private static TimeZone utc = TimeZone.getTimeZone("UTC");
	private static TimeZone defaultZone = TimeZone.getDefault();
	private static Locale timelocale = Locale.US;
	private static Calendar calendar = Calendar.getInstance(utc, timelocale);
	private static Date currentSystemDate = getTodaysDate();
	private static boolean dateRollChange = false;

	private static HashMap<String, String> xmlData = new HashMap<>();

	 

	public String GetCurrentDateTime(String DateFormat) {

		// DateFormat = "yyyy/MM/dd"
		DateFormat dateFormat = new SimpleDateFormat(DateFormat);
		// get current date time with Date()
		Date date = new Date();
		// System.out.println(dateFormat.format(date)); don't print it, but save
		// it!
		String yourDate = dateFormat.format(date);
		return yourDate;

	}

	/**
	 * Current Time Stamp
	 * @return
	 */
	public static String getTimeStamp() {
		String timeStamp1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String s=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
		String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US).format(new Date());
		String timeStamp2 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.US).format(new Date());
		String timeStamps = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US).format(new Date());
		System.out.println(timeStamp);
		//	System.out.println(timeStamps);

		return timeStamp;
	}

	public void killProcesses(String processName) throws IOException {
		Runtime.getRuntime().exec("taskkill /F /IM " + processName + ".exe");

	}

	public void invokeProcess(String command, String param) throws IOException {
		Runtime.getRuntime().exec(command + " " + param);

	}

	 

	// Reading Excel Cells
/*
	public static String readExcel(String inputFile, String testCase,
			String param) throws IOException {
		if (new File(inputFile).exists() == false) {
			System.out
			.println("INPUT Excel Sheet Not Found, Taking Generated Values........");
			return null;
		}
		File inputWorkbook = new File(inputFile);
		Workbook w;
		int row = -1;
		int col = -1;
		int rowCount = 0;
		int colCount = 0;
		int l;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(0);

			for (colCount = 0; colCount < sheet.getColumns(); colCount++) {
				for (rowCount = 0; rowCount < sheet.getRows(); rowCount++) {
					Cell cell = sheet.getCell(colCount, rowCount);
					if (cell.getContents().equals(testCase)) {
						row = rowCount;
						col = colCount;
					}

				}
			}

			if (row == -1 && col == -1) {
				System.out.println("Test Case not found");
				return null;
			} else {
				for (l = col + 1; l < sheet.getColumns(); l++) {
					Cell cell = sheet.getCell(l, row);
					if (cell.getContents().equals(param)) {

						cell = sheet.getCell(l, row + 1);
						return cell.getContents();
					}

				}
				if (l == sheet.getColumns()) {
					System.out.println("Parameter not found");
					return null;

				}
			}

		} catch (Exception e) {
			System.out.println("Excel INPUT FILE NOT FOUND");
			// e.printStackTrace();
			return null;
		}
		return null;

	}
*/
	// Get Field From CSV (arguments : row number & column name )

	public static String getFieldFromCSV(String fileName, int rowNum,
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
		return strLine.split(",")[colNum].trim();
	}// getFieldFromCSV()

	// Get Field from CSV (arguments : row number & column number )

	public static String getFieldFromCSV(String fileName, int rowNum, int colNum) {
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
	}// getFieldFromCSV()

	// Append Log

	public static void appendLog(String log, String fileName) {
		try {

			File file = new File("c:\\coreAutomationLogs");
			boolean exists = file.exists();
			if (!exists) {
				(new File("c:\\coreAutomationLogs")).mkdirs();
			}

			// Create file
			FileWriter fstream = new FileWriter("c:\\coreAutomationLogs\\"
					+ fileName + ".txt", true);
			PrintWriter out = new PrintWriter(fstream);
			out.println(Calendar.getInstance().getTime().toString().trim()
					+ " : " + log);
			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void appendLog(String log, String fileName, String extension) {
		try {

			File file = new File("c:\\coreAutomationLogs");
			boolean exists = file.exists();
			if (!exists) {
				(new File("c:\\coreAutomationLogs")).mkdirs();
			}

			// Create file
			FileWriter fstream = new FileWriter("C:\\AutomationProjectsScreenshots\\"
					+ fileName + "." + extension, true);
			PrintWriter out = new PrintWriter(fstream);
			out.println(Calendar.getInstance().getTime().toString().trim()
					+ " : " + log);
			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Find Type

	public static String findType(String XMLFilePath) throws Exception {
		FileInputStream fis = new FileInputStream(XMLFilePath);
		DataInputStream myInput = new DataInputStream(fis);
		String buffer = myInput.readUTF();
		if (buffer.contains("<Source>LEZ</Source>"))
			return "LEZ";
		else
			return "WEZ";
	}

	// Find Using Regular Expression

	private String makeAbsolute(String url, String link) {
		if (link.matches("http://.*")) {
			return link;
		}
		if (link.matches("/.*") && url.matches(".*$[^/]")) {
			return url + "/" + link;
		}
		if (link.matches("[^/].*") && url.matches(".*[^/]")) {
			return url + "/" + link;
		}
		if (link.matches("/.*") && url.matches(".*[/]")) {
			return url + link;
		}
		if (link.matches("/.*") && url.matches(".*[^/]")) {
			return url + link;
		}
		throw new RuntimeException("Cannot make the link absolute. Url: " + url
				+ " Link " + link);
	}

	 

	public static int countLines(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		int count = 0;
		try {
			while (br.readLine() != null) {
				count++;
			}
		} finally {
			br.close();
		}
		return count;
	}

	// To find ncr & npr

	/*
	 * public static int fact(int num) { int fact=1,i; for(i=1;i<=num;i++)
	 * fact=fact*i; return fact;
	 * 
	 * 
	 * Scanner sc=new Scanner(System.in);
	 * 
	 * System.out.println("Enter n"); int n=sc.nextInt();
	 * System.out.println("Enter r"); int r=sc.nextInt();
	 * System.out.println("Ncr ="+(fact(n)/(fact(n-r)*fact(r))));
	 * System.out.println("Npr ="+(fact(n)/(fact(n-r)))); }
	 * 
	 * 
	 * 
	 * 
	 * public static void main(String[] args)throws SQLException {
	 * DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	 * Connection
	 * con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
	 * ,"ram","ram"); Statement st=con.createStatement();
	 * con.setAutoCommit(false);
	 * st.executeUpdate("insert into student values(13,'m',56)");
	 * st.executeUpdate("insert into student values(14,'n',66)"); Savepoint
	 * sv=con.setSavepoint();
	 * st.executeUpdate("insert into student values(15,'o',76)");
	 * st.executeUpdate("insert into student values(16,'p',86)"); Savepoint
	 * sv1=con.setSavepoint();
	 * st.executeUpdate("insert into student values(17,'q',96)");
	 * st.executeUpdate("insert into student values(18,'r',46)");
	 * con.rollback(sv1); con.close(); }
	 */

	// *****************************************************************************************//
	// Read CSV File (with delimiter as TAB) //
	// *****************************************************************************************//

	public static String getFieldFromCSV_TAB(String fileName, int rowNum,
			int colNum) {
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
		return strLine.split("	")[colNum - 1].trim();
	}// getFieldFromCSV()

	/*
	 * public void JExcelAPIDemo() throws BiffException, IOException,
	 * WriteException {
	 * 
	 * { WritableWorkbook wworkbook; wworkbook = Workbook.createWorkbook(new
	 * File("output.xls")); WritableSheet wsheet =
	 * wworkbook.createSheet("First Sheet", 0); Label label = new Label(0, 2,
	 * "A label record"); wsheet.addCell(label); Number number = new Number(3,
	 * 4, 3.1459); wsheet.addCell(number); wworkbook.write(); wworkbook.close();
	 * 
	 * Workbook workbook = Workbook.getWorkbook(new File("output.xls")); Sheet
	 * sheet = workbook.getSheet(0); Cell cell1 = sheet.getCell(0, 2);
	 * System.out.println(cell1.getContents()); Cell cell2 = sheet.getCell(3,
	 * 4); System.out.println(cell2.getContents()); workbook.close(); } }
	 */

	// This code prints the datapool associated with the script

/*	public void printDatapool() {

		System.out.println("Print Datapool...");
		IDatapool datapool = (IDatapool) getDatapool();
		int variableCount = datapool.getVariableCount();
		for (int varIndex = 0; varIndex < variableCount; varIndex++) {
			IDatapoolVariable dpVariable = (IDatapoolVariable) datapool
					.getVariable(varIndex);
			System.out.print(dpVariable.getName() + "\t\t");
		}
		System.out.println();
		IDatapoolEquivalenceClass dpeqvcls = (IDatapoolEquivalenceClass) datapool
				.getEquivalenceClass(0);
		int recordCount = dpeqvcls.getRecordCount();
		for (int recIndex = 0; recIndex < recordCount; recIndex++) {
			IDatapoolRecord record = (IDatapoolRecord) dpeqvcls
					.getRecord(recIndex);
			int cellCount = record.getCellCount();
			for (int i = 0; i < cellCount; i++) {
				IDatapoolCell cell = (IDatapoolCell) record.getCell(i);
				Object value = cell.getCellValue();
				System.out.print(value + "\t\t");
			}
			System.out.println();
		}

	}
*/
	/*public String[][] readExcelFile(String inputFile) throws IOException {
		String[][] data = null;
		File inputWorkbook = new File(inputFile);
		Workbook w;

		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet

			Sheet sheet = w.getSheet(0);
			data = new String[sheet.getColumns()][sheet.getRows()];
			// Loop over first 10 column and lines
			// System.out.println(sheet.getColumns() + " " +sheet.getRows());
			for (int j = 0; j < sheet.getColumns(); j++) {
				for (int i = 0; i < sheet.getRows(); i++) {
					Cell cell = sheet.getCell(j, i);
					data[j][i] = cell.getContents();
					// System.out.println(cell.getContents());
				}
			}

			
			 * for (int j = 0; j < data.length; j++) { for (int i = 0; i
			 * <data[j].length; i++) {
			 * 
			 * System.out.println(data[j][i]); } }
			 

		} catch (BiffException e) {
			e.printStackTrace();
		}
		return data;
	}
*/
/*	public String[][] readExcelFile(String inputFile, int h, int k)
			throws IOException {
		String[][] data = null;
		File inputWorkbook = new File(inputFile);
		Workbook w;

		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet

			Sheet sheet = w.getSheet(0);
			data = new String[sheet.getColumns()][sheet.getRows()];
			// Loop over first 10 column and lines
			// System.out.println(sheet.getColumns() + " " +sheet.getRows());
			for (int j = 0; j < sheet.getColumns(); j++) {
				for (int i = 0; i < sheet.getRows(); i++) {
					Cell cell = sheet.getCell(j, i);
					data[j][i] = cell.getContents();
					// System.out.println(cell.getContents());
				}
			}

			
			 * for (int j = 0; j < data.length; j++) { for (int i = 0; i
			 * <data[j].length; i++) {
			 * 
			 * System.out.println(data[j][i]); } }
			 

		} catch (BiffException e) {
			e.printStackTrace();
		}
		return data;
	}
*/
	// Copy the text to clipboard
	public static void copy(String text) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();

		clipboard.setContents(new StringSelection(text), null);
	}

	// Paste the text from clipboard
	public static void paste() throws AWTException {
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
	}

	public String randomSSN1() {
		return Long.toString(System.currentTimeMillis()).substring(4);

	}

	public String randomSSN() {
		return "26"
				+ Long.toString(System.currentTimeMillis()).substring(6, 12);
		// return "24"+Long.toString(System.currentTimeMillis()).substring(6,
		// 11)+"10";

	}

	public static String get(String url) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (Scanner sc = new Scanner(new URL(url).openStream()); sc.hasNext();)
			sb.append(sc.nextLine()).append('\n');
		return sb.toString();
	}

	/*
	 * public List<String> getColumnValues(int colIndex) { WebElement
	 * colElement; List<String> colValues = new ArrayList<String>();
	 * List<WebElement> rows = getRows(); System.out.println("Rows count: " +
	 * rows.size()); Iterator<WebElement> i = rows.iterator(); while
	 * (i.hasNext()) { WebElement row = i.next();
	 * System.out.println("Row data: " + row.getText()); // How to avoid this
	 * check for the header row for each row // iteration? if
	 * (row.findElements(By.tagName("th")).size() > 0) { colElement =
	 * row.findElement(By.xpath(".//th[" + colIndex + "]")); } else { colElement
	 * = row.findElement(By.xpath(".//td[" + colIndex + "]")); }
	 * colValues.add(colElement.getText().trim()); } return colValues; }
	 */

	/*
	 * public void closealltabs()
	 * 
	 * {
	 * 
	 * 
	 * util.descriptive_GuiTestObject1_RTClick(".title", "Application Search");
	 * 
	 * //tabContainer_hcrCasesAndOutcom().click(RIGHT); sleep(1);
	 * 
	 * br.keyStrokes_Intake(dn); sleep(1); br.keyStrokes_Intake(e);
	 * 
	 * 
	 * }
	 */
 
	 

	public void name_rndChar() {

		for (int i = 0; i < 3; i++) {
			char c = chars[r1.nextInt(chars.length)];
			sb.append(c);
			/* sb.a */

		}
		String output = sb.toString();

		System.out.println(output);
		c = null;

	}

	// Read HTML Table (object hard-coded)
	/*public void readHTMLTable() {
		ITestDataTable kontents = (ITestDataTable) objintake.table_ApplicationSearchResults
				.getTestData("contentswithchildren");

		System.out.println(objintake.table_ApplicationSearchResults
				.getTestDataTypes());

		System.out.println("Total Rows in table : " + kontents.getRowCount());
		System.out
		.println("Total Cols in table : " + kontents.getColumnCount());

		for (int row = 0; row < kontents.getRowCount(); ++row) {
			for (int col = 0; col < kontents.getColumnCount(); ++col) {
				System.out.println("contents(" + row + "," + col + ")=\""
						+ kontents.getCell(row, col) + "\"");
			}
		}
	}
*/
	 

	public int calcAge() {
		LocalDate birthdate = new LocalDate(1970, 1, 20); // Birth date
		LocalDate now = new LocalDate(); // Today's date
		Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
		return period.getYears();
	}

	public int ageCalculator(String dateOfBirth) {

		if (dateOfBirth == "" || dateOfBirth.equals("") || dateOfBirth == " "
				|| dateOfBirth.equals(" ") || dateOfBirth.contains("~")
				|| dateOfBirth.equalsIgnoreCase("NA")
				|| dateOfBirth.equals("-") || dateOfBirth.equals(null)) {
			return 0;
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
			LocalDate dob = null;
			try {
				dob = new LocalDate(formatter.parse(dateOfBirth));
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			LocalDate now = new LocalDate(); // Today's date
			Period period = new Period(dob, now, PeriodType.yearMonthDay());
			return period.getYears();
		}

	}

	/**
	 * Age calculator with pattern paramerized 
	 * @param pattern = "M/d/yyyy"
	 * @param dateOfBirth = "09/09/1984"
	 * @return age
	 */
	public int ageCalculator(String pattern, String dateOfBirth) {

		if (dateOfBirth == "" || dateOfBirth.equals("") || dateOfBirth == " "
				|| dateOfBirth.equals(" ") || dateOfBirth.contains("~")
				|| dateOfBirth.equalsIgnoreCase("NA")
				|| dateOfBirth.equals("-") || dateOfBirth.equals(null)) {
			return 0;
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			LocalDate dob = null;
			try {
				dob = new LocalDate(formatter.parse(dateOfBirth));
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			LocalDate now = new LocalDate(); // Today's date
			Period period = new Period(dob, now, PeriodType.yearMonthDay());
			System.out.println("Age of " + dateOfBirth + " = "
					+ period.getYears());

			return period.getYears();
		}

	}

	public String dateToday() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(date);
	}

	/**
	 * Calculates date by adding or removing certain number of dates and returns that date
	 * @param days
	 * @return
	 * 
	 */
	public static String dateTodayPlus(int days) {
		/*
		 * DateTimeFormatter formatter =
		 * DateTimeFormat.forPattern("MM/dd/yyyy").withLocale(Locale.US);
		 * SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 * 
		 * LocalDate now = new LocalDate(); //Today's date LocalDate later =
		 * now.plusDays(days); return sdf.format(later);
		 */

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, days); // Adding days
		String output = sdf.format(c.getTime());
		return output;
	}

	public String date_firstOfLastMonth() {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/d/YYYY");
		String str = fmt.print(firstOfLastMonth);

		System.out.println(str);
		return str;

	}

	public String date_endOfLastMonth() {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/d/YYYY");
		String str = fmt.print(endOfLastMonth);

		System.out.println(str);
		return str;

	}

	public String date_PlusYear() {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDate nextYear = today.plusYears(1);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/d/YYYY");
		String str = fmt.print(nextYear);

		System.out.println(str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' years to/from today and returns that date
	 * @param n
	 * @return
	 */
	public String date_PlusYears(int n) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDate resultDate = today.plusYears(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/d/YYYY");
		String str = fmt.print(resultDate);

		System.out.println(str);
		return str;

	}

	// Dynamic Date Functions using Joda Time

	/**
	 * Adds or removes(-) 'n' years to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_PlusYears(int n, String pattern_like_MM_d_YYYY) {
		// if(Integer.toString(n).matches("^.*[^a-zA-Z0-9 ].*$"))	{

		if(Integer.toString(n).matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
			LocalDate firstOfThisMonth = today.withDayOfMonth(1);
			LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
			LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

			LocalDate resultDate = today.plusYears(n);
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);

			System.out.println(str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null");
			return null;
		}
	}

	/**
	 * Adds or removes(-) 'n' years to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_MinusYears(int n, String pattern_like_MM_d_YYYY) {
		if(Integer.toString(n).matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
			LocalDate firstOfThisMonth = today.withDayOfMonth(1);
			LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
			LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

			LocalDate resultDate = today.minusYears(n);
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);

			System.out.println(str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return null;
		}
	}
	public String date_MinusYears(String n, String pattern_like_M_d_YYYY) {
		if(n.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
			LocalDate firstOfThisMonth = today.withDayOfMonth(1);
			LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
			LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

			LocalDate resultDate = today.minusYears(Integer.parseInt(n));
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println(str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return "";
		}
	}

	/**
	 * Adds or removes(-) 'n' months to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_PlusMonths(int n, String pattern_like_MM_d_YYYY) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDate resultDate = today.plusMonths(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
		String str = fmt.print(resultDate);

		System.out.println(str);
		return str;

	}


	/**
	 * Adds or removes(-) 'n' months to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_MinusMonths(int n, String pattern_like_MM_d_YYYY) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDate resultDate = today.minusMonths(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
		String str = fmt.print(resultDate);

		System.out.println(str);
		return str;

	}


	/**
	 * Adds or removes(-) 'n' days to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_PlusDays(int n, String pattern_like_MM_d_YYYY) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDate resultDate = today.plusDays(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
		String str = fmt.print(resultDate);

		System.out.println(str);
		return str;

	}


	/**
	 * Adds or removes(-) 'n' days to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_MinusDays(int n, String pattern_like_MM_d_YYYY) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDate resultDate = today.minusDays(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
		String str = fmt.print(resultDate);

		System.out.println(str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' weeks to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_PlusWeeks(int n, String pattern_like_MM_d_YYYY) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDate resultDate = today.plusWeeks(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
		String str = fmt.print(resultDate);

		System.out.println(str);
		return str;

	}


	/**
	 * Adds or removes(-) 'n' Weeks to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_MinusWeeks(int n, String pattern_like_MM_d_YYYY) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDate resultDate = today.minusWeeks(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
		String str = fmt.print(resultDate);

		System.out.println(str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' Years, Months, Weeks, Days  to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_PlusYearsMonthsWeeksDays(int yr, int mon, int wk, int days, String pattern_like_MM_d_YYYY) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDate resultDate = today.plusWeeks(wk).plusYears(yr).plusMonths(mon).plusDays(days);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
		String str = fmt.print(resultDate);

		System.out.println(str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' Years, Months, Weeks, Days  to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_MinusYearsMonthsWeeksDays(int yr, int mon, int wk, int days, String pattern_like_MM_d_YYYY) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDate resultDate = today.minusWeeks(wk).minusYears(yr).minusMonths(mon).minusDays(days);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
		String str = fmt.print(resultDate);

		System.out.println(str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' Years, Months, Weeks, Days  to/from today and returns that date in a specified format
	 * @param yr
	 * @param mon
	 * @param wk
	 * @param days
	 * @param hr
	 * @param min
	 * @param sec
	 * @param pattern_like_MM_d_YYYY_HH_mm_ss
	 * @return
	 */
	public String date_PlusYearsMonthsWeeksDaysHoursMinutesSeconds(int yr, int mon, int wk, int days, int hr, int min, int sec, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Montreal"));
		LocalDateTime firstOfThisMonth = today.withDayOfMonth(1);
		LocalDateTime firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDateTime endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDateTime resultDate = today.plusWeeks(wk).plusYears(yr).plusMonths(mon).plusDays(days).plusHours(hr).plusMinutes(min).plusSeconds(sec);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY_HH_mm_ss);
		String str = fmt.print(resultDate);

		System.out.println(str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' Years, Months, Weeks, Days  to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String datetime_MinusYearsMonthsWeeksDaysHoursMinutesSeconds(int yr, int mon, int wk, int days, int hr, int min, int sec, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Montreal"));
		LocalDateTime firstOfThisMonth = today.withDayOfMonth(1);
		LocalDateTime firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDateTime endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDateTime resultDate = today.minusWeeks(wk).minusYears(yr).minusMonths(mon).minusDays(days).minusHours(hr).minusMinutes(min).minusSeconds(sec);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY_HH_mm_ss);
		String str = fmt.print(resultDate);

		System.out.println(str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' hours to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_PlusHours(int n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Montreal"));
		LocalDateTime resultDateTime = today.plusHours(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY_HH_mm_ss);
		String str = fmt.print(resultDateTime);

		System.out.println(str);
		return str;

	}


	/**
	 * Adds or removes(-) 'n' hours to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_MinusHours(int n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Montreal"));
		LocalDateTime resultDateTime = today.minusHours(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY_HH_mm_ss);
		String str = fmt.print(resultDateTime);

		System.out.println(str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' Minutes to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_PlusMinutes(int n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Montreal"));
		LocalDateTime resultDateTime = today.plusMinutes(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY_HH_mm_ss);
		String str = fmt.print(resultDateTime);

		System.out.println(str);
		return str;

	}


	/**
	 * Adds or removes(-) 'n' Minutes to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_MinusMinutes(int n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Montreal"));
		LocalDateTime resultDateTime = today.minusMinutes(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY_HH_mm_ss);
		String str = fmt.print(resultDateTime);

		System.out.println(str);
		return str;

	}
	/**
	 * Adds or removes(-) 'n' Seconds to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_PlusSeconds(int n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Montreal"));
		LocalDateTime resultDateTime = today.plusSeconds(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY_HH_mm_ss);
		String str = fmt.print(resultDateTime);

		System.out.println(str);
		return str;

	}


	/**
	 * Adds or removes(-) 'n' Seconds to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String date_MinusSeconds(int n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Montreal"));
		LocalDateTime resultDateTime = today.minusSeconds(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY_HH_mm_ss);
		String str = fmt.print(resultDateTime);

		System.out.println(str);
		return str;

	}


	/**
	 * Adds or removes(-) 'n' hours to/from currentTime and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String time_PlusHours(int n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Montreal"));
		LocalTime resultTime = currentTime.plusHours(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_HH_mm_ss);
		String str = fmt.print(resultTime);

		System.out.println(str);
		return str;

	}


	/**
	 * Adds or removes(-) 'n' hours to/from currentTime and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String time_MinusHours(int n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Montreal"));
		LocalTime resultTime = currentTime.minusHours(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_HH_mm_ss);
		String str = fmt.print(resultTime);

		System.out.println(str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' Minutes to/from currentTime and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String time_PlusMinutes(int n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Montreal"));
		LocalTime resultTime = currentTime.plusMinutes(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_HH_mm_ss);
		String str = fmt.print(resultTime);

		System.out.println(str);
		return str;

	}


	/**
	 * Adds or removes(-) 'n' Minutes to/from currentTime and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String time_MinusMinutes(int n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Montreal"));
		LocalTime resultTime = currentTime.minusMinutes(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_HH_mm_ss);
		String str = fmt.print(resultTime);

		System.out.println(str);
		return str;

	}
	/**
	 * Adds or removes(-) 'n' Seconds to/from currentTime and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String time_PlusSeconds(int n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Montreal"));
		LocalTime resultTime = currentTime.plusSeconds(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_HH_mm_ss);
		String str = fmt.print(resultTime);

		System.out.println(str);
		return str;

	}


	/**
	 * Adds or removes(-) 'n' Seconds to/from currentTime and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public String time_MinusSeconds(int n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Montreal"));
		LocalTime resultTime = currentTime.minusSeconds(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_HH_mm_ss);
		String str = fmt.print(resultTime);

		System.out.println(str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' Hours, Minutes, Seconds to/from currentTime and returns that time in a specified format
	 * @param n
	 * @return
	 */
	public String time_PlusHoursMinutesSeconds(int hr, int min, int sec, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Montreal"));
		LocalTime resultTime = currentTime.plusHours(hr).plusMinutes(min).plusSeconds(sec);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_HH_mm_ss);
		String str = fmt.print(resultTime);

		System.out.println(str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' Hours, Minutes, Seconds to/from currentTime and returns that time in a specified format
	 * @param n
	 * @return
	 */
	public String time_MinusHoursMinutesSeconds(int hr, int min, int sec, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Montreal"));
		LocalTime resultTime = currentTime.minusHours(hr).minusMinutes(min).minusSeconds(sec);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_HH_mm_ss);
		String str = fmt.print(resultTime);

		System.out.println(str);
		return str;

	}



	public String date_PlusYear(LocalDate date) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate nextYear = date.plusYears(1);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/dd/YYYY");
		String str = fmt.print(nextYear);

		System.out.println(str);
		return str;

	}

	// Returns current date 
	public String date_Now() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
		String 	dateString = format.print(date);
		System.out.println("Date is "+ dateString);
		return dateString;

	}


	SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
	String date = "16-Mar-05";

	public static String date_Parse2(String myDate, String desiredFormat_dd_MMM_yy) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern(desiredFormat_dd_MMM_yy);
		LocalDate localDate2 = dtf.parseLocalDate(myDate);
		System.out.println(myDate + " =>> " + dtf.print(localDate2));
		return dtf.print(localDate2);
	}




	public static String date_Parse3(String myDate, String desiredFormat_dd_MMM_yy) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern(desiredFormat_dd_MMM_yy);

		LocalDate localDate3 = LocalDate.parse(myDate, DateTimeFormat.forPattern(desiredFormat_dd_MMM_yy));
		System.out.println(myDate + " ==>> " + dtf.print(localDate3));
		return dtf.print(localDate3);
	}

	public static String date_Parse4(String myDate, String desiredFormat_dd_MMM_yy) throws Exception {
		DateTimeFormatter FORMATTER = DateTimeFormat.forPattern(desiredFormat_dd_MMM_yy);
		DateTime dateTime = FORMATTER.parseDateTime(myDate);
		LocalDate localDate4 = dateTime.toLocalDate();
		System.out.println(localDate4 + "/" + FORMATTER.print(localDate4));
		return FORMATTER.print(localDate4);
	}



	public LocalDate unmarshal(String date, String desiredFormat) throws Exception {
		if (date == null) {
			return null;
		} else {
			//first way
			final DateTimeFormatter dtf = DateTimeFormat.forPattern(desiredFormat);
			final LocalDate localDate2 = dtf.parseLocalDate(date);

			//second way
			LocalDate localDate3 = LocalDate.parse(date,DateTimeFormat.forPattern(desiredFormat));

			//third way
			DateTimeFormatter FORMATTER = DateTimeFormat.forPattern(desiredFormat);
			DateTime dateTime = FORMATTER.parseDateTime(date);
			LocalDate localDate4 = dateTime.toLocalDate();
			System.out.println(localDate4);
			return localDate4;
		}
	}

	public static String date_unmarshal(String date, String desiredFormat) throws Exception {
		if (date == null) {
			return null;
		} else {
			//first way
			final DateTimeFormatter dtf = DateTimeFormat.forPattern(desiredFormat);
			final LocalDate localDate2 = dtf.parseLocalDate(date);

			//second way
			LocalDate localDate3 = LocalDate.parse(date,DateTimeFormat.forPattern(desiredFormat));

			//third way
			DateTimeFormatter FORMATTER = DateTimeFormat.forPattern(desiredFormat);
			DateTime dateTime = FORMATTER.parseDateTime(date);
			LocalDate localDate4 = dateTime.toLocalDate();
			System.out.println(localDate4);
			return localDate4.toString(desiredFormat);
		}
	}



	public String date_Now(String desiredFormat) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter format = DateTimeFormat.forPattern(desiredFormat);
		String 	dateString = format.print(date);
		System.out.println("Date is "+ dateString);
		return dateString;

	}

	public String date_Plus(String Mydate , String desiredFormat) {
		LocalDate date = LocalDate.parse(Mydate);
		DateTimeFormatter format = DateTimeFormat.forPattern(desiredFormat);
		String 	dateString = format.print(date);
		System.out.println("Date is "+ dateString);
		return dateString;

	}

	// Returns current date time
	public String datetime_Now() {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyyHHmm");
		String 	dateString = format.print(datetime);
		System.out.println("DateTime is: "+ dateString);
		return dateString;
	}


	// Returns current date time
	public String datetime_Now(String desiredFormat) {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormat.forPattern( desiredFormat);
		String 	dateString = format.print(datetime);
		System.out.println("DateTime is: "+ dateString);
		return dateString;
	}


	//Converts or parses a date / datetime in my format to any desired format
	public static String datetime_Parse(String myDateTime, String myDateTimeFormat, String desiredDateTimeFormat) {
		//	DateTime dt = DateTime.parse("Dec-16-2015 00:00", DateTimeFormat.forPattern("MMM-dd-yyyy HH:mm"));
		DateTime dt = DateTime.parse(myDateTime, DateTimeFormat.forPattern(myDateTimeFormat));
		//	dt.toString("MMM-dd-yyyy hh:mm a");
		String dateInDesiredFormat = dt.toString(desiredDateTimeFormat);
		System.out.println("The date "+myDateTime +" in the format "+ myDateTimeFormat + " converted to the desired format "+desiredDateTimeFormat+" =>> " + dt.toString(dateInDesiredFormat));
		return dateInDesiredFormat;

	}

	public String marshal(LocalDate date) throws Exception {
		//return new SimpleDateFormat(pattern).format(date);
		System.out.println();
		return date.toString("dd-MMM-yy");
	}

	public static DateTime getItemDate(String date, String pattern) {
		return DateTimeFormat.forPattern(pattern)
				.parseDateTime(date)
				.withZone(DateTimeZone.getDefault());
	}



	// Parse date to any format (M/d/yyyy)
	public String date_Parse(String date, String desiredFormat) {
		LocalDate dt = LocalDate.parse(date);
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
		String str = fmt.print(dt);
		System.out.println(str);
		return str;

	}

	public String date_Parsing(String date, String desiredFormat) {
		//LocalDate dt = LocalDate.parse(date);
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);

		//	DateTime dt = fmt.parseDateTime(date);
		LocalDate dt = fmt.parseLocalDate(date);

		String str = fmt.print(dt);
		System.out.println(str);
		return str;

	}



	public String date_Parse(LocalDate date, String desiredFormat) {
		LocalDate nextYear = date.plusYears(0);
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
		String str = fmt.print(nextYear);
		System.out.println(str);
		return str;

	}


	public String date_PlusYear(LocalDate date, int n) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate nextYear = date.plusYears(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/dd/YYYY");
		String str = fmt.print(nextYear);

		System.out.println(str);
		return str;

	}


	public String date_PlusYear(String date, int n) {
		LocalDate dt = LocalDate.parse(date);
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Montreal"));

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/dd/YYYY");
		String str = fmt.print(dt);

		System.out.println(str);
		return str;

	}

	public String date_AnyYear(String currentDate, String dateFormat)
			throws ParseException {
		Date dt = date_convertFromString(currentDate, dateFormat);
		calendar.setTime(dt);
		calendar.add(Calendar.YEAR, 1);
		Date nextYear = calendar.getTime();
		System.out.format("next year:  %s\n", nextYear);
		return nextYear.toString();
	}

	public Date date_convertFromString(String stringDate, String dateFormat)
			throws ParseException {
		// String stringDate = "January 2, 2010";
		// DateFormat format = new SimpleDateFormat("MMMM d, yyyy",
		// Locale.ENGLISH);
		DateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);

		Date date = format.parse(stringDate);
		System.out.println(date); // Sat Jan 02 00:00:00 GMT 2010
		return date;
	}

	public void date_examples() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.set(Calendar.HOUR, 6); // 6 means 7 AM
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println(cal.getTime());
		Date now = new Date();
		long delay = cal.getTime().getTime() - now.getTime();
		System.out.println(delay);
	}

	public void date_joda() {
		// get a calendar instance at December 31, 2009, at 11:30 p.m.
		// this way we can test that we are rolling over to the next hour,
		// tomorrow, next week, and next year properly.
		Calendar calendar = new GregorianCalendar(2009, 11, 31, 23, 30, 0);

		// get a Date instance to represent "now" (the current date);
		// we'll need it to reset our calendar during the following date
		// examples.
		Date currentDate = calendar.getTime();
		System.out.format("today:      %s\n", currentDate);

		// get the date/time one hour from now
		calendar.setTime(currentDate);
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		Date oneHour = calendar.getTime();
		System.out.format("one hour:   %s\n", oneHour);

		// get tomorrow's date
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		System.out.format("tomorrow:   %s\n", tomorrow);

		// get next week's date
		// note: may want to use WEEK_OF_MONTH or WEEK_OF_YEAR
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		Date nextWeek = calendar.getTime();
		System.out.format("next week:  %s\n", nextWeek);

		// get next month
		calendar.setTime(currentDate);
		calendar.add(Calendar.MONTH, 1);
		Date nextMonth = calendar.getTime();
		System.out.format("next month: %s\n", nextMonth);

		// get next year
		calendar.setTime(currentDate);
		calendar.add(Calendar.YEAR, 1);
		Date nextYear = calendar.getTime();
		System.out.format("next year:  %s\n", nextYear);
	}

	public String dateParse(String date) throws ParseException {
		/*
		 * DateTimeFormatter formatter =
		 * DateTimeFormat.forPattern("MM/dd/yyyy").withLocale(Locale.US);
		 * SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 * 
		 * LocalDate now = new LocalDate(); //Today's date LocalDate later =
		 * now.plusDays(days); return sdf.format(later);
		 */

		String dateReceivedFromUser = date;
		DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat dateFormatNeeded = new SimpleDateFormat("yyyyMMdd");
		Date date1 = userDateFormat.parse(dateReceivedFromUser);
		String convertedDate = dateFormatNeeded.format(date1);

		return convertedDate;
		/*
		 * String DATE_FORMAT = "yyyy/MM/dd"; SimpleDateFormat sdf = new
		 * SimpleDateFormat(DATE_FORMAT); System.out.println("Formated Date " +
		 * sdf.format(date));
		 */
	}

	/**
	 * parses one date format to another
	 * @param n
	 * @return
	 */
	public String dateParse(String date, String userDateFormatArg, String dateFormatNeededArg ) throws ParseException {
		/*
		 * DateTimeFormatter formatter =
		 * DateTimeFormat.forPattern("MM/dd/yyyy").withLocale(Locale.US);
		 * SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 * 
		 * LocalDate now = new LocalDate(); //Today's date LocalDate later =
		 * now.plusDays(days); return sdf.format(later);
		 */

		String dateReceivedFromUser = date;
		DateFormat userDateFormat = new SimpleDateFormat(userDateFormatArg);
		DateFormat dateFormatNeeded = new SimpleDateFormat(dateFormatNeededArg);
		Date date1 = userDateFormat.parse(dateReceivedFromUser);
		String convertedDate = dateFormatNeeded.format(date1);

		return convertedDate;
		/*
		 * String DATE_FORMAT = "yyyy/MM/dd"; SimpleDateFormat sdf = new
		 * SimpleDateFormat(DATE_FORMAT); System.out.println("Formated Date " +
		 * sdf.format(date));
		 */
	}

	/*
	 * public static void writeCsvFile(String fileName) {
	 * 
	 * 
	 * //Delimiter used in CSV file private static final String COMMA_DELIMITER
	 * = ","; private static final String NEW_LINE_SEPARATOR = "\n";
	 * 
	 * //CSV file header private static final String FILE_HEADER =
	 * "id,firstName,lastName,gender,age"; //Create new students objects Student
	 * student1 = new Student(1, "Ahmed", "Mohamed", "M", 25); Student student2
	 * = new Student(2, "Sara", "Said", "F", 23); Student student3 = new
	 * Student(3, "Ali", "Hassan", "M", 24); Student student4 = new Student(4,
	 * "Sama", "Karim", "F", 20); Student student5 = new Student(5, "Khaled",
	 * "Mohamed", "M", 22); Student student6 = new Student(6, "Ghada", "Sarhan",
	 * "F", 21);
	 * 
	 * //Create a new list of student objects List students = new ArrayList();
	 * students.add(student1); students.add(student2); students.add(student3);
	 * students.add(student4); students.add(student5); students.add(student6);
	 * 
	 * FileWriter fileWriter = null;
	 * 
	 * try { fileWriter = new FileWriter(fileName);
	 * 
	 * //Write the CSV file header fileWriter.append(FILE_HEADER.toString());
	 * 
	 * //Add a new line separator after the header
	 * fileWriter.append(NEW_LINE_SEPARATOR);
	 * 
	 * //Write a new student object list to the CSV file for (Student student :
	 * students) { fileWriter.append(String.valueOf(student.getId()));
	 * fileWriter.append(COMMA_DELIMITER);
	 * fileWriter.append(student.getFirstName());
	 * fileWriter.append(COMMA_DELIMITER);
	 * fileWriter.append(student.getLastName());
	 * fileWriter.append(COMMA_DELIMITER);
	 * fileWriter.append(student.getGender());
	 * fileWriter.append(COMMA_DELIMITER);
	 * fileWriter.append(String.valueOf(student.getAge()));
	 * fileWriter.append(NEW_LINE_SEPARATOR); }
	 * 
	 * 
	 * 
	 * System.out.println("CSV file was created successfully !!!");
	 * 
	 * } catch (Exception e) { System.out.println("Error in CsvFileWriter !!!");
	 * e.printStackTrace(); } finally {
	 * 
	 * try { fileWriter.flush(); fileWriter.close(); } catch (IOException e) {
	 * System.out.println("Error while flushing/closing fileWriter !!!");
	 * e.printStackTrace(); }
	 * 
	 * } }
	 */

	public void readPropertyFile() {
		Properties prop = new Properties();
		String sPropVal = "";
		String fileName = "D:\\dataset.properties";

		try {
			File file = new File(fileName);
			if (!file.exists()) {
				System.err.println("Error - property file " + fileName
						+ " does not exist.");
			}

			// assign property value
			try {
				FileInputStream fInputStream = null;
				try {
					fInputStream = new FileInputStream(file);
					prop.load(fInputStream);
				} finally {
					if (fInputStream != null) {
						fInputStream.close();
					}
				}
				sPropVal = prop.getProperty("gsTestURL");
				System.out.println(sPropVal);
			} catch (Exception e) {
				System.err.println("Error - reading property from file "
						+ fileName + ".");
			}
		} finally {
			System.out.println("finally");
		}
	}

	public static void checkEmail(String host, String storeType, String user,
			String password) {
		try {

			// create properties field
			Properties properties = new Properties();

			/*
			 * properties.put("mail.pop3.host", host);
			 * properties.put("mail.pop3.port", "995");
			 * properties.put("mail.pop3.starttls.enable", "true");
			 */
			/*
			 * properties.put("pop.gmail.com", host);
			 * properties.put("pop.gmail.port", "995");
			 * properties.put("pop.gmail.starttls.enable", "true");
			 */
			properties.put("mail.sas.gmail.com", host);
			properties.put("mail.sas.gmail.com.port", "995");
			properties.put("mail.sas.gmail.com.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("pop3s");

			store.connect(host, user, password);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();
			System.out.println("messages.length---" + messages.length);

			for (int i = 0, n = messages.length; i < n; i++) {
				Message message = messages[i];
				System.out.println("---------------------------------");
				System.out.println("Email Number " + (i + 1));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Text: " + message.getContent().toString());

			}

			// close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readEmail() {
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		String username = "username";
		String password = "password";

		try {
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect("imap.gmail.com", username, password);
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			int countOfEmails = inbox.getMessageCount();
			Message msg = inbox.getMessage(inbox.getMessageCount());
			Address[] in = msg.getFrom();
			for (Address address : in) {
			}
			MimeMultipart mp = (MimeMultipart) msg.getContent();
			MimeBodyPart bp = (MimeBodyPart) mp.getBodyPart(1);
//		bp.saveFile(bp.getFileName());
			System.out.println("SENT DATE:" + msg.getSentDate());
			System.out.println("SUBJECT:" + msg.getSubject());
			System.out.println("CONTENT:" + bp.getContent());

		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}

	public static void readEmail(String username, String password) {
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");

		try {
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect("mail.sas.gmail.com", username, password);
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			int countOfEmails = inbox.getMessageCount();
			Message msg = inbox.getMessage(inbox.getMessageCount());
			Address[] in = msg.getFrom();
			for (Address address : in) {
			}
			MimeMultipart mp = (MimeMultipart) msg.getContent();
			MimeBodyPart bp = (MimeBodyPart) mp.getBodyPart(1);
 //			bp.saveFile(bp.getFileName());
			System.out.println("SENT DATE:" + msg.getSentDate());
			System.out.println("SUBJECT:" + msg.getSubject());
			System.out.println("CONTENT:" + bp.getContent());

		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}

	public static void fetchEmail() throws Exception {
	//	String UserId= new Configuration().getProperty("ldapUserID");
 //		String ldapPassword= PasswordUtils.decryptString(new Configuration().getProperty("ldapPassword"));
		// SUBSTITUTE YOUR ISP's POP3 SERVER HERE!!!
		String host = "gmail.com";
		// SUBSTITUTE YOUR USERNAME AND PASSWORD TO ACCESS E-MAIL HERE!!!
		String user = "a@a.com";
		String password ="pass";
		// SUBSTITUTE YOUR SUBJECT SUBSTRING TO SEARCH HERE!!!
		String subjectSubstringToSearch = "Test E-Mail through Java";

		// Get a session. Use a blank Properties object.
		Session session = Session.getInstance(new Properties());

		try {

			// Get a Store object
			Store store = session.getStore("pop3");
			store.connect(host, user, password);

			// Get "INBOX"
			Folder fldr = store.getFolder("INBOX");
			fldr.open(Folder.READ_WRITE);
			int count = fldr.getMessageCount();
			System.out.println(count + " total messages");

			// Message numebers start at 1
			for (int i = 1; i <= count; i++) {
				// Get a message by its sequence number
				Message m = fldr.getMessage(i);

				// Get some headers
				Date date = m.getSentDate();
				Address[] from = m.getFrom();
				String subj = m.getSubject();
				String mimeType = m.getContentType();
				System.out.println(date + "\t" + from[0] + "\t" + subj + "\t"
						+ mimeType);
			}

			// Search for e-mails by some subject substring
			String pattern = subjectSubstringToSearch;
			SubjectTerm st = new SubjectTerm(pattern);
			// Get some message references
			Message[] found = fldr.search(st);

			System.out.println(found.length
					+ " messages matched Subject pattern \"" + pattern + "\"");

			for (int i = 0; i < found.length; i++) {
				Message m = found[i];
				// Get some headers
				Date date = m.getSentDate();
				Address[] from = m.getFrom();
				String subj = m.getSubject();
				String mimeType = m.getContentType();
				System.out.println(date + "\t" + from[0] + "\t" + subj + "\t"
						+ mimeType);

				Object o = m.getContent();
				if (o instanceof String) {
					System.out.println("**This is a String Message**");
					System.out.println((String) o);
				} else if (o instanceof Multipart) {
					System.out.print("**This is a Multipart Message.  ");
					Multipart mp = (Multipart) o;
					int count3 = mp.getCount();
					System.out.println("It has " + count3
							+ " BodyParts in it**");
					for (int j = 0; j < count3; j++) {
						// Part are numbered starting at 0
						BodyPart b = mp.getBodyPart(j);
						String mimeType2 = b.getContentType();
						System.out.println("BodyPart " + (j + 1)
								+ " is of MimeType " + mimeType);

						Object o2 = b.getContent();
						if (o2 instanceof String) {
							System.out.println("**This is a String BodyPart**");
							System.out.println((String) o2);
						} else if (o2 instanceof Multipart) {
							System.out
							.print("**This BodyPart is a nested Multipart.  ");
							Multipart mp2 = (Multipart) o2;
							int count2 = mp2.getCount();
							System.out.println("It has " + count2
									+ "further BodyParts in it**");
						} else if (o2 instanceof InputStream) {
							System.out
							.println("**This is an InputStream BodyPart**");
						}
					} // End of for
				} else if (o instanceof InputStream) {
					System.out.println("**This is an InputStream message**");
					InputStream is = (InputStream) o;
					// Assumes character content (not binary images)
					int c;
					while ((c = is.read()) != -1) {
						System.out.write(c);
					}
				}

				// Uncomment to set "delete" flag on the message
				// m.setFlag(Flags.Flag.DELETED,true);

			} // End of for

			// "true" actually deletes flagged messages from folder
			fldr.close(true);
			store.close();

		} catch (MessagingException mex) {
			// Prints all nested (chained) exceptions as well
			mex.printStackTrace();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}

	}

	// Command Prompt
/*
	public void cmd(String command) {
		Runtime runTime = Runtime.getRuntime();

		try {
			runTime.exec(new String[] { "cmd.exe", "/c", "start" });
		} catch (IOException e) {

			e.printStackTrace();
		}
		((ITopWindow) objcit.commandPromptwindow).inputKeys(command + "{ENTER}"
				+ "exit" + "{ENTER}");

	}
*/
	public void command() throws IOException {
		ProcessBuilder builder = new ProcessBuilder(
				// "cmd.exe", "/c",
				// "cd \"C:\\Program Files\\Microsoft SQL Server\" && dir");
				"cmd.exe",
				"/c",
				"cd \"D:\\AutomationProjects\\ExternalApps\\jmeter\\bin\\"
						+ "jmeter  -n -t D:\\AutomationProjects\\Automation_Selenium\\webservices\\NonEsiMecWebService.jmx");

		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
	}

	public void runBatch(String batchFilePath) {
		try {
			String[] command = { "cmd.exe", "/C", "Start", batchFilePath };
			Process p = Runtime.getRuntime().exec(command);
		} catch (IOException ex) {
		}
	}


	// Runs the Webservice by launching CMD window to run Windows Shell/Batch file that calls external system (JMeter) input/request/payload
	public void runWebService(String batchFilePath) {
		try {
			String[] command = { "cmd.exe", "/C", "Start", batchFilePath };
			Process p = Runtime.getRuntime().exec(command);
		} catch (IOException ex) {
		}
	}

	// Command Prompt

/*	public void putty(String command) {
		Runtime runTime = Runtime.getRuntime();

		try {
			runTime.exec(new String[] { "putty.exe", "/c", "start" });
		} catch (IOException e) {

			e.printStackTrace();
		}
		((ITopWindow) objcit.commandPromptwindow).inputKeys(command + "{ENTER}"
				+ "exit" + "{ENTER}");

	}*/

	public void commandPrompt() {
		try {
			Runtime.getRuntime().exec("cmd.exe /c start");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// cWindowsSystem32CmdExewindow().inputKeys("ipconfig{ENTER}");
	}

	public void commandPrompt(String command) {
		try {
			Runtime.getRuntime().exec("cmd.exe /c start");
		} catch (IOException e) {

			e.printStackTrace();
		}
		// cWindowsSystem32CmdExewindow().inputKeys("ipconfig{ENTER}");
	}

	public void runCommand() {
		try {
			Process p = Runtime.getRuntime().exec("cmd /c dir");
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

		} catch (IOException e1) {
		} catch (InterruptedException e2) {
		}

		System.out.println("Done");
	}

	// mouse cursor position
	public static void moveMouse(Point p) {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();

		// Search the devices for the one that draws the specified point.
		for (GraphicsDevice device : gs) {
			GraphicsConfiguration[] configurations = device.getConfigurations();
			for (GraphicsConfiguration config : configurations) {
				Rectangle bounds = config.getBounds();
				if (bounds.contains(p)) {
					// Set point to screen coordinates.
					Point b = bounds.getLocation();
					Point s = new Point(p.x - b.x, p.y - b.y);

					try {
						Robot r = new Robot(device);
						r.mouseMove(s.x, s.y);
					} catch (AWTException e) {
						e.printStackTrace();
					}

					return;
				}
			}
		}
		// Couldn't move to the point, it may be off screen.
		return;
	}
  
 
  

	/**
	 * Returns whether or not a value is a date.
	 */
	public static boolean isDate(String value, String dateFormat) {
		return parseDate(value, dateFormat) != null;
	}

	/**
	 * Returns whether the second value is after the first value. Only works if
	 * date format matches.
	 */
	public static boolean isDateAfter(String date, String after,
			String dateFormat) {
		Date date1 = parseDate(date, dateFormat);
		Date date2 = parseDate(after, dateFormat);

		if (date1 == null || date2 == null) {
			return false;
		}

		return date1.after(date2);
	}

	/**
	 * Returns whether the second value is before the first value. Only works if
	 * date format matches.
	 */
	public static boolean isDateBefore(String date, String before,
			String dateFormat) {
		Date date1 = parseDate(date, dateFormat);
		Date date2 = parseDate(before, dateFormat);

		if (date1 == null || date2 == null) {
			return false;
		}

		return date1.before(date2);
	}

 
	/**
	 * Changes the format of a date from one format to another
	 */
	public static String formatDate(String dateStr, String dateFormat,
			String desiredFormat) {
		Date date = parseDate(dateStr, dateFormat);
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					desiredFormat);
			System.out.println("Date in format " + dateFormat+  " changed to "+ desiredFormat + " = " + simpleDateFormat.format(date));
			return simpleDateFormat.format(date);
		}

		else if (dateStr == "" || dateStr.equals("") || dateStr == " "
				|| dateStr.equals(" ") || dateStr.contains("~")
				|| dateStr.equalsIgnoreCase("NA") || dateStr.equals("-")) {
			System.out.println("Date" + " = " + "NA");

			return "";
		} else {
			System.out.println("Date" + " = " + "null");
			return null;
		}

	}

	// Automation Date Format
	public static String AutomationDateFormat(String dateValue) {
		if ((dateValue.substring(0, dateValue.indexOf("/")).length() == 2))
			return "MM/D/yyyy";
		else if ((dateValue.substring(0, dateValue.indexOf("~")).length() == 0))
			return null;
		else if ((dateValue.substring(0, dateValue.indexOf("")).length() == 0))
			return null;
		else
			return "M/D/yyyy";
	}

	/**
	 * Checks to see if a file or directory exists
	 */
	public boolean fileExists(String filePath) {
		return new File(filePath).exists();
	}

	private static Date parseDate(String value, String dateFormat) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			simpleDateFormat.setLenient(false);
			return simpleDateFormat.parse(value);
		} catch (ParseException pe) {
			return null;
		}
	}
 
	/**
	 * Returns the string with all leading and trailing white spaces
	 */
	public static String trim(String str) {
		// handle null value;
		if (str == null) {
			return str;
		} else {
			return str.trim();
		}

	}// end of trim

	/**
	 * Returns the string with in GMT date/time formatted for web service
	 * testing int is the offset amount;
	 * 
	 */
	public static String getGMTDate(int hourOffset) {
		long hour = 3600 * 1000; // in milli-seconds

		// getting
		Date date = new Date();
		Date date2 = new Date(date.getTime() + hourOffset * hour);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf2.setTimeZone(TimeZone.getTimeZone("GMT"));

		String formattedwDate = sdf2.format(date2).toString();

		return formattedwDate;
	}  
	/**
	 * 
	 * Returns a string with the current Date and time value of
	 * yyyy-MM-dd_HH:mm:ss
	 * 
	 * Excel saves all date fields as a Serial date value.
	 */
	public static String getCurrentDateTime() {
		String formatDate;

		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		// get current date time with Date()
		Date date = new Date();

		System.out.println(dateFormat.format(date));
		formatDate = dateFormat.format(date.getTime());
		System.out.println(formatDate);

		return formatDate;

	} 
	
	/**
	 * Time Difference using Joda
	 * @param dateStart
	 * @param dateStop
	 * @param format_like_MMddyyyyHHmmss
	 */
	public static void JodaDateDifference(String dateStart, String dateStop, String format_like_MMddyyyyHHmmss)
	{

		//	String dateStart = "01/14/2012 09:29:58";
		//	String dateStop = "01/15/2012 10:31:48";

		//	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat(format_like_MMddyyyyHHmmss);
		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			DateTime dt1 = new DateTime(d1);
			DateTime dt2 = new DateTime(d2);

			System.out.print(Days.daysBetween(dt1, dt2).getDays() + " days, ");
			System.out.print(Hours.hoursBetween(dt1, dt2).getHours() % 24 + " hours, ");
			System.out.print(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " minutes, ");
			System.out.print(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 + " seconds.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static int  getJodaDateDifferenceInMinutes(String dateStart, String dateStop, String format_like_MMddyyyyHHmmss)
	{
		//	String dateStart = "01/14/2012 09:29:58";
		//	String dateStop = "01/15/2012 10:31:48";

		//	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat(format_like_MMddyyyyHHmmss);
		Date d1 = null;
		Date d2 = null;
		int min = 0;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			DateTime dt1 = new DateTime(d1);
			DateTime dt2 = new DateTime(d2);

			System.out.println("The date parameters difference is as below");
			System.out.print(Days.daysBetween(dt1, dt2).getDays() + " days, ");
			System.out.print(Hours.hoursBetween(dt1, dt2).getHours() % 24 + " hours, ");
			System.out.print(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " minutes, ");
			System.out.print(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 + " seconds.");
			min = Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 ;
			System.out.println("--");
			System.out.println("The difference between two timestamps is "+ min + " Minutes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return min ;
	}


	/**
	 * Date Difference
	 */
	public static void DateDifference(String dateStart, String dateStop, String format_like_MMddyyyyHHmmss)   {

		//String dateStart = "01/14/2012 09:29:58";
		//String dateStop = "01/15/2012 10:31:48";

		//HH converts hour in 24 hours format (0-23), day calculation
		//SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat("format_like_MMddyyyyHHmmss");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public long getTimeDifferenceInHours(Date startDate, Date  endDate) {



		long duration  = endDate.getTime() - startDate.getTime();

		long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
		long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
		return diffInHours;
	}


	public long getTimeDifferenceInMinutes(String startTime1, String  endTime1) {
		DateTime startTime = null, endTime = null;
		Period p = new Period(startTime, endTime);
		long hours = p.getHours();
		long minutes = p.getMinutes();
		return minutes;

		//Interval interval = new Interval(oldTime, new Instant());
	}

	public static void getDateDifference() {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.set(2007, 01, 10);
		calendar2.set(2007, 07, 01);
		long milliseconds1 = calendar1.getTimeInMillis();
		long milliseconds2 = calendar2.getTimeInMillis();
		long diff = milliseconds2 - milliseconds1;
		long diffSeconds = diff / 1000;
		long diffMinutes = diff / (60 * 1000);
		long diffHours = diff / (60 * 60 * 1000);
		long diffDays = diff / (24 * 60 * 60 * 1000);
		System.out.println("\nThe Date Different Example");
		System.out.println("Time in milliseconds: " + diff
				+ " milliseconds.");
		System.out.println("Time in seconds: " + diffSeconds
				+ " seconds.");
		System.out.println("Time in minutes: " + diffMinutes 
				+ " minutes.");
		System.out.println("Time in hours: " + diffHours 
				+ " hours.");
		System.out.println("Time in days: " + diffDays 
				+ " days.");
	}


	/**
	 * the input dob format is YYYY-MM-DD
	 * 
	 * @param dob
	 * @return age
	 */
	public static int calculateAge(String dob) {
		int age = 0;

		int yearDOB = Integer.parseInt(dob.substring(0, 4));
		int monthDOB = Integer.parseInt(dob.substring(5, 7));
		int dayDOB = Integer.parseInt(dob.substring(8, 10));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		int thisYear = Integer.parseInt(dateFormat.format(date));

		dateFormat = new SimpleDateFormat("MM");
		date = new java.util.Date();
		int thisMonth = Integer.parseInt(dateFormat.format(date));

		dateFormat = new SimpleDateFormat("dd");
		date = new java.util.Date();
		int thisDay = Integer.parseInt(dateFormat.format(date));

		age = thisYear - yearDOB;
		if (thisMonth < monthDOB) {
			age = age - 1;
		}
		if (thisMonth == monthDOB && thisDay < dayDOB) {
			age = age - 1;
		}
		System.out.println("The age of user is : " + age);

		return age;

	}// end of calculateAge

	/*
	 * Excel phone number is read into Eclipse sometimes as 7.634331234E9
	 */
	public static String convertToPhoneNumber(String number) {
		if (number == null) {
			return number;
		}
		String phoneNumber = number;

		if (number.contains("E")) {
			// Some of the phone numbers have E9 when it read the excel number.
			int positionOfChar = number.indexOf(".");
			int positionOfEndChar = number.indexOf("E");
			String test2 = number.substring(0, positionOfChar);
			String test3 = number.substring(positionOfChar + 1,
					positionOfEndChar);
			phoneNumber = test2 + test3;
			while (phoneNumber.length() < 10) {
				phoneNumber = phoneNumber + "0";
			}
		}

		return phoneNumber;
	}

	 

	/**
	 * remove the -#### if it exits
	 * 
	 * @param fullZipcode
	 * @return
	 */
	public static String plainZipCode(String fullZipcode) {
		int postionOfEndingDash;
		int stingLength = fullZipcode.length();
		String zipCode;
		postionOfEndingDash = fullZipcode.indexOf("-");
		if (stingLength > 0 && (postionOfEndingDash > 0)) {
			// postionOfEndingDash = fullZipcode.indexOf("-");
			// getting text between the [ ]
			zipCode = fullZipcode.substring(0, postionOfEndingDash);
		} else {
			zipCode = fullZipcode;
		}

		return zipCode;
	}

	/*
	 * Return the content of the clip board.
	 */
	public static String getClipboard() {
		// get the system clip board

		Clipboard systemClipboard = Toolkit.getDefaultToolkit()
				.getSystemClipboard();
		// get the contents on the clip board in a
		// transferable object
		Transferable clipboardContents = systemClipboard.getContents(null);
		// check if clip board is empty
		if (clipboardContents == null) {
			return ("Clipboard is empty!!!");
		} else
			try {
				// see if DataFlavor of
				// DataFlavor.stringFlavor is supported
				if (clipboardContents
						.isDataFlavorSupported(DataFlavor.stringFlavor)) {
					// return text content
					String returnText = (String) clipboardContents
							.getTransferData(DataFlavor.stringFlavor);
					return returnText;
				}
			} catch (UnsupportedFlavorException ufe) {
				ufe.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		return null;
	}

	 
	/*
	 * Parameter String fileName: This is a PDF filename that will hold all of
	 * the images/screen shots. Parameter boolean firstImage: This is a flag
	 * indicating if we are adding the first image/screen shot. If firstImage is
	 * true the method adds a Title Page, otherwise the new image is added in
	 * the PDF file as represented in fileName variable. This method will always
	 * add the file under ScreenShots\screenShot.png
	 */
	public static void takeScreenShotAndAddToPDF(String folderPath,
			String fileName, boolean firstImage) {

		try {
			if (firstImage) {
				pdfPageCount = 1;
			} else {
				pdfPageCount++;
			}// end if else

			// take the screen shot
			takeScreenShot(folderPath);
			// Document1 will always store the current screen shot.
			Document document1 = new Document();
			document1.setPageSize(PageSize.A4);
			// add the screen shot to a PDf file.
			PdfWriter.getInstance(document1, new FileOutputStream(folderPath
					+ "/ScreenShots/temp/image2.pdf"));
			document1.open();
			String currentTime = Calendar.getInstance().getTime().toString();
			document1.add(new Paragraph(currentTime));
			Image image = Image.getInstance(folderPath
					+ "/ScreenShots/temp/screenShot.png");
			image.scaleToFit(500, 600);
			document1.add(image);
			Paragraph pageNumber = new Paragraph();
			// pageNumber.setAlignment(Element.ALIGN_BOTTOM);
			pageNumber.add("Page Number " + pdfPageCount);
			document1.add(pageNumber);
			document1.close();
			List<InputStream> inputFiles = new ArrayList<InputStream>();
			if (firstImage) {
				inputFiles.add(new FileInputStream(folderPath
						+ "/ScreenShots/temp/TitlePage.pdf"));
				inputFiles.add(new FileInputStream(folderPath
						+ "/ScreenShots/temp/image2.pdf"));

			} else {
				inputFiles.add(new FileInputStream(folderPath
						+ "/ScreenShots/temp/Copy.pdf"));
				inputFiles.add(new FileInputStream(folderPath
						+ "/ScreenShots/temp/image2.pdf"));
			}
			// outputFile is the final PDF file that will hold all Screen shots.
			FileOutputStream outputFile = new FileOutputStream(folderPath
					+ "/ScreenShots/" + fileName);
			doMerge(inputFiles, outputFile);
			// we need an additional copy to re-use as input file on next call
			// to doMerge
			copyFile(new File(folderPath + "/ScreenShots/" + fileName),
					new File(folderPath + "/ScreenShots/temp/Copy.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}// end try
	}// end takeScreenShotAndAddToPDF

	public static void titleForPDFScreenShots(String PdfScreenShotFileTitle,
			String folderPath, String fileName) {
		Document document2 = new Document();
		document2.setPageSize(PageSize.A4);
		try {
			createDirectories(folderPath + "/ScreenShots/temp/TitlePage.pdf");
			FileOutputStream titlePage = new FileOutputStream(folderPath
					+ "/ScreenShots/temp/TitlePage.pdf");
			PdfWriter.getInstance(document2, titlePage);
			document2.open();
			Paragraph title = new Paragraph();
			title.setAlignment(1);
			title.add(PdfScreenShotFileTitle);
			title.add("PDF Screenshot FileName: " + fileName);
			document2.add(title);
			document2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {

			e.printStackTrace();
		}// end try catch
	}// writeTitleForPDFScreenShots

	public static void addTextToPDFScreenShots(String PdfScreenShotFileText,
			String folderPath, String fileName) {
		Document document2 = new Document();
		document2.setPageSize(PageSize.A4);
		try {
			PdfWriter.getInstance(document2, new FileOutputStream(folderPath
					+ "/ScreenShots/temp/image2.pdf"));
			document2.open();
			Paragraph title = new Paragraph();
			// title.setAlignment(1);
			title.add(PdfScreenShotFileText);
			document2.add(title);
			document2.close();
			List<InputStream> inputFiles = new ArrayList<InputStream>();
			inputFiles.add(new FileInputStream(folderPath
					+ "/ScreenShots/temp/Copy.pdf"));
			inputFiles.add(new FileInputStream(folderPath
					+ "/ScreenShots/temp/image2.pdf"));
			// outputFile is the final PDF file that will hold all Screen shots.
			FileOutputStream outputFile = new FileOutputStream(folderPath
					+ "/ScreenShots/" + fileName);
			doMerge(inputFiles, outputFile);
			// we need an additional copy to re-use as input file on next call
			// to doMerge
			copyFile(new File(folderPath + "/ScreenShots/" + fileName),
					new File(folderPath + "/ScreenShots/temp/Copy.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// end try catch
	}// writeTitleForPDFScreenShots

	/**
	 * Merge multiple pdf into one pdf
	 * 
	 * @param list
	 *            of pdf input stream
	 * @param outputStream
	 *            output file output stream
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void doMerge(List<InputStream> list, OutputStream outputStream)
			throws DocumentException, IOException {
		try {
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);
			document.open();
			PdfContentByte cb = writer.getDirectContent();

			for (InputStream in : list) {
				PdfReader reader = new PdfReader(in);
				for (int i = 1; i <= reader.getNumberOfPages(); i++) {
					document.newPage();
					// import the page from source pdf
					PdfImportedPage page = writer.getImportedPage(reader, i);
					// add the page to the destination pdf
					cb.addTemplate(page, 0, 0);
				}// end for
			}// end for
			outputStream.flush();
			document.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end doMerge

	/*
	 * This method will take a screen shot of current screen and store it in
	 * ...\ScreenShots\screenShot.png file.
	 */
	public static void takeScreenShot(String folderPath) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		Rectangle screenRect = new Rectangle(screenSize);
		// create screen shot
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		BufferedImage image = robot.createScreenCapture(screenRect);
		// save captured image to PNG file
		try {
			// create the ScreenShots Directory if does not exists.
			File theDir = new File(folderPath + "/ScreenShots/temp");
			if (!theDir.exists()) {
				theDir.mkdirs();
			}
			ImageIO.write(image, "png", new File(folderPath
					+ "/ScreenShots/temp/screenShot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// give feedback
		System.out.println("Saved screen shot (" + image.getWidth() + " x "
				+ image.getHeight() + " pixels) to file" + folderPath
				+ "/ScreenShots/temp/screenShot.png ");
	}

	/*
	 * Copy sourceFile into the destFile.
	 */
	public static void copyFile(File sourceFile, File destFile)
			throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}// end if
		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}// end if
			if (destination != null) {
				destination.close();
			}// end if
		}// end try catch finally
	}// end copyFile

	/*
	 * Creates directories
	 * 
	 * @filemane = full pathname and filename
	 */
	public static boolean createDirectories(String filename) {
		boolean dirCreated = false;

		File file = new File(filename);

		System.out.println("getParent " + file.getParent());

		File dir = new File(file.getParent());
		if (!dir.exists()) {
			if (dir.mkdirs()) {
				System.out.println("Multiple directories are created!");
				dirCreated = true;
			} else {
				System.out.println("Failed to create multiple directories!");
			}
		}
		return dirCreated;

	}

	 
	/*
	 * Takes the Refernces numbe and appends to the pdf file name and deletes
	 * the orginal file
	 */

	public static String addRefernceNumbertoPDFfile(String pdfPath,
			String pdfFilename, String refNum) {

		int lastLocation = pdfFilename.lastIndexOf(".");
		String tempName = pdfFilename.substring(0, lastLocation);
		String newName = tempName + "RefNu_" + refNum + ".pdf";
		// Utils.copyFile(pdfPath +pdfFilename, pdfPath +newName);

		try {
			copyFile(new File(pdfPath + pdfFilename), new File(pdfPath
					+ newName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File(pdfPath + "/" + pdfFilename);
		// boolean success = f.delete();

		return newName;
	}

	// ***Common UTILS ****

	// Sort order
	public static enum SortOrder {
		ASCENDING, DESCENDING
	}

	/**
	 * Check that all the values in the list are the same as the passed in value
	 * 
	 * @param items
	 * @param value
	 * @return
	 */
	public static <T> boolean assertAllElementsMatch(List<T> items, Object value) {
		for (T item : items) {
			if (item == null || !item.equals(value))
				return false;
		}

		return true;
	}

	 
	 
	 

	/**
	 * Calculate the last number of pagination
	 */
	public static int calculateLastPage(int totalRecords, int pageSize) {
		// If we are displaying more than we have, then only one page
		if (pageSize > totalRecords)
			return 1;

		return (int) Math.ceil(totalRecords * 1d / pageSize);
	}

	/**
	 * Checks if a list is sorted
	 * 
	 * @param list
	 *            Items in the list must implement Comparable
	 * @return true or false
	 */
	public static <T extends Comparable<? super T>> boolean isSorted(List<T> a,
			SortOrder order) {
		return isSorted(a, order, null);
	}

	/**
	 * Checks if a list is sorted
	 * 
	 * @param list
	 *            Items in the list must implement Comparable
	 * @return true or false
	 */
	public static <T extends Comparable<? super T>> boolean isSorted(List<T> a,
			SortOrder order, Comparator<T> comparator) {

		Ordering<T> ordering;
		if (comparator == null) {
			ordering = Ordering.natural();
		} else {
			ordering = Ordering.from(comparator);
		}

		switch (order) {
		case ASCENDING:
			return ordering.isOrdered(a);
		case DESCENDING:
			return ordering.reverse().isOrdered(a);
		default:
			return false;
		}
	}

	/**
	 * Comare two images
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	public static boolean imageEquals(String src, String dest) {

		try {

			// Load the images
			BufferedImage a = ImageIO.read(new File(src));
			BufferedImage b = ImageIO.read(new File(dest));

			// Get the data information
			Raster r1 = a.getData();
			Raster r2 = b.getData();

			// Check image bands, height, width
			if (r1.getNumBands() != r2.getNumBands()
					|| r1.getWidth() != r2.getWidth()
					|| r1.getHeight() != r2.getHeight())
				return false;

			// Check pixel by pixel
			for (int i = 0; i < r1.getNumBands(); i++) {
				for (int x = 0; x < r1.getWidth(); x++) {
					for (int y = 0; y < r1.getHeight(); y++) {
						if (r1.getSample(x, y, i) != r2.getSample(x, y, i)) {
							return false;
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * Comare two images
	 * 
	 * @param src
	 *            Source Image
	 * @param dest
	 *            Destination Image
	 * @return
	 */
	public static boolean imageEquals(BufferedImage src, BufferedImage dest) {

		// Get the data information
		Raster r1 = src.getData();
		Raster r2 = dest.getData();

		// Check image bands, height, width
		if (r1.getNumBands() != r2.getNumBands()
				|| r1.getWidth() != r2.getWidth()
				|| r1.getHeight() != r2.getHeight())
			return false;

		// Check pixel by pixel
		for (int i = 0; i < r1.getNumBands(); i++) {
			for (int x = 0; x < r1.getWidth(); x++) {
				for (int y = 0; y < r1.getHeight(); y++) {
					if (r1.getSample(x, y, i) != r2.getSample(x, y, i)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	 

	public static void killAllProcesses(String processName) {

		boolean isWindows = System.getProperty("os.name").toLowerCase()
				.indexOf("windows") > -1;
				try {
					if (isWindows) {
						System.out.println("Killing Windows Process: " + processName);
						Runtime.getRuntime().exec(
								"taskkill /F /T /IM " + processName + ".exe");
					} else {
						System.out.println("Killing Unix Process: " + processName);
						Runtime.getRuntime().exec("kill -9 " + processName);
					}
				} catch (Exception err) {
					err.printStackTrace();
				}
	}

	public static void killProcess(String processID) {

		boolean isWindows = System.getProperty("os.name").toLowerCase()
				.indexOf("windows") > -1;
				try {
					String line;
					String procName = "";

					Process p;
					if (!isWindows) {
						p = Runtime.getRuntime().exec("ps -e");
					} else {
						procName = processID + ".exe";
						p = Runtime
								.getRuntime()
								.exec(System.getenv("windir")
										+ "\\system32\\"
										+ "tasklist.exe /fo csv /nh /fi \"imagename eq \""
										+ procName + "\"");
					}

					BufferedReader input = new BufferedReader(new InputStreamReader(
							p.getInputStream()));

					while ((line = input.readLine()) != null) {
						if (line.trim().equals(""))
							continue;

						line = line.substring(1);
						String sysProc = line.trim().substring(0, line.indexOf("\""));
						if (procName.equalsIgnoreCase(sysProc)) {
							if (isWindows) {
								System.out.println("Killing Windows Process: "
										+ processID);
								Runtime.getRuntime().exec(
										"taskkill /F /T /IM " + procName);
							} else {
								System.out
								.println("Killing Unix Process: " + processID);
								Runtime.getRuntime().exec("kill -9 " + procName);
							}
						} else {
							System.out.println(procName);
						}
					}
					input.close();
				} catch (Exception err) {
					err.printStackTrace();
				}
	}

	 

	// ***CSVREADER***

	public class CSVReader {

		public static final String COMMA = ",";
		public static final String TAB = "\t";
		public static final String PIPE = "|";

		// Store the lines of data
		private final List<String> lines;

		// Store the header row
		private final String[] headers;

		/**
		 * Constructor
		 * 
		 * @param csvContent
		 *            String delimited data
		 * @param delimiter
		 *            Which delimiter to use
		 * @throws IOException
		 */
		public CSVReader(String csvContent, String delimiter)
				throws IOException {
			if (csvContent == null)
				throw new IllegalArgumentException("CSV Content can't be null");

			// Read the lines
			this.lines = IOUtils.readLines(new StringReader(csvContent));

			// Get the header
			this.headers = lines.get(0).split(delimiter);
		}

		/**
		 * Get the number of headers
		 * 
		 * @return
		 */
		public int getHeaderCount() {
			return headers.length;
		}

		/**
		 * Get the header array
		 * 
		 * @return
		 */
		public String[] getHeaders() {
			return headers;
		}

		/**
		 * Get the rowcount - skipping header row
		 */
		public int getRowCount() {
			return lines.size() - 1;
		}

		/**
		 * Get the data minus the header row
		 */
		public List<String> getData() {
			return lines.subList(1, lines.size() - 1);
		}
	}

	// ***Automation DATES***

	/**
	 * calculates a day n days away from todays date
	 * 
	 * @param n
	 *            no of days away from todays date, positive value for future
	 *            date, negative for past
	 * @return the date n days away
	 */
	public static Date getDateNDaysAway(int n) {
		calendar.setTime(parseStringToDate(formatDateToString(getTodaysDate())));
		calendar.add(Calendar.DAY_OF_MONTH, n);
		Date newDate = calendar.getTime();

		calendar.setTime(getTodaysDate());

		return newDate;
	}

	/**
	 * calculates a day n days away from specified day of this week
	 * 
	 * @param n
	 *            no of days away, positive value for future date, negative for
	 *            past
	 * @param day
	 *            day of week date should fall on
	 * @return the date n days away
	 */
	public static Date getDateNDaysAwayBeginningOn(int n, int day) {
		calendar.set(Calendar.DAY_OF_WEEK, day);
		calendar.add(Calendar.DAY_OF_MONTH, n);

		Date newDate = calendar.getTime();

		calendar.setTime(getTodaysDate());

		return newDate;
	}

	/**
	 * calculates a day n days away from specified date on specified day of the
	 * week
	 * 
	 * @param n
	 *            no of days away, positive value for future date, negative for
	 *            past
	 * @param date
	 *            the date to calculate from
	 * @param day
	 *            day of week date should fall on
	 * @return the date n days away
	 */
	public static Date getDateNDaysAwayFromBeginningOn(int n, Date date, int day) {
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, day);
		calendar.add(Calendar.DAY_OF_MONTH, n);

		Date newDate = calendar.getTime();

		calendar.setTime(getTodaysDate());

		return newDate;
	}

	/**
	 * calculates a day n days away from given date
	 * 
	 * @param n
	 *            no of days away, positive value for future date, negative for
	 *            past
	 * @param date
	 *            date to calculate new date from
	 * @return the date n days away
	 */
	public static Date getDateNDaysAwayFrom(int n, Date date) {
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, n);

		Date newDate = calendar.getTime();

		calendar.setTime(getTodaysDate());

		return newDate;
	}

	/**
	 * calculates a date n months away from today
	 * 
	 * @param n
	 *            no of months away, positive value for future date, negative
	 *            for past
	 * @return the date n months away
	 */
	public static Date getDateNMonthsAway(int n) {
		calendar.add(Calendar.MONTH, n);
		Date newDate = calendar.getTime();

		calendar.setTime(getTodaysDate());

		return newDate;
	}

	/**
	 * calculates a date n months away from today
	 * 
	 * @param n
	 *            no of months away, positive value for future date, negative
	 *            for past
	 * @param date
	 *            date to calculate new date from
	 * @return the date n months away
	 */
	public static Date getDateNMonthsAwayFrom(int n, Date date) {
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		Date newDate = calendar.getTime();

		calendar.setTime(getTodaysDate());

		return newDate;
	}

	/**
	 * calculates the difference between two dates
	 * 
	 * @param date1
	 *            - first date (generally latest date)
	 * @param date2
	 *            - second date (generally older date)
	 * @return the no of days between two dates
	 */
	public static int getDiffBetweenDates(Date date1, Date date2) {
		long diff = parseStringToDate(formatDateToString(date1)).getTime()
				- parseStringToDate(formatDateToString(date2)).getTime();
		int difference = (int) (diff / (1000 * 60 * 60 * 24));
		return difference;
	}

	public static int getDiffBetweenDateAndTimes(Date date1, Date date2) {
		long diff = parseStringToDateAndTime(formatDateAndTimeToString(date1))
				.getTime()
				- parseStringToDateAndTime(formatDateAndTimeToString(date2))
				.getTime();
		int difference = (int) (diff / (1000 * 60));
		return difference;
	}


	public static int getDiffBetweenDateAndTimes(String date1, String date2) {
		long diff = parseStringToDateAndTime((date1))
				.getTime()
				- parseStringToDateAndTime((date2))
				.getTime();
		int difference = (int) (diff / (1000 * 60));
		System.out.println("The difference between "+ date1 + " & "+ date2 + " = "+ difference +" Minutes.");
		return difference;
	}

	/**
	 * retrieves todays date
	 * 
	 * @return todays date
	 */
	public static Date getTodaysDate() {
		TimeZone.setDefault(utc);
		Calendar cal = Calendar.getInstance(utc, timelocale);
		TimeZone.setDefault(defaultZone);

		Date today = cal.getTime();
		// formatDateToString(today);
		// return cal.getTime();
		return parseStringToDate(formatDateToString(today));
	}

	/**
	 * formats the date to a String
	 * 
	 * @param date
	 *            the date to format
	 * @return formatted date
	 */
	public static String formatDateToString(Date date) {
		if (date.getHours() == 0) {
			date.setHours(1);
		}

		dateFormatUS.setTimeZone(utc);
		return dateFormatUS.format(date);

	}

	public static String formatDateAndTimeToString(Date date) {

		dateAndTimeFormatUS.setTimeZone(utc);
		return dateAndTimeFormatUS.format(date);
	}

	/**
	 * formats the date to a String
	 * 
	 * @param date
	 *            the date to format
	 * @return formatted date
	 */
	public static String formatToLocaleMachineDate(Date date) {
		if (date.getHours() == 0) {
			date.setHours(1);
		}
		dateFormatLocaleMachineUS.setTimeZone(utc);
		return dateFormatLocaleMachineUS.format(date);
	}

	/**
	 * formats the date to a reassessed formatted date
	 * 
	 * @param date
	 *            the date to format
	 * @return formatted date
	 */
	public static String formatToReassessedDate(Date date) {

		reassessedDateFormatUS.setTimeZone(utc);
		return reassessedDateFormatUS.format(date);
	}

	/**
	 * formats the date to a batch formatted date
	 * 
	 * @param date
	 *            the date to format
	 * @return formatted date
	 */
	public static String formatToBatchDate(Date date) {

		batchDateFormatUS.setTimeZone(utc);
		return batchDateFormatUS.format(date);
	}

	/**
	 * calculates the last day of a month
	 * 
	 * @param month
	 *            to format
	 * @return date of the last day of the month
	 */
	public static Date getEndOfMonth(int month) {

		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.roll(Calendar.MONTH, true);

		if (month == 11) {
			calendar.roll(Calendar.YEAR, true);
		}

		Date newDate = calendar.getTime();
		calendar.setTime(getTodaysDate());

		return getDateNDaysAwayFrom(-1, newDate);
	}

	public static Date getFirstDayOfCurrentMonth() {
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * calculates the last day of a month
	 * 
	 * @param date
	 *            the date to get the last day of the month
	 * @return date of the last day of the month
	 */
	public static Date getEndOfMonthForDate(Date date) {
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, date.getMonth());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.roll(Calendar.MONTH, true);

		if (date.getMonth() == 11) {
			calendar.roll(Calendar.YEAR, true);
		}

		Date newDate = calendar.getTime();
		calendar.setTime(getTodaysDate());

		return getDateNDaysAwayFrom(-1, newDate);
	}

	/**
	 * converts the string representation to a Date format
	 * 
	 * @param date
	 *            date to convert
	 * @return the converted date
	 */
	public static Date parseStringToDate(String date) {
		Date newdate = null;
		try {

			// dateFormatUS.setTimeZone(utc);
			newdate = dateFormatUS.parse(date);

		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		return newdate;
	}

	/**
	 * converts the string representation to a Date format
	 * 
	 * @param date
	 *            date to convert
	 * @return the converted date
	 */
	public static Date parseUSStringToDate(String date) {
		Date newdate = null;

		try {
			// dateFormatUS.setTimeZone(utc);
			newdate = dateFormatUS.parse(date);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return newdate;
	}

	/**
	 * converts the string representation to a Date format
	 * 
	 * @param date
	 *            date to convert
	 * @return the converted date
	 */
	public static Date parseStringToDateAndTime(String date) {
		Date newdate = null;
		try {

			dateAndTimeFormatUS.setTimeZone(utc);
			newdate = dateAndTimeFormatUS.parse(date);

		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return newdate;
	}

	/**
	 * formats the reassessed formatted date to a date
	 * 
	 * @param date
	 *            the date to format
	 * @return formatted date
	 */
	public static Date parseReassessedStringToDate(String date) {
		Date newdate = null;
		try {

			reassessedDateFormatUS.setTimeZone(utc);
			newdate = reassessedDateFormatUS.parse(date);

		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return newdate;
	}

	/**
	 * gets todays date
	 */
	public static Date getTodaysDateAndTime() {
		TimeZone.setDefault(utc);
		Calendar cal = Calendar.getInstance(utc, timelocale);
		TimeZone.setDefault(defaultZone);
		return cal.getTime();
	}

	public static void setCurrentSystemDate(Date systemDate) {
		// logMessage("Current System date is set to " +
		// formatDateToString(systemDate));
		currentSystemDate = systemDate;
		dateRollChange = true;
	}

	public static Date getCurrentSystemDate() {
		if (dateRollChange) {
			return currentSystemDate;
		} else {
			return getTodaysDate();
		}
	}

	public static String getHour(Date date) {
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH", Locale.US);
		hourFormat.setTimeZone(defaultZone);
		return hourFormat.format(date);
	}

	public static String getMinute(Date date) {
		SimpleDateFormat minuteFormat = new SimpleDateFormat("mm", Locale.US);
		minuteFormat.setTimeZone(defaultZone);
		return minuteFormat.format(date);
	}

	public static String getMinutesNMinutesAway(int n) {
		return getMinute(getDateNMinutesAway(n));
	}

	public static String getHourNMinutesAway(int n) {
		return getHour(getDateNMinutesAway(n));
	}

	public static Date getDateNMinutesAway(int n) {
		TimeZone.setDefault(utc);
		Calendar cal = Calendar.getInstance(utc, timelocale);
		TimeZone.setDefault(defaultZone);
		cal.add(Calendar.MINUTE, n);
		Date newDate = cal.getTime();

		cal.setTime(getTodaysDate());

		return newDate;
	}

	/**
	 * formats the date to a String
	 * 
	 * @param date
	 *            the date to format
	 * @return formatted date
	 */
	public static String formatDateToGanttString(Date date) {

		ganttChartFormatUS.setTimeZone(utc);
		return ganttChartFormatUS.format(date);
	}

	/**
	 * calculates the difference between two dates
	 * 
	 * @param date1
	 *            - first date (generally latest date)
	 * @param date2
	 *            - second date (generally older date)
	 * @return the no of days between two dates
	 */
	public static int getYearsBetweenDates(Date date1, Date date2) {
		long diff = parseStringToDate(formatDateToString(date1)).getTime()
				- parseStringToDate(formatDateToString(date2)).getTime();

		int difference = (int) (diff / (1000.0 * 60.0 * 60.0 * 24.0) / 365.25);
		return difference;
	}

	public static String getBeginningOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int offset = 2 - cal.get(Calendar.DAY_OF_WEEK);
		if (cal.get(Calendar.DAY_OF_WEEK) > 4) {
			offset = offset + 7;
		}
		Date newDate = getDateNDaysAwayFrom(-offset, date);

		return formatDateToString(getDateNDaysAwayFromBeginningOn(0, newDate,
				Calendar.MONDAY));
	}

	public static Date getBeginningOfWeekAsDate(Date date) {
		return parseStringToDate(getBeginningOfWeek(date));
	}

	/**
	 * formats the date to a reassessed formatted date
	 * 
	 * @param date
	 *            the date to format
	 * @return formatted date
	 */
	public static String formatToReportingDBDate(Date date) {
		reassessedDateFormatUS.setTimeZone(utc);
		return reassessedDateFormatUS.format(date);
	}

	/**
	 * retrieves what day of the week today falls on
	 * 
	 * @return day of the week
	 * @throws Exception
	 */
	public static String getDayOfWeek() throws Exception {
		Calendar cal = Calendar.getInstance();
		int today = cal.get(Calendar.DAY_OF_WEEK);

		switch (today) {
		case Calendar.MONDAY:
			return "Monday";
		case Calendar.TUESDAY:
			return "Tuesday";
		case Calendar.WEDNESDAY:
			return "Wednesday";
		case Calendar.THURSDAY:
			return "Thursday";
		case Calendar.FRIDAY:
			return "Friday";
		case Calendar.SATURDAY:
			return "Saturday";
		case Calendar.SUNDAY:
			return "Sunday";
		default:
			return "";
		}
	}

	public static String getDateFormat() {

		return dateFormatUS.toLocalizedPattern();
	}

	// ***CLI Utils***

	public void shellCommand() {
		Runtime runtime = Runtime.getRuntime();
		Process proc = null;
		String retStr = "";
		InputStreamReader insReader = null;
		char[] tmpBuffer = new char[1024];
		int nRet = 0;
		String cmd = "mv abc.txt /home/abct.txt";
		try {
			proc = runtime.exec(cmd);
			insReader = new InputStreamReader(proc.getInputStream());

			while ((nRet = insReader.read(tmpBuffer, 0, 1024)) != -1) {
				retStr += new String(tmpBuffer, 0, nRet);
			}

			insReader.close();
			System.out.println(retStr);
		} catch (Exception e) {
			System.out.println("Bad");
		} finally {
			System.out.println("Done");
		}
	}

	/*
	 * public void puttyCommand(){ try { String command = "plink -load profile";
	 * 
	 * Runtime r = Runtime.getRuntime (); Process p = r.exec (command); std =
	 * p.getInputStream (); out = p.getOutputStream (); err = p.getErrorStream
	 * ();
	 * 
	 * out.write ("ls -l\n".getBytes ()); out.flush ();
	 * 
	 * Thread.sleep (1000);
	 * 
	 * int value = 0; if (std.available () > 0) { System.out.println ("STD:");
	 * value = std.read (); System.out.print ((char) value);
	 * 
	 * while (std.available () > 0) { value = std.read (); System.out.print
	 * ((char) value); } }
	 * 
	 * if (err.available () > 0) { System.out.println ("ERR:"); value = err.read
	 * (); System.out.print ((char) value);
	 * 
	 * while (err.available () > 0) { value = err.read (); System.out.print
	 * ((char) value); } }
	 * 
	 * p.destroy (); } catch (Exception e) { e.printStackTrace (); } }
	 */

	/*
	 * public static void SSHCommandExecutor() { String
	 * host="ssh.journaldev.com"; String user="sshuser"; String
	 * password="sshpwd"; String command1="ls -ltr"; try{
	 * 
	 * java.util.Properties config = new java.util.Properties();
	 * config.put("StrictHostKeyChecking", "no"); JSch jsch = new JSch();
	 * Session session=jsch.getSession(user, host, 22);
	 * session.setPassword(password); session.setConfig(config);
	 * session.connect(); System.out.println("Connected");
	 * 
	 * Channel channel=session.openChannel("exec");
	 * ((ChannelExec)channel).setCommand(command1);
	 * channel.setInputStream(null);
	 * ((ChannelExec)channel).setErrStream(System.err);
	 * 
	 * InputStream in=channel.getInputStream(); channel.connect(); byte[]
	 * tmp=new byte[1024]; while(true){ while(in.available()>0){ int
	 * i=in.read(tmp, 0, 1024); if(i<0)break; System.out.print(new String(tmp,
	 * 0, i)); } if(channel.isClosed()){
	 * System.out.println("exit-status: "+channel.getExitStatus()); break; }
	 * try{Thread.sleep(1000);}catch(Exception ee){} } channel.disconnect();
	 * session.disconnect(); System.out.println("DONE"); }catch(Exception e){
	 * e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * }
	 */

	// *** DATE TIME ***

	// Set Current Date Time

	public String SetCurrentDateTime(String pregDueDate) {

		long j = new Date().getTime();
		System.out.println("  date     " + j);

		@SuppressWarnings("deprecation")
		long pre_Dte = Date.parse(pregDueDate);

		System.out.println(" Preg date  " + pre_Dte);

		if (pre_Dte < j) {
			System.out.println("pregduedate is leeser");
		} else {
			System.out.println("pregduedate is future");
		}
		return null;

	}

	// *** SOAPUI ***

	/*public void SoapJavaTest() throws Exception {
		String projectFile = "D:\\WebServices\\SeleniumAutomationdev2-state-ar-soapui-project.xml";
		WsdlProject project = new WsdlProject(projectFile);
		// WsdlProject project = new WsdlProject();
		WsdlInterface[] wsdls = WsdlImporter
				.importWsdl(
						project,
						"https://SeleniumAutomationdev2.gmail.com/AutomationWS2/services/SeleniumAccountTransferService?wsdl");
		WsdlInterface wsdl = wsdls[0];
		for (Operation operation : wsdl.getOperationList()) {
			WsdlOperation op = (WsdlOperation) operation;
			System.out.println("OP:" + op.getName());
			System.out.println(op.createRequest(true));
			System.out.println("Response:");
			System.out.println(op.createResponse(true));
		}
	}
*/
	// function1: Function to save a SoapUI Project  

	/*public void soapTest() throws Exception

	{

		File projectFile = new File(
				"D:\\WebServices\\SeleniumAutomationdev2-state-ar-soapui-project.xml");

		SoapUI.setSoapUICore(new StandaloneSoapUICore(true));

		WsdlProject project = new WsdlProject("TestProjectA");

		WsdlInterface[] wsdls = WsdlImporter
				.importWsdl(
						project,
						"https://SeleniumAutomationdev2.gmail.com/AutomationWS2/services/SeleniumAccountTransferService?wsdl");

		System.out.println("The wsdl count =" + wsdls.length); // To get the
		// number of
		// wsdl
		// interfaces

		for (int j = 0; j < wsdls.length; j++)

		{

			WsdlInterface wsdl = wsdls[j];

			String soapVersion = wsdl.getSoapVersion().toString();

			System.out.println("The SOAP version =" + soapVersion);

			System.out.println("The binding name = " + wsdl.getBindingName());

			// System.out.println("The binding type ="+wsdl.getBinding());

			int c = wsdl.getOperationCount();

			System.out.println("The total number of operations =" + c);

			String reqContent = "";

			String result = "";

			for (int i = 0; i < c; i++)

			{

				WsdlOperation op = wsdl.getOperationAt(i);

				String opName = op.getName();

				System.out.println("OP:" + opName);

				reqContent = op.createRequest(true);

				WsdlRequest req = op.addNewRequest("Req_" + soapVersion + "_"
						+ opName);

				System.out.println("Req_" + soapVersion + "_" + opName);

				req.setRequestContent(reqContent);

			}

		}

		project.saveIn(projectFile);

	}
*/
	// function2 : Function to send requests  

/*	public void runSoap() throws Exception

	{

		String projectFile = "D:\\WebServices\\SeleniumAutomationdev2-state-ar-soapui-project.xml";

		SoapUI.setSoapUICore(new StandaloneSoapUICore(true));

		WsdlProject project = new WsdlProject(projectFile);

		int c = project.getInterfaceCount();

		System.out.println("The interface count   =" + c);

		for (int i = 0; i < c; i++)

		{

			WsdlInterface wsdl = (WsdlInterface) project.getInterfaceAt(i);

			String soapVersion = wsdl.getSoapVersion().toString();

			System.out.println("The SOAP version =" + soapVersion);

			System.out.println("The binding name = " + wsdl.getBindingName());

			int opc = wsdl.getOperationCount();

			System.out.println("Operation count =" + opc);

			String result = "";

			for (int j = 0; j < opc; j++)

			{

				WsdlOperation op = wsdl.getOperationAt(j);

				String opName = op.getName();

				System.out.println("OPERATION:" + opName);

				WsdlRequest req = op.getRequestByName("Req_" + soapVersion
						+ "_" + opName);

				System.out.println("REQUEST :" + req.getName());

				System.out.println("The request content is ="
						+ req.getRequestContent());

				System.out.println("The action is =" + req.getAction());

				req.setEndpoint("your_end_Point");

				// Assigning u/p to an operation: Generate

				if (opName.equals("Generate"))

				{

					// req.setAuthType("Preemptive"); //Setting the
					// Authorization type

					req.setUsername("u1");

					req.setPassword("u1");

				}

				WsdlSubmitContext wsdlSubmitContext = new WsdlSubmitContext(req);

				WsdlSubmit<?> submit = (WsdlSubmit<?>) req.submit(
						wsdlSubmitContext, false);

				Response response = submit.getResponse();

				result = response.getContentAsString();

				System.out.println("The result =" + result);

			}

		}

	}
*/
	// Create SOAP Request (Working)

	public static SOAPMessage createSOAPRequest() throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		String serverURI = "http://ws.cdyne.com/";

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("example", serverURI);

		/*
		 * Constructed SOAP Request Message: <SOAP-ENV:Envelope
		 * xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
		 * xmlns:example="http://ws.cdyne.com/"> <SOAP-ENV:Header/>
		 * <SOAP-ENV:Body> <example:VerifyEmail>
		 * <example:email>mutantninja@gmail.com</example:email>
		 * <example:LicenseKey>123</example:LicenseKey> </example:VerifyEmail>
		 * </SOAP-ENV:Body> </SOAP-ENV:Envelope>
		 */

		/*
		 * <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope"
		 * xmlns:rem="http://remote.facade.Selenium.custom.Automation"
		 * xmlns:xsd="http://struct.facade.Selenium.custom.Automation/xsd"> <soap:Header/>
		 * <soap:Body> <rem:accountTransferService> <!--Optional:--> <rem:key>
		 * <!--Optional:--> <xsd:xmlString>1</xsd:xmlString> </rem:key>
		 * </rem:accountTransferService> </soap:Body> </soap:Envelope>
		 */

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement("VerifyEmail",
				"example");
		SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("email",
				"example");
		soapBodyElem1.addTextNode("mutantninja@gmail.com");
		SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("LicenseKey",
				"example");
		soapBodyElem2.addTextNode("123");

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI + "VerifyEmail");

		soapMessage.saveChanges();

		/* Print the request message */
		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	// SOAPMessage Parameterized

	public static SOAPMessage createSOAPRequest(String uri) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// String serverURI = "http://ws.cdyne.com/";
		String serverURI = uri;

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		// envelope.addNamespaceDeclaration("soap",
		// "http://www.w3.org/2003/05/soap-envelope/");
		// envelope.addNamespaceDeclaration("soap", serverURI);

		/*
		 * Constructed SOAP Request Message: <SOAP-ENV:Envelope
		 * xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
		 * xmlns:example="http://ws.cdyne.com/"> <SOAP-ENV:Header/>
		 * <SOAP-ENV:Body> <example:VerifyEmail>
		 * <example:email>mutantninja@gmail.com</example:email>
		 * <example:LicenseKey>123</example:LicenseKey> </example:VerifyEmail>
		 * </SOAP-ENV:Body> </SOAP-ENV:Envelope>
		 */

		/*
		 * <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope"
		 * xmlns:rem="http://remote.facade.Selenium.custom.Automation"
		 * xmlns:xsd="http://struct.facade.Selenium.custom.Automation/xsd"> <soap:Header/>
		 * <soap:Body> <rem:accountTransferService> <!--Optional:--> <rem:key>
		 * <!--Optional:--> <xsd:xmlString>1</xsd:xmlString> </rem:key>
		 * </rem:accountTransferService> </soap:Body> </soap:Envelope>
		 */
		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement(
				"VerifyNonESIMECRequest", "http://vnem.ee.sim.gmail.com",
				"http://yay.com/yay/yay-core/2.0");
		SOAPElement soapBodyElem1 = soapBodyElem
				.addChildElement("IndividualRequest");
		SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("Applicant");
		SOAPElement soapBodyElem3 = soapBodyElem2
				.addChildElement("PersonSSNIdentification");
		soapBodyElem3.addTextNode("287493922");

		SOAPElement soapBodyElem4 = soapBodyElem2.addChildElement("PersonName");
		SOAPElement soapBodyElem5 = soapBodyElem4
				.addChildElement("PersonGivenName");
		soapBodyElem4.addTextNode("MAVEN");
		SOAPElement soapBodyElem6 = soapBodyElem4
				.addChildElement("PersonSurName");
		soapBodyElem4.addTextNode("ROY");

		SOAPElement soapBodyElem7 = soapBodyElem2.addChildElement(
				"PersonBirthDate", "ns");
		SOAPElement soapBodyElem8 = soapBodyElem7.addChildElement("Date", "ns");
		soapBodyElem4.addTextNode("1985-01-01");

		SOAPElement soapBodyElem9 = soapBodyElem2.addChildElement(
				"PersonSexCode", "ns");
		soapBodyElem9.addTextNode("M");

		SOAPElement soapBodyElem10 = soapBodyElem1.addChildElement(
				"LocationStateUSPostalServiceCode", "ns");
		soapBodyElem9.addTextNode("AR");

		SOAPElement soapBodyElem11 = soapBodyElem1
				.addChildElement("InsurancePolicy");
		SOAPElement soapBodyElem12 = soapBodyElem11
				.addChildElement("InsurancePolicyEffectiveDate");
		soapBodyElem12.addTextNode("2011-01-01");

		SOAPElement soapBodyElem13 = soapBodyElem11
				.addChildElement("InsurancePolicyExpirationDate");
		soapBodyElem12.addTextNode("2016-12-31");

		SOAPElement soapBodyElem14 = soapBodyElem1
				.addChildElement("Organization");
		SOAPElement soapBodyElem15 = soapBodyElem14
				.addChildElement("OrganizationCode");
		soapBodyElem12.addTextNode("MEDI");

		MimeHeaders headers = soapMessage.getMimeHeaders();
		// headers.addHeader("SOAPAction", serverURI + "VerifyEmail");

		soapMessage.saveChanges();

		/* Print the request message */
		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	public static SOAPMessage createSOAPRequest_Backup(String uri)
			throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// String serverURI = "http://ws.cdyne.com/";
		String serverURI = uri;

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("example", serverURI);

		/*
		 * Constructed SOAP Request Message: <SOAP-ENV:Envelope
		 * xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
		 * xmlns:example="http://ws.cdyne.com/"> <SOAP-ENV:Header/>
		 * <SOAP-ENV:Body> <example:VerifyEmail>
		 * <example:email>mutantninja@gmail.com</example:email>
		 * <example:LicenseKey>123</example:LicenseKey> </example:VerifyEmail>
		 * </SOAP-ENV:Body> </SOAP-ENV:Envelope>
		 */

		/*
		 * <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope"
		 * xmlns:rem="http://remote.facade.Selenium.custom.Automation"
		 * xmlns:xsd="http://struct.facade.Selenium.custom.Automation/xsd"> <soap:Header/>
		 * <soap:Body> <rem:accountTransferService> <!--Optional:--> <rem:key>
		 * <!--Optional:--> <xsd:xmlString>1</xsd:xmlString> </rem:key>
		 * </rem:accountTransferService> </soap:Body> </soap:Envelope>
		 */
		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement("VerifyEmail",
				"example");
		SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("email",
				"example");
		soapBodyElem1.addTextNode("mutantninja@gmail.com");
		SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("LicenseKey",
				"example");
		soapBodyElem2.addTextNode("123");

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI + "VerifyEmail");

		soapMessage.saveChanges();

		/* Print the request message */
		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	public static SOAPMessage createSOAPRequest2(String uri) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// String serverURI = "http://ws.cdyne.com/";
		String serverURI = uri;

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("example", serverURI);

		/*
		 * Constructed SOAP Request Message: <SOAP-ENV:Envelope
		 * xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
		 * xmlns:example="http://ws.cdyne.com/"> <SOAP-ENV:Header/>
		 * <SOAP-ENV:Body> <example:VerifyEmail>
		 * <example:email>mutantninja@gmail.com</example:email>
		 * <example:LicenseKey>123</example:LicenseKey> </example:VerifyEmail>
		 * </SOAP-ENV:Body> </SOAP-ENV:Envelope>
		 */

		/*
		 * <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope"
		 * xmlns:rem="http://remote.facade.Selenium.custom.Automation"
		 * xmlns:xsd="http://struct.facade.Selenium.custom.Automation/xsd"> <soap:Header/>
		 * <soap:Body> <rem:accountTransferService> <!--Optional:--> <rem:key>
		 * <!--Optional:--> <xsd:xmlString>1</xsd:xmlString> </rem:key>
		 * </rem:accountTransferService> </soap:Body> </soap:Envelope>
		 */
		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement("VerifyEmail",
				"example");
		SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("email",
				"example");
		soapBodyElem1.addTextNode("mutantninja@gmail.com");
		SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("LicenseKey",
				"example");
		soapBodyElem2.addTextNode("123");

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI + "VerifyEmail");

		soapMessage.saveChanges();

		/* Print the request message */
		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	public static SOAPMessage createSOAPBody2(String uri, String element1,
			String value1, String element2, String value2, String element3,
			String value3) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// String serverURI = "http://ws.cdyne.com/";
		String serverURI = uri;

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("example", serverURI);

		/*
		 * Constructed SOAP Request Message: <SOAP-ENV:Envelope
		 * xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
		 * xmlns:example="http://ws.cdyne.com/"> <SOAP-ENV:Header/>
		 * <SOAP-ENV:Body> <example:VerifyEmail>
		 * <example:email>mutantninja@gmail.com</example:email>
		 * <example:LicenseKey>123</example:LicenseKey> </example:VerifyEmail>
		 * </SOAP-ENV:Body> </SOAP-ENV:Envelope>
		 */

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement(element1, value1);
		SOAPElement soapBodyElem1 = soapBodyElem.addChildElement(element2,
				value2);
		soapBodyElem1.addTextNode("automation.Selenium@gmail.com");
		SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("LicenseKey",
				"example");
		soapBodyElem2.addTextNode("123");

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI + "VerifyEmail");

		soapMessage.saveChanges();

		/* Print the request message */
		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	// Soap Client (call this in test case)- working

	/**
	 * Sends the constructed soap message to the webservice
	 * @param uri
	 * @param url
	 * @throws Exception
	 */
	public static void sendSOAP(String uri, String url) throws Exception {
		// Create SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		// Send SOAP Message to SOAP Server
		// String url =
		// "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
		SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(uri),
				url);

		// print SOAP Response
		System.out.print("Response SOAP Message:");
		soapResponse.writeTo(System.out);

		soapConnection.close();
	}

	public static void sendSOAP2(String uri, String url) throws Exception {
		// Create SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		// Send SOAP Message to SOAP Server
		// String url =
		// "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
		SOAPMessage soapResponse = soapConnection.call(createSOAPRequest2(uri),
				url);

		// print SOAP Response
		System.out.print("Response SOAP Message:");
		soapResponse.writeTo(System.out);

		soapConnection.close();
	}

	public static void sendSOAP_Backup(String uri, String url) throws Exception {
		// Create SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		// Send SOAP Message to SOAP Server
		// String url =
		// "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
		SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(uri),
				url);

		// print SOAP Response
		System.out.print("Response SOAP Message:");
		soapResponse.writeTo(System.out);

		soapConnection.close();
	}

	// /***************************************** WEBSERVICE ******************

	public static void soapRun(String uri, String url, String element1,
			String value1, String element2, String value2, String element3,
			String value3, String element4, String value4, String element5,
			String value5) throws Exception {
		// Create SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		// Send SOAP Message to SOAP Server
		// String url =
		// "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
		SOAPMessage soapResponse = soapConnection.call(
				soapCreate(uri, element1, value1, element2, value2, element3,
						value3, element4, value4, element5, value5), url);

		// print SOAP Response
		System.out.print("Response SOAP Message:");
		soapResponse.writeTo(System.out);

		soapConnection.close();
	}

	public static SOAPMessage soapCreate(String uri, String element1,
			String value1, String element2, String value2, String element3,
			String value3, String element4, String value4, String element5,
			String value5) throws Exception {
		SOAPMessage soapMsg = null;
		try {
			MessageFactory factory = MessageFactory.newInstance();
			soapMsg = factory.createMessage();
			SOAPPart part = soapMsg.getSOAPPart();

			SOAPEnvelope envelope = part.getEnvelope();
			SOAPHeader header = envelope.getHeader();
			SOAPBody body = envelope.getBody();

			// header.addTextNode("");

			/*
			 * Constructed SOAP Request Message: <SOAP-ENV:Envelope
			 * xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
			 * xmlns:example="http://ws.cdyne.com/"> <SOAP-ENV:Header/>
			 * <SOAP-ENV:Body> <example:VerifyEmail>
			 * <example:email>mutantninja@gmail.com</example:email>
			 * <example:LicenseKey>123</example:LicenseKey>
			 * </example:VerifyEmail> </SOAP-ENV:Body> </SOAP-ENV:Envelope>
			 */

			SOAPElement soapBodyElem = body
					.addChildElement("VerifyNonESIMECRequest xmlns=\"http://vnem.ee.sim.gmail.com\" xmlns:ns=\"http://yay.com/yay/yay-core/2.0\"");
			SOAPElement soapBodyElem1 = soapBodyElem
					.addChildElement("IndividualRequest");

			SOAPElement soapBodyElem2 = soapBodyElem1
					.addChildElement("Applicant");

			SOAPElement soapBodyElem3 = soapBodyElem2.addChildElement(element1);

			soapBodyElem3.addTextNode(value1);
			SOAPElement soapBodyElem4 = soapBodyElem2
					.addChildElement("PersonName");
			SOAPElement soapBodyElem5 = soapBodyElem4.addChildElement(element2);
			soapBodyElem5.addTextNode(value2);
			SOAPElement soapBodyElem6 = soapBodyElem4.addChildElement(element3);
			soapBodyElem6.addTextNode(value3);
			SOAPElement soapBodyElem7 = soapBodyElem2.addChildElement(
					"PersonBirthDate", "ns");
			SOAPElement soapBodyElem8 = soapBodyElem7.addChildElement(element4,
					"ns");

			soapBodyElem8.addTextNode(value4);

			SOAPElement soapBodyElem9 = soapBodyElem2.addChildElement(element5,
					"ns");

			soapBodyElem9.addTextNode(value5);

			SOAPElement soapBodyElem10 = soapBodyElem1.addChildElement(
					"LocationStateUSPostalServiceCode", "ns");
			soapBodyElem10.addTextNode("AR");

			SOAPElement soapBodyElem11 = soapBodyElem1
					.addChildElement("InsurancePolicy");
			SOAPElement soapBodyElem12 = soapBodyElem11
					.addChildElement("InsurancePolicyEffectiveDate");

			soapBodyElem12.addTextNode("2011-01-01");

			SOAPElement soapBodyElem13 = soapBodyElem12
					.addChildElement("InsurancePolicyExpirationDate");
			soapBodyElem13.addTextNode("2016-12-31");

			SOAPElement soapBodyElem14 = soapBodyElem1
					.addChildElement("Organization");

			SOAPElement soapBodyElem15 = soapBodyElem14
					.addChildElement("OrganizationCode");
			soapBodyElem15.addTextNode("MEDI");

			/*
			 * SOAPBodyElement element =
			 * body.addBodyElement(envelope.createName("VerifyNonESIMECRequest",
			 * "http://vnem.ee.sim.gmail.com",
			 * "http://yay.com/yay/yay-core/2.0"));
			 * element.addChildElement("PersonSSNIdentification"
			 * ).addTextNode("287493922");
			 * 
			 * element.addChildElement("PersonSSNIdentification").addTextNode(
			 * "287493922");
			 * 
			 * SOAPBodyElement element1 =
			 * body.addBodyElement(envelope.createName("JAVA", "training",
			 * "http://JitendraZaa.com/blog"));
			 * element1.addChildElement("Spring"
			 * ).addTextNode("Training on Spring 3.0");
			 */
			soapMsg.writeTo(System.out);

			FileOutputStream fOut = new FileOutputStream("D:\\SoapMessage.xml");
			soapMsg.writeTo(fOut);

			System.out.println();
			System.out.println("SOAP msg created");

			soapMsg.saveChanges();

			/* Print the request message */
			System.out.print("Request SOAP Message:");
			soapMsg.writeTo(System.out);
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return soapMsg;
	}

	public static SOAPMessage soapCreate2(String uri, String element1,
			String value1, String element2, String value2, String element3,
			String value3, String element4, String value4, String element5,
			String value5) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// String serverURI = "http://ws.cdyne.com/";
		String serverURI = uri;

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("", serverURI);

		/*
		 * Constructed SOAP Request Message: <SOAP-ENV:Envelope
		 * xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
		 * xmlns:example="http://ws.cdyne.com/"> <SOAP-ENV:Header/>
		 * <SOAP-ENV:Body> <example:VerifyEmail>
		 * <example:email>mutantninja@gmail.com</example:email>
		 * <example:LicenseKey>123</example:LicenseKey> </example:VerifyEmail>
		 * </SOAP-ENV:Body> </SOAP-ENV:Envelope>
		 */

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement(element1, value1);
		SOAPElement soapBodyElem1 = soapBodyElem.addChildElement(element2,
				value2);
		soapBodyElem1.addTextNode("automation.Selenium@gmail.com");
		SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("LicenseKey",
				"example");
		soapBodyElem2.addTextNode("123");

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI + "VerifyEmail");

		soapMessage.saveChanges();

		/* Print the request message */
		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	// ********************* JMeter ***********************//

/*	@SuppressWarnings("deprecation")
	public void webServiceTest_JMeter() throws IOException {

		System.out.println("Started : Web Service run");

		// JMeter Engine
		StandardJMeterEngine jmeter = new StandardJMeterEngine();

		// Initialize Properties, logging, locale, etc.
		JMeterUtils
		.loadJMeterProperties("D:\\AutomationProjects\\ExternalApps\\jmeter\\bin\\jmeter.properties");
		JMeterUtils.setJMeterHome("D:\\AutomationProjects\\ExternalApps\\jmeter\\bin\\jmeter");
		JMeterUtils.initLogging();// you can comment this line out to see extra
		// log messages of i.e. DEBUG level
		JMeterUtils.initLocale();

		// Initialize JMeter SaveService
		SaveService.loadProperties();

		// Load existing .jmx Test Plan
		FileInputStream in = new FileInputStream(
				Constants.workingDir+"\\webservices\\NonEsiMecWebService.jmx");
		@SuppressWarnings("deprecation")
		HashTree testPlanTree = SaveService.loadTree(in);
		in.close();

		// Run JMeter Test
		jmeter.configure(testPlanTree);
		jmeter.run();
		System.out.println("Finished : Web Service run");
	}
*/
	// *************************** PUTTY *****************
/*
	public void puttyInvoke() throws AWTException {

		Robot robot = new Robot();
 
		Random rand = new Random();
		AllUtils util = new AllUtils();
		Constants c = new Constants();
	 
		Regex searchRegex = new Regex("Start menu");
		Regex searchRegex1 = new Regex("Automationbatchdev.gmail.com - PuTTY");

		boolean matched = true;
		boolean matched1 = true;

		// VK_Windows
		robot.keyPress(KeyEvent.VK_WINDOWS);
		sleep(0.02);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		sleep(1);

		root = getRootTestObject();

		// Find all open windows and put them in an array called windows
		windows = root.getTopWindows();

		// Perform the following for each window found

		for (int i = 0; i < windows.length; i++) {
			// Gets the text of the window (which happens to be the window
			// title)
			String windowTitle = windows[i].getText();

			// Print out the found window titles for debugging
			System.out.println("Found " + windowTitle + " -- " + i + " of "
					+ windows.length + " windows.");

			// See if a window title matches the title here, then create the
			// foundWindow object
			// Input text into the window, then close the window
			// Catch exceptions as well (code can be added if an exception is
			// caught)
			try {
				matched = searchRegex.matches(windowTitle);

				if (matched) {
					foundWindow = windows[i];
					foundWindow.inputKeys("putty" + "{ENTER}");
					sleep(3);
					System.out.println("MATCH  >>>>>>>>>>>>>");
					System.out.println("Found " + windowTitle + " -- " + i
							+ " of " + windows.length + " windows.");
					sleep(6);
				}

			} catch (Exception e) {

			}
			System.out.println(" >>>>>>");
		}

		root = getRootTestObject();
		String r = getTopWindows().toString();

		// Find all open windows and put them in an array called windows
		windows = root.getTopWindows();
		for (int j = 0; j < windows.length; j++) {
			// Gets the text of the window (which happens to be the window
			// title)
			String windowTitle = windows[j].getText();

			// Print out the found window titles for debugging
			System.out.println("Found " + windowTitle + " -- " + j + " of "
					+ windows.length + " windows.");

			try {
				matched1 = searchRegex1.matches(windowTitle);
				if (matched1) {
					foundWindow = windows[j];
					System.out.println("Parent  " + windows[j]);

					System.out
					.println("MATCH2 MATCH2 MATCH MATCH2 MATCH2  >>>>>>>>>>>>>>>>>>>>>");
					System.out.println("Found " + windowTitle + " -- " + j
							+ " of " + windows.length + " windows.");
					sleep(1);

					foundWindow
					.inputKeys("cd /autosys/TST1/Batch/" + "{ENTER}");
					sleep(0.05);
					foundWindow.maximize();
					sleep(0.08);
					foundWindow
					.inputKeys("./runBatch_SeleniumRenewalBatch_Chunker.sh"
							+ "{ENTER}");
					sleep(30);

					System.out.println("Children  " + windows[j].getChildren()
							+ "R Value " + r);

					int a = 0;
					a = r.indexOf("BUILD SUCCESSFUL");
					if (a != -1) {
						System.out.println("Ready for next Batch");
					}

					// while (foundWindow.inputChars("BUILD SUCCESSFUL") !=
					// true);
					// {
					// sleep(14);
					// }
					break;
				}

			}

			catch (Exception e) {
			}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>");

		}

	}
*//*
	public void puttyInvoke(String env) throws AWTException {

		Robot robot = new Robot();

 		Random rand = new Random();
		AllUtils util = new AllUtils();
		Constants c = new Constants();
		 

 		Regex searchRegex1 = new Regex("Automationbatchdev.gmail.com - PuTTY");

		boolean matched = true;
		boolean matched1 = true;

		// VK_Windows
		robot.keyPress(KeyEvent.VK_WINDOWS);
		sleep(0.02);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		sleep(1);

 
		// Find all open windows and put them in an array called windows
		windows = root.getTopWindows();

		// Perform the following for each window found

		for (int i = 0; i < windows.length; i++) {
			// Gets the text of the window (which happens to be the window
			// title)
			String windowTitle = windows[i].getText();

			// Print out the found window titles for debugging
			System.out.println("Found " + windowTitle + " -- " + i + " of "
					+ windows.length + " windows.");

			// See if a window title matches the title here, then create the
			// foundWindow object
			// Input text into the window, then close the window
			// Catch exceptions as well (code can be added if an exception is
			// caught)
			try {
				matched = searchRegex.matches(windowTitle);

				if (matched) {
					foundWindow = windows[i];
					foundWindow.inputKeys("putty" + "{ENTER}");
					sleep(3);
					System.out
					.println("MATCH MATCH MATCH MATCH MATCH  >>>>>>>>>>>>>>>>>>>>>");
					System.out.println("Found " + windowTitle + " -- " + i
							+ " of " + windows.length + " windows.");
					sleep(6);
				}

			} catch (Exception e) {

			}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
		}

		root = getRootTestObject();
		String r = getTopWindows().toString();

		// Find all open windows and put them in an array called windows
		windows = root.getTopWindows();
		for (int j = 0; j < windows.length; j++) {
			// Gets the text of the window (which happens to be the window
			// title)
			String windowTitle = windows[j].getText();

			// Print out the found window titles for debugging
			System.out.println("Found " + windowTitle + " -- " + j + " of "
					+ windows.length + " windows.");

			try {
				matched1 = searchRegex1.matches(windowTitle);
				if (matched1) {
					foundWindow = windows[j];
					System.out.println("Parent  " + windows[j]);

					System.out
					.println("MATCH2 MATCH2 MATCH MATCH2 MATCH2  >>>>>>>>>>>>>>>>>>>>>");
					System.out.println("Found " + windowTitle + " -- " + j
							+ " of " + windows.length + " windows.");
					sleep(1);

					foundWindow.inputKeys("cd /autosys/" + env + "/Batch/"
							+ "{ENTER}");
					sleep(0.05);
					foundWindow.maximize();
					sleep(0.08);
					foundWindow
					.inputKeys("./runBatch_SeleniumRenewalBatch_Chunker.sh"
							+ "{ENTER}");
					sleep(30);

					System.out.println("Children  " + windows[j].getChildren()
							+ "R Value " + r);

					int a = 0;
					a = r.indexOf("BUILD SUCCESSFUL");
					if (a != -1) {
						System.out.println("Ready for next Batch");
					}

					// while (foundWindow.inputChars("BUILD SUCCESSFUL") !=
					// true);
					// {
					// sleep(14);
					// }
					break;
				}

			}

			catch (Exception e) {
			}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>");

		}

	}
*/
/*	public void puttyInvoke(String environment, String shellScript,
			String argument) throws AWTException {

		Robot robot = new Robot();

 		Random rand = new Random();
		AllUtils util = new AllUtils();
		Constants c = new Constants();
		IWindow[] windows = null;
		IWindow foundWindow = null;
 
		Regex searchRegex = new Regex("Start menu");
		Regex searchRegex1 = new Regex("Automationbatchdev.gmail.com - PuTTY");

		boolean matched = true;
		boolean matched1 = true;

		// VK_Windows
		robot.keyPress(KeyEvent.VK_WINDOWS);
		sleep(0.02);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		sleep(1);

		root = getRootTestObject();

		// Find all open windows and put them in an array called windows
		windows = root.getTopWindows();

		// Perform the following for each window found

		for (int i = 0; i < windows.length; i++) {
			// Gets the text of the window (which happens to be the window
			// title)
			String windowTitle = windows[i].getText();

			// Print out the found window titles for debugging
			System.out.println("Found " + windowTitle + " -- " + i + " of "
					+ windows.length + " windows.");

			// See if a window title matches the title here, then create the
			// foundWindow object
			// Input text into the window, then close the window
			// Catch exceptions as well (code can be added if an exception is
			// caught)
			try {
				matched = searchRegex.matches(windowTitle);

				if (matched) {
					foundWindow = windows[i];
					foundWindow.inputKeys("putty" + "{ENTER}");
					sleep(3);
					System.out
					.println("MATCH MATCH MATCH MATCH MATCH  >>>>>>>>>>>>>>>>>>>>>");
					System.out.println("Found " + windowTitle + " -- " + i
							+ " of " + windows.length + " windows.");
					sleep(6);
				}

			} catch (Exception e) {

			}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
		}

		root = getRootTestObject();
		String r = getTopWindows().toString();

		// Find all open windows and put them in an array called windows
		windows = root.getTopWindows();
		for (int j = 0; j < windows.length; j++) {
			// Gets the text of the window (which happens to be the window
			// title)
			String windowTitle = windows[j].getText();

			// Print out the found window titles for debugging
			System.out.println("Found " + windowTitle + " -- " + j + " of "
					+ windows.length + " windows.");

			try {
				matched1 = searchRegex1.matches(windowTitle);
				if (matched1) {
					foundWindow = windows[j];
					System.out.println("Parent  " + windows[j]);

					System.out.println("Found " + windowTitle + " -- " + j
							+ " of " + windows.length + " windows.");
					sleep(1);

					foundWindow.inputKeys("cd /autosys/" + environment
							+ "/Batch/" + "{ENTER}");
					sleep(0.05);
					foundWindow.maximize();
					sleep(0.08);
					foundWindow.inputKeys("./" + shellScript + argument
							+ "{ENTER}");
					sleep(30);

					System.out.println("Children  " + windows[j].getChildren()
							+ "R Value " + r);

					int a = 0;
					a = r.indexOf("BUILD SUCCESSFUL");
					if (a != -1) {
						System.out.println("Batch ran & Ready for next Batch");
					}

					else {
						System.out.println("Batch not successful'");
					}

					// while (foundWindow.inputChars("BUILD SUCCESSFUL") !=
					// true);
					// {
					// sleep(14);
					// }
					break;
				}

				sleep(3);
				logInfo("Batch Run", getRootTestObject().getScreenSnapshot());

			}

			catch (Exception e) {
			}
			System.out.println("Batch Run Done");

		}

	}
*/
	 

	public void clipboard() throws UnsupportedFlavorException, IOException {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();

		String myString = "Hello I am clip board pls copy me";
		StringSelection stringSelection = new StringSelection(myString);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
 
		String result = (String) clipboard.getData(DataFlavor.stringFlavor);
		System.out.println("String from Clipboard:" + result);

	}

	public String clipboardCopy() throws UnsupportedFlavorException,
	IOException {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();

		String myString = "Hello I am clip board pls copy me";
		StringSelection stringSelection = new StringSelection(myString);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
 
		String result = (String) clipboard.getData(DataFlavor.stringFlavor);
		return result;

	}

	 
	 

	public void ScannerReadInput() {
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		System.out.println("Reading input from console using Scanner in Java ");
		System.out.println("Please enter your input: ");
		String input = scanner.nextLine();
		System.out.println("User Input from console: " + input);
		System.out.println("Reading int from console in Java: ");
		int number = scanner.nextInt();
		System.out.println("Integer input: " + number);

	}

	public boolean dateCompare(String DateFormat) throws ParseException {

		// ----------------------Received date---------------
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String rdate = DateFormat;

		Date dt0 = df.parse(rdate);
		System.out.println("received date" + dt0);
 
		// ---------------------------------------------------
		// Get Today Date
		Date dt = Calendar.getInstance().getTime();

		Calendar cal = Calendar.getInstance();
		// cal.setTime(dt);
		dt = cal.getTime();
		System.out.println("current date" + dt);
 
		// ---------------------------------------------------
		// Add/Sub Today Date
		Date dt1;
		cal.add(Calendar.DATE, -5);
		dt1 = cal.getTime();
		System.out.println("applied date " + dt1);
 
		// ---------------------------------------------------
		// Compare applied(add/sub) date with recevied date

		if (dt0.after(dt1) && dt0.before(dt)) {

			dateFlag = true;
			System.out.println("dateFLAG -->" + dateFlag + "\n");

			// In between
		} else {
			System.out.println("dateFLAG -->" + dateFlag + "\n");
		}

		return dateFlag;

	}

	public boolean dateCompareGT_45(String DateFormat) throws ParseException {

		// ----------------------Received date---------------
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String rdate = DateFormat;

		Date dt0 = df.parse(rdate);
		System.out.println("received date" + dt0);
 
		// ---------------------------------------------------
		// Get Today Date
		Date dt = Calendar.getInstance().getTime();

		Calendar cal = Calendar.getInstance();
		// cal.setTime(dt);
		dt = cal.getTime();
		System.out.println("current date" + dt);
 
		// ---------------------------------------------------
		// Add/Sub Today Date
		Date dt1;
		cal.add(Calendar.DATE, -5);
		dt1 = cal.getTime();
		System.out.println("applied date (>5) " + dt1);
 
		// ---------------------------------------------------
		// Add/Sub Today Date
		Date dt2;
		cal.add(Calendar.DATE, -46);
		dt2 = cal.getTime();
		System.out.println("applied date (<45) " + dt2 + "\n");
 
		// ---------------------------------------------------

		// Compare applied(add/sub) date with recevied date
		if (dt0.before(dt1) && dt0.after(dt2)) {

			dateFlagGT45 = true;
			System.out.println("dateFlagGT45 -->" + dateFlagGT45);

			// In between
		}

		else {
			System.out.println("dateFlagGT45 -->" + dateFlagGT45);
		}

		return dateFlagGT45;

	}

	/**
	 * @return
	 * @throws Exception
	 */
	public int readBuild() throws Exception {

		Path p = Paths.get("C:/Program Files (x86)/PuTTY/Myputty.log.txt");

		WatchService watcher = null;
		// WatchKey key = p.register(watcher, ENTRY_MODIFY);
		// System.out.println("Watch Key " + key);

		int buildIndx = 0;
		Charset charset = Charset.forName("US-ASCII");

 
		try (BufferedReader reader = Files.newBufferedReader(p, charset)) {
			String line = null;

			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				// int buildIndx = line.indexOf("BUILD SUCCESSFUL");
				buildIndx = line.indexOf("Renewal batch Chunker started");

			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		return buildIndx;
	}

	public void propertyFile(String path) throws FileNotFoundException,
	IOException {
		// Read Property File for environments
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File(
					Constants.workingDir+"\\environments.properties")));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String citizenPortal = "";
		String caseWPortal = "";

		citizenPortal = props.getProperty("TST1_CWPortal");
		props.setProperty("SOME", "yo");
		System.out.println(citizenPortal);
		props.store(
				new FileOutputStream(
						Constants.workingDir+"\\Properties\\TestCaseLog.properties"),
				"s");
	}

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

			// citizenPortal = props.getProperty("TST1_CWPortal");
			props.clear();
			props.store(new FileOutputStream(path), "comments");


		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void propertySet(String path, String prop, String value)
			throws FileNotFoundException, IOException {
		//		OrderedProperties properties = new OrderedProperties();
		//		properties.load(new FileInputStream(new File("~/some.properties")));
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
			if(value != null) {
				// citizenPortal = props.getProperty("TST1_CWPortal");
				props.setProperty(prop, value);
				props.store(new FileOutputStream(path), "comments");
				System.out.println("$Property set: " + prop + " = "
						+ value);
			}
			else
				System.out.println("$Property Not set: " + prop + " = "
						+ value);		
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			// System.out.println("finally...");
		}
	}

	public static void propertySet(String path, HashMap<String, String> rowData)
			throws FileNotFoundException, IOException {
		System.out.println("propertySet @ " + path + " >>>>>");
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
//			props.clear();
			props.putAll(rowData);
			props.store(new FileOutputStream(path), "comments");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void propertyFile_Write(String path, String prop, String value)
			throws FileNotFoundException, IOException {
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
			if(value != null) {
				// citizenPortal = props.getProperty("TST1_CWPortal");
				props.setProperty(prop, value);
				props.store(new FileOutputStream(path), "comments");
				System.out.println("$Property set: " + prop + " = "
						+ value);
			}
			else
				System.out.println("$Property Not set: " + prop + " = "
						+ value);		
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String propertyGet(String path, String prop)
			throws FileNotFoundException, IOException {
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

		// props.store(new
		// FileOutputStream(Constants.workingDir+"\\Properties\\TestCaseLog.properties"),
		// "s");
	}

	public static String propertyFile_Read(String path, String prop)
			throws FileNotFoundException, IOException {
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

		// props.store(new
		// FileOutputStream(Constants.workingDir+"\\Properties\\TestCaseLog.properties"),
		// "s");
	}

	public void robot(KeyEvent key) {
		try {
			java.awt.Robot r = new java.awt.Robot();
			// r.keyPress(KeyEvent.key);
			r.mouseRelease(KeyEvent.VK_F12);
 		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String compare(String n1, String n2) {
		long l1 = Long.valueOf(n1);
		long l2 = Long.valueOf(n2);
		if (l1 > l2)
			// return (l1 < l2 ? -1 : 1);
			return Long.toString(l1);
		else
			return Long.toString(l2);

	}

	public static String maxValue(String n1, String n2) {
		long l1 = Long.valueOf(n1);
		long l2 = Long.valueOf(n2);
		if (l1 > l2)
			// return (l1 < l2 ? -1 : 1);
			return Long.toString(l1);
		else
			return Long.toString(l2);

	}

	public static String minValue(String n1, String n2) {
		long l1 = Long.valueOf(n1);
		long l2 = Long.valueOf(n2);
		if (l1 < l2)
			// return (l1 < l2 ? -1 : 1);
			return Long.toString(l1);
		else
			return Long.toString(l2);

	}

	public static void type(String characters) throws AWTException {
		Robot robot = new Robot();
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection stringSelection = new StringSelection(characters);
		ClipboardOwner clipboardOwner = null;
		clipboard.setContents(stringSelection, clipboardOwner);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public String databaseValidation(String ip, String dbname, String port,
			String sql, String input, String resultcolname) throws Exception {
		
		String ldapUserId= new Configuration().getProperty("ldapUserID");
	//	String ldapPassword= PasswordUtils.decryptString(new Configuration().getProperty("ldapPassword"));

		String ipAddress = ip;
		String portNumber = port;
		String databaseName = dbname;
		String username = ldapUserId;
		String password = "ldapUserID";

		ArrayList<Integer> resultList = new ArrayList<Integer>();

		String K = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection(ipAddress,
					portNumber, databaseName, username, password);
			// db.connect();
			con.connect();

			// Creating SQL Query

			ResultSet resultset;

			// sql =
			// "select STATUSCODE from Automation1.concernrolealternateid where alternateid  = ('726967846')";
			// sql = sql1 + "'"+ input + "'"+ ");";
			sql = sql + "'" + input + "'" + ")";

			// sql =
			// "select STATUSCODE from Automation1.concernrolealternateid fetch first 1 rows only";
			// sql
			// ="select * from landing.MMIS_Automation_landing where RECIPIENT_SSN in (227778210)";
			System.out.println("SQL after conversion: " + sql);
			resultset = con.query(sql);
			System.out.println("resultset \n" + resultset);

			// ArrayList <String> result = new ArrayList<String>();

			if (!resultset.next()) {
				System.out.println("No Data Found");
			}

			/*
			 * 
			 * while (resultset.next()) { result = rs.getInt(1);
			 * resultList.add(result); } return resultList;
			 */

			K = (resultset.getString(resultcolname));
			// while (resultset.next()) {
			//
			// K = (resultset.getString("STATUSCODE"));
			// count++;
			// System.out.println("Count " + count);
			//
			// }

			con.close();

		}

		catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("Database Query Result: " + K);

		return K;

	}

	public static String[] databaseValidation(String ip, String dbname, String port,
			String sql) throws Exception {
		
		String username= new Configuration().getProperty("ldapUserID");
///	String pwd= PasswordUtils.decryptString(new Configuration().getProperty("ldapPassword"));

		//String username = "ss";
		 String pwd = "ss";
		int j = 0;
		String[] columnValue = new String[30];
		CustomDB2Connection con = new CustomDB2Connection(ip, port, dbname, username, pwd);
		ResultSet resultset;
		con.connect();
		resultset = con.query(sql);
		ResultSetMetaData rsmd = resultset.getMetaData();
		int numberOfColumns = rsmd.getColumnCount();
		// for (int i = 1; i <= numberOfColumns; i++) {
		// if (i > 1) System.out.print(",  ");
		// String columnName = rsmd.getColumnName(i);
		// System.out.print(columnName);
		//
		// }
		// System.out.println("");
		//
		// if(!resultset.next())
		// {
		// System.out.println("No Data Found");
		// }
		//
		System.out.println("numberOfColumns " + numberOfColumns);
		while (resultset.next()) {
			for (int i = 1; i <= numberOfColumns; i++) {

				if (i > 1)
					System.out.print(",  ");
				columnValue[j] = resultset.getString(i);
				System.out.println(columnValue[j]);
				j++;

			}

		}

		con.close();

		return columnValue;

	}

	public String databaseValidation_MultipleRows(String ip, String dbname,
			String port, String sql1, String input, String sql2,
			String resultcolname) throws Exception {
		
		String ldapUserId= new Configuration().getProperty("ldapUserID");
//		String ldapPassword= PasswordUtils.decryptString(new Configuration().getProperty("ldapPassword"));

		String ipAddress = ip;
		String portNumber = port;
		String databaseName = dbname;
		String username = ldapUserId;
		String password = "ldapUserID";

		ArrayList<Integer> resultList = new ArrayList<Integer>();

		String K = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection(ipAddress,
					portNumber, databaseName, username, password);
			// db.connect();
			con.connect();

			// Creating SQL Query
			String sql;
			ResultSet resultset;

			// sql =
			// "select STATUSCODE from Automation1.concernrolealternateid where alternateid  = ('726967846')";
			// sql = sql1 + "'"+ input + "'"+ ");";
			sql = sql1 + "'" + input + "'" + ")" + sql2;

			// sql =
			// "select STATUSCODE from Automation1.concernrolealternateid fetch first 1 rows only";
			// sql
			// ="select * from landing.MMIS_Automation_landing where RECIPIENT_SSN in (227778210)";
			System.out.println("SQL after conversion: " + sql);
			resultset = con.query(sql);
			System.out.println("resultset \n" + resultset);

			//

			if (!resultset.next()) {
				System.out.println("No Data Found");
			}

			ArrayList<String[]> result = new ArrayList<String[]>();
			Statement stat = null;
			ResultSet rs = stat.executeQuery("SELECT ...");
			int columnCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				String[] row = new String[columnCount];
				for (int i = 0; i < columnCount; i++) {
					row[i] = rs.getString(i + 1);
				}
				result.add(row);
			}

			/*
			 * ArrayList <String> result = new ArrayList<String>(); while
			 * (resultset.next()) { result = resultset.getInt(1);
			 * resultList.add(result); } return resultList;
			 */

			/*
			 * final PreparedStatement ps = null ;
			 * 
			 * ResultSet rs=ps.getResultSet(); ResultSetMetaData
			 * rsmd=rs.getMetaData(); for(int i=1;i<=rsmd.getColumnCount();i++){
			 * 
			 * String result=((ResultSet) rsmd).getString(i); }
			 * 
			 * return result;
			 */

			while (resultset.next()) {
				K = (resultset.getString(resultcolname));
			}

			// while (resultset.next()) {
			//
			// K = (resultset.getString("STATUSCODE"));
			// count++;
			// System.out.println("Count " + count);
			//
			// }

			con.close();

		}

		catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("Database Query Result: " + K);

		return K;

	}

	public void Database_Compare(String ExpectedDBResult,
			String ActualDBResult, String ReportComment) {

		if (ExpectedDBResult == ActualDBResult
				|| ExpectedDBResult.equals(ActualDBResult)) {
			System.out.println(ReportComment);
			System.out.println("DB Record 1 pass"+ ReportComment);

		} else {
			System.out.println(ReportComment);
			System.out.println("DB Record 1 fail"+ ReportComment);

		}

	}

	 
	 

	public static String[] getRooms() throws SQLException,
	ClassNotFoundException {
		ArrayList<String> a = new ArrayList<String>();

		String sqlDriver = null;
		Class.forName(sqlDriver);
		String dtbSet = null;
		String dtbUsername = null;
		String dtbPassword = null;
		Connection con = DriverManager.getConnection(dtbSet, dtbUsername,
				dtbPassword);
		String dtbTbl2 = null;
		PreparedStatement ps = con.prepareStatement("SELECT room_name FROM "
				+ dtbTbl2);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			a.add(rs.getString(1));
		}

		return (String[]) a.toArray(new String[a.size()]);
	}

	 

	/*public static void InvokeOutlook(String environment, String shellScript,
			String argument, String logComment) throws AWTException,
			IOException {

		Robot robot = new Robot();

 		Random rand = new Random();
		AllUtils util = new AllUtils();
		Constants c = new Constants();
		IWindow[] windows = null;
		IWindow foundWindow = null;
		 
		Runtime.getRuntime()
		.exec("C:\\Program Files (x86)\\Microsoft Office\\Office14\\OUTLOOK.EXE //select outlook:Notices //f msgfilename ss.msg");

		// Runtime.getRuntime().exec("C:\\Program Files (x86)\\Microsoft Office\\Office14\\OUTLOOK.EXE/select outlook:Notices outlook /select outlook:Notices\\Automation Approval Notice Document_Dev2");
 
		Altbot p = new Altbot();
		p.type("cd /autosys/" + environment + "/Batch/");
 		robot.keyPress(KeyEvent.VK_ENTER);
 		robot.keyRelease(KeyEvent.VK_ENTER);
 
		p.type("./" + shellScript + argument);
 		robot.keyPress(KeyEvent.VK_ENTER);
 		robot.keyRelease(KeyEvent.VK_ENTER);
  
		System.out.println(logComment);
	}
*/
	public void wordReportwithImage() {

		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph p = doc.createParagraph();

		XWPFRun r = p.createRun();

		/*
		 * for(String imgFile : args) { int format;
		 * 
		 * if(imgFile.endsWith(".emf")) format = XWPFDocument.PICTURE_TYPE_EMF;
		 * else if(imgFile.endsWith(".wmf")) format =
		 * XWPFDocument.PICTURE_TYPE_WMF; else if(imgFile.endsWith(".pict"))
		 * format = XWPFDocument.PICTURE_TYPE_PICT; else
		 * if(imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg")) format =
		 * XWPFDocument.PICTURE_TYPE_JPEG; else if(imgFile.endsWith(".png"))
		 * format = XWPFDocument.PICTURE_TYPE_PNG; else
		 * if(imgFile.endsWith(".dib")) format = XWPFDocument.PICTURE_TYPE_DIB;
		 * else if(imgFile.endsWith(".gif")) format =
		 * XWPFDocument.PICTURE_TYPE_GIF; else if(imgFile.endsWith(".tiff"))
		 * format = XWPFDocument.PICTURE_TYPE_TIFF; else
		 * if(imgFile.endsWith(".eps")) format = XWPFDocument.PICTURE_TYPE_EPS;
		 * else if(imgFile.endsWith(".bmp")) format =
		 * XWPFDocument.PICTURE_TYPE_BMP; else if(imgFile.endsWith(".wpg"))
		 * format = XWPFDocument.PICTURE_TYPE_WPG; else {
		 * System.err.println("Unsupported picture: " + imgFile +
		 * ". Expected emf|wmf|pict|jpeg|png|dib|gif|tiff|eps|bmp|wpg");
		 * continue; }
		 * 
		 * r.setText(imgFile); r.addBreak(); r.addPicture(new
		 * FileInputStream(imgFile), format, imgFile, Units.toEMU(200),
		 * Units.toEMU(200)); // 200x200 pixels r.addBreak(BreakType.PAGE); }
		 * 
		 * FileOutputStream out = new FileOutputStream("images.docx");
		 * doc.write(out); out.close();
		 */
	}

	public void word() throws InvalidFormatException, IOException {
		XWPFDocument doc = new XWPFDocument();

		XWPFParagraph title = doc.createParagraph();
		XWPFRun run = title.createRun();
		run.setText("Fig.1 A Natural Scene");
		run.setBold(true);
		title.setAlignment(ParagraphAlignment.CENTER);

		String imgFile = "encabezado.jpg";
		FileInputStream is = new FileInputStream(imgFile);
		run.addBreak();
		run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile,
				Units.toEMU(200), Units.toEMU(200)); // 200x200 pixels
		is.close();

		FileOutputStream fos = new FileOutputStream("test4.docx");
		doc.write(fos);
		fos.close();
	}

	/*
	 * public void word2(){ XWPFDocument docx = new XWPFDocument();
	 * XWPFParagraph par = docx.createParagraph(); XWPFRun run =
	 * par.createRun(); run.setText(
	 * "Hello, World. This is my first java generated docx-file. Have fun.");
	 * run.setFontSize(13); InputStream pic = new
	 * FileInputStream("C:\\Users\\amitabh\\Pictures\\pics\\pool.jpg"); byte []
	 * picbytes = IOUtils.toByteArray(pic); docx.addPicture(picbytes,
	 * Document.PICTURE_TYPE_JPEG);
	 * 
	 * FileOutputStream out = new
	 * FileOutputStream("C:\\Users\\amitabh\\Pictures\\pics\\simple1.docx");
	 * docx.write(out); out.close(); pic.close(); }
	 */

	// ************** WORD Reports ********

	/*
	 * public void CustomXWPFDocument() { super(); }
	 * 
	 * public void CustomXWPFDocument(InputStream in) throws IOException {
	 * super(in); }
	 */

	/*
	 * public void createPicture(String blipId,int id, int width, int height) {
	 * final int EMU = 9525; width *= EMU; height *= EMU; //String blipId =
	 * getAllPictures().get(id).getPackageRelationship().getId();
	 * 
	 * 
	 * CTInline inline =
	 * createParagraph().createRun().getCTR().addNewDrawing().addNewInline();
	 * 
	 * String picXml = "" +
	 * "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">"
	 * +
	 * "   <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
	 * +
	 * "      <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
	 * + "         <pic:nvPicPr>" + "            <pic:cNvPr id=\"" + id +
	 * "\" name=\"Generated\"/>" + "            <pic:cNvPicPr/>" +
	 * "         </pic:nvPicPr>" + "         <pic:blipFill>" +
	 * "            <a:blip r:embed=\"" + blipId +
	 * "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>"
	 * + "            <a:stretch>" + "               <a:fillRect/>" +
	 * "            </a:stretch>" + "         </pic:blipFill>" +
	 * "         <pic:spPr>" + "            <a:xfrm>" +
	 * "               <a:off x=\"0\" y=\"0\"/>" + "               <a:ext cx=\""
	 * + width + "\" cy=\"" + height + "\"/>" + "            </a:xfrm>" +
	 * "            <a:prstGeom prst=\"rect\">" + "               <a:avLst/>" +
	 * "            </a:prstGeom>" + "         </pic:spPr>" + "      </pic:pic>"
	 * + "   </a:graphicData>" + "</a:graphic>";
	 * 
	 * //CTGraphicalObjectData graphicData =
	 * inline.addNewGraphic().addNewGraphicData(); XmlToken xmlToken = null; try
	 * { xmlToken = XmlToken.Factory.parse(picXml); } catch(XmlException xe) {
	 * xe.printStackTrace(); } inline.set(xmlToken);
	 * //graphicData.set(xmlToken);
	 * 
	 * inline.setDistT(0); inline.setDistB(0); inline.setDistL(0);
	 * inline.setDistR(0);
	 * 
	 * CTPositiveSize2D extent = inline.addNewExtent(); extent.setCx(width);
	 * extent.setCy(height);
	 * 
	 * CTNonVisualDrawingProps docPr = inline.addNewDocPr(); docPr.setId(id);
	 * docPr.setName("Picture " + id); docPr.setDescr("Generated"); }
	 */

	/*
	 * public void MSWord(){ DateFormat dateFormat = new
	 * SimpleDateFormat("dd-MMM-yyyy"); Calendar cal = Calendar.getInstance();
	 * String date = dateFormat.format(cal.getTime());
	 * 
	 * 
	 * 
	 * // Adding a file try {
	 * 
	 * // Working addPicture Code below... XWPFParagraph paragraphX =
	 * createParagraph(); paragraphX.setAlignment(ParagraphAlignment.CENTER);
	 * 
	 * String blipId = paragraphX.getDocument().addPictureData( new
	 * FileInputStream(new File("D://c2//WLB.jpg")), PICTURE_TYPE_JPEG);
	 * System.out.println("4" + blipId); System.out.println(document
	 * .getNextPicNameNumber(PICTURE_TYPE_JPEG)); createPicture(blipId,
	 * getNextPicNameNumber(PICTURE_TYPE_JPEG), 200, 75);
	 * System.out.println("5");
	 * 
	 * } catch (InvalidFormatException e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); } // insert doc details // Createa a para -1
	 * XWPFParagraph paragraphOne = createParagraph();
	 * paragraphOne.setAlignment(ParagraphAlignment.CENTER); XWPFRun
	 * paragraphOneRunOne = paragraphOne.createRun();
	 * paragraphOneRunOne.setBold(true); paragraphOneRunOne.setFontSize(20);
	 * paragraphOneRunOne.setFontFamily("Verdana");
	 * paragraphOneRunOne.setColor("000070");
	 * paragraphOneRunOne.setText("Daily Status Report");
	 * 
	 * // Createa a para -2 XWPFParagraph paragraphTwo = createParagraph();
	 * paragraphTwo.setAlignment(ParagraphAlignment.CENTER); XWPFRun
	 * paragraphTwoRunOne = paragraphTwo.createRun();
	 * paragraphTwoRunOne.setFontSize(12);
	 * paragraphTwoRunOne.setFontFamily("Verdana");
	 * paragraphTwoRunOne.setColor("000070"); paragraphTwoRunOne.setText(date);
	 * paragraphTwoRunOne.addBreak();
	 * 
	 * // Createa a para -3 XWPFParagraph paragraphThree = createParagraph();
	 * paragraphThree.setAlignment(ParagraphAlignment.LEFT); XWPFRun
	 * paragraphThreeRunOne = paragraphThree.createRun();
	 * paragraphThreeRunOne.setFontSize(14);
	 * paragraphThreeRunOne.setFontFamily("Verdana");
	 * paragraphThreeRunOne.setColor("000070");
	 * paragraphThreeRunOne.setText("5.30 AM PST");
	 * paragraphThreeRunOne.addBreak();
	 * 
	 * // Createa a para -4 XWPFParagraph paragraphFour = createParagraph();
	 * paragraphFour.setAlignment(ParagraphAlignment.LEFT); XWPFRun
	 * paragraphFourRunOne = paragraphFour.createRun();
	 * paragraphFourRunOne.setBold(true);
	 * paragraphFourRunOne.setUnderline(UnderlinePatterns.SINGLE);
	 * paragraphFourRunOne.setFontSize(10);
	 * paragraphFourRunOne.setFontFamily("Verdana");
	 * paragraphFourRunOne.setColor("000070");
	 * paragraphFourRunOne.setText("ABCD");
	 * 
	 * // insert doc details end
	 * 
	 * XWPFParagraph paragraphFive = createParagraph();
	 * paragraphFive.setAlignment(ParagraphAlignment.RIGHT); XWPFRun
	 * paragraphFiveRunOne = paragraphFive.createRun();
	 * paragraphFiveRunOne.addBreak(); paragraphFourRunOne.setBold(true);
	 * paragraphFourRunOne.setUnderline(UnderlinePatterns.SINGLE);
	 * paragraphFourRunOne.setFontSize(10);
	 * paragraphFourRunOne.setFontFamily("Verdana");
	 * paragraphFourRunOne.setColor("000070");
	 * paragraphFourRunOne.setText("ABCD00000000000");
	 * 
	 * FileOutputStream outStream = null; try { double x = Math.random(); String
	 * fileName = "D:\\c2\\" + String.valueOf(x) + ".docx"; outStream = new
	 * FileOutputStream(fileName); } catch (FileNotFoundException e) {
	 * System.out.println("First Catch"); e.printStackTrace(); } try {
	 * write(outStream); outStream.close(); } catch (FileNotFoundException e) {
	 * System.out.println("Second Catch"); e.printStackTrace(); } catch
	 * (IOException e) { System.out.println("Third Catch"); e.printStackTrace();
	 * }
	 * 
	 * 
	 * }
	 */

	public String getDatePart() {

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
		int millis = now.get(Calendar.MILLISECOND);

		// System.out.printf("%d-%02d-%02d %02d:%02d:%02d.%03d", year, month,
		// day, hour, minute, second, millis);
		return String.valueOf(day);

	}

	 
	 

	 

	 

	/**
	 * Get the current line number.
	 * 
	 * @return int - Current line number.
	 */
	public int getLineNumber() {
		return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}

	public void printLog() {
		StackTraceElement l = new Exception().getStackTrace()[0];
		System.out.println(l.getClassName() + "/" + l.getMethodName() + ":"
				+ l.getLineNumber());
	}

 
	 
 
	/**
	 * Returns a HashMap of the row for the given testCaseID, sheetName and
	 * Excel sheet(xlsx).
	 * 
	 * @param filePath
	 *            eg:
	 *            Constants.workingDir+"\\Datapool\\Datapool.xlsx"
	 * @param sheetName
	 *            eg: "Sanity Test"
	 * @param testCaseID
	 *            eg: "SmokeTest_3HH"
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getTestDataXlsx(String filePath,
			String sheetName, int headerRowNum, String testCaseID) {
		HashMap<String, String> rowData = new HashMap<>();
		XSSFSheet sheet = null;
		XSSFWorkbook workbook = null;
		XSSFRow tcRow, headerRow;
		FileInputStream file = null;

		try {
			file = new FileInputStream(new File(filePath));

			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = workbook.getSheet(sheetName);
		headerRow = sheet.getRow(headerRowNum); // Row number which contains the columns
		// names
		tcRow = sheet.getRow(findRow(sheet, testCaseID));
		// iterate through each columns
		Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = headerRow
				.cellIterator();
		int i = 0;
		while (cellIterator.hasNext()) {
			org.apache.poi.ss.usermodel.Cell cell = cellIterator.next();

			rowData.put(cellValueStr(workbook, headerRow, i),
					cellValueStr(workbook, tcRow, i));
			// System.out.println(i + ":Key="
			// + cellValueStr(workbook, headerRow, i) + ",Value="
			// + cellValueStr(workbook, tcRow, i));

			i++;
		}
		try {
			workbook.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DataPool Records - " + sheetName +" - " + testCaseID + ": \n"+  rowData  );
		try {
			// Prints Hashmap data to property file
			//	printHashmap(rowData);// The date elements are convereted from number to date; Some elements are modified and put back to rowData and need to add few more elements separately, So, the values are written to property file after modification which need to be done from testdatapool itself.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowData;
	}

	/**
	 * Returns a HashMap of the row for the given testCaseID, sheetName and
	 * Excel sheet(xlsx).
	 * 
	 * @param filePath
	 *            eg:
	 *            Constants.workingDir+"\\Datapool\\Datapool_Automation.xlsx"
	 * @param sheetName
	 *            eg: "Sanity Test"
	 * @param testCaseID
	 *            eg: "SmokeTest_3HH"
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getTestDataXlsx(String filePath,
			String sheetName, String testCaseID) {
		HashMap<String, String> rowData = new HashMap<>();
		XSSFSheet sheet = null;
		XSSFWorkbook workbook = null;
		XSSFRow tcRow, headerRow;
		FileInputStream file = null;

		try {
			file = new FileInputStream(new File(filePath));

			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = workbook.getSheet(sheetName);
		headerRow = sheet.getRow(0); // Row number which contains the column
		// names
		tcRow = sheet.getRow(findRow(sheet, testCaseID));
		// iterate through each columns
		Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = headerRow
				.cellIterator();
		int i = 0;
		while (cellIterator.hasNext()) {
			org.apache.poi.ss.usermodel.Cell cell = cellIterator.next();

			rowData.put(cellValueStr(workbook, headerRow, i),
					cellValueStr(workbook, tcRow, i));
			// System.out.println(i + ":Key="
			// + cellValueStr(workbook, headerRow, i) + ",Value="
			// + cellValueStr(workbook, tcRow, i));

			i++;
		}
		try {
			workbook.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DataPool Records - " + sheetName +" - " + testCaseID + ": \n"+  rowData  );
		try {
			// Prints Hashmap data to property file
			//	printHashmap(rowData);// The date elements are convereted from number to date; Some elements are modified and put back to rowData and need to add few more elements separately, So, the values are written to property file after modification which need to be done from testdatapool itself.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowData;
	}

	/**
	 * Retuns a HashMap with the Random names of 8 persons from the list of names given in the sheetName located at filePath
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public HashMap<String, String> getNamesMap(String filePath,
			String sheetName) {
		HashMap<String, String> namesData = new HashMap<>();
		XSSFSheet sheet = null;
		XSSFWorkbook workbook = null;
		XSSFRow tcRow, headerRow;
		FileInputStream file = null;
		int noOfPersons = 8;

		try {
			file = new FileInputStream(new File(filePath));

			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = workbook.getSheet(sheetName);
		int noOfRows = sheet.getPhysicalNumberOfRows();

		for(int i = 1; i <= noOfPersons; i++){

			tcRow = sheet.getRow(randInt(1, noOfRows));
			namesData.put("firstName"+i, cellValueStr(workbook, tcRow, 0));
			tcRow = sheet.getRow(randInt(1, noOfRows));
			namesData.put("middleName"+i, cellValueStr(workbook, tcRow, 1));
			tcRow = sheet.getRow(randInt(1, noOfRows));
			namesData.put("lastName"+i, cellValueStr(workbook, tcRow, 2));

		}	
		try {
			workbook.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return namesData;
	}


	/**
	 * Returns random name from the given columnWanted from the sheetName at filePath
	 * @param filePath
	 * @param sheetName
	 * @param columnWanted
	 * @return
	 */
	public String getName(String filePath,
			String sheetName, String columnWanted) {
		XSSFSheet sheet = null;
		XSSFWorkbook workbook = null;
		XSSFRow tcRow, headerRow;
		FileInputStream file = null;

		try {
			file = new FileInputStream(new File(filePath));

			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = workbook.getSheet(sheetName);
		int noOfRows = sheet.getPhysicalNumberOfRows();
		Integer columnNo = null;

		headerRow = sheet.getRow(0);

		for(org.apache.poi.ss.usermodel.Cell cell:headerRow){
			if (cell.getStringCellValue().equals(columnWanted)){
				columnNo = cell.getColumnIndex();
			}
		}


		if (columnNo != null){
			tcRow = sheet.getRow(randInt(1, noOfRows));
			return cellValueStr(workbook, tcRow, columnNo);
		}else{
			System.out.println("could not find column " + columnWanted + " in first row");
		}
		try {
			workbook.close();
			file.close();
		} catch (IOException e) {
 			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Returns a psuedo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimim value
	 * @param max Maximim value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

		// Usually this can be a field rather than a method variable
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public int findRow(XSSFSheet sheet, String cellContent) {/*
		for (org.apache.poi.ss.usermodel.Row row : sheet) {
			for (org.apache.poi.ss.usermodel.Cell cell : row) {
				if (cell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().trim()
							.equals(cellContent)) {
						return row.getRowNum();
					}
				}
			}
		}
	*/	return 0;
	}

	public String cellValueStr(XSSFWorkbook workbook, XSSFRow row, int cellNum) {

		DataFormatter objDefaultFormat = new DataFormatter();
		FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator(
				(XSSFWorkbook) workbook);

		XSSFCell cellValue = row.getCell(cellNum);
		objFormulaEvaluator.evaluate(cellValue); // This will evaluate the cell,
		// And any type of cell will
		// return string value
		String cellValueStr = objDefaultFormat.formatCellValue(cellValue,
				objFormulaEvaluator);
		return cellValueStr;
	}

	public HashMap<String, String> gethashMapFromCSV(String filePath) {
		BufferedReader br = null;

		try {
			String lineContent;
			br = new BufferedReader(new FileReader(filePath));
			String responseData = "";

			// read file until responseData class is found in the string.
			while ((lineContent = br.readLine()) != null) {
				//				if (lineContent.contains("responseData class")) {
				//					if(!lineContent.contains("xml"))
				responseData = responseData + lineContent;
				//					continue;
				//				}
			}

			responseData = constructXMLDataString(responseData);
			System.out.println(responseData);
			getElementsIntoHashMap(new InputSource(new StringReader(
					responseData)));
			// printHashmap();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return xmlData;
	}

	public HashMap<String, String> gethashMapFromXML(String filePath) {
		BufferedReader br = null;

		try {
			String lineContent;
			br = new BufferedReader(new FileReader(filePath));
			//			String responseData = "";
			//
			//			while ((lineContent = br.readLine()) != null) {
			//						responseData = responseData + lineContent;
			//			}
			//
			//			responseData = constructXMLDataString(responseData);
			// System.out.println(responseData);
			//			getElementsIntoHashMap(new InputSource(new StringReader(
			//					responseData)));
			getElementsIntoHashMap(new InputSource(br));
			//			 printHashmap();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return xmlData;
	}

	private void printHashmap() throws Exception {
		// To print hashmap
		for (Map.Entry<String, String> entry : xmlData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
			AllUtils.propertySet(new Constants().propertyFilePath_Output, entry.getKey(), entry.getValue());
		}
	}

	private void printHashmap(HashMap<String, String> rowData) throws Exception {
		// To print hashmap
		String propFileName = new Constants().propertyFilePath_Output;
		for (Map.Entry<String, String> entry : rowData.entrySet()) {
			System.out.println("$Prop Set "+ entry.getKey() + " =:" + entry.getValue());
			AllUtils.propertySet(propFileName, entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Converts the string into xml string by replacing &lt; &gt; and &quot;
	 * 
	 * @param responseString
	 * @return responseString
	 */
	public static String constructXMLDataString(String responseString) {

		responseString = responseString.replace("&lt;", "<");
		responseString = responseString.replace("&gt;", ">");
		responseString = responseString.replace("&quot;", "\"");
		// System.out.println(responseString);
		return responseString;
	}

	/**
	 * Reads the xml File and adds all the Elements into xmlData hashmap
	 * variable.
	 * 
	 * @param xmlFile
	 */
	public HashMap<String, String> getElementsIntoHashMap(InputSource xmlFile) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			// System.out.println("Root element :"
			// + doc.getDocumentElement().getNodeName());
			try {
				if (doc.hasChildNodes()) {

					printNote(doc.getChildNodes());

				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlData;
	}

	private static void printNote(NodeList nodeList) {

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				// get the string after : from the Node Name and put that key
				// and value in the hashmap
				String strKey = tempNode.getNodeName().substring(
						tempNode.getNodeName().lastIndexOf(":") + 1);
				xmlData.put(strKey, tempNode.getTextContent());

			}

			if (tempNode.hasChildNodes()) {
				// loop again if has child nodes
				printNote(tempNode.getChildNodes());

			}

			// System.out.println("Node Name =" + tempNode.getNodeName()
			// + " [CLOSE]");
		}
	}

	private static void printAllNote(NodeList nodeList) {

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				// get node name and value
				System.out.println("\nNode Name =" + tempNode.getNodeName()
						+ " [OPEN]");
				System.out.println("Node Value =" + tempNode.getTextContent());

				// get the string after : from the Node Name and put that key
				// and value in the hashmap
				String strKey = tempNode.getNodeName().substring(
						tempNode.getNodeName().lastIndexOf(":") + 1);
				xmlData.put(strKey, tempNode.getTextContent());

				// Code to print Attributes of the node.
				if (tempNode.hasAttributes()) {
					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
						System.out.println("attr name : " + node.getNodeName());
						System.out.println("attr value : "
								+ node.getNodeValue());

					}

				}

				if (tempNode.hasChildNodes()) {
					// loop again if has child nodes
					printNote(tempNode.getChildNodes());

				}

				// System.out.println("Node Name =" + tempNode.getNodeName()
				// + " [CLOSE]");
			}
		}
	}

	/**
	 * Returns an Element string value for the xPath from the xmlString tag, stored as CDATA inside the xml file.
	 * @param xmlFilePath
	 * @param xPath
	 * @return
	 */
	public String getElementByXPathFromCDATA(String xmlFilePath, String xPath){
		DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		org.w3c.dom.Document document = null;
		XPath xPathVar =  XPathFactory.newInstance().newXPath();
		try {

			builder = builderFactory.newDocumentBuilder();
			document = builder.parse(new InputSource(new StringReader(getElementByXPathFromXML(xmlFilePath, "//xmlString"))));
			document.getDocumentElement().normalize();
			Object result = xPathVar.compile(xPath).evaluate(document, XPathConstants.NODE);
			Node node = (Node)result;
			// System.out.println(node3.getTextContent());
			return node.getTextContent();
		} catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retunrs an Element string value for the xPath from the xml file.
	 * @param xmlFilePath
	 * @param xPath
	 * @return
	 */
	public String getElementByXPathFromXML(String xmlFilePath, String xPath){
		DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder builder = null;
		org.w3c.dom.Document document = null;
		XPath xPathVar =  XPathFactory.newInstance().newXPath();
		xPathVar.setNamespaceContext(new NamespaceContext() {

            @Override
            public Iterator getPrefixes(String arg0) {
                return null;
            }

            @Override
            public String getPrefix(String arg0) {
                return null;
            }

            @Override
            public String getNamespaceURI(String arg0) {
            	if("soap".equals(arg0)) {
                    return "http://schemas.xmlsoap.org/soap/envelope/";
                }
                else if("vnem".equals(arg0)) {
                    return "http://vnem.ee.sim.gmail.com";
                }
                else if("ns".equals(arg0)) {
                    return "http://yay.com/yay/yay-core/2.0";
                }
                else if("ns1".equals(arg0)) {
                    return "http://yay.com/yay/structures/2.0";
                }
                else if("ns2".equals(arg0)) {
                    return "http://yay.com/yay/yay-core/2.0";
                }
                else if("ns3".equals(arg0)) {
                    return "http://at.dsh.san.com/extension/1.0";
                }
                else if("ns4".equals(arg0)) {
                    return "http://hix.san.com/0.1/hix-core";
                }
                else if("ns5".equals(arg0)) {
                    return "http://hix.san.com/0.1/hix-ee";
                }
                else if("ns6".equals(arg0)) {
                    return "http://yay.com/yay/domains/screening/2.1";
                }
                else if("ns7".equals(arg0)) {
                    return "http://hix.san.com/0.1/hix-pm";
                }
                else if("ns8".equals(arg0)) {
                    return "http://at.dsh.san.com/exchange/1.0";
                }
                else if("vlp".equals(arg0)) {
                    return "http://vlpdvc.ee.sim.gmail.com";
                }
            	return null;
            }
        });
		try {
			builder = builderFactory.newDocumentBuilder();
			document = builder.parse(xmlFilePath);
			document.getDocumentElement().normalize();

			Object result = xPathVar.compile(xPath).evaluate(document, XPathConstants.NODE);
			Node node = (Node)result;
			return node.getTextContent();

		} catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * updates all the element values given in the updateList in the xmlTemplateFilePath and creates new file at xmlFilePath
	 * @param xmlTemplateFilePath
	 * @param xmlFilePath
	 * @param updateList
	 */
	public void setElementByXPathFromXML(String xmlTemplateFilePath, String xmlFilePath, String[][] updateList){
		DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder builder = null;
		org.w3c.dom.Document document = null;
		XPath xPathVar =  XPathFactory.newInstance().newXPath();
		xPathVar.setNamespaceContext(new NamespaceContext() {

            @Override
            public Iterator getPrefixes(String arg0) {
                return null;
            }

            @Override
            public String getPrefix(String arg0) {
                return null;
            }

            @Override
            public String getNamespaceURI(String arg0) {
            	if("soap".equals(arg0)) {
                    return "http://schemas.xmlsoap.org/soap/envelope/";
                }
                else if("vnem".equals(arg0)) {
                    return "http://vnem.ee.sim.gmail.com";
                }
                else if("ns".equals(arg0)) {
                    return "http://yay.com/yay/yay-core/2.0";
                }
                else if("ns1".equals(arg0)) {
                    return "http://yay.com/yay/structures/2.0";
                }
                else if("ns2".equals(arg0)) {
                    return "http://yay.com/yay/yay-core/2.0";
                }
                else if("ns3".equals(arg0)) {
                    return "http://at.dsh.san.com/extension/1.0";
                }
                else if("ns4".equals(arg0)) {
                    return "http://hix.san.com/0.1/hix-core";
                }
                else if("ns5".equals(arg0)) {
                    return "http://hix.san.com/0.1/hix-ee";
                }
                else if("ns6".equals(arg0)) {
                    return "http://yay.com/yay/domains/screening/2.1";
                }
                else if("ns7".equals(arg0)) {
                    return "http://hix.san.com/0.1/hix-pm";
                }
                else if("ns8".equals(arg0)) {
                    return "http://at.dsh.san.com/exchange/1.0";
                }
                else if("vlp".equals(arg0)) {
                    return "http://vlpdvc.ee.sim.gmail.com";
                }
            	return null;
            }
        });
		try {
			builder = builderFactory.newDocumentBuilder();
			document = builder.parse(xmlTemplateFilePath);
			document.getDocumentElement().normalize();
			for(int i=0; i<updateList.length; i++){
				if((updateList[i][0]).contains("//")){
					try{
					Object result = xPathVar.compile(updateList[i][0]).evaluate(document, XPathConstants.NODE);
					Node node = (Node)result;
					node.setTextContent(updateList[i][1]);
					} catch (Exception e){
						System.out.println("Node " + updateList[i][0] + " not found in XML. Continuing with other node updates.");
					}
				}else{
					new AllUtils().findReplace(xmlFilePath, updateList[i][0], updateList[i][1]);
				}
			}

			// Use a Transformer for output
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(xmlFilePath);
			transformer.transform(source, result);

		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	}

	public String getElementByXPathFromXML_VLP(String xmlFilePath, String xPath){
		DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder builder = null;
		org.w3c.dom.Document document = null;
		XPath xPathVar =  XPathFactory.newInstance().newXPath();
		xPathVar.setNamespaceContext(new NamespaceContext() {

            @Override
            public Iterator getPrefixes(String arg0) {
                return null;
            }

            @Override
            public String getPrefix(String arg0) {
                return null;
            }

            @Override
            public String getNamespaceURI(String arg0) {
            	if("soap".equals(arg0)) {
                    return "http://schemas.xmlsoap.org/soap/envelope/";
                }
    			else if("Automation".equals(arg0)) {
                    return "http://www.Automationsoftware.com";
                }
                else if("Automation1".equals(arg0)) {
                    return "http://remote.facade.Selenium.custom.Automation";
                }
                else if("ns".equals(arg0)) {
                    return "http://remote.vlp.ws.Selenium.custom.Automation";
                }
                else if("ns2".equals(arg0)) {
                    return "http://exception.util.Automation/xsd";
                }
                else if("ns3".equals(arg0)) {
                    return "http://util.java/xsd";
                }
                else if("ns4".equals(arg0)) {
                    return "http://message.util.Automation/xsd";
                }
                else if("ns5".equals(arg0)) {
                    return "http://struct.facade.Selenium.custom.Automation/xsd";
                }
                else if(XMLConstants.DEFAULT_NS_PREFIX.equals(arg0)){
                    return "http://vlpdvc.ee.sim.gmail.com";
                }
            	return XMLConstants.NULL_NS_URI;
            }
        });
		try {
			builder = builderFactory.newDocumentBuilder();
			document = builder.parse(xmlFilePath);
			document.getDocumentElement().normalize();

			Object result = xPathVar.compile(xPath).evaluate(document, XPathConstants.NODE);
			Node node = (Node)result;
			return node.getTextContent();

		} catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setElementByXPathFromXML_VLP(String xmlTemplateFilePath, String xmlFilePath, String[][] updateList){
		DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder builder = null;
		org.w3c.dom.Document document = null;
		XPath xPathVar =  XPathFactory.newInstance().newXPath();
		xPathVar.setNamespaceContext(new NamespaceContext() {

            @Override
            public Iterator getPrefixes(String arg0) {
                return null;
            }

            @Override
            public String getPrefix(String arg0) {
                return null;
            }

            @Override
            public String getNamespaceURI(String arg0) {
            	if("soap".equals(arg0)) {
                    return "http://schemas.xmlsoap.org/soap/envelope/";
                }
    			else if("Automation".equals(arg0)) {
                    return "http://www.Automationsoftware.com";
                }
                else if("Automation1".equals(arg0)) {
                    return "http://remote.facade.Selenium.custom.Automation";
                }
                else if("ns".equals(arg0)) {
                    return "http://remote.vlp.ws.Selenium.custom.Automation";
                }
                else if("ns2".equals(arg0)) {
                    return "http://exception.util.Automation/xsd";
                }
                else if("ns3".equals(arg0)) {
                    return "http://util.java/xsd";
                }
                else if("ns4".equals(arg0)) {
                    return "http://message.util.Automation/xsd";
                }
                else if("ns5".equals(arg0)) {
                    return "http://struct.facade.Selenium.custom.Automation/xsd";
                }
                else if(XMLConstants.DEFAULT_NS_PREFIX.equals(arg0)){
                    return "http://vlpdvc.ee.sim.gmail.com";
                }
            	return XMLConstants.NULL_NS_URI;
            }
        });
		try {
			builder = builderFactory.newDocumentBuilder();
			document = builder.parse(xmlTemplateFilePath);
			document.getDocumentElement().normalize();
			for(int i=0; i<updateList.length; i++){
				try{
				Object result = xPathVar.compile(updateList[i][0]).evaluate(document, XPathConstants.NODE);
				Node node = (Node)result;
				node.setTextContent(updateList[i][1]);
				} catch (Exception e){
					System.out.println("Node " + updateList[i][0] + " not found in XML. Continuing with other node updates.");
				}
			}

			// Use a Transformer for output
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			transformer = tFactory.newTransformer();

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(xmlFilePath);
			transformer.transform(source, result);

		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	}
	private BufferedWriter prepareFileToWrite(String filePath)
			throws IOException {
		File file = new File(filePath);
		File parentFile = new File(file.getParent());
		if (!parentFile.exists()) {
			parentFile.mkdirs();

		}
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
		BufferedWriter bw = new BufferedWriter(fw);
		return bw;
	}

 
	
	// Reads Excel data and puts in a 2 dimentional string array
	
		public static class XLReader {/*

			public static String[][] xlRead(String path, String sheetName){
				XSSFWorkbook wb;
				FileInputStream fis;
				XSSFSheet sheet;
				int rowcount=0;
				int colcount=0;
				String[][] xldata = null;
				try{
					fis = new FileInputStream(new File(path));
					wb = new XSSFWorkbook(fis);
					sheet= wb.getSheet(sheetName);
					rowcount = sheet.getLastRowNum()+1;
					colcount  = sheet.getRow(1).getLastCellNum();
					xldata=new String[rowcount][colcount];
					for(int i=1; i<rowcount;i++){
						for(int j=0;j<colcount;j++){
						xldata[i][j]=XLReader.cellToString(sheet.getRow(i).getCell(j));
						}
					}
					wb.close();
		 		}catch(Exception e){
					System.out.println(e.getMessage());
				}
				
				return xldata;
			}
			
			public static String cellToString(XSSFCell column){
				Object result;
				int type = column.getCellType();
					switch(type){
						case 0:
							result = (int)column.getNumericCellValue();
							break;
						case 1:
							result = column.getStringCellValue();
							break;
						default:
							throw new RuntimeException("This column data type is not identified");
					}
				return result.toString();
			}
			
		*/}

	public void findReplace(String srcFilePath, String oldValue, String newValue){
		ArrayList<String> lines = new ArrayList<String>();
	    String line = null;

        File f1=null;
        FileReader fr=null;
        BufferedReader br=null;
        FileWriter fw=null;
        BufferedWriter out=null;
        try {
            f1 = new File(srcFilePath);
            fr = new FileReader(f1);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line.contains(oldValue))
                    line = line.replace(oldValue, newValue);
                lines.add(line);
            }

            fw = new FileWriter(f1);
            out = new BufferedWriter(fw);
            for (String s : lines)
                out.write(s);
            out.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
            fr.close();
            br.close();
            out.close();
            }catch(IOException ioe)

            {
                ioe.printStackTrace();
            }
        }
	}
		
	public void testMain(Object[] args) throws Exception {
		System.out.println(".Call any method here for a quick debugging!");
		String command = "D:\\AutomationProjects\\ExternalApps\\jmeter\\bin\\jmeter  -n -t";
		String param = Constants.workingDir+"\\webservices\\NonEsiMecWebService.jmx";
		String batchFilePath = Constants.workingDir+"\\webservices\\webservices3.bat";

		// Runtime.getRuntime().exec("D:\\AutomationProjects\\ExternalApps\\jmeter\\bin\\jmeter  -n -t D:\\AutomationProjects\\Automation_Selenium\\webservices\\NonEsiMecWebService.jmx\r\n"
		// );

		runWebService(batchFilePath);
		// commandPrompt();
		// command();
		// invokeProcess(command, param);
		// webServiceTest_JMeter();
		System.out.println("Utils End.");
	}

}
