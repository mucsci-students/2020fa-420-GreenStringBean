package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import view.*;

public class FileButtonClick implements ActionListener
{
	private MenuViews view;
	private HelperControllers controller;
	
	public FileButtonClick(MenuViews v, HelperControllers c)
	{
		this.view = v;
		this.controller = c;
	}
	
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
            catch(IOException error)
            {
                view.alert("Error Saving file");
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
            catch(IOException error)
            {
                view.alert("Error Saving file");
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
            catch(IOException error)
            {
                view.alert("Error Loading File");
            }
		}
		else if(cmd.equals("Exit"))
		{
			System.exit(0);
		}
	}
}
