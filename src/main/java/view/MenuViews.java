package view;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public interface MenuViews
{
	String getText(String string);

	void addListeners(ActionListener fileClick, ActionListener classClick,
			ActionListener fieldClick, ActionListener relationshipClick);	

	void start();

	void createClass(String classes);

	void delClass(String className);

	void updateClass(String oldClass, String updateClassString);
	
}
