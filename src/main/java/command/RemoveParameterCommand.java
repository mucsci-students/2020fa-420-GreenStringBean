package command;

import model.WorkingProject;

public class RemoveParameterCommand extends Command {
    private WorkingProject project;
    private String className;
    private String methodName;
    private String paramName;

    public RemoveParameterCommand (WorkingProject project, String className, String methodName, String paramName)
    {
        super();
        this.project = project;
        this.className = className;
        this.methodName = methodName;
        this.paramName = paramName;
    }

    public void execute()
    {
        updateStatus(project.removeParameter(className, methodName, paramName));
    }
}
