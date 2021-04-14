package Controller.Librarian;

import Controller.SceneManager;
import Model.BookCopy;
import Model.Reader;
import PopUps.PopUps;
import Serialization.SerializationPattern;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseBooksBorrowingController extends LibrarianController implements Initializable {

    @FXML
    private TableView <BookCopy> allAvailableBooksTableView, borrowingBooksTableView;

    // columns for allBooksTableView
    @FXML
    private TableColumn <BookCopy, String> allBookIdCol, allBookAuthorCol,
            allBookNameCol, allBookPublisherCol, allBookStateCol;

    // columns for borrowingBooksTableView
    @FXML
    private TableColumn <BookCopy, String> borrowingBookIdCol, borrowingBookAuthorCol,
            borrowingBookNameCol, borrowingBookPublisherCol, borrowingBookStateCol;

    private ObservableList <BookCopy> reservedBooksByReader;
    private ObservableList <BookCopy> allAvailableBooks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // check if scene contains TableViews with ids allBooksTableView, borrowingBooksTableView
        if (allAvailableBooksTableView != null && borrowingBooksTableView != null) {
            // initialize reservedBooksByReader with selectedReader's reservedBooks
            Reader selectedReader = SceneManager.selectedReader;
            reservedBooksByReader = FXCollections.observableArrayList(selectedReader.getReservedBooks());
            // map columns in allBooksTableView on methods in BookCopy class
            this.mapColumnsInTableView(allBookIdCol, allBookAuthorCol,
                    allBookNameCol, allBookPublisherCol, allBookStateCol
            );
            // map columns in borrowingBooksTableView on methods in BookCopy class
            this.mapColumnsInTableView(borrowingBookIdCol, borrowingBookAuthorCol,
                    borrowingBookNameCol, borrowingBookPublisherCol, borrowingBookStateCol
            );
            // get all available books
            this.getAllAvailableBooks();
            // display all available books in allBooksTableView
            allAvailableBooksTableView.setItems(allAvailableBooks);
            // display reserved books in borrowingBooksTableView
            borrowingBooksTableView.setItems(reservedBooksByReader);
        }
    }

    public void addSelectedBook() {
        if (allAvailableBooksTableView.getSelectionModel().getSelectedItem() == null) {
            // user didn't selected book, which he/she want to add into borrowing books, so show error popup
            PopUps.showErrorPopUp(
                    "Error",
                    "You have to select book, which you want to add to borrowing."
            );
            return;
        }
        // get selected BookCopy
        BookCopy selectedBookCopy = allAvailableBooksTableView.getSelectionModel().getSelectedItem();
        // change status of selected book
        selectedBookCopy.setStatus(BookCopy.Status.RESERVED);
        // remove book copy from all available books arraylist
        allAvailableBooks.remove(selectedBookCopy);
        // store selected book into reservedBooksByReader
        reservedBooksByReader.add(selectedBookCopy);
        // refresh tableviews, to display updated data
        allAvailableBooksTableView.refresh();
        borrowingBooksTableView.refresh();
    }

    public void removeSelectedBook() {
        if (borrowingBooksTableView.getSelectionModel().getSelectedItem() == null) {
            // user didn't selected book, which he/she wants to remove from borrowing books, so show error popup
            PopUps.showErrorPopUp(
                    "Error",
                    "You have to select book, which you want to remove from borrowing."
            );
            return;
        }
        // get selected book
        BookCopy selectedBookCopy = borrowingBooksTableView.getSelectionModel().getSelectedItem();
        // change status of selected book
        selectedBookCopy.setStatus(BookCopy.Status.AVAILABLE);
        // remove book copy from reader's reserved books arraylist
        reservedBooksByReader.remove(selectedBookCopy);
        // add selected book to all available books
        allAvailableBooks.add(selectedBookCopy);
        // refresh tableviews, to display updated data
        allAvailableBooksTableView.refresh();
        borrowingBooksTableView.refresh();
    }

    public void createBorrowing(MouseEvent event) {

    }

    public void getAllAvailableBooks() {
        // create new arraylist for all available books
        this.allAvailableBooks = FXCollections.observableArrayList();
        // get all book copies
        ArrayList<BookCopy> allBooks = SerializationPattern.getInstance().getSerializationObject().getAllBookCopies();

        for (BookCopy bookCopy : allBooks) {
            // add only available books allAvailableBooks arraylist
            if (bookCopy.isAvailable() == BookCopy.Status.AVAILABLE) {
                this.allAvailableBooks.add(bookCopy);
            }
        }
    }

    public void mapColumnsInTableView(
            TableColumn <BookCopy, String> idCol,
            TableColumn <BookCopy, String> authorCol,
            TableColumn <BookCopy, String> nameCol,
            TableColumn <BookCopy, String> publisherCol,
            TableColumn <BookCopy, String> stateCol
    ) {
        // inspiration https://stackoverflow.com/questions/25204068/how-do-i-point-a-propertyvaluefactory-to-a-value-of-a-map
        idCol.setCellValueFactory(
                data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getId()))
        );
        authorCol.setCellValueFactory(
                data -> new ReadOnlyStringWrapper(data.getValue().getBookTitle().getAuthorName())
        );
        nameCol.setCellValueFactory(
                data -> new ReadOnlyStringWrapper(data.getValue().getBookTitle().getBookName())
        );
        publisherCol.setCellValueFactory(
                data -> new ReadOnlyStringWrapper(data.getValue().getBookTitle().getPublisherName())
        );
        stateCol.setCellValueFactory(
                data -> {
                    BookCopy.Status status = data.getValue().getStatus();

                    if (status == BookCopy.Status.AVAILABLE) {
                        return new ReadOnlyStringWrapper("Available");
                    } else if (status == BookCopy.Status.BORROWED) {
                        return new ReadOnlyStringWrapper("Borrowed");
                    } else if (status == BookCopy.Status.RESERVED) {
                        return new ReadOnlyStringWrapper("Reserved");
                    }

                    return new ReadOnlyStringWrapper("None");
                }
        );
    }
}
