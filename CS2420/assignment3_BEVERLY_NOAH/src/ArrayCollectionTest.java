package assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;


class ArrayCollectionTest {
	
	public class IntComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			if (o1.compareTo(o2) < 0) {
				return -1;
			}
			
			if (o1.compareTo(o2) > 0) {
				return 1;
			}
			
			return 0;
		}
	}
	
	public class StringComparator implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			if (o1.compareTo(o2) < 0) {
				return -1;
			}
			
			if (o1.compareTo(o2) > 0) {
				return 1;
			}
			
			return 0;
		}
	}
	
	// *****************Testing Add and Grow Methods ***************************
	// add an element
	
	@Test
	void testAdd() {
		ArrayCollection<String> instance = new ArrayCollection<String>();
		boolean result = instance.add("1");

		assertTrue(result);
	}

	// attempt to add an element which already exists
	@Test
	void testAdd2() {
		ArrayCollection<String> instance = new ArrayCollection<String>();
		instance.add("1");
		boolean result = instance.add("1");

		assertFalse(result);
	}

	// attempt to add an element when we have a size of 10
	@Test
	void testAdd3() {
		ArrayCollection<String> instance = new ArrayCollection<String>();
		instance.add("1");
		instance.add("2");
		instance.add("3");
		instance.add("4");
		instance.add("5");
		instance.add("6");
		instance.add("7");
		instance.add("8");
		instance.add("9");
		instance.add("10");

		boolean result = instance.add("11");
		assertTrue(result);
	}

	// Making a test for adding when the array size is not full but not empty
	@Test
	void testAdd4() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.add("1");
		instance.add("2");
		instance.add("3");
		instance.add("4");
		instance.add("5");
		instance.add("6");
		instance.add("7");
		instance.add("8");

		boolean result = instance.contains("8");
		assertTrue(result);
	}

	// ******************Testing AddAll********************
	// add an array list to an empty array collection
	@Test
	void testAddAll() {
		ArrayCollection<String> instance = new ArrayCollection<String>();
		ArrayList<String> array = new ArrayList<>(Arrays.asList("1", "2", "3"));

		boolean result = instance.addAll(array);
		assertTrue(result);
	}

	// add an array list where each element is already in the array collection
	@Test
	void testAddAll2() {
		ArrayCollection<String> instance = new ArrayCollection<String>();
		instance.add("1");
		instance.add("2");
		instance.add("3");
		ArrayList<String> array = new ArrayList<>(Arrays.asList("1", "2", "3"));
		boolean result = instance.addAll(array);
		assertFalse(result);
	}
	
	// add an array list where each element is already in the array collection
	@Test
	void testAddAll3() {
		ArrayCollection<String> instance = new ArrayCollection<String>();
		instance.addAll(new ArrayList<>(Arrays.asList("1", "2", "3")));
		
		boolean result = instance.addAll(new ArrayList<>(Arrays.asList("1", "2", "3")));
		assertFalse(result);
	}

	// add an array list with 101 element where the array collection has a capacity
	// of 10
	@Test
	void testAddAll4() {
		ArrayCollection<Integer> instance = new ArrayCollection<>();
		ArrayList<Integer> largeArrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
				15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
				41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66,
				67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92,
				93, 94, 95, 96, 97, 98, 99, 100, 101));

		boolean result = instance.addAll(largeArrayList);
		assertTrue(result);
	}

	// add an array list where there are some elements in the array collection and
	// some elements are not in the array collection
	@Test
	void testAddAll5() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.add("1");
		ArrayList<String> array = new ArrayList<>(Arrays.asList("1", "2", "3"));

		boolean result = instance.addAll(array);
		assertTrue(result);
	}

	// ****************Testing Clear ************************
	@Test
	void clearTest() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.add("1");
		instance.add("2");
		instance.add("3");

		instance.clear();
		boolean result = instance.size() == 0;
		assertTrue(result);
	}

	@Test
	void clearTest2() {
		ArrayCollection<String> instance = new ArrayCollection<>();

		instance.clear();
		boolean result = instance.size() == 0;
		assertTrue(result);
	}

	// ****************Testing contains************************
	// test polymorphism
	@Test
	void testContains() {
		ArrayCollection<Object> instance = new ArrayCollection<>();
		Object obj = "1";
		instance.add(obj);

		boolean result = instance.contains(obj);
		assertTrue(result);
	}

	// test when array collection has the object
	@Test
	void testContains2() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.add("1");

		boolean result = instance.contains("1");
		assertTrue(result);
	}

	// test when array collection does not have the object
	@Test
	void testContains3() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.add("A");

		boolean result = instance.contains("B");
		assertFalse(result);
	}

	// ******************Testing containsAll *******************
	// test when the array collection has each of the objects
	@Test
	void testContainsAll() {
		ArrayCollection<Integer> instance = new ArrayCollection<>();
		ArrayList<Integer> arrayToAdd = new ArrayList<>(Arrays.asList(1, 2, 3));

		instance.addAll(arrayToAdd);
		ArrayList<Integer> arrayToCheck = new ArrayList<>(Arrays.asList(1, 2, 3));

		boolean result = instance.containsAll(arrayToCheck);
		assertTrue(result);
	}

	// test when the array collection has some of the objects
	@Test
	void testContainsAll2() {
		ArrayCollection<Integer> instance = new ArrayCollection<>();
		ArrayList<Integer> arrayToAdd = new ArrayList<>(Arrays.asList(1, 2, 3));
		instance.addAll(arrayToAdd);

		ArrayList<Integer> arrayToCheck = new ArrayList<>(Arrays.asList(1, 4));

		boolean result = instance.containsAll(arrayToCheck);
		assertFalse(result);
	}

	// test when the array collection has all of the objects in
	// an array list
	@Test
	void testContainsAll3() {
		ArrayCollection<Integer> instance = new ArrayCollection<>();
		instance.add(1);
		instance.add(2);

		ArrayList<Integer> instanceTest = new ArrayList<>();
		instanceTest.add(1);
		instanceTest.add(2);

		boolean result = instance.containsAll(instanceTest);
		assertTrue(result);

	}

	// test when the array collection has all of the objects,
	// but now pass an ArrayCollection
	@Test
	void testContainsAll4() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.add("A");
		instance.add("B");
		instance.add("C");

		ArrayCollection<String> compareArrayCollection = new ArrayCollection<>();
		compareArrayCollection.add("A");
		compareArrayCollection.add("B");
		compareArrayCollection.add("C");

		boolean result = instance.containsAll(compareArrayCollection);
		assertTrue(result);
	}

	// test when the array collection has none of the objects
	@Test
	void testContainsAll5() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.addAll(new ArrayList<String>(Arrays.asList("A", "B", "C")));

		ArrayList<String> compareArray = new ArrayList<>(Arrays.asList("D", "E", "F"));

		boolean result = instance.containsAll(compareArray);
		assertFalse(result);
	}

	// *******************Testing isEmpty*********************
	// test an empty array collection
	@Test
	void testIsEmpty() {
		ArrayCollection<String> instance = new ArrayCollection<>();

		boolean result = instance.isEmpty();
		assertTrue(result);
	}

	// test a non-empty array collection
	@Test
	void testIsEmpty2() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.add("A");

		boolean result = instance.isEmpty();
		assertFalse(result);
	}


	// *******************Testing Iterator*********************
	
	// *******************Testing Iterator hasNext*********************
	@Test
	void testIteratorHasNext() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		Iterator<String> iterator = instance.iterator();

		boolean result = iterator.hasNext();
		assertFalse(result);
	}
	
	@Test
	void testIteratorHasNext2() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.addAll(new ArrayList<String>(Arrays.asList("1", "2", "3")));
		
		Iterator<String> iter = instance.iterator();
		for (int i = 0; i < 3; i++) {
			iter.next();
		}
		assertFalse(iter.hasNext());
		
	}
	
	@Test
	void testIteratorHasNext3() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.addAll(new ArrayList<String>(Arrays.asList("1", "2", "3")));
	
		Iterator<String> iter = instance.iterator();
		assertTrue(iter.hasNext());
	}
	
	// *******************Testing Iterator next************************
	
	// Test a working one
	@Test
	void testIteratorNext() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.addAll(new ArrayList<String>(Arrays.asList("1", "2", "3")));
		
		Iterator<String> iter = instance.iterator();
		
		iter.next();
		iter.next();
		assertEquals("3", iter.next());
	}
	
	// Test one that throws noSuchElementException
	@Test
	void testIteratorNext2() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.addAll(new ArrayList<String>(Arrays.asList("1", "2", "3")));
		
		Iterator<String> iter = instance.iterator();
		for (int i = 0; i < 3; i++) {
			iter.next();
		}
		
		assertThrows(NoSuchElementException.class, () -> iter.next());
	}
	
	// *******************Testing Iterator Remove*********************
	// Test a working remove
	@Test
	void testIteratorRemove() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.addAll(new ArrayList<String>(Arrays.asList("1", "2", "3")));
		
		Iterator<String> iter = instance.iterator();
		for (int i = 0; i < 3; i++) {
			iter.next();
		}
		
		iter.remove();
		assertFalse(instance.contains("3"));
	}

	// Test one that throws IllegalStateException
	@Test
	void testIteratorRemove2() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.addAll(new ArrayList<String>(Arrays.asList("1", "2", "3")));
		
		Iterator<String> iter = instance.iterator();
		assertThrows(IllegalStateException.class, () -> iter.remove());
	}
	
	// *******************Testing Remove*********************
	// test remove
	@Test
	void testRemove() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.add("A");
		instance.remove("A");

		boolean result = instance.contains("A");
		assertFalse(result);
	}
	
	@Test
	void testRemove2() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.add("A");
		instance.add("B");
		
		boolean result = instance.remove("A");
		assertTrue(result);
	}
	
	@Test
	void testRemove3() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.add("A");
		instance.add("B");
		
		boolean result = instance.remove("C");
		assertFalse(result);
	}
	

	// *******************Testing RemoveAll*********************
	// test one that removes all
	
	@Test
	void testRemoveAll() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.add("A");

		ArrayList<String> arrayToRemove = new ArrayList<String>(Arrays.asList("A"));

		boolean result = instance.removeAll(arrayToRemove);
		assertTrue(result);
	}
	
	@Test
	void testRemoveAll2() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		instance.add("A");
		instance.add("B");
		
		
		ArrayList<String> arrayToRemove = new ArrayList<String>();
		arrayToRemove.add("A");
		arrayToRemove.add("B");
		
		
		boolean result = instance.removeAll(arrayToRemove);
		assertTrue(result);
	}
	
	
	@Test
	void testRemoveAll3() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		ArrayList<String> arrayToAdd = new ArrayList<>(Arrays.asList("A", "B", "C"));
		instance.addAll(arrayToAdd);
		
		ArrayList<String> arrayToRemove = new ArrayList<String>(Arrays.asList("A", "B", "C"));
		boolean result = instance.removeAll(arrayToRemove);
		assertTrue(result);
	}
	
	// test one that only removes some
	@Test
	void testRemoveAll4() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		ArrayList<String> arrayToAdd = new ArrayList<>(Arrays.asList("A", "B", "C"));
		instance.addAll(arrayToAdd);
		
		ArrayList<String> arrayToRemove = new ArrayList<String>(Arrays.asList("A", "D"));
		boolean result = instance.removeAll(arrayToRemove);
		assertTrue(result);
	}

	// test one that doesn't remove any
	@Test
	void testRemoveAll5() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		ArrayList<String> arrayToAdd = new ArrayList<>(Arrays.asList("A", "B", "C"));
		instance.addAll(arrayToAdd);
		
		ArrayList<String> arrayToRemove = new ArrayList<String>(Arrays.asList("D", "E"));
		boolean result = instance.removeAll(arrayToRemove);
		assertFalse(result);
	}

	// *******************Testing RetainAll*********************
	// test one that doesn't retain any
	@Test
	void testRetainAll() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		ArrayList<String> arrayToAdd = new ArrayList<>(Arrays.asList("A", "B", "C"));
		instance.addAll(arrayToAdd);
		
		ArrayList<String> arrayToRetain = new ArrayList<String>(Arrays.asList("D", "E"));
		boolean result = instance.retainAll(arrayToRetain);
		assertTrue(result);
	}
	
	// test one that retains all
	
	@Test
	void testRetainAll2() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		ArrayList<String> arrayToAdd = new ArrayList<>(Arrays.asList("A"));
		instance.addAll(arrayToAdd);
		
		ArrayList<String> arrayToRetain = new ArrayList<String>(Arrays.asList("A", "B"));
		boolean result = instance.retainAll(arrayToRetain);
		assertFalse(result);
		
	}
	// test one that retains some
	@Test
	void testRetainAll3() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		ArrayList<String> arrayToAdd = new ArrayList<>(Arrays.asList("A", "B", "C"));
		instance.addAll(arrayToAdd);
		
		ArrayList<String> arrayToRetain = new ArrayList<String>(Arrays.asList("A", "E"));
		boolean result = instance.retainAll(arrayToRetain);
		assertTrue(result);
	}
	
	// *******************Testing Size*********************
	// test a working case
	@Test
	void testSize() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		ArrayList<String> arrayToAdd = new ArrayList<>(Arrays.asList("A", "B", "C"));
		instance.addAll(arrayToAdd);
		
		assertEquals(3, instance.size());
	}
	
	// test with a 0 size
	@Test
	void testSize2() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		assertEquals(0, instance.size());
	}
	
	// *******************Testing toArray()*********************
	// verify that it is the same size of items as the data.
	@Test
	void testToArray() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		ArrayList<String> arrayToAdd = new ArrayList<>(Arrays.asList("A", "B", "C"));
		instance.addAll(arrayToAdd);
		
		Object[] compareArr = instance.toArray();
		assertEquals(3, compareArr.length);
	}
	
	// verify that the values in the toArray() match the ArrayCollection
	@Test
	void testToArray2() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		ArrayList<String> arrayToAdd = new ArrayList<>(Arrays.asList("A", "B", "C"));
		instance.addAll(arrayToAdd);
		
		Object[] compareArr = instance.toArray();
		assertEquals("C", compareArr[2]);
	}
	
	// *******************Testing toSortedList*********************
	// Testing with Integers
	@Test
	void testToSortedList() {
		ArrayCollection<Integer> instance = new ArrayCollection<>();
		ArrayList<Integer> arrayToAdd = new ArrayList<>(Arrays.asList(3, 1, 2));
		instance.addAll(arrayToAdd);
		
		ArrayList<Integer> testArray = instance.toSortedList(new IntComparator());
		
		ArrayList<Integer> compareArray = new ArrayList<>(Arrays.asList(1, 2, 3));
		assertTrue(testArray.equals(compareArray));
	}
	
	// Testing with Strings
	@Test
	void testToSortedList2() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		ArrayList<String> arrayToAdd = new ArrayList<>(Arrays.asList("C", "A", "B"));
		instance.addAll(arrayToAdd);
		
		ArrayList<String> testArray = instance.toSortedList(new StringComparator());
		
		ArrayList<String> compareArray = new ArrayList<>(Arrays.asList("A", "B", "C"));
		assertTrue(testArray.equals(compareArray));
	}
	
	// **********************Testing BinarySearch*******************************
	// test an unsuccessful search (Strings)
	@Test
	void testBinarySearch() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		ArrayList<String> arrayToAdd = new ArrayList<>(
				Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
		instance.addAll(arrayToAdd);
		
		ArrayList<String> sortedArray = instance.toSortedList(new StringComparator());
		boolean result = SearchUtil.binarySearch(sortedArray, "0", new StringComparator());
		assertFalse(result);
	}
	
	// test a successful search (Strings)
	@Test
	void testBinarySearch2() {
		ArrayCollection<String> instance = new ArrayCollection<>();
		ArrayList<String> arrayToAdd = new ArrayList<>(
				Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
		instance.addAll(arrayToAdd);
		
		ArrayList<String> sortedArray = instance.toSortedList(new StringComparator());
		boolean result = SearchUtil.binarySearch(sortedArray, "3", new StringComparator());
		assertTrue(result);
	}
	
	
	
	
	
}
