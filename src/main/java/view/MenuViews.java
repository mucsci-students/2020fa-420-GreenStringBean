package view;

import java.awt.event.ActionListener;
import java.io.File;

import model.WorkingProject;

public interface MenuViews extends Observer
{
	File getSaveFile();

	File getSaveAsFile();

	File getLoadFile();
	
	String getText(String prompt, String title);
	
	void alert(String message);

	void addListeners(ActionListener fileClick, ActionListener classClick,
			ActionListener fieldClick, ActionListener relationshipClick);	

	void start();

	// Temporary until we can display arrows between class boxes
	void showRelationships(WorkingProject snapshot);
}
