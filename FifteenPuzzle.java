package fifteenpuzzle;

import java.io.*;
import java.util.*;

	public class FifteenPuzzle {
	public final static int UP = 0;
	public final static int DOWN = 1;
	public final static int LEFT = 2;
	public final static int RIGHT = 3;

	// size of board
	public final static int SIZE = 4;
	private File fname;	// Creating file object
	private int[][] intArr = new int[SIZE][SIZE]; // Creating 2D integer to store board

//	/**
//	 * @param fileName
//	 * @throws FileNotFoundException if file not found
//	 * @throws BadBoardException if the board is incorrectly formatted
//	 * Reads a board from file and creates the board
//	 */
	public FifteenPuzzle(String fileName) throws IOException, BadBoardException {
		// TODO implement me

		// Variables used for BadBoardException checker
		// --------------------------------------------
		int ii = 0; // Variable used in array iteration
		int jj = 0; // Variable used in array iteration
		int jjj = 0; // Variable used in array iteration
		boolean firstTime = true; // True when comparing an element for the first time, else false
		// --------------------------------------------

		// Assigning file to file object
		fname = new File(fileName);

		Scanner reader = new Scanner(fname); // Creating new scanner object to read file

		String readingLine; // String variable to store a row of the board
		String readingSub; // String variable to store substring of readingLine

		// Inserting board numbers into 2D integer array
		for (int i = 0; i < SIZE; i++){
			readingLine = reader.nextLine(); // Reading board line by line
			if (readingLine.length() >= 2) {
				readingSub = readingLine.substring(0,2); // Reading substring from index 0 to 1 (first element in row)
				// If this position on the board is an empty space,
				// it will be represented with a zero in the array
				if (!readingSub.equals("  ")) {
					intArr[i][0] = Integer.parseInt(readingSub.trim());
				} else {
					intArr[i][0] = 0;
				}
			}
			if (readingLine.length() >= 5) {
				readingSub = readingLine.substring(3,5); // Reading substring from index 3 to 4 (second element in row)
				if (!readingSub.equals("  ")) {
					intArr[i][1] = Integer.parseInt(readingSub.trim());
				} else {
					intArr[i][1] = 0;
				}
			}
			if (readingLine.length() >= 8) {
				readingSub = readingLine.substring(6,8); // Reading substring from index 6 to 7 (third element in row)
				if (!readingSub.equals("  ")) {
					intArr[i][2] = Integer.parseInt(readingSub.trim());
				} else {
					intArr[i][2] = 0;
				}
			}
			if (readingLine.length() >= 11) {
				readingSub = readingLine.substring(9,11); // Reading substring from index 9 to 10 (last element in row)
				if (!readingSub.equals("  ")) {
					intArr[i][3] = Integer.parseInt(readingSub.trim());
				} else {
					intArr[i][3] = 0;
				}
			}
		}

		// ------------- BadBoardException Checker -------------

		// Checking if a number is more than 15 or less than 0 in board
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (intArr[i][j] > 15 || intArr[i][j] < 0) {
					reader.close(); // Closing file before throwing exception
					throw new BadBoardException("This is a bad board!");
				}
			}
		}

		// Checking if a number is being repeated in board
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				for (ii = i; ii < SIZE; ii++){
					if (firstTime){ // Checks if a new element is being compared and if the end of a row is reached
						jjj = j + 1;
					} else { // If the end of a row is reached, set jjj to 0 to start at the beginning of the next row
						jjj = 0;
					}
					for (jj = jjj; jj < SIZE; jj++){
						if (intArr[i][j] == intArr[ii][jj]) { // Comparing element with the ones after it
							reader.close(); // Closing file before throwing exception
							throw new BadBoardException("This is a bad board!");
						}
					}
					firstTime = false; // Setting firstTime to false because end of row has not been reached
				}
				firstTime = true; // Setting firstTime to false because end of row has been reached
			}
		}

		reader.close(); // Closing file after all is done
	}
	
	/**
	 * Get the number of the tile, and moves it to the specified direction
	 * 
	 * @throws IllegalMoveException if the move is illegal
	 */
	public void makeMove(int tile, int direction) throws IllegalMoveException {
		// TODO implement me

		// Four possible valid directions a tile can be moved: UP, DOWN, LEFT and RIGHT

		// Initial index of number
		int initialRow = 0;
		int initialCol = 0;

		// New index of number (where it will be moved to)
		int newRow = 0;
		int newCol = 0;

		// Checking if tile number entered is valid
		if (tile > 15 || tile < 1) {
			throw new IllegalMoveException("This is not a valid move!");
		}

		// Finding position of tile in array
		// I.e. finding the initial index of number
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++) {
				if (tile == this.intArr[i][j]) {
					initialRow = i;
					initialCol = j;
					break;
				}
			}
		}

		// Getting new position based on specified direction of move
		switch (direction) {
			case 0: // direction = UP
				newRow = initialRow - 1;
				newCol = initialCol;
				break;
			case 1: // direction is DOWN
				newRow = initialRow + 1;
				newCol = initialCol;
				break;
			case 2: // direction is LEFT
				newRow = initialRow;
				newCol = initialCol - 1;
				break;
			case 3: // direction is RIGHT
				newRow = initialRow;
				newCol = initialCol + 1;
				break;
			default: // If direction requested is not valid
				throw new IllegalMoveException("This is not a valid move!");
		}

		// Validating new position:
		if (newRow < 0 || newRow > 3 || newCol < 0 || newCol > 3){
			throw new IllegalMoveException("This is not a valid move!");
		}

		// Checking value of new position: must be empty i.e. 0 in int array for move to happen
		if (this.intArr[newRow][newCol] != 0) {
			throw new IllegalMoveException("This is not a valid move!");
		}

		// If all is good, make the move
		this.intArr[newRow][newCol] = this.intArr[initialRow][initialCol];
		this.intArr[initialRow][initialCol] = 0; // Initial index is set to 0 i.e.
	}

	
	/**
	 * @return true if and only if the board is solved,
	 * i.e., the board has all tiles in their correct positions
	 */
	public boolean isSolved() {
		// TODO implement me

		// Solved state is when the next element after the current one is 1 greater than it
		// Loop stops the moment 15 is hit, indicating end of sequence
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (this.intArr[i][j] != 15) {
					if (j != 3) {
						if (this.intArr[i][j + 1] == this.intArr[i][j] + 1) {
							j++;
						} else {
							return false;
						}
					} else {
						if (this.intArr[i + 1][0] == this.intArr[i][j] + 1) {
							j++; // Incrementing column value is next element is one more than current
						} else {
							return false;
						}
					}
				} else {
					return true;
				}
			}
		}

		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		// TODO implement me

		String addtoString = ""; // Creating string variable to hold array as it is concatenated

		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (col < SIZE-1) { // Is end of row is not reached, place a "  " after each element added
					if (this.intArr[row][col] == 0) {
						addtoString = addtoString + "   "; // Adding "  " if that space is empty, i.e. that index value is 0 in int array
					} else if (this.intArr[row][col] < 10) { // If number is single digit, add one space before it
						addtoString = addtoString + " " + this.intArr[row][col] + " ";
					} else { // If number is double digit, add two spaces before it
						addtoString = addtoString + this.intArr[row][col] + " ";
					}

				} else { // If end of row is reached, add a new line character after number
					if (this.intArr[row][col] == 0) {
						addtoString = addtoString + "  \n";
					} else if (this.intArr[row][col] < 10) {
						addtoString = addtoString + " " + this.intArr[row][col] + "\n";
					} else {
						addtoString = addtoString + this.intArr[row][col] + "\n";
					}
				}
			}
		}

		return addtoString;
	}
}
