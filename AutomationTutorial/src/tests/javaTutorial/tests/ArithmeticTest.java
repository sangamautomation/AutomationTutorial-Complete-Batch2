package tests.javaTutorial.tests;

import tests.javaTutorial.library.ArithmeticDemo;
import tests.javaTutorial.library.SwitchCaseHandler;

/**
 * Arithmetic Test
 * 
 * @author Sangam 
 */
public class ArithmeticTest {

	public static void main(String[] args) {

		//Instantiation

		ArithmeticDemo obj = new ArithmeticDemo();

		//Addition
		System.out.println("\n ################# Addition ############## \n");
		int sum =	obj.add(7, 9);
		System.out.println("The sum = "+ sum);	
		obj.add(1, 3);
		obj.add(12121, 1212123);
		obj.add(0, 2312312);
		obj.add(34324234, 1312312);
		obj.add(-123123, 3423423);
		obj.add(-3233424, -34345345);

		//Difference
		System.out.println("\n ################# Difference ############## \n");

		obj.subtract(5, 6);
		obj.subtract(23232, 3434);
		obj.subtract(2232, 3434234);
		obj.subtract(0, 343);
		obj.subtract(-334, -343);

		//Product

		System.out.println("\n ################# Product ############## \n");

		obj.product(1, 3);
		obj.product(3, 6);
		obj.product(343, 3434);
		obj.product(232, -3434);
		obj.product(-343, -3434);
		obj.product(0, 3434);


		//Division

		System.out.println("\n ################# Division ############## \n");

		obj.divide(4, 2);
		obj.divide(344234, 34);
		obj.divide(232, 12);
		obj.divide(121, - 43);
		obj.divide(0,23);
		//m.divide(343, 0);

		//Overloading method
		obj.divide(25.5, 5.5);


		//Multiplication Table
		obj.multiTable(25);
		obj.multiTable(100, 20); // Multiplication table of tables 

		//------------------------------------------------

		//Static calling (by class name)


		//	Math.moduloOfNumbers(6, 4);

		obj.numbersDisplay();

		String x = "A quick brown fox jumps over the lazy dog";
		obj.perimeterOfTriangle(5, 7, 11);
		obj.perimeterOfRectangle(9, 13);
		obj.subtraction (30, 50);
		obj.display(50);
		obj.countLetters(x);

		SwitchCaseHandler.dayOfTheWeek(1);//Calling method in a static way (static means only one object of class)
		SwitchCaseHandler w = new SwitchCaseHandler();//Calling method in a non-static way (multiple object of class can be created)
		String dayName= w.dayOfTheWeek("2");
		System.out.println("New day of the week is "+ dayName );


		try {
			obj.simpleInterest(1000.50, 4.8, 5.5);
		} catch (Exception e) {
			e.printStackTrace();
		}

		obj.swap(2, 4);


	}

}
