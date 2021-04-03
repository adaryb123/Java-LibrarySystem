package Model;

import java.util.ArrayList;

/**
 *    Record: Which user , borrowed which books, when.
 *    When the books are retrieved, this record will be deleted?
 */
public class BorrowingRecord {
    private String date;
    private Reader reader;
    private ArrayList<BookCopy> books;

    public BorrowingRecord(String date, Reader reader, ArrayList<BookCopy> books) {
        this.date = date;
        this.reader = reader;
        this.books = books;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public ArrayList<BookCopy> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<BookCopy> books) {
        this.books = books;
    }
}
