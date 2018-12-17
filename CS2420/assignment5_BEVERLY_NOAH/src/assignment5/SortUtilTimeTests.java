package assignment5;
import java.util.*;

public class SortUtilTimeTests {

	static int TIMES_TO_TEST = 10;


	public static void main (String args[]) {

		int startArraySize = 100;
		int maxArraySize = 10000;
		int increment = 100;
		
		for (int currentSize = startArraySize; currentSize <= maxArraySize; currentSize += increment) {

			ArrayList<ArrayList<Integer>> listOfTestArrayLists = new ArrayList<ArrayList<Integer>>();

			// sets up an arrayList of arraylists to test:
			for (int i = 0; i < TIMES_TO_TEST; i++) {
				listOfTestArrayLists.add(SortUtil.generateAverageCase(currentSize));
			}

			IntegerComparator cmp = new IntegerComparator();

			Long emptyBlockTime = System.nanoTime();

			while (System.nanoTime() - emptyBlockTime < 1000000000) {
				// empty block
			}

			Long startTime = System.nanoTime();

			for (int i = 0; i < listOfTestArrayLists.size(); i++) {
				SortUtil.mergeSort(listOfTestArrayLists.get(i), cmp);
			}

			Long midTime = System.nanoTime();

			// empty loop (but performs the get that is performed in the timing
			for (int i = 0; i < listOfTestArrayLists.size(); i++) {
				listOfTestArrayLists.get(i);
			}

			Long endTime = System.nanoTime();

			double averageTime = ((midTime - startTime) - (endTime - midTime))
					/ TIMES_TO_TEST;

			System.out.println(currentSize + "\t" + averageTime);
		}
	}
}
