package command;

import model.WorkingProject;

public class RemoveClassCommand extends Command {
    private WorkingProject project;
    private String className;

    public RemoveClassCommand (WorkingProject project, String className)
    {
        super();
        this.project = project;
        this.className = className;
    }

    public void execute()
    {
        updateStatus(project.removeClass(className));
    }
}
