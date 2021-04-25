package Controller.Librarian;

import Controller.SceneManager;
import Model.BookCopy;
import Model.BorrowingRecord;
import Model.ReadersCard;
import PopUps.PopUps;
import Serialization.SerializationPattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DetailBorrowingController extends ChooseBooksBorrowingController implements Initializable {

    @FXML
    private Label nameLabel, addressLabel, idLabel, phoneLabel, emailLabel, dateLabel;
    @FXML
    private TableView<BookCopy> borrowedBooksTableView;
    @FXML
    private TableColumn<BookCopy, String> bookIdCol, authorCol, bookNameCol, publisherCol, bookStateCol;

    private ObservableList<BookCopy> borrowedBooks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // if scene contains TableView with id borrowedBooksTableView
        // then map columns to attributes and methods
        // after that display all borrowed books in it
        if (borrowedBooksTableView != null) {
            // get all borrowed books for this borrowing record and create observable list from their arraylist
            borrowedBooks = FXCollections.observableArrayList(SceneManager.selectedBorrowingRecord.getBooks());
            // map columns in borrowedBooksTableView on methods in BookCopy class
            this.mapColumnsInTableView(bookIdCol, authorCol, bookNameCol, publisherCol, bookStateCol);
            // display borrowed books in tableview
            borrowedBooksTableView.setItems(borrowedBooks);
        }

        // if scene contains labels with these ids display borrowing record data in it
        if (nameLabel != null && addressLabel != null && idLabel != null &&
            phoneLabel != null && emailLabel != null && dateLabel != null) {
            // get reader's card of borrowing record
            ReadersCard readersCard = SceneManager.selectedBorrowingRecord.getReader().getReadersCard();
            // display reader's card data in labels
            nameLabel.setText(readersCard.getReaderName());
            addressLabel.setText(readersCard.getReaderAddress());
            idLabel.setText(String.valueOf(readersCard.getId()));
            phoneLabel.setText(readersCard.getReaderPhoneNumber());
            emailLabel.setText(readersCard.getReaderEmail());
            // display date of borrowing in label
            dateLabel.setText(SceneManager.selectedBorrowingRecord.getDate());
        }
    }

    public void returnBook() {
        // check if user selected book, which should be returned
        if (borrowedBooksTableView.getSelectionModel().getSelectedItem() == null) {
            PopUps.showErrorPopUp("Error", "You have to select book, which is returned.");
        }
        // if user selected book, then add it to available books
        BookCopy returnBook = borrowedBooksTableView.getSelectionModel().getSelectedItem();
        BorrowingRecord borrowingRecord = SceneManager.selectedBorrowingRecord;
        // remove book from borrowing record and borrowedBooks observable list
        borrowingRecord.getBooks().remove(returnBook);
        borrowedBooks.remove(returnBook);
        // make book available for next borrowings
        returnBook.setStatus(BookCopy.Status.AVAILABLE);
        // serialize changes
        SerializationPattern.getInstance().serializeData();
        // refresh table to display books, which are still borrowed in this borrowing record
        borrowedBooksTableView.refresh();
        // display info about successful return of book
        PopUps.showSuccessPopUp("Success", "You have successfully returned book.");
    }

    public void finishBorrowing(MouseEvent event) {
        // get current borrowing
        BorrowingRecord borrowingRecord = SceneManager.selectedBorrowingRecord;
        // remove borrowing from allBorrowing arraylist
        SerializationPattern.getInstance().removeBorrowing(borrowingRecord);
        // make each book from this borrowing record available again
        for (BookCopy bookCopy : borrowingRecord.getBooks()) {
            bookCopy.setStatus(BookCopy.Status.AVAILABLE);
        }
        // after that serialize changes again
        SerializationPattern.getInstance().serializeData();
        // display success popup about returning the books
        PopUps.showSuccessPopUp("Success", "Books were successfully returned.");
        // go back to all borrowing scene
        this.librarianBorrowings(event);
    }
}
