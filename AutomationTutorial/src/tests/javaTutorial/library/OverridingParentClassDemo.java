package tests.javaTutorial.library;
/**
 * Inheritance Demo
 * 
 * @author Sangam 
 */
public class OverridingParentClassDemo {


	int x;
	int y;
	public void setup(){
		x = 10;
		y=20;
		System.out.println("x="+x);
	}

	int add(int x, int y){

		System.out.println("The sum = "+(x+y));
		return x+y;

	}


	public void add(){
		System.out.println("Parent class - add");

	}


	public void sub(){
		System.out.println("Parent class - sub");

	}	  
}
