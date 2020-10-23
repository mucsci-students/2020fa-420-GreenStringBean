package command;

import model.WorkingProject;

public class RenameParameterCommand extends Command {
    private WorkingProject project;
    private String className;
    private String methodName;
    private String oldParamName;
    private String newParamName;

    public RenameParameterCommand (WorkingProject project, String className, String methodName, String oldParamName, String newParamName)
    {
        super();
        this.project = project;
        this.className = className;
        this.methodName = methodName;
        this.oldParamName = oldParamName;
        this.newParamName = newParamName;
    }

    public void execute()
    {
        updateStatus(project.renameParameter(className, methodName, oldParamName, newParamName));
    }
}
