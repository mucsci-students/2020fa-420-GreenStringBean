package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import controller.HelperControllers;
import java.awt.event.*;
import java.awt.FlowLayout;

public class GUIStartScreen 
{
    private JFrame win = new JFrame("UML");
    private JPanel bPanel = new JPanel();
    private JButton strtB = new JButton("Lets Get Started!");
    private JButton closeB = new JButton("Close GUI");

    /**
     * Method that creates a window for a user to start or close GUI
     */
    public GUIStartScreen()
    {
        //Creates a window 
        windowSetup();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setSize(400, 400);
        win.setLayout(new FlowLayout());

        bPanel.add(strtB);
        bPanel.add(closeB);

        win.add(bPanel);
        win.pack();
        win.setVisible(true);    
    }

    /**
     * Method to add buttons and there listeners to the start window
     */
    private void windowSetup()
    {
    	strtB.setActionCommand("Start");
    	strtB.addActionListener(new ButtonClickListener());
    	closeB.setActionCommand("Close");
    }

    /**
     * Actions for starting or closing GUI
     */
    private class ButtonClickListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) {
    		String cmd = e.getActionCommand();
    		if(cmd.equals("Start")) 
    		{
                 GUIEditor uml = new GUIEditor();
                 HelperControllers hc = new HelperControllers(uml);
                 win.setVisible(false);
    		}else if(cmd.equals("Close")){
    			System.exit(0);
    		}
    	}
    }
}
