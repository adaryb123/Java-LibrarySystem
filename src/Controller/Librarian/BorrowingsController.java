package Controller.Librarian;

import Controller.SceneManager;
import Model.BorrowingRecord;
import PopUps.PopUps;
import Serialization.SerializationPattern;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BorrowingsController extends LibrarianController implements Initializable {

    @FXML
    private TableView<BorrowingRecord> borrowingsTableView;
    @FXML
    private TableColumn<BorrowingRecord, String> dateCol, idCol, nameCol, phoneNumberCol, emailCol, borrowedBooksCol;
    @FXML
    private ComboBox<String> searchComboBox;
    @FXML
    private TextField tfSearch;

    private String searchFilter = "Name";

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

        // if scene contains ComboBox with id searchComboBox, then add items
        if (searchComboBox != null) {
            searchComboBox.setItems(FXCollections.observableArrayList("ID", "Name"));
        }
    }

    public void changeSearchFilter() {
        // if user select any of ComboBox items, then change searchFilter
        if (searchComboBox.getSelectionModel().getSelectedItem() != null) {
            searchFilter = searchComboBox.getSelectionModel().getSelectedItem();
        }
    }

    public void searchBorrowings() {
        // get all borrowings
        ArrayList<BorrowingRecord> allBorrowings = SerializationPattern.getInstance().getSerializationObject().getAllBorrowingRecords();
        // filtered borrowings
        ObservableList<BorrowingRecord> filteredBorrowings = FXCollections.observableArrayList();

        // regex inspiration from https://www.w3schools.com/java/java_regex.asp
        Matcher matcher;
        Pattern pattern = Pattern.compile(tfSearch.getText(), Pattern.CASE_INSENSITIVE);

        for (BorrowingRecord borrowingRecord : allBorrowings) {
            if (searchFilter.equals("Name")) {
                // if entered name matches reader's name in current borrowing record,
                // then current borrowing record will be displayed in TableView
                matcher = pattern.matcher(borrowingRecord.getReader().getReadersCard().getReaderName());
            } else {
                // convert reader's ID to string and try to match with entered ID in search bar
                matcher = pattern.matcher(String.valueOf(borrowingRecord.getReader().getReadersCard().getId()));
            }
            // if entered string matches value for current borrowing, then add this borrowing to filteredBorrowings
            if (matcher.find()) {
                filteredBorrowings.add(borrowingRecord);
            }
        }

        borrowingsTableView.setItems(filteredBorrowings);
    }

    public void createBorrowingScene(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.CREATE_BORROWING_SCENE, true);
    }

    public void detailBorrowingScene(MouseEvent event) {
        // firstly check if user selected borrowing, which he wants to display in detail
        if (borrowingsTableView.getSelectionModel().getSelectedItem() == null) {
            // program displays error popup, because user didn't select any borrowing
            PopUps.showErrorPopUp("Error", "You have to select borrowing, which you want to display in detail.");
            return;
        }
        // get selected borrowing and store borrowing record in SceneManager,
        // to make it accessible from anywhere
        SceneManager.selectedBorrowingRecord = borrowingsTableView.getSelectionModel().getSelectedItem();
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.DETAIL_BORROWING_SCENE, true);
    }
}
