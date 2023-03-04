## **Fifteen Puzzle - Java Object Oriented Programming**

In my current university term, I am taking a class on data structures and algorithms in which we are using Java. 
Our most recent assignment consisted of writing part of the code for the Fifteen Puzzle game and implementing the following classes: ArrayIterator, RangeIterator, and PrimeNumbersIterator. 
These three classes were a part of an interface called Integer Iterator with three methods for each class (which I also implemented): hasnext, next, and reset.

In this project, the following classes were implemented: 

**1. ArrayIterator** 
   - Iterates over an integer array, both circular and non-circular

**2. RangeIterator**
   - Iterators over a range of integer values

**3. PrimeNumbersIterator**
   - Generates prime numbers and iterates over either an infinite or finite sequence of prime numbers (range can be specified for finite sequences)

**4. FifteenPuzzle**
   - Consists part of the program behind the game FifteenPuzzle
   - Consists of the following constructor/methods:
     - FifteenPuzzle: the constructor reads a test board from a .txt file and inserts the board into a 2D integer array, while checking for correct board formatting
     - makeMove: the four possible moves (up, down, right, and left) on the board were programmed 
     - isSolved: to check for solved state of board 
     - toString: to re-print board onto terminal in the same format as it was written in the .txt file


All this was completed for the class assignment, however I am now currently working in my own time to add real user interaction, a random game board generator, and potentially computer intelligence to solve the game board as well. Please see my other project for this version, "Fifteen Puzzle Game V2.0"
