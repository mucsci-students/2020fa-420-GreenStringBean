package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MenuViews;

public class FieldClick implements ActionListener 
{
	private MenuViews view;
	private HelperControllers controller;
	
	public FieldClick(MenuViews v, HelperControllers c)
	{
		this.view = v;
		this.controller = c;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("succesfully got to FieldClick actions: FileClick()");
		
		String className = view.getText("Type in class to add/del/rename Attributes");
		String cmd = e.getActionCommand();
		//Fields
        if (cmd.equals("field")) 
        {
            String visability = view.getText("Visibility (public/private/protected)");
            String type = view.getText("Type");
            String name = view.getText("Name");
            controller.addField(className, visability, name, type);
            controller.checkStatus();
        } 
        else if (cmd.equals("dField")) 
        {
            String name = view.getText("Field Name to Delete");
            controller.removeField(className, name);
            controller.checkStatus();
        }
        else if (cmd.equals("rField"))
        {
        	String name = view.getText("Old Field Name");
        	String newName = view.getText("New Field Name");
            controller.renameField(className, name, newName);
            controller.checkStatus();
        }
        else if (cmd.equals("vField"))
        {
        	String fieldName = view.getText("Field Name");
        	String newFieldVis = view.getText("New Field Visibility(public/private/protected)");
            controller.changeFieldVisablity(className, fieldName, newFieldVis);;
            controller.checkStatus();
        }

        //Methods
        else if (cmd.equals("cMethod"))
        {
        	String visability = view.getText("Visibility (public/private/protected)");
            String type = view.getText("Type");
            String name = view.getText("Name");
            controller.addMethod(className, visability, name, type);
            controller.checkStatus();
        }
        else if (cmd.equals("dMethod"))
        {
            String name = view.getText("Method Name to Delete");
            controller.removeMethod(className, name);
            controller.checkStatus();
        }
        else if (cmd.equals("rMethod"))
        {
        	String name = view.getText("Old Method Name");
        	String newName = view.getText("New Method Name");
            controller.renameMethod(className, name, newName);
            controller.checkStatus();
        }
        else if (cmd.equals("vMethod"))
        {
        	String methodName = view.getText("Method Name");
        	String newMethVis = view.getText("New Method Visiblity(public/private/protected)");
            controller.changeMethodVisablity(className, methodName, newMethVis);;
            controller.checkStatus();
        }

        //Params
        else if (cmd.equals("param"))
        {
            String methName = view.getText("Method Name");
            String name = view.getText("Param Name");
            String type = view.getText("Param Type");
            controller.addParameter(className, methName, name, type);
            controller.checkStatus();
        }
        else if (cmd.equals("dParam"))
        {
            String methName = view.getText("Method Name");
            String paramName = view.getText("Parameter Name");
            controller.removeParameter(className, methName, paramName);
            controller.checkStatus();
        }
        else if (cmd.equals("rParam"))
        {
            String methodName = view.getText("Method Name");
            String oldParamName =  view.getText("Old Parameter Name");
        	String newParamName = view.getText("New Parameter Name");
            controller.renameParameter(className, methodName, oldParamName, newParamName);
            controller.checkStatus();
        }
	}
}
