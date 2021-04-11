package Controller.Librarian;

import Controller.SceneManager;
import Model.BookTitle;
import PopUps.PopUps;
import Serialization.SerializationPattern;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateBookTitleController extends LibrarianController {

    // logger for this class
    private static final Logger LOGGER = Logger.getLogger(CreateBookTitleController.class.getName());

    @FXML
    private TextField tfAuthorName, tfBookName, tfPublisher, tfPublishedYear;

    // on button click create new BookTitle and go back to Books evidence scene
    public void finishCreateTitle(MouseEvent event) {
        String author = tfAuthorName.getText();
        String bookName = tfBookName.getText();
        String publisher = tfPublisher.getText();
        String publishedYear = tfPublishedYear.getText();

        // BookTitle won't be created if user didn't fill all text fields
        if (author.equals("") || bookName.equals("") || publisher.equals("") || publishedYear.equals("")) {
            PopUps.showErrorPopUp("Error", "All text fields have to be filled.");
            return;
        }

        // try parse publishedYear from string to integer
        // if parsing was successful create BookTitle and switch to BooksEvidenceScene
        try {
            int publishedYearInt = Integer.parseInt(publishedYear);
            // create instance of BookTitle based on filled data
            BookTitle newBookTitle = new BookTitle(author, bookName, publisher, publishedYearInt);
            // add new BookTitle to all book titles arraylist and serialize data
            SerializationPattern.getInstance().addNewBookTitle(newBookTitle);
            // show success popup to user
            PopUps.showSuccessPopUp("Success", "Book title was successfully created.");
            // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
            SceneManager.switchScene(event, SceneManager.BOOKS_EVIDENCE_SCENE, true);
        } catch (NumberFormatException e) {
            // log info about exception and show error popup to user
            LOGGER.log(Level.SEVERE, "Unable to parse publishedYear to integer.");
            LOGGER.log(Level.SEVERE, "Exception message: " + e.getMessage());
            PopUps.showErrorPopUp(
                    "Error",
                    "Published year has to be integer"
            );
        }
    }
}
