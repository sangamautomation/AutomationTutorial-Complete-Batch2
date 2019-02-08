package tests.javaTutorial.tests;

import tests.javaTutorial.library.OverridingChildClassDemo;
import tests.javaTutorial.library.OverridingParentClassDemo;

public class OverridingTest {

	public static void main(String[] args) {

		OverridingChildClassDemo obj = new OverridingChildClassDemo(); // Access all methods (child & parent)

		OverridingParentClassDemo obj2 = new OverridingChildClassDemo(); //Access only Child class (Selenium ex)
		//ChildClass obj3 = new ParentClass(); //Illegal as child cannot hold parent
		OverridingParentClassDemo obj4 = new OverridingParentClassDemo(); // Access only Parent class methods

		obj.add(); //Overriding

		obj.sub();
		obj.mul();
		obj.div();

		obj2.add(); //Overriding
		obj4.sub();

		obj.add(1, 2);

		// WebDriver driver = new ChromeDriver();


	}

}
