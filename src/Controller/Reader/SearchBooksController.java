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
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class SearchBooksController extends ReaderController implements Initializable {

    @FXML
    private TableView<BookTitle> booksTableView;

    @FXML
    private TableColumn<BookTitle, String> authorCol, bookNameCol,publisherCol,availableCol,publishYearCol,averageScoreCol;

    private ObservableList<BookTitle> bookTitles;

    @FXML
    void addReview(MouseEvent event) {
        //show error message if no book is selected
        if (booksTableView.getSelectionModel().getSelectedItem() == null) {
            PopUps.showErrorPopUp("Select book", "You have to select book first.");
            return;
        }
        //switch to add review scene
        SceneManager.selectedBookTitleReader = booksTableView.getSelectionModel().getSelectedItem();
        SceneManager.switchScene(event, SceneManager.ADD_REVIEW_SCENE, false);
    }

    @FXML
    void reserve(MouseEvent event) {
        //show error message if no book is selected
        if (booksTableView.getSelectionModel().getSelectedItem() == null) {
            PopUps.showErrorPopUp("Select book", "You have to select book first.");
            return;
        }
        //show error message if selected book is not available
        if (!isAvailable(booksTableView.getSelectionModel().getSelectedItem()).equals("YES")) {
            PopUps.showErrorPopUp("Unavailable book", "Selected book is not available right now.");
            return;
        }

        //add the first available bookcopy to users list of reserved books
        SceneManager.selectedBookTitleReader = booksTableView.getSelectionModel().getSelectedItem();
        for (BookCopy bc : SceneManager.selectedBookTitleReader.getAllBookCopies()){
            if(bc.getStatus() == BookCopy.Status.AVAILABLE){
                ArrayList<BookCopy> readersReservedBooks = SceneManager.currentReader.getReservedBooks();
                readersReservedBooks.add(bc);
                //set the book copy status to reserved
                bc.setStatus(BookCopy.Status.RESERVED);
                //save changes
                SerializationPattern.getInstance().serializeData();
                // show success pop up, because new borrowing record was successfully created
                PopUps.showSuccessPopUp("Success", "Book reserved");
                // log info about successful creation
                LOGGER.info("Book reserved");

                return;
            }
        }
    }

    @FXML
    void viewReviews(MouseEvent event) {
        //show error messages if no book is selected
        if (booksTableView.getSelectionModel().getSelectedItem() == null) {
            PopUps.showErrorPopUp("Select book", "You have to select book first.");
            return;
        }
        //switch scenes to viewReviews
        SceneManager.selectedBookTitleReader = booksTableView.getSelectionModel().getSelectedItem();
        SceneManager.switchScene(event, SceneManager.VIEW_REVIEWS_SCENE, false);
    }

    //calculate the average of stars in reviews for one booktitle
    public double calculateAvgRating(BookTitle bookTitle){
        double total = 0;
        for (Review r : bookTitle.getReviews())
            total += r.getStarsNum();
        total /= bookTitle.getReviews().size();
        return total;
    }

    //return "YES" if booktitle has available book copies. Else return "NO"
    public String isAvailable(BookTitle bookTitle){
        for (BookCopy c : bookTitle.getAllBookCopies()) {
            if (c.getStatus() == BookCopy.Status.AVAILABLE)
                return "YES";
        }
        return "NO";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //initialize table view with all booktitles
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
