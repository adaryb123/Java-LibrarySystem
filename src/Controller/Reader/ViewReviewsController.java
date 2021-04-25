package Controller.Reader;

import Controller.SceneManager;
import Model.BookCopy;
import Model.BookTitle;
import Model.Reader;
import Model.Review;
import PopUps.PopUps;
import Serialization.SerializationPattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ViewReviewsController extends SearchBooksController implements Initializable {
    @FXML
    private VBox VBoxItemHolder;

    private ArrayList<AnchorPane> listingItems = new ArrayList<>();

    @FXML
    void reserve(MouseEvent event) {

        //add the first available bookcopy to users list of reserved books
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBoxItemHolder.getChildren().clear();

        //the reviews will be visible in scrollable window of items
        for (Review r : SceneManager.selectedBookTitleReader.getReviews()) {
            AnchorPane itemPane = new AnchorPane();
            //ItemReview.fxml is container for each item == review
            FXMLLoader fxmlLoader = SceneManager.switchListingPane(itemPane, "ItemReview",false);
            ItemReviewController controller = fxmlLoader.getController();
            //set container labels to those of current review
            controller.setReview(r);
            listingItems.add(itemPane);
        }

        VBoxItemHolder.getChildren().addAll(listingItems);

    }
}
