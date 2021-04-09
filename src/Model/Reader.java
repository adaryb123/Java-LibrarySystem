package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
    Secondary user of the app.
    Customer of the library, can reserve, borrow and retrieve books, and write reviews for them.
 */
public class Reader implements Serializable {
    private ReadersCard readersCard;
    private ArrayList<BookCopy>  borrowedBooks;
    private ArrayList<BookCopy>  reservedBooks;

    public Reader(ReadersCard readersCard, ArrayList<BookCopy> borrowedBooks, ArrayList<BookCopy> reservedBooks) {
        this.readersCard = readersCard;
        this.borrowedBooks = borrowedBooks;
        this.reservedBooks = reservedBooks;
    }

    public Reader(ReadersCard readersCard) {
        this.readersCard = readersCard;
        this.borrowedBooks = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
    }

    public ReadersCard getReadersCard() {
        return readersCard;
    }

    public void setReadersCard(ReadersCard readersCard) {
        this.readersCard = readersCard;
    }

    public ArrayList<BookCopy> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<BookCopy> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public ArrayList<BookCopy> getReservedBooks() {
        return reservedBooks;
    }

    public void setReservedBooks(ArrayList<BookCopy> reservedBooks) {
        this.reservedBooks = reservedBooks;
    }
}
