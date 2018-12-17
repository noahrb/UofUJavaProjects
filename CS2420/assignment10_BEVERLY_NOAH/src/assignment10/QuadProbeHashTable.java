package assignment10;

import java.util.*;

public class QuadProbeHashTable implements Set<String> {

	private int size, totalCapacity;
	private HashFunctor hasher;
	private String[] hashTable;
	public int collisions;

	/**
	 * Constructs a quadratic probing hash table of the given capacity that uses the hashing function
	 * specified by the given functor.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		size = 0;
		hashTable = new String[getNextPrime(capacity)];
		hasher = functor;
		totalCapacity = capacity;

	}

	/**
	 * Adds an item to the hash function.  Returns true if an item was added or existed. 
	 */
	@Override
	public boolean add(String item) {

		if(size*2 >= totalCapacity) this.grow();
		
		int hashValue = Math.abs(hasher.hash(item)) % totalCapacity;
		int iterationCount = 0;
		
		while(true) {
			if(hashTable[Math.abs(getIndexQuadProbing(hashValue, iterationCount))] == item) {
				return false;
			}
			if(hashTable[Math.abs(getIndexQuadProbing(hashValue, iterationCount))] == null) {
				hashTable[Math.abs(getIndexQuadProbing(hashValue, iterationCount))] = item;
				size++;
				
				if(iterationCount != 0) collisions++;
				return true;
			}
			iterationCount++;
		}
	}
	
	/**
	 * Adds all of the items from the input collection, returns true if any changes were made. 
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean anyAdds = false;
		for (String i : items) {
			if (!this.contains(i)) {
				add(i);
				anyAdds = true;
			}
		}
		return anyAdds;
	}

	/**
	 * Clears all of the spaces in the hashTable by creating a new one. 
	 */
	@Override
	public void clear() {
		this.hashTable = new String[totalCapacity];

	}

	/**
	 * Returns true if the hash table contains the input item, false otherwise. 
	 */
	@Override
	public boolean contains(String item) {

		int startIndex = hasher.hash(item) % totalCapacity;
		
		int iterationCount = 0;
		while(true) {
			if(hashTable[getIndexQuadProbing(startIndex, iterationCount)] == null ||
					(getIndexQuadProbing(startIndex, iterationCount) == startIndex &&
					iterationCount != 0)) {
				return false;
			}
			
			if (hashTable[getIndexQuadProbing(startIndex, iterationCount)] == item) {
				return true;
			}
			iterationCount++;
		}
	}

	/**
	 * Returns true if the hashTable contains all of the items in the input collection. False otherwise.
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for (String i : items) {
			if (!this.contains(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns true if the hashTable's size = 0. false otherwise. 
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns hashTable's size. 
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Takes a start index and iteration count and gets the index of the current position calculated by quadratic probing. 
	 */
	private int getIndexQuadProbing(int startIndex, int iterationCount) {

		return (startIndex + iterationCount*iterationCount) % totalCapacity;

	}

	/**
	 * When this method is called, we increase the size of our hash table and rehash and input the values
	 */
	private void grow() {

		String[] newTable = new String[getNextPrime(totalCapacity*totalCapacity)];
		
		for (int i = 0; i < totalCapacity; i++) {
			if (hashTable[i] != null) {
				newTable[hasher.hash(hashTable[i]) % newTable.length] = hashTable[i];
			}
		}
		
		this.hashTable = new String[getNextPrime(totalCapacity*totalCapacity)];
		
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = newTable[i];
		}
		
		totalCapacity = hashTable.length;

	}
	
	/**
	 * Helper method to return the next prime number for a given int input. 
	 */
	private int getNextPrime(int smallest) {
		int current = smallest + 1;
		while (!isPrime(current)) {
			current++;
		}
		return current;
	}
	
	/**
	 * Returns true if the input int is a prime number, false otherwise. 
	 */
	private boolean isPrime (int toTest) {
		if (toTest == 2) return true;
		for (int i = 2; i < toTest; i++) {
			if (toTest % i == 0) return false;
		}
		return true;
	}

	/**
	 * Helper method for testing to print out the entire hash table. 
	 */
	public void printItAll () {
		for (int i = 0; i < totalCapacity; i++) {
			if (hashTable[i] == null) {
				System.out.print("_ ");
			} else {
				System.out.print(hashTable[i]);
			}
		}
		System.out.println();
	}
}
