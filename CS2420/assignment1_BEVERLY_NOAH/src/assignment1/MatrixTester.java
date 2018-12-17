/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment1;

public class MatrixTester {
	public static void main(String[] args)
	{			
		Matrix M1 = new Matrix(new int[][]
		                       {{1, 2, 3},
							   {2, 5, 6}});
		
		Matrix M2 = new Matrix(new int[][]
		                       {{4, 5},
							   {3, 2},
							   {1, 1}});
		
		// this is the known correct result of multiplying M1 by M2
		Matrix referenceMultiply = new Matrix(new int[][]
		                                      {{13, 12},
								              {29, 26}});

		
		/* 
		 * Note that none of the tests below will be correct until you have implemented all methods.
		 * This is just one example of a test, you must write more tests and cover all cases.
		 */
		
		// exercise your toString method
		String m1Expected = "1 2 3 \n2 5 6 \n";
		String m1Result = M1.toString();
		if(!m1Expected.equals(m1Result))
			System.out.println("toString Error.\nGot:\n" + m1Result + "\nExpected:\n" + m1Expected);
		
		// get the matrix computed by your times method
		Matrix computedMultiply = M1.times(M2);
		
		// exercises your toString method as well
		System.out.println("Computed result for M1 * M2:\n" + computedMultiply); 
		
		// exercises your .equals method, and makes sure that your computed result is the same as the known correct result
		if(!referenceMultiply.equals(computedMultiply)) 
			System.out.println("equals error (M1 * M2 not equal to expected result)");
		
		
		
		
		//Custom Test 1: Plus method dimension compatibility check
		Matrix M3 = new Matrix(new int[][]
                {{4, 5},
				   {3, 2},
				   {1, 1}});

		Matrix test1Expected = null;
		Matrix test1Result = M3.plus(M1);
		if(test1Expected == test1Result)
			System.out.println("Custom Test 1: Incompatible addition dimensions - Success");
		if(test1Expected != test1Result)
			System.out.println("Custom Test 1: Incompatible addition dimensions - Failure");
		
		//Custom Test 2: Times method dimension compatibility check
		Matrix M4 = new Matrix(new int[][]
                {{3, 2},
				   {1, 1},
				   	{1, 2}});
		
		Matrix M5 = new Matrix(new int[][]
                {{1, 2, 3},
				   {2, 5, 6},
				   	  {3, 5, 2}});
		
		Matrix test2Expected = null;
		
		if(M4.times(M5) == test2Expected)
			System.out.println("Custom Test 2: Incompatible multiplication dimensions - Success");
		if(M4.times(M5) != test2Expected)
			System.out.println("Custom Test 2: Incompatible multiplication dimensions - Failure");
		
		//Custom Test 3: Plus method correction check. 
		Matrix M6 = new Matrix(new int[][]
                {{3, 2, 8},
				   {1, 1, 2},
				   	{1, 2, 1}});
		
		Matrix M7 = new Matrix(new int[][]
                {{1, 2, 3},
				   {2, 5, 6},
				   	  {3, 5, 2}});
		
		Matrix test3Expected = new Matrix(new int[][] 
				{{4, 4, 11}, {3, 6, 8}, {4, 7, 3}});
		
		if(M6.plus(M7).equals(test3Expected))
			System.out.println("Custom Test 3: Plus method correction check - Success");
		if(!M6.plus(M7).equals(test3Expected))
			System.out.println("Custom Test 3: Plus method correction check - Failure");
		
		//Custom Test 4: Times method correction check.
		Matrix M8 = new Matrix(new int[][]
                {{9, 10, 3},
				   {2, 12, 38},
				   	{2, 3, 14}, 
				   	 {9, 21, 20}});
		
		Matrix M9 = new Matrix(new int[][]
                {{1, 2, 3, 5, 6},
				   {2, 5, 6, 1, 9},
				   	  {3, 5, 2, 8, 7}});
		
		Matrix test4Expected = new Matrix(new int[][] 
				{{38, 83, 93, 79, 165}, {140, 254, 154, 326, 386}, 
			{50, 89, 52, 125, 137}, {111, 223, 193, 226, 383}});
		
		Matrix test4Result = M8.times(M9);
		
		if(test4Expected.equals(test4Result))
				System.out.println("Custom Test 4: Times method correction check - Success");
		if(!test4Expected.equals(test4Result))
			System.out.println("Custom Test 4: Times method correction check - Failure");
	
		//Custom Test 5: Equals method check
		Matrix M10 = new Matrix(new int[][]
                {{9, 10, 3},
				   {2, 12, 38},
				   	{2, 3, 14}, 
				   	 {9, 21, 20}});
		
		Matrix M11 = new Matrix(new int[][]
                {{9, 10, 3},
				   {2, 12, 38},
				   	{2, 3, 14}, 
				   	 {9, 21, 20}});
		
		if(M10.equals(M11))
			System.out.println("Custom Test 5: Equals method check - Success");
		
		if(!M10.equals(M11))
			System.out.println("Custom Test 5: Equals method check - Failure");
		
		//Custom Test 6: Equals unequal method check. Checks for an unequal value returned from calling .equals method.
		Matrix M12 = new Matrix(new int[][]
                {{9, 10, 3},
				   {2, 12, 38},
				   	{2, 3, 14}, 
				   	 {9, 21, 20}});
		
		Matrix M13 = new Matrix(new int[][]
                {{9, 10, 3},
				   {2, 12, 38},
				   	{2, 3, 14}, 
				   	 {9, 21, 21}});
		
		if(M12.equals(M13))
			System.out.println("Custom Test 6: Equals method, Unequal check - Failure");
		
		if(!M12.equals(M13))
			System.out.println("Custom Test 6: Equals method, Unequal check - Success");
		
		//Custom Test 7: ToString method check
		Matrix M14 = new Matrix(new int[][]
                {{9, 10, 3}, 
				   	 {9, 21, 20}});
		
		String test7Expected = "9 10 3 \n9 21 20 \n";
		String test7Result = M14.toString();
		
		if(test7Expected.equals(test7Result))
			System.out.println("Custom Test 7: To String method - Success");
		if(!test7Expected.equals(test7Result))
			System.out.println("Custom Test 7: To String method - Failure");
		
	}
}
