package assignment8;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

	// root variable of the BST
	private BinaryTreeNode root;
	
	// size variable
	private int size = 0;
	
	/**
	 * A BinarySearchTree is a tree data structure that facilitates quick searching and sorting. 	
	 */
	public BinarySearchTree() {

	}

	/**
	 * @param T item item to be added to the BST
	 * @return true if item was added, or false if not
	 */
	public boolean add(T item) {
		if (root == null) {
			size++;
			root = new BinaryTreeNode(item);
			return true;
		} else {
			return addRecursive(item, root);
		}
	}

	/**
	 * Private helper method for the add function. 
	 * Recursively steps through the list until the appropriate location is found
	 * @param item item to be added
	 * @param current the current position in the list
	 * @return true if item was added, or else false
	 */
	private boolean addRecursive(T item, BinaryTreeNode current) {

		if (item.equals(current.data)) return false;

		if (item.compareTo(current.data) <= 0) { // item is smaller than or equal to current ** NOTE - remove '=' based on Piazza response

			// perform left handed operations

			if (current.left == null) {
				current.left = new BinaryTreeNode(item,current);
				size++;
				return true;
			} else {
				if (!addRecursive(item, current.left)) {
					return false;
				}
			}
		} else {

			// perform right handed operations

			if (current.right == null) {
				current.right = new BinaryTreeNode(item,current);
				size++;
				return true;
			} else {
				if (!addRecursive(item, current.right)) {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * @param Collection items	a collection of items to be added
	 * @return returns true if all items are added, or else false
	 */
	public boolean addAll(Collection<? extends T> items) {
		boolean out = false;
		Iterator<? extends T> itr = items.iterator();
		while (itr.hasNext()) {
			this.add(itr.next());
			out = true;
		}
		return out;
	}

	/**
	 * Deletes all items in the BST and resets size to 0
	 */
	public void clear() {
		root.left = null;
		root.right = null;
		root = null;
		size = 0;
	}

	/**
	 * Returns true if an item is contained in the BST, or else returns false
	 * @param item	the item to search for
	 * @return true if the item is in the BST, or else false
	 */
	public boolean contains(T item)
	{
		return searchRecursive(item, root);
	}

	/**
	 * Private helper method to assist the contains method. 
	 * Searches recursively down the BST, using comparisons to determine whether to go left or right
	 * @param item	the item to search for
	 * @param n	the current node to compare the item to
	 * @return true if the item is found, or false if the end of the BST is reached and the item is not there
	 */
	private boolean searchRecursive(T item, BinaryTreeNode n)
	{
		if(n == null)
			return false;

		if(item.equals(n.data))
			return true;

		if(item.compareTo(n.data) < 0)
			return searchRecursive(item, n.left);
		else
			return searchRecursive(item, n.right);
	}

	/**
	 * Searches to see if the BST contains a collection of items. 
	 * Returns true only if the BST contains all of the items. 
	 * @param Collection items	the list of items to search for
	 * @return	True if all the items are present, or else false
	 */
	public boolean containsAll(Collection<? extends T> items) {

		Iterator<? extends T> itr = items.iterator();

		while (itr.hasNext()) {
			if (!this.contains(itr.next())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the smallest item in the BST
	 * @throws a NoSuchElementException if the list is empty
	 */
	public T first() throws NoSuchElementException {
		if (root == null) throw new NoSuchElementException();

		BinaryTreeNode current = root;
		while (current.left != null) {
			current = current.left;
		}
		return current.data;
	}

	/**
	 * Returns true if the list is empty, or else false
	 * @return true if the list is empty, false if the list is not empty
	 */
	public boolean isEmpty() {
		if (size == 0) return true;
		return false;
	}

	/**
	 * Returns the largest item in the BST
	 * @throws a NoSuchElementException if the list is empty
	 */
	public T last() throws NoSuchElementException {
		if (root == null) throw new NoSuchElementException();

		BinaryTreeNode current = root;
		while (current.right != null) {
			current = current.right;
		}
		return current.data;
	}

	/**
	 * Removes an item from this list
	 * @param item the item to be removed
	 * @return true if the item was removed, or else false
	 */
	public boolean remove(T item) {

		if (!this.contains(item)) return false;

		BinaryTreeNode toRemove = findNode(item);
		BinaryTreeNode replacement = new BinaryTreeNode(item);

		//**** case 1: toRemove is root ****
		if (toRemove.parent == null) {

			if (size == 0) {
				this.clear();
				size--;
				return true;
			}

			boolean removedFromRight = false;
			if (toRemove.right != null) {
				replacement = this.getSuccessorFromRight(toRemove);
				removedFromRight = true;
			}
			if (toRemove.right == null) replacement = this.getSuccessorFromLeft(toRemove);

			// At this point, we have a replacement node for the root, and a boolean telling us if we got it from left or right

			if (removedFromRight && replacement.parent != root) { // standard procedure, new root removed from right side of the tree
				replacement.parent.left = replacement.right;
				replacement.left = root.left;
				replacement.right = root.right;
				replacement.parent = null;
				root = replacement;
				size--;
				return true;
			} else if (!removedFromRight && replacement.parent != root) { // standard procedure, new root removed from right side of the tree
				replacement.parent.right = replacement.left;
				replacement.left = root.left;
				replacement.right = root.right;
				replacement.parent = null;
				root = replacement;
				size--;
				return true;
			} else if (removedFromRight && replacement.parent == root) { // new root removed from right side of tree, replacement's parent is root
				replacement.left = root.left;
				replacement.parent = null;
				root = replacement;
				size--;
				return true;
			} else if (!removedFromRight && replacement.parent == root) { // new root removed from left side of tree, replacement's parent is root
				replacement.right = root.right;
				replacement.parent = null;
				root = replacement;
				size--;
				return true;
			}

			//**** case 2: toRemove is a leaf ****
		} else if ((toRemove.right == null) && (toRemove.left == null)) {
			if (toRemove.parent.left == toRemove) {
				toRemove.parent.left = null;
				size--;
				return true;
			}
			if (toRemove.parent.right == toRemove) {
				toRemove.parent.left = null;
				size--;
				return true;
			}

			//**** case 3: toRemove is a middle node with one child ****
		} else if ((toRemove.left == null) || (toRemove.right == null)) {
			if (toRemove.parent.left == toRemove) { // if toRemove is the left-hand child of its parent
				if (toRemove.left != null) {
					toRemove.parent.left = toRemove.left;
					size--;
					return true;
				}
				if (toRemove.right != null) {
					toRemove.parent.left = toRemove.right;
					size--;
					return true;
				}
			}

			if (toRemove.parent.right == toRemove) { // if toRemove is the right-hand child of its parent
				if (toRemove.left != null) { // if the continuation is on the left side, then assign the left side of the remove node to the right position of its parent
					toRemove.parent.right = toRemove.left;
					size--;
					return true;
				} else if (toRemove.right != null) { // if the continuation is on the right side, then assign the right side of the remove node to the right position of its parent
					toRemove.parent.right = toRemove.right;
					size--;
					return true;
				}
			}
			//**** case 4: toRemove is a middle node with two children ****
		} else {

			replacement = getSuccessorFromRight(toRemove);

			if (replacement.parent != toRemove) {
				remove(getSuccessorFromRight(toRemove).data);
				toRemove.data = replacement.data;
				return true;
			} else {
				T newValue = replacement.data;
				toRemove.right = replacement.right;
				toRemove.data = newValue;
			}
		}
		return false;
	}

	/**
	 * A private helper method to assist in removing
	 * Takes one LH step, then as many RH steps as it takes
	 * @param node the node to remove
	 * @return the node that will replace the removed node
	 */
	private BinaryTreeNode getSuccessorFromLeft(BinaryTreeNode node) {

		BinaryTreeNode nodeLeft = node.left;

		while(nodeLeft.right != null) {
			nodeLeft = nodeLeft.right;
		}

		return nodeLeft;

	}

	/**
	 * A private helper method to assist in removing
	 * Takes one RH step, then as many LH steps as it takes
	 * @param node the node to remove
	 * @return the node that will replace the removed node
	 */
	private BinaryTreeNode getSuccessorFromRight(BinaryTreeNode node) {

		BinaryTreeNode nodeRight = node.right;

		while(nodeRight.left != null) {
			nodeRight = nodeRight.left;
		}

		return nodeRight;

	}

	/**
	 * Finds a node in a BST
	 * @param item the item to find
	 * @return the node that contains that item
	 * Returns null if the item does not exist
	 */
	private BinaryTreeNode findNode(T item) {
		if (item.equals(root.data)) {
			return root;
		}

		else return findNodeRecursive(item, root);
	}

	/**
	 * A recursive method to help findNode
	 */
	private BinaryTreeNode findNodeRecursive(T item, BinaryTreeNode current) {
		if(current == null)
			return null;

		if(item.equals(current.data))
			return current;

		if(item.compareTo(current.data) < 0)
			return findNodeRecursive(item, current.left);
		else
			return findNodeRecursive(item, current.right);

	}

	/**
	 * Removes all the items from the BST that are given in the argument collection
	 * Returns true if all are removed, or else false
	 * @param Collection items	the items to be removed
	 * @return true if all are removed, or else false
	 */
	public boolean removeAll(Collection<? extends T> items) {
		boolean out = false;
		Iterator<? extends T> itr = items.iterator();
		while (itr.hasNext()) {
			this.remove(itr.next());
			out = true;
		}
		return out;
	}

	/**
	 * Returns the size of the BST
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the BinarySearchTree's data in the form of a sorted ArrayList
	 * @return an ArrayList with the BST's contents in sorted order
	 */
	@Override
	public ArrayList<T> toArrayList() {
		ArrayList<T> out = new ArrayList<T>();
		toArrayListRecursive(out,root);
		return out;
	}

	/**
	 * A private helper method for toArrayList that recursively adds content into an ArrayList
	 * @param arr the ArrayList to add to
	 * @param n a BST node whose data must be added
	 */
	private void toArrayListRecursive (ArrayList<T> arr, BinaryTreeNode n) {		
		if (n.left != null) toArrayListRecursive(arr,n.left);
		arr.add(n.data);
		if (n.right != null)toArrayListRecursive(arr,n.right);				
	}

	/**
	 * A method that writes the BST as a dot file to a location on the user's memory
	 * @param filename the location to save the file to
	 */
	public void writeDot(String filename)	{
		try {
			// PrintWriter(FileWriter) will write output to a file
			PrintWriter output = new PrintWriter(new FileWriter(filename));

			// Set up the dot graph and properties
			output.println("digraph BST {");
			output.println("node [shape=record]");

			if(root != null) {
				writeDotRecursive(root, output);
			}
			// Close the graph
			output.println("}");
			output.close();
		}
		catch(Exception e){e.printStackTrace();
		}
	}

	/**
	 * Private recursive method for writing the tree to  a dot file
	 */
	private void writeDotRecursive(BinaryTreeNode n, PrintWriter output) throws Exception	{
		output.println(n.data + "[label=\"<L> |<D> " + n.data + "|<R> \"]");
		if(n.left != null) {
			// write the left subtree
			writeDotRecursive(n.left, output);

			// then make a link between n and the left subtree
			output.println(n.data + ":L -> " + n.left.data + ":D" );
		}
		if(n.right != null)		{
			// write the left subtree
			writeDotRecursive(n.right, output);

			// then make a link between n and the right subtree
			output.println(n.data + ":R -> " + n.right.data + ":D" );
		}

	}

	/**
	 * Class BinaryTreeNode is the building block for the BST. 
	 * Each node holds a T data item, and links to left, right, and parent nodes.
	 */
	private class BinaryTreeNode {

		// Since the outer BST class declares <T>, we can use it here without redeclaring it for BinaryNode
		T data;

		BinaryTreeNode left;

		BinaryTreeNode right;

		BinaryTreeNode parent;


		/**
		 * Construct a new node with a parent
		 */
		public BinaryTreeNode(T _data, BinaryTreeNode _parent) {
			data = _data;
			left = null;
			right = null;
			parent = _parent;
		}

		/**
		 * Construct a new node with no parent
		 */
		public BinaryTreeNode(T _data) {
			// Invoke the 2-parameter constructor with null parent
			this(_data, null);
		}

	}

}
