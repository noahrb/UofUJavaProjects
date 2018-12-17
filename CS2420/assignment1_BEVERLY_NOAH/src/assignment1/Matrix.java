package assignment1;

public class Matrix {
	private int numRows;
	private int numColumns;
	private int data[][];
	
	// Constructor with data for new matrix (automatically determines dimensions)
	public Matrix(int d[][])
	{
		numRows = d.length; // d.length is the number of 1D arrays in the 2D array
		if(numRows == 0)
			numColumns = 0;
		else
			numColumns = d[0].length; // d[0] is the first 1D array
		data = new int[numRows][numColumns]; // create a new matrix to hold the data
		// copy the data over
		for(int i=0; i < numRows; i++) 
			for(int j=0; j < numColumns; j++)
				data[i][j] = d[i][j];
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object o)
	{
		if(!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
			return false;
		Matrix m = (Matrix)o; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		//Value to hold if the matrix is the same. 
		boolean isMatrix = true;
		
		//Checks for the same dimensions, returns false if not. 
		if(numRows != m.numRows || numColumns != m.numColumns) {
			return false;
		}
		
		//Looping through each value in the matrix and checking for consistency between matricies. 
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				if(m.data[i][j] != data[i][j]) {
					isMatrix = false;
				}
			}
		}
		return isMatrix; //Returning whether or not the matrix is the same. 
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		//Return string to hold values
		String returnString = "";
		
		//Looping through each value of the matrix and adding the value + formatting to the returnString
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				returnString = returnString + data[i][j] + " ";
			}
			returnString = returnString + "\n";
		}
		
		return returnString; //Returning the string with matrix values formatted. 
	}
	
	public Matrix times(Matrix m)
	{	
		//Compatibility check for multiplication
		if(numColumns != m.numRows) {
			return null;
		}
		
		//Creates a new multidimensional array to hold new values. 
		int[][] test = new int[numRows][m.numColumns];
		
		//For one space of the resulting matrix, calculates the value. 
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < m.numColumns; j++) {
				for(int x = 0; x < numColumns; x++) {
					test[i][j] = test[i][j] + data[i][x] * m.data[x][j];
				}
			}
		}
		
		//Creates new matrix using multidimensional array values.
		Matrix returnMatrix = new Matrix(test);
		
		return returnMatrix; //Returns the new matrix. 
	}
	
	public Matrix plus(Matrix m)
	{
		//Compatibility test for the two matrices. Checking if they are the same size.
		if(m.numRows != numRows || m.numColumns != numColumns) {
			return null;
		}
		
		//Creates a new multidimensional array to hold new values. 
		int[][] test = new int[m.numRows][m.numColumns];
		//Creates a new matrix using the multidimensional array values. 
		Matrix returnMatrix = new Matrix(test);
		//Loops through the matrices, adding the values to the return matrix's values
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				returnMatrix.data[i][j] = data[i][j] + m.data[i][j];
			}
		}
		return returnMatrix; //Returns the new matrix created with updated values.
	}
}
