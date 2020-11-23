package controller;
import view.GUIView;

public class RightClickListenerFactory 
{
    
    private GUIView view;
    private GUIController controller;

    /**
     * Constructor for right click actions
     * @param v view of the GUI that takes in user input 
     * @param c controller to help the view actions perform
     */
    public RightClickListenerFactory(GUIView v, GUIController c)
    {
        this.view = v;
        this.controller = c;
    }

    /**
     * Makes a new ClassRightClick with user actions
     * @param className name of the class
     * @return new ClassRightClick controller
     */
    public ClassRightClick getClassRightClick(String className)
    {
        return new ClassRightClick(view, controller, className);
    }

    /**
     * Makes a new FieldRightClick with user actions
     * @param className name of the class
     * @param fieldName name of the field
     * @return new FieldRightClick controller
     */
    public FieldRightClick getFieldRightClick(String className, String fieldName)
    {

        return new FieldRightClick(view, controller, className, fieldName);
    }

    /**
     * Makes a new MethodRightClick with user actions
     * @param className name of the class
     * @param methodName name of the method
     * @return new MethodRightClick controller
     */
    public MethodRightClick getMethodRightClick(String className, String methodName)
    {

        return new MethodRightClick(view, controller, className, methodName);
    }

}
