package Controller;

/**
 * this class holds a reference for the ControllerWindow class, so it can be accesses by its children
 */
public class WindowReference {

    public static ControllerWindow parentController;

    private WindowReference() {
    }

    public static ControllerWindow getParentController() {
        return parentController;
    }

    public static void setParentController(ControllerWindow parentController) {
        WindowReference.parentController = parentController;
    }
}