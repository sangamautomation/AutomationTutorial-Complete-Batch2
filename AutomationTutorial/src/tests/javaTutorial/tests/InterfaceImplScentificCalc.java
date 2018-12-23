package tests.javaTutorial.tests;
/**
 * Interface Demo
 * 
 * @author Sangam 
 */
public class InterfaceImplScentificCalc implements InterfaceMainCalc{

	int x1 = 22;
	@Override
	public void add() {
		System.out.println("This is sci calc - add");

	}

	/*public void click(WebDriver driver){
		driver.findElement(By.xpath("\\input[@id='submit'))").click();
	}*/

	@Override
	public void sub() {
		System.out.println("This is sci calc - sub");		

	}
	public void mul() {
		System.out.println("This is sci calc - mul");		

	}

	@Override
	public void div(int x, int y){
		System.out.println(x/y);
	}

	public static void main(String[] args){
		InterfaceImplScentificCalc cal = new InterfaceImplScentificCalc(); // can access all methods
		InterfaceMainCalc cal2 = new InterfaceImplScentificCalc(); // can access onlly interface (WebDriver example) methods
		//	MainCalc cal3 = new MainCalc();//Cannot create instantiate interface
		//	ScentificCalc cal4 = new MainCalc();//Cannot create instantiate interface & child cannot hold parent

		cal.add();
		cal.sub();
		cal.mul();


		cal2.add();
		cal2.sub();
		//	cal2.mul();//cannot access implementing class method


		//	WebDriver driver = new FirefoxDriver();//Selenium usage example


		//	cal.x = 111;//final field value cannot be changed

		System.out.println(cal2.x);
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub

	}

}
