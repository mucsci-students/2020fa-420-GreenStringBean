package command;

import model.WorkingProject;

public class ChangeMethodTypeCommand extends Command {
    private String className;
    private String methodName;
    private String newMethodType;

    public ChangeMethodTypeCommand (WorkingProject project, String className, String methodName, String newMethodType)
    {
        super(project);
        this.className = className;
        this.methodName = methodName;
        this.newMethodType = newMethodType;
    }

    public void onExecute()
    {
        updateStatus(project.changeMethodType(className, methodName, newMethodType));
    }
}
