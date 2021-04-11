package Controller.Librarian;

import Controller.SceneManager;
import Model.BookCopy;
import Model.BookTitle;
import PopUps.PopUps;
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
import java.util.logging.Logger;

public class BooksEvidenceController extends LibrarianController implements Initializable {

    // logger for this class
    private static final Logger LOGGER = Logger.getLogger(BooksEvidenceController.class.getName());

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

    public void createBookCopy() {
        // user can't create book copy if he/she didn't select book title
        if (bookTitlesTableView.getSelectionModel().getSelectedItem() == null) {
            // user didn't select book title, so log info about it and show error popup to user
            LOGGER.info("User didn't select a book title for creating a book copy of it.");
            PopUps.showErrorPopUp("Error", "You have to select book title to create copy of it.");
            return;
        }

        // get selected book title by user
        BookTitle selectedBookTitle = bookTitlesTableView.getSelectionModel().getSelectedItem();
        // get unique id for book copy
        int uniqueId = SerializationPattern.getInstance().getSerializationObject().getAllBookCopies().size();
        // create new book copy based on selected book title
        BookCopy newBookCopy = new BookCopy(selectedBookTitle, uniqueId,BookCopy.Status.AVAILABLE);
        // add newly created book copy to allBookCopies in selected book title
        selectedBookTitle.getAllBookCopies().add(newBookCopy);
        // add new book copy to arraylist of all book copies and serialize data
        SerializationPattern.getInstance().addNewBookCopy(newBookCopy);
        // after creation refresh TableView, to display new data
        bookTitlesTableView.refresh();
        // show successful popup to user, because book copy was successfully created
        PopUps.showSuccessPopUp("Success", "Book copy was successfully created");
    }

    public void editBookTitle(MouseEvent event) {
        // check if user selected BookTitle, which he/she wants to edit
        if (bookTitlesTableView.getSelectionModel().getSelectedItem() == null) {
            PopUps.showErrorPopUp("Error", "You have to select book title, which you want to edit");
            return;
        }
        // set selectedBookTitle in SceneManager class, to make it accessible anywhere
        SceneManager.selectedBookTitle = bookTitlesTableView.getSelectionModel().getSelectedItem();
        // switch to EditBookTitleScene
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.EDIT_BOOK_TITLE_SCENE, true);
    }

    public void detailBookTitle(MouseEvent event) {

    }
}
