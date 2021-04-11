package Controller.Librarian;

import Controller.SceneManager;
import Model.BookTitle;
import Serialization.SerializationPattern;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BooksEvidenceController extends LibrarianController implements Initializable {

    @FXML
    private TableView<BookTitle> bookTitlesTableView;
    @FXML
    private TableColumn<BookTitle, String> authorCol, bookNameCol, publisherCol, reviewsNumCol, bookCopiesCol;
    @FXML
    private TableColumn<BookTitle, Integer> publishYearCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // if scene contains TableView with id bookTitlesTableView
        // then map columns to attributes and methods.
        // after that display allBookTitles in this TableView
        if (bookTitlesTableView != null) {
            authorCol.setCellValueFactory(new PropertyValueFactory<>("authorName"));
            bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisherName"));
            publishYearCol.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
            // inspiration https://stackoverflow.com/questions/25204068/how-do-i-point-a-propertyvaluefactory-to-a-value-of-a-map
            reviewsNumCol.setCellValueFactory(
                    // get number of reviews for this book title
                    data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getReviews().size()))
            );
            bookCopiesCol.setCellValueFactory(
                    data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getAllBookCopies().size()))
            );

            // get all book titles from SerializationPattern singleton
            ArrayList<BookTitle> allBookTitles = SerializationPattern.getInstance().getSerializationObject().getAllBookTitles();
            // cast allBookTitles arraylist to observable list and display them in TableView
            bookTitlesTableView.setItems(FXCollections.observableArrayList(allBookTitles));
        }
    }

    // on button click switch to CreateBookTitleScene
    public void createBookTitle(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.CREATE_BOOK_TITLE_SCENE, true);
    }

    public void detailBookTitle(MouseEvent event) {

    }
}
