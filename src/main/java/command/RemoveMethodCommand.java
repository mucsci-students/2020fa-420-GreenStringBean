package command;

import model.WorkingProject;

public class RemoveMethodCommand extends Command {
    private String className;
    private String methodName;

    public RemoveMethodCommand (WorkingProject project, String className, String methodName)
    {
        super(project);
        this.className = className;
        this.methodName = methodName;
    }

    public void onExecute()
    {
        updateStatus(project.removeMethod(className, methodName));
    }
}
