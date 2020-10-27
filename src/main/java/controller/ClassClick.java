package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.WorkingProject;
import view.MenuViews;

public class ClassClick implements ActionListener
{
	
	private MenuViews view;
	private HelperControllers controller;
	
	public ClassClick(MenuViews v, HelperControllers c)
	{
		this.view = v;
		this.controller = c;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("succesfully got to ClassClick actions: ClassClick()");
		
		String command = e.getActionCommand();
		if(command.equals("Open"))
		{
			String classOpen = view.getText("Class to open ->");
			controller.openClass(classOpen);
			controller.checkStatus();
		}
		else if(command.equals("Add"))
		{
			System.out.println("got to add class");
			String classToAdd = view.getText("Class -> ");
			controller.createClass(classToAdd);
			controller.checkStatus();
		}
		else if(command.equals("Delete"))
		{
			//get a list of classes and find className == classToDelete
			String classToDelete = view.getText("Class To Delete ->");
			controller.delClass(classToDelete);
			controller.checkStatus();
		}
		else if(command.equals("Rename"))
		{
			String renamed = view.getText("Class to be renamed ->"); 
			String newClassName = view.getText("New Class Name ->");
			controller.renameClass(renamed, newClassName);
			controller.checkStatus();
		}	
	}
}