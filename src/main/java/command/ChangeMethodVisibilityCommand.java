package command;

import model.WorkingProject;

public class ChangeMethodVisibilityCommand extends Command {
    private String className;
    private String methodName;
    private String newMethodVisName;

    public ChangeMethodVisibilityCommand (WorkingProject project, String className, String methodName, String newMethodVisName )
    {
        super(project);
        this.className = className;
        this.methodName = methodName;
        this.newMethodVisName = newMethodVisName;
    }

    public void onExecute()
    {
        updateStatus(project.changeMethodVisibility(className, methodName, newMethodVisName));
    }
}
