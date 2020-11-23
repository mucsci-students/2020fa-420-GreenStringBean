package controller;
import java.awt.event.*;
import view.*;

public class RelationshipClick implements ActionListener
{
	private GUIView view;
	private GUIController controller;
	
	/**
     * Cronstructor for an action performer with the views input and a helper 
	 * controller to manipulate the view.
     * @param v  view of the GUI that takes in user input
     * @param c controller to help the view actions perform correctly
     */
	public RelationshipClick(GUIView v, GUIController c)
	{
		this.view = v;
		this.controller = c;
	}
	
	/**
	 * Method for handling actions performed on a relationship
	 * @param e is the action that was performed by the user 
	 * 			and is in the form of a command 
	 */
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();

		String classNameFrom = view.promptForClassName("\"From\" class:", cmd);
		if (classNameFrom == null)
		{
			return;
		}
		String classNameTo = view.promptForClassName("\"To\" class:", cmd);
		if (classNameTo == null)
		{
			return;
		}
		
        if (cmd.equals("Create Relationship"))
        {
			String typeName = view.promptForRelType("Relationship type:", cmd);
			if (typeName == null)
			{
				return;
			}
        	controller.addRelationship(classNameFrom, classNameTo, typeName);
        }
		
        else if (cmd.equals("Change Relationship Type")) 
        {
			String newTypeName = view.promptForRelType("New relationship type:", cmd);
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
