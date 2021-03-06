package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import view.*;

public class FileButtonClick implements ActionListener
{
	private GUIView view;
	private GUIController controller;
	
	/**
     * Cronstructor for an action performer with the views input and a helper 
	 * controller to manipulate the view.
     * @param v  view of the GUI that takes in user input
     * @param c controller to help the view actions perform correctly
     */
	public FileButtonClick(GUIView v, GUIController c)
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
        if (cmd.equals("New"))
        {
            controller.clearProject();
        }
		if(cmd.equals("Save"))
		{
			File file = view.getSaveFile();
            if(file == null)
            {
                return;
            }
            try
            {
				Files.writeString(file.toPath(), controller.toJSONString());
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
				Files.writeString(file.toPath(), controller.toJSONString());
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
                controller.loadProject(Files.readString(file.toPath()));
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
