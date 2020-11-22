package controller;
import view.GUIView;

/**
 * The right click listener factory is created by a GUI controller and given to
 * a GUI view. It is used to dynamically create listeners for right-clickable
 * components in the view that can interact with the controller.
 */
public class RightClickListenerFactory {
    
    private GUIView view;
    private GUIController controller;

    public RightClickListenerFactory(GUIView v, GUIController c)
    {
        this.view = v;
        this.controller = c;
    }

    public ClassRightClick getClassRightClick(String className)
    {
        return new ClassRightClick(view, controller, className);
    }

    public FieldRightClick getFieldRightClick(String className, String fieldName)
    {

        return new FieldRightClick(view, controller, className, fieldName);
    }

    public MethodRightClick getMethodRightClick(String className, String methodName)
    {

        return new MethodRightClick(view, controller, className, methodName);
    }

}
