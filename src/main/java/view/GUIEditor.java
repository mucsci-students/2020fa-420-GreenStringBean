package view;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class GUIEditor extends GUIViews {
	   private JFrame win;
	   private JMenu me;

	   public GUIEditor()
	   {
		   win = new JFrame("UML");
		   me = new JMenu();
	       win.setLayout(new GridLayout(5,5));
	       win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       win.setSize(800,750);
	       makeMenu(win);
	       win.setJMenuBar(getMenuBar());
	       win.setVisible(true);
	   }

	   public JFrame getMainWindow()
	   {
		   return win;
	   }
}

