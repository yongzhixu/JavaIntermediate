package SortingBench;

public class Sort {

	void selectionSort(int[] A) {
		// Sort A into increasing order, using selection sort

		for (int lastPlace = A.length - 1; lastPlace > 0; lastPlace--) {
			// Find the largest item among A[0], A[1], ...,
			// A[lastPlace], and move it into position lastPlace
			// by swapping it with the number that is currently
			// in position lastPlace.

			int maxLoc = 0; // Location of largest item seen so far.

			for (int j = 1; j <= lastPlace; j++) {
				if (A[j] > A[maxLoc]) {
					// Since A[j] is bigger than the maximum we've seen
					// so far, j is the new location of the maximum value
					// we've seen so far.
					maxLoc = j;
				}
			}

			int temp = A[maxLoc]; // Swap largest item with A[lastPlace].
			A[maxLoc] = A[lastPlace];
			A[lastPlace] = temp;

		} // end of for loop

	}

	void insertionSort(int[] A) {
		// Sort the array A into increasing order.

		int itemsSorted; // Number of items that have been sorted so far.

		for (itemsSorted = 1; itemsSorted < A.length; itemsSorted++) {
			// Assume that items A[0], A[1], ... A[itemsSorted-1]
			// have already been sorted. Insert A[itemsSorted]
			// into the sorted part of the list.

			int temp = A[itemsSorted]; // The item to be inserted.
			int loc = itemsSorted - 1; // Start at end of list.

			while (loc >= 0 && A[loc] > temp) {
				A[loc + 1] = A[loc]; // Bump item from A[loc] up to loc+1.
				loc = loc - 1; // Go on to next location.
			}

			A[loc + 1] = temp; // Put temp in last vacated space.
		}
	}

	/**
	 * Postcondition: The items in A have been rearranged into a random order.
	 */
	static void shuffle(int[] A) {
		for (int lastPlace = A.length - 1; lastPlace > 0; lastPlace--) {
			// Choose a random location from among 0,1,...,lastPlace.
			int randLoc = (int) (Math.random() * (lastPlace + 1));
			// Swap items in locations randLoc and lastPlace.
			int temp = A[randLoc];
			A[randLoc] = A[lastPlace];
			A[lastPlace] = temp;
		}
	}

	/**
	 * Apply QuicksortStep to the list of items in locations lo through hi in the
	 * array A. The value returned by this routine is the final position of the
	 * pivot item in the array.
	 */
	int quicksortStep(int[] A, int lo, int hi) {

		int pivot = A[lo]; // Get the pivot value.

		// The numbers hi and lo mark the endpoints of a range
		// of numbers that have not yet been tested. Decrease hi
		// and increase lo until they become equal, moving numbers
		// bigger than pivot so that they lie above hi and moving
		// numbers less than the pivot so that they lie below lo.
		// When we begin, A[lo] is an available space, since its
		// value has been moved into the local variable, pivot.

		while (hi > lo) {

			while (hi > lo && A[hi] >= pivot) {
				// Move hi down past numbers greater than pivot.
				// These numbers do not have to be moved.
				hi--;
			}

			if (hi == lo)
				break;

			// The number A[hi] is less than pivot. Move it into
			// the available space at A[lo], leaving an available
			// space at A[hi].

			A[lo] = A[hi];
			lo++;

			while (hi > lo && A[lo] <= pivot) {
				// Move lo up past numbers less than pivot.
				// These numbers do not have to be moved.
				lo++;
			}

			if (hi == lo)
				break;

			// The number A[lo] is greater than pivot. Move it into
			// the available space at A[hi], leaving an available
			// space at A[lo].

			A[hi] = A[lo];
			hi--;

		} // end while

		// At this point, lo has become equal to hi, and there is
		// an available space at that position. This position lies
		// between numbers less than pivot and numbers greater than
		// pivot. Put pivot in this space and return its location.

		A[lo] = pivot;
		return lo;

	} // end QuicksortStep

	/**
	 * Apply quicksort to put the array elements between position lo and position hi
	 * into increasing order.
	 */
	void quicksort(int[] A, int lo, int hi) {
		if (hi <= lo) {
			// The list has length one or zero. Nothing needs
			// to be done, so just return from the subroutine.
			return;
		} else {
			// Apply quicksortStep and get the new pivot position.
			// Then apply quicksort to sort the items that
			// precede the pivot and the items that follow it.
			int pivotPosition = quicksortStep(A, lo, hi);
			quicksort(A, lo, pivotPosition - 1);
			quicksort(A, pivotPosition + 1, hi);
		}
	}

}
