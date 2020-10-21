package command;

import model.WorkingProject;

public class ChangeMethodTypeCommand extends Command {
    private WorkingProject project;
    private String className;
    private String methodName;
    private String newMethodType;

    public ChangeMethodTypeCommand (WorkingProject project, String className, String methodName, String newMethodType)
    {
        super();
        this.project = project;
        this.className = className;
        this.methodName = methodName;
        this.newMethodType = newMethodType;
    }

    public void execute()
    {
        updateStatus(project.changeMethodType(className, methodName, newMethodType));
    }
}
