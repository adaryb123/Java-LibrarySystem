package Controller.Reader;

import Model.BookTitle;
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
    private TableView<BookTitle> reservedBooksTableView, BorrowedBooksTableView;

    @FXML
    private TableColumn<BookTitle, String> borrowedAuthorCol, borrowedBookNameCol, reservedAuthorCol,reservedBookNameCol;

    @FXML
    void removeSelectedReservedBook(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        borrowedAuthorCol.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        borrowedBookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));

        reservedAuthorCol.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        reservedBookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));

    }
}
