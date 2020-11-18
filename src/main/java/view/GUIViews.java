package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ClassPanelClick;
import controller.ClassRightClick;
import controller.FieldRightClick;
import controller.MethodRightClick;
import controller.ParamButtonClick;
import controller.RightClickListenerFactory;
import model.ClassObject;
import model.Model;
import model.Relationship;

public class GUIViews implements MenuViews {
	private JMenuBar mb;
	private JLayeredPane pWindow;
	private JFrame win;
	private JMenu fileM;
	private JMenu relaM;
	private JMenu fieldM;
	private JMenu projectM;
	private RightClickListenerFactory clickFactory;
	private JFileChooser fileChooser;
	private Font font;

	private Map<String, JPanel> classPanels;
	private Map<Relationship, RelationArrow> relationArrows;

	/**
	 * Constructor for creating a new GUI view
	 */
	public GUIViews() {
		this.classPanels = new HashMap<>();
		this.relationArrows = new HashMap<>();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// Default Java look and feel will be used
		}
		font = new Font(Font.MONOSPACED, Font.PLAIN, 15);
	}

	/**
	 * Mehtod for creating a window for the all the buttons and panels to be
	 * displayed for user.
	 */
	public void window() {
		System.out.println("Got to make the window(): GUIViews()");

		win = new JFrame("UML");
		win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		win.setSize(800, 750);
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
	 * Method for returning the window for displaying all buttons for menu bar.
	 * 
	 * @return the window
	 */
	public JFrame getMainWindow() {
		return win;
	}

	/**
	 * Method for placing all the seperate buttons onto the menu bar
	 * 
	 * @param win is the window for the menu bar to be added on
	 */
	public void makeMenu(JFrame win) {
		System.out.println("made the menu: GUIViews()");

		mb = new JMenuBar();
		createFileM(mb);
		createClassM(mb);
		createRelatM(mb);
		mb.setVisible(true);
	}

	/**
	 * method for returning menu bar for placing buttons
	 * 
	 * @return menu bar
	 */
	public JMenuBar getMenuBar() {
		return mb;
	}

	/**
	 * Method for displaying an input box for a user
	 * 
	 * @param prompt
	 * @param title
	 */
	public String promptForString(String prompt, String title) {
		return JOptionPane.showInputDialog(pWindow, prompt, title, JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Method for displaying an input box for a user
	 * 
	 * @param
	 * @param
	 */
	public String promptForVis(String prompt, String title) {
		Object[] possibleValues = { "Public", "Private", "Protected" };

		Object selectedValue = JOptionPane.showInputDialog(pWindow, prompt, title, JOptionPane.PLAIN_MESSAGE, null,
				possibleValues, possibleValues[0]);

		return (String) selectedValue;
	}

	/**
	 * Method for displaying an input box for a user
	 * 
	 * @param pr
	 * @param
	 */
	public String promptForRelType(String prompt, String title) {
		Object[] possibleValues = { "Inheritance", "Aggregation", "Composition", "Realization" };

		Object selectedValue = JOptionPane.showInputDialog(pWindow, prompt, title, JOptionPane.PLAIN_MESSAGE, null,
				possibleValues, possibleValues[0]);

		return ((String) selectedValue).substring(0, 1);
	}

	/**
	 * Method for displaying an input box for a user
	 * 
	 * @param
	 * @param
	 */
	public String promptForClassName(String prompt, String title) {
		if (classPanels.keySet().isEmpty()) {
			alert("No classes exist");
			return null;
		}
		Object[] possibleValues = classPanels.keySet().toArray();

		Object selectedValue = JOptionPane.showInputDialog(pWindow, prompt, title, JOptionPane.PLAIN_MESSAGE, null,
				possibleValues, possibleValues[0]);

		return (String) selectedValue;
	}

	/**
	 * Displays a dialog box to fill in all the information for a new field
	 * 
	 * @param title the window title of the dialog box
	 * @return a map of the field's properties to their values, or null if canceled
	 */
	public Map<String, String> promptForNewField(String title)
	{
		// Create input fields
		String[] possibleVisValues = { "Public", "Protected", "Private" };
		JComboBox<String> visField = new JComboBox<>(possibleVisValues);
		visField.setAlignmentX(Component.LEFT_ALIGNMENT);
		JTextField typeField = new JTextField();
		typeField.setAlignmentX(Component.LEFT_ALIGNMENT);
		JTextField nameField = new JTextField();
		nameField.setAlignmentX(Component.LEFT_ALIGNMENT);

		JPanel fieldPrompt = new JPanel();
		fieldPrompt.setLayout(new BoxLayout(fieldPrompt, BoxLayout.Y_AXIS));

		// Section of dialog for visibility
		JLabel visLabel = new JLabel("Visibility:");
		visLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		fieldPrompt.add(visLabel);
		fieldPrompt.add(visField);
		fieldPrompt.add(Box.createVerticalStrut(10));

		// Section of dialog for data type
		JLabel typeLabel = new JLabel("Data Type:");
		typeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		fieldPrompt.add(typeLabel);
		fieldPrompt.add(typeField);
		fieldPrompt.add(Box.createVerticalStrut(10));

		// Section of dialog for name
		JLabel nameLabel = new JLabel("Field Name:");
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		fieldPrompt.add(nameLabel);
		fieldPrompt.add(nameField);

		// Display dialog to user
		int option = JOptionPane.showConfirmDialog(null, fieldPrompt, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (option == JOptionPane.OK_OPTION)
		{
			// Store input in a map and return it to the controller
			Map<String, String> fieldData = new HashMap<>();
			fieldData.put("Visibility", (String)visField.getSelectedItem());
			fieldData.put("Type", typeField.getText());
			fieldData.put("Name", nameField.getText());
			return fieldData;
		}

		return null;
	}

	/**
	* Displays a dialog box to fill in all the information for a new method
	* @param title the window title of the dialog box
	* @return      a map of the method's properties to their values, or null if canceled
	*/
	public Map<String, Object> promptForNewMethod(String title)
	{
		// Create input fields
		String[] possibleVisValues = { "Public", "Protected", "Private" };
		JComboBox<String> visField = new JComboBox<>(possibleVisValues);
		visField.setAlignmentX(Component.LEFT_ALIGNMENT);
		JTextField typeField = new JTextField();
		typeField.setAlignmentX(Component.LEFT_ALIGNMENT);
		JTextField nameField = new JTextField();
		nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
		List<JTextField> paramNameFields = new ArrayList<>();
		List<JTextField> paramTypeFields = new ArrayList<>();

		JPanel methodPrompt = new JPanel();
		methodPrompt.setLayout(new BoxLayout(methodPrompt, BoxLayout.Y_AXIS));

		// Section of dialog for visibility
		JLabel visLabel = new JLabel("Visibility:");
		visLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		methodPrompt.add(visLabel);
		methodPrompt.add(visField);
		methodPrompt.add(Box.createVerticalStrut(10));

		// Section of dialog for return type
		JLabel typeLabel = new JLabel("Return Type:");
		typeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		methodPrompt.add(typeLabel);
		methodPrompt.add(typeField);
		methodPrompt.add(Box.createVerticalStrut(10));

		// Section of dialog for name
		JLabel nameLabel = new JLabel("Field Name:");
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		methodPrompt.add(nameLabel);
		methodPrompt.add(nameField);
		methodPrompt.add(Box.createVerticalStrut(10));

		// Section of dialog for parameters (expandable)
		JLabel paramsLabel = new JLabel("Parameters:");
		paramsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		methodPrompt.add(paramsLabel);
		JPanel paramEntriesPanel = new JPanel();
		paramEntriesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		paramEntriesPanel.setLayout(new GridLayout(0, 2));
		methodPrompt.add(paramEntriesPanel);
		methodPrompt.add(Box.createVerticalStrut(10));

		// Create buttons to add or remove entries from the parameter section
		JPanel paramButtonsPanel = new JPanel();
		paramButtonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		paramButtonsPanel.setLayout(new GridLayout(0, 2));
		JButton addParamButton = new JButton("Add");
		addParamButton.setActionCommand("Add");
		paramButtonsPanel.add(addParamButton);
		JButton removeParamButton = new JButton("Remove");
		removeParamButton.setActionCommand("Remove");
		paramButtonsPanel.add(removeParamButton);
		methodPrompt.add(paramButtonsPanel);

		ParamButtonClick paramButtonListener = new ParamButtonClick(paramEntriesPanel, paramNameFields, paramTypeFields);
		addParamButton.addActionListener(paramButtonListener);
		removeParamButton.addActionListener(paramButtonListener);

		// Display dialog to user
		int option = JOptionPane.showConfirmDialog(null, methodPrompt, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (option == JOptionPane.OK_OPTION)
		{
			// Store input in a map and return in to the controller
			Map<String, Object> methodData = new HashMap<>();
			methodData.put("Visibility", visField.getSelectedItem());
			methodData.put("Type", typeField.getText());
			methodData.put("Name", nameField.getText());
			
			List<String> paramNames = new ArrayList<>();
			List<String> paramTypes = new ArrayList<>();

			paramNameFields.forEach(field -> paramNames.add(field.getText()));
			paramTypeFields.forEach(field -> paramTypes.add(field.getText()));

			methodData.put("ParamNames", paramNames);
			methodData.put("ParamTypes", paramTypes);

			return methodData;
		}

	   return null;
   }

	/**
	* Displays a dialog box to change the parameters of a method
	* @param title the window title of the dialog box
	* @return      a map of the method's properties to their values, or null if canceled
	*/
	public Map<String, List<String>> promptForNewParamList(String title, List<String> oldParamNames, List<String> oldParamTypes)
	{
		// Create input fields
		List<JTextField> paramNameFields = new ArrayList<>();
		for (String paramName : oldParamNames)
		{
			paramNameFields.add(new JTextField(paramName));
		}
		List<JTextField> paramTypeFields = new ArrayList<>();
		for (String paramType : oldParamTypes)
		{
			paramTypeFields.add(new JTextField(paramType));
		}

		JPanel paramListPrompt = new JPanel();
		paramListPrompt.setLayout(new BoxLayout(paramListPrompt, BoxLayout.Y_AXIS));

		// Section of dialog for parameters (expandable)
		JLabel paramsLabel = new JLabel("Parameters:");
		paramsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		paramListPrompt.add(paramsLabel);
		JPanel paramEntriesPanel = new JPanel();
		paramEntriesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		paramEntriesPanel.setLayout(new GridLayout(0, 2));
		paramListPrompt.add(paramEntriesPanel);
		paramListPrompt.add(Box.createVerticalStrut(10));

		// Create buttons to add or remove entries from the parameter section
		JPanel paramButtonsPanel = new JPanel();
		paramButtonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		paramButtonsPanel.setLayout(new GridLayout(0, 2));
		JButton addParamButton = new JButton("Add");
		addParamButton.setActionCommand("Add");
		paramButtonsPanel.add(addParamButton);
		JButton removeParamButton = new JButton("Remove");
		removeParamButton.setActionCommand("Remove");
		paramButtonsPanel.add(removeParamButton);
		paramListPrompt.add(paramButtonsPanel);

		ParamButtonClick paramButtonListener = new ParamButtonClick(paramEntriesPanel, paramNameFields, paramTypeFields);
		addParamButton.addActionListener(paramButtonListener);
		removeParamButton.addActionListener(paramButtonListener);
 
		// Display dialog to user
		int option = JOptionPane.showConfirmDialog(null, paramListPrompt, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (option == JOptionPane.OK_OPTION)
		{
			// Store input in a map and return in to the controller
			Map<String, List<String>> paramListData = new HashMap<>();
			
			List<String> paramNames = new ArrayList<>();
			List<String> paramTypes = new ArrayList<>();

			paramNameFields.forEach(field -> paramNames.add(field.getText()));
			paramTypeFields.forEach(field -> paramTypes.add(field.getText()));

			paramListData.put("ParamNames", paramNames);
			paramListData.put("ParamTypes", paramTypes);

			return paramListData;
		}
 
		return null;
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
		
		JMenuItem zi = new JMenuItem("Zoom In");
		JMenuItem zo = new JMenuItem("Zoom Out");
		JMenuItem s = new JMenuItem("Save");
		JMenuItem sa = new JMenuItem("Save As");
		JMenuItem l = new JMenuItem("Load");
		JMenuItem ex = new JMenuItem("Exit");

		KeyStroke zoomInKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.CTRL_DOWN_MASK);
		KeyStroke zoomOutKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK);
		KeyStroke loadKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
		KeyStroke saveKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
		KeyStroke saveAsKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);

		zi.setAccelerator(zoomInKeyStroke);
		zo.setAccelerator(zoomOutKeyStroke);
		s.setAccelerator(saveKeyStroke);
		sa.setAccelerator(saveAsKeyStroke);
		l.setAccelerator(loadKeyStroke);

		JMenuItem[] arr = {zi, zo, s, sa, l, ex};
		String[] txt = {"Zoom In", "Zoom Out", "Save edited file", "Save edited file as", "Load selected project", "Exit application"};
		String[] cmd = {"Zoom In", "Zoom Out", "Save", "Save As", "Load", "Exit"};

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
		
		projectM = new JMenu("Project");

		JMenuItem un = new JMenuItem("Undo");
		JMenuItem re = new JMenuItem("Redo");
		JMenuItem aClass = new JMenuItem("Create Class");

		KeyStroke undoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK);
		KeyStroke redoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK);

		un.setAccelerator(undoKeyStroke);
		re.setAccelerator(redoKeyStroke);

		JMenuItem[] arr = {un, re, aClass};
		String[] txt = {"Undo","Redo", "Add Class"};
		String[] cmd = {"Undo", "Redo", "Add Class"};

		for(int i = 0; i < arr.length; ++i)
		{
			projectM.add(arr[i]);
			arr[i].setToolTipText(txt[i]);
			arr[i].setActionCommand(cmd[i]);
		}
		mb.add(projectM);	
	}
	
	/**
	 * Adds action listeners to class clicks
	 * @param classL is all the class action listeners
	 */
	private void classListener(ActionListener classL)
	{
		System.out.println("adding Listeners for ClassClick: GUIViews()");
		
		for(Component i: projectM.getMenuComponents())
		{
			JMenuItem menu = (JMenuItem)i;
			menu.addActionListener(classL);
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

		JMenuItem[] arr = {in, cRelat, dRelat};
		String[] txt = {"Create Relationship", "Change Relationship", "Delete Relationship"};
		String[] cmd = {"Create Relationship", "Change Relationship Type", "Remove Relationship"};

		for(int i = 0; i < arr.length; ++i)
		{
			relaM.add(arr[i]);
			arr[i].setToolTipText(txt[i]);
			arr[i].setActionCommand(cmd[i]);
		}
		mb.add(relaM);
	}

	private void createClassRightClick(String className, JTextArea txt, JPanel panel)
	{
		System.out.println("made class right click: GUIViews()");
		
		JPopupMenu classM = new JPopupMenu();
		ClassRightClick click = clickFactory.getClassRightClick(className);

		JMenuItem oClass = new JMenuItem("Open Class");
		JMenuItem cClass = new JMenuItem("Close Class");
		JMenuItem dClass = new JMenuItem("Delete Class");
		JMenuItem rClass = new JMenuItem("Rename Class");

		JMenuItem aField = new JMenuItem("Add Field");
		JMenuItem aMeth = new JMenuItem("Add Method");

		JMenuItem[] arr = {oClass, cClass, dClass, rClass, aField, aMeth};
		String[] text = {"Open Class","Close Class", "Delete Class", "Rename Class", "Add Field", "Add Method"};
		String[] cmd = {"Open", "Close", "Remove Class", "Rename Class", "Add Field", "Add Method"};

		for(int i = 0; i < arr.length; ++i)
		{
			arr[i].addActionListener(click);
			classM.add(arr[i]);
			arr[i].setToolTipText(text[i]);
			arr[i].setActionCommand(cmd[i]);
		}	
		ClassPanelClick listen = new ClassPanelClick(this, panel, classM);
		addDragListener(txt, listen);
	}

	private void createFieldRightClick(String className, String fieldName, JTextArea txt, JPanel panel)
	{	
		JPopupMenu fieldM = new JPopupMenu();
		FieldRightClick click = clickFactory.getFieldRightClick(className, fieldName);

		JMenuItem dField = new JMenuItem("Remove Field");
		JMenuItem rField = new JMenuItem("Rename Field");
		JMenuItem tField = new JMenuItem("Change Field Type");
		JMenuItem vField = new JMenuItem("Change Field Visibility");

		JMenuItem[] arr = {dField, rField, tField, vField};
		String[] text = {"Remove Field","Rename Field", "Change Field Type", "Change Field Visibility"};
		String[] cmd = {"Remove Field", "Rename Field", "Change Field Type", "Change Field Visibility"};

		for(int i = 0; i < arr.length; ++i)
		{
			arr[i].addActionListener(click);
			fieldM.add(arr[i]);
			arr[i].setToolTipText(text[i]);
			arr[i].setActionCommand(cmd[i]);
		}	
		ClassPanelClick listen = new ClassPanelClick(this, panel, fieldM);
		addDragListener(txt, listen);
	}

	private void createMethodRightClick(String className, String methodName, JTextArea txt, JPanel panel)
	{	
		JPopupMenu methM = new JPopupMenu();
		MethodRightClick click = clickFactory.getMethodRightClick(className, methodName);

		JMenuItem dMeth = new JMenuItem("Remove Method");
		JMenuItem rMeth = new JMenuItem("Rename Method");
		JMenuItem tMeth = new JMenuItem("Change Method Type");
		JMenuItem vMeth = new JMenuItem("Change Method Visibility");

		JMenuItem eParam = new JMenuItem("Edit Parameters");

		JMenuItem[] arr = {dMeth, rMeth, tMeth, vMeth, eParam};
		String[] text = {"Remove Method","Rename Method", "Change Method Type", "Change Method Visibility", "Edit Parameters"};
		String[] cmd = {"Remove Method", "Rename Method", "Change Method Type", "Change Method Visibility", "Edit Parameters"};

		for(int i = 0; i < arr.length; ++i)
		{
			arr[i].addActionListener(click);
			methM.add(arr[i]);
			arr[i].setToolTipText(text[i]);
			arr[i].setActionCommand(cmd[i]);
		}	
		ClassPanelClick listen = new ClassPanelClick(this, panel, methM);
		addDragListener(txt, listen);
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
	public void addListeners(ActionListener fileL, ActionListener classL,
			ActionListener relatL, RightClickListenerFactory clickFactory) 
	{
		System.out.println("adding listeners for all the menu buttons: GUIViews()");
		
        fileListener(fileL);
        classListener(classL);
		relationshipListener(relatL);
		this.clickFactory = clickFactory;
		
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
		JPanel panelFrom = classPanels.get(relat.getClassNameFrom());
		JPanel panelTo = classPanels.get(relat.getClassNameTo());

		RelationArrow arrow = new RelationArrow(panelFrom, panelTo, relat.getType());
		arrow.setVisible(true);
		arrow.setOpaque(false);
		arrow.setLocation(0, 0);
		arrow.setSize(pWindow.getSize());
		pWindow.add(arrow);
		pWindow.setLayer(arrow, JLayeredPane.DEFAULT_LAYER);
		pWindow.moveToFront(arrow);
		relationArrows.put(relat, arrow);
	}

	/**
     * Updates the contents of a class panel to match a class object.
     * @param panel    the panel to update
     * @param classObj the class the panel should display
     */
    private void updateClassPanel(JPanel panel, ClassObject classObj)
    {
        panel.removeAll();
        Border classBd = BorderFactory.createLineBorder(Color.BLACK, 2);
        panel.setBorder(classBd);

        panel.setBackground(classObj.isOpen() ? Color.WHITE : Color.GRAY);

		String className = classObj.getName();

		JTextArea classTxt = new JTextArea(className);
        classTxt.setEditable(false);
        classTxt.setFocusable(false);
		classTxt.setOpaque(false);
		classTxt.setFont(font);
		panel.add(classTxt);
		createClassRightClick(className, classTxt, panel);
		
        for (String fieldName : classObj.getFieldNames())
        {
            JTextArea fieldTxt = new JTextArea(classObj.getField(fieldName).toString());
            fieldTxt.setEditable(false);
            fieldTxt.setFocusable(false);
            fieldTxt.setOpaque(false);
			fieldTxt.setFont(font);
			panel.add(fieldTxt);
			createFieldRightClick(className, fieldName, fieldTxt, panel);
        }
        
        for (String methodName : classObj.getMethodNames())
        {
            JTextArea methodTxt = new JTextArea(classObj.getMethod(methodName).toString());
            methodTxt.setEditable(false);
            methodTxt.setFocusable(false);
            methodTxt.setOpaque(false);
			methodTxt.setFont(font);
			panel.add(methodTxt);
			createMethodRightClick(className, methodName, methodTxt, panel);
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
		for (RelationArrow arrow : relationArrows.values())
		{
			arrow.setSize(pWindow.getSize());
		}
	}
}
