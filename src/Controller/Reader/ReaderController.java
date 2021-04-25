package Controller.Reader;

import Controller.SceneManager;
import Model.Reader;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ReaderController implements Initializable {

    public void readerMyBooks(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event ,SceneManager.MY_BOOKS_SCENE, false);
    }

    public void readerSearchBooks(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.SEARCH_BOOKS_SCENE, false);
    }

    public void logout(MouseEvent event) {
        // switching back to login scene
        SceneManager.switchScene(event, SceneManager.LOGIN_SCENE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
