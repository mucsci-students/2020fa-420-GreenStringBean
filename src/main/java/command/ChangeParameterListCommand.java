package command;

import java.util.List;

import model.Model;

public class ChangeParameterListCommand extends Command {
    private String className;
    private String methodName;
    private List<String> paramNames;
    private List<String> paramTypes;

    public ChangeParameterListCommand (Model project, String className, String methodName, List<String> paramNames, List<String> paramTypes)
    {
        super(project);
        this.className = className;
        this.methodName = methodName;
        this.paramNames = paramNames;
        this.paramTypes = paramTypes;
    }

    public void onExecute()
    {
        updateStatus(project.changeParameterList(className, methodName, paramNames, paramTypes));
    }
}
