package assignment6;

import java.util.*;

public class MyLinkedList <T> implements List<T>{

	Node <T> head;
	Node <T> tail;
	int size = 0;

	/**
	 * Constructor method for MyLinkedList
	 */
	public MyLinkedList() {

	}

	/**
	 * Adds an element to the beginning of a LinkedList
	 * @param element	element to be added
	 */
	@Override
	public void addFirst(T element) {
		add(0,element);
	}

	/**
	 * Adds an element to the end of a LinkedList
	 * @param element	the element to be added
	 */
	@Override
	public void addLast(T element) {
		add(size,element);
	}

	/**
	 * Adds an element to a LinkedList at the user-specified index
	 * @param index 	the index of the element to be added
	 * @param element	the element to be added
	 */
	@Override
	public void add(int index, T element) throws IndexOutOfBoundsException {

		if (index > size) throw new IndexOutOfBoundsException("Cannot add to further than the end of the list");
		if (index < 0) throw new IndexOutOfBoundsException("Cannot add a negative index");

		Node<T> nodeToInsert = new Node<T>(element);

		if (index == 0 && size != 0) {
			nodeToInsert.next = head;
			nodeToInsert.previous = null;
			head.previous = nodeToInsert;
			head = nodeToInsert;
		} else if (index ==0 && size == 0) {
			head = new Node<T>(element);
			/*
			 *  initializes the tail: 
			 *  though this element technically doesn't exist in the list,
			 *  the user can never access it so all is well 
			 */
			tail = new Node<T>(element);
			tail.previous = nodeToInsert;
			tail.next = null;

		} else if (index ==1 && size ==1) {
			tail = new Node<T>(element);
			tail.previous = head;
			head.next = tail;
			tail.next = null;
		} else if (index == size) {
			nodeToInsert.previous = tail;
			tail.next = nodeToInsert;
			nodeToInsert.next = null;
			tail = nodeToInsert;
		} else {
			Node <T> nextNode = getNode(index);
			Node <T> previousNode = nextNode.previous;


			nodeToInsert.previous=previousNode;
			previousNode.next=nodeToInsert;
			nodeToInsert.next=nextNode;
			nextNode.previous=nodeToInsert;

		}
		size++;
	}

	/**
	 * Returns the first element of a LinkedList
	 * @throws NoSuchElementException if the list size is zero
	 */
	@Override
	public T getFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("Empty List");
		} else {
			return head.element;
		}
	}


	/**
	 * Returns the last element of a LinkedList
	 * @return The last element of a LinkedList
	 * @throws NoSuchElementException if the list size is zero
	 */
	@Override
	public T getLast() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("Empty List");
		} else {
			return tail.element;
		}
	}

	/**
	 * Returns an element based on the index provided
	 * @param	the index of the element to get
	 * @return	an element of a LinkedList based on the index
	 * @throws	an out-of-bounds exception if the index is < 0 or > size-1
	 */
	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if (index>size-1) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than the greatest index (" + (size-1) + ") of the list");
		if (index<0) throw new IndexOutOfBoundsException("Cannot remove an index less than zero. Index is: " + index);
		Node<T> out = getNode(index);
		return out.element;

	}

	/**
	 * @return the first element in the LinkedList
	 * @throws a NoSuchElementException if the list is empty
	 */
	@Override
	public T removeFirst() throws NoSuchElementException {
		if (this.isEmpty()) throw new NoSuchElementException("Cannot remove from an empty list");
		if (size == 1) {
			T out = head.element;
			head = null;
			size--;
			return out;
		} else {
			T out = head.element;
			head = head.next;
			head.previous = null;
			size--;
			return out;
		}
	}

	/**
	 * @return the last element in the LinkedList
	 * @throws a NoSuchElementException if the list is empty
	 */
	@Override
	public T removeLast() throws NoSuchElementException {

		if (size == 0) throw new NoSuchElementException("Cannot remove from an empty list");
		T out = tail.element;
		tail = tail.previous;
		tail.next = null;
		size--;
		return out;

	}

	/**
	 * @param	the index of the item to remove
	 * @return	the element removed
	 * @throws	an IndexOutOfBoundsException if the index is < 0 or > size-1
	 */
	@Override
	public T remove(int index) throws IndexOutOfBoundsException {

		if (index>size-1) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than the greatest index (" + (size-1) + ") of the list");
		if (index<0) throw new IndexOutOfBoundsException("Cannot remove an index less than zero. Index is: " + index);
		
		if (index == 0) {
			T out = removeFirst();
			return out;
		} else if (index == size-1) {
			T out = removeLast();
			return out;
		} else {
			Node<T> toRemove = getNode(index);
			T out = toRemove.element;
			Node<T> previousNode = toRemove.previous;
			Node<T> nextNode = toRemove.next;
			previousNode.next = nextNode;
			nextNode.previous = previousNode;
			size--;
			return out;
		}
	}

	/**
	 * Finds the first index of an element in a LinkedList
 	 * @param element	the element to find the index of
	 * @return	the first index of the element in the list 
	 * (if the element is not in the list, returns -1) 
	 */
	@Override
	public int indexOf(T element) {
		Node <T> temp = new Node<T>(element);
		temp = head;
		for (int i = 0; i < size; i++) {
			if (temp.element.equals(element)) {
				return i;
			}
			temp = temp.next;
		}
		return -1;
	}

	/**
	 * Finds the last index of an element in a LinkedList
 	 * @param element	the element to find the index of
	 * @return	the last index of the element in the list 
	 * (if the element is not in the list, returns -1) 
	 */
	@Override
	public int lastIndexOf(T element) {
		Node <T> temp = new Node<T>(element);
		temp = tail;
		for (int i = size-1; i >=0; i--) {
			if (temp.element.equals(element)) {
				return i;
			}
			temp = temp.previous;
		}
		return -1;
	}

	/**
	 * @return	the size of a LinkedList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return	true if a LinkedList is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) return true; else return false;
	}

	/**
	 * empties a LinkedList
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}


	/**
	 * @return an Object array containing the data of the LinkedList
	 * returns null if the LinkedList is empty
	 */
	@Override
	public Object[] toArray() {
		
		if (this.isEmpty()) {
			return null;
		}
		
		Object[] out = new Object[size];
		out[0] = head.element;
		Node <T> temp = new Node <T>(head.element);
		temp = head.next;	
		
		for (int i = 1; i < size; i++) {
			out[i] = temp.element;
			temp = temp.next;
		}
		
		return out;
	}


	/**
	 * Private helper method for MyLinkedList class
	 * Searches through a LinkedList to return an element at the given index
	 * If the index is in the last half of the array, searches from the back to the middle
	 * If the index is in the first half of the array, searches from the front to the middle
	 * @param index of node to find
	 * @return Node at given index
	 */
	private Node<T> getNode(int index) {

		Node <T> previousNode;

		if (index < size/2) {
			previousNode = head;
			for (int i = 1; i <= index; i++) {
				previousNode = previousNode.next;
			}
		} else {
			previousNode = tail;
			for (int i = size-1; i > index; i--) {
				previousNode = previousNode.previous;
			}
		}

		return previousNode;
	}

	private class Node<T> {

		T element;
		Node <T> next;
		Node <T> previous;

		Node (T data) {
			element = data;
			next = null;
			previous = null;
		}

	}


}
