package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import model.ClassObject;
import model.WorkingProject;

public class GUIViews implements MenuViews{
	private JMenuBar mb;
	private JFrame pWindow;
	private JFrame win;
	private Map<String, JPanel> classes;
	private JMenu fileM;
	private JMenu relaM;
	private JMenu fieldM;
	private JMenu classM;
	
	public GUIViews()
	{
		this.classes = new HashMap<String, JPanel>();
	}
	
	public void window()
	{
		System.out.println("Got to make the window(): GUIViews()");

		   win = new JFrame("UML");
	       win.setLayout(new GridLayout(5,5));
	       win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       win.setSize(800,750);
	       win.setVisible(true);
	       pWindow = win;
	       makeMenu(win);
	       pWindow.add(mb);
	       win.setJMenuBar(mb);
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
	
	public String getText(String string) 
	{
		String str = JOptionPane.showInputDialog(pWindow, string, JOptionPane.PLAIN_MESSAGE);
		return str;
	}

	public void alert(String message)
    {
        JOptionPane.showMessageDialog(pWindow, message);
    }
	
	private void createFileM(JMenuBar mb)
	{
		System.out.println("made file menu: GUIViews()");
		
		fileM = new JMenu("File");

		JMenuItem un = new JMenuItem("Undo");
		JMenuItem re = new JMenuItem("Redo");
		JMenuItem s = new JMenuItem("Save");
		JMenuItem l = new JMenuItem("Load...");
		JMenuItem ex = new JMenuItem("Exit");

		JMenuItem[] arr = {un, re, s, l, ex};
		String[] txt = {"Undo", "Redo", "Save edited file", "Load selected project", "Exit application"};
		String[] cmd = {"Undo", "Redo", "Save", "Load", "Exit"};

		for(int count = 0; count < 5; ++count)
		{
			fileM.add(arr[count]);
			arr[count].setToolTipText(txt[count]);
			if(count < 2)
			{
				arr[count].setActionCommand(cmd[count]);
			}
			else
				arr[count].addActionListener((event) -> System.exit(0));
		}		
		mb.add(fileM);
	}
	
	private void FileListener(ActionListener fileL)
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
		JMenuItem aClass = new JMenuItem("Create Class");
		JMenuItem dClass = new JMenuItem("Delete Class");
		JMenuItem rClass = new JMenuItem("Rename Class");

		JMenuItem[] arr = {oClass, aClass, dClass, rClass};
		String[] text = {"Open Class", "Add Class", "Delete Class", "Rename Class"};
		String[] comd = {"Open", "Add", "Delete", "Rename"};

		for(int i = 0; i < 4; ++i)
		{
			classM.add(arr[i]);
			arr[i].setToolTipText(text[i]);
			arr[i].setActionCommand(comd[i]);
		}
		mb.add(classM);	
	}
	
	private void ClassListener(ActionListener classL)
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
		JMenuItem vField = new JMenuItem("Change Visability of Field");

		JMenuItem met = new JMenuItem("Create Method");
    	JMenuItem dMethod = new JMenuItem("Delete Method");
		JMenuItem rMethod = new JMenuItem("Rename Method");
		JMenuItem vMethod = new JMenuItem("Change Visability of Method");
		
		JMenuItem param = new JMenuItem("Create Parameter");
    	JMenuItem dParam = new JMenuItem("Delete Parameter");
    	JMenuItem rParam = new JMenuItem("Rename Parameter");

		JMenuItem[] arr = {field, dField, rField, vField, met, dMethod, rMethod, vMethod, param, dParam, rParam};
		String[] text = {"Create Field", "Delete Field", "Rename Field", "Change Visability of Field", "Create Method", "Delete Method", "Rename Method", "Change Visability of Method", "Create Parameter", "Delete Parameter", "Rename Parameter"};
		String[] command = {"field", "dField", "rField", "vField", "cMethod", "dMethod", "rMethod", "vMethod", "param", "dParam", "rParam"};

		for(int count = 0; count < 11; ++count)
		{
			fieldM.add(arr[count]);
			arr[count].setToolTipText(text[count]);
			arr[count].setActionCommand(command[count]);	
		}
		mb.add(fieldM);
	}
	
	private void FieldListener(ActionListener fieldL)
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

		JMenuItem in = new JMenuItem("Inheritence");
		JMenuItem ag = new JMenuItem("Aggregation");
		JMenuItem comp = new JMenuItem("Composition");
		JMenuItem re = new JMenuItem("Realization");
		JMenuItem cRelat = new JMenuItem("Change Relationship");
		JMenuItem dRelat = new JMenuItem("Delete Relationship");

		JMenuItem[] arr = {in, ag, comp, re, cRelat, dRelat};
		String[] names = {"Inheritence", "Aggregation", "Composition", "Realization", "Change Relationship", "Delete Relationship"};
		String[] comd = {"I", "A", "C", "R", "cRelat", "dRelat"};

		for(int i = 0; i < 6; ++i)
		{
			relaM.add(arr[i]);
			arr[i].setToolTipText(names[i]);
			arr[i].setActionCommand(comd[i]);
		}
		mb.add(relaM);
	}
	
	private void RelationshipListener(ActionListener relatL)
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
		
        FileListener(fileL);
        ClassListener(classL);
        FieldListener(fieldL);
		RelationshipListener(relatL);
		
		System.out.println("finished listeners: GUIViews()");
    }
	
	public void start()
    {
        window();
        refresh();
    }
	
	public void createNewClass(ClassObject className)
	{
		classPanel(className);
		refresh();
	}
	
	public void deleteOldClass(String className)
	{
		deleteClassPanel(className);
		refresh();
	}
	
	/*public void updateNewClass(String oldClassName, String newClassName)
	{
		JPanel pn = classes.get(oldClassName);
		classes.remove(oldClassName);
		classes.put(newClassName, pn);
		refresh();
	}*/
	
	private void classPanel(ClassObject aClass)
	{
		System.out.println("made the class panel for display: GUIViews()");
        
        JPanel classP = new JPanel();
        classP.setVisible(true);

        JTextArea classTxt = new JTextArea(aClass.getName());
        classTxt.setEditable(false);
        classP.add(classTxt);
        classes.put(aClass.getName(), classP);
        classP.setSize(classTxt.getSize());

        Border classBd = BorderFactory.createLineBorder(Color.RED);
        classTxt.setBorder(classBd);

        Border boxBd = BorderFactory.createLineBorder(Color.BLACK);
        classP.setBorder(boxBd);
        pWindow.add(classP);	
	}

	public void onUpdate(WorkingProject p)
	{
		System.out.println("Made it to onUpdate: GUIViews()");
		Set<String> newClasses = p.getClassNames();
		for(String className: newClasses)
		{
			if(!classes.containsKey(className))
			{
				createNewClass(p.getClass(className));
			}
			onUpdate(p.getClass(className));
		}
		for(String className: classes.keySet())
		{
			if(!newClasses.contains(className))
			{
				deleteOldClass(className);
			}
		}
		refresh();
	}

	public void onUpdate(ClassObject c)
	{
		JPanel panel = classes.get(c.getName());
        panel.removeAll();
		JTextArea classTxt = new JTextArea(c.getName());
        classTxt.setEditable(false);
        Border classBd = BorderFactory.createLineBorder(Color.RED);
        Border fieldBd = BorderFactory.createLineBorder(Color.GREEN);
        Border methodBd = BorderFactory.createLineBorder(Color.BLUE);
        classTxt.setBorder(classBd);
        panel.add(classTxt);
        for (String fieldName : c.getFieldNames())
        {
            JTextArea fieldTxt = new JTextArea(c.getField(fieldName).toString());
            fieldTxt.setEditable(false);
            fieldTxt.setBorder(fieldBd);
            panel.add(fieldTxt);
        }
        for (String methodName : c.getMethodNames())
        {
            JTextArea methodTxt = new JTextArea(c.getMethod(methodName).toString());
            methodTxt.setEditable(false);
            methodTxt.setBorder(methodBd);
            panel.add(methodTxt);
        }
        refresh();
	}
	
	private void deleteClassPanel(String aClass)
	{
		pWindow.remove(classes.get(aClass));
		classes.remove(aClass);
	}
	
	public void refresh() 
	{
		pWindow.revalidate();
		pWindow.repaint();
	}

	@Override
	public void createClass(String classes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delClass(String className) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateClass(String oldClass, String updateClassString) {
		// TODO Auto-generated method stub
		
	}

}
