package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import Main.Main;
import javafx.stage.Stage;

/**
 * this class is based on code from https://www.youtube.com/watch?v=5yQbt6lYRqk
 * It loads the .fxml file of new scene/object when switching between scenes
 */
public class SceneManager {
    public static FXMLLoader switchBorderPane(BorderPane parentPane, String fileName){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource( "/View/" + fileName + ".fxml"));
        try {
            Pane childPane = fxmlLoader.load();
            parentPane.setCenter(childPane);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fxmlLoader;
    }

    public static void switchScene(ActionEvent actionEvent, String fileName) {
        // NOTE: Use loggers instead of sout in exceptions
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/" + fileName + ".fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene fxmlScene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(fxmlScene);
            window.show();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
	

   /* public static FXMLLoader switchListingPane(Pane parentPane, String fileName){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource( "/View/" + fileName + ".fxml"));
        try{
            Pane childPane = fxmlLoader.load();
            parentPane.getChildren().clear();
            parentPane.getChildren().add(childPane);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fxmlLoader;
    }*/
}
