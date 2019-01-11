package infrastructure;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import data.Constants;
import utils.CustomDB2Connection;
import utils.PasswordUtils;
import utils.PropertyUtils;
import utils.ReportUtils;

//@author Sangam

public class DatabaseInteractions {
	
	public void database_Generic(){
		
		try {
			String dbrecordExpected = "RS001";
			String[] statusCode = new String[30];
			String sql1,sql2,sql3,sql4;
			
			
			String sql= "select renewalstatus  from schema.renewalstatustable where caseid = (select integratedcaseid from schema.caseholder wehre caseref= '123')";
			
			String sqlstate1 = "select renewalstatus from schema.renewalstatustable where caseid =";
			String sqlstate2 = " Order by duedate desc";
			String sqlstate3 = " fetch first 3 rows only";
			
		//	databaseValidation_SelectSQL(ip, dbname, port, sql1, input1, sql2, input2, sql_Suffix, resultcolname);
			
			
			
			
		} catch (Exception e) {
 		}
	}
	
	
	public static String databaseValidation_SelectSQL(String ip, String dbname, String port, String sql1, String input1, String sql2, String input2, String sql_Suffix, String resultcolname) throws Exception
	{
		String  ipAddress = ip;
		String portNumber = port;
		String databaseName = dbname;
		
	String username = PropertyUtils.propertyFile_Read(Constants.path_PropertyFile_config, "userid"); //automation
		
		String password_Encrypted = PropertyUtils.propertyFile_Read(Constants.path_PropertyFile_config, "password"); // I/mTPwYhe6w4srmtx2x/Tg\=\= 
		String password =PasswordUtils.decryptString(password_Encrypted);
		
		String sql = null;
		String columnValue = null;
		
		try{
			
			int count = 0;
			String[] StatusCode = new String[count];
			
			CustomDB2Connection con = new CustomDB2Connection(ipAddress, portNumber, databaseName, username, password);
			
			con.connect();
			
			// Create SQL Query
			ResultSet resultset;
			
			sql = sql1+"'"+input1+"'"+sql2+"'"+input2+"'"+sql_Suffix;
			
			System.out.println("SQL after conversion: "+ sql);
			
			
			resultset = con.query(sql);
			
			if(!resultset.next()){
				System.out.println("No Data Found!");
				columnValue = null;
			}
			else{
				columnValue = resultset.getString(resultcolname);
				
			}
			
			
			con.close();
 			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("Actual Database query result: "+ columnValue + " \n ----------------------");
		
		ReportUtils.reportResult("Done", "Database validation", "Query executed: \n"+sql + "\n Actual Database query Result : " + columnValue);
		if (columnValue == null)
			return null;
		
		return columnValue.replaceAll("\\s", "");
		
		
	}

	
	// Database Validation returning whole row as Array of Strings
		public static String[] databaseValidation_Select(String ip, String dbname, String port,
				String sql1, String input1, String sql2, String input2, String sql_Suffix) throws Exception {
			System.out.println("\n databaseValidation_Select");

			String ldapUserID= new Configuration("D://AutomationProjects/config.properties").getProperty("ldapUserID");
			String ldapPassword= PasswordUtils.decryptString(new Configuration("D://AutomationProjects/config.properties").getProperty("ldapPassword"));

			//String username= new Configuration().getProperty("ldapUserID");
			//String pwd= PasswordUtils.decryptString(new Configuration().getProperty("ldapPassword"));

			String username = ldapUserID;
			String password = ldapPassword;

			int j = 0;
			//		SELECT trim(AutomationCASENUMBER) FROM Automation1.SeleniumVLPRESPONSE where responsecode='SeleniumVLP004' and recordstatus='RST1' ORDER BY LastWritten DESC FETCH FIRST 1 ROW ONLY
			String  sql = sql1 + "'"+ input1 +  "'"+ sql2 + "'"+ input2 + "'" + sql_Suffix;
			System.out.println("SQL Query after conversion: "+ sql);

			CustomDB2Connection con = new CustomDB2Connection(ip, port, dbname, username, password);
			//	String[] columnValue = new String[30];

			ResultSet resultset;
			con.connect();
			resultset = con.query(sql);
			ResultSetMetaData rsmd = resultset.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			String[] columnValue = new String[numberOfColumns];

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
					System.out.println("columnValue" +i+ " = " + columnValue[j] );
					j++;

				}

			}

			con.close();

			System.out.println("End of databaseValidation_Select \n ---------------------------");
			return columnValue;

		}
		
		public static int databaseValidation_UpdateSQL(String ip, String dbname, String port, String sql_Update, String setValue, String sql1, String input1, String sql2, String input2) throws Exception
		{
			System.out.println(" \n databaseValidation_UpdateSQL");

			String ipAddress = ip;
			String portNumber = port;
			String databaseName = dbname;

			String ldapUserID= new Configuration("D://AutomationProjects/config.properties").getProperty("ldapUserID");
			String ldapPassword= PasswordUtils.decryptString(new Configuration("D://AutomationProjects/config.properties").getProperty("ldapPassword"));

			//String username= new Configuration().getProperty("ldapUserID");
			//String password= PasswordUtils.decryptString(new Configuration().getProperty("ldapPassword"));

			String username = ldapUserID;
			String password = ldapPassword;

			int updateStatus = 0;

			String columnValue = null;
			try {
				int count =0;
				String[] StatusCode= new String[count];
				// Username = text_userName().getText();
				//CustomDB2Connection db = new CustomDB2Connection();
				CustomDB2Connection con = new CustomDB2Connection(ipAddress,portNumber,databaseName,username,password);
				//db.connect();
				con.connect();

				// Creating SQL Query
				String sql;
				ResultSet resultset = null;

				//                sql = "select STATUSCODE from Automation1.concernrolealternateid where alternateid  = ('726967846')";
				//		sql = sql1 + "'"+ input +  "'"+  ");";
				//sql = sql1 + "'"+ input +  "'"+  ")" + sql2;

				//	sql = sql_Update + "'"+ setValue +  "'"+   sql1 + "'"+ input1 + "'"  + sql2 + "'"+ input2 + "'" ;
				sql = sql_Update + "'"+ setValue +  "'"+   sql1 + "'"+ input1 + "'"  + sql2 + input2 ;

				//                sql = "select STATUSCODE from Automation1.concernrolealternateid fetch first 1 rows only";
				//                sql ="select * from landing.MMIS_Automation_landing where RECIPIENT_SSN in (227778210)";
				System.out.println("SQL after conversion: "+ sql);
				//	 con.query(sql);
				updateStatus = con.query_update(sql);
				//	updateStatus = con.query_updated(sql);

				// 	con.query_updateSQL("update Automation1.concernrolealternateid set statuscode = 'RST1' where alternateid  = ('726967846')");


				//	con.commit();  
				// con.setAutoCommit(true);
				//	con.query_update("Update Automation1.SeleniumVLPRESPONSE set firstname = 'sangama' where responsecode = 'SeleniumVLP004' and recordstatus = 'RST1'");
				//		resultset = con.query(sql);
				//		System.out.println("resultset \n" + resultset.findColumn(resultcolname));
					System.out.println("resultset \n" + resultset.toString());
					String resultcolname = null;
					System.out.println("resultset \n" + resultset.getString(resultcolname));
				 

				//                ArrayList <String> result = new ArrayList<String>();

						if(!resultset.next())
				{
					System.out.println("No Data Found");
				}
				columnValue = (resultset.getString(resultcolname));
				 		//                  while (resultset.next()) {
				//
				//                        K = (resultset.getString("STATUSCODE"));
				//                        count++;
				//                        System.out.println("Count " + count);
				//
				//                  }             

				con.close();
			} 

			catch (Exception e) {
				e.printStackTrace();
			}

			if(updateStatus>0)
				System.out.println("Update query is successful.  \n ---------------------------");
			else
				System.out.println("Update query is NOT successful.  \n ---------------------------");

			return updateStatus;
		}

		
		// Compares two database results and reports
		public static void Database_Compare(String ExpectedDBResult,
				String ActualDBResult, String ReportComment) throws Exception {
			System.out.println("Database_Compare");
			if (ExpectedDBResult == ActualDBResult
					|| ExpectedDBResult.equals(ActualDBResult)) {
				System.out.println(ReportComment);
				ReportUtils.reportResult("Pass", "DB Record 1", ReportComment+" The validation is successful!");

				System.out.println(ReportComment);
			} else {
				ReportUtils.reportResult("Fail", "DB Record 1", ReportComment+" The validation is NOT successful!");

			}

		} 
	
	
}
