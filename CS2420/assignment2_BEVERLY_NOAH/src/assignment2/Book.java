package assignment2;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * 
 * Note that ISBNs are unique.
 *
 */
public class Book {

	private long isbn;

	private String author;

	private String title;

	public Book(long _isbn, String _author, String _title) {
		this.isbn = _isbn;
		this.author = _author;
		this.title = _title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * @return the ISBN
	 */
	public long getIsbn() {
		return this.isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Two books are considered equal if they have the same ISBN, author, and title.
	 * 
	 * @param other -- the object begin compared with "this"
	 * @return true if "other" is a Book and is equal to "this", false otherwise
	 */
	public boolean equals(Object other) {
		//Checks that input is an instance of a book object.
		if (other instanceof Book) {
			Book book2 = (Book) other;
			boolean isEqual = true;
			//Checking for the same ISBN, author and title. 
			if (this.isbn != book2.isbn) {
				isEqual = false;
			} else if (this.author != book2.author) {
				isEqual = false;
			} else if (this.title != book2.title) {
				isEqual = false;
			}
			return isEqual;
		} else {
			return false;
		}
	}

	/**
	 * Returns a string representation of the book.
	 */
	public String toString() {
		return isbn + ", " + author + ", \"" + title + "\"";
	}
}
