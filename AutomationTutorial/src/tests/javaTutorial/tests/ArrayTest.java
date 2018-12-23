package tests.javaTutorial.tests;
/**
 * Array Test
 * 
 * @author Sangam 
 */
public class ArrayTest
{
	public static void main(String[] args)
	{

		//Integer array of 1-Dimension
		// Array initialization
		int b = 7;
		int a[] ={5,10,2,1,5};

		/*	Length of a = 5;

		a[0] = 5;
		a[1] = 10;
		a[2] = 2;
		a[3] = 1;
		a[4] = 5;

		 */


		int sum=0;



		for(int counter=0;counter<a.length;counter++)
		{
			sum=sum+a[counter];
		}

		System.out.println("The sum of total numbers is " +sum);



		// String Array of 2 -Diminsions


		//String names[][]=new String[2][2];
		String names[][]={{"Madhu","Siri"},{"Sangam","Ya"}};

		/*	names[0][0]="Madhu";
		names[0][1]="Siri";
		names[1][0]="Sangam";
		names[1][1]="Ya";
		 */



		for(int i=0;i<names.length;i++)
		{
			for(int j=0;j<names.length;j++)
			{	
				System.out.print(names[i][j]);
				System.out.print(" ");
			}
			System.out.println();		  

		}


		// More examples of arrays

		String name[]={"abc","def","zyz"};
		System.out.println("the length of array is " +name.length);
		System.out.println("the value in second array is " +name[1]);

		int x[][] =new int[2][3];
		x[0][0] = 2;

		int y[] = {2,3,4,5,6, 7, 8};

		int z[][] = {{1,2},{3,4}};

		for (int i = 0; i < z.length; i++) {
			for (int k = 0; k < z.length; k++) {
				System.out.print(z[i][k]);
				System.out.print(" ");

			}
			System.out.println();
		}

		System.out.println("exit loop");


	}

}

