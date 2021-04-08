package Model;

import java.io.Serializable;

/**
 *      There can be more copies of the same book title, each can be reserved, borrowed or available
 */
public class BookCopy implements Serializable {
    private BookTitle bookTitle;
    private int id;
    public enum Status{
        RESERVED,
        BORROWED,
        AVAILABLE
    }
    private Status status;

    public BookCopy(BookTitle bookTitle, int id, Status status) {
        this.bookTitle = bookTitle;
        this.id = id;            //id should be generated
        this.status = status;
    }

    public BookCopy(BookTitle bookTitle, int id) {
        this.bookTitle = bookTitle;
        this.id = id;            //id should be generated
        this.status = Status.AVAILABLE;
    }

    public BookTitle getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(BookTitle bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status isAvailable() {
        return status;
    }

    public void setAvailable(Status status) {
       this.status = status;
    }
}
