package Controller.Reader;

import Model.BookTitle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchBooksController extends ReaderController implements Initializable {

    @FXML
    private TableView<BookTitle> booksTableView;

    @FXML
    private TableColumn<BookTitle, String> authorCol, bookNameCol,publisherCol,availableCol;

    @FXML
    private TableColumn<BookTitle, Integer> publishYearCol;

    @FXML
    private TableColumn<BookTitle, Double> averageScoreCol;

    @FXML
    void addReview(MouseEvent event) {

    }

    @FXML
    void reserve(MouseEvent event) {

    }

    @FXML
    void viewDetail(MouseEvent event) {

    }

    @FXML
    void viewReviews(MouseEvent event) {

    }
}
