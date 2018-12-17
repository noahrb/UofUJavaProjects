package assignment5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AssignmentFiveTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	// **********************MERGE SORT TESTS*******************************

	// merge sort small
		@Test
		void testMergesortSmall() {
			IntegerComparator cmp = new IntegerComparator();
			ArrayList<Integer> arr = new ArrayList<Integer>();

			arr = SortUtil.generateAverageCase(10);
			SortUtil.mergeSort(arr, cmp);

			assertTrue(isSorted(arr, cmp));
		}

		// merge sort medium
		@Test
		void testMergeSortMedium() {
			IntegerComparator cmp = new IntegerComparator();
			ArrayList<Integer> arr = new ArrayList<Integer>();

			arr = SortUtil.generateAverageCase(100);
			SortUtil.mergeSort(arr, cmp);

			assertTrue(isSorted(arr, cmp));
		}

		// merge sort large
		@Test
		void testMergeSortLarge() {
			IntegerComparator cmp = new IntegerComparator();
			ArrayList<Integer> arr = new ArrayList<Integer>();

			arr = SortUtil.generateAverageCase(1000);
			SortUtil.mergeSort(arr, cmp);

			assertTrue(isSorted(arr, cmp));
		}

		@Test
		void testMergeSortTwoItemList() {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			IntegerComparator cmp = new IntegerComparator();
			arr.add(500); // 0
			arr.add(400); // 1
			SortUtil.mergeSort(arr, cmp);

			assertTrue(isSorted(arr, cmp));
		}

		@Test
		void testMergeSortOneItemList() {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			IntegerComparator cmp = new IntegerComparator();
			arr.add(500);
			SortUtil.mergeSort(arr, cmp);

			assertTrue(isSorted(arr, cmp));
		}
		
		@Test
		void testMergeSortEmptyItemList() {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			IntegerComparator cmp = new IntegerComparator();

			SortUtil.mergeSort(arr, cmp);

			assertTrue(isSorted(arr, cmp));
		}

		// merge sort with Duplicates
		@Test
		void testMergeSortWithDuplicates() {
			IntegerComparator cmp = new IntegerComparator();
			ArrayList<Integer> arr = new ArrayList<Integer>();

			arr = SortUtil.generateAverageCase(100);

			arr.set(30, 39);
			arr.set(48, 39);
			arr.set(21, 39);

			SortUtil.mergeSort(arr, cmp);

			assertTrue(isSorted(arr, cmp));
		}

		// merge sort String
		@Test
		void testMergesortString() {
			StringComparator cmp = new StringComparator();
			ArrayList<String> arr = new ArrayList<String>();
			arr.add("slknes");
			arr.add("aslneso");
			arr.add("aalneksl");
			arr.add("salens");
			arr.add("qlaskdnso");
			SortUtil.mergeSort(arr, cmp);

			assertTrue(isSorted(arr, cmp));
		}

	// **********************QUICK SORT TESTS*******************************
	
	// quick sort small
	@Test
	void testQuicksortSmall() {
		IntegerComparator cmp = new IntegerComparator();
		ArrayList<Integer> arr = new ArrayList<Integer>();

		arr = SortUtil.generateAverageCase(10);
		SortUtil.quickSort(arr, cmp);

		assertTrue(isSorted(arr, cmp));
	}

	// quick sort medium
	@Test
	void testQuickSortMedium() {
		IntegerComparator cmp = new IntegerComparator();
		ArrayList<Integer> arr = new ArrayList<Integer>();

		arr = SortUtil.generateAverageCase(100);
		SortUtil.quickSort(arr, cmp);

		assertTrue(isSorted(arr, cmp));
	}

	// quick sort large
	@Test
	void testQuickSortLarge() {
		IntegerComparator cmp = new IntegerComparator();
		ArrayList<Integer> arr = new ArrayList<Integer>();

		arr = SortUtil.generateAverageCase(1000);
		SortUtil.quickSort(arr, cmp);

		assertTrue(isSorted(arr, cmp));
	}

	@Test
	void testQuickSortTwoItemList() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		IntegerComparator cmp = new IntegerComparator();
		arr.add(500); // 0
		arr.add(400); // 1
		SortUtil.quickSort(arr, cmp);

		assertTrue(isSorted(arr, cmp));
	}

	@Test
	void testQuickSortOneItemList() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		IntegerComparator cmp = new IntegerComparator();
		arr.add(500);
		SortUtil.quickSort(arr, cmp);

		assertTrue(isSorted(arr, cmp));
	}
	
	@Test
	void testQuickSortEmptyItemList() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		IntegerComparator cmp = new IntegerComparator();

		SortUtil.quickSort(arr, cmp);

		assertTrue(isSorted(arr, cmp));
	}

	// quick sort with Duplicates
	@Test
	void testQuickSortWithDuplicates() {
		IntegerComparator cmp = new IntegerComparator();
		ArrayList<Integer> arr = new ArrayList<Integer>();

		arr = SortUtil.generateAverageCase(100);

		arr.set(30, 39);
		arr.set(48, 39);
		arr.set(21, 39);

		SortUtil.quickSort(arr, cmp);

		assertTrue(isSorted(arr, cmp));
	}

	// quick sort String
	@Test
	void testQuicksortString() {
		StringComparator cmp = new StringComparator();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("slknes");
		arr.add("aslneso");
		arr.add("aalneksl");
		arr.add("salens");
		arr.add("qlaskdnso");
		SortUtil.quickSort(arr, cmp);

		assertTrue(isSorted(arr, cmp));
	}


	private <T> boolean isSorted(ArrayList<T> input1, Comparator<? super T> cmp) {
		for (int i = 0; i < input1.size() - 1; i++) {
			if (cmp.compare(input1.get(i), input1.get(i + 1)) > 0) {
				return false;
			}
		}
		return true;
	}

}
