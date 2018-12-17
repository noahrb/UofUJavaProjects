package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books).
 * @author noahbeverly (Partner Bailed: See Piazza Post)
 */
public class Library {

	private ArrayList<LibraryBook> library;

	public Library() {
		library = new ArrayList<LibraryBook>();
	}

	/**
	 * Add the specified book to the library, assume no duplicates.
	 * 
	 * @param isbn   -- ISBN of the book to be added
	 * @param author -- author of the book to be added
	 * @param title  -- title of the book to be added
	 */
	public void add(long isbn, String author, String title) {
		library.add(new LibraryBook(isbn, author, title));
	}

	/**
	 * Add the list of library books to the library, assume no duplicates.
	 * 
	 * @param list -- list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBook> list) {
		library.addAll(list);
	}

	/**
	 * Add books specified by the input file. One book per line with ISBN, author,
	 * and title separated by tabs.
	 * 
	 * If file does not exist or format is violated, do nothing.
	 * 
	 * @param filename
	 */
	@SuppressWarnings("resource")
	public void addAll(String filename) {
		ArrayList<LibraryBook> toBeAdded = new ArrayList<LibraryBook>();

		try {
			Scanner fileIn = new Scanner(new File(filename));
			int lineNum = 1;

			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();

				Scanner lineIn = new Scanner(line);
				lineIn.useDelimiter("\\t");

				if (!lineIn.hasNextLong())
					throw new ParseException("ISBN", lineNum);
				long isbn = lineIn.nextLong();

				if (!lineIn.hasNext())
					throw new ParseException("Author", lineNum);
				String author = lineIn.next();

				if (!lineIn.hasNext())
					throw new ParseException("Title", lineNum);
				String title = lineIn.next();

				toBeAdded.add(new LibraryBook(isbn, author, title));

				lineNum++;
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return;
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Nothing added to the library.");
			return;
		}

		library.addAll(toBeAdded);
	}

	/**
	 * Returns the holder of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns null.
	 * Returns null if the book is not checked out but exists in the library.
	 * @param isbn -- ISBN of the book to be looked up
	 */
	public String lookup(long isbn) {
		//Loops through each book in the library, and if the ISBN matches the one provided, returns the books holder. 
		for (LibraryBook book : library) {
			if (book.getIsbn() == isbn) {
				return book.getHolder();
			}
		}
		return null;
	}

	/**
	 * Returns the list of library books checked out to the specified holder.
	 * 
	 * If the specified holder has no books checked out, returns an empty list.
	 * 
	 * @param holder -- holder whose checked out books are returned
	 */
	public ArrayList<LibraryBook> lookup(String holder) {
		ArrayList<LibraryBook> returnList = new ArrayList<>();

		//Loops through books, if the holder matches input, adds to a list to return all the books they hold. 
		for (LibraryBook book : library) {
			if (book.getHolder() == holder) {
				returnList.add(book);
			}
		}
		return returnList;
	}

	/**
	 * Sets the holder and due date of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked out, returns false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn   -- ISBN of the library book to be checked out
	 * @param holder -- new holder of the library book
	 * @param month  -- month of the new due date of the library book
	 * @param day    -- day of the new due date of the library book
	 * @param year   -- year of the new due date of the library book
	 * 
	 */
	public boolean checkout(long isbn, String holder, int month, int day, int year) {
		// Returns false if the ISBN isn't in the library.
		boolean isInLibrary = false;
		for (LibraryBook book : library) {
			if (book.getIsbn() == isbn) {
				isInLibrary = true;
			}
		}
		if (isInLibrary == false) {
			return false;
		}
		
		// Returns false if the book exists in the library and is checked out
		for (LibraryBook book : library) {
			if (book.getIsbn() == isbn && book.getIsCheckedIn() == false) {
				return false;
			}
		}
		// Sets the holder and due date of the library book with the specified ISBN.
		for (LibraryBook book : library) {
			if (book.getIsbn() == isbn) {
				// Calls LibraryBook checkout helper method to update values.
				book.checkOut(holder, year, month, day);
				
			}
		}
		// Returns true.
		return true;
	}

	/**
	 * Unsets the holder and due date of the library book.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked in, returns false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn -- ISBN of the library book to be checked in
	 */
	public boolean checkin(long isbn) {
		// Returns false if the ISBN isn't in the library.
		if (this.lookup(isbn) == null) {
			return false;
		}

		// Returns false if the book is already checked in
		for (LibraryBook book : library) {
			if (book.getIsbn() == isbn && book.getIsCheckedIn()) {
				return false;
			}
		}

		// Unsets the holder and the due date of the library book
		for (LibraryBook book : library) {
			if (book.getIsbn() == isbn) {
				book.checkIn();
			}
		}
		// Returns true;
		return true;
	}

	/**
	 * Un-sets the holder and due date for all library books checked out be the
	 * specified holder.
	 * 
	 * If no books with the specified holder are in the library, returns false;
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param holder -- holder of the library books to be checked in
	 */
	public boolean checkin(String holder) {
		// If no books w/ holder are in the library, return false.
		boolean isInLibrary = false;
		for (LibraryBook book : library) {
			if (book.getHolder() == holder) {
				isInLibrary = true;
			}
		}
		if (isInLibrary == false) {
			return false;
		}

		// Unsets the holder and due date for all books with specified holder.
		for (LibraryBook book : library) {
			if (book.getHolder() == holder) {
				book.checkIn();
			}
		}
		// Otherwise returns true
		return true;
	}
}
