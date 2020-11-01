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

	// Map containing the current class panels to display
	private Map<String, JPanel> classPanels;
	
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

	public JFrame getMainWindow()
	{
		return win;
	}
	
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
	
	public JMenuBar getMenuBar()
	{
		return mb;
	}
	
	public String getText(String prompt, String title) 
	{
		return JOptionPane.showInputDialog(pWindow, prompt, title, JOptionPane.PLAIN_MESSAGE);
	}

	public void alert(String message)
    {
        JOptionPane.showMessageDialog(pWindow, message);
	}

	private void setupFileChooser()
	{
		fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("JSON file", "json");
		fileChooser.setFileFilter(filter);
	}
	
	public File getSaveFile()
	{
		if (fileChooser.getSelectedFile() == null)
		{
			return getSaveAsFile();
		}
		return fileChooser.getSelectedFile();
	}

	public File getSaveAsFile()
	{
		if (fileChooser.showSaveDialog(pWindow) == JFileChooser.APPROVE_OPTION)
		{
			// If JSON filetype is selected (default), ensure .json extension
			if (fileChooser.getFileFilter() != fileChooser.getAcceptAllFileFilter())
			{
				String filePath = fileChooser.getSelectedFile().getAbsolutePath();
				if (!filePath.endsWith(".json"))
				{
					fileChooser.setSelectedFile(new File(filePath + ".json"));
				}
			}
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	public File getLoadFile()
	{
		if (fileChooser.showOpenDialog(pWindow) == JFileChooser.APPROVE_OPTION)
		{
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	
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
	
	private void fileListener(ActionListener fileL)
	{
		System.out.println("adding Listeners for FileClick: GUIViews()");
		
		for(Component i: fileM.getMenuComponents())
		{
			JMenuItem menu = (JMenuItem)i;
			menu.addActionListener(fileL);
		}
	}
	
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
	
	private void classListener(ActionListener classL)
	{
		System.out.println("adding Listeners for ClassClick: GUIViews()");
		
		for(Component i: classM.getMenuComponents())
		{
			JMenuItem menu = (JMenuItem)i;
			menu.addActionListener(classL);
		}
	}
	
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
	
	private void fieldListener(ActionListener fieldL)
	{
		System.out.println("adding listeners for FieldClick: GUIViews()");
		
		for(Component i: fieldM.getMenuComponents())
		{
			JMenuItem menu = (JMenuItem)i;
			menu.addActionListener(fieldL);
		}
	}
	
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
	
	private void relationshipListener(ActionListener relatL)
	{
		System.out.println("adding listeners for RelationshipClick: GUIViews()");
		
		for(Component i: relaM.getMenuComponents())
		{
			JMenuItem menu = (JMenuItem)i;
			menu.addActionListener(relatL);
		}
	}
	
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
