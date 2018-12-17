package assignment10;

public class TestHashFunctions {

	public static void main (String[] args) {
		MediocreHashFunctor fct = new MediocreHashFunctor();
		ChainingHashTable qt = new ChainingHashTable(10, fct);
		qt.add("Hello World");
		qt.add("I am bugged");
		qt.add("I hate neuroscience");
		qt.add("A");
		qt.add("B");
		qt.add("C");
		qt.add("D");
		qt.add("E");
		qt.add("F");
		qt.add("G");
		qt.add("H");
		qt.add("I");
		qt.add("J");
		System.out.println(qt.contains("I"));
		System.out.println(qt.contains("Q"));

		
	}
	
	
}
