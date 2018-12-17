package comprehensive;
import java.util.*;

public class ComprehensiveAnalysis {

	static int TIMES_TO_TEST = 10;
	static int MIN_SIZE = 100;
	static int MAX_SIZE = 5000;
	static int INCREMENT = 100;

	public static void main(String[]args) {
	
		long warmUpTime = System.nanoTime();
		// warm up thread
		while (System.nanoTime() < warmUpTime + 1000000000) {

		}

		for (int i = MIN_SIZE; i < MAX_SIZE; i+=INCREMENT) {
			ArrayList<GrammarTree> grammarTrees = new ArrayList<GrammarTree> ();

			for (int j = 0; j < TIMES_TO_TEST; j++) {
				GrammarTree toAdd = new GrammarTree(100,0,i);
				grammarTrees.add(toAdd);
			}

			long startTime = System.nanoTime();

			for (int j = 0; j < TIMES_TO_TEST; j++) {
				grammarTrees.get(j).generateRandomPhrase();
			}

			long midTime = System.nanoTime();

			for (int j = 0; j < TIMES_TO_TEST; j++) {
				grammarTrees.get(j);
			}

			long endTime = System.nanoTime();

			long timeToRun = (((midTime-startTime) - (endTime-midTime))/TIMES_TO_TEST);

			System.out.println(timeToRun);
		}
	}

}
