package assignment7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author ??
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 * @throws FileNotFoundException 
	 */
	public static String checkFile(String filename) throws FileNotFoundException {

		MyStack<Character> stack = new MyStack<Character>();
		Scanner s = new Scanner(new File(filename));
		ArrayList<String> lines = new ArrayList<String>();
		while (s.hasNextLine()) {
			lines.add(s.nextLine());
		}
		s.close();

		char thisChar = 0;
		char previousChar = 0;
		char nextChar = 0;
		boolean gotNextChar = false;

		boolean inCommentDoubleSlash = false;
		boolean inCommentSlashStar = false;
		boolean inSingleQuotes = false;
		boolean inDoubleQuotes = false;

		boolean terminate = false;


		for (int i = 0; i < lines.size(); i++) {

			inCommentDoubleSlash = false; // double-slash comments only apply to one line - turn off every time
			if (terminate) break;

			String thisLine = lines.get(i);

			for (int j = 0; j < thisLine.length(); j++) {

				// ************* STEP ONE: GET THE CURRENT, PREVIOUS, AND FOLLOWING CHARACTERS *****************				

				if (lineHasThisChar(i,j,lines)) {
					thisChar = getThisChar(i,j,lines);
				} 

				if (lineHasPreviousChar(i,j,lines)) {
					previousChar = getPreviousChar(i,j,lines);
				} 

				if (lineHasNextChar(i,j,lines)) {
					nextChar = getNextChar(i,j,lines);
					gotNextChar = true;
				} else {
					gotNextChar = false;
				}

				//************************ STEP 2: TOGGLE COMMENT AND QUOTE BOOLEANS **************************

				// this turns inCommentDoubleSlash on if stepping into a double-slash comment
				if (!inCommentDoubleSlash // if not already in a double slash comment
						&& !inCommentSlashStar // and not already in a /* comment
						&& previousChar != '\\' // and the previous character is not an escape character
						&& thisChar == '/' // and this character is a comment character
						&& gotNextChar && nextChar == '/') { // and the next character is also a comment character
					inCommentDoubleSlash = true; 
				}

				// this toggles inCommentSlashStar on and off if stepping into or out of an extended comment
				if (!inCommentDoubleSlash // if not already in a double slash comment
						&& !inCommentSlashStar // and not already in a /* comment
						&& previousChar != '\\' // and the previous character is not an escape character
						&& thisChar == '/' // and this character is a comment character
						&& gotNextChar && nextChar == '*') { // and the next character is also a comment character
					inCommentSlashStar = true;
				} else if (inCommentSlashStar
						&& previousChar != '\\' // and the previous character is not an escape character
						&& thisChar == '*' // and this character is a comment character
						&& gotNextChar && nextChar == '/') { // and the next character is also a comment character
					inCommentSlashStar = false;
				}

				// this toggles inSingleQuotes
				if (!inCommentSlashStar // not in any comments
						&& !inCommentDoubleSlash
						&& !inSingleQuotes // not already in single quotes
						&& previousChar != '\\' // no escape characters
						&& thisChar == '\'') {  // this character is the single quote
					inSingleQuotes = true;
				} else if (!inCommentSlashStar // not in any comments
						&& !inCommentDoubleSlash
						&& inSingleQuotes // already in single quotes
						&& previousChar != '\\' // no escape characters
						&& thisChar == '\'') {  // this character is the single quote
					inSingleQuotes = false;
				}

				// this toggles inDoubleQuotes
				if (!inCommentSlashStar // not in any comments
						&& !inCommentDoubleSlash
						&& !inDoubleQuotes // not already in single quotes
						&& previousChar != '\\' // no escape characters
						&& thisChar == '\"') {  // this character is the single quote
					inDoubleQuotes = true;
				} else if (!inCommentSlashStar // not in any comments
						&& !inCommentDoubleSlash
						&& inDoubleQuotes // already in single quotes
						&& previousChar != '\\' // no escape characters
						&& thisChar == '\"') {  // this character is the single quote
					inDoubleQuotes = false;
				}

				// ******************** STEP 3: PERFORM STACK BASED SYMBOL MATCHING TESTS *************************

				/*
				 * the big outside if ensures that operations are only performed outside of comments and '' and "", 
				 * and ensures there are no escape keys
				 */

				if (!inCommentDoubleSlash 
						&& !inCommentSlashStar
						&& !inSingleQuotes
						&& !inDoubleQuotes
						&& previousChar != '\\') { 

					if (isOpenerChar(thisChar)) { // if opening character, push

						stack.push(thisChar);

					} else if (isCloserChar(thisChar)) { // if closing character, compare

						char poppedChar = 'a';
						if (stack.size() !=0) {
							poppedChar = stack.pop(); 
						} else {
							poppedChar = ' '; // if the stack size is 0, then the poppedChar must be a blank space (per specified output reqs)
						}
						/*
						 * this bit is nuanced. We want to compare an closing char to a closing char. 
						 * If this block of code is accessed, thisChar is already a closer.
						 * But the poppedChar is an opener. The getReciprocal method turns the openers into closers
						 */

						char correspondingChar = getReciprocal(poppedChar); 

						/*
						 * Compares the reciprocal of the poppedChar to the char at i,j
						 * If they are not the same, then prints out an error message and terminates.
						 */

						if (thisChar != correspondingChar) {
							return unmatchedSymbol(i+1, j+1, thisChar, correspondingChar);
						}
					}
				}
			}


		}
		
		if (inCommentSlashStar) {
			return unfinishedComment();
		} else if (stack.size() != 0) {
			return unmatchedSymbolAtEOF(getReciprocal(stack.pop())); // again, stack.pop returns an opening char, and we want a closer. 
		} else {
			return allSymbolsMatch();
		}


	}

	private static boolean isOpenerChar (char c) {
		if (c == '{' || c == '(' || c == '[') return true;
		return false;
	}

	private static boolean isCloserChar (char c) {
		if (c == '}' || c == ')' || c == ']') return true;
		return false;
	}

	private static boolean lineHasThisChar (int i, int j, ArrayList<String> lines) {
		if (i > lines.size() -1) return false;

		String thisLine = lines.get(i);
		if (j<thisLine.length()) return true;
		if (i<lines.size() -1) return true;
		return false;
	}

	private static char getReciprocal (char c) {
		if (c == '{') return '}';
		if (c == '(') return ')';
		if (c == '[') return ']';
		if (c == ']') return '[';
		if (c == ')') return '(';
		if (c == '}') return '{';
		if (c == ' ') return ' ';
		return 'a';
	}

	private static boolean lineHasPreviousChar (int i, int j, ArrayList<String> lines) {
		if (i > lines.size() -1) return false; // redundant check for out of bounds
		if (lines.size() == 0) return false; // check for an empty file
		if (j == 0) return false; // check for at the beginning of the line
		return true;
	}

	private static boolean lineHasNextChar (int i, int j, ArrayList<String> lines) {
		if (i > lines.size() -1) return false; // redundant check for out of bounds
		if (lines.size() == 0) return false; // check for an empty file
		if (j == lines.get(i).length() -1) return false; //return false if at the end of the line
		return true;
	}

	private static char getThisChar(int i, int j, ArrayList<String> lines) {
		return lines.get(i).charAt(j);
	}

	private static char getPreviousChar (int i, int j, ArrayList<String> lines) {

		return lines.get(i).charAt(j-1);

	}

	private static char getNextChar(int i, int j, ArrayList<String> lines) {

		String thisLine = lines.get(i);

		return thisLine.charAt(j+1);
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private static String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected " + symbolExpected
				+ ", but read " + symbolRead + " instead.";
	}

	/**
	 * Returns an error message for unmatched symbol at the end of file.
	 * Indicates the symbol match that was expected.
	 */
	private static String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	}

	/**
	 * Returns an error message for a file that ends with an open /* comment.
	 */
	private static String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Returns a message for a file in which all symbols match.
	 */
	private static String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}

}


