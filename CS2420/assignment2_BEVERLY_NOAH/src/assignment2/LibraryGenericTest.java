package assignment2;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Testing class for LibraryGeneric.
 *
 */
public class LibraryGenericTest {

	public static void main(String[] args) {

		// test a library that uses names (String) to id patrons
		LibraryGeneric<String> lib1 = new LibraryGeneric<String>();
		lib1.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib1.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib1.add(9780446580342L, "David Baldacci", "Simple Genius");

		String patron1 = "Jane Doe";

		if (!lib1.checkout(9780330351690L, patron1, 1, 1, 2008))
			System.err.println("TEST FAILED: first checkout");
		if (!lib1.checkout(9780374292799L, patron1, 1, 1, 2008))
			System.err.println("TEST FAILED: second checkout");
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut1 = lib1.lookup(patron1);
		if (booksCheckedOut1 == null || booksCheckedOut1.size() != 2
				|| !booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
				|| !booksCheckedOut1.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat"))
				|| !booksCheckedOut1.get(0).getHolder().equals(patron1)
				|| !booksCheckedOut1.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1))
				|| !booksCheckedOut1.get(1).getHolder().equals(patron1)
				|| !booksCheckedOut1.get(1).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
			System.err.println("TEST FAILED: lookup(holder)");
		if (!lib1.checkin(patron1))
			System.err.println("TEST FAILED: checkin(holder)");

		// test a library that uses phone numbers (PhoneNumber) to id patrons
		LibraryGeneric<PhoneNumber> lib2 = new LibraryGeneric<PhoneNumber>();
		lib2.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib2.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib2.add(9780446580342L, "David Baldacci", "Simple Genius");

		PhoneNumber patron2 = new PhoneNumber("801.555.1234");

		if (!lib2.checkout(9780330351690L, patron2, 1, 1, 2008))
			System.err.println("TEST FAILED: first checkout");
		if (!lib2.checkout(9780374292799L, patron2, 1, 1, 2008))
			System.err.println("TEST FAILED: second checkout");
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib2.lookup(patron2);
		if (booksCheckedOut2 == null || booksCheckedOut2.size() != 2
				|| !booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
				|| !booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat"))
				|| !booksCheckedOut2.get(0).getHolder().equals(patron2)
				|| !booksCheckedOut2.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1))
				|| !booksCheckedOut2.get(1).getHolder().equals(patron2)
				|| !booksCheckedOut2.get(1).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
			System.err.println("TEST FAILED: lookup(holder)");
		if (!lib2.checkin(patron2))
			System.err.println("TEST FAILED: checkin(holder)");

		//*********************Testing My Methods*********************
		//Testing getOrderedByAuthor
		LibraryGeneric<String> lib3 = new LibraryGeneric<String>();
		lib3.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib3.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib3.add(9780446580342L, "David Baldacci", "Simple Genius");
		
		LibraryGeneric<String> sorted1 = new LibraryGeneric<String>();
		sorted1.add(9780446580342L, "David Baldacci", "Simple Genius");
		sorted1.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		sorted1.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		
		if(!lib3.getOrderedByAuthor().equals(sorted1.getRawBooks()))
			System.err.println("TEST FAILED: Generic getOrderedByAuthor");
		
		//Testing getOverdueList
		LibraryGeneric<String> lib4 = new LibraryGeneric<String>();
		lib4.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib4.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib4.add(9780446580342L, "David Baldacci", "Simple Genius");
	
		lib4.checkout(9780374292799L, "Jimmy", 1, 2, 2008);
		lib4.checkout(9780330351690L, "Sam", 1, 3, 2007);
		lib4.checkout(9780446580342L, "Patrick", 2, 4, 2019);
		
		LibraryGeneric<String> sorted2 = new LibraryGeneric<String>();
		sorted2.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		sorted2.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		sorted2.add(9780446580342L, "David Baldacci", "Simple Genius");
		if(!lib4.getOverdueList(2, 3, 2004).equals(sorted2.getRawBooks())) {
			System.err.println("TEST FAILED: Generic getOrderedByDueDate: Different Year");
		}
		
		//Testing if year and month are the same.
		LibraryGeneric<String> lib5 = new LibraryGeneric<String>();
		lib5.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib5.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib5.add(9780446580342L, "David Baldacci", "Simple Genius");
	
		lib5.checkout(9780374292799L, "Jimmy", 2, 2, 2007);
		lib5.checkout(9780330351690L, "Sam", 1, 2, 2007);
		lib5.checkout(9780446580342L, "Patrick", 3, 2, 2007);
		
		LibraryGeneric<String> sorted3 = new LibraryGeneric<String>();
		sorted3.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		sorted3.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		sorted3.add(9780446580342L, "David Baldacci", "Simple Genius");
		if(!lib5.getOverdueList(2, 3, 2004).equals(sorted3.getRawBooks())) {
			System.err.println("TEST FAILED: Generic getOrderedByDueDate: Same Year, Same Month, Different Day");
		}
		
		System.out.println("Testing done.");
	}
}
