package command;

import model.WorkingProject;

public class AddParameterCommand extends Command {
    private WorkingProject project;
    private String className;
    private String methodName;
    private String paramName;
    private String paramType;

    public AddParameterCommand (WorkingProject project, String className, String methodName, String paramName, String paramType)
    {
        super();
        this.project = project;
        this.className = className;
        this.methodName = methodName;
        this.paramName = paramName;
        this.paramType = paramType;
    }

    public void execute()
    {
        updateStatus(project.addParameter(className, methodName, paramName, paramType));
    }
}
