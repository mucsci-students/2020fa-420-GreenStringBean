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
		//System.out.println("succesfully got to ClassClick actions: ClassClick()");
		
		String cmd = e.getActionCommand();
		if(cmd.equals("Add Class"))
		{
			String className = view.promptForString("Class name:", cmd);
			if (className == null)
			{
				return;
			}
			controller.createClass(className);
		}	
		else if(cmd.equals("Undo"))
		{
			controller.undo();
		}
		else if(cmd.equals("Redo"))
		{
			controller.redo();
		}
	}
}