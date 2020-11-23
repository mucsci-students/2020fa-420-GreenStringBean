package view;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WindowResizeListener implements ComponentListener 
{
    GUIView view;

    /**
     * Constructor for window listener
     * @param view view of the GUI that takes in user input
     */
    public WindowResizeListener(GUIView view)
    {
        this.view = view;
    }

    /**
     * When a resize is done, get everything stored in the view
     * @param e component action 
     */
    @Override
    public void componentResized(ComponentEvent e)
    {
        view.containAll();
    }

    @Override
    public void componentMoved(ComponentEvent e){
        // TODO Auto-generated method stub

    }

    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub

    }
    
}
