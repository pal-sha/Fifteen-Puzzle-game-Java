package integeriterators;

import java.util.NoSuchElementException;

public class RangeIterator implements IntegerIterator
{
	// Member variables:
	private int endPoint; // Holds value of upper bound of sequence
	private int startPoint; // Holds value of lower bound of sequence
	private int current; // Holds value of current position on range

	/**
	 * Creates an iterator for the infinite sequence 0,1,2,...
	 */
	public RangeIterator() {
		// TODO implement me

		// Starting at -1 in order to be right before first element
		startPoint = -1;

		// Since sequence is infinite, assume end is equal to -1
		endPoint = -1;

		current = startPoint;
	}
	
	/**
	 * Creates an iterator for the infinite sequence s,s+1,s+2...
	 */
	public RangeIterator(int s) {
		// TODO implement me

		// Starting point is s of infinite sequence, but iteration will begin just before first element in sequence
		startPoint = s-1;

		// Since sequence is infinite, assume end is equal to -1
		endPoint = -1;

		current = startPoint;
	}
	
	/**
	 * Creates an iterator for the finite sequence [s,s+1,s+2...t-1]
	 * @throws IllegalArgumentException if t<s
	 */
	public RangeIterator(int s, int t) {
		// TODO implement me
		startPoint = s-1;
		endPoint = t-1;
		current = startPoint;
		if (t < s) {
			throw new IllegalArgumentException("Invalid: last number can not be greater than the start");
		}
	}
	
	@Override
	public boolean hasNext() {
		// TODO implement me

		// If sequence is infinite, then there is always a next element
		if (endPoint == -1) {
			return true;
 		} else { // If sequence is finite, there is a next element as long as current position is at second last element of array
			return current < endPoint;
		}
	}
	
	@Override
	public Integer next() {
		// TODO implement me

		if (!this.hasNext()) {
			throw new NoSuchElementException("No next element");
		}

		current = current+1;
		return current;
}

	public void reset() {
		// TODO implement me

		// Resetting current position back to starting point
		this.current = this.startPoint;
	}
}
