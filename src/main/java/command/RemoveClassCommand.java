package command;

import model.WorkingProject;

public class RemoveClassCommand extends Command {
    private String className;

    public RemoveClassCommand (WorkingProject project, String className)
    {
        super(project);
        this.className = className;
    }

    public void onExecute()
    {
        updateStatus(project.removeClass(className));
    }
}
