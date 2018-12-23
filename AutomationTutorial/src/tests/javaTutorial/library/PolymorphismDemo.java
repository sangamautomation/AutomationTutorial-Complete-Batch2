package tests.javaTutorial.library;
/**
 * Polymorphism Demo
 * 
 * @author Sangam 
 */
public class PolymorphismDemo {
	
	public int add(int a, int b)
	{
		System.out.println("called two arguments");
		int c=a+b;
		System.out.println("value is " +c);
		return c;
	}
	public void add(int a, int b, int c)
	{
		System.out.println("called three arguments");
		int d=a+b+c;
		System.out.println("value is " +d);
		
	}
	public static int add(double a, int b)
	{
		System.out.println("called two arguments");
		double c=a+b;
		System.out.println("value is " +c);
		return (int) c; //type-cast
		
	}

}
