package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.GUIView;

public class ClassClick implements ActionListener
{
	private GUIView view;
	private GUIEditorController controller;
	
	/**
     * Cronstructor for an action performer with the views input and a helper 
	 * controller to manipulate the view.
     * @param v  view of the GUI that takes in user input
     * @param c  controller to help the view actions perform correctly
     */
	public ClassClick(GUIView v, GUIEditorController c)
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
		String cmd = e.getActionCommand();
		if(cmd.equals("Add Class"))
		{
			String className = view.promptForString("Class name:", cmd);
			if (className == null)
			{
				return;
			}
			controller.addClass(className);
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