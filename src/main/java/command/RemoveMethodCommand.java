package command;

import model.WorkingProject;

public class RemoveMethodCommand extends Command {
    private WorkingProject project;
    private String className;
    private String methodName;

    public RemoveMethodCommand (WorkingProject project, String className, String methodName)
    {
        super();
        this.project = project;
        this.className = className;
        this.methodName = methodName;
    }

    public void execute()
    {
        updateStatus(project.removeMethod(className, methodName));
    }
}
