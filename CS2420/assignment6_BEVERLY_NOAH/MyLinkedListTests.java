package assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyLinkedListTests {

	@BeforeEach
	void setUp() throws Exception {
	}
	//************************ Edge Case Testing **************************
	@Test
	void testSizeOf1() {
		System.out.println("Edge: Size 1");
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(0, 24); // 0
		
		assertEquals(24, list.getFirst().intValue());
		assertEquals(24, list.getLast().intValue());
		assertEquals(1, list.size());
		assertEquals(0, list.lastIndexOf(24));
		assertEquals(24, list.getLast().intValue());
		/*printAllListElements(list);
		System.out.println("First: " + list.getFirst());
		System.out.println("Last: " + list.getLast());
		
		list.clear();
		printAllListElements(list);
		System.out.println(list.lastIndexOf(100));*/
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//************************ Add Tests **************************
	@Test
	void testAddInTheMiddle() {
		System.out.println("Add: in the middle");
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(0, 0); // 0
		list.add(1, 1); // 0 1
		list.add(2, 2); // 0 1 2 
		list.add(3, 3); // 0 1 2 3
		list.add(3, 10); // 0 1 2 10 3
		list.add(3, 100); // 0 1 2 100 10 3
		list.add(6, 1000); // 0 1 2 100 10 3 1000
		printAllListElements(list);
		list.clear();
		printAllListElements(list);
		System.out.println(list.lastIndexOf(100));
		
	}
	
	@Test
	public void testAddFirst() {
		System.out.println("Add: First");
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(0, 0); 
		list.add(1, 1); 
		list.add(2, 2); 
		list.add(3, 3); 
		list.add(4, 10); 
		list.add(5, 100);
		list.add(6, 1000);  
		printAllListElements(list);
		list.addFirst(9999);
		printAllListElements(list);
		list.clear();
		printAllListElements(list);
		System.out.println(list.lastIndexOf(100));
	}
	
	@Test
	public void testAddLast() {
		System.out.println("Add: Last");
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(0, 0); 
		list.add(1, 1); 
		list.add(2, 2); 
		list.add(3, 3); 
		list.add(4, 10); 
		list.add(5, 100);
		list.add(6, 1000);  
		printAllListElements(list);
		list.addLast(9999);
		printAllListElements(list);
		list.clear();
		printAllListElements(list);
		System.out.println(list.lastIndexOf(100));
	}
	
	//************************ Remove Tests **************************
	@Test
	public void testRemoveFirst() {
		System.out.println("Remove: First");
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(0, 0); 
		list.add(1, 1); 
		list.add(2, 2); 
		list.add(3, 3); 
		list.add(4, 10); 
		list.add(5, 100);
		list.add(6, 1000);  
		printAllListElements(list);
		list.removeFirst();
		printAllListElements(list);
		list.clear();
		printAllListElements(list);
		System.out.println(list.lastIndexOf(100));
	}
	
	@Test
	public void testRemoveLast() {
		System.out.println("Remove Last");
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(0, 0); 
		list.add(1, 1); 
		list.add(2, 2); 
		list.add(3, 3); 
		list.add(4, 10); 
		list.add(5, 100);
		list.add(6, 1000);  
		printAllListElements(list);
		list.removeLast();
		printAllListElements(list);
		list.clear();
		printAllListElements(list);
		System.out.println(list.lastIndexOf(100));
	}
	
	@Test
	public void testRemoveInTheMiddle() {
		System.out.println("Remove In the middle");
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(0, 0); 
		list.add(1, 1); 
		list.add(2, 2); 
		list.add(3, 3); 
		list.add(4, 10); 
		list.add(5, 100);
		list.add(6, 1000);  
		printAllListElements(list);
		list.remove(4);
		printAllListElements(list);
		list.clear();
		printAllListElements(list);
		System.out.println(list.lastIndexOf(100));
	}
	
	//************************ Index Tests **************************
	@Test
	public void testIndexOf() {
		System.out.println("Test Index Of");
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(0, 0); 
		list.add(1, 1); 
		list.add(2, 2); 
		list.add(3, 3); 
		list.add(4, 10); 
		list.add(5, 100);
		list.add(6, 1000);  
		printAllListElements(list);
		System.out.println("Index of 100: " + list.indexOf(10));
		list.clear();
		printAllListElements(list);
		System.out.println(list.lastIndexOf(100));
	}
	
	@Test
	public void testLastIndexOf() {
		System.out.println("Test Last Index Of");
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(0, 0); 
		list.add(1, 1); 
		list.add(2, 2); 
		list.add(3, 3); 
		list.add(4, 10); 
		list.add(5, 100);
		list.add(6, 1000);  
		printAllListElements(list);
		System.out.println("Last Index:  " + list.lastIndexOf(1000));
		list.clear();
		printAllListElements(list);
		System.out.println(list.lastIndexOf(100));
	}
	
	//************************ Other Tests **************************
	@Test
	public void testSize() {
		System.out.println("Test Size");
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(0, 0); 
		list.add(1, 1); 
		list.add(2, 2); 
		list.add(3, 3); 
		list.add(4, 10); 
		list.add(5, 100);
		list.add(6, 1000);  
		printAllListElements(list);
		System.out.println("Size:  " + list.size());
		list.clear();
		printAllListElements(list);
		System.out.println(list.lastIndexOf(100));
	}
	
	@Test
	public void testClear() {
		System.out.println("Test Size");
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(0, 0); 
		list.add(1, 1); 
		list.add(2, 2); 
		list.add(3, 3); 
		list.add(4, 10); 
		list.add(5, 100);
		list.add(6, 1000);  
		printAllListElements(list);
		list.clear();
		printAllListElements(list);
		System.out.println(list.lastIndexOf(100));
	}
	
	private <T> void printAllListElements(MyLinkedList<T> list) {
		Object[] arr = new Object[list.size()];
		arr = list.toArray();
		if (arr == null) {
			System.out.println("empty array");
			return;
		}
		int i;
		for (i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "; ");
		}
		System.out.print("  (size = " + (i) + ")");
		System.out.print("\n");
	}

}
