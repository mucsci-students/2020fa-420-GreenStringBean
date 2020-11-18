package controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Listener for "add" and "remove" buttons in parameter list when creating a
 * method or editing its list of parameters.
 */

public class ParamButtonClick implements ActionListener {
	private JPanel paramEntriesPanel;
	private List<JTextField> paramNameFields;
	private List<JTextField> paramTypeFields;

	/**
	 * Cronstructor for an action performer with the views input and a helper
	 * controller to manipulate the view.
	 * 
	 * @param v view of the GUI that takes in user input
	 * @param c controller to help the view actions perform correctly
	 */
	public ParamButtonClick(JPanel paramEntriesPanel, List<JTextField> paramNameFields, List<JTextField> paramTypeFields)
	{
		this.paramEntriesPanel = paramEntriesPanel;
		this.paramNameFields = paramNameFields;
		this.paramTypeFields = paramTypeFields;
		updateEntriesPanel();
	}

	/**
	 * Method for handling actions performed on a class
	 * 
	 * @param e is the action that was performed by the user and is in the form of a
	 *          command
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Add")) 
		{
			paramNameFields.add(new JTextField());
			paramTypeFields.add(new JTextField());
			updateEntriesPanel();
		} 
		else if (cmd.equals("Remove") && !paramNameFields.isEmpty()) 
		{
			paramNameFields.remove(paramNameFields.size() - 1);
			paramTypeFields.remove(paramTypeFields.size() - 1);
			updateEntriesPanel();
		}
	}

	private void updateEntriesPanel()
	{
		paramEntriesPanel.removeAll();
		for (int i = 0; i < paramNameFields.size(); ++i)
		{
			paramEntriesPanel.add(new JLabel("Data Type:"));
			paramEntriesPanel.add(new JLabel("Name:"));
			paramEntriesPanel.add(paramTypeFields.get(i));
			paramEntriesPanel.add(paramNameFields.get(i));
		}
		Container dialogWindow = paramEntriesPanel.getTopLevelAncestor();
		if (dialogWindow != null)
		{
			dialogWindow.setSize(dialogWindow.getPreferredSize());
		}
	}
}