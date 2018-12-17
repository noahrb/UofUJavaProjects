package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Daniel Kopta and Stephen Harman and Noah Beverly. Implements the
 *         Collection interface using an array as storage. The array must grow
 *         as needed. An ArrayCollection can not contain duplicates. All methods
 *         should be implemented as defined by the Java API, unless otherwise
 *         specified.
 * 
 *         It is your job to fill in the missing implementations and to comment
 *         this class. Every method should have comments (Javadoc-style
 *         prefered). The comments should at least indicate what the method
 *         does, what the arguments mean, and what the returned value is. It
 *         should specify any special cases that the method handles. Any code
 *         that is not self-explanatory should be commented.
 *
 * @param <T> - generic type placeholder
 */
public class ArrayCollection<T> implements Collection<T> {

	private T data[]; // Storage for the items in the collection
	private int size; // Keep track of how many items this collection holds

	// There is no clean way to convert between T and Object, so we suppress the
	// warning.
	@SuppressWarnings("unchecked")
	public ArrayCollection() {
		size = 0;
		// We can't instantiate an array of unknown type T, so we must create an Object
		// array, and typecast
		data = (T[]) new Object[10]; // Start with an initial capacity of 10
	}

	/**
	 * This is a helper function specific to ArrayCollection Doubles the size of the
	 * data storage array, retaining its current contents.
	 */
	@SuppressWarnings("unchecked")
	private void grow() {
		T[] temp = (T[]) new Object[data.length * 2];

		// looping through the data array and copying over the values to the temp array
		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}

		data = temp;
	}

	/**
	 * Null values will not be passed to the add method for testing. Adds a new
	 * element to the ArrayCollection. Does not add the item if it already exists in
	 * the ArrayCollection. Returns a boolean indicating if a value was added.
	 */
	@Override
	public boolean add(T arg0) {

		// the equals method will be called for the generic parameter.
		// note: the object's equals method checks reference types
		// Check if the parameter already exists in the data array
		for (T element : data) {
			if (element != null && element.equals(arg0)) {
				return false;
			}
		}

		// If there is no more room, calls the grow() method.
		if (size == data.length) {
			grow();
		}

		// At this point, we know the data array has sufficient size to add a new
		// element.
		data[size] = arg0;
		size++;
		return true;

	}

	/**
	 * Adds all of the items found in the Collection argument to the
	 * ArrayCollection. Does not add if already exists. Returns a boolean value
	 * indicating if a value was added and false if none were added.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		// loop through the parameter to check if any of the items already exist
		T[] array = (T[]) arg0.toArray();

		if (array.length == 0) {
			return false;
		}

		boolean itemWasAdded = false;
		

		for (int i = 0; i < array.length; i++) {
			boolean containsItem = false;
			
			for (int j = 0; j < size(); j++) {
				if (array[i].equals(data[j])) {
					containsItem = true;
				}
			}
			
			// If it does not contain the item, calls the add method
			if (containsItem != true) {
				add(array[i]);
				itemWasAdded = true;
			}
		}

		return itemWasAdded;
	}

	/**
	 * Sets size to zero and removes all of the items from the Collection.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		size = 0;
		data = (T[]) new Object[10];
	}

	/**
	 * Determines if ArrayCollection contains the value. Returns true if the item is
	 * in the collection, false otherwise.
	 */
	@Override
	public boolean contains(Object arg0) {
		
		for (int i = 0; i < size(); i++) {
			if (data[i].equals(arg0)) {
				return true;
			}
		}
		
		return false;
		
//		Iterator<T> iterator = iterator();
//
//		while (iterator.hasNext()) {
//			if (iterator.next().equals(arg0))
//				return true;
//		}
//		return false;

	}

	/**
	 * Determines if ArrayCollection contains all of the values. Returns true if the
	 * ArrayCollection contains all the items in the argument. Returns false
	 * otherwise.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean containsAll(Collection<?> arg0) {
		// the toArray() method here is coming from Collection
		T[] array = (T[]) arg0.toArray();

		for (int i = 0; i < array.length; i++) {
			if (!contains(array[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determines if the ArrayCollection is empty. Returns true if the
	 * ArrayCollection contains no items. Return false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		// This code can be condensed to return size == 0;,
		// but we think the code below is more readable.
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Creates a new ArrayCollectionIterator. Returns a new ArrayCollectionIterator
	 */
	@Override
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	/**
	 * Removes value from the ArrayCollection. If value is not in the collection,
	 * returns false. If the value is in the collection, removes and returns true;
	 */
	@Override
	public boolean remove(Object arg0) {
		if (!contains(arg0)) {
			return false;
		} else {
			for (int i = 0; i < size(); i++) {
				int removeIndex;
				if (arg0.equals(data[i])) {
					// this null is used for the case when we delete the last element from the data
					// array
					removeIndex = i;
					data[i] = null;
					for (int j = removeIndex; j < size() - 1; j++) {
						data[j] = data[j + 1];
					}
					
				}
			}
			size--;
			return true;
		}
	}

	
	/**
	 * If any values from the collection are in the ArrayCollection, then they are removed.
	 * Returns true if the data array changed as a result of a call to this method.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAll(Collection<?> arg0) {
		T[] array = (T[]) arg0.toArray();
		
		boolean dataArrayHasChanged = false;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < size(); j++) {
				if (array[i].equals(data[j])) {
					remove(data[j]);
					dataArrayHasChanged = true;
				}
			}
		}
		
		
//		Iterator<T> iterator = iterator();
//		
//		for (int i = 0; i < array.length; i++) {
//			
//			while(iterator.hasNext()) {
//				T nextItem = iterator.next();
//				System.out.println(nextItem);
//				if (nextItem.equals(array[i])) {
//					System.out.println(iterator.toString());
//					iterator.remove();
//					System.out.println("after remove");
//					dataArrayHasChanged = true;
//					System.out.println(dataArrayHasChanged);
//				}
//				System.out.println("out of if");
//				
//			}
//		}
		
		return dataArrayHasChanged;
	}

	/**
	 * If the data array contains any items which are not in the specified parameter, 
	 * then those items are removed from the data array. The only remaining values in
	 * the data array will be those contained in the specified parameter.
	 * Returns true if the data array changed as a result of the call to the method.
	 * Returns false otherwise.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> arg0) {
		boolean dataArrayHasChanged = false;
		T[] array = (T[]) arg0.toArray();
		
		Iterator<T> iterator = iterator();

		for (int i = 0; i < array.length; i++) {
			while (iterator.hasNext()) {
				T nextItem = iterator.next();
				if (!nextItem.equals(array[i])) {
					iterator.remove();
					dataArrayHasChanged = true;
				}
			}
		}

		return dataArrayHasChanged;
	}

	/**
	 * Returns the private variable size for this ArrayCollection
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns an Object array with only the non-null values contained in the data
	 * array.
	 */
	@Override
	public Object[] toArray() {
		Object[] returnArray = new Object[size()];
		for (int i = 0; i < size(); i++) {
			returnArray[i] = data[i];
		}
		return returnArray;
	}

	/*
	 * Don't implement this method (unless you want to). It must be here to complete
	 * the Collection interface. We will not test this method.
	 */
	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	/*
	 * Sorting method specific to ArrayCollection - not part of the Collection
	 * interface. Must implement a selection sort (see Assignment 2 for code). Must
	 * not modify this ArrayCollection.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		T[] list = (T[]) this.toArray();
		ArrayList<T> test = new ArrayList<>();

		for (int i = 0; i < list.length - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.length; j++) {
				if (cmp.compare(list[j], list[minIndex]) < 0) {
					minIndex = j;
				}
			}
			T temp = list[i];
			list[i] = list[minIndex];
			list[minIndex] = temp;
		}

		for (int i = 0; i < list.length; i++) {
			test.add(list[i]);
		}

		return test;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		private int nextIndex;
		private boolean canRemove;

		public ArrayCollectionIterator() {
			nextIndex = 0;
			canRemove = false;
		}

		/**
		 * Determine if the current ArrayCollectio being iterated through has a next
		 * value.
		 */
		@Override
		public boolean hasNext() {
			return nextIndex < size();
		}

		/**
		 * Returns the next value in the iterator. If no such value exists, then a
		 * NoSuchElementException is thrown.
		 */
		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			canRemove = true;
			return data[nextIndex++];

		}

		/**
		 * Returns the last value returned by next from the iterator. If a value cannot
		 * be removed an IllegalStateException is thrown.
		 */
		@Override
		public void remove() {
			if (canRemove) {
				ArrayCollection.this.remove(data[nextIndex-1]);
				canRemove = false;
				// shift the next index back once an element has been removed
				nextIndex--;
			} else {
				throw new IllegalStateException();
			}

		}
		
		@Override
		public String toString() {
			return "next index: " + nextIndex + " can remove: " + canRemove;
		}

	}

}
