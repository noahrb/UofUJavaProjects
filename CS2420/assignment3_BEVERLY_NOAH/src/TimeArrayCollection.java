package assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import assignment3.ArrayCollectionTest.StringComparator;

public class TimeArrayCollection {
	private static Random rand;

	public static void main(String[] args) {
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		for(int i = 1000; i < 21000;) {
			timeMethod(i); 
			i = i + 1000;
		}
	}
	// TODO: Write code to time your toSortedList, contains, and
	// SearchUtil.binarySearch methods so you can plot the results.

	private static void timeMethod(int timesToRun) {
		ArrayCollection<Integer> testarr = new ArrayCollection<>();

		for (long i = 0; i < timesToRun; i++) {
			testarr.add(randomInt());
		}


		ArrayList<Integer> sortedArray = testarr.toSortedList(new IntegerComparator());
		long startTime, midpointTime, stopTime;

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.
		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}

		// Now, run the test.
		long timesToLoop = 100000;

		startTime = System.nanoTime();

		for (long i = 0; i < timesToLoop; i++) {
			SearchUtil.binarySearch(sortedArray, randomInt(), new IntegerComparator());
		}
		// testarr.toSortedList(new IntegerComparator());

		midpointTime = System.nanoTime();

		// Run an empty loop to capture the cost of running the loop.

		for (long i = 0; i < timesToLoop; i++) { // empty block
		}

		stopTime = System.nanoTime();

		// Compute the time, subtract the cost of running the loop
		// from the cost of running the loop and computing square roots.
		// Average it over the number of runs.

		double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

		System.out.println("N = " + timesToRun + "It takes exactly " + averageTime + " nano seconds");

	}

	public static Integer randomInt() {
		return rand.nextInt();
	}

}
