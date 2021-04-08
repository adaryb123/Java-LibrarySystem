package Controller;

/**
 * this class holds a reference for the ControllerWindow class, so it can be accesses by its children
 */
public class WindowLibrarianReference {

    public static LibrarianController parentController;

    private WindowLibrarianReference() {
    }

    public static LibrarianController getParentController() {
        return parentController;
    }

    public static void setParentController(LibrarianController parentController) {
        WindowLibrarianReference.parentController = parentController;
    }
}