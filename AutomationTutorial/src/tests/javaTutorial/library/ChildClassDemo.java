package tests.javaTutorial.library;
/**
 * Inheritance - Overriding - Child class methods take precedence over parent class methods
 * 
 * @author Sangam 
 */

public class ChildClassDemo extends ParentClassDemo {


	public void mul(){
		System.out.println("Child class - mul");


	}


	public void div(){
		System.out.println("Child class - div");


	}


	// Overriding 
	public void add(){

		System.out.println("Overriding : Child method overrides parent method");
	}


	public static void main(String[] args) {


		ChildClassDemo obj = new ChildClassDemo(); // Access all methods (child & parent)

		ParentClassDemo obj2 = new ChildClassDemo(); //Access only Child class (Selenium ex)
		//ChildClass obj3 = new ParentClass(); //Illegal as child cannot hold parent
		ParentClassDemo obj4 = new ParentClassDemo(); // Access only Parent class methods

		obj.add(); //Overriding

		obj.sub();
		obj.mul();
		obj.div();

		obj2.add(); //Overriding
		obj4.sub();

		// WebDriver driver = new ChromeDriver();

	}

}
