package assignment10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String> {

	private int size, totalCapacity;
	private HashFunctor hasher;
	private ArrayList<LinkedList<String>> hashTable;
	public int collisions;
	
	/**
	 * Constructs a chaining hash table of the given capacity that uses the hashing function
	 * specified by the given functor.
	 */
	public ChainingHashTable(int capacity, HashFunctor functor) {
		collisions = 0;
		hashTable = new ArrayList<LinkedList<String>>();
		hasher = functor;
		totalCapacity = capacity;
		
		for (int i = 0; i < capacity; i++) {
			hashTable.add(new LinkedList<String>());
		}
	}
	
	/**
	 * Adds an item to the hash function.  Returns true if an item was added or existed. 
	 */
	@Override
	public boolean add(String item) {
		int hashValue = Math.abs(hasher.hash(item)) % totalCapacity;
		
		if(hashTable.size() < totalCapacity) totalCapacity ++;
		if(hashTable.get(hashValue).size() == 0) {
			LinkedList<String> tempLL = new LinkedList<String>();
			tempLL.add(item);
			hashTable.add(hashValue, tempLL); }
		else if(hashTable.get(hashValue).size() != 0) {
			hashTable.get(hashValue).add(item);
			collisions++;
		}
		return true;
	}

	/**
	 * Adds all of the items from the input collection, returns true if any changes were made. 
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean addedAny = false;
		for (String i : items) {
			if(add(i)) {
				addedAny = true;
			}
		}
		return addedAny;
	}

	/**
	 * Clears all of the spaces in the hashTable by creating a new one. 
	 */
	@Override
	public void clear() {
		this.hashTable = new ArrayList<LinkedList<String>>();
		
	}

	/**
	 * Returns true if the hash table contains the input item, false otherwise. 
	 */
	@Override
	public boolean contains(String item) {
		int hashValue = Math.abs(hasher.hash(item)) % totalCapacity;
		if(hashTable.get(hashValue).contains(item)) {
			return true;
		} else {
		return false; }
	}

	/**
	 * Returns true if the hashTable contains all of the items in the input collection. False otherwise.
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for (String i : items) {
			if(!this.contains(i)) {
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

}
