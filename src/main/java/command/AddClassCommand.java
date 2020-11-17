package command;

import model.Model;

public class AddClassCommand extends Command {
    
    private String className;

    public AddClassCommand (Model project, String className)
    {
        super(project);
        this.className = className;
    }

    public void onExecute()
    {
        updateStatus(project.addClass(className));
    }
}
