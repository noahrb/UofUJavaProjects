package assignment11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a priority queue of generically-typed items. 
 * The queue is implemented as a min heap. 
 * The min heap is implemented implicitly as an array.
 * 
 * @author Erin Parker, Joshua Whisenant, & Noah Beverly
 */
public class PriorityQueue<T> {

	private int currentSize;

	private T[] array;

	private Comparator<? super T> cmp;

	/**
	 * Constructs an empty priority queue. Orders elements according
	 * to their natural ordering (i.e., T is expected to be Comparable)
	 * T is not forced to be Comparable.
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue() {
		currentSize = 0;
		cmp = null;
		array = (T[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * Construct an empty priority queue with a specified comparator.
	 * Orders elements according to the input Comparator (i.e., T need not
	 * be Comparable).
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue(Comparator<? super T> c) {
		currentSize = 0;
		cmp = c;
		array = (T[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() {
		return currentSize;
	}

	/**
	 * Makes this priority queue empty.
	 */
	public void clear() {
		currentSize = 0;
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in constant time.)
	 */
	public T findMin() throws NoSuchElementException {
		return array[0];
	}

	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in logarithmic time.)
	 */
	public T deleteMin() throws NoSuchElementException {
		if (currentSize == 0) throw new NoSuchElementException(); // if the heap is empty, throw a NoSuchElementException
		T out = array[0];
		array[0] = array[currentSize-1];
		array[currentSize-1] = null;
		currentSize--;
		
		percolateDown();

		return out;
	}

	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in constant time.)
	 * 
	 * @param x -- the item to be inserted
	 */
	public void add(T x) {
		if (currentSize >= array.length) this.grow();

		this.array[currentSize] = x;

		percolateUp(currentSize);

		currentSize++;

	}

	/**
	 * Used in the deleteMin method. When an item is deleted, a larger item than ideal may be at index zero. 
	 * This method moves that item down the heap until it is in an appropriate position.
	 */
	private void percolateDown() {

		T percolationItem = array[0];
		T childItem = getChildItem(0);
		int currentIndex = 0;
		int childIndex = getChildIndex(0);

		while (childItem != null && this.compare(percolationItem, childItem) > 0) { // i.e. when the percolationItem is bigger than the smaller childItem

			this.array[currentIndex] = childItem;
			this.array[childIndex] = percolationItem;

			currentIndex = childIndex;	

			childIndex = getChildIndex(currentIndex);
			childItem = getChildItem(currentIndex);

		}
	}

	/**
	 * Used in the add method. When an item is added, it may be too small for its index
	 * This method moves that item up the heap until it is in the right position.
	 */
	private void percolateUp (int childIndex) {

		int parentIndex = findParentIndex(childIndex);

		T childItem = this.array[childIndex];
		T parentItem = this.array[parentIndex];



		while (this.compare(childItem, parentItem) < 0) {


			this.array[childIndex] = parentItem;
			this.array[parentIndex] = childItem;

			childIndex = parentIndex;
			parentIndex = findParentIndex(childIndex);
			parentItem = findParent(childIndex);

		}
	}
	
	/**
	 * Internal method that returns the index of the smaller child of the parent
	 * NOTE: does NOT perform error checking. Required error checking is all performed in the getChildItem method
	 */
	private int getChildIndex (int parentIndex) {

		T leftChild = findLeftChild(parentIndex);
		T rightChild = findRightChild(parentIndex);

		if (rightChild == null) {
			return findLeftChildIndex(parentIndex);
		} else if (this.compare(leftChild, rightChild) > 0) { // i.e. if the left child is larger
			return findRightChildIndex (parentIndex);
		} else {
			return findLeftChildIndex (parentIndex);
		}
	}

	/**
	 * Internal method that returns the smallest child item of a parent index, or else it returns null
	 */
	private T getChildItem(int parentIndex) {

		T leftChild = findLeftChild(parentIndex);
		T rightChild = findRightChild(parentIndex);

		if (rightChild == null && leftChild == null) {
			return null;
		} else if (rightChild == null) {
			return leftChild;
		} else if (this.compare(leftChild, rightChild) > 0) { // i.e. if the left child is larger
			return rightChild;
		} else {
			return leftChild;
		}
	}

	/**
	 * returns the parent item of the item at index i
	 */
	private T findParent (int i) {
		return this.array[(i-1)/2];
	}

	/**
	 * returns the parent index of the item at index i
	 */
	private int findParentIndex (int i) {
		return (i-1)/2;
	}

	/**
	 * returns the left T child of a parent at index i
	 * if there is no child to find, returns null
	 */
	private T findRightChild (int i) {
		if (i*2 + 2 >= array.length) return null;
		return this.array[i*2+2];
	}

	/**
	 * returns the index of the right child of the parent index at i
	 */
	private int findRightChildIndex (int i) {
		return i*2 + 2;
	}

	/**
	 * returns the left T child of a parent at index i
	 * if there is no child to find, returns null
	 */
	private T findLeftChild (int i) {
		if (i*2 + 1 >= array.length) return null;
		return this.array[i*2+1];
	}

	/*
	 * returns the index of the left child of the parent index
	 */
	private int findLeftChildIndex (int i) {
		return i*2 + 1;
	}

	/**
	 * Internal method that doubles the array size if the number of items in the heap is big enough
	 */
	@SuppressWarnings("unchecked")
	private void grow () {
		T[] temp = (T[]) new Object[array.length *2];
		for (int i = 0; i < currentSize; i++) {
			temp[i] = array[i];
		}
		this.array = temp;
	}

	/**
	 * Generates a DOT file for visualizing the binary heap.
	 */
	public void generateDotFile(String filename) {
		try {
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Heap {\n\tnode [shape=record]\n");

			for(int i = 0; i < currentSize; i++) {
				out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]");
				if(((i*2) + 1) < currentSize)
					out.println("\tnode" + i + ":f0 -> node" + ((i*2) + 1) + ":f1");
				if(((i*2) + 2) < currentSize)
					out.println("\tnode" + i + ":f2 -> node" + ((i*2) + 2) + ":f1");
			}

			out.println("}");
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Internal method for comparing lhs and rhs using Comparator if provided by the
	 * user at construction time, or Comparable, if no Comparator was provided.
	 */
	private int compare(T lhs, T rhs) { // always remember, if lhs is bigger than rhs, returns a positive number.
		if (cmp == null)
			return ((Comparable<? super T>) lhs).compareTo(rhs); // safe to ignore warning
		// We won't test your code on non-Comparable types if we didn't supply a Comparator

		return cmp.compare(lhs, rhs);
	}

	//LEAVE IN for grading purposes
	public Object[] toArray() {    
		Object[] ret = new Object[currentSize];
		for(int i = 0; i < currentSize; i++)
			ret[i] = array[i];
		return ret;
	}

}
