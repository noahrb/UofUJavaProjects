package assignment5;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
/**
 * 
 * @author Noah Beverly and Joshua Whisenant
 * A series of algorithms related to sorting
 * @param <T>
 *
 */
public class SortUtil<T> {

	/**
	 * Performs a merge sort on the specified input
	 * @param arr ArrayList holding the input to be sorted
	 * @param cmp Input Comparator
	 */
	public static <T> void mergeSort(ArrayList<T> arr, Comparator<? super T> cmp) {

		ArrayList<T> temp = new ArrayList<T>(arr.size());

		for(int i=0; i < arr.size(); i++) {
			temp.add(null);
		}

		mergeSortRecursive(arr, temp, 0, arr.size()-1, cmp);
	}

	/**
	 * Performs a quick sort on the specified input
	 * @param arr ArrayList holding the input to be sorted
	 * @param cmp Input Comparator
	 */
	public static <T> void quickSort(ArrayList<T> arr, Comparator<? super T> cmp) {
		if (arr.size() == 2) {
			if (cmp.compare(arr.get(0), arr.get(1)) > 0) {
				Swap (arr, 0, 1);
			}
		} else if (arr.size() != 1) {
			quickSortRecursive(arr,0,arr.size()-1,cmp);
		}
	}
	
	/**
	 * Helper method to use on MergeSort (For internal use only)
	 * @param arr Input Items
	 * @param temp	Temporary array that helps break down the sort
	 * @param start	Start index of the specified values 
	 * @param end	End index of the specified values
	 * @param cmp	Input Comparator
	 */
	private static <T> void mergeSortRecursive(ArrayList<T> arr, ArrayList<T> temp, int start, int end, Comparator<? super T> cmp) {

		if (start + 5 >= end) { 
			insertionSort(arr, start, end, cmp);
			return;
		}

		int mid = start + (end -start) / 2; 

		//		System.out.println("start: " + start);
		//		System.out.println("mid: " + mid);
		//		System.out.println("end: " + end);

		mergeSortRecursive(arr, temp, start, mid, cmp); // left half
		mergeSortRecursive(arr, temp, mid+1, end, cmp); // right half

		Merge(arr, temp, start, mid, end, cmp); //merge halves
	}

	/**
	 * Helper method to use on QuickSort (For internal use only)
	 * @param arr Input Items
	 * @param temp	Temporary array that helps break down the sort
	 * @param start	Start index of the specified values 
	 * @param end	End index of the specified values
	 * @param cmp	Input Comparator
	 */
	private static <T> void quickSortRecursive(ArrayList<T> arr, int leftBound, int rightBound, Comparator<? super T> cmp) {

		if (leftBound<rightBound) {

			// does the actual nuts and bolts of quickSorting, returns the index of the found pivot
			int thisPivotIndex = performQuickSort(arr, leftBound, rightBound, cmp);

			quickSortRecursive(arr, leftBound, thisPivotIndex-1, cmp);
			quickSortRecursive(arr, thisPivotIndex+1, rightBound, cmp);

		} else {
			
			// swap only if the second is less than the first, and if there is a right bound to swap with
			if (leftBound < arr.size() - 1 && cmp.compare(arr.get(leftBound), arr.get(leftBound+1)) > 0) {
				
				Swap (arr, leftBound, leftBound+1);
			}
			
		}

	}

	/**
	 * Helper method to use on QuickSort (For internal use only)
	 * @param arr Input Items
	 * @param leftBound		Start index of the specified values 
	 * @param rightBound 	End index of the specified values
	 * @param cmp	Input Comparator
	 * @return L 	the new index of the original pivot
	 */
	private static <T> int performQuickSort (ArrayList<T> arr, int leftBound, int rightBound, Comparator <? super T> cmp) {
		int pivotIndex = findPivotIndex(arr, leftBound, rightBound, cmp);
		T pivot = arr.get(pivotIndex);

		Swap(arr,pivotIndex,rightBound);

		int L = leftBound, R = rightBound-1;

		while(L <= R) {	

			while (L < rightBound && cmp.compare(arr.get(L), pivot) <= 0) {
				L++;
			}

			while (R >= 0 && cmp.compare(arr.get(R), pivot) >0) {
				R--;
			}

			if(L < R) {
				Swap(arr, L, R);
			}
		}

		Swap(arr, L, rightBound);
		return L;
	}

	/**
	 * Helper method to use on mergeSort (for internal use only)
	 * @param arr	array to merge
	 * @param temp	temporary array to store merged values
	 * @param start	start index of merging
	 * @param mid	start index of second array to merge
	 * @param end	end index of portion of array that is being merged
	 * @param cmp 	input comparator
	 */
	private static <T> void Merge (ArrayList<T> arr, ArrayList<T> temp, int start, int  mid, int end, Comparator<? super T> cmp){



		int i1 = start;
		int i2 = mid+1;
		int currentTempIndex = start;
		/*
		 *  NOTE: start is the first to include;
		 *  mid is the last index to include in the first half
		 *  end is the last index to include (in the second half)
		 */
		while(i1 <= mid && i2 <= end) {

			if (cmp.compare(arr.get(i1), arr.get(i2)) <= 0) {
				temp.set(currentTempIndex,arr.get(i1));
				i1++;
				currentTempIndex++;
			}


			if (cmp.compare(arr.get(i2), arr.get(i1)) < 0) {
				temp.set(currentTempIndex, arr.get(i2));
				i2++;
				currentTempIndex++;
			}

		}

		while (i1 <= mid ) {
			temp.set(currentTempIndex,arr.get(i1));
			i1++;
			currentTempIndex++;
		}

		while (i2 <= end ) {
			temp.set(currentTempIndex,arr.get(i2));
			currentTempIndex++;
			i2++;
		}

		for (int j = start; j <= end; j++) {
			arr.set(j, temp.get(j));
		}

	}	

	/**
	 * Swaps two items in an array (for internal use only)
	 * @param arr	array to swap
	 * @param index1	index of first item to swap
	 * @param index2	index of second item to swap
	 */
	private static <T> void Swap (ArrayList<T> arr, int index1, int index2) {
		T temp = arr.get(index1);
		arr.set(index1, arr.get(index2));
		arr.set(index2, temp);
	}

	/**
	 * Helper method to find the pivot index. Helper method for quickSort (for internal use only)
	 * This method simply uses the middle item in the array as pivot
	 * @param arr	array to find the pivot index in
	 * @param leftBound	left hand bound of the array to be quickSorted
	 * @param rightBound right hand bound of the array to be quickSorted
	 * @param cmp	input comparator
	 * @return	index of the pivot to use
	 */
	private static <T> Integer findPivotIndex (ArrayList<T> arr, int leftBound, int rightBound, Comparator<? super T> cmp) {
		return leftBound + (rightBound - leftBound)/2;
	}

	/**
	 * Helper method to use on MergeSort. Is called by mergesort when values are small (For internal use only)
	 * @param arr Input Items
	 * @param start	Start index of the specified values 
	 * @param end	End index of the specified values
	 * @param cmp	Input Comparator
	 */
	private static <T> void insertionSort(ArrayList<T> arr, int startIndex, int endIndex, Comparator<? super T> cmp) {
		for (int i = startIndex+1; i <= endIndex; i++) {

			T currentVal = arr.get(i);
			int currentValIndex = i;

			for (int j = i; j >= startIndex; j--) {
				if (cmp.compare(arr.get(currentValIndex), arr.get(j)) < 0) {
					T tempJ = arr.get(j);
					arr.set(currentValIndex -1, currentVal);
					arr.set(currentValIndex, tempJ);
					currentValIndex--;
				}
			}
		}
	}

	

	/**
	 * Method used to generate best case for use in testing the sorting algorithm
	 * returns array list with numbers between 1 and size (inclusive), in order
	 * @param size	size of list to return
	 * @return	already sorted integer arrayList. 
	 */
	public static ArrayList<Integer> generateBestCase(int size) {
		// This method generates and returns an ArrayList of integers 1 to size in ascending order.
		ArrayList<Integer> out = new ArrayList<Integer>();
		for (Integer addToArrayList = 1; addToArrayList<size+1;addToArrayList++) {
			out.add(addToArrayList);
		}
		return out;
	}
	
	/**
	 * Method used to generate average case for use in testing the sorting algorithm
	 * returns array list with numbers between 1 and size (inclusive), in random order
	 * @param size	size of list to return
	 * @return	unsorted integer arrayList. 
	 */
	public static ArrayList<Integer> generateAverageCase(int size) {
		Random rand = new Random();
		ArrayList<Integer> out = new ArrayList<Integer>();
		while (out.size() < size) {
			Integer addToArrayList = rand.nextInt(size) + 1;
			if (!out.contains(addToArrayList)) {
				out.add(addToArrayList);
			}
		}
		return out;
	}

	/**
	 * Method used to generate worst case for use in testing the sorting algorithm
	 * returns array list with numbers between 1 and size (inclusive), in reverse order
	 * @param size	size of list to return
	 * @return	totally backwords sorted integer arrayList. 
	 */
	public static ArrayList<Integer> generateWorstCase(int size) {
		ArrayList<Integer> out = new ArrayList<Integer>();
		for (Integer addToArrayList = size; addToArrayList>0;addToArrayList--) {
			out.add(addToArrayList);
		}
		return out;
	}


}

