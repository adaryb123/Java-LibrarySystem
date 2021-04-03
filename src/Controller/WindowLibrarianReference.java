package Controller;

/**
 * this class holds a reference for the ControllerWindow class, so it can be accesses by its children
 */
public class WindowLibrarianReference {

    public static ControllerWindowLibrarian parentController;

    private WindowLibrarianReference() {
    }

    public static ControllerWindowLibrarian getParentController() {
        return parentController;
    }

    public static void setParentController(ControllerWindowLibrarian parentController) {
        WindowLibrarianReference.parentController = parentController;
    }
}