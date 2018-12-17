package assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class AnagramTest {

	public static <T> boolean containsAll(T[] array, T[] toCompareArray) {
		return Arrays.asList(array).containsAll(Arrays.asList(toCompareArray));
	}
	
	public static<T> boolean equals(T[] array, T[] toCompareArray) {
		if(array.length != toCompareArray.length) {
			return false;
		}
		
		for(int i = 0; i < array.length; i++) {
			if(!array[i].equals(toCompareArray[i])) {
				return false;
			}
		}
		
		return true;
	}
	
	
	
	// *******************SORT TESTS*********************
	// Mixed Capitalization
	@Test
	void sortTest1() {
		assertEquals("aBCDef", AnagramUtil.sort("DfaBeC"));
	}

	// All Uppercase String Test
	@Test
	void sortTest2() {
		assertEquals("ABCDEF", AnagramUtil.sort("DBAECF"));
	}

	// All lowercase string test
	@Test
	void sortTest3() {
		assertEquals("abcdef", AnagramUtil.sort("dbaecf"));
	}

	// String with Numbers Test
	@Test
	void sortTest5() {
		assertEquals("12ABCDEF", AnagramUtil.sort("ABC1DEF2"));
	}

	// Empty String Test
	@Test
	void sortTest6() {
		assertEquals("", AnagramUtil.sort(""));
	}
	
	// Test fail case
	@Test 
	void sortTest7() {
		assertFalse("efgh".equals(AnagramUtil.sort("abcd")));
	}
	
	// Test fail case, duplicate letter
	@Test 
	void sortTest8() {
		assertFalse("abcd".equals(AnagramUtil.sort("abcdd")));
	}

	// *******************INSERTION SORT TESTS*********************
	// String Type Test
	@Test
	void insertionSortTest1() {
		String[] test = new String[6];
		test[0] = "abc";
		test[1] = "bca";
		test[2] = "acb";
		test[3] = "dca";
		test[4] = "bac";
		test[5] = "cbd";

		AnagramUtil.insertionSort(test, new StringComparator());

		String[] result = new String[6];
		result[0] = "abc";
		result[1] = "acb";
		result[2] = "bac";
		result[3] = "bca";
		result[4] = "cbd";
		result[5] = "dca";

		assertTrue(equals(test, result));
	}

	// Int Type Test
	@Test
	void insertionSortTest2() {
		Integer[] test = new Integer[6];
		test[0] = 2;
		test[1] = 8;
		test[2] = 1;
		test[3] = 7;
		test[4] = 3;
		test[5] = 9;

		AnagramUtil.insertionSort(test, new IntegerComparator());

		Integer[] result = new Integer[6];
		result[0] = 1;
		result[1] = 2;
		result[2] = 3;
		result[3] = 7;
		result[4] = 8;
		result[5] = 9;

		assertTrue(equals(test, result));
	}

	// Other Type Test
	@Test
	void insertionSortTest3() {
		Double[] test = new Double[6];
		test[0] = 2.3;
		test[1] = 3.6;
		test[2] = 4.6;
		test[3] = 2.1;
		test[4] = 1.2;
		test[5] = 0.8;

		AnagramUtil.insertionSort(test, new DoubleComparator());

		Double[] result = new Double[6];
		result[0] = 0.8;
		result[1] = 1.2;
		result[2] = 2.1;
		result[3] = 2.3;
		result[4] = 3.6;
		result[5] = 4.6;

		assertTrue(equals(test, result));
	}

	// Duplicate Test
	@Test
	void insertionSortTest4() {
		String[] test = new String[6];
		test[0] = "abc";
		test[1] = "bca";
		test[2] = "acb";
		test[3] = "dca";
		test[4] = "bac";
		test[5] = "dca";

		AnagramUtil.insertionSort(test, new StringComparator());

		String[] result = new String[6];
		result[0] = "abc";
		result[1] = "acb";
		result[2] = "bac";
		result[3] = "bca";
		result[4] = "dca";
		result[5] = "dca";

		assertTrue(equals(test, result));
	}

	// Empty Test
	@Test
	void insertionSortTest5() {
		String[] test = new String[0];

		AnagramUtil.insertionSort(test, new StringComparator());

		String[] result = new String[0];

		assertTrue(equals(test, result));
	}

	// Already Sorted
	@Test
	void insertionSortTest6() {
		String[] test = new String[6];
		test[0] = "abc";
		test[1] = "acb";
		test[2] = "bac";
		test[3] = "bca";
		test[4] = "cbd";
		test[5] = "dca";

		AnagramUtil.insertionSort(test, new StringComparator());

		String[] result = new String[6];
		result[0] = "abc";
		result[1] = "acb";
		result[2] = "bac";
		result[3] = "bca";
		result[4] = "cbd";
		result[5] = "dca";

		assertTrue(containsAll(test, result));
	}

	// Just One
	@Test
	void insertionSortTest7() {
		String[] test = new String[1];
		test[0] = "abc";

		AnagramUtil.insertionSort(test, new StringComparator());

		String[] result = new String[1];
		result[0] = "abc";

		assertTrue(equals(test, result));
	}

	// *******************SHELL SORT TESTS*********************
	// String Type Test
	@Test
	void shellSortTest1() {
		String[] test = new String[6];
		test[0] = "abc";
		test[1] = "bca";
		test[2] = "acb";
		test[3] = "dca";
		test[4] = "bac";
		test[5] = "cbd";

		AnagramUtil.shellSort(test, new StringComparator());

		String[] result = new String[6];
		result[0] = "abc";
		result[1] = "acb";
		result[2] = "bac";
		result[3] = "bca";
		result[4] = "cbd";
		result[5] = "dca";

		assertTrue(equals(test, result));
	}

	// Int Type Test
	@Test
	void shellSortTest2() {
		Integer[] test = new Integer[6];
		test[0] = 2;
		test[1] = 8;
		test[2] = 1;
		test[3] = 7;
		test[4] = 3;
		test[5] = 9;

		AnagramUtil.shellSort(test, new IntegerComparator());

		Integer[] result = new Integer[6];
		result[0] = 1;
		result[1] = 2;
		result[2] = 3;
		result[3] = 7;
		result[4] = 8;
		result[5] = 9;

		assertTrue(equals(test, result));
	}

	// Other Type Test
	@Test
	void shellSortTest3() {
		Double[] test = new Double[6];
		test[0] = 2.3;
		test[1] = 3.6;
		test[2] = 4.6;
		test[3] = 2.1;
		test[4] = 1.2;
		test[5] = 0.8;

		AnagramUtil.shellSort(test, new DoubleComparator());

		Double[] result = new Double[6];
		result[0] = 0.8;
		result[1] = 1.2;
		result[2] = 2.1;
		result[3] = 2.3;
		result[4] = 3.6;
		result[5] = 4.6;

		assertTrue(equals(test, result));
	}

	// Duplicate Test
	@Test
	void shellSortTest4() {
		String[] test = new String[6];
		test[0] = "abc";
		test[1] = "bca";
		test[2] = "acb";
		test[3] = "dca";
		test[4] = "bac";
		test[5] = "dca";

		AnagramUtil.shellSort(test, new StringComparator());

		String[] result = new String[6];
		result[0] = "abc";
		result[1] = "acb";
		result[2] = "bac";
		result[3] = "bca";
		result[4] = "dca";
		result[5] = "dca";

		assertTrue(equals(test, result));
	}

	// Empty Test
	@Test
	void shellSortTest5() {
		String[] test = new String[0];

		AnagramUtil.shellSort(test, new StringComparator());

		String[] result = new String[0];

		assertTrue(equals(test, result));
	}

	// Already Sorted
	@Test
	void shellSortTest6() {
		String[] test = new String[6];
		test[0] = "abc";
		test[1] = "acb";
		test[2] = "bac";
		test[3] = "bca";
		test[4] = "cbd";
		test[5] = "dca";

		AnagramUtil.shellSort(test, new StringComparator());

		String[] result = new String[6];
		result[0] = "abc";
		result[1] = "acb";
		result[2] = "bac";
		result[3] = "bca";
		result[4] = "cbd";
		result[5] = "dca";

		assertTrue(equals(test, result));
	}

	// Just One
	@Test
	void shellSortTest7() {
		String[] test = new String[1];
		test[0] = "abc";

		AnagramUtil.shellSort(test, new StringComparator());

		String[] result = new String[1];
		result[0] = "abc";

		assertTrue(equals(test, result));
	}

	// *******************ARE ANAGRAMS TESTS*********************
	// Test success case
	@Test
	void areAnagramsTest1() {
		assertTrue(AnagramUtil.areAnagrams("FBCDAE", "ABCDEF"));
	}

	// Test mixed capital and Lower case
	@Test
	void areAnagramsTest2() {
		assertTrue(AnagramUtil.areAnagrams("FBcDAe", "AbCDEf"));
	}

	// Test failure case
	@Test
	void areAnagramsTest3() {
		assertTrue(AnagramUtil.areAnagrams("FBCDAE", "ABCDEF"));
	}

	// Test failure case, different lengths
	@Test
	void areAnagramsTest4() {
		assertFalse(AnagramUtil.areAnagrams("FBCDAEELSI", "ABCDEF"));
	}


	// *******************GET LARGEST ANAGRAM GROUP TESTS*********************
	// Group is at front of array
	@Test
	void getLargestAnagramGroupTest1() {
		String[] test = new String[6];
		test[0] = "LEVO";
		test[1] = "LEOV";
		test[2] = "LOVE";
		test[3] = "TLWNAO";
		test[4] = "TWLANEWSO";
		test[5] = "TACOS";

		String[] result = new String[3];
		result[0] = "LEVO";
		result[1] = "LEOV";
		result[2] = "LOVE";

		assertTrue(equals(AnagramUtil.getLargestAnagramGroup(test), result));
	}

	// Group is at back of array
	@Test
	void getLargestAnagramGroupTest2() {
		String[] test = new String[6];

		test[0] = "TLWNAO";
		test[1] = "TWLANEWSO";
		test[2] = "TACOS";
		test[3] = "LEVO";
		test[4] = "LEOV";
		test[5] = "LOVE";

		String[] result = new String[3];
		result[0] = "LEVO";
		result[1] = "LEOV";
		result[2] = "LOVE";

		assertTrue(equals(AnagramUtil.getLargestAnagramGroup(test), result));
	}

	// empty
	@Test
	void getLargestAnagramGroupTest3() {
		String[] test = new String[0];

		String[] result = new String[0];

		assertTrue(equals(AnagramUtil.getLargestAnagramGroup(test), result));
	}

	// just one word
	@Test
	void getLargestAnagramGroupTest4() {
		String[] test = new String[1];
		test[0] = "LEVO";

		String[] result = new String[0];

		assertTrue(equals(AnagramUtil.getLargestAnagramGroup(test), result));
	}

	// One anagram group of 2
	@Test
	void getLargestAnagramGroupTest5() {
		String[] test = new String[5];
		test[0] = "LEVO";
		test[1] = "TLWNAO";
		test[2] = "LEOV";
		test[3] = "TWLANEWSO";
		test[4] = "TACOS";

		String[] result = new String[2];
		result[0] = "LEVO";
		result[1] = "LEOV";

		assertTrue(equals(AnagramUtil.getLargestAnagramGroup(test), result));
	}

	// One Group of 5
	@Test
	void getLargestAnagramGroupTest6() {
		String[] test = new String[10];
		test[0] = "LEVO";
		test[1] = "LEOV";
		test[2] = "TLWNAO";
		test[3] = "LOVE";
		test[4] = "TWLANEWSO";
		test[5] = "TACOS";
		test[6] = "SOCAT";
		test[7] = "OSCAT";
		test[8] = "ELVO";
		test[9] = "VOLE";

		String[] result = new String[5];
		result[0] = "LEVO";
		result[1] = "LEOV";
		result[2] = "LOVE";
		result[3] = "ELVO";
		result[4] = "VOLE";

		assertTrue(containsAll(AnagramUtil.getLargestAnagramGroup(test), result));
	}

	// Using sample_word_list.txt
	@Test
	void getLargestAnagramGroupTest7() {
		String[] input = AnagramTester.readFile("sample_word_list.txt");
		String[] expected = new String[7];
		expected[0] = "Reacts";
		expected[1] = "Caters";
		expected[2] = "carets";
		expected[3] = "traces";
		expected[4] = "crates";
		expected[5] = "recast";
		expected[6] = "caster";
		
		assertTrue(equals(AnagramUtil.getLargestAnagramGroup(input), expected));
	}
	
	// Using moderate_word_list.txt
	@Test
	void getLargestAnagramGroupTest8() {
		String[] input = AnagramTester.readFile("moderate_word_list.txt");
		
		String[] expected = new String[2];
		expected[0] = "cat";
		expected[1] = "act";
		
		assertTrue(containsAll(AnagramUtil.getLargestAnagramGroup(input), expected));
	}
}
