package assignment9;

import java.util.*;
import java.io.*;

/**
 * @author Joshua Whisenant & Noah Beverly
 * 
 */
public class PathFinder {

	/**
	 * Finds the shortest path from the start to the goal in the
	 * input maze file.
	 * The output file should contain the same maze with the shortest 
	 * path marked. See the assignment instructions for details.
	 * @param inputFile - the file path to the input maze
	 * @param outputFile - the file path to the output maze
	 */
	public static void solveMaze (String inputFile, String outputFile) {

		Graph graph = new Graph(generateGraphDataFromFile(inputFile));
		performBFS(graph);
		generateFileFromGraph(outputFile, graph);



	}

	/**
	 * Conducts a breadth-first search on a graph. 
	 * Does not return a new graph; only changes the graph's contents
	 * The returned graph has a series of nodes that is now a singly-linked list. 
	 * These nodes track from the end of the graph back to the beginning. 
	 * @param graph	the graph to adjust
	 */
	private static void performBFS (Graph graph) {

		Queue<CoordinatePair> qe = new Queue <CoordinatePair>();
		CoordinatePair startCoordinates = getStartCoordinates(graph);
		CoordinatePair endCoordinates = getEndCoordinates(graph);

		qe.enqueue(startCoordinates);
		graph.get(startCoordinates).visited = true;

		while (!qe.isEmpty()) {

			CoordinatePair current = qe.dequeue();

			if (graph.get(current).data == 'G') break;

			if (graph.hasLeft(current) 
					&& graph.getLeft(current).data != 'X' 
					&& !graph.getLeft(current).visited) {
				CoordinatePair add = new CoordinatePair(current.x-1,current.y);
				qe.enqueue(add);
				graph.get(add).visited = true;
				graph.get(add).cameFrom = graph.get(current);
			}

			if (graph.hasRight(current) 
					&& graph.getRight(current).data != 'X' 
					&& !graph.getRight(current).visited) {
				CoordinatePair add = new CoordinatePair(current.x + 1,current.y);
				qe.enqueue(add);
				graph.get(add).visited = true;
				graph.get(add).cameFrom = graph.get(current);
			}

			if (graph.hasBelow(current) 
					&& graph.getBelow(current).data != 'X'
					&& !graph.getBelow(current).visited) {
				CoordinatePair add = new CoordinatePair(current.x,current.y+1);
				qe.enqueue(add);
				graph.get(add).visited = true;
				graph.get(add).cameFrom = graph.get(current);
			}

			if (graph.hasAbove(current) 
					&& graph.getAbove(current).data != 'X'
					&& !graph.getAbove(current).visited) {
				CoordinatePair add = new CoordinatePair(current.x,current.y-1);
				qe.enqueue(add);
				graph.get(add).visited = true;
				graph.get(add).cameFrom = graph.get(current);
			}

		}


		buildPathBackToStartRecursive(graph.get(endCoordinates));

	}

	/**
	 * Iterates through a singly-linked list of nodes, and replaces each Node's data with a '.'
	 * The singly-linked list will be the shortest path back to start. 
	 * Assumes the starting node is marked with an 'S,' and the ending node is marked with a 'G.'
	 * @param n	the goal node in the graph. 
	 */
	private static void buildPathBackToStartRecursive(Node n) {

		if (n.data == 'S') return;

		if (n.data != 'G') n.data = '.';

		buildPathBackToStartRecursive(n.cameFrom);

	}

	/**
	 * Searches through a graph to find the coordinates of 'S'
	 * @param graph to search
	 * @return the CoordinatePair of the starting location. 
	 */
	private static CoordinatePair getStartCoordinates (Graph graph) {
		int y = 0;
		while (y <graph.height) {
			int x = 0;
			while (x<graph.width) {
				CoordinatePair c = new CoordinatePair (x,y);
				if (graph.get(c).data == 'S') {
					return new CoordinatePair(x,y);
				}
				x++;
			}
			y++;
		}
		return null;
	}

	/**
	 * Searches through a graph to find the coordinates of 'G'
	 * @param graph to search
	 * @return the CoordinatePair of the ending location. 
	 */
	private static CoordinatePair getEndCoordinates (Graph graph) {
		int y = 0;
		while (y <graph.height) {
			int x = 0;
			while (x<graph.width) {
				CoordinatePair c = new CoordinatePair (x,y);
				if (graph.get(c).data == 'G') {
					return new CoordinatePair(x,y);
				}
				x++;
			}
			y++;
		}
		return null;
	}

	/**
	 * takes in a data file and converts it into a graph
	 * @param inputFile the location of the file to read in
	 * @return an ArrayList of ArrayList of Nodes
	 * that can then be passed to the graph constructor
	 */
	private static ArrayList<ArrayList<Node>> generateGraphDataFromFile (String inputFile) {

		//****** STEP 1: Generate an ArrayList of the lines of the text file using a buffered reader. ******//

		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<ArrayList<Node>> out = new ArrayList<ArrayList<Node>>();

		try {


			BufferedReader in = new BufferedReader(new FileReader(new File(inputFile)));
			in.readLine();
			String current = "";

			while (current != null) {
				current = in.readLine();
				if (current!=null) lines.add(current);	
			}
			in.close();

		} catch (Exception e) {
			System.out.println("Error - File not found");
			e.printStackTrace();
		}

		// ****** STEP 2: Turn the ArrayList of lines into an ArrayList of an ArrayList of Nodes ****** //
		for (int i = 0; i < lines.size(); i++) {

			ArrayList<Node> temp = new ArrayList<Node>();

			for (int j = 0; j < lines.get(i).length(); j++) {
				Node toAddToTemp = new Node();
				toAddToTemp.data = lines.get(i).charAt(j);
				temp.add(toAddToTemp);
			}

			out.add(temp);

		}

		return out;


	}

	/**
	 * Converts a graph into a text file at the location specified. 
	 * @param outputFile the location to write the file to
	 * @param graph
	 */
	private static void generateFileFromGraph(String outputFile, Graph graph) {
		
		ArrayList<ArrayList<Node>> toPrint = graph.toArray();
		try {
			PrintWriter pw = new PrintWriter(new File(outputFile));
			pw.println(graph.height + " " + graph.width);
			for (int i = 0; i < toPrint.size(); i++) {
				
				String printThisLine = "";
				
				for (int j = 0; j < toPrint.get(i).size(); j++) {
					printThisLine += toPrint.get(i).get(j).data;
				}

				pw.println(printThisLine);
				
			}
			
			pw.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Joshua Whisenant & Noah Beverly
	 * A Graph is a rectangular collection of Nodes. Designed specifically for solving mazes
	 */
	private static class Graph {
		/*
		 * the inside ArrayLists are columns - 
		 * so the position of the Node in the inside ArrayList is the 'x' value
		 * the outside ArrayList controls height -
		 * so the position of the inside ArrayLists inside the outside ArrayList is the 'y' value.
		 */
		private ArrayList<ArrayList<Node>> data = new ArrayList<ArrayList<Node>>();

		public int height;
		public int width;
		private int lastColumn;
		private int lastRow;

		public Graph(ArrayList<ArrayList<Node>> data_) {
			
			for (int i = 0; i < data_.size(); i++) {
				ArrayList<Node> temp = new ArrayList<Node>();
				for (int j = 0; j < data_.get(i).size(); j++) {
					temp.add(data_.get(i).get(j));
				}
				this.data.add(temp);
			}

			height = data.size();
			width = data.get(0).size();
			lastColumn = width - 1;
			lastRow = height - 1;
		}

		public Node get (CoordinatePair c) {
			if (c.y > data.size() && c.x > data.get(c.y).size()) return null;
			return data.get(c.y).get(c.x);
		}

		/**
		 * @param x the x-position of the node to get (left is zero)
		 * @param y the y-position of the node to get (top is zero)
		 * @return the node sought
		 */
		private Node getInternal (int x, int y) {
			if (y > data.size() && x > data.get(y).size()) return null;
			return data.get(y).get(x);
		}

		/**
		 * @param x the x-position of the node to get to the right of (left is zero)
		 * @param y the y-position of the node to get (top is zero)
		 * @return the node sought
		 */
		public Node getRight (CoordinatePair c) {
			return getInternal(c.x+1,c.y);
		}

		/**
		 * @param x the x-position of the node to get to the left of (left is zero)
		 * @param y the y-position of the node to get (top is zero)
		 * @return the node sought
		 */
		public Node getLeft (CoordinatePair c) {
			return getInternal(c.x-1,c.y);
		}

		/**
		 * @param x the x-position of the node to get (left is zero)
		 * @param y the y-position of the node to get to the above (top is zero)
		 * @return the node sought
		 */
		public Node getAbove (CoordinatePair c) {
			return getInternal(c.x,c.y-1);
		}

		/**
		 * @param x the x-position of the node to get (left is zero)
		 * @param y the y-position of the node to get to the below (top is zero)
		 * @return the node sought
		 */
		public Node getBelow (CoordinatePair c) {
			return getInternal(c.x,c.y+1);
		}

		/**
		 * @param x the x-position of the node to get (left is zero)
		 * @param y the y-position of the node to get (top is zero)
		 * @return true if a node is at this position, or else false
		 */
		public boolean hasCurrent (CoordinatePair c) {
			if (c.y <= lastRow && c.y >= 0 && c.x <= lastColumn && c.x >= 0) return true;
			return false;
		}

		private boolean hasCurrentInternal (int x, int y) {
			if (y <= lastRow && y >= 0 && x <= lastColumn && x >= 0) return true;
			return false;
		}

		/**
		 * @param x the x-position of the node to get to the left of (left is zero)
		 * @param y the y-position of the node to get (top is zero)
		 * @return true if a node is to the right of this position, or else false
		 */
		public boolean hasRight (CoordinatePair c) {
			return hasCurrentInternal(c.x+1,c.y);
		}

		/**
		 * @param x the x-position of the node to get to the left of (left is zero)
		 * @param y the y-position of the node to get (top is zero)
		 * @return true if a node is to the left of this position, or else false
		 */
		public boolean hasLeft (CoordinatePair c) {
			return hasCurrentInternal(c.x-1,c.y);
		}

		/**
		 * @param x the x-position of the node to get (left is zero)
		 * @param y the y-position of the node to get the below of (top is zero)
		 * @return true if a node is below this position, or else false
		 */
		public boolean hasBelow (CoordinatePair c) {
			return hasCurrentInternal(c.x,c.y+1);
		}

		/**
		 * @param x the x-position of the node to get (left is zero)
		 * @param y the y-position of the node to get to above of (top is zero)
		 * @return true if a node is above this position, or else false
		 */
		public boolean hasAbove (CoordinatePair c) {
			return hasCurrentInternal(c.x,c.y-1);
		}

		public ArrayList<ArrayList<Node>> toArray() {
			return data;
		}
	}

	/**
	 * @author Joshua Whisenant & Noah Beverly
	 * A Node is an item that holds a single maze element.
	 * 'visited' and 'cameFrom' are used by the BFS algorithm and tracing back the path, respectively
	 */
	private static class Node {
		
		public Node () {
			
		}
		public boolean visited;
		public char data;
		public Node cameFrom;
	}

	/**
	 * @author Joshua Whisenant & Noah Beverly
	 * A CoordinatePair is simply two integers - an x and y location. 
	 * Designed to function with the graph class to simplify operations. 
	 */
	private static class CoordinatePair {
		int x;
		int y;

		public CoordinatePair(int x_, int y_) {
			x = x_;
			y = y_;
		}
	}

	/**
	 * @author Joshua Whisenant & Noah Beverly
	 * @param <T>
	 * A queue data structure that is used in BFS. Utilizes a doubly-linked list for data storage. 
	 */
	private static class Queue <T> {

		LinkedList<T> data = new LinkedList<T>();

		public Queue () {

		}

		public T dequeue () {
			return data.removeLast();
		}

		public void enqueue (T item) {
			data.addFirst(item);
		}

		public T poll () {
			return data.getLast();
		}

		public int size() {
			return data.size();
		}

		public boolean isEmpty() {
			return data.isEmpty();
		}

	}
}