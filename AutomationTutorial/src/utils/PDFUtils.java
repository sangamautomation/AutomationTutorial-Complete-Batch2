package utils;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
/**
 * PDF Utils
 * @author Sangam
 */
public class PDFUtils {
	
	// Constants
	char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWZYZ"
			.toCharArray();
	StringBuilder sb = new StringBuilder();
	Random r1 = new Random();

	// this static variable is used to keep track of page numbers in
	// takescreenshotandaddtopdf method.
	static int pdfPageCount = 0;
	
	public static void main(String[] args){
		convertPDfToTxt("Z:\\Team Folders\\a\\Notices_Selenium\\");
	}

	public static void convertPDfToTxt(String path){

		File[] files;
		ArrayList<String> results = new ArrayList<String>();
		File[] arrfile = files = new File(path).listFiles();
		int n = arrfile.length;
		int n2 = 0;
		while (n2 < n) {
			File file = arrfile[n2];
			if (file.isFile()) {
				String pdfFile = file.getName();
				results.add(pdfFile);
			if (pdfFile.toLowerCase().contains(".pdf") && ( pdfFile.toLowerCase().contains("notice") || pdfFile.toLowerCase().contains("dco7") || pdfFile.toLowerCase().contains("dco19") ) ) {
					String TXTfile = pdfFile.replace(".pdf", ".txt");
					System.out.println("itexPDF jar:\t" + pdfFile);
					PDFUtils.convertPDFToText(path.concat(pdfFile), path.concat(TXTfile));
				}
			}
			++n2;
		}
		try {
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void selectPDFFiles() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", "pdf");
		chooser.setFileFilter(filter);
		chooser.setMultiSelectionEnabled(true);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == 0) {
			File[] Files = chooser.getSelectedFiles();
			System.out.println("Please wait...");
			int i = 0;
			while (i < Files.length) {
				PDFUtils.convertPDFToText(Files[i].toString(), "textfrompdf" + i + ".txt");
				++i;
			}
			System.out.println("Conversion complete");
		}
	}

	public static void convertPDFToText(String src, String desc) {
		try {
			FileWriter fw = new FileWriter(desc);
			BufferedWriter bw = new BufferedWriter(fw);
			PdfReader pr = new PdfReader(src);
			int pNum = pr.getNumberOfPages();
			int page = 1;
			while (page <= pNum) {
				String text = PdfTextExtractor.getTextFromPage(pr, page);
				bw.write(text);
				bw.newLine();
				++page;
			}
			bw.flush();
			bw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	
	

	/* * USED - Parameter String fileName: This is a PDF filename that will hold all of
	 * the images/screen shots. Parameter boolean firstImage: This is a flag
	 * indicating if we are adding the first image/screen shot. If firstImage is
	 * true the method adds a Title Page, otherwise the new image is added in
	 * the PDF file as represented in fileName variable. This method will always
	 * add the file under ScreenShots\screenShot.png with counter as suffix
	 * 
	 * */

	public static void screenshotToPdf(String folderPath, String fileNameOfImage, String fileName, boolean firstImage, int counter) {

		try {
			if (firstImage) {
				pdfPageCount = 1;
			} else {
				pdfPageCount++;
			} 

			// Take the screen shot
			screenshot(folderPath, counter);
			// Document1 will always store the current screen shot.
			Document document1 = new Document();
			document1.setPageSize(PageSize.A4);
			// add the screen shot to a PDf file.
			PdfWriter.getInstance(document1, new FileOutputStream(folderPath  + "/Testcase.pdf"));
			document1.open();
			String currentTime = Calendar.getInstance().getTime().toString();
			document1.add(new Paragraph(currentTime));
			Image image = Image.getInstance(folderPath    + fileNameOfImage);
			image.scaleToFit(500, 600);
			document1.add(image);
			Paragraph pageNumber = new Paragraph();
			// pageNumber.setAlignment(Element.ALIGN_BOTTOM);
			pageNumber.add("Page Number " + pdfPageCount);
			document1.add(pageNumber);
			document1.close();
			List<InputStream> inputFiles = new ArrayList<InputStream>();
			if (firstImage) {
				//            inputFiles.add(new FileInputStream(folderPath                + "/TitlePage.pdf"));
				inputFiles.add(new FileInputStream(folderPath                + "/Testcase.pdf"));
				inputFiles.add(new FileInputStream(folderPath                + "/Testcase.pdf"));
				inputFiles.add(new FileInputStream(folderPath                + "/Testcase.pdf"));

			} else {
				inputFiles.add(new FileInputStream(folderPath                + fileNameOfImage));
				inputFiles.add(new FileInputStream(folderPath                + fileNameOfImage));
			}
			// outputFile is the final PDF file that will hold all Screen shots.
			//                            FileOutputStream outputFile = new FileOutputStream(folderPath + fileName);
			//                            mergePdfs(inputFiles, outputFile);
			// we need an additional copy to re-use as input file on next call
			// to doMerge
			//            fileCopy(new File(folderPath  + fileName),
			//                                            new File(folderPath + "Copy.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void screenshotToPdf(String folderPath,	String fileName, boolean firstImage) {

		try {
			if (firstImage) {
				pdfPageCount = 1;
			} else {
				pdfPageCount++;
			} 

			// take the screen shot
			screenshot(folderPath);
			// Document1 will always store the current screen shot.
			Document document1 = new Document();
			document1.setPageSize(PageSize.A4);
			// add the screen shot to a PDf file.
			PdfWriter.getInstance(document1, new FileOutputStream(folderPath	+ "/image2.pdf"));
			document1.open();
			String currentTime = Calendar.getInstance().getTime().toString();
			document1.add(new Paragraph(currentTime));
			Image image = Image.getInstance(folderPath	+ "/ScreenShots/screenShot.png");
			image.scaleToFit(500, 600);
			document1.add(image);
			Paragraph pageNumber = new Paragraph();
			// pageNumber.setAlignment(Element.ALIGN_BOTTOM);
			pageNumber.add("Page Number " + pdfPageCount);
			document1.add(pageNumber);
			document1.close();
			List<InputStream> inputFiles = new ArrayList<InputStream>();
			if (firstImage) {
				inputFiles.add(new FileInputStream(folderPath	+ "/ScreenShots/TitlePage.pdf"));
				inputFiles.add(new FileInputStream(folderPath	+ "/ScreenShots/image2.pdf"));

			} else {
				inputFiles.add(new FileInputStream(folderPath	+ "/ScreenShots/Copy.pdf"));
				inputFiles.add(new FileInputStream(folderPath	+ "/ScreenShots/image2.pdf"));
			}
			// outputFile is the final PDF file that will hold all Screen shots.
			FileOutputStream outputFile = new FileOutputStream(folderPath		+ "/ScreenShots/" + fileName);
			doMerge(inputFiles, outputFile);
			// we need an additional copy to re-use as input file on next call
			copyFile(new File(folderPath + "/ScreenShots/" + fileName),
					new File(folderPath + "/ScreenShots/Copy.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	/* * USED - Adds text to PDF file
	 * 	using path
	 * */

	public static void textToPdf(String PdfScreenShotFileText,
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
			mergePdfs(inputFiles, outputFile);
			// we need an additional copy to re-use as input file on next call
			fileCopy(new File(folderPath + "/ScreenShots/" + fileName),
					new File(folderPath + "/ScreenShots/temp/Copy.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}


	/* * Takes the Refernces number and appends to the pdf file name and deletes
	 * the orginal file
	 * */

	public static String numberToPdf(String pdfPath,
			String pdfFilename, String refNum) {

		int lastLocation = pdfFilename.lastIndexOf(".");
		String tempName = pdfFilename.substring(0, lastLocation);
		String newName = tempName + "RefNu_" + refNum + ".pdf";
		// Utils.copyFile(pdfPath +pdfFilename, pdfPath +newName);

		try {
			fileCopy(new File(pdfPath + pdfFilename), new File(pdfPath
					+ newName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		File f = new File(pdfPath + "/" + pdfFilename);
		// boolean success = f.delete();

		return newName;
	}


	/* * Gives title to pdf file name 
	 * */

	public static void titleForPdf(String PdfScreenShotFileTitle,
			String folderPath, String fileName) {
		Document document2 = new Document();
		document2.setPageSize(PageSize.A4);
		try {
			folderCreate(folderPath + "/ScreenShots/TitlePage.pdf");
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
			e.printStackTrace();
		} catch (DocumentException e) {

			e.printStackTrace();
		}
	}


	/*	 * Merge multiple pdf into one pdf
	 * 
	 * @param list
	 *            of pdf input stream
	 * @param outputStream
	 *            output file output stream
	 * @throws DocumentException
	 * @throws IOException
	 * */

	public static void mergePdfs(List<InputStream> list, OutputStream outputStream)
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
				} 
			} 
			outputStream.flush();
			document.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// Copy sourceFile into the destFile.

	public static void fileCopy(File sourceFile, File destFile)
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
			} 
		} 
	} 


	/** Creates directories
	 * 
	 * @filemane = full pathname and filename
	 * */

	public static boolean folderCreate(String filename) {
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


	// ******************** Reporting with Hardcoded values (just for debugging) ************************


	/* * Parameter String fileName: This is a PDF filename that will hold all of
	 * the images/screen shots. Parameter boolean firstImage: This is a flag
	 * indicating if we are adding the first image/screen shot. If firstImage is
	 * true the method adds a Title Page, otherwise the new image is added in
	 * the PDF file as represented in fileName variable. This method will always
	 * add the file under ScreenShots\screenShot.png
	 * 
	 * */

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


	/*	 * Merge multiple pdf into one pdf
	 * 
	 * @param list
	 *            of pdf input stream
	 * @param outputStream
	 *            output file output stream
	 * @throws DocumentException
	 * @throws IOException
	 * */

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



	/* * This method will take a screen shot of current screen and store it in
	 * ...\ScreenShots\screenShot.png file.
	 * */

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



	// Copy sourceFile into the destFile.

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


	/** Creates directories
	 * 
	 * @filemane = full pathname and filename
	 * */

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

	/*	**
	 * validate the SSN value and adjust value if it is wrong. switches the
	 * first number in SSN to a 1 is first three numbers are 000, 666 or it
	 * starts with a 9. if middle number is 00 switches number to 01. .
	 * */
/*	public static String validateSSN(String value) {
		String returnValue = "";
		String temp1 = "";
		List<String> SSNNumber = Utils.parseDelimitedString(value, "-");
		int value1 = Integer.parseInt(SSNNumber.get(0));
		int value2 = Integer.parseInt(SSNNumber.get(1));
		int value3 = Integer.parseInt(SSNNumber.get(2));

		if (value1 == 0 || (value1 == 666) || (value1 >= 900)) {
			temp1 = Integer.toString(value1);
			value1 = Integer.parseInt("1" + temp1.substring(1));
		}

		if (value2 == 0) {
			temp1 = Integer.toString(value2);
			value2 = Integer.parseInt("1" + temp1.substring(1));
		}

		return returnValue = StringUtils
				.leftPad(String.valueOf(value1), 3, "0")
				+ "-"
				+ StringUtils.leftPad(String.valueOf(value2), 2, "0")
				+ "-"
				+ StringUtils.leftPad(String.valueOf(value3), 4, "0");

	}
*/

	/* * Takes the Refernces number and appends to the pdf file name and deletes
	 * the orginal file
	 * */


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

	

	
	/* * USED-This method will take a screen shot of current screen and store it in
	 * ...\ScreenShots\screenShot.png file with passed counter number as suffix.
	 * */
	public static void screenshot(String folderPath, int counter) {

	
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		Rectangle screenRect = new Rectangle(screenSize);
		// Create screen shot
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		String sCounter = "0000" + String.valueOf(counter);
		sCounter = sCounter.substring(  sCounter.length()-4, sCounter.length() );

		BufferedImage image = robot.createScreenCapture(screenRect);
		// Save captured image to PNG file
		try {
			// create the ScreenShots Directory if does not exists.
			File theDir = new File(folderPath);
			if (!theDir.exists()) {
				theDir.mkdirs();
			}

			ImageIO.write(image, "png", new File(folderPath + "/screenShot"+sCounter+".png"));


		} catch (IOException e) {
			e.printStackTrace();
		}
		// Give feedback
		System.out.println("Screen shot saved (" + image.getWidth() + " x "
				+ image.getHeight() + " pixels) to file: " + folderPath + "/screenShot"+sCounter+".png");
	}

	public static void screenshot(String folderPath) {
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
			File theDir = new File(folderPath);
			if (!theDir.exists()) {
				theDir.mkdirs();
			}

			ImageIO.write(image, "png", new File(folderPath
					+ "/screenShot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// give feedback
		System.out.println("Saved screen shot (" + image.getWidth() + " x "
				+ image.getHeight() + " pixels) to file" + folderPath
				+ "/screenShot.png ");
	}


}
