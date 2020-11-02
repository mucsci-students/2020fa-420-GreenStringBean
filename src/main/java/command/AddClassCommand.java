package command;

import model.WorkingProject;

public class AddClassCommand extends Command {
    
    private String className;

    public AddClassCommand (WorkingProject project, String className)
    {
        super(project);
        this.className = className;
    }

    public void onExecute()
    {
        updateStatus(project.addClass(className));
    }
}
