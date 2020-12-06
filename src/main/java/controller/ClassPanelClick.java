package controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import javax.swing.JLayeredPane;
import view.GUIView;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class ClassPanelClick implements MouseListener, MouseMotionListener
{
    private int dragX, dragY;
	private GUIView view;
    private JPanel panel;
    private JPopupMenu menu;

    /**
     * Constructor for a class panel action performer
     * @param view  view of the GUI that takes in user input
     * @param panel panel thats being manipulated
     * @param menu  menu for right click action
     */
    public ClassPanelClick(GUIView view, JPanel panel, JPopupMenu menu)
	{
		this.view = view;
        this.panel = panel;
        this.menu = menu;
	}

    /**
     * Get the starting location of a class panel then when a mouse is dragged
     * it sets the loaction it was released on.
     * @param e mouse event listener
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        Point parentPos = panel.getParent().getLocationOnScreen();
        panel.setLocation((e.getXOnScreen() - parentPos.x - dragX), (e.getYOnScreen() - parentPos.y - dragY));
        view.contain(panel);
        view.refresh();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * When mouse is pressed if there is a right click it will show a menu or
     * a left click will start a mouse drag action. 
     * @param e mouse event listener
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.isPopupTrigger() && menu != null)
        {
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
        else
        {
            dragX = e.getX();
            dragY = e.getY();

            if (e.getComponent() instanceof JTextArea || e.getComponent() instanceof JSeparator)
            {
                Point childPos = e.getComponent().getLocation();
                dragX += childPos.x;
                dragY += childPos.y;
            }

            ((JLayeredPane)panel.getParent()).moveToFront(panel);
        }
    }

    /** 
     * Set class panels location when mouse is released
     * @param e mouse event listener
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.isPopupTrigger() && menu != null)
        {
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        if(e.getComponent() instanceof JTextArea)
        {
            Color borderColor =  ((LineBorder)panel.getBorder()).getLineColor();
            e.getComponent().setForeground(borderColor.equals(Color.BLACK) ? Color.RED : Color.CYAN);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        if(e.getComponent() instanceof JTextArea)
        {
            e.getComponent().setForeground(((LineBorder)panel.getBorder()).getLineColor());
        }
    }

}