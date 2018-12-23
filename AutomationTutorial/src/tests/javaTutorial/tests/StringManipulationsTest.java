package tests.javaTutorial.tests;
/**
 * String Test
 * 
 * @author Sangam 
 */
public class StringManipulationsTest {

	public static void main(String[] args) {

		System.out.println("------String Manipulations--------------------------");


		String firstName = "John";
		String lastName = "Doe";

		//	firstName = "Russel";

		String celebrity = "Shah Rukh Khan";
		String lastNameOfCeleb = celebrity.substring(10);

		System.out.println("Full name is "+ firstName +" "+lastName);

		System.out.println("Last name of "+celebrity+ " is "+ lastNameOfCeleb); 

		int lengthOfName = celebrity.length();
		System.out.println("The lengh of "+celebrity+" = "+lengthOfName);

		int index = celebrity.indexOf('h');
		System.out.println("The position of h in "+celebrity + " = "+index);
	}

}
