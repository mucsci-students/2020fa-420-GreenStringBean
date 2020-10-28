import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.GridLayout;

import model.WorkingProject;

public class Menu {

	private JMenuBar menuB;
	private JFrame pWindow;
	//private ArrayList<ClassObject> classes;
	private Map<String, JPanel> classList;
	private WorkingProject project;

	//Makes a menu for the GUI
	//view
	public void makeMenu(JFrame window) 
	{
		project = new WorkingProject();
		//classes = new ArrayList<ClassObject>();
		classList = new HashMap<String, JPanel>();
		pWindow = window;
		pWindow.setLayout(new GridLayout(10, 10));
		menuB = new JMenuBar();
		createFMenu(menuB);
		createCMenu(menuB);
		createRelationshipMenu(menuB);
		createAMenu(menuB);
	}

	//Generates navigation bar
	//view
	public JMenuBar getMenuBar()
	{
		return menuB;
	}	

	//Method for getting user input
	//controller
	private String getText(String string)
	{
		String str = JOptionPane.showInputDialog(pWindow, string, JOptionPane.PLAIN_MESSAGE);

		return str;
	}

	//controller
	private void addClass(String className) 
	{
		//className = project.toJSONString();
		ClassPanel(className);
		refresh();
	}

	//controller
	private void deleteClass(String className)
	{
		className = project.toJSONString();
		deleteClassPanel(className);
	}

	//Create the view panel for project
	//view
	private void ClassPanel(String aClass)
	{
		JPanel classP = new JPanel();
		classP.setSize(30, 250);
		classP.setVisible(true);

		aClass = project.toJSONString();

		pWindow.add(classP);

		JTextArea classTxt = new JTextArea(aClass);
		classTxt.setEditable(false);
		classP.add(classTxt);
		classList.put(aClass, classP);

		Border bd = BorderFactory.createLineBorder(Color.RED);
		classTxt.setBorder(bd);
		pWindow.add(classP);	
	}

	//controller
	private void deleteClassPanel(String aClass)
	{
		JPanel pn = classList.get(aClass);
		classList.remove(aClass);
		classList.remove(pn);
		//write a refresh window
		refresh();
	}

	//controller
	private void refresh()
	{
		pWindow.repaint();
	}

	//This creates the "file" navigation menu at the top of the panel
	//view
	public void createFMenu(JMenuBar mb)
	{
		JMenu f = new JMenu("File");

		JMenuItem s = new JMenuItem("Save");
		JMenuItem l = new JMenuItem("Load...");
		JMenuItem ex = new JMenuItem("Exit");

		JMenuItem[] arr = {s, l, ex};
		String[] txt = {"Save edited file", "Load selected project", "Exit application"};
		String[] cmd = {"Save", "Load"};

		for(int count = 0; count < 3; ++count)
		{
			f.add(arr[count]);
			arr[count].setToolTipText(txt[count]);
			if(count < 2)
			{
				arr[count].setActionCommand(cmd[count]);
				arr[count].addActionListener(new FileBClickListener());
			}
			else
				arr[count].addActionListener((event) -> System.exit(0));
		}		

		mb.add(f);
	}

	//Creates nav menu for Class
	//view
	public void createCMenu(JMenuBar mb)
	{
		JMenu classes = new JMenu("Class");
		JMenuItem aClass = new JMenuItem("Create class");
		JMenuItem dClass = new JMenuItem("Delete class");
		JMenuItem rClass = new JMenuItem("Rename class");

		JMenuItem[] arr = {aClass, dClass, rClass};
		String[] text = {"Add Class", "Delete Class", "Rename Class"};
		String[] comd = {"Add", "Delete", "Rename"};

		for(int i = 0; i < 3; ++i)
		{
			classes.add(arr[i]);
			arr[i].setToolTipText(text[i]);
			arr[i].setActionCommand(comd[i]);
			arr[i].addActionListener(new ClassBClickListener());
		}
		mb.add(classes);	
	}

	//Creates nav menu for Relationships
	//view
	public void createRelationshipMenu(JMenuBar mb) 
	{
		JMenu relat = new JMenu("Relationship");
		JMenuItem in = new JMenuItem("Inheritence");
		JMenuItem ag = new JMenuItem("Aggregation");
		JMenuItem comp = new JMenuItem("Composition");
		JMenuItem gen = new JMenuItem("Generalization");

		JMenuItem[] arr = {in, ag, comp, gen};
		String[] names = {"Inheritence", "Aggregation", "Composition", "Generalization"};
		String[] comd = {"I", "A", "C", "G"};

		for(int i = 0; i < 4; ++i)
		{
			relat.add(arr[i]);
			arr[i].setToolTipText(names[i]);
			arr[i].setActionCommand(comd[i]);
			arr[i].addActionListener(new RelatBClickListener());
		}
		mb.add(relat);
	}

	//Creates nav menu for Attribute
	//view
	public void createAMenu(JMenuBar mb)
	{
		JMenu attrib = new JMenu("Attribute");

		JMenuItem field = new JMenuItem("Create field");
        JMenuItem dField = new JMenuItem("Delete field");
        JMenuItem rField = new JMenuItem("Rename field");

		JMenuItem met = new JMenuItem("Create method");
        JMenuItem dMethod = new JMenuItem("Delete method");
        JMenuItem rMethod = new JMenuItem("Rename method");

		JMenuItem[] arr = {field, dField, rField, met, dMethod, rMethod};
		String[] text = {"Create Field", "Delete Field", "Rename Field", "Create Method", "Delete Method", "Rename Method"};
		String[] command = {"cField", "aField", "rField", "cMethod", "dMethod", "rMethod"};

		for(int count = 0; count < 6; ++count)
		{
			attrib.add(arr[count]);
			arr[count].setToolTipText(text[count]);
			arr[count].setActionCommand(command[count]);
			arr[count].addActionListener(new AttribBClickListener());		
		}
		mb.add(attrib);
	}

	//Event handler for Classes
	//BUG: RENAME
	//Not a bug but delete might not work fully
	//controller
	private class ClassBClickListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String command = e.getActionCommand();
			if(command.equals("Add"))
			{
				String classToAdd = getText("Class -> ");
				project.addClass(classToAdd);		
				addClass(classToAdd);
				refresh();
			}
			else if(command.equals("Delete"))
			{
				String classToDelete = getText("Class To Delete ->");
				project.removeClass(classToDelete);
				deleteClass(classToDelete);
				refresh();
			}
			else if(command.equals("Rename"))
			{
				String renamed = getText("Class to be renamed ->"); 
				String newClassName = getText("New Class Name ->");
				project.renameClass(renamed, newClassName);
			}	
		}
	}

	//Event handler for Relationships
	//BUG: DOES NOT ADD RELAT
	//TODO: Delete
	//controller
	private class RelatBClickListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String classNameOne = getText("Class one ->");
			String classNametwo = getText("Class two ->");
			String cmd = e.getActionCommand();
	        if (cmd.equals("Inheritence"))
	        {
	        	String r = new String("I");
	        	project.addRelationship(classNameOne, classNametwo, r);
	        	refresh();
	        }
	        else if (cmd.equals("Aggregation")) 
	        {
	        	String a = new String("A");
	        	project.addRelationship(classNameOne, classNametwo, a);
	        	refresh();
	        }
	        else if (cmd.equals("Composition"))
	        {
	        	String c = new String("C");
	        	project.addRelationship(classNameOne, classNametwo, c);
	        	refresh();
	        }
	        else if (cmd.equals("Generalization"))
	        {
	        	String g = new String("G");
	        	project.addRelationship(classNameOne, classNametwo, g);
	        	refresh();
	        }
	        /*else if (cmd.equals("DeleteRelationship")) 
	        {
	        	
	        }*/
	        refresh();
		}
	}

	//Event handler for Attributes/Fields/Methods
	//BUG: DELETE/RENAME
	//controller
	private class AttribBClickListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String className = getText("Type in class to add/del/rename:");
			String cmd = e.getActionCommand();
	        if (cmd.equals("cField")) 
	        {
	            String type = getText("Type-> ");
	            String name = getText("Name-> ");
	            //project.addField(className, name, type);
	            refresh();
	        } 
	        else if (cmd.equals("dField")) 
	        {
	            String name = getText("Field name to delete->");
	            project.removeField(className, name);
	            //refresh();
	        }
	        else if (cmd.equals("rField"))
	        {
	        	String name = getText("Old field name");
	        	String newName = getText("New field name");
	        	project.renameField(className, name, newName);
	        	refresh();
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
	        refresh();
		}
	}

	//Event handler for saving/loading a project
	//controller
	private class FileBClickListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();
			if(cmd.equals("Save"))
			{
				String currFile = getText("Save ->");
				currFile = project.toJSONString();
			}
			else if(cmd.equals("Load"))
			{
				//Clears the data that is already in the JPanel
				for(Map.Entry<String, JPanel> panel : classList.entrySet())
				{
					pWindow.remove(panel.getValue());
				}
				//classes.clear();
				classList.clear();

				pWindow.revalidate();
				pWindow.repaint();
				String projectToLoad = getText("Load -> ");
				projectToLoad = project.toJSONString();
				ClassPanel(projectToLoad);
			}
			refresh();
		}
	}
}