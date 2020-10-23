package command;

import model.WorkingProject;

public class RenameParameterCommand extends Command {
    private String className;
    private String methodName;
    private String oldParamName;
    private String newParamName;

    public RenameParameterCommand (WorkingProject project, String className, String methodName, String oldParamName, String newParamName)
    {
        super(project);
        this.className = className;
        this.methodName = methodName;
        this.oldParamName = oldParamName;
        this.newParamName = newParamName;
    }

    public void onExecute()
    {
        updateStatus(project.renameParameter(className, methodName, oldParamName, newParamName));
    }
}
