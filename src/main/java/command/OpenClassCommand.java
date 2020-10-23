package command;

import model.WorkingProject;

public class OpenClassCommand extends Command {
    private String className;

    public OpenClassCommand (WorkingProject project, String className)
    {
        super(project);
        this.className = className;
    }

    public void onExecute()
    {
        updateStatus(project.openClass(className));
    }
}
