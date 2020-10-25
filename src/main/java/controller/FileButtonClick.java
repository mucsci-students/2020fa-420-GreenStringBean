package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JPanel;

import model.WorkingProject;
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
		if(cmd.equals("Save"))
		{
			String currFile = view.getText("Save ->");
			controller.save(currFile);
		}
		else if(cmd.equals("Load"))
		{
			String projectToLoad = view.getText("Load -> ");
			controller.load(projectToLoad);
		}
	}
}
