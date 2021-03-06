package command;

import model.Model;

public class AddParameterCommand extends Command {
    private String className;
    private String methodName;
    private String paramName;
    private String paramType;

    public AddParameterCommand (Model project, String className, String methodName, String paramName, String paramType)
    {
        super(project);
        this.className = className;
        this.methodName = methodName;
        this.paramName = paramName;
        this.paramType = paramType;
    }

    public void onExecute()
    {
        updateStatus(project.addParameter(className, methodName, paramName, paramType));
    }
}
