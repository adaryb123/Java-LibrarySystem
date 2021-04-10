package Controller.Librarian;

import Controller.SceneManager;
import Model.Reader;
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

public class AllReadersController extends LibrarianController implements Initializable {

    @FXML
    private TableView<Reader> readersTableView;
    @FXML
    private TableColumn<Reader, String> idCol, nameCol, phoneNumberCol, emailCol,addressCol, reservedBooksCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // if scene contains TableView with this id then map all columns in it and display all readers
        if (readersTableView != null) {
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
        }
    }

    public void createReaderCard(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.CREATE_READER_SCENE, true);
    }

    public void detailReader(MouseEvent event) {

    }
}
