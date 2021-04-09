package Main;

import Controller.LoginController;
import Serialization.SerializationPattern;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // deserialize data from library_data.ser before stage shows
        SerializationPattern.getInstance();
        // create fxmlLoader
        FXMLLoader fxmlLoader = new FXMLLoader();
        // set location to fxmlLoader
        fxmlLoader.setLocation(getClass().getResource("/View/LoginScene.fxml"));

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
