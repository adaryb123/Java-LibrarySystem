package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
   This class is responsible to switch between scenes.
    In future it may also control some header/footer that will always be visible
 */
public class ControllerWindowLibrarian {

    @FXML
    private BorderPane BorderPaneContent;

    public ControllerWindowLibrarian(){
        WindowLibrarianReference.setParentController(this);
    }

    @FXML
    void switchSceneToBorrowings(ActionEvent event) {
        FXMLLoader fxmlLoader = SceneManager.switchBorderPane(BorderPaneContent, "sceneBorrowings");
        ((ControllerSceneBorrowings) fxmlLoader.getController()).init();
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
