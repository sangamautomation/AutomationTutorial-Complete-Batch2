package tests.javaTutorial.tests;

import tests.javaTutorial.library.ConstructorDemo;
/**
 * Constructor Test
 * 
 * @author Sangam 
 */
public class ConstructorTest {

	public static void main(String[] args) {

		//Default Constructor Call at the time of instantiation

		ConstructorDemo c = new ConstructorDemo();
		c.display();

		//Param Constructor Call

		ConstructorDemo c1 = new ConstructorDemo("Sangam", "E", "Yad", 35, 60000.58);
		c1.display();

		// Setter Call

		c1.setMiddleName("Esh");
		c1.display();

		//Getter Call
		System.out.println("My changed middle name is : "+ c1.getMiddleName());


	}

}
