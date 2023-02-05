package integeriterators;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class PrimeNumbersIterator implements IntegerIterator {

	// Member variable:
	private int startPoint;
	public PrimeNumbersIterator() {
		// TODO implement me

		// First element in sequence is 2, but starting point is before first element
		startPoint = 1;
	}
	
	public PrimeNumbersIterator(int n) {
		// TODO implement me

		// Starting point will be before first element
		startPoint = n-1;
	}
	
	@Override
	public boolean hasNext() {
		// TODO implement me

		// Sequence is infinite, so there is always a next element
		return true;
	}
	
	@Override
	public Integer next() {
		// TODO implement me

		if (!this.hasNext()) {
			throw new NoSuchElementException("No next element");
		}

		// First number checked will be 2
		int nextPrime = this.startPoint + 1;

		// Using a helper function, current number in the sequence (1,2,3,4,...) is returned if it is a prime number
		while (!isPrime(nextPrime)) {
			// If current number is not a prime number, move onto next value
			nextPrime = nextPrime + 1;
		}

		// If current number is a prime number, this becomes the starting point for next iteration and current number is returned
		this.startPoint = nextPrime;
		return nextPrime;
	}

	// Helper function to check if a number is prime or not in the sequence (1,2,3,4...)
	public boolean isPrime(int nextPrime) {

		int checker = 2; // Variable used to check remainder of modular division

		// If checker value hits number being tested, this is a prime number
		// e.g. checker = 7 and number being tested = 7:
		// it means that 7 cannot be divided by anything less than itself except for 1
		while (checker != nextPrime) {
			// If remainder does not equal to 0, increment checker value
			if (nextPrime % checker != 0) {
				checker++;
			} else { // (nextPrime % checker == 0)
				return false;
			}
		}

		return true;
	}

	public void reset() {
		// TODO implement me
		this.startPoint = 1;
	}
}
