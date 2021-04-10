package Controller.Librarian;

import Controller.SceneManager;
import Model.Reader;
import PopUps.PopUps;
import Serialization.SerializationPattern;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateBorrowingController extends LibrarianController implements Initializable {

    @FXML
    private TableView<Reader> readersTableView;
    @FXML
    private TableColumn<Reader, String> idCol, nameCol, phoneNumberCol, emailCol, addressCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (readersTableView != null) {
            this.mapColumnsOnReaderMethods(idCol, nameCol, phoneNumberCol, emailCol, addressCol);
            // get all readers objects from SerializationPattern singleton
            ArrayList<Reader> allReaders = SerializationPattern.getInstance().getSerializationObject().getAllReaders();
            // before setting items to readersTableView, casts allReaders arraylist to observable list
            readersTableView.setItems(FXCollections.observableArrayList(allReaders));
        }
    }

    public void chooseBooksForBorrowing(MouseEvent event) {
        // check if user selected reader, because borrowing will be associated to this reader
        if (readersTableView.getSelectionModel().getSelectedItem() == null) {
            PopUps.showErrorPopUp("Select reader", "You have to select reader to create borrowing.");
            return;
        }
        // set selectedReader in SceneManager for better access in next scene
        SceneManager.selectedReader = readersTableView.getSelectionModel().getSelectedItem();
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.CHOOSE_BOOKS_BORROWING_SCENE, true);
    }
}
