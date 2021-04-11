package Controller.Librarian;

import Controller.SceneManager;
import Model.Reader;
import Model.ReadersCard;
import PopUps.PopUps;
import Serialization.SerializationPattern;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CreateReaderController extends LibrarianController {

    @FXML
    private TextField tfName, tfPhoneNumber, tfEmail, tfAddress;

    private String name, phoneNumber, email, address;

    public void createReader(MouseEvent event) {
        // continue only if user fill each TextField
        if (!this.checkTextFieldsReader()) {
            return;
        }

        // we get unique number for readers id, by getting the size of the all readers arraylist
        int uniqueId = SerializationPattern.getInstance().getSerializationObject().getAllReaders().size();
        // create reader card based on filled data
        ReadersCard readersCard = new ReadersCard(
                this.name,
                this.address,
                this.phoneNumber,
                this.email,
                uniqueId
        );

        // create new reader with created reader card
        // add reader to all readers arraylist and serialize data
        SerializationPattern.getInstance().addNewReader(
                new Reader(readersCard)
        );

        // if user was successfully created show success popup
        PopUps.showSuccessPopUp("Success", "Reader was successfully created.");
        // after pop up switch back to all readers scene
        SceneManager.switchScene(event, SceneManager.ALL_READERS_SCENE, true);
    }

    public boolean checkTextFieldsReader() {
        this.name = tfName.getText();
        this.phoneNumber = tfPhoneNumber.getText();
        this.email = tfEmail.getText();
        this.address = tfAddress.getText();

        // continue only if user fill each TextField
        if (this.name.equals("") || this.phoneNumber.equals("") || this.email.equals("") || this.address.equals("")) {
            PopUps.showErrorPopUp("Error", "Each text field has to be filled.");
            return false;
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
