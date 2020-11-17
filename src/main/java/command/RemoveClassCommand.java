package command;

import model.Model;

public class RemoveClassCommand extends Command {
    private String className;

    public RemoveClassCommand (Model project, String className)
    {
        super(project);
        this.className = className;
    }

    public void onExecute()
    {
        updateStatus(project.removeClass(className));
    }
}
