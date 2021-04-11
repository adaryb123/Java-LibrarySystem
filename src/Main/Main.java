package Main;

import Controller.LoginController;
import Controller.SceneManager;
import Serialization.SerializationPattern;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // deserialize data from library_data.ser before stage shows
        SerializationPattern.getInstance();
        // create fxmlLoader
        FXMLLoader fxmlLoader = new FXMLLoader();
        // set location to fxmlLoader
        fxmlLoader.setLocation(getClass().getResource("/View/LoginScene.fxml"));

        // default language will be english
        ResourceBundle resourceBundle = ResourceBundle.getBundle(
                "Resources.english_en_UK",
                new Locale("en", "UK")
        );
        // set default ResourceBundle in SceneManager abstract class
        SceneManager.resourceBundle = resourceBundle;
        // set resources with created ResourceBundle
        fxmlLoader.setResources(resourceBundle);

        // set LoginController as controller for LoginScreen
        LoginController controller = new LoginController();
        fxmlLoader.setController(controller);
        // Load fxmlLoader
        Parent root = fxmlLoader.load();
        // create new scene and set root
        Scene scene = new Scene(root);
        // scene.getStylesheets().add(getClass().getResource("/css/template.css").toExternalForm());

        primaryStage.setTitle("Library system");
        primaryStage.setScene(scene); // set created scene to primaryStage
        primaryStage.setResizable(false); // stage won't be resizeable
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
