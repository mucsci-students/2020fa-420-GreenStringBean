package controller;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIStartScreen 
{
    private GUIController controller;
    private JFrame win;
    private JPanel bPanel;
    private JButton strtB;
    private JButton closeB;

    /**
     * Method that creates a window for a user to start or close GUI
     */
    public GUIStartScreen(GUIController controller)
    {
        this.controller = controller;
        win = new JFrame("UML");
        bPanel = new JPanel();
        strtB = new JButton("Lets Get Started!");
        closeB = new JButton("Close GUI");

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
        ButtonClickListener listener = new ButtonClickListener();
    	strtB.setActionCommand("Start");
    	strtB.addActionListener(listener);
        closeB.setActionCommand("Close");
        closeB.addActionListener(listener);
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
                controller.start();
                win.setVisible(false);
            }
            else if(cmd.equals("Close"))
            {
    			System.exit(0);
    		}
    	}
    }
}
