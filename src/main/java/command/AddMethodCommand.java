package command;

import model.Model;

public class AddMethodCommand extends Command {
    private String className;
    private String methodName;
    private String methodType;
    private String methodVisName;

    public AddMethodCommand (Model project, String className, String methodName, String methodType, String methodVisName)
    {
        super(project);
        this.className = className;
        this.methodName = methodName;
        this.methodType = methodType;
        this.methodVisName = methodVisName;
    }

    public void onExecute()
    {
        updateStatus(project.addMethod(className, methodName, methodType, methodVisName));
    }
}
