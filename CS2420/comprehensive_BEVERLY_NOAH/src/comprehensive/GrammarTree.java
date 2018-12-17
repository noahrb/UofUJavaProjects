package comprehensive;
import java.util.*;
import java.io.*;

/** 
 * @author Joshua Whisenant & Noah Beverly
 * A GrammarTree holds a grammar per the syntax instructions given in class
 * Can generate a random phrase from the tree. 
 */
public class GrammarTree {

	private TreeMap<String,Node> nodes;

	/**
	 * Generates a new grammar tree based on the filename provided
	 * @param filename
	 */
	public GrammarTree (String filename) {
		nodes = new TreeMap<String,Node>();
		readInFile(filename);
	}
	
	/**
	 * Initializes a new GrammarTree that can be used for testing
	 * @param height	the user-specified height of the tree to be generated
	 * @param percentToRecurse	the user-specified likelihood an option will step back one item in the tree
	 * @param charsPerOption	the user-specified number of characters each option should contain 
	 * 							(plus a ' <[integer]>' at the end of each option to step into the next node)
	 */
	public GrammarTree (int height, int percentToRecurse, int charsPerOption) {
		// initialize nodes tree map structure
		nodes = new TreeMap<String,Node>();

		// initialize new random (used later)
		Random rand = new Random();

		// create the starting node
		Node startNode = new Node();
		startNode.title = "<start>";
		startNode.options.add("<1>");
		nodes.put("<start>",startNode);

		// create the rest of the tree
		for (int i = 1; i < height; i++) {

			/*
			 * create an 'addString.' 
			 * addString length lets the user control the number of characters in each option of each node
			 */
			String addString = "";
			StringBuilder SB = new StringBuilder();
			for (int j = 0; j < charsPerOption; j++) {
				SB.append(Integer.toString(i));
			}
			addString = SB.toString();

			// generate the to be added node
			Node toAdd = new Node();
			toAdd.title = "<" + Integer.toString(i) + ">";

			/*
			 *  generates the recursive parts of the tree. 
			 *  Only steps one step back in recursion.
			 */
			if (i!=1) {
				while (Math.abs((rand.nextInt()%100)) < percentToRecurse) {
					toAdd.options.add(addString + " <" + Integer.toString(i-1) +">");
				}
			}
			// Either adds the pathway to the next node, or adds no angle brackets if is a terminal
			if (i != height-1) {
				toAdd.options.add(addString + " <" + Integer.toString(i+1) +">");
				nodes.put(toAdd.title, toAdd);
			} else {
				toAdd.options.add(addString);
				nodes.put(toAdd.title, toAdd);
			}
		}
	}

	/**
	 * returns the size of the GrammarTree
	 */
	public int size() {
		return nodes.size();
	}

	/**
	 * Prints all the nodes of the tree in a way easy for a human to understand. 
	 * Designed to make the GrammarTree's functionality more clear
	 */
	public void printAllNodes () {
		Collection<Node> c = nodes.values();
		Iterator<Node> itr = c.iterator();
		while (itr.hasNext()) {
			Node toPrint = (Node) itr.next();
			System.out.println("Title: " + toPrint.title);
			System.out.println("Options: ");
			for (int i = 0; i < toPrint.options.size(); i++) {
				System.out.println("   " + toPrint.options.get(i));
			}
			System.out.println();
		}
	}

	/**
	 * Generates a random phrase from the grammar stored in the tree
	 */
	public String generateRandomPhrase () {
		Random rand = new Random(); 
		String start = getOption(getNode("<start>"),rand);		
		return(generateRecursiveSolution(start,rand));
	}

	/**
	 * Recursive method to generate solution
	 * @param solution the solution ArrayList to continue adding things to
	 * @param in the ArrayList of strings to add, piece by piece, to the solutions ArrayList (corresponds to one option of one node)
	 * @param rand a random number generator to select which option of the node to take
	 */
	private String generateRecursiveSolution (String in, Random rand) {
		StringBuilder outSB = new StringBuilder();
		for (int i = 0; i < in.length(); i++) {
			if (in.charAt(i) != '<') {
				outSB.append(in.charAt(i));
			} else {
				String newTitle = ""; 
				while (in.charAt(i)!= '>') {
					newTitle += in.charAt(i);
					i++;
				}
				newTitle += '>';
				outSB.append(generateRecursiveSolution(getOption(getNode(newTitle),rand),rand));
			}
		}
		return outSB.toString();
	}

	/**
	 * Returns a string that is a sub-option inside a Node
	 * @param n
	 * @param rand
	 * @return
	 */
	private String getOption (Node n, Random rand) {
		if (n.options.size() == 0) {
			return null;
		}
		int getIndex = rand.nextInt(n.options.size());
		return n.options.get(getIndex);
	}

	/**
	 * @param str the string to parse: assumes it has the '<' and '>' characters 
	 * @return
	 */
	private Node getNode (String str) {
		return nodes.get(str);
	}

	/**
	 * Generates a grammar tree based on a file given. Does not perform error checking
	 * @param filename
	 */
	private void readInFile (String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));

			while (true) {
				String in = br.readLine();
				if (in == null) break;
				if (in.equals("{")) { 	
					Node thisNode = new Node();
					String title = br.readLine();
					title = title.substring(1, title.length()-1);
					thisNode.title = title;
					while (true) {
						in = br.readLine();
						if (in.equals("}")) break;
						if (!isWhiteSpace(in)) thisNode.options.add(in);
					}
					nodes.put("<" + thisNode.title + ">",thisNode);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns true if there is no text in the string given
	 * @param str
	 * @return true if white space, else false
	 */
	private boolean isWhiteSpace (String str) {
		if (str == "") return true;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ') return false;
		}
		return true;
	}

	/**
	 * A Node represents either a terminal or non-terminal in a grammar structure. 
	 * Has a title (includes '<' and '>' and an ArrayList of the different options to select from. 
	 * @author Josh Whisenant & Noah Beverly
	 */
	class Node {	

		String title;
		ArrayList<String> options;

		public Node() {
			options = new ArrayList<String>();
			title = "";
		}

	}
}
