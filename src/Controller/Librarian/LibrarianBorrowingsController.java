package Controller.Librarian;

import Controller.SceneManager;
import Model.BorrowingRecord;
import Serialization.SerializationPattern;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LibrarianBorrowingsController extends LibrarianController implements Initializable {

    @FXML
    private TableView<BorrowingRecord> borrowingsTableView;
    @FXML
    private TableColumn<BorrowingRecord, String> dateCol, idCol, nameCol, phoneNumberCol, emailCol, borrowedBooksCol;

    // this method runs, when scene is loading
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // if scene contains borrowingsTableView, then map values to tableView's columns
        if (borrowingsTableView != null) {
            // inspiration https://stackoverflow.com/questions/25204068/how-do-i-point-a-propertyvaluefactory-to-a-value-of-a-map
            dateCol.setCellValueFactory(
                    // get borrowing date
                    data -> new ReadOnlyStringWrapper(data.getValue().getDate())
            );
            idCol.setCellValueFactory(
                    // get id on reader's card
                    data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getReader().getReadersCard().getId()))
            );
            nameCol.setCellValueFactory(
                    // get reader's name
                    data -> new ReadOnlyStringWrapper(data.getValue().getReader().getReadersCard().getReaderName())
            );
            phoneNumberCol.setCellValueFactory(
                    // get reader's phone
                    data -> new ReadOnlyStringWrapper(data.getValue().getReader().getReadersCard().getReaderPhoneNumber())
            );
            emailCol.setCellValueFactory(
                    // get reader's email
                    data -> new ReadOnlyStringWrapper(data.getValue().getReader().getReadersCard().getReaderEmail())
            );
            borrowedBooksCol.setCellValueFactory(
                    // get number of borrowed books and cast it to string
                    data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getBooks().size()))
            );

            // get all borrowings objects from SerializationPattern singleton
            ArrayList<BorrowingRecord> allBorrowings = SerializationPattern.getInstance().getSerializationObject().getAllBorrowingRecords();
            // before setting items to borrowingsTableView, it casts allBorrowings arraylist to observable list
            borrowingsTableView.setItems(FXCollections.observableArrayList(allBorrowings));
        }
    }

    public void createBorrowingScene(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.LIBRARIAN_CREATE_BORROWING_SCENE, true);
    }

    public void detailBorrowingScene() {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes

    }
}
