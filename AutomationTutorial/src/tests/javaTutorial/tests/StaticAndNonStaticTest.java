package tests.javaTutorial.tests;

import tests.javaTutorial.library.NonStaticDemo;
import tests.javaTutorial.library.StaticDemo;
/**
 * Static & Non-Static Test
 * 
 * @author Sangam 
 */
public class StaticAndNonStaticTest {


	public static void main(String[] args) {

		StaticDemo sc = new StaticDemo();
		NonStaticDemo nsc = new NonStaticDemo();

		// Static method can be accessed in a static way (using class name)-OK

		StaticDemo.myStaticMethod(); //Ideal way 

		// Static method can be accessed in a non-static way (using instance name)-OK

		sc.myStaticMethod(); //Non-ideal way


		// Non-static method can be accessed in non-static way - OK

		nsc.myNonStaticMethod(); //Ideal way

		// Non-static method cannot be accessed in a static way - Not OK

		//	NonStaticClass.myNonStaticMethod(); //Not allowed















	}

}
