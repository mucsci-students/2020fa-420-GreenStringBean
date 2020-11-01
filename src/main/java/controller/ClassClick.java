package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		
		String cmd = e.getActionCommand();
		if(cmd.equals("Open"))
		{
			String className = view.getText("Name of class to open:", cmd);
			if (className == null)
			{
				return;
			}
			controller.openClass(className);
		}
		if(cmd.equals("Close"))
		{
			String className = view.getText("Name of class to close:", cmd);
			if (className == null)
			{
				return;
			}
			controller.closeClass(className);
		}
		else if(cmd.equals("Add Class"))
		{
			String className = view.getText("Class name:", cmd);
			if (className == null)
			{
				return;
			}
			controller.createClass(className);
		}
		else if(cmd.equals("Remove Class"))
		{
			// TODO select a class from the list instead of raw input
			String className = view.getText("Name of class to remove:", cmd);
			if (className == null)
			{
				return;
			}
			controller.delClass(className);
		}
		else if(cmd.equals("Rename Class"))
		{
			// TODO select a class from the list instead of raw input
			String oldClassName = view.getText("Old class name:", cmd);
			if (oldClassName == null)
			{
				return;
			}
			String newClassName = view.getText("New class Name:", cmd);
			if (newClassName == null)
			{
				return;
			}
			controller.renameClass(oldClassName, newClassName);
		}	
	}
}