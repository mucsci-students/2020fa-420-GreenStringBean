package controller;

import java.awt.event.*;
import java.util.Map;

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
		
        if (cmd.equals("Create Relationship"))
        {
			Map<String, String> relationshipData = view.promptForNewRelationship(cmd);
			if (relationshipData == null)
			{
				return;
			}
        	controller.addRelationship(relationshipData.get("From"), relationshipData.get("To"), relationshipData.get("Type").substring(0, 1));
        }
		
        else if (cmd.equals("Change Relationship Type")) 
        {
			Map<String, String> relationshipData = view.promptToModifyRelationship(cmd, true);
			if (relationshipData == null)
			{
				return;
			}
			controller.changeRelationshipType(relationshipData.get("From"), relationshipData.get("To"), relationshipData.get("Type").substring(0, 1));
		}
		else if (cmd.equals("Remove Relationship"))
		{
			Map<String, String> relationshipData = view.promptToModifyRelationship(cmd, false);
			if (relationshipData == null)
			{
				return;
			}
			controller.removeRelationship(relationshipData.get("From"), relationshipData.get("To"));
		}
	}
}
