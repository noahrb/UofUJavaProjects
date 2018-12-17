package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Comparator;

/**
 * Class representation of a library (a collection of library books).
 * @author noahbeverly (Partner Bailed: See Piazza Post)
 */
public class LibraryGeneric<Type> {

	private ArrayList<LibraryBookGeneric<Type>> library;

	public LibraryGeneric() {
		library = new ArrayList<LibraryBookGeneric<Type>>();
	}

	/**
	 * Add the specified book to the library, assume no duplicates.
	 * 
	 * @param isbn   -- ISBN of the book to be added
	 * @param author -- author of the book to be added
	 * @param title  -- title of the book to be added
	 */
	public void add(long isbn, String author, String title) {
		library.add(new LibraryBookGeneric<Type>(isbn, author, title));
	}

	/**
	 * Add the list of library books to the library, assume no duplicates.
	 * 
	 * @param list -- list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBookGeneric<Type>> list) {
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
		ArrayList<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<LibraryBookGeneric<Type>>();

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

				toBeAdded.add(new LibraryBookGeneric<Type>(isbn, author, title));

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
	 * 
	 * @param isbn -- ISBN of the book to be looked up
	 */
	public Type lookup(long isbn) {
		for (LibraryBookGeneric<Type> book : library) {
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
	public ArrayList<LibraryBookGeneric<Type>> lookup(Type holder) {
		ArrayList<LibraryBookGeneric<Type>> returnList = new ArrayList<>();

		for (LibraryBookGeneric<Type> book : library) {
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
	public boolean checkout(long isbn, Type holder, int day, int month, int year) {
		// Returns false if the ISBN isn't in the library.
		boolean isInLibrary = false;
		for (LibraryBookGeneric<Type> book : library) {
			if (book.getIsbn() == isbn) {
				isInLibrary = true;
			}
		}
		if (isInLibrary == false) {
			return false;
		}

		// Returns false if the book exists in the library and is checked out
		for (LibraryBookGeneric<Type> book : library) {
			if (book.getIsbn() == isbn && book.getIsCheckedIn() == false) {
				return false;
			}
		}
		// Sets the holder and due date of the library book with the specified ISBN.
		for (LibraryBookGeneric<Type> book : library) {
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
		for (LibraryBookGeneric<Type> book : library) {
			if (book.getIsbn() == isbn && book.getIsCheckedIn()) {
				return false;
			}
		}

		// Unsets the holder and the due date of the library book
		for (LibraryBookGeneric<Type> book : library) {
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
	public boolean checkin(Type holder) {
		// If no books w/ holder are in the library, return false.
		boolean isInLibrary = false;
		for (LibraryBookGeneric<Type> book : library) {
			if (book.getHolder() == holder) {
				isInLibrary = true;
			}
		}
		if (isInLibrary == false) {
			return false;
		}

		// Unsets the holder and due date for all books with specified holder.
		for (LibraryBookGeneric<Type> book : library) {
			if (book.getHolder() == holder) {
				book.checkIn();
			}
		}
		// Otherwise returns true
		return true;
	}

	/**
	 * Returns the list of library books, as they are, unsorted
	 */
	public ArrayList<LibraryBookGeneric<Type>> getRawBooks() {
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		libraryCopy.addAll(library);

		return libraryCopy;
	}

	/**
	 * Returns the list of library books, sorted by ISBN (smallest ISBN first).
	 */
	public ArrayList<LibraryBookGeneric<Type>> getInventoryList() {
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		libraryCopy.addAll(library);

		OrderByIsbn comparator = new OrderByIsbn();

		sort(libraryCopy, comparator);

		return libraryCopy;
	}

	/**
	 * Returns the list of library books, sorted by author
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOrderedByAuthor() {
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		libraryCopy.addAll(library);

		OrderByAuthor comparator = new OrderByAuthor();
		//Calls sort using our custom comparator to Order values by Author String first, then Title. 
		sort(libraryCopy, comparator);

		return libraryCopy;
	}

	/**
	 * Returns the list of library books whose due date is older than the input
	 * date. The list is sorted by date (oldest first).
	 *
	 * If no library books are overdue, returns an empty list.
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day, int year) {
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		libraryCopy.addAll(library);
		
		ArrayList<LibraryBookGeneric<Type>> libraryOverdue = new ArrayList<LibraryBookGeneric<Type>>();
		//For each book in libraryCopy, check for overdue, if overdue, add to new libraryOverdue.
		GregorianCalendar cal = new GregorianCalendar(year, month, day);
		for(int i = 0; i < libraryCopy.size(); i++) {
			
			if((libraryCopy.get(i).getDueDate().compareTo(cal)) > 0) {
				libraryOverdue.add(libraryCopy.get(i));
			}
			
		OrderByDueDate comparator = new OrderByDueDate();
		//Uses our OrderByDueDate function to order values in the ArrayList by the DueDate. 
		//Year first, then month, then day. 
		sort(libraryOverdue, comparator);
		}
		
		return libraryOverdue;
	}

	/**
	 * Performs a SELECTION SORT on the input ArrayList. 1. Find the smallest item
	 * in the list. 2. Swap the smallest item with the first item in the list. 3.
	 * Now let the list be the remaining unsorted portion (second item to Nth item)
	 * and repeat steps 1, 2, and 3.
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++)
				if (c.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			ListType temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the ISBN.
	 */
	protected class OrderByIsbn implements Comparator<LibraryBookGeneric<Type>> {

		/**
		 * Returns a negative value if lhs is smaller than rhs. Returns a positive value
		 * if lhs is larger than rhs. Returns 0 if lhs and rhs are equal.
		 */
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			return (int) (lhs.getIsbn() - rhs.getIsbn());
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the author, and
	 * book title as a tie-breaker.
	 */
	protected class OrderByAuthor implements Comparator<LibraryBookGeneric<Type>> {
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			//If the name is the same, moves on to comparing via Title. 
			if (lhs.getAuthor().compareTo(rhs.getAuthor()) == 0) {
				return lhs.getTitle().compareTo(rhs.getTitle());
			} else {
				return (lhs.getAuthor().compareTo(rhs.getAuthor()));
			}
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the due date.
	 */
	protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>> {
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			//Creating values to hold the year, month and day of both inputs. 
			int lhsYear = lhs.getDueDate().get(Calendar.YEAR);
			int lhsMonth = lhs.getDueDate().get(Calendar.MONTH);
			int lhsDay = lhs.getDueDate().get(Calendar.DAY_OF_MONTH);

			int rhsYear = rhs.getDueDate().get(Calendar.YEAR);
			int rhsMonth = rhs.getDueDate().get(Calendar.MONTH);
			int rhsDay = rhs.getDueDate().get(Calendar.DAY_OF_MONTH);

			//Calling Integer.compare to compare the values. 
			//Year first, then month, then day. 
			if (lhsYear == rhsYear) {
				if (lhsMonth == rhsMonth) {
					return Integer.compare(lhsDay, rhsDay);
				} else {
					return Integer.compare(lhsMonth, rhsMonth);
				}
			} else {
				return Integer.compare(lhsYear, rhsYear);
			}
		}
	}
}
