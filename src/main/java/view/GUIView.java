package view;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import controller.RightClickListenerFactory;

/**
 * The GUIView interface represents a view for a graphical UML editor.
 */
public interface GUIView extends View
{	
	/**
	* Mehtod for creating a window for the all the buttons and panels to be
	* displayed for user.
	*/
	void showWindow();

	/**
	 * Displays a dialog box for the user to fill in a string
	 * @param prompt the prompt given to the user
	 * @param title  the title of the window
	 * @return       the string input by the user, or null if canceled
	 */
	String promptForString(String prompt, String title);

	/**
	 * Displays a dialog box for the user to choose a visibility type
	 * @param prompt the prompt given to the user
	 * @param title  the title of the window
	 * @return       the visibility type chosen by the user, or null if canceled
	 */
	String promptForVis(String prompt, String title);

	/**
	 * Displays a dialog box for the user to choose a relationship type
	 * @param prompt the prompt given to the user
	 * @param title  the title of the window
	 * @return       the relationship type chosen by the user, or null if canceled
	 */
	String promptForRelType(String prompt, String title);

	/**
	 * Displays a dialog box for the user to choose a class name from the project
	 * @param prompt the prompt given to the user
	 * @param title  the title of the window
	 * @return       the class name chosen by the user, or null if canceled
	 */
	String promptForClassName(String prompt, String title);

	/**
	 * Displays a dialog box to fill in all the information for a new field
	 * @param title the window title of the dialog box
	 * @return      a map of the field's properties to their values, or null if canceled
	 */
	Map<String, String> promptForNewField(String title);

	/**
	* Displays a dialog box to fill in all the information for a new method
	* @param title the window title of the dialog box
	* @return      a map of the method's properties to their values, or null if canceled
	*/
	public Map<String, Object> promptForNewMethod(String title);

	/**
	 * Displays a dialog box to change the parameters of a method
	 * @param title the window title of the dialog box
	 * @return      a map of the method's properties to their values, or null if canceled
	 */
 	public Map<String, List<String>> promptForNewParamList(String title, List<String> oldParamNames, List<String> oldParamTypes);

	 /**
	  * Displays a dialog box to fill in all the information for a new relationship
	  * @param title the window title of the dialog box
	  * @return a map of the relationship's properties to their values, or null if canceled
	  */
	 public Map<String, String> promptForNewRelationship(String title);

	 /**
	  * Displays a dialog box to select a relationship, and optionally to change its type
	  * @param title         the window title of the dialog box
	  * @param promptForType if true, prompt for a new relationship type
	  * @return a map of the selected relationship's from and to classes
	  */
	 public Map<String, String> promptToModifyRelationship(String title, boolean promptForType);

	/**
	 * Returns the current file to save to if there is one, or displays a dialog box for the user to choose a file to save to
	 * @return the file to save to, or null if canceled
	 */
	File getSaveFile();

	/**
	 * Displays a dialog box for the user to choose a file to save to
	 * @return the file chosen by the user, or null if canceled
	 */
	File getSaveAsFile();

	/**
	 * Displays a dialog box for the user to choose a file to load
	 * @return the file chosen by the user, or null if canceled
	 */
	File getLoadFile();

	/**
	 * Connects listeners to the appropriate menu items, and stores a factory for when right click listeners are needed
	 * @param fileClick         a listener for the "File" menu items
	 * @param viewClick         a listener for the "View" menu items
	 * @param classClick        a listener or the "Project" menu items
	 * @param relationshipClick a listener for the "Relationship" menu items
	 * @param clickFactory      a factory to create right click listeners
	 */
	void addListeners(ActionListener fileClick, ActionListener viewClick, ActionListener classClick, ActionListener relationshipClick, RightClickListenerFactory clickFactory);

	/**
	 * Increases the size of elements on screen, with a maximum of 60pt font
	 * @return true if the size was increased, false if not
	 */
	boolean zoomIn();

	/**
	 * Decreases the size of elements on screen, with a minimum of 6pt font
	 * @return true if the size was increased, false if not
	 */
	boolean zoomOut();

	/**
	 * Switches dark mode on or off
	 */
	void toggleDarkMode();

	/**
	 * Ensures a panel is within the bounds of the window. If a panel does not
	 * fit in the window, it is kept on the left or top of the window.
	 * @param panel
	 */
	void contain(JPanel panel);

	/**
	 * Contain all panels within the window.
	 */
	void containAll();

	/**
	 * Refreshes the window, ensuring all elements are up to date
	 */
	void refresh();

	/**
	 * Creates a JSON string combining the state of the model with the state of the view
	 * @param jsonProjectString a JSON string encoding the state of the model
	 * @return                  a JSON string encoding the state of the view and model
	 */
	String toJSONString(String jsonProjectString);

	/**
	 * Loads a JSON string encoding the state of the view
	 * @param jsonString a JSON string encoding the state of the view
	 */
	void loadFromJSON(String jsonString);
}
