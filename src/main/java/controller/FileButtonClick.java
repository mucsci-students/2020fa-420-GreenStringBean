package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import view.*;

/**
 * FileButtonClick is a controller class for the GUI.
 * It contains the set of actions perfomed by a user in the view, 
 * and then sends the actions performed to another controller to 
 * manipulate the view accordingly.
 */

public class FileButtonClick implements ActionListener
{
	private MenuViews view;
	private HelperControllers controller;
	
	/**
     * Cronstructor for an action performer with the views input and a helper 
	 * controller to manipulate the view.
     * @param v  view of the GUI that takes in user input
     * @param c controller to help the view actions perform correctly
     */
	public FileButtonClick(MenuViews v, HelperControllers c)
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
		System.out.println("succesfully got to FileClick actions: FileClick()");
		
		String cmd = e.getActionCommand();
		if(cmd.equals("Undo"))
		{
			controller.undo();
		}
		else if(cmd.equals("Redo"))
		{
			controller.redo();
		}
		else if(cmd.equals("Zoom In"))
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
		else if(cmd.equals("Save"))
		{
			File file = view.getSaveFile();
            if(file == null)
            {
                return;
            }
            try
            {
				Files.writeString(file.toPath(), controller.save());
            }
            catch(IOException ex)
            {
                view.alert("Error saving file");
            }
		}
		else if(cmd.equals("Save As"))
		{
			File file = view.getSaveAsFile();
            if(file == null)
            {
                return;
            }
            try
            {
				Files.writeString(file.toPath(), controller.save());
            }
            catch(IOException ex)
            {
                view.alert("Error saving file");
            }
		}
		else if(cmd.equals("Load"))
		{
			File file = view.getLoadFile();
            if(file == null)
            {
                return;
            }
            try
            {
                controller.load(Files.readString(file.toPath()));
            }
            catch(IOException ex)
            {
                view.alert("Error loading File");
            }
		}
		else if(cmd.equals("Exit"))
		{
			System.exit(0);
		}
	}
}
