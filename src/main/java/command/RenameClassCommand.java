package command;

import model.Model;

public class RenameClassCommand extends Command {
    private String oldClassName;
    private String newClassName;

    public RenameClassCommand (Model project, String oldClassName, String newClassName)
    {
        super(project);
        this.oldClassName = oldClassName;
        this.newClassName = newClassName;
    }

    public void onExecute()
    {
        updateStatus(project.renameClass(oldClassName, newClassName));
    }
}
