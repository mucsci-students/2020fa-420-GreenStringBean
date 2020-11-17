package command;

import model.Model;

public class CloseClassCommand extends Command {
    private String className;

    public CloseClassCommand (Model project, String className)
    {
        super(project);
        this.className = className;
    }

    public void onExecute()
    {
        updateStatus(project.closeClass(className));
    }
}
