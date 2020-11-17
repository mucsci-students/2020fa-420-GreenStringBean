package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MenuViews;

public class MethodRightClick implements ActionListener
{
    private MenuViews view;
    private HelperControllers controller;
    private String className;
    private String methodName;
    
    /**
     * Cronstructor for an action performer with the views input and a helper 
	 * controller to manipulate the view.
     * @param v  view of the GUI that takes in user input
     * @param c controller to help the view actions perform correctly
     */
	public MethodRightClick(MenuViews v, HelperControllers c, String className, String methodName)
	{
		this.view = v;
        this.controller = c;
        this.className = className;
        this.methodName = methodName;
    }

    public void actionPerformed(ActionEvent e) 
	{	
		String cmd = e.getActionCommand();
        if (cmd.equals("Remove Method"))
        {
            controller.removeMethod(className, methodName);
        }
        else if (cmd.equals("Rename Method"))
        {
            String newMethodName = view.promptForString("New method name:", cmd);
            if (newMethodName == null)
            {
                return;
            }
            controller.renameMethod(className, methodName, newMethodName);
        }
        else if (cmd.equals("Change Method Type"))
        {
            String newMethodType = view.promptForString("New return type:", cmd);
            if (newMethodType == null)
            {
                return;
            }
            controller.changeMethodType(className, methodName, newMethodType);
        }
        else if (cmd.equals("Change Method Visibility"))
        {
            String newMethodVis = view.promptForVis("New visibility:", cmd);
            if (newMethodVis == null)
            {
                return;
            }
            controller.changeMethodVisiblity(className, methodName, newMethodVis);
        }
        else
        {
            if (cmd.equals("Add Parameter"))
            {
                String paramName = view.promptForString("Parameter name:", cmd);
                if (paramName == null)
                {
                    return;
                }
                String paramType = view.promptForString("Data type:", cmd);
                if (paramType == null)
                {
                    return;
                }
                controller.addParameter(className, methodName, paramName, paramType);
            }
            else if (cmd.equals("Remove Parameter"))
            {
                String paramName = view.promptForString("Name of parameter to remove:", cmd);
                if (paramName == null)
                {
                    return;
                }
                controller.removeParameter(className, methodName, paramName);
            }
            else if (cmd.equals("Rename Parameter"))
            {
                String oldParamName = view.promptForString("Old parameter name:", cmd);
                if (oldParamName == null)
                {
                    return;
                }
                String newParamName = view.promptForString("New parameter name:", cmd);
                if (newParamName == null)
                {
                    return;
                }
                controller.renameParameter(className, methodName, oldParamName, newParamName);
            }
            else if (cmd.equals("Change Parameter Type"))
            {
                String paramName = view.promptForString("Name of parameter to modify:", cmd);
                if (paramName == null)
                {
                    return;
                }
                String newParamType = view.promptForString("New data type:", cmd);
                if (newParamType == null)
                {
                    return;
                }
                controller.changeParameterType(className, methodName, paramName, newParamType);
            }
        }
	}
}
