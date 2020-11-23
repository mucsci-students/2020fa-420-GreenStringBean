package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import view.GUIView;
import model.Parameter;

public class MethodRightClick implements ActionListener
{
    private GUIView view;
    private GUIController controller;
    private String className;
    private String methodName;
    
    /**
     * Cronstructor for an action performer with the views input and a helper 
	 * controller to manipulate the view.
     * @param v  view of the GUI that takes in user input
     * @param c controller to help the view actions perform correctly
     */
	public MethodRightClick(GUIView v, GUIController c, String className, String methodName)
	{
		this.view = v;
        this.controller = c;
        this.className = className;
        this.methodName = methodName;
    }

    /**
	 * Method for handling actions performed on a method
	 * @param e is the action that was performed by the user 
	 * 			and is in the form of a command 
	 */
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
            controller.changeMethodVisibility(className, methodName, newMethodVis);
        }
        else if (cmd.equals("Edit Parameters"))
        {
            List<Parameter> currentParams = controller.getProjectSnapshot().getClass(className).getMethod(methodName).getParameters();
            List<String> currentParamNames = new ArrayList<>();
            List<String> currentParamTypes = new ArrayList<>();
            for (Parameter param : currentParams)
            {
                currentParamNames.add(param.getName());
                currentParamTypes.add(param.getType());
            }
            Map<String, List<String>> paramListData = view.promptForNewParamList(cmd, currentParamNames, currentParamTypes);
            if (paramListData == null)
            {
                return;
            }
            controller.changeParameterList(className, methodName, paramListData.get("ParamNames"), paramListData.get("ParamTypes"));
        }
	}
}
