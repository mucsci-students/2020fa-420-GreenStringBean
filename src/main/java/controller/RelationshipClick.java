package controller;
import java.awt.event.*;
import view.*;

public class RelationshipClick implements ActionListener
{
	private MenuViews view;
	private HelperControllers controller;
	
	public RelationshipClick(MenuViews v, HelperControllers c)
	{
		this.view = v;
		this.controller = c;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("succesfully got to RelationshipClick actions: RelationshipClick()");
		String cmd = e.getActionCommand();
		
        if (cmd.equals("I"))
        {
			System.out.println("adding I relat to classes: relationclick()");
			
			String classNameOne = view.getText("Class one ->");
			String classNametwo = view.getText("Class two ->");
        	String i = new String("I");
        	controller.addRelationship(classNameOne, classNametwo, i);
        }
        else if (cmd.equals("A")) 
        {
			String classNameOne = view.getText("Class one ->");
			String classNametwo = view.getText("Class two ->");
        	String a = new String("A");
        	controller.addRelationship(classNameOne, classNametwo, a);
        }
        else if (cmd.equals("C"))
        {
			String classNameOne = view.getText("Class one ->");
			String classNametwo = view.getText("Class two ->");
        	String c = new String("Composition");
        	controller.addRelationship(classNameOne, classNametwo, c);
        }
        else if (cmd.equals("R"))
        {
			String classNameOne = view.getText("Class one ->");
			String classNametwo = view.getText("Class two ->");
        	String r = new String("Realization");
        	controller.addRelationship(classNameOne, classNametwo, r);
        }
        else if (cmd.equals("dRelat")) 
        {
			String classOne = view.getText("Class One");
			String classTwo = view.getText("Class Two");
			controller.removeRelationship(classOne, classTwo);
		}
		else if(cmd.equals("cRelat"))
		{
			String classNameFrom = view.getText("Class From");
			String classNameTo = view.getText("Class To");
			String newTypeName = view.getText("New Relationship name (I/A/C/R)");
			controller.changeRelationship(classNameFrom, classNameTo, newTypeName);

		}
	}
}
