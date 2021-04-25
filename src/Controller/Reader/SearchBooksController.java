package Controller.Reader;

import Controller.SceneManager;
import Model.BookCopy;
import Model.BookTitle;
import Model.Review;
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
import java.util.ResourceBundle;

public class SearchBooksController extends ReaderController implements Initializable {

    @FXML
    private TableView<BookTitle> booksTableView;

    @FXML
    private TableColumn<BookTitle, String> authorCol, bookNameCol,publisherCol,availableCol,publishYearCol,averageScoreCol;

    private ObservableList<BookTitle> bookTitles;

    @FXML
    void addReview(MouseEvent event) {
        if (booksTableView.getSelectionModel().getSelectedItem() == null) {
            PopUps.showErrorPopUp("Select book", "You have to select book first.");
            return;
        }
        SceneManager.selectedBookTitleReader = booksTableView.getSelectionModel().getSelectedItem();
        SceneManager.switchScene(event, SceneManager.ADD_REVIEW_SCENE, false);
    }

    @FXML
    void reserve(MouseEvent event) {

    }

    @FXML
    void viewReviews(MouseEvent event) {
        if (booksTableView.getSelectionModel().getSelectedItem() == null) {
            PopUps.showErrorPopUp("Select book", "You have to select book first.");
            return;
        }
        SceneManager.selectedBookTitleReader = booksTableView.getSelectionModel().getSelectedItem();
        SceneManager.switchScene(event, SceneManager.VIEW_REVIEWS_SCENE, false);
    }

    public double calculateAvgRating(BookTitle bookTitle){
        double total = 0;
        for (Review r : bookTitle.getReviews())
            total += r.getStarsNum();
        total /= bookTitle.getReviews().size();
        return total;
    }

    public String isAvailable(BookTitle bookTitle){
        for (BookCopy c : bookTitle.getAllBookCopies()) {
            if (c.isAvailable() == BookCopy.Status.AVAILABLE)
                return "YES";
        }
        return "NO";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bookTitles = FXCollections.observableArrayList(SerializationPattern.getInstance().getSerializationObject().getAllBookTitles());

        authorCol.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getAuthorName()));
        bookNameCol.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getBookName()));
        publisherCol.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(lambda.getValue().getPublisherName()));
        publishYearCol.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(String.valueOf(lambda.getValue().getYearPublished())));
        averageScoreCol.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(String.valueOf(calculateAvgRating(lambda.getValue()))));
        availableCol.setCellValueFactory(lambda -> new ReadOnlyStringWrapper(isAvailable(lambda.getValue())));

        booksTableView.setItems(bookTitles);
    }
}
