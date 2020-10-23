package command;

import model.WorkingProject;

public class AddMethodCommand extends Command {
    private String className;
    private String methodName;
    private String methodType;

    public AddMethodCommand (WorkingProject project, String className, String methodName, String methodType)
    {
        super(project);
        this.className = className;
        this.methodName = methodName;
        this.methodType = methodType;
    }

    public void onExecute()
    {
        updateStatus(project.addMethod(className, methodName, methodType));
    }
}
