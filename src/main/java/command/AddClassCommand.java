package command;

import model.WorkingProject;

public class AddClassCommand extends Command {
    private WorkingProject project;
    private String className;

    public AddClassCommand (WorkingProject project, String className)
    {
        super();
        this.project = project;
        this.className = className;
    }

    public void execute()
    {
        updateStatus(project.addClass(className));
    }
}
