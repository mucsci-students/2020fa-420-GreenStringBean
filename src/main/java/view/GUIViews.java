package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ClassPanelClick;
import model.ClassObject;
import model.Model;
import model.Relationship;
import model.Relationship.relationshipType;

public class GUIViews implements MenuViews{
	private JMenuBar mb;
	private JLayeredPane pWindow;
	private JFrame win;
	private JMenu fileM;
	private JMenu relaM;
	private JMenu fieldM;
	private JMenu classM;
	private JFileChooser fileChooser;
	private Font font;

	private Map<String, JPanel> classPanels;
	private Map<Relationship, RelationArrow> relationArrows; 

	/**
	 * Constructor for creating a new GUI view
	 */
	public GUIViews()
	{
		this.classPanels = new HashMap<>();
		this.relationArrows = new HashMap<>();
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			// Default Java look and feel will be used
		}
		font = new Font(Font.MONOSPACED, Font.PLAIN, 15);
	}
	
	/**
	 * Mehtod for creating a window for the all the buttons and panels
	 * to be displayed for user.
	 */
	public void window()
	{
		System.out.println("Got to make the window(): GUIViews()");

		win = new JFrame("UML");
		win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		win.setSize(800,750);
		win.setVisible(true);
		win.addComponentListener(new WindowResizeListener(this));
		pWindow = new JLayeredPane();
		pWindow.setLayout(null);
		pWindow.setVisible(true);
		win.add(pWindow);
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
	public String promptForString(String prompt, String title) 
	{
		return JOptionPane.showInputDialog(pWindow, prompt, title, JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Method for displaying an input box for a user
	 * @param  
	 * @param 
	 */
	public String promptForVis(String prompt, String title) 
	{
		Object[] possibleValues = { "Public", "Private", "Protected" };

 		Object selectedValue = JOptionPane.showInputDialog(pWindow,
             prompt, title,
             JOptionPane.PLAIN_MESSAGE, null,
			 possibleValues, possibleValues[0]);
			 
		return (String) selectedValue;	
	}

	/**
	 * Method for displaying an input box for a user
	 * @param  pr
	 * @param 
	 */
	public String promptForRelType(String prompt, String title)
	{
		Object[] possibleValues = { "Inheritance", "Aggregation", "Composition", "Realization" };

 		Object selectedValue = JOptionPane.showInputDialog(pWindow,
             prompt, title,
             JOptionPane.PLAIN_MESSAGE, null,
			 possibleValues, possibleValues[0]);
			 
		return ((String)selectedValue).substring(0, 1);
	}

	/**
	 * Method for displaying an input box for a user
	 * @param  
	 * @param 
	 */
	public String promptForClassName(String prompt, String title)
	{
		if(classPanels.keySet().isEmpty())
		{
			alert("No classes exist");
			return null;
		}
		Object[] possibleValues = classPanels.keySet().toArray();

		Object selectedValue = JOptionPane.showInputDialog(pWindow,
		prompt, title,
		JOptionPane.PLAIN_MESSAGE, null,
		possibleValues, possibleValues[0]);

		return (String) selectedValue;
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
		JMenuItem zi = new JMenuItem("Zoom In");
		JMenuItem zo = new JMenuItem("Zoom Out");
		JMenuItem s = new JMenuItem("Save");
		JMenuItem sa = new JMenuItem("Save As");
		JMenuItem l = new JMenuItem("Load");
		JMenuItem ex = new JMenuItem("Exit");

		KeyStroke undoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK);
		KeyStroke redoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK);
		KeyStroke zoomInKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.CTRL_DOWN_MASK);
		KeyStroke zoomOutKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK);
		KeyStroke loadKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
		KeyStroke saveKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
		KeyStroke saveAsKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);

		un.setAccelerator(undoKeyStroke);
		re.setAccelerator(redoKeyStroke);
		zi.setAccelerator(zoomInKeyStroke);
		zo.setAccelerator(zoomOutKeyStroke);
		s.setAccelerator(saveKeyStroke);
		sa.setAccelerator(saveAsKeyStroke);
		l.setAccelerator(loadKeyStroke);

		JMenuItem[] arr = {un, re, zi, zo, s, sa, l, ex};
		String[] txt = {"Undo", "Redo", "Zoom In", "Zoom Out", "Save edited file", "Save edited file as", "Load selected project", "Exit application"};
		String[] cmd = {"Undo", "Redo", "Zoom In", "Zoom Out", "Save", "Save As", "Load", "Exit"};

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

		JMenuItem in = new JMenuItem("Create Relationship");
		JMenuItem cRelat = new JMenuItem("Change Type");
		JMenuItem dRelat = new JMenuItem("Delete Relationship");
		JMenuItem sRelat = new JMenuItem("Show Relationships");

		JMenuItem[] arr = {in, cRelat, dRelat, sRelat};
		String[] txt = {"Create Relationship", "Change Relationship", "Delete Relationship", "Show Relationships"};
		String[] cmd = {"Create Relationship", "Change Relationship Type", "Remove Relationship", "Show Relationships"};

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
	 * Adds all the action listeners to their respective clicks
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
	public void onUpdate(Model project, boolean newLoadedProject)
	{
		if (newLoadedProject)
		{
			System.out.println("View notified with a loaded project");
			clearClassPanels();

			for (String className : project.getClassNames())
			{
				createClassPanel(project.getClass(className));
			}
		}
		else
		{
			System.out.println("View notified with a non-loaded project");
			String missingClassName = "";
			String foundClassName = "";
			Set<String> existingClassNames = classPanels.keySet();
			Set<String> notifClassNames = project.getClassNames();
			for (String existingClassName : existingClassNames)
			{
				if (!notifClassNames.contains(existingClassName))
				{
					missingClassName = existingClassName;
				}
			}
			for (String notifClassName : notifClassNames)
			{
				if (!existingClassNames.contains(notifClassName))
				{
					foundClassName = notifClassName;
				}
			}

			if (!missingClassName.isEmpty() && !foundClassName.isEmpty())
			{
				// Class has been renamed

				JPanel panel = classPanels.remove(missingClassName);
				classPanels.put(foundClassName, panel);
				pWindow.moveToFront(panel);
			}
			else if (!missingClassName.isEmpty())
			{
				// Class has been removed
				pWindow.remove(classPanels.get(missingClassName));
				classPanels.remove(missingClassName);
			}
			else if (!foundClassName.isEmpty())
			{
				// Class has been added
				createClassPanel(project.getClass(foundClassName));
			}

			for (Map.Entry<String, JPanel> panelEntry : classPanels.entrySet())
			{
				updateClassPanel(panelEntry.getValue(), project.getClass(panelEntry.getKey()));
			}
		}
		relationArrows.clear();

		for (Component c : pWindow.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER))
		{
			pWindow.remove(c);
		}

		for (Relationship r : project.getRelationships())
		{
			createRelationArrow(r);
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
		pWindow.moveToFront(panel);
        refresh();
	}
	
	/**
	 * Creates and adds a new class panel to the view.
	 * @param classObj the class the new panel should display
	 */
	private void createClassPanel(ClassObject classObj)
	{
		JPanel panel = new JPanel();
		ClassPanelClick listener = new ClassPanelClick(this, panel);
		addDragListener(panel, listener);
		panel.setLocation(0, 0);
		boolean goodLocation = false;
		while (!goodLocation)
		{
			int offset = font.getSize() * 4 / 3;
			panel.setLocation(panel.getX() + offset, panel.getY() + offset);
			goodLocation = true;
			for (JPanel other : classPanels.values())
			{
				if (other.getLocation().equals(panel.getLocation()))
				{
					goodLocation = false;
				}
			}
		}
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setVisible(true);
		classPanels.put(classObj.getName(), panel);
		pWindow.add(panel);
		pWindow.setLayer(panel, JLayeredPane.PALETTE_LAYER);
		pWindow.moveToFront(panel);
		
		updateClassPanel(panel, classObj);
	}

	/**
	 * 
	 * @param panel
	 */
	public void addDragListener(Component component, ClassPanelClick listener)
	{
		component.addMouseListener(listener);
		component.addMouseMotionListener(listener);
	}

	/**
	 * 
	 */
	public void createRelationArrow(Relationship relat)
	{
		System.out.println("Got to createRelationArrow: GUIViews()");

		JPanel panelFrom = classPanels.get(relat.getClassNameFrom());
		JPanel panelTo = classPanels.get(relat.getClassNameTo());

		int xDist = panelTo.getX() - panelFrom.getX();
		int yDist = panelTo.getY() - panelFrom.getY();

		Point arrowStart = new Point();
		Point arrowFin = new Point();

		if(Math.abs(xDist) > Math.abs(yDist))
		{
			//Arrow is mostly horizontal
			if(xDist > 0)
			{
				//Left to right
				arrowStart.x = panelFrom.getX() + panelFrom.getWidth();
				arrowFin.x = panelTo.getX();
			}
			else
			{
				//Right to left
				arrowStart.x = panelFrom.getX();
				arrowFin.x = panelTo.getX() + panelTo.getWidth();

			}
			arrowFin.y = panelTo.getX() + (panelTo.getHeight() / 2);
			arrowStart.y = panelFrom.getY() + (panelFrom.getHeight() / 2);
		}
		else
		{
			//Arrow mostly vertical
			if(yDist > 0)
			{
				//Top to bottom 
				arrowStart.y = panelFrom.getY() + panelFrom.getWidth();
				arrowFin.y = panelTo.getY();
			}
			else
			{
				//Bottom to top
				arrowStart.y = panelFrom.getY();
				arrowFin.y = panelTo.getY() + panelTo.getWidth();
			}
			arrowStart.x = panelFrom.getX() + (panelFrom.getWidth() / 2);
			arrowFin.x = panelTo.getX() + (panelTo.getWidth() / 2);
		}
		RelationArrow arrow = new RelationArrow(arrowStart, arrowFin, relat.getType());
		relationArrows.put(relat, arrow);
		pWindow.add(arrow);
		//pWindow.setLayer(arrow, JLayeredPane.DEFAULT_LAYER);
		arrow.setVisible(true);
		pWindow.moveToFront(arrow);
	}

	/**
     * Updates the contents of a class panel to match a class object.
     * @param panel    the panel to update
     * @param classObj the class the panel should display
     */
    private void updateClassPanel(JPanel panel, ClassObject classObj)
    {
        panel.removeAll();
		ClassPanelClick listener =  (ClassPanelClick) panel.getMouseListeners()[0];
        Border classBd = BorderFactory.createLineBorder(Color.BLACK, 2);
        panel.setBorder(classBd);

        panel.setBackground(classObj.isOpen() ? Color.WHITE : Color.GRAY);

		JTextArea classTxt = new JTextArea(classObj.getName());
        classTxt.setEditable(false);
        classTxt.setFocusable(false);
		classTxt.setOpaque(false);
		classTxt.setFont(font);
		panel.add(classTxt);
		addDragListener(classTxt, listener);
        
        for (String fieldName : classObj.getFieldNames())
        {
            JTextArea fieldTxt = new JTextArea(classObj.getField(fieldName).toString());
            fieldTxt.setEditable(false);
            fieldTxt.setFocusable(false);
            fieldTxt.setOpaque(false);
			fieldTxt.setFont(font);
			panel.add(fieldTxt);
			addDragListener(fieldTxt, listener);
        }
        
        for (String methodName : classObj.getMethodNames())
        {
            JTextArea methodTxt = new JTextArea(classObj.getMethod(methodName).toString());
            methodTxt.setEditable(false);
            methodTxt.setFocusable(false);
            methodTxt.setOpaque(false);
			methodTxt.setFont(font);
			panel.add(methodTxt);
			addDragListener(methodTxt, listener);
        }

		panel.setSize(panel.getPreferredSize());
		
		contain(panel);
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
	 * Increases the size of elements on screen, with a maximum of 60pt font
	 * @return true if the size was increased, false if not
	 */
	public boolean zoomIn()
	{
		if (font.getSize() < 60)
		{
			font = font.deriveFont(font.getSize2D() + 3);
			for (JPanel panel : classPanels.values())
			{
				panel.setLocation(panel.getX() + 4, panel.getY() + 4);
			}
			containAll();
			return true;
		}
		return false;
	}

	/**
	 * Decreases the size of elements on screen, with a minimum of 6pt font
	 * @return true if the size was increased, false if not
	 */
	public boolean zoomOut()
	{
		if (font.getSize() > 6)
		{
			font = font.deriveFont(font.getSize2D() - 3);
			for (JPanel panel : classPanels.values())
			{
				panel.setLocation(panel.getX() - 4, panel.getY() - 4);
			}
			containAll();
			return true;
		}
		return false;
	}
	
	/**
	 * Updates view
	 */
	public void refresh() 
	{
		pWindow.revalidate();
		pWindow.repaint();
	}

	/**
	 * Ensures a panel is within the bounds of the window. If a panel does not
	 * fit in the window, it is kept on the left or top of the window.
	 * @param panel
	 */
	public void contain(JPanel panel)
	{
		int panelX = panel.getX();
		int panelY = panel.getY();
		int panelWidth = panel.getWidth();
		int panelHeight = panel.getHeight();
		int windowWidth = pWindow.getWidth();
		int windowHeight = pWindow.getHeight();

		if (panelX + panelWidth > windowWidth)
		{
			panelX = windowWidth - panelWidth;
		}
		if (panelX < 0)
		{
			panelX = 0;
		}

		if (panelY + panelHeight > windowHeight)
		{
			panelY = windowHeight - panelHeight;
		}
		if (panelY < 0)
		{
			panelY = 0;
		}

		panel.setLocation(panelX, panelY);
	}

	/**
	 * Contain all panels within the window.
	 */
	public void containAll()
	{
		for (JPanel panel : classPanels.values())
		{
			contain(panel);
		}
	}

	// Temporary until we can display arrows between class boxes
	public void showRelationships(Model project)
	{
		StringBuilder message = new StringBuilder("Relationships:");
		for (Relationship relationship : project.getRelationships())
		{
			message.append("\n" + relationship.toString());
		}
		alert(message.toString());
	}
}
