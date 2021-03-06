package Controller;
import Controller.Librarian.*;
import Controller.Reader.MyBooksController;
import Controller.Reader.SearchBooksController;
import Controller.Reader.ViewReviewsController;
import Controller.Reader.WriteReviewController;
import Model.BookCopy;
import Model.BookTitle;
import Model.BorrowingRecord;
import Model.Reader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import Main.Main;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is based on code from https://www.youtube.com/watch?v=5yQbt6lYRqk .
 * It loads the .fxml file of new scene when switching between scenes.
 * Class is abstract, because we don't need to create an instance of it.
 */
public abstract class SceneManager {

    // login constant
    public static final String LOGIN_SCENE = "LoginScene";

    // constants for Librarian's scene files
    public static final String BORROWINGS_SCENE = "BorrowingsScene";
    public static final String BOOKS_EVIDENCE_SCENE = "BooksEvidenceScene";
    public static final String ALL_READERS_SCENE = "AllReadersScene";
    public static final String CREATE_BORROWING_SCENE = "CreateBorrowingScene";
    public static final String CHOOSE_BOOKS_BORROWING_SCENE = "ChooseBooksBorrowingScene";
    public static final String CREATE_READER_SCENE = "CreateReaderScene";
    public static final String CREATE_BOOK_TITLE_SCENE = "CreateBookTitleScene";
    public static final String EDIT_BOOK_TITLE_SCENE = "EditBookTitleScene";
    public static final String EDIT_READER_SCENE = "EditReaderScene";
    public static final String DETAIL_BORROWING_SCENE = "DetailBorrowingScene";
    public static final String MY_BOOKS_SCENE = "MyBooksScene";
    public static final String SEARCH_BOOKS_SCENE = "SearchBooksScene";
    public static final String VIEW_REVIEWS_SCENE = "ViewReviewsScene";
    public static final String ADD_REVIEW_SCENE = "WriteReviewScene";

    // this reader is needed when creating new borrowing and on reader detail/edit
    public static Reader selectedReader = null;
    // this book title is needed for editing selected book title in EditBookTitleScene
    public static BookTitle selectedBookTitle = null;
    // this borrowing record is needed for displaying it's detail in DetailBorrowingScene
    public static BorrowingRecord selectedBorrowingRecord = null;
    // current reader that is logged in
    public static Reader currentReader = null;
    // selected book title in readers screen
    public static BookTitle selectedBookTitleReader = null;

    // each scene will have ResourceBundle for localization and internationalization
    public static ResourceBundle resourceBundle;

    // logger for this class
    private static final Logger LOGGER = Logger.getLogger(SceneManager.class.getName());

    // basic switching scene on mouse click
    public static void switchScene(MouseEvent event, String fileName) {
        // set localization and language by resourceBundle when creating a new FXMLLoader
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/" + fileName + ".fxml"), resourceBundle);
        // get stage before makeSwitch method
        // otherwise we will have to make more overloading
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.makeSwitch(fxmlLoader, window, fileName);
    }

    // this overloading is needed, when user is changing languages on login screen
    public static void switchScene(ActionEvent event, String fileName) {
        // set localization and language by resourceBundle when creating a new FXMLLoader
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/" + fileName + ".fxml"), resourceBundle);
        // get stage before makeSwitch method
        // otherwise we will have to make more overloading
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.makeSwitch(fxmlLoader, window, fileName);
    }

    // overload switchScene method, when SceneManager switching between Librarian's or Reader's scenes
    public static void switchScene(MouseEvent event, String fileName, boolean librarianSceneFlag) {
        FXMLLoader fxmlLoader;
        // if this condition is true, stage will be switching only librarian's scenes
        // otherwise it will be switching only reader's scenes.
        // there is important difference in path, which goes to getResource method
        // localization and language will be set by resourceBundle when creating a new FXMLLoader
        if (librarianSceneFlag) {
            fxmlLoader = new FXMLLoader(Main.class.getResource("/View/Librarian/" + fileName + ".fxml"), resourceBundle);
        } else {
            fxmlLoader = new FXMLLoader(Main.class.getResource("/View/Reader/" + fileName + ".fxml"), resourceBundle);
        }

        // get stage before makeSwitch method
        // otherwise we will have to make more overloading
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.makeSwitch(fxmlLoader, window, fileName);
    }

    //for loading dynamic review pane in ViewReviewsScene
    public static FXMLLoader switchListingPane(Pane parentPane, String fileName,boolean librarianSceneFlag){
        FXMLLoader fxmlLoader;
        // if this condition is true, stage will be switching only librarian's scenes
        // otherwise it will be switching only reader's scenes.
        // there is important difference in path, which goes to getResource method
        // localization and language will be set by resourceBundle when creating a new FXMLLoader
        if (librarianSceneFlag) {
            fxmlLoader = new FXMLLoader(Main.class.getResource("/View/Librarian/" + fileName + ".fxml"), resourceBundle);
        } else {
            fxmlLoader = new FXMLLoader(Main.class.getResource("/View/Reader/" + fileName + ".fxml"), resourceBundle);
        }
        try{
            Pane childPane = fxmlLoader.load();
            parentPane.getChildren().clear();
            parentPane.getChildren().add(childPane);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fxmlLoader;
    }

    // make switch scene
    public static void makeSwitch(FXMLLoader fxmlLoader, Stage window, String fileName) {
        try {
            // set scene controller before switching to new scene in stage
            // scene controller will be return from getController method based on fileName parameter
            fxmlLoader.setController(SceneManager.getController(fileName));
            Parent root = fxmlLoader.load();
            Scene fxmlScene = new Scene(root);
            window.setScene(fxmlScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace(); // Remove this line, when program is finished
            LOGGER.log(Level.SEVERE, "Something went wrong during switching scene with name: " + fileName + "\n"
                    + "Exception message: " + e.getMessage());
        }
    }

    // this method returns proper controller by fileName for scene
    public static Object getController(String fileName) {
        switch (fileName) {
            case LOGIN_SCENE:
                return new LoginController();
            case BORROWINGS_SCENE:
                return new BorrowingsController();
            case BOOKS_EVIDENCE_SCENE:
                return new BooksEvidenceController();
            case ALL_READERS_SCENE:
                return new AllReadersController();
            case CREATE_BORROWING_SCENE:
                return new CreateBorrowingController();
            case CHOOSE_BOOKS_BORROWING_SCENE:
                return new ChooseBooksBorrowingController();
            case CREATE_READER_SCENE:
                return new CreateReaderController();
            case CREATE_BOOK_TITLE_SCENE:
                return new CreateBookTitleController();
            case EDIT_BOOK_TITLE_SCENE:
                return new EditBookTitleController();
            case EDIT_READER_SCENE:
                return new EditReaderController();
            case DETAIL_BORROWING_SCENE:
                return new DetailBorrowingController();
            case MY_BOOKS_SCENE:
                return new MyBooksController();
            case SEARCH_BOOKS_SCENE:
                return new SearchBooksController();
            case VIEW_REVIEWS_SCENE:
                return new ViewReviewsController();
            case ADD_REVIEW_SCENE:
                return new WriteReviewController();
        }

        return null;
    }
}
