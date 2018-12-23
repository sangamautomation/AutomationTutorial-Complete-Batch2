package tests.javaTutorial.tests;

import tests.javaTutorial.library.ConditionsDemo;
/**
 * Conditional Statements Test
 * 
 * @author Sangam 
 */
public class ConditionsTest {

	public static void main(String[] args) {
		// Logic based programs
		ConditionsDemo con= new ConditionsDemo();

		con.min(2, 5);
		con.max(8, -3);
		int minimum = con.min(2, 3, 4);
		int myNumber = minimum + 2;
		System.out.println("My special number = "+ myNumber);

		con.max(23, 34, 44);

		ConditionsDemo.maxMinOfNumber(34, 2323);
		ConditionsDemo.maxMinNumbers(134.343, 134.343);
		ConditionsDemo.maxMinNumbers(121212121, 34343434);
		ConditionsDemo.maxMinOfNumber(1, 1);

		con.weekDays(7);
		String monthInfo = con.month("January");
		System.out.println(monthInfo);

		//con.displayNumbers(150);

		ConditionsDemo cond= new ConditionsDemo();
		int number1=5, number2=10, number3 = 15;

		cond.min(number1, number2);


		new ConditionsDemo().max(50, 457);//non-static calling

		cond.min(number1, number2, number3);

		new ConditionsDemo().max(15,20,50);
		cond.max(number1, number2, number3);

		int numbers[]={1, 4, 3, 9, 10};
		con.greaterNumberInArray(numbers);
	}

}
