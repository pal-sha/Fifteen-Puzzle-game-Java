import java.io.FileNotFoundException;  // Import this class to handle errors

import fifteenpuzzle.IllegalMoveException;
import fifteenpuzzle.BadBoardException;
import fifteenpuzzle.FifteenPuzzle;

 
public class TestFifteenPuzzle
{
	public static void testReadFromFile1() {
		try {
			FifteenPuzzle game1 = new FifteenPuzzle("board1.txt");
			System.out.println("created game1: " + "\n" + game1);
			String expected1 =
					" 1  8  4  6" + "\n" +
					" 2  3  5 15" + "\n" +
					"11 12 10  9" + "\n" +
					" 7 14 13   " + "\n";

			FifteenPuzzle game2 = new FifteenPuzzle("board2.txt");
			System.out.println("created game2: " + "\n" + game2);
//			 add a test for toString() yourself

			String expected2 =
					" 1  2  3  4" + "\n" +
					" 5  6  8 11" + "\n" +
					" 9 10    12" + "\n" +
			        "13 14  7 15" + "\n";

			if (!expected1.equals(game1.toString())) {
				System.out.println("testReadFromFile1 game1 toString() ERROR");
				return;
			}

			if (!expected2.equals(game2.toString())) {
				System.out.println("testReadFromFile1 game2 toString() ERROR");
				return;
			}
			
			System.out.println("testReadFromFile1 OK");
		}
		catch (Exception e) {
			System.out.println("testReadFromFile1 ERROR" + e);
			e.printStackTrace();
		}
	}

	public static void testReadFromFile2() {
		try {
			FifteenPuzzle game = new FifteenPuzzle("nofile.txt");
			System.out.println("testReadFromFile2 ERROR - nofile.txt does not exist" + game);
		}
		catch (FileNotFoundException e) {
			System.out.println("testReadFromFile2 OK: " + e);
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("testReadFromFile2 unexpected exception: " + e);
			e.printStackTrace();
		}
	}

	public static void testReadFromFile3() {
		try {
			FifteenPuzzle game = new FifteenPuzzle("badboard1.txt");
			System.out.println("testReadFromFile3 ERROR - badboard.txt is bad" + "\n" + game);
		}
		catch (FileNotFoundException e) {
			System.out.println("testReadFromFile3 ERROR: " + "\n" + e);
			e.printStackTrace();
		}
		catch (BadBoardException e) {
			System.out.println("testReadFromFile3 OK: " + "\n" + e);
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("testReadFromFile2 unexpected exception: " + "\n" + e);
			e.printStackTrace();
		}
	}

	public static void testReadFromFile4() {
		try {
			FifteenPuzzle game = new FifteenPuzzle("badboard2.txt");
			System.out.println("testReadFromFile4 ERROR - badboard.txt is bad" + "\n" + game);
		}
		catch (FileNotFoundException e) {
			System.out.println("testReadFromFile4 ERROR: " + "\n" + e);
			e.printStackTrace();
		}
		catch (BadBoardException e) {
			System.out.println("testReadFromFile4 OK: " + "\n" + e);
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("testReadFromFile2 unexpected exception: " + "\n" + e);
			e.printStackTrace();
		}
	}

	public static void testMoves1() {
		try {
			FifteenPuzzle game = new FifteenPuzzle("board1.txt");
			//  1  8  4  6
			//  2  3  5 15
			// 11 12 10  9
			//  7 14 13
			game.makeMove(9, FifteenPuzzle.DOWN);
			//System.out.println("Board after 9 DOWN: " + "\n" + game);
			game.makeMove(10, FifteenPuzzle.RIGHT);
			//System.out.println("Board after 1O RIGHT: " + "\n" + game);
			game.makeMove(13, FifteenPuzzle.UP);
			//System.out.println("Board after 13 UP: " + "\n" + game);
			game.makeMove(13, FifteenPuzzle.DOWN);
			//System.out.println("Board after 13 DOWN: " + "\n" + game);
			game.makeMove(5, FifteenPuzzle.DOWN);
			//System.out.println("Board after 5 DOWN: " + "\n" + game);
			game.makeMove(15, FifteenPuzzle.LEFT);
			//System.out.println("Board after 15 LEFT: " + "\n" + game);
			game.makeMove(10, FifteenPuzzle.UP);
			//System.out.println("Board after 10 UP: " + "\n" + game);
			// Expected state
			//  1  8  4  6
			//  2  3 15 10
			// 11 12  5
			//  7 14 13  9
			System.out.println("testMoves1 OK");
		}
		catch (Exception e) {
			System.out.println("testMoves1 ERROR with exception: " + e);
			e.printStackTrace();
		}

	}

	public static void testMoves2() {
		try {
			FifteenPuzzle game = new FifteenPuzzle("board2.txt");
			//  1  2  3  4
			//  5  6  8 11
			//  9 10    12
			// 13 14  7 15
			game.makeMove(3, FifteenPuzzle.RIGHT);
			System.out.println("testMoves2 ERROR: illegal move not caught");
		}
		catch (IllegalMoveException ime) {
			System.out.println("testMoves2 Ok: illegal move exception: " + ime);
			ime.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("testMoves2 ERROR with an unexpected exception: " + e);
			e.printStackTrace();
		}

	}

	public static void testIsSolved1() {
		try {
			FifteenPuzzle game = new FifteenPuzzle("board1.txt");
			if (!game.isSolved())
				System.out.println("testIsSolved1 OK");
			else
				System.out.println("testIsSolved1 ERROR");
		}
		catch (Exception e) { // catching all exception
			System.out.println("testIsSolved1 ERROR with exception: " + e);
			e.printStackTrace();
		}
	}

	public static void testIsSolved2() {
		try {
			FifteenPuzzle game = new FifteenPuzzle("board2.txt");
			//  1  2  3  4
			//  5  6  8 11
			//  9 10    12
			// 13 14  7 15
			game.makeMove(7, FifteenPuzzle.UP);
			game.makeMove(15, FifteenPuzzle.LEFT);
			game.makeMove(12, FifteenPuzzle.DOWN);
			game.makeMove(11, FifteenPuzzle.DOWN);
			boolean b1 = game.isSolved();
			//  1  2  3  4
			//  5  6  8
			//  9 10  7 11
			// 13 14 15 12
			game.makeMove(8, FifteenPuzzle.RIGHT);
			game.makeMove(7, FifteenPuzzle.UP);
			game.makeMove(11, FifteenPuzzle.LEFT);
			game.makeMove(12, FifteenPuzzle.UP);
			//  1  2  3  4
			//  5  6  7  8
			//  9 10 11 12
			// 13 14 15
			boolean b2 = game.isSolved();

			if (!b1 && b2)
				System.out.println("testIsSolved2 OK");
			else
				System.out.println("testIsSolved2 ERROR");
		}
		catch (Exception e) { // catching all exception
			System.out.println("testIsSolved2 ERROR with exception: " + e);
			e.printStackTrace();
		}
	}

	public static void testIsSolved3() {
		try {
			FifteenPuzzle game = new FifteenPuzzle("board3.txt");
			// 1  2  3  4
			// 5  6  7  8
			// 9 10 11
			//13 14 15 12
			game.makeMove(12, FifteenPuzzle.UP);
			if (game.isSolved())
				System.out.println("testIsSolved3 Ok");
			else
				System.out.println("testIsSolved3 ERROR");
		}
		catch (Exception e) { // catching all exception
			System.out.println("testIsSolved3 ERROR with exception: " + e);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("current dir: " + System.getProperty("user.dir") + "\n");
		testReadFromFile1();
		testReadFromFile2();
		testReadFromFile3();
		testReadFromFile4();
		testMoves1();
		testMoves2();
		testIsSolved1();
		testIsSolved2();
		testIsSolved3();
	}
}