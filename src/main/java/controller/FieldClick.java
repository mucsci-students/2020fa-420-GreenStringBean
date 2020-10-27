package controller;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.ClassObject;
import model.WorkingProject;
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
		
		String className = view.getText("Type in class to add/del/rename:");
		String cmd = e.getActionCommand();
		//Fields
        if (cmd.equals("cField")) 
        {
            String visability = view.getText("Visibility (public/private/protected) ->");
            String type = view.getText("Type-> ");
            String name = view.getText("Name-> ");
            controller.addField(className, visability, name, type);
            controller.checkStatus();
        } 
        else if (cmd.equals("dField")) 
        {
            String name = view.getText("Field name to delete->");
            controller.removeField(className, name);
            controller.checkStatus();
        }
        else if (cmd.equals("rField"))
        {
        	String name = view.getText("Old field name");
        	String newName = view.getText("New field name");
            controller.renameField(className, name, newName);
            controller.checkStatus();
        }
        //Methods
        else if (cmd.equals("cMethod"))
        {
        	String visability = view.getText("Visibility (public/private/protected) ->");
            String type = view.getText("Type-> ");
            String name = view.getText("Name-> ");
            controller.addMethod(className, visability, name, type);
            controller.checkStatus();
        }
        else if (cmd.equals("dMethod"))
        {
            String name = view.getText("Field name to delete->");
            controller.removeMethod(className, name);
            controller.checkStatus();
        }
        else if (cmd.equals("rMethod"))
        {
        	String name = view.getText("Old field name");
        	String newName = view.getText("New field name");
            controller.renameMethod(className, name, newName);
            controller.checkStatus();
        }
        //Params
        else if (cmd.equals("cParam"))
        {
            String methName = view.getText("Method Name-> ");
            String name = view.getText("Param Name-> ");
            String type = view.getText("Param Type-> ");
            controller.addParameter(className, methName, name, type);
            controller.checkStatus();
        }
        else if (cmd.equals("dParam"))
        {
            String name = view.getText("Field name to delete->");
            controller.removeMethod(className, name);
            controller.checkStatus();
        }
        else if (cmd.equals("rParam"))
        {
        	String name = view.getText("Old field name");
        	String newName = view.getText("New field name");
            controller.renameMethod(className, name, newName);
            controller.checkStatus();
        }
	}
}
