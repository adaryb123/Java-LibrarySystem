package Controller.Reader;

import Controller.SceneManager;
import Model.BookTitle;
import Model.Reader;
import Model.Review;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewReviewsController extends SearchBooksController implements Initializable {
    @FXML
    private VBox VBoxItemHolder;

    private ArrayList<AnchorPane> listingItems = new ArrayList<>();

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
