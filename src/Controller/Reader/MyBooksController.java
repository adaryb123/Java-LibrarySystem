package Controller.Reader;

import Controller.SceneManager;
import Model.BookCopy;
import Model.BookTitle;
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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (BookCopy bk : SceneManager.currentReader.getBorrowedBooks())
            System.out.println(bk.getBookTitle());

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
