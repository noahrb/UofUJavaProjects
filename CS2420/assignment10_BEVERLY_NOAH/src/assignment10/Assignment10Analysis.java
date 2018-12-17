package assignment10;

import java.util.ArrayList;
import java.util.Random;

public class Assignment10Analysis {

	public static void main (String[] args) {


		GoodHashFunctor goodFCT = new GoodHashFunctor();
		MediocreHashFunctor mediocreFCT = new MediocreHashFunctor();
		BadHashFunctor badFCT = new BadHashFunctor();

		for (int i = 100; i <= 5000; i+=100) {
			ArrayList<String> test = buildStringList(i);
			ChainingHashTable chtGood = new ChainingHashTable(i, goodFCT);
			QuadProbeHashTable qhtGood = new QuadProbeHashTable(i,goodFCT);
			ChainingHashTable chtMediocre = new ChainingHashTable(i, mediocreFCT);
			QuadProbeHashTable qhtMediocre = new QuadProbeHashTable(i,mediocreFCT);
			ChainingHashTable chtBad = new ChainingHashTable(i, badFCT);
			QuadProbeHashTable qhtBad = new QuadProbeHashTable(i,badFCT);

			chtGood.addAll(test);
			qhtGood.addAll(test);
			chtMediocre.addAll(test);
			qhtMediocre.addAll(test);
			chtBad.addAll(test);
			qhtBad.addAll(test);
//			System.out.println(chtGood.collisions);
//			System.out.println(qhtGood.collisions);
//			System.out.println(chtMediocre.collisions);
//			System.out.println(qhtMediocre.collisions);
			System.out.println(chtBad.collisions);
//			System.out.println(qhtBad.collisions);
		}

	}

	private static ArrayList<String> buildStringList (int size) {
		ArrayList<String> out = new ArrayList<String>();
		Random r1 = new Random(50);
		Random r2 = new Random(500);
		for (int i = 0; i < size; i++) {
			int thisStringSize = r1.nextInt(20);
			String thisString = "";
			for (int j = 0; j < thisStringSize; j++) {
				char c = (char) ((char)'a' + r2.nextInt(25));
				thisString += c;
			}
			out.add(thisString);
		}
		return out;
	}

	private static void printAll(ArrayList<String> test) {
		for (int i = 0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
	}
}
