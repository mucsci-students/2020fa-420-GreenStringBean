package command;

import model.Model;

public class OpenClassCommand extends Command {
    private String className;

    public OpenClassCommand (Model project, String className)
    {
        super(project);
        this.className = className;
    }

    public void onExecute()
    {
        updateStatus(project.openClass(className));
    }
}
