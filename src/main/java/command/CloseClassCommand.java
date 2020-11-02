package command;

import model.WorkingProject;

public class CloseClassCommand extends Command {
    private String className;

    public CloseClassCommand (WorkingProject project, String className)
    {
        super(project);
        this.className = className;
    }

    public void onExecute()
    {
        updateStatus(project.closeClass(className));
    }
}
