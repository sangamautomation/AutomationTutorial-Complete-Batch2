package tests.javaTutorial.tests;
/**
 * Debugging Test
 * 
 * @author Sangam 
 */
public class DebuggingTest {

	public static void main(String[] args) {
		String totalPrice = "$1153434 USD";
		int totalPriceLastIndex = totalPrice.indexOf(' ');
		totalPrice = totalPrice.substring(1, totalPriceLastIndex); 
		System.out.println("total Price = "+ totalPrice);
		
		//Extract first name from full name
		String name = "Sangam E Yadagiri";
		int index = name.indexOf(' ');
		name = name.substring(0, index);
		System.out.println("First Name = "+name);
		
	}

}
