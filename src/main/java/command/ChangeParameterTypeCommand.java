package command;

import model.WorkingProject;

public class ChangeParameterTypeCommand extends Command {
    private String className;
    private String methodName;
    private String paramName;
    private String newParamType;

    public ChangeParameterTypeCommand (WorkingProject project, String className, String methodName, String paramName, String newParamType)
    {
        super(project);
        this.className = className;
        this.methodName = methodName;
        this.paramName = paramName;
        this.newParamType = newParamType;
    }

    public void onExecute()
    {
        updateStatus(project.changeParameterType(className, methodName, paramName, newParamType));
    }
}
