package assignment4;

import java.util.Random;

/**
 * @author Daniel Kopta, Noah Beverly, Spencer Bonds CS2420 Assignment 4
 *
 */
public class TimeTester {
	private static Random rand;

	public static void main(String[] args) {
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		long startTime, midpointTime, stopTime;

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.
		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}

		// Now, run the test. Use 5 iterations for toSortedList since it's slow
		int timesToLoop = 5;

		// Instead of hard-coding N, we analyze growing values of N
		for (int N = 1000; N < 21000; N += 1000) {
			// for sort
			long size = N;

			// Generate the array of random inputs before starting the timer
			 String[] arr = generateRandomArray(N);

			// Generate the random strings before starting the timer
			
			/*String str1 = randomString(N);
			String str2 = randomString(N);*/

			// garbage = aci.toSortedList(new IntegerComparator());
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {

				// Time areAnagrams method
				//AnagramUtil.areAnagrams(str1, str2);
				AnagramUtil.getLargestAnagramGroup(arr);
			}

			midpointTime = System.nanoTime();

			// Run an empty loop to capture the cost of running the loop.

			for (long i = 0; i < timesToLoop; i++) { // empty block
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(size + "\t" + averageTime);
		}
	}

	public static String[] generateRandomArray(int N) {
		String[] aci = new String[N];
		for (int i = 0; i < aci.length; i++) {
			aci[i] = randomString(7);
		}

		return aci;
	}

	public static String randomString(int length) {

		String retval = "";
		for (int i = 0; i < length; i++) {
			// ASCII values a-z are contiguous (26 characters)
			retval += (char) ('a' + (rand.nextInt(26)));
		}
		return retval;
	}

}
