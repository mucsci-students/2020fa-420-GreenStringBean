import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.GridLayout;

public class UMLwindow {
	   private JFrame win;
	   private Menu me;
	   
	   public UMLwindow()
	   {
		   win = new JFrame("UML");
		   me = new Menu();
	       win.setLayout(new GridLayout(5,5));
	       win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       win.setSize(800,750);
	       me.makeMenu(win);
	       win.setJMenuBar(me.getMenuBar());
	       win.setVisible(true);
	   }
	   
	   public JFrame getMainWindow()
	   {
		   return win;
	   }
}
