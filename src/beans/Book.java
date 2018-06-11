package beans;

public class Book {
	
	private String Book_ID;
	private String Book_Name;
	private String Book_Author;
	private String Book_Size;
	private String Book_Path;
	private String Book_Tip;
	
	public Book() {}
	
	public Book(String Book_ID, String Book_Name, String Book_Author, 
				String Book_Size, String Book_Path, String Book_Tip) {
		super();
		this.Book_ID = Book_ID;
		this.Book_Name = Book_Name;
		this.Book_Author = Book_Author;
		this.Book_Size = Book_Size;
		this.Book_Path = Book_Path;
		this.Book_Tip = Book_Tip;
	}

	public String getBook_ID() {
		return Book_ID;
	}

	public void setBook_ID(String book_ID) {
		Book_ID = book_ID;
	}

	public String getBook_Name() {
		return Book_Name;
	}

	public void setBook_Name(String book_Name) {
		Book_Name = book_Name;
	}

	public String getBook_Author() {
		return Book_Author;
	}

	public void setBook_Author(String book_Author) {
		Book_Author = book_Author;
	}

	public String getBook_Size() {
		return Book_Size;
	}

	public void setBook_Size(String book_Size) {
		Book_Size = book_Size;
	}

	public String getBook_Path() {
		return Book_Path;
	}

	public void setBook_Path(String book_Path) {
		Book_Path = book_Path;
	}

	public String getBook_Tip() {
		return Book_Tip;
	}

	public void setBook_Tip(String book_Tip) {
		Book_Tip = book_Tip;
	}
	
	
}
