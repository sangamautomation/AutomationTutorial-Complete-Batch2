
package tests.javaTutorial.tests;

import java.util.Scanner;
/**
 * Scanner Test
 * 
 * @author Sangam 
 */
public class ScannerTest
{
	public static void interest()
	{
		double amount, rate, term, CI;
		Scanner s=new Scanner(System.in);
		System.out.println("Please enter the amount, rate and term");
		amount=s.nextInt();
		rate=s.nextInt();
		term=s.nextInt();
		CI=amount*Math.pow(1+rate/100,term);
		System.out.println("The compound interest is " +CI);
	} 

	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter your name here:");
		String temp=s.nextLine();
		System.out.println("the name you have entered is " +temp);

		// Calculate Average
		int total=0, temp1, counter=0;
		float average;
		Scanner s1=new Scanner(System.in);
		System.out.println("Enter five numbers:");

		while(counter<5)
		{ 
			temp1=s1.nextInt();
			total=total+temp1;
			counter++;

		}
		average=total/5;
		System.out.println("The average is " +average);

		interest();

	}
}
