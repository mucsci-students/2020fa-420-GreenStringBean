package view;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WindowResizeListener implements ComponentListener {
    MenuViews view;

    public WindowResizeListener(MenuViews view)
    {
        this.view = view;
    }

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
