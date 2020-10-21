package command;

import model.WorkingProject;

public class OpenClassCommand extends Command {
    private WorkingProject project;
    private String className;

    public OpenClassCommand (WorkingProject project, String className)
    {
        super();
        this.project = project;
        this.className = className;
    }

    public void execute()
    {
        updateStatus(project.openClass(className));
    }
}
