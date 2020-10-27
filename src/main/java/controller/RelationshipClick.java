package controller;
import java.awt.event.*;
import java.util.ArrayList;
import model.WorkingProject;
import view.*;
import model.Relationship;

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
		
		String classNameOne = view.getText("Class one ->");
		String classNametwo = view.getText("Class two ->");
		String cmd = e.getActionCommand();
        if (cmd.equals("Inheritence"))
        {
        	String i = new String("I");
        	controller.addRelationship(classNameOne, classNametwo, i);
        }
        else if (cmd.equals("Aggregation")) 
        {
        	String a = new String("A");
        	controller.addRelationship(classNameOne, classNametwo, a);
        }
        else if (cmd.equals("Composition"))
        {
        	String c = new String("C");
        	controller.addRelationship(classNameOne, classNametwo, c);
        }
        else if (cmd.equals("Generalization"))
        {
        	String g = new String("G");
        	controller.addRelationship(classNameOne, classNametwo, g);
        }
        /*else if (cmd.equals("DeleteRelationship")) 
        {
        	
        }*/
	}
}
