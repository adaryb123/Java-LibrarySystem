package Controller.Reader;

import Controller.SceneManager;
import Model.BorrowingRecord;
import Model.Review;
import PopUps.PopUps;
import Serialization.SerializationPattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class WriteReviewController extends SearchBooksController implements Initializable {
    @FXML
    private Label authorLabel;

    @FXML
    private Label bookTitleLabel;

    @FXML
    private Spinner<Integer> starsNumSpinner;

    @FXML
    private TextArea reviewTextArea;

    @FXML
    void createReview(MouseEvent event) {
        //get current date to string
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String stringDate = dateFormat.format(currentDate);

        Review review  = new Review(
                stringDate,
                reviewTextArea.getText(),
                starsNumSpinner.getValue(),
                SceneManager.selectedBookTitleReader,
                SceneManager.currentReader
        );
        // add new review to all reviews and serialize data
        SerializationPattern.getInstance().addNewReview(review);
        ArrayList<Review> selectedBookReviews = SceneManager.selectedBookTitleReader.getReviews();
        selectedBookReviews.add(review);
        SceneManager.selectedBookTitleReader.setReviews(selectedBookReviews);
        SerializationPattern.getInstance().serializeData();

        // show success pop up, because review was successfully created
        PopUps.showSuccessPopUp("Success", "New review was successfully created");
        // log info about successfull creation
        LOGGER.info("Review was successfully created");
        // go back to search books cene
        this.readerSearchBooks(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        authorLabel.setText(SceneManager.selectedBookTitleReader.getAuthorName());
        bookTitleLabel.setText(SceneManager.selectedBookTitleReader.getBookName());

        SpinnerValueFactory<Integer> possibleRatings = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,5,5);
        starsNumSpinner.setValueFactory(possibleRatings);
    }
}
