package Controller.Librarian;

import Controller.SceneManager;
import Model.Reader;
import PopUps.PopUps;
import Serialization.SerializationPattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class EditReaderController extends CreateReaderController implements Initializable {

    @FXML
    private TextField tfName, tfPhoneNumber, tfEmail, tfAddress;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // if scene contains TextFields with these ids tfName, tfPhoneNumber, tfEmail, tfAddress
        // then display data of selectedReader in them
        if (tfName != null && tfPhoneNumber != null && tfEmail != null && tfAddress != null) {
            Reader selectedReader = SceneManager.selectedReader;
            tfName.setText(selectedReader.getReadersCard().getReaderName());
            tfPhoneNumber.setText(selectedReader.getReadersCard().getReaderPhoneNumber());
            tfEmail.setText(selectedReader.getReadersCard().getReaderEmail());
            tfAddress.setText(selectedReader.getReadersCard().getReaderAddress());
        }
    }

    public void finishEditReader(MouseEvent event) {
        // continue only if user confirm saving of changes
        if (PopUps.showConfirmationPopUp("Confirmation", "Do you want to save changes?")) {
            // check if user filled all TextFields
            if (this.checkTextFieldsReader()) {
                // change attribute values in selectedReader based on data, which were filled by user in TextFields
                Reader selectedReader = SceneManager.selectedReader;
                selectedReader.getReadersCard().setReaderName(this.getName());
                selectedReader.getReadersCard().setReaderAddress(this.getAddress());
                selectedReader.getReadersCard().setReaderPhoneNumber(this.getPhoneNumber());
                selectedReader.getReadersCard().setReaderEmail(this.getEmail());
                // serialize changed data
                SerializationPattern.getInstance().serializeData();
                // show success popup to user
                PopUps.showSuccessPopUp("Success", "Reader was successfully edited.");
                // parameter librarianSceneFlag has to be true, because we are switching to one of the librarian's scenes
                SceneManager.switchScene(event, SceneManager.ALL_READERS_SCENE, true);
            }
        }
    }
}
