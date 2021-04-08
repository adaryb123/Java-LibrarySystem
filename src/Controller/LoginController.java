package Controller;

import Controller.SceneManager;
import Controller.WindowLibrarianReference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
    public void loginAsLibrarian(ActionEvent event) throws Exception {
        SceneManager.switchScene(event,"LibrarianScene");
        WindowLibrarianReference.getParentController().switchSceneToBorrowings(new ActionEvent());
    }

    public void loginAsReader(ActionEvent event) {

    }
}
