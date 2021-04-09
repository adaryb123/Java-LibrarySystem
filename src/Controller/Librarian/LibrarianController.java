package Controller.Librarian;

import Controller.SceneManager;
import javafx.scene.input.MouseEvent;

/**
 * Each Librarian's controller extends from this controller,
 * because this controller handles navbar in librarian's scenes
 */
public class LibrarianController {
    public void librarianBorrowings(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event ,SceneManager.LIBRARIAN_BORROWINGS_SCENE, true);
    }

    public void librarianReadersCards(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.LIBRARIAN_READERS_CARDS_SCENE, true);
    }

    public void booksEvidence(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.LIBRARIAN_BOOKS_EVIDENCE_SCENE, true);

    }

    public void logout(MouseEvent event) {
        // switching back to login scene
        SceneManager.switchScene(event, SceneManager.LOGIN_SCENE);
    }
}
