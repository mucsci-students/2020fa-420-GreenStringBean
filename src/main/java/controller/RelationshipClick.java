package controller;
import java.awt.event.*;
import view.*;

/**
 * RelationshipClick is a controller class for the GUI.
 * It contains the set of actions perfomed by a user in the view, 
 * and then sends the actions performed to another controller to 
 * manipulate the view accordingly.
 */

public class RelationshipClick implements ActionListener
{
	private MenuViews view;
	private HelperControllers controller;
	
	/**
     * Cronstructor for an action performer with the views input and a helper 
	 * controller to manipulate the view.
     * @param v  view of the GUI that takes in user input
     * @param c controller to help the view actions perform correctly
     */
	public RelationshipClick(MenuViews v, HelperControllers c)
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
		System.out.println("succesfully got to RelationshipClick actions: RelationshipClick()");
		String cmd = e.getActionCommand();

		// Temporary until we can display arrows between class boxes
		if (cmd.equals("Show Relationships"))
		{
			controller.showRelationships();
			return;
		}

		String classNameFrom = view.getText("\"From\" class:", cmd);
		if (classNameFrom == null)
		{
			return;
		}
		String classNameTo = view.getText("\"To\" class:", cmd);
		if (classNameTo == null)
		{
			return;
		}
		
        if (cmd.equals("Add Inheritance"))
        {
        	controller.addRelationship(classNameFrom, classNameTo, "I");
        }
		
        else if (cmd.equals("Add Aggregation"))
        {
        	controller.addRelationship(classNameFrom, classNameTo, "A");
        }
		
        else if (cmd.equals("Add Composition"))
        {
        	controller.addRelationship(classNameFrom, classNameTo, "C");
        }
		
        else if (cmd.equals("Add Realization"))
        {
        	controller.addRelationship(classNameFrom, classNameTo, "R");
		}
		
        else if (cmd.equals("Change Relationship Type")) 
        {
            // TODO Choose from options instead of raw input
			String newTypeName = view.getText("New relationship type (I / A / C / R):", cmd);
			if (newTypeName == null)
			{
				return;
			}
			controller.changeRelationshipType(classNameFrom, classNameTo, newTypeName);
		}
		else if (cmd.equals("Remove Relationship"))
		{
			controller.removeRelationship(classNameFrom, classNameTo);
		}
	}
}
