package tests.javaTutorial.tests;
/**
 * String Test
 * 
 * @author Sangam 
 */
public class StringManipulationsTest {

	public static void StringUtil(String fn, String mn, String ln, int age){
		System.out.println(fn + " "+ mn + " "+ ln + "'s age is "+ age +" years!");
	}


	public static void main(String[] args) {

		System.out.println("------String Manipulations--------------------------");



		String x  = "John Doe Robertson"; //Delimiter = <space>


		int lenghOfString = x.length();


		System.out.println("The lengh of the string x = " + lenghOfString); // + Concatenation Operator
		System.out.println("The trimmed string_" + x.trim()+"_");
		System.out.println("The Substring starting from index 5="+ x.substring(5));
		System.out.println("The Substring starting from index 5 & ending at 7="+ x.substring(5, 8));
		System.out.println("The Character at the index 5="+ x.charAt(5));

		System.out.println("The index of o ="+ x.indexOf('o'));
		System.out.println("The index of R : "+ x.indexOf('R'));


		String[] partOfString = x.split(" ");

		System.out.println("The First Name = "+ partOfString[0]);
		System.out.println("The middler Name = "+ partOfString[1]);
		System.out.println("The last Name = "+ partOfString[2]);


		System.out.println("The name in lower case = " + x.toLowerCase());
		System.out.println("The name in uppe case = " + x.toUpperCase());
		System.out.println("The replaced string is  = " + x.replaceAll(" ", "_"));





		StringUtil("Shah", "Rukh", "Khan", 55);



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
