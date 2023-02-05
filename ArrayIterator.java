package integeriterators;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayIterator implements IntegerIterator
{
	// Member variables:
	private int[] intArr; // Member integer array
	private int iniPos;	// Variable to track initial and current position in array
	private boolean isCircle = false; // Member variable to declare if an array is circular or not
	private int nextVal; // Member variable used in next() function that holds the value returned
	private int count;	// Member variable that holds the value of the array size, used to check if end of array is reached

	/**
	 * Creates an iterator for ar 
	 */
	public ArrayIterator(int[] ar) {
		// TODO implement me
		intArr = ar;
		count = ar.length;

		// Initializing iniPos at -1 because we are starting right before the first element on the array,
		// not on the first element
		iniPos = -1;
	}
	
	/**
	 * Creates an iterator for the ar
	 * If isCurcular is true, the iterator will be infinite,
	 * outputting the array in circle 
	 * ar[0],ar[1]...ar[ar.length-1],ar[0],ar[1]...ar[ar.length-1],ar[0]...
	 */
	public ArrayIterator(int[] ar, boolean isCircular) {
		// TODO implement me

		intArr = ar;
		count = ar.length;

		// Initializing iniPos at -1 because we are starting right before the first element on the array,
		// not on the first element
		iniPos = -1;
		isCircle = isCircular;
	}
	
	@Override
	public boolean hasNext() {
		// TODO implement me

		// If array is not circular
		if (isCircle == false) {
			// As long as current position is not on the last index, there is a next element
			if (iniPos < count-1) {
				return true;
			} else {
				return false;
			}
		} else { // If array is circular, there is always a next element
			return true;
		}
	}
	
	@Override
	public Integer next() {
		// TODO implement me

		if (!this.hasNext()) {
			throw new NoSuchElementException("No next element");
		}

		// If there is a next element
		if (this.hasNext()) {
			// If the array is circular:
			if (isCircle == true) {
				// If you reach the last element in the circular array, current position goes back to the first element
				if (iniPos == count-1) {
					reset();
					return intArr[iniPos];
				} else {
					iniPos = iniPos+1;
					nextVal = intArr[iniPos];
					return nextVal;
				}
			// If array is not circular, current position is incremented and next element is returned
			} else {
				iniPos = iniPos+1;
				nextVal = intArr[iniPos];
				return nextVal;
			}
		}

		return -1;
	}
	
	public void reset() {
		// TODO implement me
		iniPos = 0;
	}
}
