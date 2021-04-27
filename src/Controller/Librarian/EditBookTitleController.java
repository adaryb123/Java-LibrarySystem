package Controller.Librarian;

import Controller.SceneManager;
import Model.BookCopy;
import Model.BookTitle;
import PopUps.PopUps;
import Serialization.SerializationPattern;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditBookTitleController extends CreateBookTitleController implements Initializable {

    // logger for this class
    private static final Logger LOGGER = Logger.getLogger(EditBookTitleController.class.getName());

    @FXML
    private TextField tfAuthorName, tfBookName, tfPublisher, tfPublishedYear;

    @FXML
    private TableView<BookCopy> bookCopiesTableView;
    @FXML
    private TableColumn<BookCopy, String> bookIdCol, bookStatusCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // if scene contains TextFields with ids tfAuthorName, tfBookName, tfPublisher, tfPublishedYear
        // then display selectedBookTitle data in them
        if (bookCopiesTableView != null && tfAuthorName != null &&
                tfBookName != null && tfPublisher != null && tfPublishedYear != null) {
            BookTitle selectedBookTitle = SceneManager.selectedBookTitle;
            tfAuthorName.setText(selectedBookTitle.getAuthorName());
            tfBookName.setText(selectedBookTitle.getBookName());
            tfPublisher.setText(selectedBookTitle.getPublisherName());
            tfPublishedYear.setText(String.valueOf(selectedBookTitle.getYearPublished()));

            // map table columns on book copy methods
            bookIdCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getId())));
            bookStatusCol.setCellValueFactory(data -> {
                String value = "";
                BookCopy.Status status = data.getValue().getStatus();
                // create string value based on Status enum
                if (status == BookCopy.Status.AVAILABLE) {
                    value = "Available";
                } else if (status == BookCopy.Status.BORROWED) {
                    value = "Borrowed";
                } else {
                    value = "Reserved";
                }

                return new ReadOnlyStringWrapper(value);
            });
            // display book copies in table view
            bookCopiesTableView.setItems(FXCollections.observableArrayList(selectedBookTitle.getAllBookCopies()));
        }
    }

    public void removeBookCopy() {
        // if user didn't select book copy, which he/she wants to remove, then display error popup
        if (bookCopiesTableView.getSelectionModel().getSelectedItem() == null) {
            PopUps.showErrorPopUp("Error", "You have to select book copy, which you want to remove.");
            return;
        }
        // get selected book copy
        BookCopy selectedBookCopy = bookCopiesTableView.getSelectionModel().getSelectedItem();
        // don't remove selected book copy, if it isn't available
        if (selectedBookCopy.getStatus() != BookCopy.Status.AVAILABLE) {
            PopUps.showErrorPopUp("Error", "You can't remove book, which is reserved or borrowed.");
            return;
        }
        // remove book copy from selected book title
        BookTitle selectedBookTitle = SceneManager.selectedBookTitle;
        selectedBookTitle.getAllBookCopies().remove(selectedBookCopy);
        // remove book copy from all book copies list and serialize changes
        SerializationPattern.getInstance().removeBookCopy(selectedBookCopy);
        // display new items in table view
        bookCopiesTableView.setItems(FXCollections.observableArrayList(selectedBookTitle.getAllBookCopies()));
    }

    public void finishEditingBookTitle(MouseEvent event) {
        // show confirmation popup to user
        if (PopUps.showConfirmationPopUp("Confirmation", "Do you want to save changes?")) {
            // user wants to save changes, which were made for selectedBookTitle
            if (this.checkTextField()) {
                // user fill all TextFields, so editing process will continue
                // try parse publishedYear from string to integer
                // if parsing was successful create BookTitle and switch to BooksEvidenceScene
                try {
                    int publishedYearInt = Integer.parseInt(this.getPublishedYear());
                    // make changes in selectedBookTitle
                    BookTitle selectedBookTitle = SceneManager.selectedBookTitle;
                    selectedBookTitle.setAuthorName(this.getAuthor());
                    selectedBookTitle.setBookName(this.getBookName());
                    selectedBookTitle.setPublisherName(this.getPublisher());
                    selectedBookTitle.setYearPublished(publishedYearInt);
                    // serialize changed data
                    SerializationPattern.getInstance().serializeData();
                    // show success popup to user
                    PopUps.showSuccessPopUp("Success", "Book title was successfully edited.");
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
    }
}
