package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import view.*;

public class ViewButtonClick implements ActionListener
{
	private GUIView view;
	private GUIController controller;
	
	/**
     * Cronstructor for an action performer with the views input and a helper 
	 * controller to manipulate the view.
     * @param v  view of the GUI that takes in user input
     * @param c controller to help the view actions perform correctly
     */
	public ViewButtonClick(GUIView v, GUIController c)
	{
		this.view = v;
		this.controller = c;
	}
	
	/**
	 * Method for handling actions performed on a file
	 * @param e is the action that was performed by the user 
	 * 			and is in the form of a command 
	 */
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		if(cmd.equals("Zoom In"))
		{
			if (view.zoomIn())
			{
				view.onUpdate(controller.getProjectSnapshot(), false);
			}
		}
		else if(cmd.equals("Zoom Out"))
		{
			if (view.zoomOut())
			{
				view.onUpdate(controller.getProjectSnapshot(), false);
			}
		}
		else if (cmd.equals("Dark Mode"))
		{
			view.toggleDarkMode();
			view.onUpdate(controller.getProjectSnapshot(), false);
		}
	}
}
