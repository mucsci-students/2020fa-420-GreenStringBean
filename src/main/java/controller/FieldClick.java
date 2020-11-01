package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MenuViews;

/**
 * FieldClick is a controller class for the GUI.
 * It contains the set of actions perfomed by a user in the view, 
 * and then sends the actions performed to another controller to 
 * manipulate the view accordingly.
 */

public class FieldClick implements ActionListener 
{
	private MenuViews view;
	private HelperControllers controller;
    
    /**
     * Cronstructor for an action performer with the views input and a helper 
	 * controller to manipulate the view.
     * @param v  view of the GUI that takes in user input
     * @param c controller to help the view actions perform correctly
     */
	public FieldClick(MenuViews v, HelperControllers c)
	{
		this.view = v;
		this.controller = c;
	}
    
    /**
	 * Method for handling actions performed on a field
	 * @param e is the action that was performed by the user 
	 * 			and is in the form of a command 
	 */
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("succesfully got to FieldClick actions: FieldClick()");
		
		String cmd = e.getActionCommand();
        String className = view.getText("Name of class to modify:", cmd);
        if (className == null)
        {
            return;
        }
		//Fields
        if (cmd.equals("Add Field")) 
        {
            // TODO Choose from options instead of raw input
            String fieldVis = view.getText("Visibility (public / protected / private):", cmd);
            if (fieldVis == null)
            {
                return;
            }
            String fieldType = view.getText("Data type:", cmd);
            if (fieldType == null)
            {
                return;
            }
            String fieldName = view.getText("Field name:", cmd);
            if (fieldName == null)
            {
                return;
            }
            controller.addField(className, fieldName, fieldType, fieldVis);
        } 
        else if (cmd.equals("Remove Field")) 
        {
            String fieldName = view.getText("Name of field to remove:", cmd);
            if (fieldName == null)
            {
                return;
            }
            controller.removeField(className, fieldName);
        }
        else if (cmd.equals("Rename Field"))
        {
            String oldFieldName = view.getText("Old field name:", cmd);
            if (oldFieldName == null)
            {
                return;
            }
            String newFieldName = view.getText("New field name:", cmd);
            if (newFieldName == null)
            {
                return;
            }
            controller.renameField(className, oldFieldName, newFieldName);
        }
        else if (cmd.equals("Change Field Type"))
        {
            String fieldName = view.getText("Name of field to modify:", cmd);
            if (fieldName == null)
            {
                return;
            }
            String newFieldType = view.getText("New data type:", cmd);
            if (newFieldType == null)
            {
                return;
            }
            controller.changeFieldType(className, fieldName, newFieldType);
        }
        else if (cmd.equals("Change Field Visibility"))
        {
            String fieldName = view.getText("Name of field to modify:", cmd);
            if (fieldName == null)
            {
                return;
            }
            String newFieldVis = view.getText("New visibility (public / protected / private):", cmd);
            if (newFieldVis == null)
            {
                return;
            }
            controller.changeFieldVisiblity(className, fieldName, newFieldVis);;
        }

        //Methods
        else if (cmd.equals("Add Method"))
        {
            String methodVis = view.getText("Visibility (public / protected / private):", cmd);
            if (methodVis == null)
            {
                return;
            }
            String methodType = view.getText("Return type:", cmd);
            if (methodType == null)
            {
                return;
            }
            String methodName = view.getText("Method name:", cmd);
            if (methodName == null)
            {
                return;
            }
            controller.addMethod(className, methodName, methodType, methodVis);
        }
        else if (cmd.equals("Remove Method"))
        {
            String methodName = view.getText("Name of method to remove:", cmd);
            if (methodName == null)
            {
                return;
            }
            controller.removeMethod(className, methodName);
        }
        else if (cmd.equals("Rename Method"))
        {
            String oldMethodName = view.getText("Old method name:", cmd);
            if (oldMethodName == null)
            {
                return;
            }
            String newMethodName = view.getText("New method name:", cmd);
            if (newMethodName == null)
            {
                return;
            }
            controller.renameMethod(className, oldMethodName, newMethodName);
        }
        else if (cmd.equals("Change Method Type"))
        {
            String methodName = view.getText("Name of method to modify:", cmd);
            if (methodName == null)
            {
                return;
            }
            String newMethodType = view.getText("New return type:", cmd);
            if (newMethodType == null)
            {
                return;
            }
            controller.changeMethodType(className, methodName, newMethodType);
        }
        else if (cmd.equals("Change Method Visibility"))
        {
            String methodName = view.getText("Name of method to modify:", cmd);
            if (methodName == null)
            {
                return;
            }
            String newMethodVis = view.getText("New visibility (public / protected / private):", cmd);
            if (newMethodVis == null)
            {
                return;
            }
            controller.changeMethodVisiblity(className, methodName, newMethodVis);
        }
        else
        {
            //Params
            String methodName = view.getText("Name of method to modify:", cmd);
            if (methodName == null)
            {
                return;
            }
            if (cmd.equals("Add Parameter"))
            {
                String paramName = view.getText("Parameter name:", cmd);
                if (paramName == null)
                {
                    return;
                }
                String paramType = view.getText("Data type:", cmd);
                if (paramType == null)
                {
                    return;
                }
                controller.addParameter(className, methodName, paramName, paramType);
            }
            else if (cmd.equals("Remove Parameter"))
            {
                String paramName = view.getText("Name of parameter to remove:", cmd);
                if (paramName == null)
                {
                    return;
                }
                controller.removeParameter(className, methodName, paramName);
            }
            else if (cmd.equals("Rename Parameter"))
            {
                String oldParamName = view.getText("Old parameter name:", cmd);
                if (oldParamName == null)
                {
                    return;
                }
                String newParamName = view.getText("New parameter name:", cmd);
                if (newParamName == null)
                {
                    return;
                }
                controller.renameParameter(className, methodName, oldParamName, newParamName);
            }
            else if (cmd.equals("Change Parameter Type"))
            {
                String paramName = view.getText("Name of parameter to modify:", cmd);
                if (paramName == null)
                {
                    return;
                }
                String newParamType = view.getText("New data type:", cmd);
                if (newParamType == null)
                {
                    return;
                }
                controller.changeParameterType(className, methodName, paramName, newParamType);
            }
        }
	}
}
