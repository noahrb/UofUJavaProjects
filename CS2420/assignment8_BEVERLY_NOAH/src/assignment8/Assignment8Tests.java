package assignment8;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Assignment8Tests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1225);
		bst.remove(1000);
//		bst.remove(700);
//		bst.remove(1250);
//		bst.writeDot("/Users/noahbeverly/Desktop/visualize");

//		ArrayList<Integer> out = bst.toArrayList();
//		
//		for (int i = 0; i < out.size(); i++) {
//			System.out.print(out.get(i) + "; ");
//		}

	}

	// BinarySearchTree Tests

	// Testing add/addRecursive
	@Test
	void bstTest1() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);
		// bst.writeDot("/Users/noahbeverly/Desktop/visualize");
	}

	// Testing addAll
	@Test
	void bstTest2() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		list.add(1);
		list.add(6);
		list.add(4);

		bst.addAll(list);

		// bst.writeDot("/Users/noahbeverly/Desktop/visualize");
	}

	// Testing remove
	@Test
	void bstTest3() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);
		bst.remove(1000);
		bst.remove(700);
		bst.remove(1250);
		// bst.writeDot("/Users/noahbeverly/Desktop/visualize");
	}

	// Testing clear
	@Test
	void bstTest4() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		bst.clear();

		bst.writeDot("/Users/noahbeverly/Desktop/visualize");
	}

	// Testing contains/searchRecursive Success Case
	@Test
	void bstTest5() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		// bst.writeDot("/Users/noahbeverly/Desktop/visualize");
		assertEquals(true, bst.contains(50) && bst.contains(775) && bst.contains(1200));
	}

	// Testing contains/searchRecursive Failure Case
	@Test
	void bstTest6() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		// bst.writeDot("/Users/noahbeverly/Desktop/visualize");
		assertEquals(false, bst.contains(53) && bst.contains(775) && bst.contains(1200));
	}

	// Testing containsAll Success Case
	@Test
	void bstTest7() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(725);
		list.add(125);
		list.add(775);
		// bst.writeDot("/Users/noahbeverly/Desktop/visualize");
		assertEquals(true, bst.containsAll(list));
	}

	// Testing containsAll Failure Case
	@Test
	void bstTest8() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(725);
		list.add(125);
		list.add(776);
		// bst.writeDot("/Users/noahbeverly/Desktop/visualize");
		assertEquals(false, bst.containsAll(list));
	}

	// Testing First
	@Test
	void bstTest9() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		assertEquals(25, bst.first().intValue());
	}

	// Testing isEmpty Success Case
	@Test
	void bstTest10() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

		assertEquals(true, bst.isEmpty());
	}

	// Testing isEmpty Failure Case
	@Test
	void bstTest11() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		assertEquals(false, bst.isEmpty());
	}

	// Testing Last
	@Test
	void bstTest12() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		assertEquals(1300, bst.last().intValue());
	}

	// Testing Remove Case toRemove is root
	@Test
	void bstTest13() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		bst.remove(500);
		// bst.writeDot("/Users/noahbeverly/Desktop/visualize");
		assertEquals(false, bst.contains(500));
	}

	// Testing Remove Case toRemove is a leaf
	@Test
	void bstTest14() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		bst.remove(25);
		// bst.writeDot("/Users/noahbeverly/Desktop/visualize");
		assertEquals(false, bst.contains(25));
	}

	// Testing Remove Case toRemove is a middle node with one child
	@Test
	void bstTest15() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		// bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		bst.remove(1250);
		// bst.writeDot("/Users/noahbeverly/Desktop/visualize");
		assertEquals(false, bst.contains(1250));
	}

	// Testing Remove Case toRemove is a middle node with two children
	@Test
	void bstTest16() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		bst.remove(1250);
		// bst.writeDot("/Users/noahbeverly/Desktop/visualize");
		assertEquals(false, bst.contains(1250));
	}

	// Testing removeAll
	@Test
	void bstTest17() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(725);
		list.add(125);
		list.add(775);

		bst.removeAll(list);
		// bst.writeDot("/Users/noahbeverly/Desktop/visualize");
		assertEquals(false, bst.contains(725) && bst.contains(125) && bst.contains(775));
	}

	// Testing size
	@Test
	void bstTest18() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		// bst.writeDot("/Users/noahbeverly/Desktop/visualize");
		assertEquals(15, bst.size());
	}

	// Testing toArrayList
	@Test
	void bstTest19() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(500);
		bst.add(100);
		bst.add(1000);
		bst.add(50);
		bst.add(150);
		bst.add(750);
		bst.add(1250);
		bst.add(1300);
		bst.add(75);
		bst.add(25);
		bst.add(125);
		bst.add(175);
		bst.add(775);
		bst.add(725);
		bst.add(1200);

		ArrayList<Integer> out = bst.toArrayList();

		assertEquals(true, out.contains(125));
	}

	// SpellChecker Tests
	
	//Testing case where there are no misspelled words. 
	void spellCheckerTest1() {
		SpellChecker mySC = new SpellChecker(new File("dictionary.txt"));

		File doc = new File("hello_world.txt");
		List<String> misspelledWords = mySC.spellCheck(doc);

		assertEquals(0, misspelledWords.size() == 0);

	}
	
	//Testing case where there are misspelled words
	void spellCheckerTest2() {
		SpellChecker mySC = new SpellChecker(new File("dictionary.txt"));

		File doc = new File("good_luck.txt");
		List<String> misspelledWords = mySC.spellCheck(doc);
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("bst");
		list.add("danny");
		
		assertEquals(true, misspelledWords.containsAll(list));
	}

}
