package tests.javaTutorial.tests;

import tests.javaTutorial.library.EnumDemo.Operation;
import tests.javaTutorial.library.EnumDemo.States;
import tests.javaTutorial.library.EnumDemo.UserStatus;
/**
 * Enum Test
 * 
 * @author Sangam 
 */
public class EnumTest {

	public static void main(String[] args) {

		//Printing
		System.out.println(UserStatus.ACTIVE);

		//Calling
		double result = Operation.PLUS.calculate(1, 2);
		System.out.println(result); //3.0

		//Looping

		for (UserStatus status : UserStatus.values()) {
			System.out.println(status);
		}

		// States full form

		String op = States.AR.fullForm("AR");
		System.out.println(op);
	}

}
