package utils;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadableInstant;
import org.joda.time.Seconds;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Date and Time related utility functions
 * @author Sangam
 *
 */

public class DateUtils {


	private static SimpleDateFormat dateFormatUS = new SimpleDateFormat("M/d/yyyy", Locale.US);

	private static SimpleDateFormat dateAndTimeFormatUS = new SimpleDateFormat("M/d/yyyy HH:mm", Locale.US);

	private static SimpleDateFormat batchDateFormatUS = new SimpleDateFormat("yyyyMMdd", Locale.US);

	private static SimpleDateFormat dateFormatLocaleMachineUS = new SimpleDateFormat("yyyyMMdd", Locale.US);

	private static SimpleDateFormat reassessedDateFormatUS = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

	private static SimpleDateFormat ganttChartFormatUS = new SimpleDateFormat("MMMMM d, yyyy", Locale.US);

	private static TimeZone utc = TimeZone.getTimeZone("UTC");
	private static TimeZone defaultZone = TimeZone.getDefault();
	private static Locale timelocale = Locale.US;
	private static Calendar calendar = Calendar.getInstance(utc, timelocale);
	private static boolean dateRollChange = false;


	// Returns Day of the Week for current date
	public  static String dayOfWeek() {
		System.out.println("dayOfWeek:");
		Date now = new Date();

		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
		System.out.println(simpleDateformat.format(now));

		simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
		System.out.println(simpleDateformat.format(now));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK)); // the day of the week in numerical format
		return simpleDateformat.format(now);
	}

	public static int calcAge() {
		LocalDate birthdate = new LocalDate(1970, 1, 20); // Birth date
		LocalDate now = new LocalDate(); // Today's date
		Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
		return period.getYears();
	}

	public static int ageCalculator(String dateOfBirth) {

		if (dateOfBirth == "" || dateOfBirth.equals("") || dateOfBirth == " "
				|| dateOfBirth.equals(" ") || dateOfBirth.contains("~")
				|| dateOfBirth.equalsIgnoreCase("NA")
				|| dateOfBirth.equals("-") || dateOfBirth.equals(null)) {
			System.out.println("ageCalculator "+  " of "+dateOfBirth + " = " + "0");
			return 0;
		} else {
			DateTimeFormatter formatter = DateTimeFormat.forPattern("M/D/yyyy")
					.withLocale(Locale.US);
			// DateTimeFormatter formatter =
			// DateTimeFormat.forPattern("MM/dd/yyyy").withLocale(Locale.US);
			LocalDate dob = formatter.parseLocalDate(dateOfBirth); // Birth date
			LocalDate now = new LocalDate(); // Today's date
			Period period = new Period(dob, now, PeriodType.yearMonthDay());
			System.out.println("ageCalculator "+  " of "+dateOfBirth + " = " + period.getYears());

			return period.getYears();
		}

	}

	// Age calculator with pattern paramterized "MM/dd/yyyy" or ""M/d/yyyy"..
	public static int ageCalculator(String pattern, String dateOfBirth) {

		if (dateOfBirth == "" || dateOfBirth.equals("") || dateOfBirth == " "
				|| dateOfBirth.equals(" ") || dateOfBirth.contains("~")
				|| dateOfBirth.equalsIgnoreCase("NA")
				|| dateOfBirth.equals("-") || dateOfBirth.equals(null)) {
			System.out.println("ageCalculator "+  " of "+dateOfBirth + " = " + "0");
			return 0;
		} else {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern)
					.withLocale(Locale.US);
			LocalDate dob = formatter.parseLocalDate(dateOfBirth); // Birth date
			LocalDate now = new LocalDate(); // Today's date
			Period period = new Period(dob, now, PeriodType.yearMonthDay());
			System.out.println("Age of " + dateOfBirth + " = "
					+ period.getYears());
			System.out.println("ageCalculator "+  " of "+dateOfBirth + " = " + period.getYears());
			return period.getYears();
		}

	}

	//Returns age in full with Years.Months.Days String with parameterized delimiter
	public static String ageCalculator_YearsMonthsDays(String pattern, String dateOfBirth, String delimiter) {

		if (dateOfBirth == "" || dateOfBirth.equals("") || dateOfBirth == " "
				|| dateOfBirth.equals(" ") || dateOfBirth.contains("~")
				|| dateOfBirth.equalsIgnoreCase("NA")
				|| dateOfBirth.equals("-") || dateOfBirth.equals(null)) {
			return "0";
		} else {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern)
					.withLocale(Locale.US);
			LocalDate dob = formatter.parseLocalDate(dateOfBirth); // Birth date
			LocalDate now = new LocalDate(); // Today's date
			Period period = new Period(dob, now, PeriodType.yearMonthDay());
			System.out.println("Age of " + dateOfBirth + " = "
					+ period.getYears());

			String age_Years = Integer.toString(period.getYears());
			String age_Months = Integer.toString(period.getMonths());
			String age_Days = Integer.toString(period.getDays());

			String age_Accurate = Integer.toString(period.getYears())+delimiter+Integer.toString(period.getMonths())+delimiter+Integer.toString(period.getDays());
			return age_Accurate;
		}

	}

	//Returns age in full with Years.Months.Days String with delimiter as dot
	public static String ageCalculator_YearsMonthsDays(String pattern, String dateOfBirth) {

		if (dateOfBirth == "" || dateOfBirth.equals("") || dateOfBirth == " "
				|| dateOfBirth.equals(" ") || dateOfBirth.contains("~")
				|| dateOfBirth.equalsIgnoreCase("NA")
				|| dateOfBirth.equals("-") || dateOfBirth.equals(null)) {
			System.out.println("ageCalculator_YearsMonthsDays "+  " of "+dateOfBirth + " in pattern "+pattern+ " = " + "0");
			return "0";
		} else {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern)
					.withLocale(Locale.US);
			LocalDate dob = formatter.parseLocalDate(dateOfBirth); // Birth date
			LocalDate now = new LocalDate(); // Today's date
			Period period = new Period(dob, now, PeriodType.yearMonthDay());
			System.out.println("Age of " + dateOfBirth + " = "
					+ period.getYears() + " Years"+ period.getYears());

			String age_Years = Integer.toString(period.getYears());
			String age_Months = Integer.toString(period.getMonths());
			String age_Days = Integer.toString(period.getDays());

			String age_Accurate = Integer.toString(period.getYears())+"."+Integer.toString(period.getMonths())+"."+Integer.toString(period.getDays());
			System.out.println("ageCalculator_YearsMonthsDays "+  " of "+dateOfBirth + " in pattern "+pattern+ " = " + age_Accurate);
			return age_Accurate;
		}

	}
	public static String dateToday() {
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

	public static String date_firstOfLastMonth() {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/d/YYYY");
		String str = fmt.print(firstOfLastMonth);

		System.out.println(str);
		return str;

	}

	public static String date_firstOfThisMonth(String desiredFormat) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
		String str = fmt.print(firstOfThisMonth);

		System.out.println(str);
		return str;

	}
	//Getting last day of this month
	public static String date_lastOfThisMonth() {
		//LocalDate monthEnd = new LocalDate().plusMonths(1).withDayOfMonth(1).minusDays(1);
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate lastofthisMonth = firstOfThisMonth.plusMonths(1).minusDays(1);
		System.out.println("fisrttime1"+firstOfThisMonth);
		System.out.println("fisrttime2"+lastofthisMonth);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY-MM-d");
		String str = fmt.print(lastofthisMonth);
		String str1 = fmt.print(firstOfThisMonth);
		System.out.println(str);
		System.out.println(str1);
		return str;

	}

	// Returns parts of date like date, month, year in a desired format (Useful if we need to extract month eg. November desiredFormat=MMMM/dd/yyyy, Nov desiredFormat=MMM/dd/yyyy, 11 desiredFormat=MM/dd/yyyy)
	public static String date_PartsOfFullDate(String noOfMonthsFromCurrentMonth, String desiredFormat, String partOfDate_Like_Month_Date_Year) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);
		LocalDate firstOfMonth = firstOfThisMonth.plusMonths(Integer.parseInt(noOfMonthsFromCurrentMonth));

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
		String str = fmt.print(firstOfMonth);

		String[] dateParts = str.split("/"); //using java split -works
		//	String[] dateParts = StringUtils.split(str, "/"); //using apache common jar - works
		//String[] dateParts = utils.StringUtils.splitStringAtDelimiter(str, "/"); //using my own utils - works

		String month = dateParts[0];
		String date = dateParts[1];
		String year = dateParts[2];

		switch (partOfDate_Like_Month_Date_Year) {
		case "Month":
			str=month;
			break;
		case "Date":
			str=date;
			break;
		case "Year":
			str=year;
			break;
		default:
			str=month;
			break;
		}

		System.out.println("date_PartsOfFullDate "+ noOfMonthsFromCurrentMonth + " in the format "+desiredFormat + " = " + str);
		return str;

	}

	// Returns whole string array with parts (can extract using index like [0],[1] after calling) of date like date, month, year in a desired format (Useful if we need to extract month )
	public static String[] date_PartsOfFullDate_Array(String noOfMonthsFromCurrentMonth, String desiredFormat) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);
		LocalDate firstOfMonth = firstOfThisMonth.plusMonths(Integer.parseInt(noOfMonthsFromCurrentMonth));

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
		String str = fmt.print(firstOfMonth);

		String[] dateParts = utils.StringUtils.splitStringAtDelimiter(str, "/"); 

		System.out.println("date_ArrayOfFullDate "+ noOfMonthsFromCurrentMonth + " in the format "+desiredFormat + " = " + "Call using index of array extract values like [0]");
		return dateParts;

	}

	public static String date_firstOfMonthBeforeSpecificMonthsFromDate(String Mydate_yyyy_mm_dd_WithHyphen_Format, String noOfMonthsFromCurrentMonth, String desiredFormat) {
		LocalDate today2 = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		System.out.println("1         "+ today2); // yyyy-mm-dd format
		LocalDate today = LocalDate.parse(Mydate_yyyy_mm_dd_WithHyphen_Format);
		System.out.println("2         "+today); // should be in yyyy-mm-dd format

		//	DateTimeFormatter format = DateTimeFormat.forPattern(desiredFormat);
		//	String 	dateString = format.print(date);

		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);
		LocalDate firstOfMonth = firstOfThisMonth.minusMonths(Integer.parseInt(noOfMonthsFromCurrentMonth));

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
		String str = fmt.print(firstOfMonth);
		System.out.println("date_firstOfMonthBeforeSpecificMonthsFromDate "+ noOfMonthsFromCurrentMonth + " in the format "+desiredFormat + " = " + str);
		return str;

	}
	public static String date_firstOfMonthBeforeSpecificMonths(String noOfMonthsFromCurrentMonth, String desiredFormat) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);
		LocalDate firstOfMonth = firstOfThisMonth.minusMonths(Integer.parseInt(noOfMonthsFromCurrentMonth));
		//LocalDate firstOfMonth = firstOfThisMonth.minusMonths(noOfMonthsFromCurrentMonth);

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
		String str = fmt.print(firstOfMonth);
		System.out.println("date_firstOfMonthBeforeSpecificMonths "+ noOfMonthsFromCurrentMonth + " in the format "+desiredFormat + " = " + str);
		return str;

	}

	public static String date_firstOfMonthAfterSpecificMonthsFromDate(String Mydate_yyyy_mm_dd_WithHyphen_Format, String noOfMonthsFromCurrentMonth, String desiredFormat) {
		String str = null;
		try {
			LocalDate today2 = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			//	System.out.println("1         "+ today2); // yyyy-mm-dd format
			LocalDate today = LocalDate.parse(Mydate_yyyy_mm_dd_WithHyphen_Format);
			//	System.out.println("2         "+today); // should be in yyyy-mm-dd format

			//	DateTimeFormatter format = DateTimeFormat.forPattern(desiredFormat);
			//	String 	dateString = format.print(date);

			LocalDate firstOfThisMonth = today.withDayOfMonth(1);
			LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
			LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);
			LocalDate firstOfMonth = firstOfThisMonth.plusMonths(Integer.parseInt(noOfMonthsFromCurrentMonth));

			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
			str = fmt.print(firstOfMonth);
			System.out.println("date_firstOfMonthAfterSpecificMonthsFromDate "+ noOfMonthsFromCurrentMonth + " in the format "+desiredFormat + " = " + str);
		} 

		catch (NumberFormatException e) {
			System.out.println("Please enter proper date.");	
			//e.printStackTrace();
		}

		catch (Exception e1) {
			System.out.println("Please enter proper date..");	
			//	e1.printStackTrace();
		}
		return str;

	}


	public static int date_adverseActionClosureNotice(String Mydate_yyyy_mm_dd_WithHyphen_Format, String desiredFormat) {
		String str = null;
		int noOfMonthsFromCurrentMonth = 0;
		try {
			Calendar c = Calendar.getInstance();
			int maxDaysOfThisMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			int todaysDay = LocalDate.now().getDayOfMonth();
			int adverseActionDay = maxDaysOfThisMonth - todaysDay;

			LocalDate today = LocalDate.parse(Mydate_yyyy_mm_dd_WithHyphen_Format);

			if(adverseActionDay >= 10){
				noOfMonthsFromCurrentMonth = 1;	
				System.out.println("Effective date will be end of this month");
			}else{
				noOfMonthsFromCurrentMonth = 2;
				System.out.println("Effective date will be end of next month");
			}

		} 

		catch (NumberFormatException e) {
			System.out.println("Please enter proper date.");	
			//e.printStackTrace();
		}

		catch (Exception e1) {
			System.out.println("Please enter proper date..");	
			//	e1.printStackTrace();
		}
		return noOfMonthsFromCurrentMonth;

	}


	/**
	 * This method returns effective date due to adverse action.
	 * If difference between last day of current month and todays date is greater than 10 then return next month first day or else return next to next month first day.
	 * @param Mydate_yyyy_mm_dd_WithHyphen_Format = Application date
	 * @param desiredFormat = "M/d/yyyy"
	 * @return eg : 4/1/2017
	 */
	public static String date_adverseAction(String Mydate_yyyy_mm_dd_WithHyphen_Format, String desiredFormat) {
		String str = null;
		try {
			Calendar c = Calendar.getInstance();
			int maxDaysOfThisMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			int todaysDay = LocalDate.now().getDayOfMonth();
			int adverseActionDay = maxDaysOfThisMonth - todaysDay;

			LocalDate today = LocalDate.parse(Mydate_yyyy_mm_dd_WithHyphen_Format);
			int noOfMonthsFromCurrentMonth = 0;
			if(adverseActionDay >= 10){
				noOfMonthsFromCurrentMonth = 1;	
			}else{
				noOfMonthsFromCurrentMonth = 2;
			}

			LocalDate firstOfThisMonth = today.withDayOfMonth(1);
			LocalDate firstOfMonth = firstOfThisMonth.plusMonths(noOfMonthsFromCurrentMonth);

			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
			str = fmt.print(firstOfMonth);
			System.out.println("date_firstOfMonthAfterSpecificMonthsFromDate "+ noOfMonthsFromCurrentMonth + " in the format "+desiredFormat + " = " + str);
		} 

		catch (NumberFormatException e) {
			System.out.println("Please enter proper date.");	
			//e.printStackTrace();
		}

		catch (Exception e1) {
			System.out.println("Please enter proper date..");	
			//	e1.printStackTrace();
		}
		return str;

	}

	public static String date_firstOfMonthAfterSpecificMonths(String noOfMonthsFromCurrentMonth, String desiredFormat) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);
		LocalDate firstOfMonth = firstOfThisMonth.plusMonths(Integer.parseInt(noOfMonthsFromCurrentMonth));

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
		String str = fmt.print(firstOfMonth);
		System.out.println("date_firstOfMonthAfterSpecificMonths "+ noOfMonthsFromCurrentMonth + " in the format "+desiredFormat + " = " + str);
		return str;

	}

	public static String date_lastOfMonthBeforeSpecificMonthsFromDate(String Mydate_yyyy_mm_dd_WithHyphen_Format, String noOfMonthsFromCurrentMonth, String desiredFormat) {
		LocalDate today2 = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		System.out.println("1         "+ today2); // yyyy-mm-dd format
		LocalDate today = LocalDate.parse(Mydate_yyyy_mm_dd_WithHyphen_Format);
		System.out.println("2         "+today); // should be in yyyy-mm-dd format

		//	DateTimeFormatter format = DateTimeFormat.forPattern(desiredFormat);
		//	String 	dateString = format.print(date);

		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);
		LocalDate lastOfMonth = firstOfThisMonth.minusMonths(Integer.parseInt(noOfMonthsFromCurrentMonth)).minusDays(1);

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
		String str = fmt.print(lastOfMonth);
		System.out.println("date_lastOfMonthBeforeSpecificMonthsFromDate "+ noOfMonthsFromCurrentMonth + " in the format "+desiredFormat + " = " + str);
		return str;

	}
	public static String date_lastOfMonthBeforeSpecificMonths(String noOfMonthsFromCurrentMonth, String desiredFormat) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);
		LocalDate lastOfMonth = firstOfThisMonth.minusMonths(Integer.parseInt(noOfMonthsFromCurrentMonth)).minusDays(1);

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
		String str = fmt.print(lastOfMonth);
		System.out.println("date_lastOfMonth "+ noOfMonthsFromCurrentMonth + " in the format "+desiredFormat + " = " + str);
		return str;

	}
	public static String date_lastOfMonthAfterSpecificMonthsFromDate(String Mydate_yyyy_mm_dd_WithHyphen_Format, String noOfMonthsFromCurrentMonth, String desiredFormat) {
		System.out.println("date_lastOfMonthAfterSpecificMonthsFromDate :");
		String str = null;
		try {
			LocalDate today2 = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			//	System.out.println("1         "+ today2); // yyyy-mm-dd format
			LocalDate today = LocalDate.parse(Mydate_yyyy_mm_dd_WithHyphen_Format);
			//	System.out.println("2         "+today); // should be in yyyy-mm-dd format

			//	DateTimeFormatter format = DateTimeFormat.forPattern(desiredFormat);
			//	String 	dateString = format.print(date);

			LocalDate firstOfThisMonth = today.withDayOfMonth(1);
			LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
			LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);
			LocalDate lastOfMonth = firstOfThisMonth.plusMonths(Integer.parseInt(noOfMonthsFromCurrentMonth)).minusDays(1);

			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
			str = fmt.print(lastOfMonth);
			System.out.println("date_lastOfMonthAfterSpecificMonthsFromDate "+ noOfMonthsFromCurrentMonth + " in the format "+desiredFormat + " = " + str);
		}
		catch (NumberFormatException e) {
			System.out.println("Please enter proper date.");	
			//e.printStackTrace();
		}

		catch (Exception e1) {
			System.out.println("Please enter proper date..");	
			//e1.printStackTrace();
		}

		return str;

	}

	public static String date_lastOfMonthAfterSpecificMonths(String noOfMonthsFromCurrentMonth, String desiredFormat) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);
		LocalDate lastOfMonth = firstOfThisMonth.plusMonths(Integer.parseInt(noOfMonthsFromCurrentMonth)).minusDays(1);

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
		String str = fmt.print(lastOfMonth);
		System.out.println("date_lastOfMonth "+ noOfMonthsFromCurrentMonth + " in the format "+desiredFormat + " = " + str);
		return str;

	}
	public static String date_endOfLastMonth() {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/d/YYYY");
		String str = fmt.print(endOfLastMonth);

		System.out.println(str);
		return str;

	}

	public static String date_PlusYear() {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
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
	public static String date_PlusYears(int n) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
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
	public static String date_PlusYears(int n, String pattern_like_MM_d_YYYY) {
		// if(Integer.toString(n).matches("^.*[^a-zA-Z0-9 ].*$"))	{
		if(Integer.toString(n).matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate firstOfThisMonth = today.withDayOfMonth(1);
			LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
			LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);
			LocalDate resultDate = today.plusYears(n);
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_PlusYears "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
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
	public static String date_MinusYears(int n, String pattern_like_M_d_YYYY) {
		if(Integer.toString(n).matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate firstOfThisMonth = today.withDayOfMonth(1);
			LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
			LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);
			LocalDate resultDate = today.minusYears(n);
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_MinusYears "+ n + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return null;
		}
	}


	/**
	 * Adds or removes(-) 'n' months to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_PlusMonths(int n, String pattern_like_MM_d_YYYY) {
		if(Integer.toString(n).matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate resultDate = today.plusMonths(n);
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_PlusMonths "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return "";
		}
	}

	public static String date_PlusMonths(String Date,int n, String pattern_like_MM_d_YYYY) {
		if(Integer.toString(n).matches("^[0-9]+$"))	{
			//LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));

			LocalDate today = 	LocalDate.parse(Date);
			System.out.println("today"+today);
			LocalDate resultDate = today.plusMonths(n);
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_PlusMonths "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
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
	public static String date_MinusMonths(int n, String pattern_like_MM_d_YYYY) {
		if(Integer.toString(n).matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));

			LocalDate resultDate = today.minusMonths(n);
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_MinusMonths "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return "";
		}
	}


	public static String date_MinusMonthsFromSpecificDate(String Mydate_yyyy_mm_dd_WithHyphen_Format, int n, String pattern_like_MM_d_YYYY) {
		try {
			if(Integer.toString(n).matches("^[0-9]+$"))	{
				//	LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate today = LocalDate.parse(Mydate_yyyy_mm_dd_WithHyphen_Format);			
				LocalDate resultDate = today.minusMonths(n);
				// DateTime dt = new DateTime();
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
				String str = fmt.print(resultDate);
				System.out.println("date_MinusMonthsFromSpecificDate "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
				return str;
			}
			else	{
				System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pattern_like_MM_d_YYYY;
	}

	//date_MinusDaysFromSpecificDate
	public static String date_MinusDaysFromSpecificDate(String Mydate_yyyy_mm_dd_WithHyphen_Format, int n, String pattern_like_MM_d_YYYY) {
		try {
			if(Integer.toString(n).matches("^[0-9]+$"))	{
				//	LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate today = LocalDate.parse(Mydate_yyyy_mm_dd_WithHyphen_Format);			
				LocalDate resultDate = today.minusDays(n);
				// DateTime dt = new DateTime();
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
				String str = fmt.print(resultDate);
				System.out.println("date_MinusDaysFromSpecificDate "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
				return str;
			}
			else	{
				System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pattern_like_MM_d_YYYY;
	}

	//date_PlusDaysFromSpecificDate
	public static String date_PlusDaysFromSpecificDate(String Mydate_yyyy_mm_dd_WithHyphen_Format, int n, String pattern_like_MM_d_YYYY) {
		try {
			if(Integer.toString(n).matches("^[0-9]+$"))	{
				//	LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate today = LocalDate.parse(Mydate_yyyy_mm_dd_WithHyphen_Format);			
				LocalDate resultDate = today.plusDays(n);
				// DateTime dt = new DateTime();
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
				String str = fmt.print(resultDate);
				System.out.println("date_PlusDaysFromSpecificDate "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
				return str;
			}
			else	{
				System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pattern_like_MM_d_YYYY;
	}

	/**
	 * Adds or removes(-) 'n' days to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_PlusDays(int n, String pattern_like_MM_d_YYYY) {
		if(Integer.toString(n).matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));

			LocalDate resultDate = today.plusDays(n);
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_PlusDays "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return "";
		}
	}

	/**
	 * Adds or removes(-) 'n' days to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_MinusDays(int n, String pattern_like_MM_d_YYYY) {
		if(Integer.toString(n).matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));

			LocalDate resultDate = today.minusDays(n);
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_MinusDays "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return "";
		}
	}


	/**
	 * Adds or removes(-) 'n' weeks to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_PlusWeeks(int n, String pattern_like_MM_d_YYYY) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDate resultDate = today.plusWeeks(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
		String str = fmt.print(resultDate);

		System.out.println("date_PlusWeeks "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
		return str;

	}


	/**
	 * Adds or removes(-) 'n' Weeks to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_MinusWeeks(int n, String pattern_like_MM_d_YYYY) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));

		LocalDate resultDate = today.minusWeeks(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
		String str = fmt.print(resultDate);

		System.out.println("date_MinusWeeks "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' Years, Months, Weeks, Days  to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_PlusYearsMonthsWeeksDays(int yr, int mon, int wk, int days, String pattern_like_MM_d_YYYY) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDate resultDate = today.plusWeeks(wk).plusYears(yr).plusMonths(mon).plusDays(days);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
		String str = fmt.print(resultDate);

		System.out.println("date_PlusYearsMonthsWeeksDays "+ yr +", "+ mon+", "+ wk+", "+ days+ " in the format "+pattern_like_MM_d_YYYY + " = " + str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' Years, Months, Weeks, Days  to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_MinusYearsMonthsWeeksDays(int yr, int mon, int wk, int days, String pattern_like_MM_d_YYYY) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));

		LocalDate resultDate = today.minusWeeks(wk).minusYears(yr).minusMonths(mon).minusDays(days);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
		String str = fmt.print(resultDate);

		System.out.println("date_MinusYearsMonthsWeeksDays "+ yr +", "+ mon+", "+ wk+", "+ days+ " in the format "+pattern_like_MM_d_YYYY + " = " + str);
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
	public static String datetime_PlusYearsMonthsWeeksDaysHoursMinutesSeconds(int yr, int mon, int wk, int days, int hr, int min, int sec, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
		LocalDateTime firstOfThisMonth = today.withDayOfMonth(1);
		LocalDateTime firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDateTime endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDateTime resultDate = today.plusWeeks(wk).plusYears(yr).plusMonths(mon).plusDays(days).plusHours(hr).plusMinutes(min).plusSeconds(sec);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY_HH_mm_ss);
		String str = fmt.print(resultDate);

		System.out.println("datetime_PlusYearsMonthsWeeksDaysHoursMinutesSeconds "+ yr +", "+ mon+", "+ wk+", "+ days+ ", "+hr +", "+min+", "+sec+ " in the format "+pattern_like_MM_d_YYYY_HH_mm_ss + " = " + str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' Years, Months, Weeks, Days  to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String datetime_MinusYearsMonthsWeeksDaysHoursMinutesSeconds(int yr, int mon, int wk, int days, int hr, int min, int sec, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
		LocalDateTime firstOfThisMonth = today.withDayOfMonth(1);
		LocalDateTime firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDateTime endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDateTime resultDate = today.minusWeeks(wk).minusYears(yr).minusMonths(mon).minusDays(days).minusHours(hr).minusMinutes(min).minusSeconds(sec);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY_HH_mm_ss);
		String str = fmt.print(resultDate);

		System.out.println("datetime_MinusYearsMonthsWeeksDaysHoursMinutesSeconds "+ yr +", "+ mon+", "+ wk+", "+ days+ ", "+hr +", "+min+", "+sec+ " in the format "+pattern_like_MM_d_YYYY_HH_mm_ss + " = " + str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' hours to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String datetime_PlusHours(int n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
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
	public static String datetime_MinusHours(int n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
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
	public static String datetime_PlusMinutes(int n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
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
	public static String datetime_MinusMinutes(int n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
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
	public static String datetime_PlusSeconds(int n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
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
	public static String datetime_MinusSeconds(int n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
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
	public static String time_PlusHours(int n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
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
	public static String time_MinusHours(int n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
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
	public static String time_PlusMinutes(int n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
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
	public static String time_MinusMinutes(int n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
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
	public static String time_PlusSeconds(int n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
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
	public static String time_MinusSeconds(int n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
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
	public static String time_PlusHoursMinutesSeconds(int hr, int min, int sec, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
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
	public static String time_MinusHoursMinutesSeconds(int hr, int min, int sec, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
		LocalTime resultTime = currentTime.minusHours(hr).minusMinutes(min).minusSeconds(sec);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_HH_mm_ss);
		String str = fmt.print(resultTime);

		System.out.println(str);
		return str;

	}



	public static String date_PlusYear(LocalDate date) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
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
	public static String date_Now() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
		String 	dateString = format.print(date);
		System.out.println("Date is "+ dateString);
		return dateString;
	}

	// Returns current date in a specified format
	public static String date_Now(String desiredFormat) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter format = DateTimeFormat.forPattern(desiredFormat);
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

	public static String date_Plus(String Mydate , String desiredFormat) {
		LocalDate date = LocalDate.parse(Mydate);
		DateTimeFormatter format = DateTimeFormat.forPattern(desiredFormat);
		String 	dateString = format.print(date);
		System.out.println("Date is "+ dateString);
		return dateString;

	}

	// Returns current date time
	public static String datetime_Now() {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyyHHmm");
		String 	dateString = format.print(datetime);
		System.out.println("DateTime is: "+ dateString);
		return dateString;
	}


	// Returns current date time
	public static String datetime_Now(String desiredFormat) {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormat.forPattern( desiredFormat);
		String 	dateString = format.print(datetime);
		System.out.println("DateTime is: "+ dateString);
		return dateString;
	}

	// Returns current date time
	public static String timestamp() {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormat.forPattern( "MM/d/yy HH:mm:ss.SSS");  //MM-dd-yyyy HH:mm:ss
		String 	dateString = format.print(datetime);
		return "["+dateString+"]"; //[10/9/16 18:36:26:999 CDT] 00000021 SystemOut 
	}
	// Returns current date time in desired format
	public static String timestamp(String desiredFormat) {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormat.forPattern( desiredFormat);
		String 	dateString = format.print(datetime);
		return dateString;
	}


	//USE: Converts or parses a date / datetime in my format to any desired format (Works)
	public static String datetime_Parse(String myDateTime, String myDateTimeFormat, String desiredDateTimeFormat) {
		String dateInDesiredFormat = null;

		try {
			//			DateTime dt = DateTime.parse("Dec-16-2015 00:00", DateTimeFormat.forPattern("MMM-dd-yyyy HH:mm"));
			DateTime dt = DateTime.parse(myDateTime, DateTimeFormat.forPattern(myDateTimeFormat));
			//	dt.toString("MMM-dd-yyyy hh:mm a");
			dateInDesiredFormat = dt.toString(desiredDateTimeFormat);
			System.out.println("datetime_Parse: The date "+myDateTime +" in the format "+ myDateTimeFormat + " converted to the desired format "+desiredDateTimeFormat+" =>> " + dt.toString(dateInDesiredFormat));
		} catch (java.lang.IllegalArgumentException e) {
			System.out.println("Please pass proper date");
			//throw e; // if throw then it will stop execution; don't throw so it will continue..
		}
		catch (java.lang.NullPointerException e1) {
			System.out.println("Please pass proper date");
			//throw e; // if throw then it will stop execution; don't throw so it will continue..
		}
		catch ( Exception e1) {
			System.out.println("Please pass proper date");
			//throw e; // if throw then it will stop execution; don't throw so it will continue..
		}
		return dateInDesiredFormat;
	}

	//date_Parse (Does not work - Use datetime_Parse)
	public static String date_Parse(String myDateTime, String myDateTimeFormat, String desiredDateTimeFormat) {
		String dateInDesiredFormat = null;
		try {
			String datex = datetime_Parse(myDateTime, myDateTimeFormat,"yyyy-MM-dd");

			//	DateTime dt = DateTime.parse("Dec-16-2015 00:00", DateTimeFormat.forPattern("MMM-dd-yyyy HH:mm"));
			DateTime dt = DateTime.parse(datex, DateTimeFormat.forPattern("yyyy-MM-dd")); // first convert to standard format

			datetime_Parse( myDateTime,  myDateTimeFormat,  desiredDateTimeFormat); //duplicate call
			//	dt.toString("MMM-dd-yyyy hh:mm a");
			dateInDesiredFormat = dt.toString(desiredDateTimeFormat);
			System.out.println("date_Parse: The date "+myDateTime +" in the format "+ myDateTimeFormat + " converted to the desired format "+desiredDateTimeFormat+" =>> " + dt.toString(dateInDesiredFormat));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateInDesiredFormat;

	}

	public static String marshal(LocalDate date) throws Exception {
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
	public static String date_Parse(String date, String desiredFormat) {
		LocalDate dt = LocalDate.parse(date);
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
		String str = fmt.print(dt);
		System.out.println(str);
		return str;

	}

	public static String date_Parsing(String date, String desiredFormat) {
		//LocalDate dt = LocalDate.parse(date);
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);

		//	DateTime dt = fmt.parseDateTime(date);
		LocalDate dt = fmt.parseLocalDate(date);

		String str = fmt.print(dt);
		System.out.println(str);
		return str;

	}



	public static String date_Parse(LocalDate date, String desiredFormat) {
		LocalDate nextYear = date.plusYears(0);
		DateTimeFormatter fmt = DateTimeFormat.forPattern(desiredFormat);
		String str = fmt.print(nextYear);
		System.out.println(str);
		return str;

	}


	public static String date_PlusYear(LocalDate date, int n) {
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
		LocalDate firstOfThisMonth = today.withDayOfMonth(1);
		LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDate nextYear = date.plusYears(n);
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/dd/YYYY");
		String str = fmt.print(nextYear);

		System.out.println(str);
		return str;

	}


	public static String date_PlusYear(String date, int n) {
		LocalDate dt = LocalDate.parse(date);
		LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));

		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/dd/YYYY");
		String str = fmt.print(dt);

		System.out.println(str);
		return str;

	}


	public  String date_AnyYear(String currentDate, String dateFormat)
			throws ParseException {
		Date dt = date_convertFromString(currentDate, dateFormat);
		calendar.setTime(dt);
		calendar.add(Calendar.YEAR, 1);
		Date nextYear = calendar.getTime();
		System.out.format("next year:  %s\n", nextYear);
		return nextYear.toString();
	}





	public static Date date_convertFromString(String stringDate, String dateFormat)
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

	public static String dateParse(String date) throws ParseException {
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
	public static String dateParse(String date, String userDateFormatArg, String dateFormatNeededArg ) throws ParseException {
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


	// Date Plus Minus functions


	/**
	 * Adds or removes(-) 'n' years to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_PlusYears(String n, String pattern_like_MM_d_YYYY) {
		if(n.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate firstOfThisMonth = today.withDayOfMonth(1);
			LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
			LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

			LocalDate resultDate = today.plusYears(Integer.parseInt(n));
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_PlusYears "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			return str;
		}
		else	{
			//	System.out.println(n+"  is not a number. Do not enter an alphabet, special char or null"); 
			return "";
		}
	}


	/**
	 * Adds or removes(-) 'n' years to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */

	public static String date_MinusYears(String n, String pattern_like_M_d_YYYY) {
		if(n.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate resultDate = today.minusYears(Integer.parseInt(n));
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_MinusYears "+ n + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			return str;
		}
		else if (n.contains("0."))
		{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate resultDate = today.minusMonths(Integer.parseInt(n.replaceAll("0.", "")));
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_MinusMonths "+ n + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			return str;
		}
		else	{
			//	System.out.println(n+"  is not a number. Do not enter an alphabet, special char or null"); 
			return "";
		}
	}


	public static String ageYears(String yearsDOTmonths) {
		String[] yrmon = yearsDOTmonths.split("\\s*\\.\\s*");
		String years = yrmon[0];
		String months =  yrmon[1];
		return years;
	}


	public static String ageYearsMonths(String yearsDOTmonths) {
		if(yearsDOTmonths.matches("^[0-9]+$"))	{
			return yearsDOTmonths;
		}

		/*else if (yearsDOTmonths.contains("0."))
		{
  			String months = yearsDOTmonths.replaceAll("0.", "");
 			return months;
		}*/

		else if(yearsDOTmonths.contains("."))	{
			String[] yrmon = yearsDOTmonths.split("\\s*\\.\\s*");
			String years = yrmon[0];
			String months =  yrmon[1];
			System.out.println("ageYearsMonths "+  " of "+yearsDOTmonths + " = " + years);
			return years;
		}
		else	{
			System.out.println("ageYearsMonths :"+yearsDOTmonths+"  is NOT a number. Do not enter an alphabet, special char or null"); 
			return "";
		}

	}
	/**
	 * Adds or removes(-) 'n' years & months & days to/from today and returns that date in a specified format
	 * 	// (Can use any delimiter ,./* etc) - customize it or overload it for delimiter if needed, This uses . as demiliter
	 * @param n
	 * @return
	 */

	public static String date_MinusYearsMonthsDays_Multi(String yearsDOTmonthsDOTdays, String pattern_like_M_d_YYYY) {

		String str = "";
		try {
			if(yearsDOTmonthsDOTdays.matches("^[0-9]+$"))	{
				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusYears(Integer.parseInt(yearsDOTmonthsDOTdays));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}


			else if (yearsDOTmonthsDOTdays.contains("0.0."))
			{
				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusDays(Integer.parseInt(yearsDOTmonthsDOTdays.replaceAll("0.0.", "")));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			else if (yearsDOTmonthsDOTdays.contains("0.0."))
			{
				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusDays(Integer.parseInt(yearsDOTmonthsDOTdays.replaceAll("0.0.", "")));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}


			else if (yearsDOTmonthsDOTdays.contains("0."))
			{
				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusMonths(Integer.parseInt(yearsDOTmonthsDOTdays.replaceAll("0.", "")));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}
			// int charCount = s1.length() - s1.replaceAll("\\.", "").length();

			else if(yearsDOTmonthsDOTdays.length()-yearsDOTmonthsDOTdays.replaceAll("\\.", "").length() ==2)	{
				List<String> myList = Arrays.asList(yearsDOTmonthsDOTdays.split("\\s*\\.\\s*"));
				String years = myList.get(0).toString();
				String months = myList.get(1).toString();
				String days = myList.get(2).toString();

				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusYears(Integer.parseInt(years)).minusMonths(Integer.parseInt(months)).minusDays(Integer.parseInt(days));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			else if(yearsDOTmonthsDOTdays.contains("."))	{
				List<String> myList = Arrays.asList(yearsDOTmonthsDOTdays.split("\\s*\\.\\s*"));
				String years = myList.get(0).toString();
				String months = myList.get(1).toString();

				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusYears(Integer.parseInt(years)).minusMonths(Integer.parseInt(months));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			else	{
				System.out.println(yearsDOTmonthsDOTdays+"  is not a number. Do not enter an alphabet, special char or null"); 
				str = "";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String date_MinusYearsMonthsDays(String yearsDOTmonthsDOTdays, String pattern_like_M_d_YYYY) {

		String str = "";
		try {
			//Years
			if(yearsDOTmonthsDOTdays.matches("^[0-9]+$"))	{
				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusYears(Integer.parseInt(yearsDOTmonthsDOTdays));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays1 "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			//Years.Months
			else if(yearsDOTmonthsDOTdays.matches("^[0-9]+.[0-9]+$"))	{
				List<String> myList = Arrays.asList(yearsDOTmonthsDOTdays.split("\\s*\\.\\s*"));
				String years = myList.get(0).toString();
				String months = myList.get(1).toString();

				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusYears(Integer.parseInt(years)).minusMonths(Integer.parseInt(months));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays2 "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			//Years.Months.Days
			else if(yearsDOTmonthsDOTdays.matches("^[0-9]+.\\d+.[0-9]+$"))	{
				List<String> myList = Arrays.asList(yearsDOTmonthsDOTdays.split("\\s*\\.\\s*"));//split at .
				String years = myList.get(0).toString();
				String months = myList.get(1).toString();
				String days = myList.get(2).toString();

				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusYears(Integer.parseInt(years)).minusMonths(Integer.parseInt(months)).minusDays(Integer.parseInt(days));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays3 "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}
			//0Years.Months
			else if(yearsDOTmonthsDOTdays.length()-yearsDOTmonthsDOTdays.replaceAll("\\.", "").length() ==2)	{
				List<String> myList = Arrays.asList(yearsDOTmonthsDOTdays.split("\\s*\\.\\s*"));
				String years = myList.get(0).toString();
				String months = myList.get(1).toString();
				String days = myList.get(2).toString();

				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusYears(Integer.parseInt(years)).minusMonths(Integer.parseInt(months)).minusDays(Integer.parseInt(days));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays4 "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			//Otherwise
			else	{
				System.out.println(yearsDOTmonthsDOTdays+"  is not a number. Do not enter an alphabet, special char or null"); 
				str = "";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String date_minusYearsMonthsDays(String yearsDOTmonthsDOTdays, String delimiter, String pattern_like_M_d_YYYY) {

		String str = "";
		try {

			if(yearsDOTmonthsDOTdays.matches("^[0-9]+$"))	{
				System.out.println("Date entered in the format: x");
				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusYears(Integer.parseInt(yearsDOTmonthsDOTdays));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			else if(yearsDOTmonthsDOTdays.matches("^[0-9]+"+delimiter+"[0-9]+$"))	{
				System.out.println("Date entered in the format: x,y");
				List<String> myList = Arrays.asList(yearsDOTmonthsDOTdays.split("\\s*\\"+delimiter+"\\s*"));
				String years = myList.get(0).toString();
				String months = myList.get(1).toString();

				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusYears(Integer.parseInt(years)).minusMonths(Integer.parseInt(months));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			else if(yearsDOTmonthsDOTdays.matches("^[0-9]+"+delimiter+"[0-9]+"+delimiter+"[0-9]+$"))	{
				System.out.println("Date entered in the format: x,y,z");
				List<String> myList = Arrays.asList(yearsDOTmonthsDOTdays.split("\\s*\\"+delimiter+"\\s*"));
				String years = myList.get(0).toString();
				String months = myList.get(1).toString();
				String days = myList.get(2).toString();

				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusYears(Integer.parseInt(years)).minusMonths(Integer.parseInt(months)).minusDays(Integer.parseInt(days));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			else	{
				System.out.println(yearsDOTmonthsDOTdays+"  is not a number. Do not enter an alphabet, special char or null"); 
				str = "";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return str;
	}


	// date_plusYearsMonthsDays [all formats: (x), (x,y), (x,y,z) ]

	public static String date_plusYearsMonthsDays(String yearsDOTmonthsDOTdays, String delimiter, String pattern_like_M_d_YYYY) {
		String str = "";
		try {

			if(yearsDOTmonthsDOTdays.matches("^[0-9]+$"))	{
				System.out.println("Date entered in the format: x");
				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.plusYears(Integer.parseInt(yearsDOTmonthsDOTdays));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_plusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			else if(yearsDOTmonthsDOTdays.matches("^[0-9]+"+delimiter+"[0-9]+$"))	{
				System.out.println("Date entered in the format: x,y");
				List<String> myList = Arrays.asList(yearsDOTmonthsDOTdays.split("\\s*\\"+delimiter+"\\s*"));
				String years = myList.get(0).toString();
				String months = myList.get(1).toString();

				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.plusYears(Integer.parseInt(years)).plusMonths(Integer.parseInt(months));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_plusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			else if(yearsDOTmonthsDOTdays.matches("^[0-9]+"+delimiter+"[0-9]+"+delimiter+"[0-9]+$"))	{
				System.out.println("Date entered in the format: x,y,z");
				List<String> myList = Arrays.asList(yearsDOTmonthsDOTdays.split("\\s*\\"+delimiter+"\\s*"));
				String years = myList.get(0).toString();
				String months = myList.get(1).toString();
				String days = myList.get(2).toString();

				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.plusYears(Integer.parseInt(years)).plusMonths(Integer.parseInt(months)).plusDays(Integer.parseInt(days));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_plusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			else	{
				System.out.println(yearsDOTmonthsDOTdays +"  is not a number. Do not enter an alphabet, special char or null"); 
				str = "";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return str;
	}


	// date_PlusYearsMonthsDays (Enter x.y.z to calculate Years+.Months+.Days+ {where .y.z are optional} and return date as string)
	// This uses . as demiliter
	public static String date_PlusYearsMonthsDays(String yearsDOTmonthsDOTdays, String pattern_like_M_d_YYYY) {

		String str = "";
		try {
			if(yearsDOTmonthsDOTdays.matches("^[0-9]+$"))	{
				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.plusYears(Integer.parseInt(yearsDOTmonthsDOTdays));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_PlusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			else if (yearsDOTmonthsDOTdays.contains("0."))
			{
				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.plusMonths(Integer.parseInt(yearsDOTmonthsDOTdays.replaceAll("0.", "")));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_PlusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}
			// int charCount = s1.length() - s1.replaceAll("\\.", "").length();

			else if(yearsDOTmonthsDOTdays.length()-yearsDOTmonthsDOTdays.replaceAll("\\.", "").length() ==2)	{
				List<String> myList = Arrays.asList(yearsDOTmonthsDOTdays.split("\\s*\\.\\s*"));
				String years = myList.get(0).toString();
				String months = myList.get(1).toString();
				String days = myList.get(2).toString();

				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.plusYears(Integer.parseInt(years)).plusMonths(Integer.parseInt(months)).plusDays(Integer.parseInt(days));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_PlusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			else if(yearsDOTmonthsDOTdays.contains("."))	{
				List<String> myList = Arrays.asList(yearsDOTmonthsDOTdays.split("\\s*\\.\\s*"));
				String years = myList.get(0).toString();
				String months = myList.get(1).toString();

				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.plusYears(Integer.parseInt(years)).plusMonths(Integer.parseInt(months));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_PlusYearsMonthsDays "+ yearsDOTmonthsDOTdays + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			}

			else	{
				System.out.println(yearsDOTmonthsDOTdays+"  is not a number. Do not enter an alphabet, special char or null"); 
				str = "";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return str;
	}


	/**
	 * Adds or removes(-) 'n' years & months to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */

	public static String date_MinusYearsMonths(String yearsDOTmonths, String pattern_like_M_d_YYYY) {

		if(yearsDOTmonths.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate resultDate = today.minusYears(Integer.parseInt(yearsDOTmonths));
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_MinusYears "+ yearsDOTmonths + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			return str;
		}

		else if (yearsDOTmonths.contains("0."))
		{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate resultDate = today.minusMonths(Integer.parseInt(yearsDOTmonths.replaceAll("0.", "")));
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_MinusMonths "+ yearsDOTmonths + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			return str;
		}

		else if(yearsDOTmonths.contains("."))	{
			List<String> myList = Arrays.asList(yearsDOTmonths.split("\\s*\\.\\s*"));
			String years = myList.get(0).toString();
			String months = myList.get(1).toString();

			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate resultDate = today.minusYears(Integer.parseInt(years)).minusMonths(Integer.parseInt(months));
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_MinusYearsMonths "+ yearsDOTmonths + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			return str;
		}


		else	{
			System.out.println(yearsDOTmonths+"  is not a number. Do not enter an alphabet, special char or null"); 
			return "";
		}
	}

	public static String date_MinusYearsOnly(String n, String pattern_like_M_d_YYYY) {
		if(n.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate resultDate = today.minusYears(Integer.parseInt(n));
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_MinusYears "+ n + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return "";
		}
	}

	public static String date_MinusYearsFraction(String n, String pattern_like_M_d_YYYY) {
		if(n.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate resultDate = today.minusYears(Integer.parseInt(n));
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_MinusYears "+ n + " in the format "+pattern_like_M_d_YYYY + " = " + str);
			return str;
		}
		else if (n.contains("0."))
		{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate resultDate = today.minusMonths(Integer.parseInt(n.replaceAll("0.", "")));
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_M_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_MinusMonthss "+ n + " in the format "+pattern_like_M_d_YYYY + " = " + str);
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
	public static String date_PlusMonths(String n, String pattern_like_MM_d_YYYY) {
		if(n.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate firstOfThisMonth = today.withDayOfMonth(1);
			LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
			LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);
			LocalDate resultDate = today.plusMonths(Integer.parseInt(n));
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_PlusMonths "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			return str;
		}
		else	{
			System.out.println(n+"  is not a number. Do not enter an alphabet, special char or null"); 
			return "";
		}
	}


	/**
	 * Adds or removes(-) 'n' months to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_MinusMonths(String n, String pattern_like_MM_d_YYYY) {
		if(n.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate resultDate = today.minusMonths(Integer.parseInt(n));
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_MinusMonths "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return "";
		}
	}

	/**
	 * Adds or removes(-) months and days to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_MinusMonthsDays(String monthsDOTdays, String pattern_like_MM_d_YYYY) {

		String str = "";
		try {
			if(monthsDOTdays.matches("^[0-9]+$"))	{
				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusMonths(Integer.parseInt(monthsDOTdays));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusMonths "+ monthsDOTdays + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			}

			else if (monthsDOTdays.contains("0."))
			{
				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusDays(Integer.parseInt(monthsDOTdays.replaceAll("0.", "")));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays "+ monthsDOTdays + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			}
			// int charCount = s1.length() - s1.replaceAll("\\.", "").length();

			/*	
		 // For calculating years, months, days for y.m.d format - not required for preg end date
		  else if(monthsDOTdays.length()-monthsDOTdays.replaceAll("\\.", "").length() ==2)	{
				List<String> myList = Arrays.asList(monthsDOTdays.split("\\s*\\.\\s*"));
				String years = myList.get(0).toString();
				String months = myList.get(1).toString();
				String days = myList.get(2).toString();

				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusYears(Integer.parseInt(years)).minusMonths(Integer.parseInt(months)).minusDays(Integer.parseInt(days));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusYearsMonthsDays "+ monthsDOTdays + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
				}
			 */
			else if(monthsDOTdays.contains("."))	{
				List<String> myList = Arrays.asList(monthsDOTdays.split("\\s*\\.\\s*"));
				String months = myList.get(0).toString();
				String days = myList.get(1).toString();

				LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
				LocalDate resultDate = today.minusMonths(Integer.parseInt(months)).minusDays(Integer.parseInt(days));
				DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
				str = fmt.print(resultDate);
				System.out.println("date_MinusMonthsDays "+ monthsDOTdays + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			}

			else	{
				System.out.println(monthsDOTdays+"  is not a number. Do not enter an alphabet, special char or null"); 
				str = "";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return str;

	}

	/**
	 * Adds or removes(-) 'n' days to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_PlusDays(String n, String pattern_like_MM_d_YYYY) {
		if(n.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));

			LocalDate resultDate = today.plusDays(Integer.parseInt(n));
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_PlusDays "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			return str;
		}
		else	{
			//	System.out.println(n+"  is not a number. Do not enter an alphabet, special char or null"); 
			return "";
		}
	}



	/**
	 * Adds or removes(-) 'n' days to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_MinusDays(String n, String pattern_like_MM_d_YYYY) {
		if(n.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));

			LocalDate resultDate = today.minusDays(Integer.parseInt(n));
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);

			System.out.println("date_MinusDays "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return "";
		}
	}


	/**
	 * Adds or removes(-) 'n' weeks to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_PlusWeeks(String n, String pattern_like_MM_d_YYYY) {
		if(n.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate firstOfThisMonth = today.withDayOfMonth(1);
			LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
			LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

			LocalDate resultDate = today.plusWeeks(Integer.parseInt(n));
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_PlusWeeks "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return "";
		}
	}


	/**
	 * Adds or removes(-) 'n' Weeks to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_MinusWeeks(String n, String pattern_like_MM_d_YYYY) {
		if(n.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate firstOfThisMonth = today.withDayOfMonth(1);
			LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
			LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

			LocalDate resultDate = today.minusWeeks(Integer.parseInt(n));
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_MinusWeeks "+ n + " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return "";
		}
	}

	/**
	 * Adds or removes(-) 'n' Years, Months, Weeks, Days  to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_PlusYearsMonthsWeeksDays(String yr, String mon, String wk, String days, String pattern_like_MM_d_YYYY) {
		if(yr.matches("^[0-9]+$") && mon.matches("^[0-9]+$") && wk.matches("^[0-9]+$") && days.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate resultDate = today.plusWeeks(Integer.parseInt(wk)).plusYears(Integer.parseInt(yr)).plusMonths(Integer.parseInt(mon)).plusDays(Integer.parseInt(days));
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);
			System.out.println("date_PlusYearsMonthsWeeksDays "+ yr +", "+ mon+", "+ wk+", "+ days+ " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return "";
		}
	}

	/**
	 * Adds or removes(-) 'n' Years, Months, Weeks, Days  to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_MinusYearsMonthsWeeksDays(String yr, String mon, String wk, String days, String pattern_like_MM_d_YYYY) {
		if(yr.matches("^[0-9]+$") && mon.matches("^[0-9]+$") && wk.matches("^[0-9]+$") && days.matches("^[0-9]+$"))	{
			LocalDate today = LocalDate.now(DateTimeZone.forID("America/Chicago"));
			LocalDate firstOfThisMonth = today.withDayOfMonth(1);
			LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
			LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

			LocalDate resultDate = today.minusWeeks(Integer.parseInt(wk)).minusYears(Integer.parseInt(yr)).minusMonths(Integer.parseInt(mon)).minusDays(Integer.parseInt(days));
			// DateTime dt = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY);
			String str = fmt.print(resultDate);

			System.out.println("date_MinusYearsMonthsWeeksDays "+ yr +", "+ mon+", "+ wk+", "+ days+ " in the format "+pattern_like_MM_d_YYYY + " = " + str);
			return str;
		}
		else	{
			System.out.println("The values is not a number. It should not be a alphabet, special char or null"); 
			return "";
		}
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
	public static String date_PlusYearsMonthsWeeksDaysHoursMinutesSeconds(String yr, String mon, String wk, String days, String hr, String min, String sec, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
		LocalDateTime firstOfThisMonth = today.withDayOfMonth(1);
		LocalDateTime firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDateTime endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDateTime resultDate = today.plusWeeks(Integer.parseInt(wk)).plusYears(Integer.parseInt(yr)).plusMonths(Integer.parseInt(mon)).plusDays(Integer.parseInt(days)).plusHours(Integer.parseInt(hr)).plusMinutes(Integer.parseInt(min)).plusSeconds(Integer.parseInt(sec));
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY_HH_mm_ss);
		String str = fmt.print(resultDate);

		System.out.println("datetime_PlusYearsMonthsWeeksDaysHoursMinutesSeconds "+ yr +", "+ mon+", "+ wk+", "+ days+ ", "+hr +", "+min+", "+sec+ " in the format "+pattern_like_MM_d_YYYY_HH_mm_ss + " = " + str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' Years, Months, Weeks, Days  to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String datetime_MinusYearsMonthsWeeksDaysHoursMinutesSeconds(String yr, String mon, String wk, String days, String hr, String min, String sec, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
		LocalDateTime firstOfThisMonth = today.withDayOfMonth(1);
		LocalDateTime firstOfLastMonth = firstOfThisMonth.minusMonths(1);
		LocalDateTime endOfLastMonth = firstOfThisMonth.minusDays(1);

		LocalDateTime resultDate = today.minusWeeks(Integer.parseInt(wk)).minusYears(Integer.parseInt(yr)).minusMonths(Integer.parseInt(mon)).minusDays(Integer.parseInt(days)).minusHours(Integer.parseInt(hr)).minusMinutes(Integer.parseInt(min)).minusSeconds(Integer.parseInt(sec));
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_MM_d_YYYY_HH_mm_ss);
		String str = fmt.print(resultDate);

		System.out.println("datetime_MinusYearsMonthsWeeksDaysHoursMinutesSeconds "+ yr +", "+ mon+", "+ wk+", "+ days+ ", "+hr +", "+min+", "+sec+ " in the format "+pattern_like_MM_d_YYYY_HH_mm_ss + " = " + str);
		return str;

	}

	/**
	 * Adds or removes(-) 'n' hours to/from today and returns that date in a specified format
	 * @param n
	 * @return
	 */
	public static String date_PlusHours(String n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
		LocalDateTime resultDateTime = today.plusHours(Integer.parseInt(n));
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
	public static String date_MinusHours(String n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
		LocalDateTime resultDateTime = today.minusHours(Integer.parseInt(n));
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
	public static String date_PlusMinutes(String n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
		LocalDateTime resultDateTime = today.plusMinutes(Integer.parseInt(n));
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
	public static String date_MinusMinutes(String n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
		LocalDateTime resultDateTime = today.minusMinutes(Integer.parseInt(n));
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
	public static String date_PlusSeconds(String n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
		LocalDateTime resultDateTime = today.plusSeconds(Integer.parseInt(n));
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
	public static String date_MinusSeconds(String n, String pattern_like_MM_d_YYYY_HH_mm_ss) {
		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Chicago"));
		LocalDateTime resultDateTime = today.minusSeconds(Integer.parseInt(n));
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
	public static String time_PlusHours(String n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
		LocalTime resultTime = currentTime.plusHours(Integer.parseInt(n));
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
	public static String time_MinusHours(String n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
		LocalTime resultTime = currentTime.minusHours(Integer.parseInt(n));
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
	public static String time_PlusMinutes(String n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
		LocalTime resultTime = currentTime.plusMinutes(Integer.parseInt(n));
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
	public static String time_MinusMinutes(String n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
		LocalTime resultTime = currentTime.minusMinutes(Integer.parseInt(n));
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
	public static String time_PlusSeconds(String n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
		LocalTime resultTime = currentTime.plusSeconds(Integer.parseInt(n));
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
	public static String time_MinusSeconds(String n, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
		LocalTime resultTime = currentTime.minusSeconds(Integer.parseInt(n));
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
	public static String time_PlusHoursMinutesSeconds(String hr, String min, String sec, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
		LocalTime resultTime = currentTime.plusHours(Integer.parseInt(hr)).plusMinutes(Integer.parseInt(min)).plusSeconds(Integer.parseInt(sec));
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
	public static String time_MinusHoursMinutesSeconds(String hr, String min, String sec, String pattern_like_HH_mm_ss) {
		LocalTime currentTime = LocalTime.now(DateTimeZone.forID("America/Chicago"));
		LocalTime resultTime = currentTime.minusHours(Integer.parseInt(hr)).minusMinutes(Integer.parseInt(min)).minusSeconds(Integer.parseInt(sec));
		// DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_like_HH_mm_ss);
		String str = fmt.print(resultTime);

		System.out.println(str);
		return str;

	}




	public static Date getFirstDayOfCurrentMonth() {
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}



	/** converts the string representation to a Date format
	 * @param date date to convert
	 * @return the converted date
	 */
	public static Date parseStringToDate(String date) {
		Date newdate = null;
		try{

			//dateFormatUS.setTimeZone(utc);
			newdate = dateFormatUS.parse(date);

		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		return newdate;
	}

	/** converts the string representation to a Date format
	 * @param date date to convert
	 * @return the converted date
	 */
	public static Date parseUSStringToDate(String date) {
		Date newdate = null;

		try {
			//dateFormatUS.setTimeZone(utc);
			newdate = dateFormatUS.parse(date);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return newdate;
	}

	/** converts the string representation to a Date format
	 * @param date date to convert
	 * @return the converted date
	 */
	public static Date parseStringToDateAndTime(String date) {
		Date newdate = null;
		try{

			dateAndTimeFormatUS.setTimeZone(utc);
			newdate = dateAndTimeFormatUS.parse(date);

		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return newdate;
	}

	/**
	 * formats the reassessed formatted date to a date
	 * @param date the date to format
	 * @return formatted date
	 */
	public static Date parseReassessedStringToDate(String date) {
		Date newdate = null;
		try{

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



	/**
	 * formats the date to a String
	 * @param date the date to format
	 * @return formatted date
	 */
	public static String formatDateToGanttString(Date date) {

		ganttChartFormatUS.setTimeZone(utc);
		return ganttChartFormatUS.format(date);
	}






	/**
	 * formats the date to a reassessed formatted date
	 * @param date the date to format
	 * @return formatted date
	 */
	public static String formatToReportingDBDate(Date date) {
		reassessedDateFormatUS.setTimeZone(utc);
		return reassessedDateFormatUS.format(date);
	}

	/**
	 * retrieves what day of the week today falls on
	 * @return day of the week
	 * @throws Exception 
	 */
	public static String getDayOfWeek() throws Exception
	{
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

	public static String getDateFormat()
	{

		return dateFormatUS.toLocalizedPattern();
	}

	// Miscellaneous

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
			System.out.println("Date" + " = " + simpleDateFormat.format(date));
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
	}// end of trim



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
	public static void JodaDateTimeDifference(String dateStart, String dateStop, String format_like_MMddyyyyHHmmss)
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

	/**
	 * Time Difference using Joda - returns Min Sec
	 * @param dateStart
	 * @param dateStop
	 * @param format_like_MMddyyyyHHmmss
	 */
	public static String datetime_JodaDateTimeDifference_MinSec(String dateStart, String dateStop, String format_like_MMddyyyyHHmmss)
	{

		//	String dateStart = "01/14/2012 09:29:58";
		//	String dateStop = "01/15/2012 10:31:48";

		//	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat(format_like_MMddyyyyHHmmss);
		Date d1 = null;
		Date d2 = null;

		int min = 0;
		int sec = 0;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			DateTime dt1 = new DateTime(d1);
			DateTime dt2 = new DateTime(d2);

			min = Minutes.minutesBetween(dt1, dt2).getMinutes()%60;
			sec = Seconds.secondsBetween(dt1, dt2).getSeconds()%60;
			System.out.println("datetime_JodaDateTimeDifference_MinSec");
			System.out.print(Days.daysBetween(dt1, dt2).getDays() + " days, ");
			System.out.print(Hours.hoursBetween(dt1, dt2).getHours() % 24 + " hours, ");
			System.out.print(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " minutes, ");
			System.out.print(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 + " seconds.");
			System.out.println();
			System.out.println("datetime_JodaDateTimeDifference_MinSec -- "+ dateStart+" & "+ dateStop +" * format * "+ format_like_MMddyyyyHHmmss + " = "+min+" Min."+" "+sec+" Sec.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return min+" Min."+" "+sec+" Sec.";
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
		System.out.println("Time in seconds: " + diffSeconds + " seconds.");
		System.out.println("Time in minutes: " + diffMinutes + " minutes.");
		System.out.println("Time in hours: " + diffHours + " hours.");
		System.out.println("Time in days: " + diffDays 	+ " days.");
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

	/**
	 * getDiffBetweenDateAndTimes - Returns the time difference between two timestamps in terms of minutes as an integer
	 * @return
	 */
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
	 * getDiffBetweenDateAndTimes_MinSec - Returns the time difference between two timestamps in terms of minutes & seconds as a string [8 Min. 7 Sec.]
	 * @return
	 */
	public static String getDiffBetweenDateAndTimes_MinSec(String date1, String date2) {
		long diff = parseStringToDateAndTime((date1)).getTime()	- parseStringToDateAndTime((date2)).getTime();
		int difference_sec = (int) (diff / (1000 )); // Sec
		//int difference = (int) (diff / (1000 * 60)); // Min
		System.out.println(parseStringToDateAndTime((date1)).getTime());
		System.out.println(parseStringToDateAndTime((date2)).getTime());

		System.out.println(diff);
		System.out.println("The difference between "+ date1 + " & "+ date2 + " = "+ difference_sec +" Seconds.");
		return "sec";
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();

		return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}

	public static long getDateDiff_Sec(Date date1, Date date2,TimeUnit timeUnit) {
		long diffInSec = date2.getTime() - date1.getTime();

		return timeUnit.convert(diffInSec,TimeUnit.SECONDS);
	}

	public static long getDateDiff_Min(Date date1, Date date2,TimeUnit timeUnit) {
		long diffInMin = date2.getTime() - date1.getTime();

		return timeUnit.convert(diffInMin,TimeUnit.MINUTES);
	}

	/**
	 * getDateDiff_MinSec - Returns time difference between two datetimes as '8 Min. 7 Sec.' 
	 * @param date1
	 * @param date2
	 * @param timeUnit
	 * @return
	 */
	public static String getDateDiff_MinSec(Date date1, Date date2) {

		//Date datde1 = new Date("dd");

		long diffInMin = date2.getTime() - date1.getTime();
		long diffInSec = date2.getTime() - date1.getTime();

		diffInMin= TimeUnit.MINUTES.convert(diffInMin,TimeUnit.MINUTES);
		diffInSec= TimeUnit.SECONDS.convert(diffInMin,TimeUnit.SECONDS);
		System.out.println("getDateDiff_MinSec :"+diffInMin+" Min."+" "+diffInSec+" Sec." );
		return diffInMin+" Min."+" "+diffInSec+" Sec.";
	}

	//   getDateDiff(date1,date2,TimeUnit.MINUTES);


	//  JodaDateDifference(dateStart, dateStop, format_like_MMddyyyyHHmmss)

	public static String time_Difference(String date1, String date2) {

		ReadableInstant oldInstant = null;
		Interval interval = new Interval(oldInstant, new Instant());

		// can also get the diff for Local dates/times 

		// returns 4 because of the leap year of 366 days
		new Period(LocalDate.now(), LocalDate.now().plusDays(365*5), PeriodType.years()).getYears() ;

		// this time it returns 5
		new Period(LocalDate.now(), LocalDate.now().plusDays(365*5+1), PeriodType.years()).getYears() ;

		// And you can also use these static methods
		Years.yearsBetween(LocalDate.now(), LocalDate.now().plusDays(365*5)).getYears();
		return date2;

	}
	/**
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

	//1 minute = 60 seconds
	//1 hour = 60 x 60 = 3600
	//1 day = 3600 x 24 = 86400
	public static void dateDifference(Date startDate, Date endDate){

		//milliseconds
		long different = endDate.getTime() - startDate.getTime();

		System.out.println("startDate : " + startDate);
		System.out.println("endDate : "+ endDate);
		System.out.println("different : " + different);

		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;

		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;

		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;

		long elapsedSeconds = different / secondsInMilli;

		System.out.printf(
				"%d days, %d hours, %d minutes, %d seconds%n",
				elapsedDays,
				elapsedHours, elapsedMinutes, elapsedSeconds);

	}


	public static void date_Difference(Date startDate, Date endDate){

		Interval interval =
				new Interval(startDate.getTime(), endDate.getTime());
		Period period = interval.toPeriod();

		System.out.printf(
				"%d years, %d months, %d days, %d hours, %d minutes, %d seconds%n",
				period.getYears(), period.getMonths(), period.getDays(),
				period.getHours(), period.getMinutes(), period.getSeconds());

	}

	public static String getCurrentDate(String desiredDateFormat){
		String dateString = null;
		try {
			LocalDate date = LocalDate.now();
			DateTimeFormatter format = DateTimeFormat.forPattern(desiredDateFormat);
			dateString = format.print(date);
			System.out.println("Current Date = "+ dateString);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateString;


	}


	// desiredDateTimeFormat - Returns current timestamp

	public static String getCurrentTimestamp(String desiredDateTimeFormat){
		String timeStamp = null;
		try {
			timeStamp = new SimpleDateFormat(desiredDateTimeFormat,Locale.US).format(new Date());
			//System.out.println("Current Timestamp = "+ timeStamp);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return timeStamp;
	}

	/**
	 * param getDateTimeDifference - Takes 3 arguments (StartTime, EndTime, DateTimeFormat) Returns the difference between two timestamps in format: 8 Min. 15 Sec.
	 * @param startDateTime
	 * @param endDateTime
	 * @param format_DateTime
	 * @return
	 * @throws ParseException
	 */

	public static String getDateTimeDifference(String startDateTime, String endDateTime, String format_DateTime) throws ParseException{

		SimpleDateFormat format = new SimpleDateFormat(format_DateTime);

		Date d1 = null;
		Date d2 = null;

		int min = 0;
		int sec = 0;


		d1 =format.parse(startDateTime);
		d2 =format.parse(endDateTime);


		DateTime  dt1 = new DateTime(d1);
		DateTime  dt2 = new DateTime(d2);

		min= Minutes.minutesBetween(dt1, dt2).getMinutes()%60;
		sec =Seconds.secondsBetween(dt1, dt2).getSeconds()%60;
		//	int hours = Hours.hoursBetween(dt1, dt2).getHours()%24;
		System.out.println("~ The difference between "+startDateTime +" & "+ endDateTime +" = "+ min+" Min."+" "+sec+ "Sec." );
		return min+" Min."+" "+sec+ "Sec.";

	}	


	public static String datetime_MinusDays(int n, String pattern_MMdyyyHHmmss){

		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Montreal"));
		LocalDateTime resultDateTime = today.minusDays(n);

		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_MMdyyyHHmmss);
		String str = fmt.print(resultDateTime);

		System.out.println("datetime_MinusDays :"+str);

		return str;

	}



	public static String datetime_PlusYears(int n, String pattern_MMdyyyHHmmss){

		LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Montreal"));
		LocalDateTime resultDateTime = today.plusYears(n);

		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern_MMdyyyHHmmss);
		String str = fmt.print(resultDateTime);

		System.out.println("datetime_PlusYears :"+str);

		return str;

	}

	/**
	 * datetime_PlusOrMinus_YearsMonthsWeeksDaysHoursMinutsSeconds - Add or Subtract YearsMonthsWeeksDaysHoursMinutsSecond
	 *  Pass Positive integer for adding the values
	 *  Pass Negative integer for subtracting the values
	 * @param years
	 * @param months
	 * @param weeks
	 * @param days
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @param DateTimeFormat
	 */
	public static String datetime_PlusOrMinus_YearsMonthsWeeksDaysHoursMinutsSeconds(int years, int months, int weeks, int days , int hours, int minutes, int seconds, String DateTimePattern )
	{
		String str = "";
		try {
			LocalDateTime today = LocalDateTime.now(DateTimeZone.forID("America/Montreal"));

			LocalDateTime resultDateTime = today.plusYears(years).plusMonths(months).plusWeeks(weeks).plusDays(days).plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);

			DateTimeFormatter fmt = DateTimeFormat.forPattern(DateTimePattern);
			str = fmt.print(resultDateTime);

			System.out.println("datetime_PlusOrMinus_YearsMonthsWeeksDaysHoursMinutsSeconds :\n"
					+ years + "years"+"+"
					+ months + "months"+"+"
					+ weeks + "weeks"+"+"
					+ days + "days"+"+"
					+ hours + "hours"+"+"
					+ minutes + "minutes"+"+"
					+ seconds + "seconds"
					+" = "+str);


		} catch (Exception e) {
			e.printStackTrace();
		}

		finally{
			System.out.println("Entered finally block!");
		}
		return str;
	}

	public static void main(String args[]){

		try {
			//			datetime_PlusHours(2, "MM/d/yyyy HH.mm.ss");
			//			datetime_MinusDays(5, "MM/d/yyy HH.mm.ss");
			//			datetime_PlusYears(-2, "MM-d-YYYY HH:mm:ss");
			//			datetime_PlusOrMinus_YearsMonthsWeeksDaysHoursMinutsSeconds(1, 2, 3, 4, 2, 15, 10, "MM-dd-yyyy HH:mm:ss");
			//			datetime_PlusOrMinus_YearsMonthsWeeksDaysHoursMinutsSeconds(-5, 2, 3, 4, 2, 15, 10, "MM-dd-yyyy HH:mm:ss");
			//			getCurrentDate("MM/dd/yyyy");
			getCurrentTimestamp("MM/dd/yyyy HH:mm:ss");
			//	getDateTimeDifference(datetime_PlusHours(0, "MM/d/yyyy HH.mm.ss"), datetime_PlusHours(2, "MM/d/yyyy HH.mm.ss"), "MM/dd/yyyy HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
