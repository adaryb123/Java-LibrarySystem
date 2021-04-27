package Controller.Librarian;

import Controller.SceneManager;
import Model.BorrowingRecord;
import Model.Reader;
import PopUps.PopUps;
import Serialization.SerializationPattern;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllReadersController extends BorrowingsController implements Initializable {

    @FXML
    private TableView<Reader> readersTableView;
    @FXML
    private TableColumn<Reader, String> idCol, nameCol, phoneNumberCol, emailCol,addressCol, reservedBooksCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // if scene contains TableView with this id then map all columns in it and display all readers
        if (readersTableView != null && searchComboBox != null) {
            this.mapColumnsOnReaderMethods(idCol, nameCol, phoneNumberCol, emailCol, addressCol);
            // inspiration https://stackoverflow.com/questions/25204068/how-do-i-point-a-propertyvaluefactory-to-a-value-of-a-map
            reservedBooksCol.setCellValueFactory(
                    // get number of borrowed books and cast it to string
                    data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getReservedBooks().size()))
            );

            // get arraylist of all readers
            ArrayList<Reader> allReaders = SerializationPattern.getInstance().getSerializationObject().getAllReaders();
            // cast arraylist of all readers to observable list and display them in TableView
            readersTableView.setItems(FXCollections.observableArrayList(allReaders));

            // set items to combobox
            searchComboBox.setItems(FXCollections.observableArrayList("Name", "ID"));
        }
    }

    public void createReaderCard(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.CREATE_READER_SCENE, true);
    }

    public void editReader(MouseEvent event) {
        if (readersTableView.getSelectionModel().getSelectedItem() == null) {
            // show error popup, because user didn't select reader
            PopUps.showErrorPopUp("Error", "You have to select reader, who you want to edit.");
            return;
        }
        // get selected reader from tableView and store it in SceneManager's selectedReader attribute
        SceneManager.selectedReader = readersTableView.getSelectionModel().getSelectedItem();
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.EDIT_READER_SCENE, true);
    }

    public void detailReader() {

    }

    public void searchReader() {
        // get all readers
        ArrayList<Reader> allReaders = SerializationPattern.getInstance().getSerializationObject().getAllReaders();
        // filtered readers
        ObservableList<Reader> filteredReaders = FXCollections.observableArrayList();

        // regex inspiration from https://www.w3schools.com/java/java_regex.asp
        Matcher matcher;
        Pattern pattern = Pattern.compile(tfSearch.getText(), Pattern.CASE_INSENSITIVE);

        for (Reader reader : allReaders) {
            if (this.getSearchFilter().equals("Name")) {
                // check if reader name contains something from entered string
                matcher = pattern.matcher(reader.getReadersCard().getReaderName());
            } else {
                // convert reader's ID to string and try to match with entered ID in search bar
                matcher = pattern.matcher(String.valueOf(reader.getReadersCard().getId()));
            }
            // if entered string matches or contains value for current borrowing, then add this reader to filteredReaders
            if (matcher.find()) {
                filteredReaders.add(reader);
            }
        }
        // display filtered readers in table view
        readersTableView.setItems(filteredReaders);
    }
}
