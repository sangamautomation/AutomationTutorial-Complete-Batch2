/**
 * 
 */
package tests.javaTutorial.tests;

/**
 * OverridingParent Test
 * 
 * @author Sangam 
 */
public class OverridingParent {

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


}
