package command;

import model.Model;

public class RemoveMethodCommand extends Command {
    private String className;
    private String methodName;

    public RemoveMethodCommand (Model project, String className, String methodName)
    {
        super(project);
        this.className = className;
        this.methodName = methodName;
    }

    public void onExecute()
    {
        updateStatus(project.removeMethod(className, methodName));
    }
}
