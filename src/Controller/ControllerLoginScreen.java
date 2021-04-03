package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControllerLoginScreen {

    @FXML
    private TextField TextFieldReadersID;

    @FXML
    private TextField TextFieldPassword;

    @FXML
    private TextField TextFieldUsername;

    @FXML
    void LoginAsLibrarian(ActionEvent event) throws Exception {
        SceneManager.switchScene(event,"WindowLibrarian");
        WindowLibrarianReference.getParentController().switchSceneToBorrowings(new ActionEvent());
    }

    @FXML
    void LoginAsReader(ActionEvent event) {

    }

}
