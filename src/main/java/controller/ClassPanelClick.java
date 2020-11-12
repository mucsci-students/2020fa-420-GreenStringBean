package controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import view.MenuViews;
import java.awt.event.MouseEvent;

public class ClassPanelClick implements MouseListener, MouseMotionListener
{
    private int dragX, dragY;
	//private MenuViews view;
    //private HelperControllers controller;
    private JPanel panel;

    public ClassPanelClick(JPanel panel)
	{
		//this.view = v;
        //this.controller = c;
        this.panel = panel;
	}

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        panel.setLocation((e.getXOnScreen()-dragX), (e.getYOnScreen()-dragY));
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
        // TODO Auto-generated method stub
        System.out.println("Got to ClassPanelClick() setting coords");
        dragX = e.getX();
        dragY = e.getY();
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