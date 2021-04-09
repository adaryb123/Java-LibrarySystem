package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *     There can be more copies of the same book title in the libary.
 *     But reviews must be assigned to the title, not individual copies.
 */
public class BookTitle implements Serializable {
    private String authorName;
    private String bookName;
    private String publisherName;
    private int yearPublished;
    private ArrayList<Review> reviews;

    public BookTitle(String authorName, String bookName,
                     String publisherName, int yearPublished, ArrayList<Review> reviews) {
        this.authorName = authorName;
        this.bookName = bookName;
        this.publisherName = publisherName;
        this.yearPublished = yearPublished;
        this.reviews = reviews;
    }

    public BookTitle(String authorName, String bookName, String publisherName, int yearPublished) {
        this.authorName = authorName;
        this.bookName = bookName;
        this.publisherName = publisherName;
        this.yearPublished = yearPublished;
        this.reviews = new ArrayList<>();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
}
