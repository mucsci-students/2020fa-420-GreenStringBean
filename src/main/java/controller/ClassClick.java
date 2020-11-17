package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MenuViews;

/**
 * ClassClick is a controller class for the GUI.
 * It contains the set of actions perfomed by a user in the view, 
 * and then sends the actions performed to another controller to 
 * manipulate the view accordingly.
 */

public class ClassClick implements ActionListener
{
	private MenuViews view;
	private HelperControllers controller;
	
	/**
     * Cronstructor for an action performer with the views input and a helper 
	 * controller to manipulate the view.
     * @param v  view of the GUI that takes in user input
     * @param c controller to help the view actions perform correctly
     */
	public ClassClick(MenuViews v, HelperControllers c)
	{
		this.view = v;
		this.controller = c;
	}
	
	/**
	 * Method for handling actions performed on a class
	 * @param e is the action that was performed by the user 
	 * 			and is in the form of a command 
	 */
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("succesfully got to ClassClick actions: ClassClick()");
		
		String cmd = e.getActionCommand();
		if(cmd.equals("Open"))
		{
			String className = view.promptForString("Name of class to open:", cmd);
			if (className == null)
			{
				return;
			}
			controller.openClass(className);
		}
		if(cmd.equals("Close"))
		{
			String className = view.promptForString("Name of class to close:", cmd);
			if (className == null)
			{
				return;
			}
			controller.closeClass(className);
		}
		else if(cmd.equals("Add Class"))
		{
			String className = view.promptForString("Class name:", cmd);
			if (className == null)
			{
				return;
			}
			controller.createClass(className);
		}
		else if(cmd.equals("Remove Class"))
		{
			// TODO select a class from the list instead of raw input
			String className = view.promptForString("Name of class to remove:", cmd);
			if (className == null)
			{
				return;
			}
			controller.delClass(className);
		}
		else if(cmd.equals("Rename Class"))
		{
			// TODO select a class from the list instead of raw input
			String oldClassName = view.promptForString("Old class name:", cmd);
			if (oldClassName == null)
			{
				return;
			}
			String newClassName = view.promptForString("New class Name:", cmd);
			if (newClassName == null)
			{
				return;
			}
			controller.renameClass(oldClassName, newClassName);
		}	
	}
}