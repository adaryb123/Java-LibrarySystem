package Controller;
import Controller.Librarian.LibrarianBooksEvidenceController;
import Controller.Librarian.LibrarianBorrowingsController;
import Controller.Librarian.LibrarianReadersCardsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import Main.Main;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is based on code from https://www.youtube.com/watch?v=5yQbt6lYRqk .
 * It loads the .fxml file of new scene when switching between scenes.
 * Class is abstract, because we don't need to create an instance of it.
 */
public class SceneManager {

    // login constant
    public static final String LOGIN_SCENE = "LoginScene";

    // constants for Librarian's scenes files
    public static final String LIBRARIAN_BORROWINGS_SCENE = "LibrarianBorrowingsScene";
    public static final String LIBRARIAN_BOOKS_EVIDENCE_SCENE = "LibrarianBooksEvidenceScene";
    public static final String LIBRARIAN_READERS_CARDS_SCENE = "LibrarianReadersCardsScene";
    public static final String LIBRARIAN_CREATE_BORROWING_SCENE = "LibrarianCreateBorrowingScene";

    // logger for this class
    private static final Logger LOGGER = Logger.getLogger(SceneManager.class.getName());

    public static void switchScene(MouseEvent event, String fileName) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/" + fileName + ".fxml"));
        SceneManager.makeSwitch(fxmlLoader, event, fileName);
    }

    // overload switchScene method, when SceneManager switching between Librarian's or Reader's scenes
    public static void switchScene(MouseEvent event, String fileName, boolean librarianSceneFlag) {
        FXMLLoader fxmlLoader;

        // if this condition is true, stage will be switching only librarian's scenes
        // otherwise it will be switching only reader's scenes.
        // there is important difference in path, which goes to getResource method
        if (librarianSceneFlag) {
            fxmlLoader = new FXMLLoader(Main.class.getResource("/view/librarian/" + fileName + ".fxml"));
        } else {
            fxmlLoader = new FXMLLoader(Main.class.getResource("/view/reader/" + fileName + ".fxml"));
        }

        SceneManager.makeSwitch(fxmlLoader, event, fileName);
    }

    public static void makeSwitch(FXMLLoader fxmlLoader, MouseEvent event, String fileName) {
        try {
            // set scene controller before switching to new scene in stage
            fxmlLoader.setController(SceneManager.getController(fileName));
            Parent root = fxmlLoader.load();
            Scene fxmlScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(fxmlScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace(); // Remove this line, when program is finished
            LOGGER.log(Level.SEVERE, "Something went wrong during switching scene: " + e.getMessage());
        }
    }

    // this method returns proper controller by fileName for scene
    public static Object getController(String fileName) {
        switch (fileName) {
            case LOGIN_SCENE:
                return new LoginController();
            case LIBRARIAN_BORROWINGS_SCENE:
                return new LibrarianBorrowingsController();
            case LIBRARIAN_BOOKS_EVIDENCE_SCENE:
                return new LibrarianBooksEvidenceController();
            case LIBRARIAN_READERS_CARDS_SCENE:
                return new LibrarianReadersCardsController();
        }

        return null;
    }
}
