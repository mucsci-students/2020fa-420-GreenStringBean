package command;

import model.WorkingProject;

public class CloseClassCommand extends Command {
    private WorkingProject project;
    private String className;

    public CloseClassCommand (WorkingProject project, String className)
    {
        super();
        this.project = project;
        this.className = className;
    }

    public void execute()
    {
        updateStatus(project.closeClass(className));
    }
}
