/***********************************
*       Author: Pedro Anibarro     *
*           Matrix ToolBox     	   *
*          October 21, 2014        *
************************************/

import java.util.Scanner;

public class MatrixToolbox{

				/*******************MAIN*******************/

	public static void main(String[] args){
		//creates a Scanner object for inputs
		Scanner input = new Scanner(System.in);


		// First brackets = which array 
		//					[0] = Matrix 1
		//					[1] = Matrix 2
		//					[2] = Matrix 3 (Answers)
		// Second brackets = rows of selected array
		// Third brackets = columns of selected array
		double[][][] bothMatrix = new double[3][][]; //triple dimension array that contains the 2 arrays of matrixes

		newMatrix(bothMatrix); //call function to make matrixes 

		menu(bothMatrix); //call function that generates the menu


	}

				/*******************FUNCTIONS*******************/



	/*******************NEW MATRIX*******************/

	//generates 3 matrixes
	public static void newMatrix(double[][][] bothMatrix){
		//creates a Scanner object for inputs
		Scanner input = new Scanner(System.in);

		int dummy = 0; //variable that is used for temporary stuff
		int rowsColumns = 0; //the user input for n x n matrix

		//do while to get the desired rows and columns for the matrixes
		do{
			System.out.println("Enter the number of rows and columns of the desired matrix:");
			rowsColumns = input.nextInt();
		}while(rowsColumns < 0);

		//do while to know if user want to enter matrix values or to be random
		do{
			System.out.println("1. User inputs");
			System.out.println("2. Generate two random ");
			dummy = input.nextInt();
		}while(dummy != 1 && dummy != 2);

		double[][] matrix1 = new double[rowsColumns][rowsColumns]; //matrix 1
		double[][] matrix2 = new double[rowsColumns][rowsColumns]; //matrix 2
		double[][] matrix3 = new double[rowsColumns][rowsColumns]; //matrix 3

		//assign each local array to the triple dimensional array on corresponding position
		bothMatrix[0] = matrix1; 
		bothMatrix[1] = matrix2;
		bothMatrix[2] = matrix3;


		//if user wants to do a random matrix, ask the range of the numbers
		if(dummy == 1){
			userMatrix(bothMatrix);
		}else{
			System.out.println("Which limit you want for the random number (from 0 to input):");
			dummy = input.nextInt();
			randomMatrix(bothMatrix, dummy);
		}
		
	
		return;

	}



	/*******************USER MATRIX*******************/

	//generates a user matrix
	public static void userMatrix(double[][][] bothMatrix){
		//creates a Scanner object for inputs
		Scanner input = new Scanner(System.in);

		//for loops to move through arrays 
		for(int z = 0; z < 2; z++){ //move through each array
			System.out.println("/*******************Matrix " + (z + 1) + "*******************/");
			System.out.println();

			for(int y = 0; y < bothMatrix[z].length; y++){ //move through array rows
				for(int x = 0; x < bothMatrix[z][y].length; x++){ //move through array columns
					System.out.println("Enter [" + y + "][" + x + "] value of the matrix:");
					bothMatrix[z][y][x] = input.nextInt();
				}
			}
			System.out.println();
		}


		return;
	}



	/*******************RANDOM MATRIX*******************/

	//generates random arrays 
	public static void randomMatrix(double[][][] bothMatrix, int max){

		//for loops to move through arrays
		for(int z = 0; z < 2; z++){ //move through each array
			for(int y = 0; y < bothMatrix[z].length; y++){ //move through array rows
				for(int x = 0; x < bothMatrix[z][y].length; x++){	//move through array columns
					bothMatrix[z][y][x] = (int)(Math.random() * (max + 1)); //generates a random number
				}
			}
		}


		return;
	}



	/*******************MENU*******************/

	//generates the menu where all is controled
	public static void menu(double[][][] bothMatrix){
		//creates a Scanner object for inputs
		Scanner input = new Scanner(System.in);

		int dummy = 0; //variable that is used for temporary stuff

		//keep in the loop while user dont enter 10
		do{

			do{
				System.out.println("\t\t\tMenú:");
				System.out.println();
				System.out.println("1. Add the two matrices");
				System.out.println("2. Substract the two matrices");
				System.out.println("3. Multiply two matrices");
				System.out.println("4. Find the trace of a matrix");
				System.out.println("5. Find the transpose of a matrix");
				System.out.println("6. Find the determinant of a matrix");
				System.out.println("7. Compute the Frobenius norm of a matrix");
				System.out.println("8. Change matrices");
				System.out.println("9. Display the matrices");
				System.out.println("10. Quit");

				dummy = input.nextInt();

			}while(dummy < 1 || dummy > 10);

			//control what function is called depending on user input on the menu
			switch(dummy){

				case 1:
					addMatrix(bothMatrix);
					break;
				case 2:
					substractMatrix(bothMatrix);
					break;
				case 3:
					multiplyMatrix(bothMatrix);
					break;
				case 4:
					traceMatrix(bothMatrix);
					break;
				case 5:
					transposeMatrix(bothMatrix);
					break;
				case 6:
					int matrix = 0;

					printMatrixes(bothMatrix, 2);

					do{
						System.out.println("Which of the matrix you want to find the determinant:");
						System.out.println("1. Matrix 1 ");
						System.out.println("2. Matrix 2 ");

						matrix = input.nextInt();

					}while(matrix != 1 && matrix != 2);

					matrix--;

					//temporary array to calculate the determinant
					double[][] arr = new double[bothMatrix[matrix].length][bothMatrix[matrix].length];

					arr = bothMatrix[matrix];

					double determinant = determinantMatrix(arr);

					System.out.println("The determinant of the matrix is: " + determinant);
					System.out.println();
					System.out.println();

					break;
				case 7:
					frobeniusMatrix(bothMatrix);
					break;
				case 8:
					newMatrix(bothMatrix);
					break;
				case 9:
					printMatrixes(bothMatrix, 3);
					break;

			}

		}while(dummy != 10);


		return;

	}


	/*******************ADD TWO MATRIX*******************/

	// add two matrix
	public static void addMatrix(double[][][] bothMatrix){

		for(int z = 0; z < 2; z++){ //move through each array
			for(int y = 0; y < bothMatrix[z].length; y++){ //move through array rows
				for(int x = 0; x < bothMatrix[z][y].length; x++){ //move through array columns 
					bothMatrix[2][y][x] = bothMatrix[0][y][x] + bothMatrix[1][y][x];
				}
			}
		}

		printMatrixes(bothMatrix, 3);

		return;

	}



	/*******************SUBSTRACT TWO MATRIX*******************/

	// substract two matrix
	public static void substractMatrix(double[][][] bothMatrix){

		for(int z = 0; z < 2; z++){ //move through arrays
			for(int y = 0; y < bothMatrix[z].length; y++){ //move through each array rows
				for(int x = 0; x < bothMatrix[z][y].length; x++){ //move through each array columns 
					bothMatrix[2][y][x] = bothMatrix[0][y][x] - bothMatrix[1][y][x];
				}
			}
		}

		printMatrixes(bothMatrix, 3);

		return;

	}


	/*******************MULTIPLY TWO MATRIX*******************/

	//multiply two matrix
	public static void multiplyMatrix(double[][][] bothMatrix){

		
		for(int y = 0; y < bothMatrix[0].length; y++){ //move through each array rows
			for(int x = 0; x < bothMatrix[0][y].length; x++){ //move thorugh each array columns
				for(int y2 = 0; y2 < bothMatrix[0].length; y2++){ //move through each array rows
					bothMatrix[2][y][x] += bothMatrix[0][y][y2] * bothMatrix[1][y2][x];
				}
			}
		}


		

		printMatrixes(bothMatrix, 3);

		return;

	}



	/*******************FIND TRACE OF MATRIX*******************/

	//find the trace of a matrix
	public static void traceMatrix(double[][][] bothMatrix){
		//creates a Scanner object for inputs
		Scanner input = new Scanner(System.in);

		int dummy = 0; //variable that is used for temporary stuff
		int matrix = 0; //holds the desired matrix to work
		double trace = 0; //holds the trace of the desired matrix

		printMatrixes(bothMatrix, 2);


		// asks for the desired matrix to work
		do{
			System.out.println("Which of the matrix you want the trace:");
			System.out.println("1. Matrix 1 ");
			System.out.println("2. Matrix 2 ");

			matrix = input.nextInt();

		}while(matrix != 1 && matrix != 2);

		matrix--;


		//asks for the desired diagonal
		do{
			System.out.println("Which is the desired diagonal:");
			System.out.println("1. Principal (from top left) ");
			System.out.println("2. Secondary (from top right) ");

			dummy = input.nextInt();

		}while(dummy != 1 && dummy != 2);


		//depends on user input, trace the principal or secondary diagonal
		if(dummy == 1){
			for(int y = 0; y < bothMatrix[0].length; y++){
					trace += bothMatrix[matrix][y][y];
				}
		}else{
			for(int y = 0, x = (bothMatrix[0].length - 1); y < bothMatrix[0].length; y++, x--){
					trace += bothMatrix[matrix][y][x];
			}
		}


		System.out.println("The trace of matrix " + (matrix + 1) + ": " + trace);
		System.out.println();
		System.out.println();
			

		return;

	}



	/*******************FIND THE TRANSPOSE OF A MATRIX*******************/

	//find the transpose of a desired matrix
	public static void transposeMatrix(double[][][] bothMatrix){
		//creates a Scanner object for inputs
		Scanner input = new Scanner(System.in);

		int matrix = 0; //holds  the desired matrix to be worked

		printMatrixes(bothMatrix, 2);

		// ask for the desired matrix to be worked
		do{
			System.out.println("Which of the matrix you want to find the transpose:");
			System.out.println("1. Matrix 1 ");
			System.out.println("2. Matrix 2 ");

			matrix = input.nextInt();

		}while(matrix != 1 && matrix != 2);

		matrix--;


		//for loops to move through arrays
		for(int x = 0; x < bothMatrix[0].length; x++){ //move trough each array rows
			for(int y = 0; y < bothMatrix[0].length; y++){ //move through each array columns
				bothMatrix[2][x][y] = bothMatrix[matrix][y][x];
			}
			
		}


		printMatrixes(bothMatrix, 3);
			

		return;

	}



	/*******************FIND THE DETERMINANT OF A MATRIX*******************/

	//find the determinant of a desired matrix
	public static double determinantMatrix(double[][] arr){


		double determinant = 0; //holds the determinant calculated

		// use recursion to calculate the determinate of a n x n matrix
        if(arr.length == 1){ //array is 1 x 1

                determinant = arr[0][0];
                return determinant;

        }else if(arr.length == 2){ //array is 2 x 2

                determinant = arr[0][0] * arr[1][1] - arr[0][1] * arr[1][0];
                return determinant;

        }else{ //array is n x n

        	for(int y = 0; y < arr[0].length; y++){

           		double subArray[][] = new double[arr.length - 1][arr[0].length - 1]; //creates an sub array to manage the remaining array

	            for (int x = 1; x < arr.length; x++){ 

	                for (int z = 0; z < arr[0].length; z++) {

                        if(z < y){

                            subArray[x - 1][z] = arr[x][z];

                        }else if(z > y){

                        	subArray[x - 1][z - 1] = arr[x][z];

                        }
	                }
	            }
            	determinant += arr[0][y] * (Math.pow(-1, y)) * determinantMatrix(subArray); // calculates and simplifies the array using recursion
        	}
        
        }
        

		return determinant;

	}



	/*******************FIND FROBENIUS NORM OF A MATRIX*******************/

	// calculates the frobenius of a desired matrix
	public static void frobeniusMatrix(double[][][] bothMatrix){
		//creates a Scanner object for inputs
		Scanner input = new Scanner(System.in);

		int matrix = 0; //holds desired matrix to be worked
		double frobenius = 0; //holds the frobenius of the matrix

		printMatrixes(bothMatrix, 2);

		//asks which is the matrix to be work in 
		do{
			System.out.println("Which of the matrix you want to find the Frobenius:");
			System.out.println("1. Matrix 1 ");
			System.out.println("2. Matrix 2 ");

			matrix = input.nextInt();

		}while(matrix != 1 && matrix != 2);

		matrix--;

		//for loops to move through array
		for(int y = 0; y < bothMatrix[0].length; y++){ //move through each array rows
			for(int x = 0; x < bothMatrix[0].length; x++){ //move through each array columns
				frobenius += Math.pow(bothMatrix[matrix][y][x], 2); 
			}
		}


		frobenius = Math.sqrt(frobenius);


		System.out.printf("The Frobenius of matrix " + (matrix + 1) + ": %.2f\t", frobenius);
		System.out.println();
		System.out.println();
		



	}



	/*******************PRINT THE MATRIXES*******************/

	//print the matrixes needed
	public static void printMatrixes(double[][][] bothMatrix, int numberOfMatrix){
	
		//for loops to move though arrays
		for(int z = 0; z < numberOfMatrix; z++){ //move through each array
			System.out.println("/*******************Matrix " + (z + 1) + "*******************/");
			System.out.println();

			for(int y = 0; y < bothMatrix[z].length; y++){ //move through each array rows
				System.out.print("[");
				for(int x = 0; x < bothMatrix[z][y].length; x++){ //move through each array columns
					System.out.print(bothMatrix[z][y][x]);
					if(x != (bothMatrix[z][y].length - 1)){
						System.out.print("\t");
					}
				}
				System.out.println("]");
			}
			System.out.println();
		}
		

		return;


	}

}