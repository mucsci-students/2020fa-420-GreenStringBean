package view;

/**
 * The view interface represents any view that could be used to display a UML
 * editor to the user.
 */
public interface View extends Observer {
    /**
     * Displays a message to the user
     * @param message the message to display
     */
    void alert(String message);
}
