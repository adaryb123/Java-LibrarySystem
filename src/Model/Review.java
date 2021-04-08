package Model;

import java.io.Serializable;

/**
 *  A text written by user about the book he borrowed, after he retrieved it
 */
public class Review implements Serializable {
    private String date;
    private String reviewText;
    private int starsNum;
    private BookTitle book;
    private Reader reader;

    public Review(String date, String reviewText, int starsNum, BookTitle book, Reader reader) {
        this.date = date;
        this.reviewText = reviewText;
        this.starsNum = starsNum;
        this.book = book;
        this.reader = reader;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getStarsNum() {
        return starsNum;
    }

    public void setStarsNum(int starsNum) {
        this.starsNum = starsNum;
    }

    public BookTitle getBook() {
        return book;
    }

    public void setBook(BookTitle book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
