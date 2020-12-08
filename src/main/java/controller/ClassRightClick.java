package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import view.GUIView;

public class ClassRightClick implements ActionListener
{
	private GUIView view;
    private GUIController controller;
    private String className;
	
	/**
     * Cronstructor for an action performer with the views input and a helper 
	 * controller to manipulate the view.
     * @param v  view of the GUI that takes in user input
     * @param c  controller to help the view actions perform correctly
     */
	public ClassRightClick(GUIView v, GUIController c, String s)
	{
		this.view = v;
        this.controller = c;
        this.className = s;
	}
	
	/**
	 * Method for handling actions performed on a class
	 * @param e is the action that was performed by the user 
	 * 			and is in the form of a command 
	 */
	public void actionPerformed(ActionEvent e)
	{	
		String cmd = e.getActionCommand();
		if(cmd.equals("Toggle Open"))
		{
			if (controller.getProjectSnapshot().hasClass(className) && controller.getProjectSnapshot().getClass(className).isOpen())
			{
				controller.closeClass(className);
			}
			else
			{
				controller.openClass(className);
			}
		}
		else if(cmd.equals("Remove Class"))
		{
			controller.removeClass(className);
		}
		else if(cmd.equals("Rename Class"))
		{
			String newClassName = view.promptForString("New class Name:", cmd);
			if (newClassName == null)
			{
				return;
			}
			controller.renameClass(className, newClassName);
        }
        else if(cmd.equals("Add Field")) 
        {    
            Map<String, String> fieldData = view.promptForNewField(cmd);
            if (fieldData == null)
            {
                return;
            }
            controller.addField(className, fieldData.get("Name"), fieldData.get("Type"), fieldData.get("Visibility"));
        }
        else if(cmd.equals("Add Method"))
        {
            Map<String, Object> methodData = view.promptForNewMethod(cmd);
            if (methodData == null)
            {
                return;
            }
            controller.addMethod(className, (String)methodData.get("Name"), (String)methodData.get("Type"), (String)methodData.get("Visibility"), (List<String>)methodData.get("ParamNames"), (List<String>)methodData.get("ParamTypes"));
        }	
	}
}