package command;

import model.WorkingProject;

public class RenameClassCommand extends Command {
    private WorkingProject project;
    private String oldClassName;
    private String newClassName;

    public RenameClassCommand (WorkingProject project, String oldClassName, String newClassName)
    {
        super();
        this.project = project;
        this.oldClassName = oldClassName;
        this.newClassName = newClassName;
    }

    public void execute()
    {
        updateStatus(project.renameClass(oldClassName, newClassName));
    }
}
