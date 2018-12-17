package assignment11;

import java.util.*;

public class PriorityQueueTester {

	public static void main (String[] args) {
		
		PriorityQueue<Integer> quBig = new PriorityQueue<Integer>();
		PriorityQueue<Integer> quMed = new PriorityQueue<Integer>();
		PriorityQueue<Integer> quSmall = new PriorityQueue<Integer>();
		PriorityQueue<Integer> quBigDelete = new PriorityQueue<Integer>();
		PriorityQueue<Integer> quMedDelete = new PriorityQueue<Integer>();
		PriorityQueue<Integer> quSmallDelete = new PriorityQueue<Integer>();
		
		ArrayList<Integer> big = generateRandomIntArray(500,10000,1000);
		ArrayList<Integer> med = generateRandomIntArray(500,1000,100);
		ArrayList<Integer> small = generateRandomIntArray(500,100,10);
		
		addAlltoQU (quBig, big);
		addAlltoQU (quSmall, small);
		addAlltoQU (quMed, med);	
		addAlltoQU (quBigDelete, big);
		addAlltoQU (quSmallDelete, small);
		addAlltoQU (quMedDelete, med);
		
		System.out.println(isMinTree(quSmallDelete));
		System.out.println(isMinTree(quBigDelete));
		System.out.println(isMinTree(quMedDelete));
		
		quSmall.generateDotFile("/Users/Josh/Desktop/15 dot file.csv");
		
		System.out.println(quBig.size());
		System.out.println(quMed.size());
		System.out.println(quSmall.size());
		
		System.out.println(doCrazyStuff(50,5000,500));
		System.out.println(doCrazyStuff(5,50000,500));
		System.out.println(doCrazyStuff(51,5,500));
		System.out.println(doCrazyStuff(52,50,500));
		System.out.println(doCrazyStuff(53,7777,500));
		System.out.println(doCrazyStuff(54,98765,500));
		System.out.println(doCrazyStuff(55,456,500));
		System.out.println(doCrazyStuff(56,12,500));
		System.out.println(doCrazyStuff(57,1,1));
		System.out.println();
		System.out.println(doCrazyStuff(57,100,0));
		
		PriorityQueue<Integer> quTest = new PriorityQueue<Integer>();
		quTest.deleteMin();
		
		
		
	}
	
	
	
	private static boolean doCrazyStuff(int seed, int max, int size) {
		
		for (int i = 0; i < size; i++) {
			
			ArrayList<Integer> arr = generateRandomIntArray(seed,max,size); // same every time
			PriorityQueue<Integer> qu = new PriorityQueue<Integer>();
			addAlltoQU(qu, arr);
			
			for (int j = 0; j < i; j++) {
				qu.deleteMin();
			}
			
			if (!isMinTree(qu)) return false;
		}
		
		return true;
	}
	
	private static boolean isMinTree (PriorityQueue<Integer> qu_) {	
		PriorityQueue<Integer> qu = new PriorityQueue<Integer>();
		qu = qu_;
		Integer tempSmall = qu.deleteMin();
		Integer tempLarge = 0;
		int bound = qu.size();
		
		for (int i = 0; i < bound; i++) {
			
			tempLarge = qu.deleteMin();
			
			if (tempSmall > tempLarge) return false;
			
			tempSmall = tempLarge;
			
		}
		return true;
	}
	
	private static ArrayList<Integer> generateRandomIntArray (int seed, int max, int size) {
		ArrayList<Integer> out = new ArrayList<Integer>();
		Random rand = new Random(seed);
		for (int i = 0; i < size; i++) {
			out.add(rand.nextInt(max));
		}
		return out;
	}
	
	private static void addAlltoQU (PriorityQueue<Integer> qu, ArrayList<Integer> arr) {
		for (int i = 0; i < arr.size(); i++) {
			qu.add(arr.get(i));
		}
	}
}
