package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.GUIView;

/**
 * FieldClick is a controller class for the GUI.
 * It contains the set of actions perfomed by a user in the view, 
 * and then sends the actions performed to another controller to 
 * manipulate the view accordingly.
 */

public class FieldRightClick implements ActionListener 
{
	private GUIView view;
    private GUIController controller;
    private String className;
    private String fieldName;
    
    /**
     * Cronstructor for an action performer with the views input and a helper 
	 * controller to manipulate the view.
     * @param v  view of the GUI that takes in user input
     * @param c controller to help the view actions perform correctly
     */
	public FieldRightClick(GUIView v, GUIController c, String className, String fieldName)
	{
		this.view = v;
        this.controller = c;
        this.className = className;
        this.fieldName = fieldName;
	}
    
    /**
	 * Method for handling actions performed on a field
	 * @param e is the action that was performed by the user 
	 * 			and is in the form of a command 
	 */
	public void actionPerformed(ActionEvent e) 
	{
		//System.out.println("succesfully got to FieldClick actions: FieldClick()");
		
		String cmd = e.getActionCommand();
        if (cmd.equals("Remove Field")) 
        {
            controller.removeField(className, fieldName);
        }
        else if (cmd.equals("Rename Field"))
        {
            String newFieldName = view.promptForString("New field name:", cmd);
            if (newFieldName == null)
            {
                return;
            }
            controller.renameField(className, fieldName, newFieldName);
        }
        else if (cmd.equals("Change Field Type"))
        {
            String newFieldType = view.promptForString("New data type:", cmd);
            if (newFieldType == null)
            {
                return;
            }
            controller.changeFieldType(className, fieldName, newFieldType);
        }
        else if (cmd.equals("Change Field Visibility"))
        {
            String newFieldVis = view.promptForVis("New visibility:", cmd);
            if (newFieldVis == null)
            {
                return;
            }
            controller.changeFieldVisibility(className, fieldName, newFieldVis);
        }
    }
}