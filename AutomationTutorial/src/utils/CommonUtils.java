package utils;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.math.NumberUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * Description : Common Utility Functions
 * 
 * @author Sangam
 */

public class CommonUtils {
	//this static variable is used to keep track of
	//page numbers in takescreenshotandaddtopdf method.
	static int pdfPageCount = 0;


	/**
	 * Returns whether or not a value is a number.
	 */
	public static boolean isNumber(String value) {
		return NumberUtils.isNumber(value);
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
	public static boolean isDateAfter(String date, String after, String dateFormat) {
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
	public static boolean isDateBefore(String date, String before, String dateFormat) {
		Date date1 = parseDate(date, dateFormat);
		Date date2 = parseDate(before, dateFormat);

		if (date1 == null || date2 == null) {
			return false;
		}

		return date1.before(date2);
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
		//handle null value;
		if (str == null ) {
			return str;
		}else{
			return str.trim();
		}

	}//end of trim
	/**
	 * Returns the string with in GMT date/time formatted for web service testing
	 * int is the offset amount;
	 * 
	 */
	public static String getGMTDate(int hourOffset) {
		long hour = 3600*1000; // in milli-seconds

		//getting 
		Date date =new Date();
		Date date2 = new Date(date.getTime() + hourOffset * hour);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf2.setTimeZone(TimeZone.getTimeZone("GMT"));

		String formattedwDate = sdf2.format(date2).toString();

		return formattedwDate;
	}//end of trim


	/**
	 *
	 * Returns a string with the  current Date and time value of yyyy-MM-dd_HH:mm:ss
	 * 
	 * Excel saves all date fields as a Serial date value.
	 */
	public static String getCurrentDateTime() {
		String formatDate; 

		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		//get current date time with Date()
		Date date = new Date();

		System.out.println(dateFormat.format(date));
		formatDate = dateFormat.format(date.getTime());
		System.out.println(formatDate);

		return formatDate;

	}//end of getCurrentDateTime



	/**
	 * the input dob format is YYYY-MM-DD
	 * @param dob
	 * @return age
	 */
	public static int calculateAge(String dob){
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

		age = thisYear-yearDOB;
		if(thisMonth < monthDOB){
			age = age-1;
		}
		if(thisMonth == monthDOB && thisDay < dayDOB){
			age = age-1;
		}
		System.out.println("The age of user is : " + age);

		return age;

	}//end of calculateAge

	/*
	 * Excel phone number is read into Eclipse sometimes as 7.634331234E9
	 * 
	 */
	public static String convertToPhoneNumber(String number){
		if (number == null){
			return number;
		}
		String phoneNumber = number;

		if (number.contains("E")){
			// Some of the phone numbers have E9 when it read the excel number.
			int positionOfChar = number.indexOf(".");
			int positionOfEndChar = number.indexOf("E");
			String test2 = number.substring(0, positionOfChar  );
			String test3 = number.substring(positionOfChar +1, positionOfEndChar );
			phoneNumber = test2 + test3;
			while  (phoneNumber.length() < 10){
				phoneNumber = phoneNumber +"0";
			}
		}

		return phoneNumber;
	}


	/**
	 * remove the -#### if it exits
	 * @param fullZipcode
	 * @return
	 */
	public static String plainZipCode (String fullZipcode){
		int postionOfEndingDash;
		int stingLength = fullZipcode.length();
		String zipCode; 
		postionOfEndingDash = fullZipcode.indexOf("-");
		if (stingLength > 0 && (postionOfEndingDash >0 )){
			//postionOfEndingDash = fullZipcode.indexOf("-");
			//getting text between the [ ]
			zipCode = fullZipcode.substring(0,postionOfEndingDash);
		} else{
			zipCode = fullZipcode;
		}

		return zipCode;
	}

	/*
	 * Return the content of the clip board.
	 */
	public static String getClipboard() {
		// get the system clip board

		Clipboard systemClipboard =
				Toolkit
				.getDefaultToolkit()
				.getSystemClipboard();
		// get the contents on the clip board in a 
		// transferable object
		Transferable clipboardContents =
				systemClipboard
				.getContents(
						null);
		// check if clip board is empty
		if (clipboardContents
				== null) {
			return ("Clipboard is empty!!!");
		} else
			try {
				// see if DataFlavor of 
				// DataFlavor.stringFlavor is supported
				if (clipboardContents
						.isDataFlavorSupported(
								DataFlavor
								.stringFlavor)) {
					// return text content
					String returnText =
							(
									String) clipboardContents
							.getTransferData(
									DataFlavor
									.stringFlavor);
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
	public static void takeScreenShotAndAddToPDF(String folderPath, String fileName, boolean firstImage) {

		try {			
			if ( firstImage){
				pdfPageCount = 1;
			}else{
				pdfPageCount++;
			}//end if else

			//take the screen shot
			takeScreenShot(folderPath);
			// Document1 will always store the current screen shot.
			Document document1 = new Document();		
			document1.setPageSize(PageSize.A4);		
			// add the screen shot to a PDf file.
			PdfWriter.getInstance(document1, new FileOutputStream(folderPath+"/ScreenShots/temp/image2.pdf"));
			document1.open();
			String currentTime = Calendar.getInstance().getTime().toString();
			document1.add(new Paragraph(currentTime));
			Image image = Image.getInstance(folderPath+"/ScreenShots/temp/screenShot.png");
			image.scaleToFit(500, 600);
			document1.add(image);
			Paragraph pageNumber = new Paragraph();
			pageNumber.setAlignment(Element.ALIGN_BOTTOM);
			pageNumber.add("Page Number " + pdfPageCount);
			document1.add(pageNumber);			
			document1.close();
			List<InputStream> inputFiles = new ArrayList<InputStream>();
			if (firstImage) {				
				inputFiles.add(new FileInputStream(folderPath+"/ScreenShots/temp/TitlePage.pdf"));
				inputFiles.add(new FileInputStream(folderPath+"/ScreenShots/temp/image2.pdf"));

			} else {
				inputFiles.add(new FileInputStream(folderPath+"/ScreenShots/temp/Copy.pdf"));
				inputFiles.add(new FileInputStream(folderPath+"/ScreenShots/temp/image2.pdf"));
			}
			// outputFile is the final PDF file that will hold all Screen shots.
			FileOutputStream outputFile = new FileOutputStream(folderPath+"/ScreenShots/" + fileName);
			doMerge(inputFiles, outputFile);
			// we need an additional copy to re-use as input file on next call
			// to doMerge
			copyFile(new File(folderPath+"/ScreenShots/" + fileName), new File(folderPath+"/ScreenShots/temp/Copy.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}// end try
	}// end takeScreenShotAndAddToPDF

	public static void titleForPDFScreenShots(String PdfScreenShotFileTitle, String folderPath, String fileName){
		Document document2 = new Document();		
		document2.setPageSize(PageSize.A4);
		try {
			createDirectories(folderPath+"/ScreenShots/temp/TitlePage.pdf");	
			FileOutputStream titlePage = new FileOutputStream(folderPath+"/ScreenShots/temp/TitlePage.pdf");	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// end try catch		
	}// writeTitleForPDFScreenShots

	public static void addTextToPDFScreenShots(String PdfScreenShotFileText, String folderPath, String fileName){
		Document document2 = new Document();		
		document2.setPageSize(PageSize.A4);
		try {
			PdfWriter.getInstance(document2, new FileOutputStream(folderPath+"/ScreenShots/temp/image2.pdf"));
			document2.open();
			Paragraph title = new Paragraph();
			//title.setAlignment(1);
			title.add(PdfScreenShotFileText);							
			document2.add(title);				
			document2.close();
			List<InputStream> inputFiles = new ArrayList<InputStream>();
			inputFiles.add(new FileInputStream(folderPath+"/ScreenShots/temp/Copy.pdf"));
			inputFiles.add(new FileInputStream(folderPath+"/ScreenShots/temp/image2.pdf"));		
			// outputFile is the final PDF file that will hold all Screen shots.
			FileOutputStream outputFile = new FileOutputStream(folderPath+"/ScreenShots/" + fileName);
			doMerge(inputFiles, outputFile);
			// we need an additional copy to re-use as input file on next call
			// to doMerge
			copyFile(new File(folderPath+"/ScreenShots/" + fileName), new File(folderPath+"/ScreenShots/temp/Copy.pdf"));
		} catch( IOException e){ 
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
	public static void takeScreenShot( String folderPath ) {
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
			File theDir = new File(folderPath+"/ScreenShots/temp");
			if (!theDir.exists()) {
				theDir.mkdirs();
			}
			ImageIO.write(image, "png", new File(folderPath+"/ScreenShots/temp/screenShot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// give feedback
		System.out.println("Saved screen shot (" + image.getWidth() + " x "
				+ image.getHeight()
				+ " pixels) to file" + folderPath +"/ScreenShots/temp/screenShot.png ");
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
	 * @filemane = full pathname and filename
	 * 
	 */
	public static boolean createDirectories (String filename){
		boolean dirCreated = false;

		File file = new File(filename);

		System.out.println("getParent " +file.getParent() );

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
	 * Takes the Refernces numbe and appends to the pdf file name and deletes the orginal file
	 * 
	 * 
	 */

	public static String addRefernceNumbertoPDFfile (String pdfPath, String pdfFilename, String refNum){

		int lastLocation = pdfFilename.lastIndexOf(".");
		String tempName = pdfFilename.substring(0, lastLocation);
		String newName = tempName +"RefNu_" +refNum +".pdf";
		//CommonUtils.copyFile(pdfPath +pdfFilename, pdfPath +newName);

		try {
			copyFile(new File(pdfPath +pdfFilename), new File(pdfPath  +newName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File(pdfPath +"/" +pdfFilename);
		//	boolean success = f.delete();

		return newName;
	}

} 