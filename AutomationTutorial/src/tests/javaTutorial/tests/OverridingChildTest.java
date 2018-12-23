package tests.javaTutorial.tests;
/**
 * OverridingChild Test
 * 
 * @author Sangam 
 */
public class OverridingChildTest extends OverridingParent  {

	int p,q;

	void showThis(){
		p=4;
		q=6;
	}

	int  add(int x, int y){


		System.out.println("The sum in child class = "+(x+y));
		return x+y;

	}

	public static void main(String args[]){

		OverridingParent p = new OverridingParent();
		OverridingChildTest c = new OverridingChildTest();


		c.add(1, 2);

	}


}
