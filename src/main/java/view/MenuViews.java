package view;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JPanel;

import model.Model;

public interface MenuViews extends Observer
{
	/**
	 * Gets user input from text box
	 */
	String promptForString(String prompt, String title);

	/**
	 * Gets file from user to be saved
	 */
	String promptForVis(String p, String t);

	/**
	 * Gets file from user to be saved
	 */
	String promptForRelType(String p, String t);

	/**
	 * Gets file from user to be saved
	 */
	String promptForClassName(String p, String t);

	/**
	 * Gets file from user to be saved
	 */
	File getSaveFile();

	/**
	 * Gets the name of the file from user to be saved
	 */
	File getSaveAsFile();

	/**
	 * Gets file from user that is to be loaded 
	 */
	File getLoadFile();
	
	/**
	 * Sends an alert message to user when an ilegal action is performed
	 */
	void alert(String message);

	/**
	 * Add action listeners to there respected menu options
	 */
	void addListeners(ActionListener fileClick, ActionListener classClick,
			ActionListener fieldClick, ActionListener relationshipClick);	

	/**
	 * Starts the GUI with a clean window
	 */
	void start();

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
	 * Ensures a panel is within the bounds of the window. If a panel does not
	 * fit in the window, it is kept on the left or top of the window.
	 * @param panel
	 */
	void contain(JPanel panel);

	/**
	 * Contain all panels within the window.
	 */
	void containAll();

	void refresh();

	// Temporary until we can display arrows between class boxes
	void showRelationships(Model snapshot);
}
