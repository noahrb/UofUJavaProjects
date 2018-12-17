package assignment7;

import java.util.*;

/**
 * Represents a generic stack (first in, last out).
 * 
 * @author Joshua Whisenant and Noah Beverly
 * 
 * @param <T>
 *            -- the type of elements contained in the stack
 */
public class MyStack<T> {

	private LinkedList<T> stack;

	public MyStack() {
		stack = new LinkedList<T>();
	}

	/**
	 * Removes all of the elements from the stack.
	 */
	public void clear() {
		stack.clear();
	}

	/**
	 * Returns true if the stack contains no elements.
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	/**
	 * Returns the item at the top of the stack without removing it from the
	 * stack. Throws NoSuchElementException if the stack is empty.
	 */
	public T peek() throws NoSuchElementException {
		return stack.getLast();
	}

	/**
	 * Returns and removes the item at the top of the stack. Throws
	 * NoSuchElementException if the stack is empty.
	 */
	public T pop() throws NoSuchElementException {
		return stack.removeLast();
	}

	/**
	 * Pushes the input item onto the top of the stack.
	 */
	public void push(T item) {
		stack.addLast(item);
	}

	/**
	 * Returns the number of items in the stack.
	 */
	public int size() {
		return stack.size();
	}
}
