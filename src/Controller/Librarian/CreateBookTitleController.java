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
    // these attributes are needed to make code DRY in EditBookTitleController and this class
    private String author, bookName, publisher, publishedYear;

    // on button click create new BookTitle and go back to Books evidence scene
    public void finishCreateTitle(MouseEvent event) {
        // BookTitle won't be created if user didn't fill all text fields
        if (!checkTextField()) {
            return;
        }

        // try parse publishedYear from string to integer
        // if parsing was successful create BookTitle and switch to BooksEvidenceScene
        try {
            int publishedYearInt = Integer.parseInt(this.publishedYear);
            // create instance of BookTitle based on filled data
            BookTitle newBookTitle = new BookTitle(
                    this.author,
                    this.bookName,
                    this.publisher,
                    publishedYearInt
            );
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

    public boolean checkTextField() {
        this.author = tfAuthorName.getText();
        this.bookName = tfBookName.getText();
        this.publisher = tfPublisher.getText();
        this.publishedYear = tfPublishedYear.getText();

        // BookTitle won't be created if user didn't fill all text fields
        if (this.author.equals("") || this.bookName.equals("") || this.publisher.equals("") || this.publishedYear.equals("")) {
            PopUps.showErrorPopUp("Error", "All text fields have to be filled.");
            return false;
        }

        return true;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookName() {
        return bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedYear() {
        return publishedYear;
    }
}
