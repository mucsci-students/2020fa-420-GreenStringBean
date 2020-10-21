package command;

import model.WorkingProject;

public class RenameMethodCommand extends Command {
    private WorkingProject project;
    private String className;
    private String oldMethodName;
    private String newMethodName;

    public RenameMethodCommand (WorkingProject project, String className, String oldMethodName, String newMethodName)
    {
        super();
        this.project = project;
        this.className = className;
        this.oldMethodName = oldMethodName;
        this.newMethodName = newMethodName;
    }

    public void execute()
    {
        updateStatus(project.renameMethod(className, oldMethodName, newMethodName));
    }
}
