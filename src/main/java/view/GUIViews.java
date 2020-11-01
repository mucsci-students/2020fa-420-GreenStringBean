package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;

import controller.HelperControllers;

import javax.swing.WindowConstants;

import model.ClassObject;
import model.WorkingProject;
import model.Relationship;

public class GUIViews implements MenuViews{
	private JMenuBar mb;
	private JFrame pWindow;
	private JFrame win;
	private JMenu fileM;
	private JMenu relaM;
	private JMenu fieldM;
	private JMenu classM;
	private JFileChooser fileChooser;

	private Map<String, JPanel> classPanels;

	/**
	 * Method for making the GUI window a different style
	 */
	public GUIViews()
	{
		this.classPanels = new HashMap<>();
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			// Default Java look and feel will be used
		}
	}
	
	/**
	 * Mehtod for creating a window for the all the buttons and panels
	 * to be displayed for user.
	 */
	public void window()
	{
		System.out.println("Got to make the window(): GUIViews()");

		win = new JFrame("UML");
		win.setLayout(new GridLayout(5,5));
		win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		win.setSize(800,750);
		win.setVisible(true);
		pWindow = win;
		makeMenu(win);
		pWindow.add(mb);
		win.setJMenuBar(mb);
		setupFileChooser();
	}

	/**
	 * Method for returning the window for displaying 
	 * all buttons for menu bar.
	 * @return the window
	 */
	public JFrame getMainWindow()
	{
		return win;
	}
	
	/**
	 * Method for placing all the seperate buttons onto the menu bar
	 * @param win is the window for the menu bar to be added on
	 */
	public void makeMenu(JFrame win)
	{
		System.out.println("made the menu: GUIViews()");
		
		mb = new JMenuBar();
		createFileM(mb);
		createClassM(mb);
		createFieldM(mb);
		createRelatM(mb);
		mb.setVisible(true);
	}
	
	/**
	 * method for returning menu bar for placing buttons
	 * @return menu bar
	 */
	public JMenuBar getMenuBar()
	{
		return mb;
	}
	
	/**
	 * Method for displaying an input box for a user
	 * @param prompt 
	 * @param title
	 */
	public String getText(String prompt, String title) 
	{
		return JOptionPane.showInputDialog(pWindow, prompt, title, JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Method for alerting the user with a prompt after an ilegal action was performed
	 * @param message is the alert message to be dispalyed to user
	 */
	public void alert(String message)
    {
        JOptionPane.showMessageDialog(pWindow, message);
	}

	/**
	 * Method for setting a saved file to a .json file
	 */
	private void setupFileChooser()
	{
		fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("JSON file", "json");
		fileChooser.setFileFilter(filter);
	}
	
	/**
	 * Method to get a saved file 
	 * @return the selected file
	 */
	public File getSaveFile()
	{
		if (fileChooser.getSelectedFile() == null)
		{
			return getSaveAsFile();
		}
		return fileChooser.getSelectedFile();
	}

	/**
	 * Method to get text input of file name from user and get a saved file
	 * @return the selected file
	 */
	public File getSaveAsFile()
	{
		if (fileChooser.showSaveDialog(pWindow) == JFileChooser.APPROVE_OPTION)
		{
			// If no extension is given, add .json extension
			String filePath = fileChooser.getSelectedFile().getAbsolutePath();
			if (!filePath.contains("."))
			{
				fileChooser.setSelectedFile(new File(filePath + ".json"));
			}
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	/**
	 * Method to get a loaded file from user input
	 * @return the selected file
	 */
	public File getLoadFile()
	{
		if (fileChooser.showOpenDialog(pWindow) == JFileChooser.APPROVE_OPTION)
		{
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	/**
	 * Creates a file drop down menu for undo, redo, save, and load buttons.
	 * @param mb is the menu bar
	 */
	private void createFileM(JMenuBar mb)
	{
		System.out.println("made file menu: GUIViews()");
		
		fileM = new JMenu("File");

		JMenuItem un = new JMenuItem("Undo");
		JMenuItem re = new JMenuItem("Redo");
		JMenuItem s = new JMenuItem("Save");
		JMenuItem sa = new JMenuItem("Save As");
		JMenuItem l = new JMenuItem("Load...");
		JMenuItem ex = new JMenuItem("Exit");

		JMenuItem[] arr = {un, re, s, sa, l, ex};
		String[] txt = {"Undo", "Redo", "Save edited file", "Save edited file as", "Load selected project", "Exit application"};
		String[] cmd = {"Undo", "Redo", "Save", "Save As", "Load", "Exit"};

		for(int count = 0; count < arr.length; ++count)
		{
			fileM.add(arr[count]);
			arr[count].setToolTipText(txt[count]);
			arr[count].setActionCommand(cmd[count]);
		}		
		mb.add(fileM);
	}
	
	/**
	 * Adds action listeners to file clicks
	 * @param fileL is all the file action listeners
	 */
	private void fileListener(ActionListener fileL)
	{
		System.out.println("adding Listeners for FileClick: GUIViews()");
		
		for(Component i: fileM.getMenuComponents())
		{
			JMenuItem menu = (JMenuItem)i;
			menu.addActionListener(fileL);
		}
	}
	
	/**
	 * Creates a class drop down menu for adding, changing,
	 * and deleting classes.
	 * @param mb is the menu bar
	 */
	private void createClassM(JMenuBar mb)
	{
		System.out.println("made class menu: GUIViews()");
		
		classM = new JMenu("Class");

		JMenuItem oClass = new JMenuItem("Open Class");
		JMenuItem cClass = new JMenuItem("Close Class");
		JMenuItem aClass = new JMenuItem("Create Class");
		JMenuItem dClass = new JMenuItem("Delete Class");
		JMenuItem rClass = new JMenuItem("Rename Class");

		JMenuItem[] arr = {oClass, cClass, aClass, dClass, rClass};
		String[] txt = {"Open Class","Close Class", "Add Class", "Delete Class", "Rename Class"};
		String[] cmd = {"Open", "Close", "Add Class", "Remove Class", "Rename Class"};

		for(int i = 0; i < arr.length; ++i)
		{
			classM.add(arr[i]);
			arr[i].setToolTipText(txt[i]);
			arr[i].setActionCommand(cmd[i]);
		}
		mb.add(classM);	
	}
	
	/**
	 * Adds action listeners to class clicks
	 * @param classL is all the class action listeners
	 */
	private void classListener(ActionListener classL)
	{
		System.out.println("adding Listeners for ClassClick: GUIViews()");
		
		for(Component i: classM.getMenuComponents())
		{
			JMenuItem menu = (JMenuItem)i;
			menu.addActionListener(classL);
		}
	}
	
	/**
	 * Creates an attribute drop down menu for adding, changing,
	 * and deleting attributes.
	 * @param mb is the menu bar
	 */
	private void createFieldM(JMenuBar mb)
	{
		System.out.println("made field menu:  GUIView()");
		
		fieldM = new JMenu("Attribute");

		JMenuItem field = new JMenuItem("Create Field");
    	JMenuItem dField = new JMenuItem("Delete Field");
		JMenuItem rField = new JMenuItem("Rename Field");
		JMenuItem tField = new JMenuItem("Change Field Type");
		JMenuItem vField = new JMenuItem("Change Field Visibility");

		JMenuItem met = new JMenuItem("Create Method");
    	JMenuItem dMethod = new JMenuItem("Delete Method");
		JMenuItem rMethod = new JMenuItem("Rename Method");
		JMenuItem tMethod = new JMenuItem("Change Method Type");
		JMenuItem vMethod = new JMenuItem("Change Method Visibility");
		
		JMenuItem param = new JMenuItem("Create Parameter");
    	JMenuItem dParam = new JMenuItem("Delete Parameter");
		JMenuItem rParam = new JMenuItem("Rename Parameter");
		JMenuItem tParam = new JMenuItem("Change Parameter Type");

		JMenuItem[] arr = {field, dField, rField, tField, vField, met, dMethod, rMethod, tMethod, vMethod, param, dParam, rParam, tParam};
		String[] txt = {"Create Field", "Delete Field", "Rename Field", "Change Field Type", "Change Field Visibility", "Create Method", "Delete Method", "Rename Method", "Change Method Type", "Change Method Visability", "Create Parameter", "Delete Parameter", "Rename Parameter", "Change Parameter Type"};
		String[] cmd = {"Add Field", "Remove Field", "Rename Field", "Change Field Type", "Change Field Visibility", "Add Method", "Remove Method", "Rename Method", "Change Method Type", "Change Method Visibility", "Add Parameter", "Remove Parameter", "Rename Parameter", "Change Parameter Type"};

		for(int count = 0; count < arr.length; ++count)
		{
			fieldM.add(arr[count]);
			arr[count].setToolTipText(txt[count]);
			arr[count].setActionCommand(cmd[count]);	
		}
		mb.add(fieldM);
	}
	
	/**
	 * Adds action listeners to field clicks
	 * @param fieldL is all the field action listeners
	 */
	private void fieldListener(ActionListener fieldL)
	{
		System.out.println("adding listeners for FieldClick: GUIViews()");
		
		for(Component i: fieldM.getMenuComponents())
		{
			JMenuItem menu = (JMenuItem)i;
			menu.addActionListener(fieldL);
		}
	}
	
	/**
	 * Creates a relationship drop down menu for adding, changing,
	 * deleting, and showing relationships
	 * @param mb is the menu bar
	 */
	private void createRelatM(JMenuBar mb) 
	{
		System.out.println("made relationship menu:  GUIView()");
		
		relaM = new JMenu("Relationship");

		JMenuItem in = new JMenuItem("Create Inheritence");
		JMenuItem ag = new JMenuItem("Create Aggregation");
		JMenuItem comp = new JMenuItem("Create Composition");
		JMenuItem re = new JMenuItem("Create Realization");
		JMenuItem cRelat = new JMenuItem("Change Type");
		JMenuItem dRelat = new JMenuItem("Delete Relationship");
		JMenuItem sRelat = new JMenuItem("Show Relationships");

		JMenuItem[] arr = {in, ag, comp, re, cRelat, dRelat, sRelat};
		String[] txt = {"Inheritence", "Aggregation", "Composition", "Realization", "Change Relationship", "Delete Relationship", "Show Relationships"};
		String[] cmd = {"Add Inheritance", "Add Aggregation", "Add Composition", "Add Realization", "Change Relationship Type", "Remove Relationship", "Show Relationships"};

		for(int i = 0; i < arr.length; ++i)
		{
			relaM.add(arr[i]);
			arr[i].setToolTipText(txt[i]);
			arr[i].setActionCommand(cmd[i]);
		}
		mb.add(relaM);
	}
	
	/**
	 * Adds action listeners to relationship clicks
	 * @param relatL is all the relationship action listeners
	 */
	private void relationshipListener(ActionListener relatL)
	{
		System.out.println("adding listeners for RelationshipClick: GUIViews()");
		
		for(Component i: relaM.getMenuComponents())
		{
			JMenuItem menu = (JMenuItem)i;
			menu.addActionListener(relatL);
		}
	}
	
	/**
	 * Adds all the action listeners to there respected clicks
	 */
	public void addListeners(ActionListener fileL, ActionListener classL, ActionListener fieldL,
			ActionListener relatL) 
	{
		System.out.println("adding listeners for all the menu buttons: GUIViews()");
		
        fileListener(fileL);
        classListener(classL);
        fieldListener(fieldL);
		relationshipListener(relatL);
		
		System.out.println("finished listeners: GUIViews()");
    }
	
	/**
	 * Starts the program with a clean window for GUI
	 */
	public void start()
    {
		window();
        refresh();
    }

	/**
	 * Called when an observed project updates. Update all class panels.
	 * @param project a copy of the observed project
	 */
	public void onUpdate(WorkingProject project)
	{
		System.out.println("View notified with a project");
		clearClassPanels();

		for (String className : project.getClassNames())
		{
			createClassPanel(project.getClass(className));
		}

		refresh();
	}

	/**
	 * Called when a single class of an observed project updates. Update the
	 * associated class panel.
	 * @param classObj a copy of the class from the observed project
	 */
	public void onUpdate(ClassObject classObj)
	{
		System.out.println("View notified with a class");
		JPanel panel = classPanels.get(classObj.getName());
		updateClassPanel(panel, classObj);
        refresh();
	}
	
	/**
	 * Creates and adds a new class panel to the view.
	 * @param classObj the class the new panel should display
	 */
	private void createClassPanel(ClassObject classObj)
	{
		System.out.println("made the class panel for display: GUIViews()");
        
        JPanel panel = new JPanel();
		panel.setVisible(true);
		classPanels.put(classObj.getName(), panel);
		pWindow.add(panel);
		
		updateClassPanel(panel, classObj);
	}

	/**
	 * Updates the contents of a class panel to match a class object.
	 * @param panel    the panel to update
	 * @param classObj the class the panel should display
	 */
	private void updateClassPanel(JPanel panel, ClassObject classObj)
	{
		panel.removeAll();
		
		Border classBd = BorderFactory.createLineBorder(Color.BLACK);
        Border classNameBd = BorderFactory.createLineBorder(Color.RED);
        Border fieldBd = BorderFactory.createLineBorder(Color.GREEN);
		Border methodBd = BorderFactory.createLineBorder(Color.BLUE);
		panel.setBorder(classBd);

		if(classObj.isOpen())
		{
			panel.setBackground(Color.LIGHT_GRAY);
		}
		else
		{
			panel.setBackground(Color.GRAY);
		}

		JTextArea classTxt = new JTextArea(classObj.getName());
		classTxt.setEditable(false);
        classTxt.setBorder(classNameBd);
		panel.add(classTxt);
		
        for (String fieldName : classObj.getFieldNames())
        {
            JTextArea fieldTxt = new JTextArea(classObj.getField(fieldName).toString());
            fieldTxt.setEditable(false);
            fieldTxt.setBorder(fieldBd);
            panel.add(fieldTxt);
		}
		
        for (String methodName : classObj.getMethodNames())
        {
            JTextArea methodTxt = new JTextArea(classObj.getMethod(methodName).toString());
            methodTxt.setEditable(false);
            methodTxt.setBorder(methodBd);
            panel.add(methodTxt);
		}
	}

	/**
	 * Removes all class panels from the view.
	 */
	private void clearClassPanels()
	{
		for (JPanel panel : classPanels.values())
		{
			pWindow.remove(panel);
		}
		classPanels.clear();
	}
	
	/**
	 * Updates view
	 */
	public void refresh() 
	{
		pWindow.revalidate();
		pWindow.repaint();
	}

	// Temporary until we can display arrows between class boxes
	public void showRelationships(WorkingProject project)
	{
		StringBuilder message = new StringBuilder("Relationships:");
		for (Relationship relationship : project.getRelationships())
		{
			message.append("\n" + relationship.toString());
		}
		alert(message.toString());
	}
}
