package utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ibm.db2.jcc.DB2Driver;

import data.Constants;
//import oracle.jdbc.driver.OracleDriver;

/**
 * @author Sangam
 *CustomDB2Connection - Base Database Connectivity
 */

public class CustomDB2Connection {
	
	
	private Connection connection = null;
	private ResultSet resultset = null;
	private Statement statement = null;
	
	private String ipAddress = "localhost";
	private String portNumber = "1521";
	private String databaseName ="db1";
	
	// default constructor - initialize variables
	
	public CustomDB2Connection(){
		String env = PropertyUtils.propertyFile_Read(Constants.path_PropertyFile_config, "env");
 		switch (env) {
		case "TST1":
			  ipAddress = "localhost";
			  portNumber = "1521";
			  databaseName ="db1";
			break;
		case "DEV1":
			  ipAddress = "localhost";
			  portNumber = "1522";
			  databaseName ="db2";
			break;
		default:
			 ipAddress = "localhost";
			  portNumber = "1525";
			  databaseName ="db5";
			break;
		}
	}
	
	// parameterized constructor - initialize variables
	
	public CustomDB2Connection(String pipAddress, String pportNumber, String pdatabaseName, String pUserName, String pPassword){
		this.ipAddress = pipAddress;
		portNumber = pportNumber;
		databaseName = pdatabaseName;

}
	
	
	// connect to database
	
	public void connect() throws Exception{
		
		String username = PropertyUtils.propertyFile_Read(Constants.path_PropertyFile_config, "userid"); //automation
		
		String password_Encrypted = PropertyUtils.propertyFile_Read(Constants.path_PropertyFile_config, "password"); // I/mTPwYhe6w4srmtx2x/Tg\=\= 
//		String password =PasswordUtils.decryptString(password_Encrypted);
		
		try {
			
		 	Driver driver = new DB2Driver();
		//	Driver driver = new OracleDriver();
		//	 Driver driver = new MySQLDriver();
			
			DriverManager.registerDriver(driver);
			System.out.println("Driver loaded successfully!");
			
			String str = "jdbc:db2://"+ipAddress+":"+portNumber+"/"+databaseName; // "jdbc:db2://109.10.34.8:60000/coredb"
			
			connection = DriverManager.getConnection(str, username,"Password" ); // Connect to db
//			conn.ConnectionString = 
//		              "Driver=OraOLEDB.Oracle;" + 
//		              "Data Source=ServerName;" + 
//		              "User id=UserName;" + 
//		              "Password=Secret;"; 
//			
			
			
			if(connection==null)
				System.out.println("Connection failed!");
			
			connection.setAutoCommit(true);
			System.out.println("Successfully connected to database...");
			
			
		} catch (Exception e) {
			e.printStackTrace();
 		}
		
		
	}
	
	// Construct SELECT query, submit the query to database and returns resultset
	
	public ResultSet query(String arg) throws SQLException{
		
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		resultset  = statement.executeQuery(arg); // SELECT Statements only ( select firstname, lastname from schemaname.databasename where age > 30;)
		System.out.println("Query executed "+ arg);
		
 		return resultset;
 	}
	
	// Construct UPDATE query, submit the query to database and returns success(1)/failure(-1/0) index

	public int query_update (String arg) throws SQLException{
		
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	 
		int i = statement.executeUpdate(arg); // UPDATE Statements only
 		connection.commit();
		connection.setAutoCommit(true);
		return i;
		
	}
	
	// Close the connection to database
	public void close() throws SQLException{
		connection.close();
		System.out.println("The database connection is closed successfully!");
		connection = null;
		
	}
}