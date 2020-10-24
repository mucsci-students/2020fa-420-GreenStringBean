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
	private WorkingProject project;
	private ClassObject classObj;
	private MenuViews view;
	private HelperControllers controller;
	
	public FieldClick(WorkingProject p, MenuViews v, HelperControllers c)
	{
		this.project = p;
		this.view = v;
		this.controller = c;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("succesfully got to FieldClick actions: FileClick()");
		
		String className = view.getText("Type in class to add/del/rename:");
		String cmd = e.getActionCommand();
		
        if (cmd.equals("cField")) 
        {
            String type = view.getText("Type-> ");
            String name = view.getText("Name-> ");
            controller.addField(className, name, type);
        } 
        else if (cmd.equals("dField")) 
        {
            String name = view.getText("Field name to delete->");
            controller.removeField(className, name);
        }
        else if (cmd.equals("rField"))
        {
        	String name = view.getText("Old field name");
        	String newName = view.getText("New field name");
        	controller.renameField(className, name, newName);
        }
        /*else if (cmd.equals("cMethod"))
        {
        	
        }
        else if (cmd.equals("dMethod"))
        {
        	
        }
        else if (cmd.equals("rMethod"))
        {
        	
        }*/
	}
}
