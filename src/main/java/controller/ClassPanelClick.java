package controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLayeredPane;
import view.MenuViews;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class ClassPanelClick implements MouseListener, MouseMotionListener
{
    private int dragX, dragY;
	private MenuViews view;
    //private HelperControllers controller;
    private JPanel panel;

    public ClassPanelClick(MenuViews view, JPanel panel)
	{
		this.view = view;
        //this.controller = c;
        this.panel = panel;
	}

    @Override
    public void mouseDragged(MouseEvent e) {
        Point parentPos = panel.getParent().getLocationOnScreen();
        panel.setLocation((e.getXOnScreen() - parentPos.x - dragX), (e.getYOnScreen() - parentPos.y - dragY));
        view.contain(panel);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        dragX = e.getX();
        dragY = e.getY();

        if (e.getComponent() instanceof JTextArea)
        {
            Point childPos = e.getComponent().getLocation();
            dragX += childPos.x;
            dragY += childPos.y;
        }

        ((JLayeredPane)panel.getParent()).moveToFront(panel);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}