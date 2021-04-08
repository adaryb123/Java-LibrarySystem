package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
   This class is responsible to switch between scenes.
    In future it may also control some header/footer that will always be visible
 */
public class LibrarianController {
    public LibrarianController() {
        WindowLibrarianReference.setParentController(this);
    }

    @FXML
    void switchSceneToBorrowings(ActionEvent event) {
        SceneManager.switchScene(event, "BorrowingScene");
    }

    @FXML
    void switchSceneToBrowseBooks(ActionEvent event) {

    }

    @FXML
    void switchSceneToReadersCards(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {

    }
}
