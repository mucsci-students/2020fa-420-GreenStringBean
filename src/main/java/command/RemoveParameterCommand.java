package command;

import model.Model;

public class RemoveParameterCommand extends Command {
    private String className;
    private String methodName;
    private String paramName;

    public RemoveParameterCommand (Model project, String className, String methodName, String paramName)
    {
        super(project);
        this.className = className;
        this.methodName = methodName;
        this.paramName = paramName;
    }

    public void onExecute()
    {
        updateStatus(project.removeParameter(className, methodName, paramName));
    }
}
