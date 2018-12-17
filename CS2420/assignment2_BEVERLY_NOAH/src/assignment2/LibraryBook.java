package assignment2;

import java.util.GregorianCalendar;
/**
 * 
 * @author noahbeverly (Partner Bailed: See Piazza Post)
 *
 */
public class LibraryBook extends Book {

	private String bookHolder;
	private GregorianCalendar dueDate;
	private boolean isCheckedIn;

	public LibraryBook(long _isbn, String _author, String _title) {
		super(_isbn, _author, _title);
		isCheckedIn = true;
		if (isCheckedIn == true) {
			dueDate = null;
			bookHolder = null;
		}
	}

	/**
	 * Method Getter to publicly expose bookHolder String
	 * 
	 * @return Returns the bookHolder String Value
	 */
	public String getHolder() {
		return bookHolder;
	}

	/**
	 * Method Getter to publicly expose dueDate Calendar Value
	 * 
	 * @return Returns the dueDate in a gregorian calendar value
	 */
	public GregorianCalendar getDueDate() {
		return dueDate;
	}

	/**
	 * Method Getter to publicly expose isCheckedIn Boolean.
	 * 
	 * @return Boolean value isCheckedIn.
	 */
	public boolean getIsCheckedIn() {
		return isCheckedIn;
	}

	/**
	 * Method to set private variables to null and change boolean isCheckedIn to
	 * true when a book is checked in.
	 */
	public void checkIn() {
		bookHolder = null;
		dueDate = null;
		isCheckedIn = true;
	}

	/**
	 * Method to update private variables when a book is checked out.
	 * 
	 * @param holder Name of the person checking out the book.
	 * @param year   Year of the due date
	 * @param month  Month of the due date
	 * @param day    Day of the due date.
	 */
	public void checkOut(String holder, int year, int month, int day) {
		bookHolder = holder;
		dueDate = new GregorianCalendar(year, month, day);
		isCheckedIn = false;
	}
}
