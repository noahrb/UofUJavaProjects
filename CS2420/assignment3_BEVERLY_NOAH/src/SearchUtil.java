package assignment3;

import java.util.ArrayList;
import java.util.Comparator;

public class SearchUtil {

	
	/**
	 * 
	 * @param <T>	The type of elements contained in the list
	 * @param list	An ArrayList to search through. 
	 * 				This ArrayList is assumed to be sorted according 
	 * 				to the supplied comparator. This method has
	 * 				undefined behavior if the list is not sorted. 
	 * @param item	The item to search for
	 * @param cmp	Comparator for comparing Ts or a super class of T
	 * @return		true if "item" exists in "list", false otherwise
	 */
	public static <T> boolean binarySearch(ArrayList<T> list, T item, Comparator<? super T> cmp)
	{
		boolean itemExists = false;
		// Our binarySearch method is recursive.
		// Therefore, each of these local variables will be updated on each call
		
		int begIndex = 0;
		int endIndex = list.size() -1;
		int midIndex = endIndex /2;
		
		T begItem = list.get(begIndex);
		T endItem = list.get(endIndex);
		T midItem = list.get(midIndex);
		
		/**
		 * We have broken down the search into four cases:
		 * Case 0: The item is to the left of the start or to the right of the end.
		 * Case 1: The item is at the start, middle, or end.
		 * Case 2: The item is to the left of the middle element.
		 * Case 3: The item is to the right of the middle element.
		 */
		
		if ( (cmp.compare(item, begItem) < 0) || (cmp.compare(item, endItem) > 0)) {
			return false;
		}
		
		// Case 1: The item is at the start, middle, or end
		if (cmp.compare(item, begItem) == 0 || cmp.compare(item, midItem) == 0 || cmp.compare(item, endItem) == 0) {
			return true;
		}
		
		// Case 2: The item is to the left of the middle element
		if (cmp.compare(item, midItem) < 0) {
			// make a new ArrayList comprised of the elements from list.get(0) to list.get(midIndex - 1)
			ArrayList<T> newList = new ArrayList<T>();
			for (int i = 0; i < midIndex; i++) {
				newList.add(list.get(i));
			}
			if (binarySearch(newList, item, cmp)) {
				itemExists = true;
				return true;
			}
		}
		
		// Case 3: The item is to the right of the middle element
		if (cmp.compare(item, midItem) > 0) {
			ArrayList<T> newList = new ArrayList<T>();
			// make a new ArrayList comprised of the elements from list.get(midIndex) to list.get(endIndex - 1)
			for (int i = midIndex; i < endIndex; i++) {
				newList.add(list.get(i));
			}
			if (binarySearch(newList, item, cmp)) {
				itemExists = true;
				return true;
			}
		}
		
		return itemExists;
	}	
}