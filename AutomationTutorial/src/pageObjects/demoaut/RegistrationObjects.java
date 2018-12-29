package pageObjects.demoaut;

public class RegistrationObjects {
	
	public static final String link_Register = "//a[contains(text(),'REGISTER')]";
	public static final String textBox_FirstName = "//input[contains(@name,'firstName')]";
	public static final String textBox_LastName = "//input[contains(@name,'lastName')]";
	public static final String textBox_Phone = "//input[contains(@name,'phone')]";
	public static final String textBox_Email = "//input[contains(@name,'userName')]";
	public static final String textBox_Address1 = "//input[contains(@name,'address1')]";
	public static final String textBox_Address2 = "//input[contains(@name,'address2')]";

	public static final String textBox_City = "//input[contains(@name,'city')]";
	public static final String textBox_StateProvince = "//input[contains(@name,'state')]";
	public static final String textBox_PostalCode = "//input[contains(@name,'postalCode')]";
	public static final String dropdown_Country = "//select[contains(@name,'country')]";

	// User Information
	public static final String textBox_UserName = "//input[contains(@name,'email')]";
	public static final String textBox_Password = "//input[contains(@name,'password')]";
	public static final String textBox_ConfirmPassword = "//input[contains(@name,'confirmPassword')]";

	public static final String button_Submit = "//input[contains(@name,'register')]";
	
	//Confirmation
	public static final String text_Note ="//b[contains(text(),'Note:')]";
	
 }
