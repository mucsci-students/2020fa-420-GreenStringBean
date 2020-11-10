package view;

import java.awt.event.ActionListener;
import java.io.File;

import model.Model;

public interface MenuViews extends Observer
{
	/**
	 * Gets file from user to be saved
	 */
	String getVis(String p, String t);

	/**
	 * Gets file from user to be saved
	 */
	String getClass(String p, String t);

	/**
	 * Gets file from user to be saved
	 */
	String getRelation(String p, String t);

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
	 * Gets user input from text box
	 */
	String getText(String prompt, String title);
	
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

	// Temporary until we can display arrows between class boxes
	void showRelationships(Model snapshot);
}
