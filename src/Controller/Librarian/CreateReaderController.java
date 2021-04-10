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

    public void createReader(MouseEvent event) {
        String name = tfName.getText();
        String phoneNumber = tfPhoneNumber.getText();
        String email = tfEmail.getText();
        String address = tfAddress.getText();

        // continue only if user fill each TextField
        if (name.equals("") || phoneNumber.equals("") || email.equals("") || address.equals("")) {
            PopUps.showErrorPopUp("Error", "Each text field has to be filled.");
            return;
        }

        // we get unique number for readers id, by getting the size of the all readers arraylist
        int uniqueId = SerializationPattern.getInstance().getSerializationObject().getAllReaders().size();
        // create reader card based on filled data
        ReadersCard readersCard = new ReadersCard(name, address, phoneNumber, email, uniqueId);

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
}
