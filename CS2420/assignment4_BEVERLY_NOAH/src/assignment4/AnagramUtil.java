package assignment4;

import java.util.Comparator;

/**
 * 
 * @author Spencer Bonds and Noah Beverly.
 *
 */
public class AnagramUtil {

	/**
	 * Returns the sorted version of the string using lexicographical ordering. This
	 * is accomplished using insertion sort.
	 * 
	 * @param str Input String to be sorted.
	 * @return A sorted String.
	 */
	public static String sort(String str) {
		String returnString = "";
		Character[] charArray = new Character[str.length()];

		for (int i = 0; i < str.length(); i++) {
			char charI = str.charAt(i);
			charArray[i] = charI;
		}

		insertionSort(charArray, new CharacterComparator());
		for (int i = 0; i < charArray.length; i++) {
			returnString = returnString + charArray[i];
		}
		return returnString;
	}

	/**
	 * Sorts the input array using insertion sort and the input Comparator.
	 * 
	 * @param input Array to be sorted
	 * @param cmp   Comparator used to compare.
	 */
	public static <T> void insertionSort(T[] input, Comparator<? super T> cmp) {

		for (int i = 1; i < input.length; i++) {
			T inputI = input[i];
			int j;

			for (j = i; j > 0; j--) {
				int tCompare = cmp.compare(inputI, input[j - 1]);
				if (tCompare >= 0) {
					break;
				}
				input[j] = input[j - 1];
			}
			input[j] = inputI;
		}
	}

	/**
	 * Sorts the input array using Shellsort and a provided comparator.
	 * 
	 * @param input Generic type array to be sorted.
	 * @param cmp   Comparator passed in to determine the sort.
	 */
	public static <T> void shellSort(T[] input, Comparator<? super T> cmp) {
		for (int gapSize = input.length / 2; gapSize > 0; gapSize /= 2) {
			for (int i = gapSize; i < input.length; i++) {
				T inputI = input[i];
				int j;

				for (j = i - gapSize; j >= 0 && cmp.compare(input[j], inputI) > 0; j -= gapSize) {
					input[j + gapSize] = input[j];
				}
				input[j + gapSize] = inputI;
			}
		}
	}

	/**
	 * Check if input strings are anagrams. Returns true if they are, false
	 * otherwise.
	 * 
	 * @param str1 String to be checked for anagram with other.
	 * @param str2 Other String to be checked for anagram with the first String.
	 * @return Returns a boolean value whether or not the two Strings are anagrams.
	 */
	public static boolean areAnagrams(String str1, String str2) {
		if (str1.length() != str2.length()) {
			return false;
		}
		if (sort(str1).toLowerCase().equals(sort(str2).toLowerCase())) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the largest group of anagrams in an input String array. Returns a String
	 * Array of those values if any.
	 * 
	 * @param str String Array to be checked for largest anagram groups.
	 * @return Returns a string array of the largest anagram group.
	 */
	public static String[] getLargestAnagramGroup(String[] str) {
		// copy of the input array
		String[] temp = str;

		// Sort the temp array using the anagramComparator
		shellSort(temp, new AnagramComparator());
		//insertionSort(temp, new AnagramComparator());
		//Arrays.sort(temp, new AnagramComparator());
		// the size of the largest anagram group
		int groupSize = 0;
		// the beginning index of the largest anagram group in temp.
		int begginingIndex = 0;
		// the beginning index of the possible largest group
		int testBegIndex = 0;
		// counter for the while loop
		int j = 0;
		while (j < temp.length) {
			int i = testBegIndex + 1;
			int testSize = 0;
			// a loop that increments the final index and size of the anagram group.
			while (i < temp.length && areAnagrams(temp[testBegIndex], temp[i])) {
				i++;
				testSize++;
			}

			// add the final anagram to the group
			testSize++;
			if (testSize > groupSize) {
				groupSize = testSize;
				begginingIndex = testBegIndex;
			}
			testBegIndex = i;
			j = i;
		}
		// initializes the result array
		String[] result = new String[0];
		// if the groupsize is larger than the one then fills "result" with the largest
		// anagrma group.
		if (groupSize > 1) {
			result = new String[groupSize];
			for (int i = 0; i < groupSize; i++) {
				result[i] = temp[begginingIndex + i];
			}
		}
		return result;
	}
}

//****Section with custom comparators used to compare and test.

//Anagram Comparator 
class AnagramComparator implements Comparator<String> {

	/**
	 * Takes left and right String inputs and sorts them to determine if they are
	 * anagrams. If anagrams, returns 0, if not, returns -1 or 1 accordingly.
	 */
	public int compare(String lhs, String rhs) {
		String lhs1 = AnagramUtil.sort(lhs).toLowerCase();
		String rhs1 = AnagramUtil.sort(rhs).toLowerCase();
		return lhs1.compareTo(rhs1);
	}
}

//String Comparator
class StringComparator implements Comparator<String> {
	/**
	 * 2 input strings, compares strings lexicographically, returns -1 if left <
	 * right, returns 1 if left > right, and 0 if they equal.
	 */
	public int compare(String lhs, String rhs) {
		return lhs.compareTo(rhs);
	}
}

//Integer Comparator
class IntegerComparator implements Comparator<Integer> {

	/**
	 * 2 input integers. Compares them. Returns -1 if left < right, returns 1 if
	 * left > right, and 0 if they equal.
	 */
	@Override
	public int compare(Integer o1, Integer o2) {
		return o1.compareTo(o2);
	}
}

//Double Comparator
class DoubleComparator implements Comparator<Double> {

	/**
	 * 2 input doubles. Compares them. Returns -1 if left < right, returns 1 if left
	 * > right, and 0 if they equal.
	 */
	@Override
	public int compare(Double o1, Double o2) {
		return o1.compareTo(o2);
	}
}

//Character Comparator
class CharacterComparator implements Comparator<Character> {

	/**
	 * 2 input doubles, compares doubles lexicographically, returns -1 if left <
	 * right, returns 1 if left > right, and 0 if they equal.
	 */
	public int compare(Character lhs, Character rhs) {
		Character lhs1 = Character.toLowerCase(lhs);
		Character rhs1 = Character.toLowerCase(rhs);
		return lhs1.compareTo(rhs1);
	}
}
