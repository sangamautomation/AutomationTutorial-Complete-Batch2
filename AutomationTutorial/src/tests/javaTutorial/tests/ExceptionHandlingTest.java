package tests.javaTutorial.tests;
/**
 * Exception Handling Test
 * 
 * @author Sangam 
 */
public class ExceptionHandlingTest {

	public static void divide(int x, int y)
	{
		System.out.println("The quotient = " + (x/y)); // + means concatenation of two different data types / when there is a string involved 
	}

	public static void main(String[] args) {


		try
		{
			System.out.println("Try block:");
			divide(4, 0);

		}
		catch(Exception e)
		{
			System.out.println("Catch block:");

			e.printStackTrace(); // Not throwing
			System.out.println(e.toString());
			throw e; //throwing

		}

		finally{
			System.out.println("Finally block:");

		}

		System.out.println("After finally block...");

	}

}
