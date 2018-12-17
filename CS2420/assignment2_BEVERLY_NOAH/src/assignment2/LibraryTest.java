package assignment2;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Testing class for Library.
 * 
 * 
 */
public class LibraryTest {

	public static void main(String[] args) {
		// ***********Test Library Methods with an empty library *******************
		Library lib = new Library();

		if (lib.lookup(978037429279L) != null)
			System.err.println("TEST FAILED -- empty library: lookup(isbn)");
		ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
		if (booksCheckedOut == null || booksCheckedOut.size() != 0)
			System.err.println("TEST FAILED -- empty library: lookup(holder)");
		if (lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008))
			System.err.println("TEST FAILED -- empty library: checkout");
		if (lib.checkin(978037429279L))
			System.err.println("TEST FAILED -- empty library: checkin(isbn)");
		if (lib.checkin("Jane Doe"))
			System.err.println("TEST FAILED -- empty library: checkin(holder)");

		// ***********Test Library Methods with a small library *******************
		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");
		if (lib.lookup(9780330351690L) != null)
			System.err.println("TEST FAILED -- small library: lookup(isbn)");
		if (!lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008))
			System.err.println("TEST FAILED -- small library: checkout");
		booksCheckedOut = lib.lookup("Jane Doe");
		if (booksCheckedOut == null || booksCheckedOut.size() != 1
				|| !booksCheckedOut.get(0).equals(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
				|| !booksCheckedOut.get(0).getHolder().equals("Jane Doe")
				|| !booksCheckedOut.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
			System.err.println("TEST FAILED -- small library: lookup(holder)");
		if (!lib.checkin(9780330351690L))
			System.err.println("TEST FAILED -- small library: checkin(isbn)");
		if (lib.checkin("Jane Doe"))
			System.err.println("TEST FAILED -- small library: checkin(holder)");

		// ***********Test Library Methods with a medium library *******************
		lib = new Library();
		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");
		lib.addAll("Mushroom_Publishing.txt");

		// Testing lookup by ISBN
		if (lib.lookup(9781843192022L) != null)
			System.err.println("TEST FAILED -- medium library: lookup(isbn)");

		// Testing lookup by holder
		lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		lib.checkout(9781843193319L, "Jane Doe", 1, 1, 2008);
		lib.lookup(9781843193319L);
		booksCheckedOut = lib.lookup("Jane Doe");

		if (booksCheckedOut.get(0).getHolder() != "Jane Doe"
				|| !booksCheckedOut.get(0).equals(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
				|| !booksCheckedOut.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1))) {
			System.err.println("TEST FAILED -- medium library: lookup(holder)");
		}

		// Testing Checkout
		lib = new Library();
		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");
		lib.addAll("Mushroom_Publishing.txt");

		if (!lib.checkout(9781843190875L, "Bill", 2, 1, 2004) || !lib.checkout(9781843190936L, "Jim", 1, 4, 2018)
				|| !lib.checkout(9781843192701L, "Tom", 3, 5, 2019)) {
			System.err.println("TEST FAILED -- medium library: checkout");
		}

		// Testing Checkin
		lib.checkin(9781843190875L);
		lib.checkin(9781843190936L);
		lib.checkin(9781843192701L);
		if (lib.lookup("Bill").size() != 0 || lib.lookup("Jim").size() != 0 || lib.lookup("Tom").size() != 0) {
			System.err.println("TEST FAILED -- medium library: checkin by isbn");
		}
		
		//Testing Checkin by holder
		lib.checkout(9781843190875L, "Bill", 2, 1, 2004);
		lib.checkout(9781843190936L, "Jim", 1, 4, 2018);
		lib.checkout(9781843192701L, "Tom", 3, 5, 2019);
		lib.checkin("Bill");
		lib.checkin("Jim");
		lib.checkin("Tom");
		
		if (lib.lookup("Bill").size() != 0 || lib.lookup("Jim").size() != 0 || lib.lookup("Tom").size() != 0) {
			System.err.println("TEST FAILED -- medium library: checkin by holder");
		}
		//******************Testing Book Methods******************

		// Testing .equals() method - Success Case and Failure case.
		long isbn1 = generateIsbn();
		long isbn2 = generateIsbn();
		Book book1 = new Book(isbn1, "Bill", "Adventures of Bill");
		Book book2 = new Book(isbn1, "Bill", "Adventures of Bill");
		Book book3 = new Book(isbn2, "Bill", "Adventures of Bill");

		if (!book1.equals(book2))
			System.err.println("TEST FAILED -- Book Method - .equals() - Success Case");
		if (book1.equals(book3))
			System.err.println("TEST FAILED -- Book Method - .equals(); - Failure Case");

		System.out.println("Testing done.");

		//******************LibraryBook Methods*******************
		//Testing getHolder
		//Case if no holder
		LibraryBook a = new LibraryBook(9781843190875L, "Renee Angers", "Ice and a Curious Man");
		if(a.getHolder() != null) {
			System.err.println("TEST FAILED -- LibraryBook Method - .getHolder() - Null Holder Case");
		}
		
		//Case if holder
		a.checkOut("BillyBobJoe", 1999, 4, 2);
		if(a.getHolder() != "BillyBobJoe") {
			System.err.println("TEST FAILED -- LibraryBook Method - .getHolder() - Success Case");
		}
		
		//Testing getDueDate
		//Case if noDueDate
		LibraryBook b = new LibraryBook(9781843190875L, "Renee Angers", "Ice and a Curious Man");
		if(b.getDueDate() != null) {
			System.err.println("TEST FAILED -- LibraryBook Method - .getDueDate() - Null Holder Case");
		}
		
		//Case if holder
		b.checkOut("BillyBobJoe", 1999, 4, 2);
		if(b.getDueDate().compareTo(new GregorianCalendar(1999, 4, 2)) != 0) {
			System.err.println("TEST FAILED -- LibraryBook Method - .getDueDate() - Success Case");
		}
		
		//Testing Checkin
		lib = new Library();
		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");
		lib.addAll("Mushroom_Publishing.txt");
		

		lib.checkout(9781843190875L, "Bill", 2, 1, 2004);
		
		if(lib.checkin(9781843190875L) != true || lib.lookup(9781843190875L) != null) {
			System.err.println("TEST FAILED -- LibraryBook Method - .checkIn() - Success Case");
		}
		
		//Testing Checkout
		LibraryBook c = new LibraryBook(9781843190875L, "Renee Angers", "Ice and a Curious Man");
		
		c.checkOut("Billy", 2009, 9, 2);
		if(c.getDueDate().compareTo(new GregorianCalendar(2009, 9, 2)) != 0 || c.getHolder() != "Billy") {
			System.err.println("TEST FAILED -- LibraryBook Method - .checkOut() - Success Case");
		}
	}

	/**
	 * Returns a library of "dummy" books (random ISBN and placeholders for author
	 * and title).
	 * 
	 * Useful for collecting running times for operations on libraries of varying
	 * size.
	 * 
	 * @param size -- size of the library to be generated
	 */
	public static ArrayList<LibraryBook> generateLibrary(int size) {
		ArrayList<LibraryBook> result = new ArrayList<LibraryBook>();

		for (int i = 0; i < size; i++) {
			// generate random ISBN
			Random randomNumGen = new Random();
			String isbn = "";
			for (int j = 0; j < 13; j++)
				isbn += randomNumGen.nextInt(10);

			result.add(new LibraryBook(Long.parseLong(isbn), "An author", "A title"));
		}
		return result;
	}

	/**
	 * Returns a randomly-generated ISBN (a long with 13 digits).
	 * 
	 * Useful for collecting running times for operations on libraries of varying
	 * size.
	 */
	public static long generateIsbn() {
		Random randomNumGen = new Random();

		String isbn = "";
		for (int j = 0; j < 13; j++)
			isbn += randomNumGen.nextInt(10);

		return Long.parseLong(isbn);
	}
}
