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
        for (BookCopy bc : SceneManager.selectedBookTitleReader.getAllBookCopies()){
            if(bc.getStatus() == BookCopy.Status.AVAILABLE){
                ArrayList<BookCopy> readersReservedBooks = SceneManager.currentReader.getReservedBooks();
                readersReservedBooks.add(bc);
                bc.setStatus(BookCopy.Status.RESERVED);
                SerializationPattern.getInstance().serializeData();
                // show success pop up, because new borrowing record was successfully created
                PopUps.showSuccessPopUp("Success", "Book reserved");
                // log info about successfull creation
                LOGGER.info("Book reserved");
                return;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBoxItemHolder.getChildren().clear();

        for (Review r : SceneManager.selectedBookTitleReader.getReviews()) {
            AnchorPane itemPane = new AnchorPane();
            FXMLLoader fxmlLoader = SceneManager.switchListingPane(itemPane, "ItemReview",false);
            ItemReviewController controller = fxmlLoader.getController();
            controller.setReview(r);
            listingItems.add(itemPane);
        }

        VBoxItemHolder.getChildren().addAll(listingItems);

    }
}
