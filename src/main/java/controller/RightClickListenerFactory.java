package controller;
import view.MenuViews;

public class RightClickListenerFactory {
    
    private MenuViews view;
    private HelperControllers controller;

    public RightClickListenerFactory(MenuViews v, HelperControllers c)
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
