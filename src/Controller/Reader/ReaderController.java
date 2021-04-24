package Controller.Reader;

import Controller.SceneManager;
import Model.Reader;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ReaderController implements Initializable {

    private Reader currentReader;

    public void setCurrentReader(Reader reader){

    }

    public Reader getCurrentReader(){
        return currentReader;
    }

    public void readerMyBooks(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event ,SceneManager.MY_BOOKS_SCENE, false);
    }

    public void readerSearchBooks(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.SEARCH_BOOKS_SCENE, false);
    }

    public void logout(MouseEvent event) {
        // switching back to login scene
        SceneManager.switchScene(event, SceneManager.LOGIN_SCENE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

  /*  public void mapColumnsOnReaderMethods(
            TableColumn<Reader, String> idCol,
            TableColumn<Reader, String> nameCol,
            TableColumn<Reader, String> phoneNumberCol,
            TableColumn<Reader, String> emailCol,
            TableColumn<Reader, String> addressCol
    ) {
        // inspiration https://stackoverflow.com/questions/25204068/how-do-i-point-a-propertyvaluefactory-to-a-value-of-a-map
        idCol.setCellValueFactory(
                // get id on reader's card
                data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getReadersCard().getId()))
        );
        nameCol.setCellValueFactory(
                // get reader's name
                data -> new ReadOnlyStringWrapper(data.getValue().getReadersCard().getReaderName())
        );
        phoneNumberCol.setCellValueFactory(
                // get reader's phone
                data -> new ReadOnlyStringWrapper(data.getValue().getReadersCard().getReaderPhoneNumber())
        );
        emailCol.setCellValueFactory(
                // get reader's email
                data -> new ReadOnlyStringWrapper(data.getValue().getReadersCard().getReaderEmail())
        );
        addressCol.setCellValueFactory(
                // get reader's address
                data -> new ReadOnlyStringWrapper(data.getValue().getReadersCard().getReaderAddress())
        );
    }*/
}
