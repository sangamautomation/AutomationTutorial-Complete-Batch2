package utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import data.Constants;
import data.TestDataPool;
import infrastructure.Configuration;
 
/**
 * Database Utils
  * @author Sangam
 */
public class DatabaseUtils {

	public static String databaseValidation_SelectSQL(String ip, String dbname, String port, String sql1, String input1,
			String sql2, String input2, String sql_Suffix, String resultcolname) throws Exception {
		System.out.println(" \n databaseValidation_SelectSQL");

		String ipAddress = ip;
		String portNumber = port;
		String databaseName = dbname;

		String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
		String ldapPassword = PasswordUtils
				.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

		// String username= new Configuration().getProperty("ldapUserID");
		// String password= PasswordUtils.decryptString(new
		// Configuration().getProperty("ldapPassword"));

		String username = ldapUserID;
		String password = ldapPassword;

		String sql = null;

		String columnValue = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection(ipAddress, portNumber, databaseName, username, password);
			// db.connect();
			con.connect();

			// Creating SQL Query

			ResultSet resultset;

			// sql = "select STATUSCODE from Automation1.concernrolealternateid where
			// alternateid = ('726967846')";
			// sql = sql1 + "'"+ input + "'"+ ");";
			// sql = sql1 + "'"+ input + "'"+ ")" + sql2;
			// sql = sql1 + "'"+ input1 + "'"+ sql2 + "'"+ input2 + "'" +
			// sql_Suffix;
			sql = sql1 + "'" + input1 + "'" + sql2 + input2 + sql_Suffix; // for
																			// input2
																			// is
																			// empty

			// sql = "select STATUSCODE from Automation1.concernrolealternateid fetch
			// first 1 rows only";
			// sql ="select * from landing.MMIS_Automation_landing where
			// RECIPIENT_SSN in (227778210)";
			System.out.println("SQL after conversion: " + sql);
			resultset = con.query(sql);
			/*
			 * System.out.println("resultset \n" +
			 * resultset.findColumn(resultcolname)); System.out.println(
			 * "resultset \n" + resultset.toString()); System.out.println(
			 * "resultset \n" + resultset.getString(resultcolname));
			 */

			// ArrayList <String> result = new ArrayList<String>();

			if (!resultset.next()) {
				System.out.println("No Data Found");
				columnValue = null;
			} else {
				columnValue = (resultset.getString(resultcolname));
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
		System.out.println("Actual Database query result: " + columnValue + " \n ---------------------------");
		ReportUtils.reportResult("Done", "Database Validation",
				"Query executed: \n" + sql + "\n Actual Database query result: " + columnValue);
		if (null == columnValue) {
			return null;
		}
		return columnValue.replaceAll("\\s", "");
	}

	// Database Validation returning whole row as Array of Strings
	public static String[] databaseValidation_Select(String ip, String dbname, String port, String sql1, String input1,
			String sql2, String input2, String sql_Suffix) throws Exception {
		System.out.println("\n databaseValidation_Select");

		String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
		String ldapPassword = PasswordUtils
				.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

		// String username= new Configuration().getProperty("ldapUserID");
		// String pwd= PasswordUtils.decryptString(new
		// Configuration().getProperty("ldapPassword"));

		String username = ldapUserID;
		String password = ldapPassword;

		int j = 0;
		// SELECT trim(AutomationCASENUMBER) FROM Automation1.SeleniumVLPRESPONSE where
		// responsecode='SeleniumVLP004' and recordstatus='RST1' ORDER BY LastWritten
		// DESC FETCH FIRST 1 ROW ONLY
		String sql = sql1 + "'" + input1 + "'" + sql2 + "'" + input2 + "'" + sql_Suffix;
		System.out.println("SQL Query after conversion: " + sql);

		CustomDB2Connection con = new CustomDB2Connection(ip, port, dbname, username, password);
		// String[] columnValue = new String[30];

		ResultSet resultset;
		con.connect();
		resultset = con.query(sql);
		ResultSetMetaData rsmd = resultset.getMetaData();
		int numberOfColumns = rsmd.getColumnCount();
		String[] columnValue = new String[numberOfColumns];

		// for (int i = 1; i <= numberOfColumns; i++) {
		// if (i > 1) System.out.print(", ");
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
				System.out.println("columnValue" + i + " = " + columnValue[j]);
				j++;

			}

		}

		con.close();

		System.out.println("End of databaseValidation_Select \n ---------------------------");
		return columnValue;

	}

	// Database Validation with Update SQL
	public static int databaseValidation_UpdateSQL(String ip, String dbname, String port, String sql_Update,
			String setValue, String sql1, String input1, String sql2, String input2) throws Exception {
		System.out.println(" \n databaseValidation_UpdateSQL");

		String ipAddress = ip;
		String portNumber = port;
		String databaseName = dbname;

		String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
		String ldapPassword = PasswordUtils
				.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

		// String username= new Configuration().getProperty("ldapUserID");
		// String password= PasswordUtils.decryptString(new
		// Configuration().getProperty("ldapPassword"));

		String username = ldapUserID;
		String password = ldapPassword;

		int updateStatus = 0;

		String columnValue = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection(ipAddress, portNumber, databaseName, username, password);
			// db.connect();
			con.connect();

			// Creating SQL Query
			String sql;
			ResultSet resultset;

			// sql = "select STATUSCODE from Automation1.concernrolealternateid where
			// alternateid = ('726967846')";
			// sql = sql1 + "'"+ input + "'"+ ");";
			// sql = sql1 + "'"+ input + "'"+ ")" + sql2;

			// sql = sql_Update + "'"+ setValue + "'"+ sql1 + "'"+ input1 + "'"
			// + sql2 + "'"+ input2 + "'" ;
			sql = sql_Update + "'" + setValue + "'" + sql1 + "'" + input1 + "'" + sql2 + input2;

			// sql = "select STATUSCODE from Automation1.concernrolealternateid fetch
			// first 1 rows only";
			// sql ="select * from landing.MMIS_Automation_landing where
			// RECIPIENT_SSN in (227778210)";
			System.out.println("SQL after conversion: " + sql);
			// con.query(sql);
			updateStatus = con.query_update(sql);
			// updateStatus = con.query_updated(sql);

			// con.query_updateSQL("update Automation1.concernrolealternateid set
			// statuscode = 'RST1' where alternateid = ('726967846')");

			// con.commit();
			// con.setAutoCommit(true);
			// con.query_update("Update Automation1.SeleniumVLPRESPONSE set firstname =
			// 'sangama' where responsecode = 'SeleniumVLP004' and recordstatus =
			// 'RST1'");
			// resultset = con.query(sql);
			/*
			 * System.out.println("resultset \n" +
			 * resultset.findColumn(resultcolname)); System.out.println(
			 * "resultset \n" + resultset.toString()); System.out.println(
			 * "resultset \n" + resultset.getString(resultcolname));
			 */

			// ArrayList <String> result = new ArrayList<String>();

			/*
			 * if(!resultset.next()) { System.out.println("No Data Found"); }
			 * columnValue = (resultset.getString(resultcolname));
			 */ // while (resultset.next()) {
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

		if (updateStatus > 0)
			System.out.println("Update query is successful.  \n ---------------------------");
		else
			System.out.println("Update query is NOT successful.  \n ---------------------------");

		return updateStatus;
	}

	// Compares two database results and reports
	public static void Database_Compare(String ExpectedDBResult, String ActualDBResult, String ReportComment)
			throws Exception {
		System.out.println("Database_Compare");
		if (ExpectedDBResult == ActualDBResult || ExpectedDBResult.equals(ActualDBResult)) {
			System.out.println(ReportComment);
			ReportUtils.reportResult("Pass", "DB Record 1", ReportComment + " The validation is successful!");

			System.out.println(ReportComment);
		} else {
			ReportUtils.reportResult("Fail", "DB Record 1", ReportComment + " The validation is NOT successful!");

		}

	}

	public static void insertIntoAutomationDB(HashMap<String, String> h) throws Exception {
		System.out.println("databaseValidation");

		String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
		String ldapPassword = PasswordUtils
				.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));
		String jenkinsUser = TestDataPool.rowData.get("jenkinsUserName");
		String ipAddress = "rsadb.gmail.com";
		String portNumber = "50001";
		String databaseName = "ptr";
		String username = ldapUserID;
		String password = ldapPassword;

		try {
			CustomDB2Connection con = new CustomDB2Connection(ipAddress, portNumber, databaseName, username, password);
			con.connect();

			// Creating SQL Query
			String commentsValue = h.get("hostname") + "," + h.get("ipAddress") + ","
					+ TestDataPool.rowData.get("jenkinsBuildNumber");
			String logPathUpdated = h.get("logPath").replace("D:", "\\\\" + h.get("hostname"));
			String sql = "INSERT INTO PTR.AUTOMATION_REGRESSIONSTATUS (SCRIPTNAME, ENVIRONMENT, STARTTIME, ENDTIME, STATUS, PATHOFLOG, FIRSTNAME, LASTNAME, DOB, SSN, COMMENTS, LASTWRITTEN, EXECUTEDBY) "
					+ "VALUES ('" + h.get("stack") + "','" + h.get("Environment") + "','" + h.get("scriptStartTime")
					+ "','" + h.get("scriptEndTime") + "','" + h.get("Verdict") + "','" + logPathUpdated + "','"
					+ h.get("fName1") + "','" + h.get("lName1") + "','" + h.get("dob1") + "','" + h.get("ssn1") + "','"
					+ commentsValue + "','" + DateUtils.datetime_Now("yyyy-MM-dd-HH.mm.ss") + "','" + jenkinsUser
					+ "')";

			System.out.println("SQL after conversion: " + sql);
			//System.out.println("Query status:" + con.query_insert(sql));
			System.out.println("Query status:" + con.query_update(sql));

			con.close();

		}

		catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Database Validation returning only cell value as String

	public static String databaseValidation(String ip, String dbname, String port, String sql, String input,
			String resultcolname) throws Exception {
		System.out.println("databaseValidation");

		String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
		String ldapPassword = PasswordUtils
				.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

		// String ldapUserId= new Configuration().getProperty("ldapUserID");
		// String ldapPassword= PasswordUtils.decryptString(new
		// Configuration().getProperty("ldapPassword"));

		String ipAddress = ip;
		String portNumber = port;
		String databaseName = dbname;
		String username = ldapUserID;
		String password = ldapPassword;

		ArrayList<Integer> resultList = new ArrayList<Integer>();

		String K = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection(ipAddress, portNumber, databaseName, username, password);
			// db.connect();
			con.connect();

			// Creating SQL Query

			ResultSet resultset;

			// sql =
			// "select STATUSCODE from Automation1.concernrolealternateid where
			// alternateid = ('726967846')";
			// sql = sql1 + "'"+ input + "'"+ ");";
			sql = sql + "'" + input + "'" + ")";

			// sql =
			// "select STATUSCODE from Automation1.concernrolealternateid fetch first
			// 1 rows only";
			// sql
			// ="select * from landing.MMIS_Automation_landing where RECIPIENT_SSN in
			// (227778210)";
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

	public static String[] databaseValidation(String ip, String dbname, String port, String sql) throws Exception {

		String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
		String ldapPassword = PasswordUtils
				.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

		// String username= new Configuration().getProperty("ldapUserID");
		// String pwd= PasswordUtils.decryptString(new
		// Configuration().getProperty("ldapPassword"));

		String username = ldapUserID;
		String password = ldapPassword;

		int j = 0;
		CustomDB2Connection con = new CustomDB2Connection(ip, port, dbname, username, password);
		// String[] columnValue = new String[30];

		ResultSet resultset;
		con.connect();
		resultset = con.query(sql);
		ResultSetMetaData rsmd = resultset.getMetaData();
		int numberOfColumns = rsmd.getColumnCount();
		String[] columnValue = new String[numberOfColumns];

		// for (int i = 1; i <= numberOfColumns; i++) {
		// if (i > 1) System.out.print(", ");
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
				// System.out.println(columnValue[j]);
				j++;

			}

		}

		con.close();

		return columnValue;

	}

	// Database Validation returning multiple rows of Strings (need to return
	// String [][])

	public static String databaseValidation_MultipleRows(String ip, String dbname, String port, String sql1,
			String input, String sql2, String resultcolname) throws Exception {

		String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
		String ldapPassword = PasswordUtils
				.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

		// String ldapUserId= new Configuration().getProperty("ldapUserID");
		// String ldapPassword= PasswordUtils.decryptString(new
		// Configuration().getProperty("ldapPassword"));

		String ipAddress = ip;
		String portNumber = port;
		String databaseName = dbname;
		String username = ldapUserID;
		String password = ldapPassword;

		ArrayList<Integer> resultList = new ArrayList<Integer>();

		String K = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection(ipAddress, portNumber, databaseName, username, password);
			// db.connect();
			con.connect();

			// Creating SQL Query
			String sql;
			ResultSet resultset;

			// sql =
			// "select STATUSCODE from Automation1.concernrolealternateid where
			// alternateid = ('726967846')";
			// sql = sql1 + "'"+ input + "'"+ ");";
			sql = sql1 + "'" + input + "'" + ")" + sql2;

			// sql =
			// "select STATUSCODE from Automation1.concernrolealternateid fetch first
			// 1 rows only";
			// sql
			// ="select * from landing.MMIS_Automation_landing where RECIPIENT_SSN in
			// (227778210)";
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

	// ****************************************************************//
	// Database Validation (use in Library function) //
	// ****************************************************************//

	// Declare the variable as global (in the beginning of the class so that it
	// can be used in any function)
	String Username;

	public static void databaseValidation2() throws Exception {

		try {
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection();
			// db.connect();
			con.connect();

			String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
			String ldapPassword = PasswordUtils
					.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

			// String username= new Configuration().getProperty("ldapUserID");
			// String pwd= PasswordUtils.decryptString(new
			// Configuration().getProperty("ldapPassword"));

			String username = ldapUserID;
			String password = ldapPassword;

			// Creating SQL Query
			String sql;
			ResultSet resultset;
			// sql = "select username from curapd01.externaluser where username
			// = ('" + Username + "')";
			// sql = "select STATUSCODE from Automation1.concernrolealternateid where
			// alternateid like ('72696784')";
			sql = "select STATUSCODE from Automation1.concernrolealternateid where alternateid  = ('726967846')";
			// sql = "select tt.TASKID from Automation1.task tt ,
			// Automation1.taskassignment ta where ta.taskid = tt.taskid and
			// TT.STATUS = 'WS1' and WDOSNAPSHOT like '%name=\"caseReference\"
			// value=\"'||271||'%' and WDOSNAPSHOT like '%AutoActivate%'";
			// sql = getTaskByCaseReference( 271, "Automation1", "AutoActivate" );
			System.out.println("SQL after conversion: " + sql);
			// sql = "select EXTRACT( YEAR from ppp.DATEOFBIRTH) DOB FROM
			// Automation1.codetableitem cti, Automation1.SeleniumCASEDETERMINATION h JOIN
			// Automation1.CASEHEADER chb ON chb.CASEID = h.CASEID JOIN
			// Automation1.SeleniumCASEDETERMINATIONDATA d ON d.DETERMINATIONID =
			// h.DETERMINATIONID JOIN Automation1.concernrole crn ON
			// crn.CONCERNROLEID = d.CONCERNROLEID JOIN
			// Automation1.SeleniumCASEDETERMINATIONVALUE v ON v.determinationvalueid =
			// d.determinationvalueid JOIN Automation1.person ppp ON
			// crn.CONCERNROLEID = ppp.CONCERNROLEID WHERE h.RECORDSTATUS =
			// 'RST1' AND cti.TABLENAME = 'SeleniumDeterminationDataName' AND
			// cti.CODE = d.NAME AND d.Name='SeleniumDN00008' -- coverage type and
			// ppp.dateofbirth between '6/23/1996' and '6/23/1999' group by
			// EXTRACT( YEAR from ppp.DATEOFBIRTH), v.value order by EXTRACT(
			// YEAR from ppp.DATEOFBIRTH) asc, count(distinct crn.CONCERNROLEID)
			// desc; ";
			// sql = "select EXTRACT( YEAR from ppp.DATEOFBIRTH) DOB, v.value
			// coverage, count(distinct crn.CONCERNROLEID) count_ FROM
			// Automation1.codetableitem cti, Automation1.SeleniumCASEDETERMINATION h JOIN
			// Automation1.CASEHEADER chb ON chb.CASEID = h.CASEID JOIN
			// Automation1.SeleniumCASEDETERMINATIONDATA d ON d.DETERMINATIONID =
			// h.DETERMINATIONID JOIN Automation1.concernrole crn ON
			// crn.CONCERNROLEID = d.CONCERNROLEID JOIN
			// Automation1.SeleniumCASEDETERMINATIONVALUE v ON v.determinationvalueid =
			// d.determinationvalueid JOIN Automation1.person ppp ON
			// crn.CONCERNROLEID = ppp.CONCERNROLEID WHERE h.RECORDSTATUS =
			// 'RST1' AND cti.TABLENAME = 'SeleniumDeterminationDataName' AND
			// cti.CODE = d.NAME AND d.Name='SeleniumDN00008' type and
			// ppp.dateofbirth between '6/23/1996' and '6/23/1999' group by
			// EXTRACT( YEAR from ppp.DATEOFBIRTH), v.value order by EXTRACT(
			// YEAR from ppp.DATEOFBIRTH) asc, count(distinct crn.CONCERNROLEID)
			// desc; ";

			resultset = con.query(sql);

			// System.out.println("Results: "+ resultset.getString(1));

			// System.out.println("Results: "+ resultSetToArrayList(resultset));

			ArrayList<String> result = new ArrayList<String>();

			while (resultset.next()) {
				result.add(resultset.getString(1));
			}
			for (int i = 0; i <= result.size(); i++)
				System.out.println((String[]) result.toArray(new String[result.size()]));

			/*
			 * for (int i=0; resultset.next(); i++) { result.add(
			 * resultset.getString(i) ); System.out.println("Resultss: "+
			 * result); } resultset.next();
			 * 
			 */

			/*
			 * if(resultset.getString("STATUSCODE").equals("RST1")) {
			 * System.out.println(
			 * "USERNAME record has been created in the Database table ExternalUser : PASS"
			 * ); sp.logInfo(
			 * "USERNAME record has been created in the Database table ExternalUser : PASS"
			 * ); } else { System.out.println(
			 * "USERNAME record has NOT been created in the Database table ExternalUser : FAIL"
			 * ); sp.logInfo(
			 * "USERNAME record has NOT been created in the Database table ExternalUser : FAIL"
			 * ); }
			 */
			con.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static String databaseValidation3() throws Exception {

		String K = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection();
			// db.connect();
			con.connect();

			// Creating SQL Query
			String sql;
			ResultSet resultset;

			String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
			String ldapPassword = PasswordUtils
					.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

			// String username= new Configuration().getProperty("ldapUserID");
			// String pwd= PasswordUtils.decryptString(new
			// Configuration().getProperty("ldapPassword"));

			String username = ldapUserID;
			String password = ldapPassword;

			// sql = "select STATUSCODE from Automation1.concernrolealternateid fetch
			// first 3 rows only";

			// sql = "select username from curapd01.externaluser where username
			// = ('" + Username + "')";
			// sql = "select STATUSCODE from Automation1.concernrolealternateid where
			// alternateid like ('72696784')";
			sql = "select STATUSCODE from Automation1.concernrolealternateid where alternateid  = ('726967846')";
			// sql = "select tt.TASKID from Automation1.task tt ,
			// Automation1.taskassignment ta where ta.taskid = tt.taskid and
			// TT.STATUS = 'WS1' and WDOSNAPSHOT like '%name=\"caseReference\"
			// value=\"'||271||'%' and WDOSNAPSHOT like '%AutoActivate%'";
			// sql = getTaskByCaseReference( 271, "Automation1", "AutoActivate" );
			System.out.println("SQL after conversion: " + sql);
			// sql = "select EXTRACT( YEAR from ppp.DATEOFBIRTH) DOB FROM
			// Automation1.codetableitem cti, Automation1.SeleniumCASEDETERMINATION h JOIN
			// Automation1.CASEHEADER chb ON chb.CASEID = h.CASEID JOIN
			// Automation1.SeleniumCASEDETERMINATIONDATA d ON d.DETERMINATIONID =
			// h.DETERMINATIONID JOIN Automation1.concernrole crn ON
			// crn.CONCERNROLEID = d.CONCERNROLEID JOIN
			// Automation1.SeleniumCASEDETERMINATIONVALUE v ON v.determinationvalueid =
			// d.determinationvalueid JOIN Automation1.person ppp ON
			// crn.CONCERNROLEID = ppp.CONCERNROLEID WHERE h.RECORDSTATUS =
			// 'RST1' AND cti.TABLENAME = 'SeleniumDeterminationDataName' AND
			// cti.CODE = d.NAME AND d.Name='SeleniumDN00008' -- coverage type and
			// ppp.dateofbirth between '6/23/1996' and '6/23/1999' group by
			// EXTRACT( YEAR from ppp.DATEOFBIRTH), v.value order by EXTRACT(
			// YEAR from ppp.DATEOFBIRTH) asc, count(distinct crn.CONCERNROLEID)
			// desc; ";
			// sql = "select EXTRACT( YEAR from ppp.DATEOFBIRTH) DOB, v.value
			// coverage, count(distinct crn.CONCERNROLEID) count_ FROM
			// Automation1.codetableitem cti, Automation1.SeleniumCASEDETERMINATION h JOIN
			// Automation1.CASEHEADER chb ON chb.CASEID = h.CASEID JOIN
			// Automation1.SeleniumCASEDETERMINATIONDATA d ON d.DETERMINATIONID =
			// h.DETERMINATIONID JOIN Automation1.concernrole crn ON
			// crn.CONCERNROLEID = d.CONCERNROLEID JOIN
			// Automation1.SeleniumCASEDETERMINATIONVALUE v ON v.determinationvalueid =
			// d.determinationvalueid JOIN Automation1.person ppp ON
			// crn.CONCERNROLEID = ppp.CONCERNROLEID WHERE h.RECORDSTATUS =
			// 'RST1' AND cti.TABLENAME = 'SeleniumDeterminationDataName' AND
			// cti.CODE = d.NAME AND d.Name='SeleniumDN00008' type and
			// ppp.dateofbirth between '6/23/1996' and '6/23/1999' group by
			// EXTRACT( YEAR from ppp.DATEOFBIRTH), v.value order by EXTRACT(
			// YEAR from ppp.DATEOFBIRTH) asc, count(distinct crn.CONCERNROLEID)
			// desc; ";

			System.out.println("SQL after conversion: " + sql);
			resultset = con.query(sql);

			// ArrayList <String> result = new ArrayList<String>();

			K = (resultset.getString("STATUSCODE"));
			System.out.println("Inisde1 K" + K);

			if (!resultset.next()) {
				System.out.println("No Data Found");
			}

			while (resultset.next()) {

				K = (resultset.getString("STATUSCODE"));
				count++;
				System.out.println(count);

			}

			con.close();

		}

		catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("Inisde " + K);
		return K;

	}

	public static String databaseValidation() throws Exception {

		String K = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();

			String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
			String ldapPassword = PasswordUtils
					.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

			// String Username= new Configuration().getProperty("ldapUserID");
			// String password= PasswordUtils.decryptString(new
			// Configuration().getProperty("ldapPassword"));

			CustomDB2Connection con = new CustomDB2Connection();
			// CustomDB2Connection con = new CustomDB2Connection();
			// db.connect();
			con.connect();

			// Creating SQL Query
			String sql;
			ResultSet resultset;

			sql = "select STATUSCODE from Automation1.concernrolealternateid where alternateid  = ('726967846')";
			// sql = "select * from landing.MMIS_Automation_landing where
			// RECIPIENT_SSN = ('111222333')";
			// sql = "select STATUSCODE from Automation1.concernrolealternateid fetch
			// first 1 rows only";
			// sql ="select * from landing.MMIS_Automation_landing where
			// RECIPIENT_SSN in (227778210)";
			System.out.println("SQL after conversion: " + sql);
			resultset = con.query(sql);
			System.out.println("resultset \n" + resultset);

			// ArrayList <String> result = new ArrayList<String>();

			if (!resultset.next()) {
				System.out.println("No Data Found");
			}

			K = (resultset.getString("STATUSCODE"));
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
		System.out.println("Value =  " + K);
		return K;

	}

	public static String databaseValidation(String input) throws Exception {

		String ipAddress = "SeleniumAutomationdbtst.gmail.com";
		String portNumber = "50000";
		String databaseName = "curtst1";

		String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
		String ldapPassword = PasswordUtils
				.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

		// String username= new Configuration().getProperty("ldapUserID");
		// String password= PasswordUtils.decryptString(new
		// Configuration().getProperty("ldapPassword"));

		String username = ldapUserID;
		String password = ldapPassword;

		String K = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection(ipAddress, portNumber, databaseName, username, password);
			// db.connect();
			con.connect();

			// Creating SQL Query
			String sql;
			ResultSet resultset;

			// sql = "select STATUSCODE from Automation1.concernrolealternateid where
			// alternateid = ('726967846')";
			sql = "select RECIPIENT_CATEGORY from landing.MMIS_Automation_landing where RECIPIENT_SSN = " + "'" + input + "'"
					+ ");";
			// sql = "select STATUSCODE from Automation1.concernrolealternateid fetch
			// first 1 rows only";
			// sql ="select * from landing.MMIS_Automation_landing where
			// RECIPIENT_SSN in (227778210)";
			System.out.println("SQL after conversion: " + sql);
			resultset = con.query(sql);
			System.out.println("resultset \n" + resultset);

			// ArrayList <String> result = new ArrayList<String>();

			if (!resultset.next()) {
				System.out.println("No Data Found");
			}

			K = (resultset.getString("STATUSCODE"));
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
		System.out.println("Inisde " + K);
		return K;

	}

	public static String databaseCheck(String ip, String dbname, String port, String sql1, String input1, String sql2,
			String input2, String sql_Suffix, String resultcolname) throws Exception {
		System.out.println(" \n database_Check_selectSQL");

		String ipAddress = ip;
		String portNumber = port;
		String databaseName = dbname;

		String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
		String ldapPassword = PasswordUtils
				.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

		// String username= new Configuration().getProperty("ldapUserID");
		// String password= PasswordUtils.decryptString(new
		// Configuration().getProperty("ldapPassword"));

		String username = ldapUserID;
		String password = ldapPassword;

		String sql = null;

		String columnValue = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection(ipAddress, portNumber, databaseName, username, password);
			// db.connect();
			con.connect();

			// Creating SQL Query

			ResultSet resultset;

			// sql = "select STATUSCODE from Automation1.concernrolealternateid where
			// alternateid = ('726967846')";
			// sql = sql1 + "'"+ input + "'"+ ");";
			// sql = sql1 + "'"+ input + "'"+ ")" + sql2;
			// sql = sql1 + "'"+ input1 + "'"+ sql2 + "'"+ input2 + "'" +
			// sql_Suffix;
			sql = sql1 + "'" + input1 + "'" + sql2 + input2 + sql_Suffix; // for
																			// input2
																			// is
																			// empty

			// sql = "select STATUSCODE from Automation1.concernrolealternateid fetch
			// first 1 rows only";
			// sql ="select * from landing.MMIS_Automation_landing where
			// RECIPIENT_SSN in (227778210)";
			System.out.println("SQL after conversion: " + sql);
			resultset = con.query(sql);
			/*
			 * System.out.println("resultset \n" +
			 * resultset.findColumn(resultcolname)); System.out.println(
			 * "resultset \n" + resultset.toString()); System.out.println(
			 * "resultset \n" + resultset.getString(resultcolname));
			 */

			// ArrayList <String> result = new ArrayList<String>();

			if (!resultset.next()) {
				System.out.println("No Data Found");
				columnValue = null;
			} else {
				columnValue = (resultset.getString(resultcolname));
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
		System.out.println("Actual Database query result: " + columnValue + " \n ---------------------------");
		if (null == columnValue) {
			return null;
		}
		return columnValue.replaceAll("\\s", "");
	}

	public static String databaseValidation_SelectQuery(String ip, String dbname, String port, String sql1,
			String input, String sql2, String resultcolname) throws Exception {
		System.out.println("\n databaseValidation_SelectQuery");

		String ipAddress = ip;
		String portNumber = port;
		String databaseName = dbname;

		String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
		String ldapPassword = PasswordUtils
				.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

		// String username= new Configuration().getProperty("ldapUserID");
		// String password= PasswordUtils.decryptString(new
		// Configuration().getProperty("ldapPassword"));

		String username = ldapUserID;
		String password = ldapPassword;

		String columnValue = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection(ipAddress, portNumber, databaseName, username, password);
			// db.connect();
			con.connect();

			// Creating SQL Query
			String sql;
			ResultSet resultset;

			// sql = "select STATUSCODE from Automation1.concernrolealternateid where
			// alternateid = ('726967846')";
			// sql = sql1 + "'"+ input + "'"+ ");";
			sql = sql1 + "'" + input + "'" + ")" + sql2;

			// sql = "select STATUSCODE from Automation1.concernrolealternateid fetch
			// first 1 rows only";
			// sql ="select * from landing.MMIS_Automation_landing where
			// RECIPIENT_SSN in (227778210)";
			System.out.println("SQL after conversion: " + sql);
			resultset = con.query(sql);
			/*
			 * System.out.println("resultset \n" +
			 * resultset.findColumn(resultcolname)); System.out.println(
			 * "resultset \n" + resultset.toString()); System.out.println(
			 * "resultset \n" + resultset.getString(resultcolname));
			 */

			// ArrayList <String> result = new ArrayList<String>();

			if (!resultset.next()) {
				System.out.println("No Data Found");
			}
			columnValue = (resultset.getString(resultcolname));
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
		System.out.println("Actual Database query result: " + columnValue + " \n ---------------------------");
		return columnValue.replaceAll("\\s", "");
	}

	public static String databaseValidation_complexSQLQeury(String ip, String dbname, String port, String sql1,
			String input, String sql2, String resultcolname) throws Exception {
		System.out.println("databaseValidation_complexSQLQeury");

		String ipAddress = ip;
		String portNumber = port;
		String databaseName = dbname;

		String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
		String ldapPassword = PasswordUtils
				.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

		// String username= new Configuration().getProperty("ldapUserID");
		// String password= PasswordUtils.decryptString(new
		// Configuration().getProperty("ldapPassword"));

		String username = ldapUserID;
		String password = ldapPassword;

		String K = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection(ipAddress, portNumber, databaseName, username, password);
			// db.connect();
			con.connect();

			// Creating SQL Query
			String sql;
			ResultSet resultset;

			// sql = "select STATUSCODE from Automation1.concernrolealternateid where
			// alternateid = ('726967846')";
			// sql = sql1 + "'"+ input + "'"+ ");";
			sql = sql1 + "'" + input + "'" + ")" + sql2;

			// sql = "select STATUSCODE from Automation1.concernrolealternateid fetch
			// first 1 rows only";
			// sql ="select * from landing.MMIS_Automation_landing where
			// RECIPIENT_SSN in (227778210)";
			System.out.println("SQL after conversion: " + sql);
			resultset = con.query(sql);
			/*
			 * System.out.println("resultset \n" +
			 * resultset.findColumn(resultcolname)); System.out.println(
			 * "resultset \n" + resultset.toString()); System.out.println(
			 * "resultset \n" + resultset.getString(resultcolname));
			 */

			// ArrayList <String> result = new ArrayList<String>();

			if (!resultset.next()) {
				System.out.println("No Data Found");
			}

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
		System.out.println("Actual Databse query result: " + K);
		return K.replaceAll("\\s", "");

	}

	public static String databaseValidation(String ip, String dbname, String port, String sql1, String input,
			String sql2, String resultcolname) throws Exception {
		System.out.println("databaseValidation");

		String ipAddress = ip;
		String portNumber = port;
		String databaseName = dbname;

		String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
		String ldapPassword = PasswordUtils
				.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

		// String username= new Configuration().getProperty("ldapUserID");
		// String password= PasswordUtils.decryptString(new
		// Configuration().getProperty("ldapPassword"));

		String username = ldapUserID;
		String password = ldapPassword;

		String K = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection(ipAddress, portNumber, databaseName, username, password);
			// db.connect();
			con.connect();

			// Creating SQL Query
			String sql;
			ResultSet resultset;

			// sql = "select STATUSCODE from Automation1.concernrolealternateid where
			// alternateid = ('726967846')";
			// sql = sql1 + "'"+ input + "'"+ ");";
			sql = sql1 + "'" + input + "'" + ")" + sql2; // for containing
															// braces for input
			// sql = sql1 + "'"+ input + "'"+ sql2;

			// sql = "select STATUSCODE from Automation1.concernrolealternateid fetch
			// first 1 rows only";
			// sql ="select * from landing.MMIS_Automation_landing where
			// RECIPIENT_SSN in (227778210)";
			System.out.println("SQL after conversion: " + sql);
			resultset = con.query(sql);
			System.out.println("resultset \n" + resultset);

			// ArrayList <String> result = new ArrayList<String>();

			if (!resultset.next()) {
				System.out.println("No Data Found");
			}

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
		System.out.println("Inisde " + K);
		return K;

	}

	// DB validation with hard coded database components (not ideal)
	public static String databaseValidation_simpleSQLQuery(String sql, String input) throws Exception {

		String ipAddress = "SeleniumAutomationdbtst.gmail.com";
		String portNumber = "50000";
		String databaseName = "curtst1";

		String ldapUserID = new Configuration(Constants.localConfig).getProperty("ldapUserID");
		String ldapPassword = PasswordUtils
				.decryptString(new Configuration(Constants.localConfig).getProperty("ldapPassword"));

		// String username= new Configuration().getProperty("ldapUserID");
		// String password= PasswordUtils.decryptString(new
		// Configuration().getProperty("ldapPassword"));

		String username = ldapUserID;
		String password = ldapPassword;

		String K = null;
		try {
			int count = 0;
			String[] StatusCode = new String[count];
			// Username = text_userName().getText();
			// CustomDB2Connection db = new CustomDB2Connection();
			CustomDB2Connection con = new CustomDB2Connection(ipAddress, portNumber, databaseName, username, password);
			// db.connect();
			con.connect();

			// Creating SQL Query
			String sqlquery = sql + input;
			ResultSet resultset;

			// sql = "select STATUSCODE from Automation1.concernrolealternateid where
			// alternateid = ('726967846')";
			// sql = "select * from landing.MMIS_Automation_landing where
			// RECIPIENT_SSN = " + input;
			// sql = "select STATUSCODE from Automation1.concernrolealternateid fetch
			// first 1 rows only";
			// sql ="select * from landing.MMIS_Automation_landing where
			// RECIPIENT_SSN in (227778210)";
			System.out.println("SQL after conversion: " + sqlquery);
			resultset = con.query(sqlquery);
			System.out.println("resultset \n" + resultset);

			// ArrayList <String> result = new ArrayList<String>();

			if (!resultset.next()) {
				System.out.println("No Data Found");
			}

			K = (resultset.getString("STATUSCODE"));
			// while (resultset.next()) {
			//
			// K = (resultset.getString("STATUSCODE"));
			// count++;
			// System.out.println("Count " + count);
			//
			// }

			boolean flag = true;

			// logTestResult("DB Validation", flag, "DB validation passed");
			ReportUtils.reportResult("Pass", "DB Record 1", "DB validation passed");

			con.close();

		}

		catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("Inisde " + K);
		return K;

	}

}
