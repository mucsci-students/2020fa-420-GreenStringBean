package view;
import javax.swing.*;

public class GUIEditor extends GUIViews 
{
	   private JFrame win;

	   /**
		* Sets up the GUI UML editor with a clean window
	    */
	   public GUIEditor()
	   {
		   super();
		   start();
	   }

	   public JFrame getMainWindow()
	   {
		   return win;
	   }
}

