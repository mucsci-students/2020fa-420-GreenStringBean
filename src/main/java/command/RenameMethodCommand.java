package command;

import model.WorkingProject;

public class RenameMethodCommand extends Command {
    private String className;
    private String oldMethodName;
    private String newMethodName;

    public RenameMethodCommand (WorkingProject project, String className, String oldMethodName, String newMethodName)
    {
        super(project);
        this.className = className;
        this.oldMethodName = oldMethodName;
        this.newMethodName = newMethodName;
    }

    public void onExecute()
    {
        updateStatus(project.renameMethod(className, oldMethodName, newMethodName));
    }
}
