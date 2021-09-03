package lms.co.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.hateoas.ResourceSupport;
public class Book extends ResourceSupport{

    private String bookid;

    @NotBlank
    private String title;

    private String author;
    
    private String category;
    private String status;
    private String borrower;
    private String borrowDate;
    private String returnDate;
    
    public Book() {
    }

	public Book(String bookid, String title, String author,
			String category, String status, String borrower,
			String borrowDate, String returnDate) {
        this.bookid = bookid;
        this.title = title;
        this.author =author;
        this.category = category;
        this.status = status;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getBookId() {
        return bookid;
    }

    public void setBookId(String bookid) {
        this.bookid = bookid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getCategory() {
		return category;
	}

	public void setCategory(String publisher) {
		this.category = publisher;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

    @Override
    public String toString() {
        return "Book{" + "bookid='" + bookid + '\'' + ", title='" + title + '\'' + '}';
    }
}
