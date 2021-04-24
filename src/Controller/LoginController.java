package Controller;

import Controller.Reader.ReaderController;
import Model.Reader;
import PopUps.PopUps;
import Serialization.SerializationPattern;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class LoginController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private TextField tfUserName;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfReaderId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set items if scene contains ComboBox with languageComboBox id
        if (languageComboBox != null) {
            // ComboBox will have two items English and Slovak
            languageComboBox.setItems(
                    FXCollections.observableArrayList("English", "Slovak")
            );
        }
    }

    // on mouse click check if user name and user password are right and login as a Librarian
    public void loginAsLibrarian(MouseEvent event) {
        // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
        SceneManager.switchScene(event, SceneManager.BORROWINGS_SCENE, true);
    }

    // on mouse click check reader's ID and login as a reader
    public void loginAsReader(MouseEvent event) {
        // parameter librarianSceneFlag has to be false, because we are switching to one of the reader's scenes
        ArrayList<Reader> allReaders = SerializationPattern.getInstance().getSerializationObject().getAllReaders();
        for (Reader r : allReaders) {
            if (r.getReadersCard().getId() == Integer.parseInt(tfReaderId.getText().toString())){
                FXMLLoader fxmlLoader = SceneManager.switchSceneWithReturn(event, SceneManager.MY_BOOKS_SCENE, false);
                ReaderController readerController = fxmlLoader.getController();
                readerController.setCurrentReader(r);
                return;
            }
        }
        PopUps.showErrorPopUp("Error", "Invalid reader ID");
    }

    // this method quit whole program
    public void quit() {
        System.exit(0);
    }

    public void setLanguage(ActionEvent event) {
        ResourceBundle resourceBundle;
        String language = languageComboBox.getSelectionModel().getSelectedItem();

        // set localization and internationalization based on selected language in ComboBox
        if (language.equals("Slovak")) {
            Locale locale = new Locale("sk", "SK");
            resourceBundle = ResourceBundle.getBundle("Resources.slovak_sk_SK", locale);
        } else {
            Locale locale = new Locale("en", "UK");
            resourceBundle = ResourceBundle.getBundle("Resources.english_en_UK", locale);
        }
        // log info about switching language
        LOGGER.info("Changing language to " + language);
        // set ResourceBundle in SceneManager with new ResourceBundle with localization and internationalization
        SceneManager.resourceBundle = resourceBundle;
        // reload login scene to change language
        SceneManager.switchScene(event, SceneManager.LOGIN_SCENE);
    }
}
