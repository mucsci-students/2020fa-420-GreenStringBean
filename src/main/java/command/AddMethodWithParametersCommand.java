package command;

import java.util.List;

import model.Model;

public class AddMethodWithParametersCommand extends Command {
    private String className;
    private String methodName;
    private String methodType;
    private String methodVisName;
    private List<String> paramNames;
    private List<String> paramTypes;

    public AddMethodWithParametersCommand (Model project, String className, String methodName, String methodType, String methodVisName, List<String> paramNames, List<String> paramTypes)
    {
        super(project);
        this.className = className;
        this.methodName = methodName;
        this.methodType = methodType;
        this.methodVisName = methodVisName;
        this.paramNames = paramNames;
        this.paramTypes = paramTypes;
    }

    public void onExecute()
    {
        updateStatus(project.addMethod(className, methodName, methodType, methodVisName, paramNames, paramTypes));
    }
}
