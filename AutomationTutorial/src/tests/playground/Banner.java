package tests.playground;

import java.util.Scanner;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.runner.DataDrivenTestRunner.EasyTestRunner;

public class Banner {
	
	@DataLoader
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String word;

		System.out.println("Please print something:");
		word = input.nextLine();

	}
	public static void H()
	{
		System.out.println();
		System.out.println("** **");
		System.out.println("** **");
		System.out.println("******");
		System.out.println("** **");
		System.out.println("** **");
	}

	public static void E()
	{
		System.out.println();
		System.out.println("******");
		System.out.println("**    ");
		System.out.println("******");
		System.out.println("**    ");
		System.out.println("******");
	}

	public static void L()
	{
		System.out.println();
		System.out.println("*     ");
		System.out.println("*     ");
		System.out.println("*     ");
		System.out.println("*     ");
		System.out.println("******");
	}

	public static void O()
	{
		System.out.println();
		System.out.println("******");
		System.out.println("*    *");
		System.out.println("*    *");
		System.out.println("*    *");
		System.out.println("******");
	}

	public static void W()
	{
		System.out.println();
		System.out.println("*    *");
		System.out.println("*    *");
		System.out.println("* ** *");
		System.out.println("* ** *");
		System.out.println("** **");
	}

	public static void R()
	{
		System.out.println();
		System.out.println("***** ");
		System.out.println("** **");
		System.out.println("***** ");
		System.out.println("** **");
		System.out.println("** **");
	}

	public static void D()
	{
		System.out.println();
		System.out.println("**** ");
		System.out.println("*   * ");
		System.out.println("*    *");
		System.out.println("** * ");
		System.out.println("**** ");
	}

	public static void d() {

		System.out.println();

		System.out.println("*******");

		System.out.println("*     *");

		System.out.println("*     *");

		System.out.println(" *   * ");

		System.out.println(" *** ");

		System.out.println();

	}


	public static void blank() {

		System.out.println();

		System.out.println();

		System.out.println();

	}




}  