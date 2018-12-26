package pageObjects.toolsqa;

public class Toolsqa {

	//Fields for constant XPaths
	public static final String table_TableHeader1 = "//table/tbody/tr[1]/th";
	public static final String table_TableData11 = "//div[@id='content']//table//tbody/tr[1]/td[1]";
	public static final String table_TableData12 = "//div[@id='content']//table//tbody/tr[1]/td[2]";
	public static final String table_TableData13 = "//div[@id='content']//table//tbody/tr[1]/td[3]";
	public static final String table_TableData14 = "//div[@id='content']//table//tbody/tr[1]/td[4]";
	public static final String table_TableData15 = "//div[@id='content']//table//tbody/tr[1]/td[5]";
	public static final String table_TableData16 = "//div[@id='content']//table//tbody/tr[1]/td[6]";

	public static final String table_TableHeader2 = "//table/tbody/tr[1]/th";
	public static final String table_TableData21 = "//div[@id='content']//table//tbody/tr[2]/td[1]";
	public static final String table_TableData22 = "//div[@id='content']//table//tbody/tr[2]/td[2]";
	public static final String table_TableData23 = "//div[@id='content']//table//tbody/tr[2]/td[3]";
	public static final String table_TableData24 = "//div[@id='content']//table//tbody/tr[2]/td[4]";
	public static final String table_TableData25 = "//div[@id='content']//table//tbody/tr[2]/td[5]";
	public static final String table_TableData26 = "//div[@id='content']//table//tbody/tr[2]/td[6]";

	public static final String table_TableHeader3 = "//table/tbody/tr[3]/th";
	public static final String table_TableData31 = "//div[@id='content']//table//tbody/tr[3]/td[1]";
	public static final String table_TableData32 = "//div[@id='content']//table//tbody/tr[3]/td[2]";
	public static final String table_TableData33 = "//div[@id='content']//table//tbody/tr[3]/td[3]";
	public static final String table_TableData34 = "//div[@id='content']//table//tbody/tr[3]/td[4]";
	public static final String table_TableData35 = "//div[@id='content']//table//tbody/tr[3]/td[5]";
	public static final String table_TableData36 = "//div[@id='content']//table//tbody/tr[3]/td[6]";

	public static final String table_TableHeader4 = "//table/tbody/tr[4]/th";
	public static final String table_TableData41 = "//div[@id='content']//table//tbody/tr[4]/td[1]";
	public static final String table_TableData42 = "//div[@id='content']//table//tbody/tr[4]/td[2]";
	public static final String table_TableData43 = "//div[@id='content']//table//tbody/tr[4]/td[3]";
	public static final String table_TableData44 = "//div[@id='content']//table//tbody/tr[4]/td[4]";
	public static final String table_TableData45 = "//div[@id='content']//table//tbody/tr[4]/td[5]";
	public static final String table_TableData46 = "//div[@id='content']//table//tbody/tr[4]/td[6]";

	public static final String textbox_FirstName = "//input[contains(@name,'firstname')]";
	public static final String textbox_LastName = "//input[contains(@name,'lastname')]";
	public static final String radiobutton_SexMale = "//input[contains(@name,'sex') and contains(@value,'Male')]";
	public static final String radiobutton_SexFemale = "//input[contains(@name,'sex') and contains(@value,'Female')]";
	public static final String checkbox_ManualTester = "//input[contains(@value,'Manual Tester')]";
	public static final String checkbox_AutomationTester = "//input[contains(@value,'Automation Tester')]";
	public static final String link_Photo = "//input[@id='photo' and @name='photo']";
	public static final String link_Download = "//a[text()='Test File to Download']";
	
	public static final String checkbox_Tool = "//input[contains(@value,'Selenium Webdriver')]";
	public static final String dropdown_Continents = "//select[contains(@id,'continents')]";
	public static final String dropdown_SeleniumCommands = "//select[contains(@id,'selenium_commands')]";
	public static final String image_Logo = "//div[@class='branding']/a/img[contains(@src,'http://20tvni1sjxyh352kld2lslvc.wpengine.netdna-cdn.com/wp-content/uploads/2014/08/Toolsqa.jpg')]";

	public static final String textlabel_Text1 = "//label/span[contains(@class,'bcd')]";
	
	
	//Methods for Dynamic XPaths
	public static final String table_TableAll(int i, int j){
		return "//div[@id='content']//table//tbody/tr["+i+"]/td["+j+"]";
	}
	
	public static final String radiobutton_Experience(int i){
		return "//input[contains(@name,'exp') and contains(@value,'"+i+"')]";
	}
}
