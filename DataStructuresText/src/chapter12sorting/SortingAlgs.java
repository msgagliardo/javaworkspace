package chapter12sorting;

/* In testing soring algorithms, it is important to check the following
 * cases;
 * 1)  An array of random numbers
 * 2)  An array that is already sorted
 * 3)  An array that is in reverse order
 * 4)  An array that has a size of 1 or 0
 */

public class SortingAlgs {

	public static void selectionSort(int[] data, int first, int n) {
		
		int i, j, temp, small;
		
		for (i = first; i < n - 1; i++) {
			small = i;
			for (j = i + 1; j < n; j++)
				if (data[j] < data[small])
					small = j;
			
			temp = data[small];
			data[small] = data[i];
			data[i] = temp;
		}
	}
	// O(n^2) worst and average case running time
	// O(n) when the array is already sorted (selection sort
	// is still O(n^2) for a sorted array)
	// Also quick when the starting array is nearly sorted.
	public static void insertionSort(int[] data, int first, int n) {
		
		int i, j, entry;
		
		for (i = first + 1; i < n; i++) {
			entry = data[i];
			for (j = i; (first < j) && (data[j - 1] > entry); j--)
				data[j] = data[j - 1];
			data[j] = entry;
		}
	}
	private static void merge(int[] data, int first, int n1, int n2) {
		
		int[] temp = new int[n1 + n2];
		int copied = 0;
		int copied1 = 0;
		int copied2 = 0;
		int i;
		
		while (copied1 < n1 && copied2 < n2) {
			if (data[first + copied1] <= data[first + n1 + copied2])
				temp[copied++] = data[first + copied1++];
			else 
				temp[copied++] = data[first + n1 + copied2++];
			
		}
		// you don't need a second while loop for copied2 because
		// extra elements at the end of the second section are 
		// already in the right spot (and hence, they don't need 
		// to be copied into temp and back again)
		while (copied1 < n1)
			temp[copied++] = data[first + copied1++];
		
		for (i = 0; i < copied; i++)
			data[first + i] = temp[i];
	}
	/* The worst-case running time, the average-case running time, and the 
	 * best-case running time for mergesort are all O(nlogn).  In mergesort, 
	 * the division is trivial and the combining complicated.  With quicksort, 
	 * the division is complicated and the combining is trivial. */
	public static void mergeSort(int[] data, int first, int n) {
		
		if (n > 1) {
			int n1 = n / 2;
			int n2 = n - n1;
			
			mergeSort(data, first, n1);
			mergeSort(data, first + n1, n2);
			
			merge(data, first, n1, n2);
		}
	}
	private static int partition(int[] data, int first, int n) {
		
		int pivot = data[first];
		int low = first + 1;
		int high = first + n - 1;
		int temp;
		
		while (high > low) {
			while (low < n && data[low] <= pivot)
				low++;
			while (high >= first && data[high] > pivot)
				high--;
			
			if (high > low) {
				temp = data[low];
				data[low] = data[high];
				data[high] = temp;
				low++;
				high--;
			}
		}
		if (data[high] < pivot) {
			data[first] = data[high];
			data[high] = pivot;
		}
		return high;
	}
	/*In the ideal case with the pivot element near the middle of each piece, the 
	 * number of levels is about logn (just like in mergesort).  This results in a 
	 * running time of O(nlogn) which is the best that quicksort can manage.  But 
	 * sometimes quicksort is significantly worse than O(nlogn).  In fact, the worst 
	 * case behavior occurs when the array is already sorted.  In this case the running 
	 * time is O(n^2).  The average running time is O(nlogn) as it is for mergesort
	 * but we have the benefit of not having to allocate an extra array for the 
	 * merging process.  Obtaining a good running time requires the choice of a 
	 * good pivot element. */
	public static void quickSort(int[] data, int first, int n) {
		
		if (n > 1) {
			int pivotIndex = partition(data, first, n);
			int n1 = pivotIndex - first;
			// notice why this is not n2 = n - pivotIndex - 1:
			int n2 = n - n1 - 1;
			quickSort(data, first, n1);
			quickSort(data, pivotIndex + 1, n2);
		}
	}
	private static void bubbleDown(int[] data, int root, int n) {
		int child = 2*root + 1;
		int temp;
		
		if ((child + 1) < n && data[child + 1] > data[child])
			child++;
		if (child < n && data[child] > data[root]) {
			temp = data[root];
			data[root] = data[child];
			data[child] = temp;
			bubbleDown(data, child, n);
		}
		
	}
	/*This is the more efficient way to create a heap as outlined in 
	 * the foundations book and Programming Project #5 at the end of 
	 * chapter 12.  The other way you can heapify an array is by 
	 * starting at the front on the heap and adding elements one-by-one
	 * and then bubblingUp (reheapifying up) those elements.  That process 
	 * is detailed on page 656 of chapter 12, */
	private static void heapify(int[] data, int n) {
		
		for (int root = ((n - 1) - 1) / 2; root >= 0; root--)
			bubbleDown(data, root, n);
	}
	/*Building the heap requires O(nlogn) operations, and then pulling the 
	 * elements out of the heap requires another O(nlogn) operations.  Therefore
	 * the worst-case running time for heapsort is O(nlogn).  The average case running
	 * time is also O(nlogn).  Heapsort combines the time efficiency of mergesort 
	 * with the space efficiency of quicksort. */
	public static void heapSort(int[] data, int n) {
		int temp;
		int unsorted;
		heapify(data, n);
		unsorted = n;
		
		while (unsorted > 1) {
			unsorted--;
			temp = data[0];
			data[0] = data[unsorted];
			data[unsorted] = temp;
			bubbleDown(data, 0, unsorted);
		}
	}
	public static void main(String[] args) {
		int[] myArray = {23, 52, 3, 12, -1, 9, 0, 235, 124, 333, 43};
		
		quickSort(myArray, 0, 11);
		
		for (int element: myArray)
			System.out.print(element + " ");
		
		quickSort(myArray, 0, 11);
		System.out.println();
		for (int element: myArray)
			System.out.print(element + " ");
		
		int[] array2 = {100};
		
		quickSort(array2, 0, 1);
		System.out.println();
		for (int element: array2)
			System.out.print(element + " ");
		/*
		heapSort(myArray, 11);
		for (int element: myArray)
			System.out.print(element + " ");*/
	}
}
