package Controller.Reader;

import Controller.SceneManager;
import Model.BookCopy;
import Model.BookTitle;
import PopUps.PopUps;
import Serialization.SerializationPattern;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyBooksController extends ReaderController implements Initializable {

    @FXML
    private TableView<BookCopy> reservedBooksTableView, borrowedBooksTableView;

    @FXML
    private TableColumn<BookCopy, String> borrowedAuthorCol, borrowedBookNameCol, reservedAuthorCol,reservedBookNameCol;

    private ObservableList<BookCopy> reservedBooks;
    private ObservableList <BookCopy> borrowedBooks;

    @FXML
    void removeSelectedReservedBook(MouseEvent event) {
        if (reservedBooksTableView.getSelectionModel().getSelectedItem() == null) {
            PopUps.showErrorPopUp("Select book", "You have to select book first.");
            return;
        }

        BookCopy selectedBook = reservedBooksTableView.getSelectionModel().getSelectedItem();
        reservedBooks.remove(selectedBook);
        selectedBook.setStatus(BookCopy.Status.AVAILABLE);
        ArrayList<BookCopy> storedBooks = SceneManager.currentReader.getReservedBooks();
        storedBooks.remove(selectedBook);
        SceneManager.currentReader.setReservedBooks(storedBooks);
        SerializationPattern.getInstance().serializeData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        reservedBooks = FXCollections.observableArrayList(SceneManager.currentReader.getReservedBooks());
        borrowedBooks = FXCollections.observableArrayList(SceneManager.currentReader.getBorrowedBooks());

        borrowedAuthorCol.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getBookTitle().getAuthorName()));
        borrowedBookNameCol.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getBookTitle().getBookName()));
        reservedAuthorCol.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getBookTitle().getAuthorName()));
        reservedBookNameCol.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getBookTitle().getBookName()));

        reservedBooksTableView.setItems(reservedBooks);
        borrowedBooksTableView.setItems(borrowedBooks);

    }
}
