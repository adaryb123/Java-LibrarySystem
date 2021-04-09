package Controller;

import javafx.scene.input.MouseEvent;

public class LoginController {
    // on mouse click check if user name and user password are right and login as a Librarian
    public void loginAsLibrarian(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.BORROWINGS_SCENE, true);
    }

    // on mouse click check reader's ID and login as a reader
    public void loginAsReader(MouseEvent event) {
        // parameter librarianSceneFlag has to be false, because we are switching to one of the reader's scenes
    }
}
