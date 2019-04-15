package SortingBench;

public class Sort {
	
	void selectionSort(int[] A) {
	      // Sort A into increasing order, using selection sort
	      
		   for (int lastPlace = A.length-1; lastPlace > 0; lastPlace--) {
		         // Find the largest item among A[0], A[1], ...,
		         // A[lastPlace], and move it into position lastPlace 
		         // by swapping it with the number that is currently 
		         // in position lastPlace.
		         
		      int maxLoc = 0;  // Location of largest item seen so far.
		      
		      for (int j = 1; j <= lastPlace; j++) {
		         if (A[j] > A[maxLoc]) {
		               // Since A[j] is bigger than the maximum we've seen
		               // so far, j is the new location of the maximum value
		               // we've seen so far.
		            maxLoc = j;  
		         }
		      }
		      
		      int temp = A[maxLoc];  // Swap largest item with A[lastPlace].
		      A[maxLoc] = A[lastPlace];
		      A[lastPlace] = temp;
		      
		   }  // end of for loop
		   
		}
	
	void insertionSort(int[] A) {
	      // Sort the array A into increasing order.
	      
	   int itemsSorted; // Number of items that have been sorted so far.

	   for (itemsSorted = 1; itemsSorted < A.length; itemsSorted++) {
	         // Assume that items A[0], A[1], ... A[itemsSorted-1] 
	         // have already been sorted.  Insert A[itemsSorted]
	         // into the sorted part of the list.
	         
	      int temp = A[itemsSorted];  // The item to be inserted.
	      int loc = itemsSorted - 1;  // Start at end of list.
	      
	      while (loc >= 0 && A[loc] > temp) {
	         A[loc + 1] = A[loc]; // Bump item from A[loc] up to loc+1.
	         loc = loc - 1;       // Go on to next location.
	      }
	      
	      A[loc + 1] = temp; // Put temp in last vacated space.
	   }
	}
	
	/**
	 * Postcondition:  The items in A have been rearranged into a random order.
	 */
	static void shuffle(int[] A) {
	   for (int lastPlace = A.length-1; lastPlace > 0; lastPlace--) {
	         // Choose a random location from among 0,1,...,lastPlace.
	      int randLoc = (int)(Math.random()*(lastPlace+1));
	         // Swap items in locations randLoc and lastPlace.
	      int temp = A[randLoc];
	      A[randLoc] = A[lastPlace];
	      A[lastPlace] = temp;
	   }
	}

}
