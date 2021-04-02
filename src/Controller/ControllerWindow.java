package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
   This class is responsible to switch between scenes.
    In future it may also control some header/footer that will always be visible
 */
public class ControllerWindow {

    @FXML
    private BorderPane BorderPaneContent;

    public ControllerWindow(){
        WindowReference.setParentController(this);
    }

    @FXML
    void ButtonHomeOnAction(MouseEvent event) {
        switchToMain();
    }

    public void switchToMain() {
        FXMLLoader fxmlLoader = SceneManager.switchBorderPane(BorderPaneContent, "sceneMain");
        ((ControllerSceneMain) fxmlLoader.getController()).init();
    }

}
